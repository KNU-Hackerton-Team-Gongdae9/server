package com.knu.community.board.repository;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

    Optional<List<Board>> findByTitle(String title);

    Optional<List<Board>> findByCategory(Category category);

    Optional<List<Board>> findByAuthor(String author);

    @EntityGraph(attributePaths = {"commentList", "commentList.replyList"})
    Board findBoardWithAllById(Long id);
}
