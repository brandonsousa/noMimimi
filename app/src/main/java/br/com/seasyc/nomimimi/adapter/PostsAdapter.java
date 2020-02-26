package br.com.seasyc.nomimimi.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import br.com.seasyc.nomimimi.R;
import br.com.seasyc.nomimimi.model.Posts;
import br.com.seasyc.nomimimi.util.Factory;
import br.com.seasyc.nomimimi.viewholder.PostsViewHolder;

public class PostsAdapter extends Adapter<PostsViewHolder> {

    private Context mContext;
    private List<Posts> postCardList;
    private Posts p;

    public PostsAdapter(Context mContext, List<Posts> postCard) {
        this.mContext = mContext;
        this.postCardList = postCard;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mimimi_item, parent, false);
        return new PostsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostsViewHolder post, final int position) {

        post.hashtags.setText(postCardList.get(position).getHashtag());
        post.content.setText(postCardList.get(position).getContent());
        post.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Factory.toastMessage(mContext, postCardList.get(position).getP_id());
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
