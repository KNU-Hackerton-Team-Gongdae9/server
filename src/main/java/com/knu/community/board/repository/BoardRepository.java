package com.knu.community.board.repository;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.board.dto.BoardDetailDto;
import com.knu.community.comment.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select b from Board b where b.title LIKE %:title%")
    Optional<List<Board>> findByTitle(@Param(value="title") String title);

    Optional<List<Board>> findByCategory(Category category);

    Optional<List<Board>> findByAuthor(String author);

    @Query("select new com.knu.community.board.dto.BoardDetailDto(b) from Board b where b.member.id = :memId ")
    Optional<List<BoardDetailDto>> findMyBoards(@Param(value="memId") Long memId);

    @Query("select b from Board b where b.content LIKE %:content%")
    Optional<List<Board>> findByContent(@Param(value="content") String content);


    @Query("select new com.knu.community.board.dto.BoardDetailDto(b) from Board b where b.id = :id ")
    Optional<List<BoardDetailDto>> findBoardWithAll(@Param(value="id") Long id);
}
