package com.demo.todoapps.web;

import com.demo.todoapps.core.application.TodoService;
import com.demo.todoapps.core.domain.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String listTodos(Model model) {
        model.addAttribute("todos", todoService.findAll());
        model.addAttribute("newTodo", new Todo());
        return "todos/list";
    }
    @PostMapping
    public String createTodo(@ModelAttribute Todo todo, RedirectAttributes redirectAttributes) {
        todoService.save(todo);
        redirectAttributes.addFlashAttribute("message", "할 일이 성공적으로 추가되었습니다.");
        return "redirect:/todos";
    }

}
