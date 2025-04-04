package com.demo.todoapps.core.application;

import com.demo.todoapps.core.domain.Todo;
import com.demo.todoapps.core.domain.TodoNotFoundException;
import com.demo.todoapps.data.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final ConcurrentHashMap<Long, Todo> todos = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


   public List<Todo> findAll(){
       return todoRepository.findAll();
   }

    public Todo findById(Long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public List<Todo> findByCompleted(boolean completed){
        return todoRepository.findByCompleted(completed);
    }

    public List<Todo> searchByText(String text){
       return todoRepository.findByTextContaining(text);
    }

    public Todo save(Todo todo) {
         return todoRepository.save(todo);
    }

    public void deleteById(Long id){
        if(todoRepository.existsById(id)){
           todoRepository.deleteById(id);
        }
        throw new TodoNotFoundException(id);
    }

    // 페이지네이션 추가
    public Page<Todo> findAll(Pageable pageable){
        return todoRepository.findAll(pageable);
    }

    public Page<Todo> findByCompleted(boolean completed, Pageable pageable){
        return todoRepository.findByCompleted(completed, pageable);
    }

    public Page<Todo> searchByText(String text, Pageable pageable){
        return todoRepository.findByTextContaining(text, pageable);
    }



}
