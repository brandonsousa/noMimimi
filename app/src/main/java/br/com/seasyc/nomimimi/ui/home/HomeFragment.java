package br.com.seasyc.nomimimi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import br.com.seasyc.nomimimi.NewPost;
import br.com.seasyc.nomimimi.R;
import br.com.seasyc.nomimimi.adapter.PostsAdapter;
import br.com.seasyc.nomimimi.config.FirebaseSettings;
import br.com.seasyc.nomimimi.model.Posts;
import br.com.seasyc.nomimimi.util.Factory;

public class HomeFragment extends Fragment {

    private DatabaseReference databaseReference;

    private List<Posts> posts;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PostsAdapter postsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.allPosts);
        databaseReference = FirebaseSettings.getDatabaseReference().child("posts");
        posts = new ArrayList<Posts>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    Posts p = data.getValue(Posts.class);
                    posts.add(p);

                }
                recyclerView.setHasFixedSize(true);
                linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                postsAdapter = new PostsAdapter(getContext(), posts);


                recyclerView.setAdapter(postsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}