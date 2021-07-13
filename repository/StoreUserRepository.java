package kr.co.maptics.mapticslogin.repository;

import kr.co.maptics.mapticslogin.entity.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StoreUserRepository extends JpaRepository<StoreUser, Integer>, QuerydslPredicateExecutor<StoreUser> {

}
