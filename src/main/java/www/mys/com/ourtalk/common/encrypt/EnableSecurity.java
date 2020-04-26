package www.mys.com.ourtalk.common.encrypt;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({EncryptResponseBodyAdvice.class, EncryptRequestBodyAdvice.class})
public @interface EnableSecurity {

}
