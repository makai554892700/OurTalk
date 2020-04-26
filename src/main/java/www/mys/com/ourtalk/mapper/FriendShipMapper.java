package www.mys.com.ourtalk.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import www.mys.com.ourtalk.pojo.FriendShip;

@Repository("friendShipMapper")
public interface FriendShipMapper extends JpaRepository<FriendShip, Integer> {

    public long countByFromUser(String fromUser);

    public Page<FriendShip> getByFromUser(String fromUser, Pageable pageable);

    public Page<FriendShip> getByToUser(String toUser, Pageable pageable);

    public FriendShip getByFromUserAndToUser(String fromUser, String toUser);

}
