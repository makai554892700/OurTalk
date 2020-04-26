package www.mys.com.ourtalk.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import www.mys.com.ourtalk.utils.LogUtils;

import java.util.Date;

@Component
public class CommonScheduler {

    private static boolean is1Running;
    private static int thread1Times;

    @Scheduled(cron = "10 */1 * * * ?")
    public void checkOnline() {
        if (thread1Times++ % 5 == 1) {
            LogUtils.log("每5分钟执行一次.checkOnline.Time=" + new Date());
        }
        if (!is1Running) {
            is1Running = true;
            is1Running = false;
        }
    }
}
