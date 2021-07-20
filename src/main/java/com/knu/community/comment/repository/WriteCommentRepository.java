package com.knu.community.comment.repository;

import com.knu.community.comment.domain.WriteComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteCommentRepository extends JpaRepository<WriteComment,Long> {

}
