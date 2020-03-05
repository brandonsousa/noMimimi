package br.com.seasyc.nomimimi.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.Date;

import br.com.seasyc.nomimimi.config.FirebaseSettings;

public class User {

    private String id;
    private String name;
    private String email;
    private Date created_at;
    private String password;

    public User() {
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseSettings.getDatabaseReference();
        databaseReference.child("users").child(getId()).setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
