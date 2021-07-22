package com.knu.community.comment.repository;

import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.dto.CommentDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    Optional<List<Comment>> findByBoard_Id(Long boardId);

    @Query("select m.commentList from Member m where m.id = :memId")
    Optional<List<Comment>> findMyComments(@Param(value="memId") Long memId);

    @Query("select b.commentList from Board b where b.id = :boardId")
    Optional<List<Comment>> findContentsByBoardId(@Param(value = "boardId") Long boardId);
}
