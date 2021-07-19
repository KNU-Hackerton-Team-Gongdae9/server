package com.knu.community.member.repository;


import com.knu.community.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Member findByEmail(String email);

    Member findByNickname(String nickname);
}