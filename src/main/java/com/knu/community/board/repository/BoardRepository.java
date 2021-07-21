package com.knu.community.board.repository;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.comment.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select b from Board b where b.title LIKE %:title%")
    Optional<List<Board>> findByTitle(@Param(value="title") String title);

    Optional<List<Board>> findByCategory(Category category);

    Optional<List<Board>> findByAuthor(String author);

    @Query("select m.boardList from Member m where m.id = :memId")
    Optional<List<Board>> findMyBoards(@Param(value="memId") Long memId);

    @Query("select b from Board b where b.content LIKE %:content%")
    Optional<List<Board>> findByContent(@Param(value="content") String content);
}
