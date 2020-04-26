package www.mys.com.ourtalk.service;

import org.springframework.data.domain.Page;
import www.mys.com.ourtalk.pojo.Texts;
import www.mys.com.ourtalk.vo.request.RequestText;
import www.mys.com.ourtalk.vo.response.ResponseText;

import java.util.List;

public interface TextsService {

    public List<Texts> uploadTexts(List<Texts> texts);

    public Texts updateTexts(Texts texts);

    public int deleteTexts(List<Texts> texts);

    public int deleteByTextTypeAndGroupMD5(Integer textType, String groupName);

    public Page<Texts> getByType(int type, int page, int count);

    public Page<Texts> getAllByTextId(int textId, int page, int count);

    public Page<Texts> getAllByGroupMd5(String groupMd5, int page, int count);

    public Page<ResponseText> getByTypeGroupByGroupName(int type, int page, int count);

    public Page<ResponseText> getByTextTypeAndGroupName(int type, String groupName, int page, int count);

    public Page<ResponseText> getByTextTypeAndGroupName2(int type, String groupMD5, int page, int count);

    public Page<Texts> getByTextTypeAndGroupMD5(int type, String groupName, int page, int count);

    public Texts getById(Integer id);

    public List<Texts> saveAll(List<Texts> texts);

    public Texts save(Texts texts);

    public void deleteByIdIn(List<Integer> ids);

    public void updateIntDesc(Integer id, Integer intDesc);

    public void addIntDesc(Integer id);

    public ResponseText updateText(Integer id, RequestText requestText);


}
