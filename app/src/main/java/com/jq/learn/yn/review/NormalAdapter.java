package com.jq.learn.yn.review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.NormalViewHolder> {

    private List<String> mTitles = new ArrayList<>();

    public NormalAdapter(List<String> titles) {
        mTitles = titles;
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new NormalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        holder.setTitle(mTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        NormalViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item);
        }

        void setTitle(String title) {
            mTextView.setText(title);
        }

    }
}
