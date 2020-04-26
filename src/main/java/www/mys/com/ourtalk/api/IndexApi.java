package www.mys.com.ourtalk.api;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

public interface IndexApi {

    @RequestMapping(value = "test")
    public String test();

    @GetMapping(value = {"/imgs/*", "/imgs/**"})
    public void imgs(HttpServletResponse response) throws Exception;

}
