package www.mys.com.ourtalk.common.repeatsubmit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import www.mys.com.ourtalk.utils.LogUtils;
import www.mys.com.ourtalk.utils.net.MD5Utils;

import java.util.concurrent.TimeUnit;

@Component
public class RePeatSubmitUtils {

    public static final String SEND_TEXT_ALL = "STA_";
    private static final String SEND_TEXT_COUNT = "STC_";
    private static final String SEND_TEXT_SUCCESS_COUNT = "STSC_";
    private static final long TIME_DAY = 24 * 60 * 60 * 1000;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean isRePeatSubmit(String... datas) {
        return isRePeatSubmit(10000, datas);
    }

    public void clearMark(String... datas) {
        String redisKey = getKey(datas);
        LogUtils.log("clearMark redisKey=" + redisKey);
        redisTemplate.delete(redisKey);
    }

    public void addSendTextCount(String userName) {
        commonAddCount(SEND_TEXT_COUNT + userName);
    }

    public void addSendTextSuccessCount(String userName) {
        commonAddCount(SEND_TEXT_SUCCESS_COUNT + userName);
    }

    public void clearSendTextLog(String userName) {
        redisTemplate.delete(SEND_TEXT_ALL + userName);
        redisTemplate.delete(SEND_TEXT_COUNT + userName);
        redisTemplate.delete(SEND_TEXT_SUCCESS_COUNT + userName);
    }

    public String getSendTextLog(String userName) {
        return commonGetCount(SEND_TEXT_SUCCESS_COUNT + userName)
                + "/" + commonGetCount(SEND_TEXT_COUNT + userName);
    }

    public boolean isRePeatSubmit(long time, boolean needRefreshTime, String... datas) {
        String redisKey = getKey(datas);
        Boolean hasKey = null;
        try {
            hasKey = redisTemplate.hasKey(redisKey);
        } catch (Exception e) {
            LogUtils.log("e=" + e);
        }
        if (hasKey == null || !hasKey) {
            redisTemplate.opsForValue().set(redisKey, "yes");
            redisTemplate.expire(redisKey, time, TimeUnit.MILLISECONDS);
            return false;
        } else {
            StringBuilder tempStr = new StringBuilder();
            if (datas != null) {
                for (String tempObj : datas) {
                    tempStr.append(tempObj);
                }
            }
            if(needRefreshTime){
                redisTemplate.expire(redisKey, time, TimeUnit.MILLISECONDS);
            }
            LogUtils.log("重复请求:" + tempStr);
            return true;
        }
    }

    public boolean isRePeatSubmit(long time, String... datas) {
        return isRePeatSubmit(time, false, datas);
    }

    private void commonAddCount(String redisKey) {
        Boolean hasKey = null;
        int value = 0;
        try {
            hasKey = redisTemplate.hasKey(redisKey);
        } catch (Exception e) {
            LogUtils.log("e=" + e);
        }
        if (hasKey != null && hasKey) {
            try {
                value = (int) redisTemplate.opsForValue().get(redisKey);
            } catch (Exception e) {
                LogUtils.log("commonAddCount error.redisKey=" + redisKey + ";e=" + e);
            }
        }
        redisTemplate.opsForValue().set(redisKey, value + 1);
        redisTemplate.expire(redisKey, TIME_DAY, TimeUnit.MILLISECONDS);
    }

    private int commonGetCount(String redisKey) {
        Boolean hasKey = null;
        int value = 0;
        try {
            hasKey = redisTemplate.hasKey(redisKey);
        } catch (Exception e) {
            LogUtils.log("e=" + e);
        }
        if (hasKey != null && hasKey) {
            try {
                value = (int) redisTemplate.opsForValue().get(redisKey);
            } catch (Exception e) {
                LogUtils.log("commonGetCount error.redisKey=" + redisKey + ";e=" + e);
            }
        }
        return value;
    }

    private String getKey(String... datas) {
        StringBuilder tempStr = new StringBuilder();
        if (datas != null) {
            for (String tempObj : datas) {
                tempStr.append(tempObj);
            }
        }
        return "request_" + MD5Utils.MD5(tempStr.toString(), true);
    }

}
