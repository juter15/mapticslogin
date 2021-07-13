package kr.co.maptics.mapticslogin.repository;

import kr.co.maptics.mapticslogin.entity.StoreUserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StoreUserAuthRepository extends JpaRepository<StoreUserAuth, Integer>, QuerydslPredicateExecutor<StoreUserAuth> {

	StoreUserAuth findByAuthCode(String authCode);
}
