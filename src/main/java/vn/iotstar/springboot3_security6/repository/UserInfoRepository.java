package vn.iotstar.springboot3_security6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.springboot3_security6.entity.UserInfo;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
}