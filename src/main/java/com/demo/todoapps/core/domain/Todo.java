package com.demo.todoapps.core.domain;

public class Todo {

        private Long id;
        private String text;
        private boolean completed;


        //기본 생성자
    public Todo() {

    }


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

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
