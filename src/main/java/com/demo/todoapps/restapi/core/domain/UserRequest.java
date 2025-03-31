package com.demo.todoapps.restapi.core.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// TODO: 3/31/25  사용자에게 요청할 도메인 구현

@Setter
@Getter
public class UserRequest {

    private Long id;
    @NotBlank(message = "이름은 필수 작성값 입니다.")
    @Size(min = 2, max = 10, message = "이름은 2~10자 사이여야 합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 작성값 입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    public UserRequest() {
    }



}
