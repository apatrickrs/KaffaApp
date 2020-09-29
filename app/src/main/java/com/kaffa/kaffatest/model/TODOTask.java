package com.kaffa.kaffatest.model;

import com.google.firebase.database.DatabaseReference;
import com.kaffa.kaffatest.helper.ConfigFirebase;

import java.io.Serializable;

public class TODOTask implements Serializable {

    private String id;
    private String categorie;
    private String name;
    private String date;
    private String todo;

    public TODOTask() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void save() {
        DatabaseReference todoRef = ConfigFirebase.getFirebaseDatabase()
                .child("todo").child(getCategorie()).child(getId());
        todoRef.setValue(this);
    }

    public void remove() {
        DatabaseReference todoRef = ConfigFirebase.getFirebaseDatabase()
                .child("todo").child(getCategorie()).child(getId());
        todoRef.removeValue();
    }
}
