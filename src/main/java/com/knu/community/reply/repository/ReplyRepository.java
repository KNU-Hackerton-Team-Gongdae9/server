package com.knu.community.reply.repository;

import com.knu.community.reply.domain.Reply;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Query("select r from Reply r where r.author = :nickname")
    Optional<List<Reply>> findMyReplies(@Param(value = "nickname") String nickname);
}
