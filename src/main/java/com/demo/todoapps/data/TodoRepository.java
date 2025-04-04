package com.demo.todoapps.data;

import com.demo.todoapps.core.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    // 완료된/미완료된 할 일 목록 찾기
    List<Todo> findByCompleted(boolean completed);

    // 텍스트로 할 일 검색
    List<Todo> findByTextContaining(String text);

    Page<Todo> findAll(Pageable pageable);
    Page<Todo> findByCompleted(boolean completed, Pageable pageable);
    Page<Todo> findByTextContaining(String text, Pageable pageable);


}
