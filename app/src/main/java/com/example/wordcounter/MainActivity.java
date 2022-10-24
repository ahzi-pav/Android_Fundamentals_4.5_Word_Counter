package com.example.wordcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinkedList<String> list = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContent();

        int gridCount = getResources().getInteger(R.integer.grid_count);

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new WordListAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            String [] saved_list = savedInstanceState.getStringArray("saved_list");
            list.clear();
            for (int i = 0; i < saved_list.length; i++) {
                list.addLast(saved_list[i]);
            }
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] savedList = list.toArray(new String[list.size()]);
        outState.putStringArray("saved_list", savedList);
    }

    public void onCLickFAB(View view) {
        list.addLast("Word " + (list.size()));
        mAdapter.notifyDataSetChanged();

        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
    }

    public void setContent() {
        for (int i = 0; i < 20; i++) {
            list.addLast("Word " + i);
        }
    }
}