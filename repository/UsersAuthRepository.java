package kr.co.maptics.mapticslogin.repository;


import kr.co.maptics.mapticslogin.entity.UsersAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UsersAuthRepository extends JpaRepository<UsersAuth, Integer>, QuerydslPredicateExecutor<UsersAuth> {

	UsersAuth findByAuthCode(String authCode);
}
