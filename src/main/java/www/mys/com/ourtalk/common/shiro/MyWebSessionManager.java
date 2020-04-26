package www.mys.com.ourtalk.common.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import www.mys.com.ourtalk.common.base.StaticParam;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class MyWebSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        Serializable result = super.getSessionId(request, response);
        if (result == null) {
            if (request instanceof HttpServletRequest) {
                result = ((HttpServletRequest) request).getHeader(StaticParam.HEAD_SESSION);
            }
        }
        return result;
    }
}
