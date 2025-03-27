package com.demo.todoapps.core.domain;

public class TodoNotFoundException extends RuntimeException{

    public TodoNotFoundException(Long id){
        super("요청정보가 없습니다. id = "+id);

    }
}
