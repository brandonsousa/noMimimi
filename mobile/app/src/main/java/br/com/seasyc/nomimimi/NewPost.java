package br.com.seasyc.nomimimi;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.Date;

import br.com.seasyc.nomimimi.config.FirebaseSettings;
import br.com.seasyc.nomimimi.model.Posts;
import br.com.seasyc.nomimimi.util.Factory;

public class NewPost extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private TextInputLayout hashtag, content;
    private Button create;

    private Posts post;

    private Date now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseSettings.getFirebaseAuth();
        firebaseUser = firebaseAuth.getCurrentUser();

        hashtag = findViewById(R.id.hashtags);
        content = findViewById(R.id.post_content);
        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPost();
                if (verifyFilds()){
                    post.newPost();
                    Factory.toastMessage(NewPost.this, "Postagem realizada");
                    hashtag.getEditText().setText("");
                    content.getEditText().setText("");
                }
            }
        });

    }

    private void setPost(){
        post = new Posts();
        now = new Date();
        post.setHashtag(hashtag.getEditText().getText().toString());
        post.setContent(content.getEditText().getText().toString());
        post.setOwner(firebaseUser.getUid());
        post.setLikes(0);
        post.setCreated_at(now);
    }

    private boolean verifyFilds(){
        if (post.getHashtag().isEmpty()){
            hashtag.setError(getString(R.string.erro_empty));
            if (!verifyHashtag(post.getHashtag())){
                hashtag.setError(getString(R.string.erro_invalid));
            }
        }
        if (post.getContent().isEmpty()){
            content.setError(getString(R.string.erro_invalid));
        }

        return true;
    }

    private boolean verifyHashtag(String hashtag){
        if (!hashtag.contains("#")){
            return false;
        }
        return true;
    }

}
