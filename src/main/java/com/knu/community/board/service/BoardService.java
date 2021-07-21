package com.knu.community.board.service;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.board.domain.WriteBoard;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.board.repository.BoardRepository;
import com.knu.community.board.repository.WriteBoardRepository;
import com.knu.community.error.NotFoundException;
import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final WriteBoardRepository writeBoardRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Board writeBoard(Long userId, BoardForm boardForm){
        Board board = createBoard(boardForm);
        Member member = memberRepository.findById(userId).orElseThrow(
            ()-> new NotFoundException("Member를 찾을 수 없습니다"));

        WriteBoard writeBoard = new WriteBoard(member,board);
        writeBoardRepository.save(writeBoard);

        return board;
    }

    @Transactional(readOnly = true)
    public Board findById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(
            ()-> new NotFoundException(("Board를 찾을 수 없습니다")));
    }

    @Transactional(readOnly = true)
    public List<Board> findByTitle(String title){
        return boardRepository.findByTitle(title).orElseThrow(
            ()->new NotFoundException(("Title에 해당하는 Board를 찾을 수 없습니다")));
    }

    @Transactional(readOnly = true)
    public List<Board> findByAuthor(String author){
        return boardRepository.findByAuthor(author).orElseThrow(
            ()->new NotFoundException(("Author에 해당하는 Board를 찾을 수 없습니다")));
    }

    @Transactional(readOnly = true)
    public List<Board> findByCategory(Category category){
        return boardRepository.findByCategory(category).orElseThrow(
            ()->new NotFoundException(("Category에 해당하는 Board를 찾을 수 없습니다")));
    }

    @Transactional
    public void deleteBoard(Long boardId){
       boardRepository.deleteById(boardId);
    }

    @Transactional
    public void updateBoard(Long userId,Long boardId,BoardForm boardForm){
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundException::new);
        board.edit(boardForm);
    }

    @Transactional
    public Board createBoard(BoardForm boardForm){
        Board board = Board.createBoard(boardForm);
        boardRepository.save(board);

        return board;
    }
}
