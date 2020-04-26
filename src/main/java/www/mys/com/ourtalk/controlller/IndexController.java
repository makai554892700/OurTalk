package www.mys.com.ourtalk.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import www.mys.com.ourtalk.api.IndexApi;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.common.exception.BaseException;
import www.mys.com.ourtalk.common.repeatsubmit.RePeatSubmitUtils;
import www.mys.com.ourtalk.utils.LogUtils;
import www.mys.com.ourtalk.utils.file.FileUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
public class IndexController implements IndexApi {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RePeatSubmitUtils rePeatSubmitUtils;

    @Override
    public String test() {

        StringBuilder tempStr = new StringBuilder(request.getRequestURI());
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> kv : params.entrySet()) {
            for (String str : kv.getValue()) {
                LogUtils.log(kv.getKey() + "=" + str);
                tempStr.append(str);
            }
        }
        if ("post".equals(request.getMethod().toLowerCase())) {
            byte[] datas = readAsBytes(request);
            tempStr.append(new String(datas, StandardCharsets.UTF_8));
        }
        if (rePeatSubmitUtils.isRePeatSubmit(getClass() + "test", tempStr.toString())) {
            throw new BaseException(StaticParam.RE_PEAT_SUBMIT, -1);
        }
        return "Hello world.";
    }

    public static byte[] readAsBytes(HttpServletRequest request) {
        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
            in.read(buffer, 0, len);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    @Override
    public void imgs(HttpServletResponse response) throws Exception {
        File file = new File(StaticParam.ROOT_PATH + File.separator
                + StaticParam.IMG_PATH + File.separator
                + request.getRequestURI().replace("/imgs", "/"));
        FileInputStream inputStream = new FileInputStream(file);
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        out.write(FileUtils.inputStream2Bytes(inputStream));
        inputStream.close();
        out.flush();
        out.close();
    }

}
