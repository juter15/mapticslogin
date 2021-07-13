package kr.co.maptics.mapticslogin.repository;

import kr.co.maptics.mapticslogin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UsersRepository extends JpaRepository<Users, Integer>, QuerydslPredicateExecutor<Users> {

}
