package br.com.seasyc.nomimimi.model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.seasyc.nomimimi.config.FirebaseSettings;
import br.com.seasyc.nomimimi.util.Factory;

public class Posts {

    private String p_id;
    private String owner;
    private String hashtag;
    private String content;
    private Date created_at;
    private int likes;

    public Posts() {

    }

    public void newPost(){
        DatabaseReference databaseReference = FirebaseSettings.getDatabaseReference();
        String key = databaseReference.child("posts").push().getKey();
        setP_id(key);
        databaseReference.child("posts").child(getP_id()).setValue(this);
    }

    public static void like(String p_id, final Context context){
        DatabaseReference databaseReference = FirebaseSettings.getDatabaseReference().child("posts").child(p_id);
        databaseReference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {

                Posts post = mutableData.getValue(Posts.class);

                if (post == null) {
                    return Transaction.success(mutableData);
                }

                post.p_id = post.getP_id();
                post.owner = post.getOwner();
                post.content = post.getContent();
                post.likes = post.getLikes() + 1;
                post.created_at = post.getCreated_at();
                post.hashtag = post.getHashtag();

                mutableData.setValue(post);

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                Factory.toastMessage(context, "liked");
            }
        });
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
