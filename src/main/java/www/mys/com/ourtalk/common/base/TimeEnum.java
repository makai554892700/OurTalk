package www.mys.com.ourtalk.common.base;

public enum TimeEnum {
    FORMAT_DAY_MSEC("yyyy-MM-dd HH:mm:ss.SSS"),
    FORMAT_DAY_SECOND("yyyy-MM-dd HH:mm:ss"),
    FORMAT_INT_DAY("yyyyMMdd"),
    FORMAT_DAY("yyyy-MM-dd");
    private String str;

    TimeEnum(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
