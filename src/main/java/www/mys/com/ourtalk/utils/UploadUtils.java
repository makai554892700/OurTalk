package www.mys.com.ourtalk.utils;

import org.springframework.web.multipart.MultipartFile;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.pojo.Texts;
import www.mys.com.ourtalk.utils.file.FileUtils;
import www.mys.com.ourtalk.utils.net.MD5Utils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class UploadUtils {

    public static ArrayList<Texts> uploadFiles(MultipartFile[] files, final int textType, final String groupName
            , final String textDesc) {
        InputStream is;
        final ArrayList<Texts> result = new ArrayList<>();
        final String typeName = Texts.getTypeName(textType);
        for (MultipartFile file : files) {
            try {
                is = file.getInputStream();
            } catch (Exception e) {
                LogUtils.log("commonUploadFile failed.textType=" + textType + ";error=" + e);
                continue;
            }
            FileUtils.readLine(is, new FileUtils.LineBack() {
                private StringBuilder tempSB = new StringBuilder();
                private int enterCount;

                @Override
                public void onStart(String fileName) {
                }

                @Override
                public void onLine(String line) {
                    if (textType == Texts.Type.SAY_HELLO || textType == Texts.Type.AUTO_RETURN) {
                        if (StringUtils.isEmpty(line)) {
                            enterCount++;
                            if (enterCount > 1) {
                                if (tempSB.length() > 0 && !StringUtils.isEmpty(tempSB.toString().trim())) {
                                    tempSB.delete(tempSB.length() - 1, tempSB.length());
                                    result.add(new Texts(textType, tempSB.toString()
                                            , groupName, MD5Utils.MD5(textType + groupName, false)
                                            , textDesc, typeName));
                                    tempSB.delete(0, tempSB.length());
                                }
                                enterCount = 0;
                            }
                        } else {
                            if (enterCount == 1) {
                                tempSB.append("\n");
                            }
                            enterCount = 0;
                            tempSB.append(line).append("\n");
                        }
                    } else {
                        if (line.length() > 0) {
                            result.add(new Texts(textType, line, groupName, MD5Utils.MD5(textType + groupName, false)
                                    , textDesc, typeName));
                        }
                    }
                }

                @Override
                public void onEnd(String fileName) {
                }
            });
        }
        return result;
    }

    public static ArrayList<Texts> uploadImgFiles(MultipartFile[] files, final int textType, final String groupName
            , final String textDesc) {
        ArrayList<Texts> result = new ArrayList<>();
        String imgPath;
        String tempTypeName = Texts.getTypeName(textType);
        for (MultipartFile file : files) {
            imgPath = uploadImg(file);
            if (imgPath == null) {
                continue;
            }
            result.add(new Texts(textType, imgPath, groupName, MD5Utils.MD5(textType + groupName, false)
                    , textDesc, tempTypeName));
        }
        return result;
    }


    private static String uploadImg(MultipartFile file) {
        StringBuilder result = new StringBuilder(StaticParam.IMG_PATH);
        String tempName = MD5Utils.MD5(System.currentTimeMillis() + file.getOriginalFilename(), false);
        result.append(File.separator).append(tempName.substring(0, 2));
        FileUtils.sureDir(StaticParam.ROOT_PATH + File.separator + result);
        result.append(File.separator).append(tempName.substring(tempName.length() - 2));
        FileUtils.sureDir(StaticParam.ROOT_PATH + File.separator + result);
        result.append(File.separator).append(tempName).append(StaticParam.PNG_END);
        try {
            file.transferTo(new File(StaticParam.ROOT_PATH + File.separator + result));
        } catch (Exception e) {
            return null;
        }
        return result.toString();
    }
}
