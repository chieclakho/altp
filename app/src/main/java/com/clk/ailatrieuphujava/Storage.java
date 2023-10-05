package com.clk.ailatrieuphujava;

import com.clk.ailatrieuphujava.db.Question;

import java.util.List;

public class Storage {
    public List<Question> listQ;
    public Question question;
    public boolean startThread;

    public void setListQuestion(List<Question> listQuestion) {
        listQ = listQuestion;
    }

    public void startThread(boolean b) {
        startThread = b;
    }
}
