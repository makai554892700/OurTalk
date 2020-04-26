package www.mys.com.ourtalk.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import www.mys.com.ourtalk.pojo.Texts;
import www.mys.com.ourtalk.vo.response.ResponseText;

import java.util.List;
import java.util.Set;

@Repository("textsMapper")
public interface TextsMapper extends JpaRepository<Texts, Integer> {

    public Texts getById(Integer id);

    public Page<Texts> getByTextType(Integer textType, Pageable pageable);

    @Query(value = "select new www.mys.com.ourtalk.vo.response.ResponseText(t.id,t.textType,t.textValue,t.groupName" +
            ",t.groupMD5,t.textDesc,t.typeName,count(t.id),t.intDesc,t.dateDesc,t.createdAt,t.updatedAt) from Texts as t" +
            " where t.textType = :textType group by t.groupMD5")
    public Page<ResponseText> getByTextTypeGroupByGroupMD5(@Param("textType") Integer textType, Pageable pageable);

    @Transactional
    public int deleteByIdIn(List<Integer> ids);

    @Query(value = "select new www.mys.com.ourtalk.vo.response.ResponseText(t.id,t.textType,t.textValue,t.groupName" +
            ",t.groupMD5,t.textDesc,t.typeName,t.intDesc,t.dateDesc,t.createdAt,t.updatedAt) from Texts as t" +
            " where t.textType = :textType and t.groupMD5 = :groupMD5")
    public Page<ResponseText> getByTextTypeAndGroupMD5s(@Param("textType") int textType
            , @Param("groupMD5") String groupMD5, Pageable pageable);

    public Page<Texts> getByTextTypeAndGroupMD5(int textType, String groupMD5, Pageable pageable);

    @Transactional
    public int deleteByTextTypeAndGroupMD5(Integer textType, String groupMD5);

    @Query(value = "select new www.mys.com.ourtalk.pojo.Texts(t.id,t.textType,t.textValue,t.groupName,t.groupMD5" +
            ",t.textDesc,t.typeName,t.intDesc,t.dateDesc,t.createdAt,t.updatedAt)" +
            " from Texts as t where t.textType = (select textType from Texts where id = :textId)" +
            " and t.groupMD5 = (select groupMD5 from Texts where id = :textId)")
    public Page<Texts> getAllByTextId(@Param("textId") int textId, Pageable pageable);

    public Page<Texts> getByGroupMD5(String groupMd5, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Texts set intDesc = intDesc + 1 where id = :id")
    public void addIntDesc(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update Texts set intDesc = :intDesc where id = :id")
    public void updateIntDesc(@Param("id") Integer id
            , @Param("intDesc") Integer intDesc);

    public List<Texts> getByIdIn(Set<Integer> ids);

}
