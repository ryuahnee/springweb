package com.demo.todoapps.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "할 일 내용은 필수입니다")
    @Size(min = 3, max = 100, message = "할 일 내용은 3~100자 사이여야 합니다")
    private String text;
    private boolean completed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    //기본 생성자
    public Todo() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }


    public Todo(String text) {
        this();
        this.text = text;
        this.completed = false;
    }

    public Todo(Long id, String text, boolean completed) {
        this(text);
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
