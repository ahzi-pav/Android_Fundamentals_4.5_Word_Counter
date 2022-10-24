package com.example.wordcounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LinkedList<String> list;
    private LayoutInflater mInflater;


    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mTextview;
        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            mTextview = itemView.findViewById(R.id.textView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            String element = list.get(position);
            if (!element.contains("Clicked!")) {
                list.set(position, "Clicked! " + element);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public WordListAdapter(Context context, LinkedList<String> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        holder.mTextview.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
