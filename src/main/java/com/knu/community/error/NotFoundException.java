package com.knu.community.error;

import org.hibernate.annotations.NotFound;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException() { super("찾을 수 없습니다."); }
}
