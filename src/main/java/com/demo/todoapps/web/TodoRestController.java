package com.demo.todoapps.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

    // 간단한 Todo 클래스
    public static class Todo {
        private Long id;
        private String text;
        private boolean completed;

        // 생성자, getter, setter
        public Todo(Long id, String text, boolean completed) {
            this.id = id;
            this.text = text;
            this.completed = completed;
        }

        public Long getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public boolean isCompleted() {
            return completed;
        }
    }

    @GetMapping
    public List<Todo> getAllTodos(){
        return Arrays.asList(
                new Todo(1L,"스프링 MVC 학습하기", false),
                new Todo(2L,"스프링 MVC 학습하기", false),
                new Todo(3L,"스프링 MVC 학습하기", true)
        );
    }

    @GetMapping("/{id}")
    public Todo getTodoId(@PathVariable Long id){
        return new Todo(2L,"스프링 MVC 학습하기", false);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody Todo todo){
        return new Todo(4L,todo.getText(), todo.isCompleted());
    }


    @PostMapping("/{id}")
    public Todo update(@RequestParam Long id , @RequestBody Todo todo){
        return new Todo(id, todo.getText(), todo.isCompleted());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@RequestParam Long id){
       System.out.println("삭제처리:  %d"+id);
    }



}


