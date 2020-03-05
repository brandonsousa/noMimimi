package br.com.seasyc.nomimimi.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.List;

import br.com.seasyc.nomimimi.R;
import br.com.seasyc.nomimimi.config.FirebaseSettings;
import br.com.seasyc.nomimimi.model.Posts;
import br.com.seasyc.nomimimi.model.Saves;
import br.com.seasyc.nomimimi.util.Factory;
import br.com.seasyc.nomimimi.viewholder.PostsViewHolder;

public class PostsAdapter extends Adapter<PostsViewHolder> {

    private Context mContext;
    private List<Posts> postCardList;
    private Posts p;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public PostsAdapter(Context mContext, List<Posts> postCard) {
        this.mContext = mContext;
        this.postCardList = postCard;
        firebaseAuth = FirebaseSettings.getFirebaseAuth();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mimimi_item, parent, false);
        return new PostsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostsViewHolder post, final int position) {

        post.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.like.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.ic_favorite_black_24dp)));
                Posts.like(postCardList.get(position).getP_id(), mContext);
                post.like.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.ic_favorite_border_black_24dp)));
            }
        });
        post.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Saves save = new Saves();
                Date now = new Date();
                save.setOwner_id(firebaseUser.getUid());
                save.setPost_id(postCardList.get(position).getP_id());
                save.setCreated_at(now);
                save.newSave();
            }
        });
        post.hashtags.setText(postCardList.get(position).getHashtag());
        post.content.setText(postCardList.get(position).getContent());
        post.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Posts.like(postCardList.get(position).getP_id(), mContext);
            }
        });
        post.likes.setText(postCardList.get(position).getLikes() + " curtidas");

    }

    @Override
    public int getItemCount() {
        return postCardList.size();
    }

}
