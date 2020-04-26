package www.mys.com.ourtalk.common.base;

import www.mys.com.ourtalk.utils.net.MD5Utils;

public class StaticParam {

    public static final String[] HEX_STR = new String[]{"0", "1", "2"
            , "3", "4", "5", "6", "7", "8", "9", "A", "B"
            , "C", "D", "E", "F"};

    public static final int SEX_NONE = -1;
    public static final int SEX_MAN = 1;
    public static final int SEX_WOMAN = 2;
    public static final long DAY = 24 * 60 * 60 * 1000;


    public static final String LINE = "\n";
    public static final String OK = "ok";
    public static final String SUCCESS = "success";
    public static final String FORMAT_CONTACT_USER = "{\"appid\":2,\"userid\":%s}";
    public static final String ORDER_MARK = "_";
    public static final String PNG_END = ".png";

    public static final String ENCRYPT_KEY = MD5Utils.MD5("http://imgs.demo.com", false);
    public static final String BASE_HOST = "http://imgs.demo.com";
    public static final String HTML_END = ".html";
    public static final String RE_PEAT_SUBMIT = "请勿重复请求";

    public static final String SHIRO_USER = "shiro_user";
    public static final long SHIRO_MONTH = 30 * 24 * 60 * 60;//一个月
    public static final long SHIRO_EVER = 30 * DAY;//永久
    public static final String SHIRO_PREFIX = "shiro_prefix";
    public static final long SHIRO_SESSION_TIME_OUT = Integer.MAX_VALUE;
    public static final String HEAD_SESSION = "mark";
    public static final int DEFAULT_NODE_PORT = 10086;
    public static final int MAX_RETRY_TIMES = 2;

    public static final int START_PORT = 40000;
    public static final int END_PORT = 60000;
    public static final String EMPTY = "";
    public static final String ZERO = "0";
    public static final String FILE_SPLIT = "/";

    public static String ROOT_PATH;
    public static String DY_VERSION = "1";
    public static final String DY_VERSION_KEY = "dy_version_key";
    public static String IMG_PATH = "imgs";
    public static final String IMG_PATH_KEY = "img_path_key";
    public static String IMG_HOST = "http://img.demo.com/imgs";
    public static final String IMG_HOST_KEY = "img_host_key";
    public static final String NICK_NAME_GROUP = "nick_name_group";
    public static final String FORMAT_USER = "{\"appid\":%s,\"userid\":%s}";

    static {
        try {
            ROOT_PATH = System.getProperty("user.dir");
        } catch (Exception e) {
            System.out.println("root path error.e=" + e);
        }
    }

}
