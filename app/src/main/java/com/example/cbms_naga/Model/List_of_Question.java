package com.example.cbms_naga.Model;

public class List_of_Question {

    private String questions_id,questions_label,questions_type;
    private int id;

    public List_of_Question() {
    }

    public List_of_Question(String questions_id, String questions_label, String questions_type, int id) {
        this.questions_id = questions_id;
        this.questions_label = questions_label;
        this.questions_type = questions_type;
        this.id = id;
    }

    public String getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(String questions_id) {
        this.questions_id = questions_id;
    }

    public String getQuestions_label() {
        return questions_label;
    }

    public void setQuestions_label(String questions_label) {
        this.questions_label = questions_label;
    }

    public String getQuestions_type() {
        return questions_type;
    }

    public void setQuestions_type(String questions_type) {
        this.questions_type = questions_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
