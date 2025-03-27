package com.demo.todoapps.core.application;

import com.demo.todoapps.core.domain.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {

    private final ConcurrentHashMap<Long, Todo> todos = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    // 초기 데이터 추가
    public TodoService() {
        save(new Todo(idGenerator.incrementAndGet(), "스프링 MVC 학습하기", false));
        save(new Todo(idGenerator.incrementAndGet(), "JPA 학습하기", false));
        save(new Todo(idGenerator.incrementAndGet(), "REST API 설계하기", true));
    }


   public List<Todo> findAll(){
        return new ArrayList<>(todos.values());
   }

    public Optional<Todo> fidById(Long id){
        return Optional.ofNullable(todos.get(id));
    }


    public Todo save(Todo todo) {
        if(todo == null){
           todo =  new Todo(idGenerator.incrementAndGet(), todo.getText(), todo.isCompleted());
        }
        todos.put(todo.getId(),todo);
        return todo;

    }

    public boolean  deleteById(Long id){
        return todos.remove(id) != null ;
    }

}
