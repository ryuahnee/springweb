package com.demo.todoapps.data;

import com.demo.todoapps.core.domain.Todo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDate(TodoRepository todoRepository){
        return args -> {
            if(todoRepository.count() == 0){
                todoRepository.save(new Todo("스프링 mvc 학습"));
                todoRepository.save(new Todo("mvc 학습하기"));

                Todo completedTodo = new Todo("REST API 설계하기");
                completedTodo.setCompleted(true);
                todoRepository.save(completedTodo);

                todoRepository.save(new Todo("Spring Data JPA 실습하기"));
            }
        };
    }

}
