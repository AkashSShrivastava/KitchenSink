package com.hitachi.KitchenSink.repository;

import com.hitachi.KitchenSink.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByEmail(String email);

//    @Query("SELECT m FROM Member m ORDER BY m.name")
//    List<Member> findAllOrderedByName();
}
