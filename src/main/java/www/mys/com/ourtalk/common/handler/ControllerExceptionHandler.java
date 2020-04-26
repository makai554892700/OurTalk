package www.mys.com.ourtalk.common.handler;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import www.mys.com.ourtalk.common.exception.BaseException;
import www.mys.com.ourtalk.utils.LogUtils;
import www.mys.com.ourtalk.utils.ResultUtils;
import www.mys.com.ourtalk.vo.response.Result;

//controller层错误拦截，用于拦截错误页面返回给用户，给用户正确的格式
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseBody
    public Result<String> onError(UnauthenticatedException e) {
        return ResultUtils.field(10086, "请重新登录");
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result<String> onField(BaseException baseException) {
        LogUtils.log("onField error message=" + baseException.getMessage());
        return ResultUtils.field(baseException.getCode(), baseException.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> onError(Exception e) {
        LogUtils.log("onError error message=" + e.getMessage());
        return ResultUtils.field(e.getMessage());
    }

}
