package br.com.seasyc.nomimimi.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.seasyc.nomimimi.config.FirebaseSettings;

public class Saves {

    private String id;
    private String post_id;
    private String owner_id;
    private Date created_at;

    public Saves() {
    }

    public void newSave(){
        DatabaseReference databaseReference = FirebaseSettings.getDatabaseReference();
        String key = databaseReference.child("saves").child(getOwner_id()).push().getKey();
        setId(key);
        databaseReference.child("saves").child(getOwner_id()).setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
