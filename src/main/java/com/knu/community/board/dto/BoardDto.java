package com.knu.community.board.dto;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BoardDto {

    private Category category;

    private String title;

    private String content;

    private String author;

    private LocalDateTime dateTime;

    public BoardDto(){}

    public BoardDto(Board board){
        this.category=board.getCategory();
        this.title=board.getTitle();
        this.content=board.getContent();
        this.author=board.getAuthor();
        this.dateTime=board.getCreatedDate();
    }
}
