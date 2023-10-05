package com.clk.ailatrieuphujava.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.clk.ailatrieuphujava.db.Question;


public class PlayViewModel extends BaseViewModel {
    private final MutableLiveData<Integer> time = new MutableLiveData<>(30);
    private final MutableLiveData<Integer> index = new MutableLiveData<>(0);
    private boolean isRunning;
    private boolean isPaused = true;
    private String mAnswer ;
    private Question mQuestion;

    public Question getmQuestion() {
        return mQuestion;
    }

    public MutableLiveData<Integer> getTime() {
        return time;
    }

    public MutableLiveData<Integer> getIndex() {
        return index;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
    public String getmAnswer() {
        return mAnswer;
    }

    public void startCountDown() {
        new Thread(() -> {
            while (isRunning) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isPaused) continue;
                if (time.getValue() != null) {
                    if (time.getValue() > 0) {
                        time.postValue(time.getValue() - 1);
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onCleared() {
        isRunning = false;
        super.onCleared();
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public boolean checkAnswer() {
        return mQuestion.trueCase.equals(mAnswer);
    }

    public void setQuestion(Question question) {
        mQuestion = question;
    }

    public void nextQuestion() {
        if (index.getValue() != null) {
            index.setValue(index.getValue() + 1);
            time.postValue(15);
        }
    }
    public void reSetTime() {
        time.setValue(15);
    }
}
