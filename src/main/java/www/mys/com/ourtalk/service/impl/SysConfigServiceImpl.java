package www.mys.com.ourtalk.service.impl;

import org.springframework.stereotype.Service;
import www.mys.com.ourtalk.mapper.SysConfigMapper;
import www.mys.com.ourtalk.pojo.SysConfig;
import www.mys.com.ourtalk.service.SysConfigService;

import javax.annotation.Resource;

@Service("sysConfigServiceImpl")
public class SysConfigServiceImpl implements SysConfigService {

    @Resource(name = "sysConfigMapper")
    private SysConfigMapper sysConfigMapper;

    @Override
    public SysConfig saveSysConfig(String key, String value) {
        return sysConfigMapper.save(new SysConfig(key, value));
    }

    @Override
    public SysConfig getSysConfigByKey(String key) {
        return sysConfigMapper.getByConfigKey(key);
    }

    @Override
    public boolean getValueByKey(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(getValueByKey(key, String.valueOf(defaultValue)));
        } catch (Exception e) {
        }
        return defaultValue;
    }

    @Override
    public int getValueByKey(String key, int defaultValue) {
        try {
            return Integer.parseInt(getValueByKey(key, String.valueOf(defaultValue)));
        } catch (Exception e) {
        }
        return defaultValue;
    }

    @Override
    public float getValueByKey(String key, float defaultValue) {
        try {
            return Float.parseFloat(getValueByKey(key, String.valueOf(defaultValue)));
        } catch (Exception e) {
        }
        return defaultValue;
    }

    @Override
    public double getValueByKey(String key, double defaultValue) {
        try {
            return Double.parseDouble(getValueByKey(key, String.valueOf(defaultValue)));
        } catch (Exception e) {
        }
        return defaultValue;
    }

    @Override
    public long getValueByKey(String key, long defaultValue) {
        try {
            return Long.parseLong(getValueByKey(key, String.valueOf(defaultValue)));
        } catch (Exception e) {
        }
        return defaultValue;
    }

    @Override
    public String getValueByKey(String key, String defaultValue) {
        SysConfig sysConfig = sysConfigMapper.getByConfigKey(key);
        if (sysConfig == null) {
            return defaultValue;
        }
        return sysConfig.getConfigValue();
    }
}
