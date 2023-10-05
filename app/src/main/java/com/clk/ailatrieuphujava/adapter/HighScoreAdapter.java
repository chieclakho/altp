package com.clk.ailatrieuphujava.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.db.HighScore;

import java.util.List;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.HighScoreHolder> {
    private final List<HighScore> listHighScore;
    private final Context context;

    public HighScoreAdapter(List<HighScore> listHighScore, Context context) {
        this.listHighScore = listHighScore;
        this.context = context;
    }

    @NonNull
    @Override
    public HighScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item__high_scode, parent, false);
        return new HighScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighScoreHolder holder, int position) {
        HighScore item = listHighScore.get(position);
        holder.tvScore.setText(String.format("%s đ", item.score));
        holder.tvName.setText(item.name);
        if (position == 0) {
            holder.tvRank.setBackgroundResource(R.drawable.ic_rank_1);
            holder.itemView.setBackgroundColor(Color.parseColor("#9C27B0"));
            holder.tvName.setTextColor(Color.parseColor("#FF9800"));
        } else if (position == 1) {
            holder.tvRank.setBackgroundResource(R.drawable.ic_rank_2);
            holder.itemView.setBackgroundColor(Color.parseColor("#009688"));
            holder.tvName.setTextColor(Color.parseColor("#00E676"));
        } else if (position == 2) {
            holder.tvRank.setBackgroundResource(R.drawable.ic_rank_3);
            holder.itemView.setBackgroundColor(Color.parseColor("#03A9F4"));
            holder.tvName.setTextColor(Color.parseColor("#9C27B0"));
        } else {
            holder.tvRank.setText(String.format("%s", position + 1));
        }
    }

    @Override
    public int getItemCount() {
        return listHighScore.size();
    }

    public static class HighScoreHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvScore, tvRank;

        public HighScoreHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvRank = itemView.findViewById(R.id.tv_rank);
        }
    }
}
