package www.mys.com.ourtalk.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import www.mys.com.ourtalk.pojo.AddFriend;

@Repository("addFriendMapper")
public interface AddFriendMapper extends JpaRepository<AddFriend, Integer> {

    public AddFriend getById(Integer id);

    public Page<AddFriend> getByFromUserAndLastAddIdIsNull(String fromUser, Pageable pageable);

    public Page<AddFriend> getByToUserAndLastAddIdIsNull(String toUser, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update AddFriend set send = :send where id = :id")
    public void updateSend(@Param("id") Integer id, @Param("send") Boolean send);

    @Transactional
    @Modifying
    @Query(value = "update AddFriend set backSend = :backSend where id = :id")
    public void updateBackSend(@Param("id") Integer id, @Param("backSend") Boolean backSend);

    @Transactional
    @Modifying
    @Query(value = "update AddFriend set addMsg = :addMsg where id = :id")
    public void updateBackMsg(@Param("id") Integer id, @Param("addMsg") String addMsg);

}
