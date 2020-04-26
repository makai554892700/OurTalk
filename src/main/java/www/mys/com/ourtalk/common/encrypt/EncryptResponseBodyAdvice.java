package www.mys.com.ourtalk.common.encrypt;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import www.mys.com.ourtalk.utils.net.EncryptUtils;

@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private boolean encrypt;

    private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        encrypt = returnType.getMethod() != null && returnType.getMethod().isAnnotationPresent(Encrypt.class);
        return encrypt;
    }

    @Override
    public String beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType
            , Class<? extends HttpMessageConverter<?>> selectedConverterType
            , ServerHttpRequest request, ServerHttpResponse response) {
        Boolean status = encryptLocal.get();
        if (null != status && !status) {
            encryptLocal.remove();
            return body == null ? null : JSON.toJSONString(body);
        }
        if (encrypt) {
            String content = JSON.toJSONString(body);
            String result = EncryptUtils.encrypt(content);
//            LogUtils.log("body=" + body);
//            LogUtils.log("data=" + content);
//            LogUtils.log("encrypt=" + result);
            return result;
        }
        return body == null ? null : JSON.toJSONString(body);
    }
}
