package com.demo.todoapps.web;

import com.demo.todoapps.core.application.TodoService;
import com.demo.todoapps.core.domain.Todo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        return ResponseEntity.ok(todoService.findById(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@Valid @PathVariable Todo todo){
      return todoService.save(todo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id , @Valid @RequestBody Todo todo){
        return ResponseEntity.ok(todoService.save(todo));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@RequestParam Long id){

        todoService.findById(id);
        todoService.deleteById(id);

    }

    // 유효성 검증 실패 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }



}


