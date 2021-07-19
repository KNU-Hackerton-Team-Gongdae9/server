package com.knu.community.board.dto;

import com.knu.community.board.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardForm {

    private Category category;

    private String Title;

    private String content;

    private String author;


}
