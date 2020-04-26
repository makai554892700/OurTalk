package www.mys.com.ourtalk.common.encrypt;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import www.mys.com.ourtalk.utils.LogUtils;

import java.lang.reflect.Type;

@ControllerAdvice
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {

    private boolean encrypt;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType
            , Class<? extends HttpMessageConverter<?>> converterType) {
        encrypt = methodParameter.getMethod() != null
                && methodParameter.getMethod().isAnnotationPresent(Decrypt.class);
        return encrypt;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage
            , MethodParameter parameter, Type targetType
            , Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter
            , Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (encrypt) {
            try {
                return new DecryptHttpInputMessage(inputMessage);
            } catch (Exception e) {
                LogUtils.log("Decryption failed" + e);
            }
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter
            , Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
