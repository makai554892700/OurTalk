package www.mys.com.ourtalk.utils.net;

import www.mys.com.ourtalk.common.base.StaticParam;

public class EncryptUtils {

    public static void main(String[] args) {
    }

    public static String encrypt(String data) {
        return RC4Utils.enCode(StaticParam.ENCRYPT_KEY, data);
    }

    public static String decrypt(String data) {
        return RC4Utils.deCode(StaticParam.ENCRYPT_KEY, data);
    }

}
