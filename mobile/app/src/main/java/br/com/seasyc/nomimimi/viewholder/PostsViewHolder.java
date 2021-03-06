package br.com.seasyc.nomimimi.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.com.seasyc.nomimimi.R;

public class PostsViewHolder extends RecyclerView.ViewHolder {
    public CardView card;
    public TextView hashtags, content, likes;
    public Button like, save;
    public PostsViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.cv_item_mimimi);
        save = itemView.findViewById(R.id.save);
        hashtags = itemView.findViewById(R.id.hashtag);
        content = itemView.findViewById(R.id.post_content);
        like = itemView.findViewById(R.id.like);
        likes = itemView.findViewById(R.id.likeds);
    }
}
