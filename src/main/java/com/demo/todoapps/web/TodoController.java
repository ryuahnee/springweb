package com.demo.todoapps.web;

import com.demo.todoapps.core.application.TodoService;
import com.demo.todoapps.core.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public String listTodos(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "completed", required = false) Boolean completed,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "sort",defaultValue = "createdAt,desc") String sort,
            Model model) {

        String[] sortParams = sort.split(",");
        Sort sortObj = Sort.by(
                sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC,
                sortParams[0]
        );


        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Todo> todoPage;

        if (search != null && !search.isEmpty()) {
            todoPage = todoService.searchByText(search, pageable);
            model.addAttribute("searchTerm", search);
        } else if (completed != null) {
            todoPage = todoService.findByCompleted(completed, pageable);
            model.addAttribute("completedFilter", completed);
        } else {
            todoPage = todoService.findAll(pageable);
        }

        model.addAttribute("todos",todoPage.getContent());
        model.addAttribute("page", todoPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", todoPage.getTotalPages());
        model.addAttribute("totalItems", todoPage.getTotalElements());
        model.addAttribute("sort", sort);

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
