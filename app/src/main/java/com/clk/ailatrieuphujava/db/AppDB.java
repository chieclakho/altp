package com.clk.ailatrieuphujava.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class , HighScore.class }, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract QuestionDAO getQuestionDAO();
}
