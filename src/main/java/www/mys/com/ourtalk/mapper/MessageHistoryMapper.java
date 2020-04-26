package www.mys.com.ourtalk.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import www.mys.com.ourtalk.pojo.chat.MessageHistory;

@Repository("messageHistoryMapper")
public interface MessageHistoryMapper extends JpaRepository<MessageHistory, Integer> {

    public MessageHistory getById(Integer id);

    public Page<MessageHistory> getByUserKey(String userKey, Pageable pageable);

}
