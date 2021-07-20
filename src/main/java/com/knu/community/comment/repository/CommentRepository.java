package com.knu.community.comment.repository;

import com.knu.community.comment.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    Optional<List<Comment>> findByBoard_Id(Long boardId);
}
