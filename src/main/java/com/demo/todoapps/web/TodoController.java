package com.demo.todoapps.web;

import com.demo.todoapps.core.application.TodoService;
import com.demo.todoapps.core.domain.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String listTodos(@RequestParam(name = "search",required = false)String search,
                            @RequestParam(name="completed",required = false)Boolean completed,
                            Model model){

        List<Todo> todos;
        if(search != null || !search.isEmpty()){
            todos =  todoService.searchByText(search);
            model.addAttribute("searchTerm", search);
        }else if(completed != null ){
            todos =    todoService.findByCompleted(completed);
            model.addAttribute("completedFilter", completed);
        }else {
            todos = todoService.findAll();
        }

        model.addAttribute("todos",todos);

        if (!model.containsAttribute("newTodo")) {
            model.addAttribute("newTodo", new Todo());
        }

        return "todos/list";
    }

    @PostMapping
    public String createTodo(@ModelAttribute Todo todo, RedirectAttributes redirectAttributes) {
        todoService.save(todo);
        redirectAttributes.addFlashAttribute("message", "할 일이 성공적으로 추가되었습니다.");
        return "redirect:/todos";
    }

}
