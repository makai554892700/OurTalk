package www.mys.com.ourtalk.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import www.mys.com.ourtalk.common.base.BaseResultEnum;
import www.mys.com.ourtalk.common.exception.BaseException;
import www.mys.com.ourtalk.mapper.TextsMapper;
import www.mys.com.ourtalk.pojo.Texts;
import www.mys.com.ourtalk.service.TextsService;
import www.mys.com.ourtalk.utils.net.MD5Utils;
import www.mys.com.ourtalk.vo.request.RequestText;
import www.mys.com.ourtalk.vo.response.ResponseText;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("textsServiceImpl")
public class TextsServiceImpl implements TextsService {

    @Resource(name = "textsMapper")
    private TextsMapper textsMapper;

    @Override
    public Texts getById(Integer id) {
        return textsMapper.getById(id);
    }

    @Override
    public List<Texts> uploadTexts(List<Texts> texts) {
        return textsMapper.saveAll(texts);
    }

    @Override
    public Texts updateTexts(Texts texts) {
        Texts tempTexts = textsMapper.getById(texts.getId());
        if (tempTexts == null) {
            throw new BaseException(BaseResultEnum.NO_SUCH_DATA);
        }
        Page<Texts> texts1 = null;
        return textsMapper.save(texts);
    }

    @Override
    public int deleteTexts(List<Texts> texts) {
        List<Integer> ids = new ArrayList<>();
        for (Texts datas : texts) {
            ids.add(datas.getId());
        }
        return textsMapper.deleteByIdIn(ids);
    }

    @Override
    public Page<Texts> getByType(int type, int page, int count) {
        return textsMapper.getByTextType(type, PageRequest.of(page, count, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<ResponseText> getByTypeGroupByGroupName(int type, int page, int count) {
        return textsMapper.getByTextTypeGroupByGroupMD5(type
                , PageRequest.of(page, count, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<ResponseText> getByTextTypeAndGroupName(int type, String groupName, int page, int count) {
        return textsMapper.getByTextTypeAndGroupMD5s(type, MD5Utils.MD5(type + groupName, false)
                , PageRequest.of(page, count, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<ResponseText> getByTextTypeAndGroupName2(int type, String groupMD5, int page, int count) {
        return textsMapper.getByTextTypeAndGroupMD5s(type, groupMD5
                , PageRequest.of(page, count, Sort.Direction.DESC, "intDesc"));
    }

    @Override
    public Page<Texts> getByTextTypeAndGroupMD5(int type, String groupMD5, int page, int count) {
        return textsMapper.getByTextTypeAndGroupMD5(type, groupMD5
                , PageRequest.of(page, count, Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Texts> saveAll(List<Texts> texts) {
        return textsMapper.saveAll(texts);
    }

    @Override
    public Texts save(Texts texts) {
        return textsMapper.save(texts);
    }

    @Override
    public int deleteByTextTypeAndGroupMD5(Integer textType, String groupMD5) {
        return textsMapper.deleteByTextTypeAndGroupMD5(textType, groupMD5);
    }

    @Override
    public void deleteByIdIn(List<Integer> ids) {
        textsMapper.deleteByIdIn(ids);
    }

    @Override
    public Page<Texts> getAllByTextId(int textId, int page, int count) {
        return textsMapper.getAllByTextId(textId, PageRequest.of(page, count
                , Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Texts> getAllByGroupMd5(String groupMd5, int page, int count) {
        return textsMapper.getByGroupMD5(groupMd5, PageRequest.of(page, count
                , Sort.Direction.DESC, "id"));
    }

    @Override
    public void updateIntDesc(Integer id, Integer intDesc) {
        textsMapper.updateIntDesc(id, intDesc);
    }

    @Override
    public void addIntDesc(Integer id) {
        textsMapper.addIntDesc(id);
    }

    @Override
    public ResponseText updateText(Integer id, RequestText requestText) {
        Texts texts = textsMapper.getById(id);
        if (texts != null) {
            if (requestText.getDateDesc() != null) {
                texts.setDateDesc(requestText.getDateDesc());
            }
            if (requestText.getGroupName() != null) {
                texts.setGroupName(requestText.getGroupName());
                texts.setGroupMD5(MD5Utils.MD5(requestText.getTextType()
                        + requestText.getGroupName(), false));
            }
            if (requestText.getIntDesc() != null) {
                texts.setIntDesc(requestText.getIntDesc());
            }
            if (requestText.getTextDesc() != null) {
                texts.setTextDesc(requestText.getTextDesc());
            }
            if (requestText.getTextType() != null) {
                texts.setTextType(requestText.getTextType());
            }
            if (requestText.getTextValue() != null) {
                texts.setTextValue(requestText.getTextValue());
            }
            if (requestText.getTypeName() != null) {
                texts.setTypeName(requestText.getTypeName());
            }
            textsMapper.save(texts);
            return new ResponseText(texts.getId(), texts.getTextType(), texts.getTextValue()
                    , texts.getGroupName(), texts.getGroupMD5(), texts.getTextDesc(), texts.getTypeName()
                    , texts.getIntDesc(), texts.getDateDesc(), texts.getCreatedAt(), texts.getUpdatedAt());
        } else {
            return null;
        }
    }
}
