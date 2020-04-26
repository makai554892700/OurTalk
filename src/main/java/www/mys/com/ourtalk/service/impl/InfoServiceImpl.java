package www.mys.com.ourtalk.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import www.mys.com.ourtalk.common.base.BaseResultEnum;
import www.mys.com.ourtalk.common.exception.BaseException;
import www.mys.com.ourtalk.pojo.Texts;
import www.mys.com.ourtalk.service.InfoService;
import www.mys.com.ourtalk.service.TextsService;
import www.mys.com.ourtalk.utils.DataUtils;
import www.mys.com.ourtalk.utils.LogUtils;
import www.mys.com.ourtalk.utils.UploadUtils;
import www.mys.com.ourtalk.utils.net.MD5Utils;
import www.mys.com.ourtalk.vo.response.ResponseText;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("infoServiceImpl")
public class InfoServiceImpl implements InfoService {

    @Resource(name = "textsServiceImpl")
    private TextsService textsService;

    @Override
    public List<ResponseText> updateGroupName(String groupName, Integer oldGroupId, Integer groupCount) {
        Texts tempTexts = textsService.getById(oldGroupId);
        if (tempTexts == null) {
            throw new BaseException(BaseResultEnum.NO_SUCH_DATA);
        }
        int page = 0;
        Page<Texts> texts;
        List<Texts> allText = new ArrayList<>();
        String groupMD5 = tempTexts.getGroupMD5();
        int textType = tempTexts.getTextType();
        if (groupCount == null || groupCount < 1) {
            do {
                texts = textsService.getByTextTypeAndGroupMD5(
                        textType, groupMD5, page++, 1000
                );
                for (Texts data : texts) {
                    data.setGroupName(groupName);
                    data.setGroupMD5(MD5Utils.MD5(data.getTextType() + groupName, false));
                    allText.add(data);
                }
            } while (texts.hasNext());
        } else {
            texts = textsService.getByTextTypeAndGroupMD5(
                    textType, groupMD5, 0, groupCount
            );
            for (Texts data : texts) {
                data.setGroupName(groupName);
                data.setGroupMD5(MD5Utils.MD5(data.getTextType() + groupName, false));
                allText.add(data);
            }
        }
        LogUtils.log("updateGroupName dataLen=" + allText.size());
        return DataUtils.getResponseTexts(textsService.saveAll(allText));
    }

    @Override
    public void delGroup(Integer groupId) {
        Texts tempTexts = textsService.getById(groupId);
        if (tempTexts == null) {
            throw new BaseException(BaseResultEnum.NO_SUCH_DATA);
        }
        textsService.deleteByTextTypeAndGroupMD5(tempTexts.getTextType(), tempTexts.getGroupMD5());
    }

    @Override
    public void delGroups(List<Integer> ids) {
        textsService.deleteByIdIn(ids);
    }

    @Override
    public List<ResponseText> commonUploadImgFile(MultipartFile[] files, int textType, String groupName, String textDesc) {
        List<Texts> texts = UploadUtils.uploadImgFiles(files, textType, groupName, textDesc);
        textsService.saveAll(texts);
        return DataUtils.getResponseTexts(texts);
    }

    @Override
    public List<ResponseText> commonUploadFile(MultipartFile[] files, int textType, String groupName, String textDesc) {
        List<Texts> texts = UploadUtils.uploadFiles(files, textType, groupName, textDesc);
        textsService.saveAll(texts);
        return DataUtils.getResponseTexts(texts);
    }

}
