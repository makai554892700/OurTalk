package www.mys.com.ourtalk.utils.net;

import www.mys.com.ourtalk.common.base.StaticParam;

import java.util.Random;

public class HexUtils {

    public static String getHexStr(int len) {
        if (len < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            stringBuffer.append(StaticParam.HEX_STR[random.nextInt(StaticParam.HEX_STR.length)]);
        }
        return stringBuffer.toString();
    }

}
