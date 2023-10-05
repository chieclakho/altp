package com.clk.ailatrieuphujava.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
    public class Question {
        @NonNull
        @ColumnInfo(name = "_id")
        @PrimaryKey
        public String id;
        @ColumnInfo(name = "question")
        public String question;
        @NonNull
        @ColumnInfo(name = "level")
        public int level;
        @ColumnInfo(name = "casea")
        public String caseA;
        @ColumnInfo(name = "caseb")
        public String caseB;
        @ColumnInfo(name = "casec")
        public String caseC;
        @ColumnInfo(name = "cased")
        public String caseD;
        @ColumnInfo(name = "truecase")
        public String trueCase;

    public Question() {

    }

    public Question(@NonNull String id, String question, int level, String caseA, String caseB, String caseC, String caseD, String trueCase) {
        this.id = id;
        this.question = question;
        this.level = level;
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.trueCase = trueCase;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", level=" + level +
                ", caseA='" + caseA + '\'' +
                ", caseB='" + caseB + '\'' +
                ", caseC='" + caseC + '\'' +
                ", caseD='" + caseD + '\'' +
                ", trueCase='" + trueCase + '\'' +
                '}';
    }
}
