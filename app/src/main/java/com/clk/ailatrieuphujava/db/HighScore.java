package com.clk.ailatrieuphujava.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HighScore {
    @NonNull
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    public int ID;
    @ColumnInfo(name = "Name")
    public String name;
    @NonNull
    @ColumnInfo(name = "Score")
    public int score;

    public HighScore() {
    }

    public HighScore(int ID, String name, int score) {
        this.ID = ID;
        this.name = name;
        this.score = score;
    }
}
