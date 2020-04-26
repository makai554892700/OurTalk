package www.mys.com.ourtalk.utils;

import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.common.base.TimeEnum;
import www.mys.com.ourtalk.utils.file.AppendFileUtils;
import www.mys.com.ourtalk.utils.file.FileUtils;
import www.mys.com.ourtalk.utils.net.TimeUtils;

import java.util.Date;

public class LogUtils {

    private static final String logRootPath = StaticParam.ROOT_PATH + StaticParam.FILE_SPLIT + "log";
    private static String currentDirPath;

    public static void initRootPath() {
        StringBuilder tempSB = new StringBuilder(logRootPath);
        String timeData = TimeUtils.getTimeZoneDateString(new Date(), 8, TimeEnum.FORMAT_DAY);
        String[] tempTimeData = timeData.split("-");
        if (FileUtils.sureDir(logRootPath) == null) {
            LogUtils.log("sureDir error.dir=" + logRootPath);
            return;
        }
        tempSB.append(StaticParam.FILE_SPLIT).append(tempTimeData[0]);
        if (FileUtils.sureDir(tempSB.toString()) == null) {
            LogUtils.log("sureDir error.dir=" + tempSB);
            return;
        }
        tempSB.append(StaticParam.FILE_SPLIT).append(tempTimeData[1]);
        if (FileUtils.sureDir(tempSB.toString()) == null) {
            LogUtils.log("sureDir error.dir=" + tempSB);
            return;
        }
        tempSB.append(StaticParam.FILE_SPLIT).append(tempTimeData[2]);
        if (FileUtils.sureDir(tempSB.toString()) == null) {
            LogUtils.log("sureDir error.dir=" + tempSB);
            return;
        }
        currentDirPath = tempSB.toString();
    }

    public static void log(String logStr) {
        logStr = TimeUtils.getTimeZoneDateString(new Date(), 8, TimeEnum.FORMAT_DAY_MSEC)
                + "=====" + logStr;
        System.out.println(logStr);
        String timeData = TimeUtils.getTimeZoneDateString(new Date(), 8, TimeEnum.FORMAT_DAY);
        String[] tempTimeData = timeData.split("-");
        StringBuilder filePath = new StringBuilder(logRootPath);
        filePath.append(StaticParam.FILE_SPLIT).append(tempTimeData[0])
                .append(StaticParam.FILE_SPLIT).append(tempTimeData[1])
                .append(StaticParam.FILE_SPLIT).append(tempTimeData[2]);
        if (!filePath.toString().equals(currentDirPath)) {
            initRootPath();
        }
        AppendFileUtils.getInstance(FileUtils.sureFile(
                filePath.append(StaticParam.FILE_SPLIT).append("text.log").toString())
        ).appendLineString(logStr);
    }

}
