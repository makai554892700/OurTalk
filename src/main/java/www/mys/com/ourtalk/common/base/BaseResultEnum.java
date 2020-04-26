package www.mys.com.ourtalk.common.base;

//返回结果的
public enum BaseResultEnum {
    DATA_ERROR(0, "请求数据出错"),
    NO_USER(1, "用户不存在"),
    DOUBLE_USER_NAME(2, "用户名已被占用"),
    NO_DATA(3, "数据库没有数据"),
    GET_TOKEN_ERROR(4, "获取token失败"),
    OUTSIDE_RESPONSE_ERROR(5, "外部返回数据出错"),
    INSIDE_RESPONSE_ERROR(6, "内部返回数据出错"),
    DATA_ALREADY_EXIST(7, "数据已存在"),
    SQL_ERROR(8, "操作数据时出错"),
    NO_SUCH_DATA(9, "数据不存在"),
    USER_BLOCKED(10, "用户已封锁"),
    LOGIN_ERROR(16, "登录错误"),
    USER_PASS_ERROR(17, "密码格式错误"),
    UNKNOW_ERROR(-1, "未知错误");

    private String msg;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    BaseResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
