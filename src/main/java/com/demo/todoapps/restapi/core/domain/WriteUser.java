package com.demo.todoapps.restapi.core.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// TODO: 3/31/25  사용자에게 요청할 도메인 구현

@Setter
@Getter
public class WriteUser {

    private Long id;
    private String name;
    private String email;

    public WriteUser() {
    }

}
