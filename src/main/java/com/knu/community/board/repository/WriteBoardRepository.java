package com.knu.community.board.repository;

import com.knu.community.board.domain.WriteBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteBoardRepository extends JpaRepository<WriteBoard,Long> {

}
