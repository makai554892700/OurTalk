package www.mys.com.ourtalk.service;

import org.springframework.web.multipart.MultipartFile;
import www.mys.com.ourtalk.vo.response.ResponseText;

import java.util.List;

public interface InfoService {

    public List<ResponseText> updateGroupName(String groupName, Integer oldGroupId, Integer groupCount);

    public void delGroup(Integer groupId);

    public void delGroups(List<Integer> ids);

    public List<ResponseText> commonUploadImgFile(MultipartFile[] files, int textType, final String groupName
            , final String textDesc);

    public List<ResponseText> commonUploadFile(MultipartFile[] files, final int textType, final String groupName
            , final String textDesc);

}
