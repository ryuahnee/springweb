package com.demo.todoapps.web;

import com.demo.todoapps.core.application.TodoService;
import com.demo.todoapps.core.domain.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

   private final TodoService todoService;

    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos(){
      return todoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoId(@PathVariable Long id){
        return todoService.fidById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@PathVariable Todo todo){
      return todoService.save(todo);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id , @RequestBody Todo todo){
        return todoService.fidById(id).map(extend -> {
                extend.setId(id);
              return ResponseEntity.ok(todoService.save(todo));
            })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public void  delete(@RequestParam Long id){
        todoService.fidById(id).map(exid ->{
            todoService.deleteById(id);
            return new ResponseEntity<Todo>(HttpStatus.NO_CONTENT);
        })
                .orElse(ResponseEntity.notFound().build());
    }



}


