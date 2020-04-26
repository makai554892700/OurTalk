package www.mys.com.ourtalk.common.encrypt;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.util.StringUtils;
import www.mys.com.ourtalk.utils.LogUtils;
import www.mys.com.ourtalk.utils.net.EncryptUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DecryptHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;
    private InputStream body;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
        this.headers = inputMessage.getHeaders();
        String content = new BufferedReader(new InputStreamReader(inputMessage.getBody()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        String decryptBody = "";
        if (content.startsWith("{")) {
            LogUtils.log("Unencrypted without decryption:{" + content + "}");
            decryptBody = content;
        } else {
            if (!StringUtils.isEmpty(content)) {
                decryptBody = EncryptUtils.decrypt(content);
            }
//            LogUtils.log("Encrypted data received：{" + content
//                    + "},After decryption：{" + decryptBody + "}");
        }
        this.body = new ByteArrayInputStream(decryptBody.getBytes());
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
