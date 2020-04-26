package www.mys.com.ourtalk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtils {

    public static void main(String[] args) {
    }

    public static int getTimeOut(String data) {
        String regularResult = getRegular("\\w*(=|= )\\d+ms\\w*", data);
        int result = -1;
        if (regularResult != null) {
            try {
                result = Integer.parseInt(regularResult.replace("=", "")
                        .replace("ms", "").trim());
            } catch (Exception e) {
                System.out.println("getTimeOut error.e = " + e);
            }
        }
        return result;
    }

    public static String getHost(String host, boolean needStart) {
        String result = getRegular("(http|https)://(\\w+(\\.)?)+", host);
        if (result != null && !needStart) {
            result = result.replace("http://", "").replace("https://", "");
        }
        return result;
    }

    public static String getRegular(String regular, String data) {
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            String result = matcher.group().trim();
//            System.out.println("getRegular success.result=" + result + "\ndata=" + data);
            return result;
        } else {
            System.out.println("getRegular error.data=" + data + "\nregular=" + regular);
            return null;
        }
    }

}
