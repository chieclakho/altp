package com.clk.ailatrieuphujava.view.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.clk.ailatrieuphujava.App;
import com.clk.ailatrieuphujava.MediaManeger;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.FragmentPlayBinding;
import com.clk.ailatrieuphujava.db.Question;
import com.clk.ailatrieuphujava.dialog.CallDialog;
import com.clk.ailatrieuphujava.dialog.DialogAudience;
import com.clk.ailatrieuphujava.dialog.InfromReady;
import com.clk.ailatrieuphujava.viewmodel.PlayViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayFragment extends BaseFragment<FragmentPlayBinding, PlayViewModel> {
    public static final String TAG = PlayFragment.class.getName();
    public static final int[] ID_SONG_Q = {R.raw.ques1, R.raw.ques2, R.raw.ques3, R.raw.ques4,
            R.raw.ques5, R.raw.ques6, R.raw.ques7, R.raw.ques8, R.raw.ques9, R.raw.ques10, R.raw.ques11,
            R.raw.ques12, R.raw.ques13, R.raw.ques14, R.raw.ques15};
    public static final String[] money = {"200,000 đ", "400,000 đ", "600,000 đ", "1,000,000 đ",
            "2,000,000 đ", "3,000,000 đ", "6,000,000 đ", "10,000,000 đ", "14,000,000 đ", "22,000,000 đ", "30,000,000 đ",
            "40,000,000 đ", "60,000,000 đ", "85,000,000 đ", "150,000,000 đ"};
    private int a;
    private final Random rd = new Random();
    @Override
    protected Class<PlayViewModel> initClassModel() {
        return PlayViewModel.class;
    }
    @Override
    protected FragmentPlayBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentPlayBinding.inflate(inflater, container, false);
    }
    @Override
    protected void initViews() {
        MediaManeger.getInstance().playBG(R.raw.background_music);
        model.getTime().observe(this, this::checkTime);
        model.setPaused(true);
        model.setRunning(true);
        model.startCountDown();
        model.getIndex().observe(this, this::initQuestion);
        binding.tvAnsa.setOnClickListener(this);
        binding.tvAnsb.setOnClickListener(this);
        binding.tvAnsc.setOnClickListener(this);
        binding.tvAnsd.setOnClickListener(this);
        binding.iv5050.setOnClickListener(this);
        binding.ivCall.setOnClickListener(this);
        binding.ivAudience.setOnClickListener(this);
        binding.ivChange.setOnClickListener(this);
        binding.ivStop.setOnClickListener(this);
        playMusicCheckAnswer();
    }
    @Override
    protected void clickView(View v) {
        model.setPaused(true);
        a = rd.nextInt(3);
        if (v.getId() == R.id.tv_ansa) {
            setBackGround(v, "1");
        } else if (v.getId() == R.id.tv_ansb) {
            setBackGround(v, "2");
        } else if (v.getId() == R.id.tv_ansc) {
            setBackGround(v, "3");
        } else if (v.getId() == R.id.tv_ansd) {
            setBackGround(v, "4");
        } else if (v.getId() == R.id.iv_stop) {
            stopGame();
        } else if (v.getId() == R.id.iv_change) {
            changeQuestion();
        } else if (v.getId() == R.id.iv_5050) {
            init5050();
        } else if (v.getId() == R.id.iv_audience) {
            audience();
        } else if (v.getId() == R.id.iv_call) {
            call();
        }
    }
    private void audience() {
        InfromReady infromReady = new InfromReady(context, (key, data) -> {
            if (key.equals(InfromReady.KEY_READY)) {
                binding.ivAudience.setImageLevel(2);
                binding.ivAudience.setEnabled(false);
                App.getInstance().getStorage().question = model.getmQuestion();
                showDialogAudience();
            }
            model.setPaused(false);
        });
        infromReady.setInfromReady("Bạn có muốn gọi hỏi ý kiến khán giả không?", "Có", "Không");
        model.setPaused(true);
        infromReady.show();
    }
    private void showDialogAudience() {
        DialogAudience audience = new DialogAudience(context, (key, data) -> model.setPaused(false));
        audience.show();
    }
    private void stopGame() {
        InfromReady infromReady = new InfromReady(context, (key, data) -> {
            if (key.equals(InfromReady.KEY_READY)) {
                binding.ivCall.setImageLevel(2);
                callBackMain.backToPrevious();
            } else if (key.equals(InfromReady.KEY_BACK)) {
                model.setPaused(false);
            }
        });
        infromReady.setInfromReady("Bạn có dùng cuộc chơi không", "Có", "Không");
        infromReady.show();
    }
    private void call() {
        InfromReady infromReady = new InfromReady(context, (key, data) -> {
            if (key.equals(InfromReady.KEY_READY)) {
                binding.ivCall.setEnabled(false);
                binding.ivCall.setImageLevel(2);
                App.getInstance().getStorage().question = model.getmQuestion();
                showDialogCall();
            } else if (key.equals(InfromReady.KEY_BACK)) {
                model.setPaused(false);
            }
        });
        infromReady.setInfromReady("Bạn có muốn gọi điện cho người thân", "Đồng ý", "Hủy bỏ");
        infromReady.show();
    }
    private void showDialogCall() {
        CallDialog callDialog = new CallDialog(context, (key, data) -> model.setPaused(false));
        callDialog.show();
    }
    private void setBackGround(View view, String key) {
        setEnabled(false);
        switch (key) {
            case "1":
                if (a == 0) {
                    MediaManeger.getInstance().playGame(R.raw.ans_a, mp -> checkAnswer());
                } else {
                    MediaManeger.getInstance().playGame(R.raw.ans_a, mp -> checkAnswer());
                }
                break;
            case "2":
                if (a == 0) {
                    MediaManeger.getInstance().playGame(R.raw.ans_b, mp -> checkAnswer());
                } else {
                    MediaManeger.getInstance().playGame(R.raw.ans_b, mp -> checkAnswer());
                }
                break;
            case "3":
                if (a == 0) {
                    MediaManeger.getInstance().playGame(R.raw.ans_c, mp -> checkAnswer());
                } else {
                    MediaManeger.getInstance().playGame(R.raw.ans_c, mp -> checkAnswer());
                }
                break;
            case "4":
                if (a == 0) {
                    MediaManeger.getInstance().playGame(R.raw.ans_d, mp -> checkAnswer());
                } else {
                    MediaManeger.getInstance().playGame(R.raw.ans_d, mp -> checkAnswer());
                }
                break;
        }
        model.setAnswer(key);
        resetBackground();
        view.getBackground().setLevel(1);
    }
    private void playMusicCheckAnswer() {
        MediaManeger.getInstance().stopGame();
        if (a == 0) {
            MediaManeger.getInstance().playGame(R.raw.ans_now1, null);
        } else {
            MediaManeger.getInstance().playGame(R.raw.ans_now3, null);
        }
    }
    private void init5050() {
        InfromReady infromReady = new InfromReady(context, (key, data) -> {
            if (key.equals(InfromReady.KEY_READY)) {
                binding.iv5050.setImageLevel(2);
                binding.iv5050.setEnabled(false);
                List<String> options = new ArrayList<>();
                options.add("1");
                options.add("2");
                options.add("3");
                options.add("4");
                String answer = model.getmQuestion().trueCase;
                options.remove(answer);
                removeRandomOption(options);
                removeRandomOption(options);
                options.add(answer);
                if (!options.contains("1")) {
                    binding.tvAnsa.setText("");
                }
                if (!options.contains("2")) {
                    binding.tvAnsb.setText("");
                }
                if (!options.contains("3")) {
                    binding.tvAnsc.setText("");
                }
                if (!options.contains("4")) {
                    binding.tvAnsd.setText("");
                }
            }
            model.setPaused(false);
        });
        infromReady.setInfromReady("Bạn có muốn chọn trợ giúp 50/50", "Đồng ý", "Hủy bỏ");
        model.setPaused(true);
        infromReady.show();
    }
    private void removeRandomOption(List<String> options) {
        a = rd.nextInt(options.size());
        options.remove(a);
    }
    private void changeQuestion() {
        InfromReady infromReady = new InfromReady(context, (key, data) -> {
            if (key.equals(InfromReady.KEY_READY)) {
                binding.ivChange.setImageLevel(2);
                binding.ivChange.setEnabled(false);
                if (model.getIndex().getValue() == null) return;
                int a = (model.getIndex().getValue() + 1);
                new Thread(() -> {
                    Question question = App.getInstance().getDb().getQuestionDAO().getQuestionByLevel(a);
                    changeQuestioned(question);
                }).start();
            }
            model.setPaused(false);
        });
        infromReady.setInfromReady("Bạn có muốn đổi câu hỏi không?", "Đồng ý", "Hủy bỏ");
        model.setPaused(true);
        infromReady.show();
    }
    private void changeQuestioned(Question question) {
        resetBackground();
        model.setQuestion(question);
        binding.tvQuestion.setText(String.format("A : %s", question.question));
        binding.tvAnsa.setText(String.format("A : %s", question.caseA));
        binding.tvAnsb.setText(String.format("B : %s", question.caseB));
        binding.tvAnsc.setText(String.format("C : %s", question.caseC));
        binding.tvAnsd.setText(String.format("D : %s", question.caseD));
    }
    private void startGames() {
        model.setPaused(true);
        model.setRunning(true);
        if (model.getIndex().getValue() == null) return;
        binding.tvMoney.setText(money[model.getIndex().getValue()]);
        if (model.getIndex().getValue() >= 14) {
            Toast.makeText(context, "You win 150,000,000", Toast.LENGTH_SHORT).show();
            MediaManeger.getInstance().playGame(R.raw.best_player, null);
            InfromReady infromReady = new InfromReady(context, null);
            infromReady.setInfromReady("Bạn đã chiến thắng phần thưởng cao nhất của chương trình", "OK", "Thoát");
            infromReady.show();
        } else {
            model.nextQuestion();
        }
    }
    private void setEnabled(boolean value) {
        binding.tvAnsa.setEnabled(value);
        binding.tvAnsb.setEnabled(value);
        binding.tvAnsc.setEnabled(value);
        binding.tvAnsd.setEnabled(value);
    }
    @SuppressLint("SetTextI18n")
    private void checkTime(Integer value) {
        binding.tvTime.setText(value.toString());
        if (value == 0) {
            MediaManeger.getInstance().stopBG();
            checkAnswer();
        }
    }
    private void checkAnswer() {
        if (model.getmAnswer() == null || !model.checkAnswer()) {
            MediaManeger.getInstance().stopBG();
            Toast.makeText(context, "You loose!", Toast.LENGTH_SHORT).show();
            switch (model.getmQuestion().trueCase) {
                case "1":
                    binding.tvAnsa.getBackground().setLevel(3);
                    blinkAnswer(binding.tvAnsa.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.lose_a, mp -> startMusicGameOver());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.lose_a2, mp -> startMusicGameOver());
                    }
                    break;
                case "2":
                    binding.tvAnsb.getBackground().setLevel(3);
                    blinkAnswer(binding.tvAnsb.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.lose_b, mp -> startMusicGameOver());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.lose_b2, mp -> startMusicGameOver());
                    }
                    break;
                case "3":
                    binding.tvAnsc.getBackground().setLevel(3);
                    blinkAnswer(binding.tvAnsc.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.lose_c, mp -> startMusicGameOver());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.lose_c2, mp -> startMusicGameOver());
                    }
                    break;
                case "4":
                    binding.tvAnsd.getBackground().setLevel(3);
                    blinkAnswer(binding.tvAnsd.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.lose_d, mp -> startMusicGameOver());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.lose_d2, mp -> startMusicGameOver());
                    }
                    break;
            }

        } else {
            switch (model.getmQuestion().trueCase) {
                case "1":
                    blinkAnswer(binding.tvAnsa.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.true_a, mp -> startGames());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.true_a2, mp -> startGames());
                    }
                    break;
                case "2":
                    blinkAnswer(binding.tvAnsb.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.true_b, mp -> startGames());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.true_b2, mp -> startGames());
                    }
                    break;
                case "3":
                    blinkAnswer(binding.tvAnsc.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.true_c, mp -> startGames());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.true_c2, mp -> startGames());
                    }
                    break;
                case "4":
                    blinkAnswer(binding.tvAnsd.getBackground());
                    if (a == 0) {
                        MediaManeger.getInstance().playGame(R.raw.true_d3, mp -> startGames());
                    } else {
                        MediaManeger.getInstance().playGame(R.raw.true_d2, mp -> startGames());
                    }
                    break;
            }
        }
    }
    private void blinkAnswer(Drawable background) {
        final Handler handler = new Handler();
        new Thread(() -> {
            int duration = 300; // Thời gian nhấp nháy (milliseconds)
            int blinkCount = 6; // Số lần nhấp nháy

            for (int i = 0; i < blinkCount; i++) {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final int finalI = i;
                handler.post(() -> {
                    if (finalI % 2 == 0) {
                        background.setLevel(3);
                    } else {
                        background.setLevel(2);
                    }
                });
            }
        }).start();
    }
    private void startMusicGameOver() {
        InfromReady infromReady = new InfromReady(context, (key, data) -> {
            if (key.equals(InfromReady.KEY_READY)) {
                callBackMain.initDB();
                initQuestion(0);
                resetLevelHelp();
            } else {
                callBackMain.backToPrevious();
            }
        });
        infromReady.setInfromReady("Bạn có muốn chơi lại không", "OK", "NO");
        infromReady.show();
        if (a == 0) {
            MediaManeger.getInstance().playGame(R.raw.lose, null);
        } else {
            MediaManeger.getInstance().playGame(R.raw.lose2, null);
        }
    }
    private void resetLevelHelp() {
        binding.iv5050.setEnabled(true);
        binding.ivChange.setEnabled(true);
        binding.ivAudience.setEnabled(true);
        binding.ivCall.setEnabled(true);
        binding.ivChange.setImageLevel(0);
        binding.ivAudience.setImageLevel(0);
        binding.ivStop.setImageLevel(0);
        binding.ivCall.setImageLevel(0);
        binding.tvMoney.setText("o đ");
    }
    private void initQuestion(int index) {
        setEnabled(true);
        MediaManeger.getInstance().playBG(R.raw.background_music);
        resetBackground();
        model.setAnswer(null);
        model.setPaused(false);
        model.setRunning(true);
        model.reSetTime();
        Log.e(TAG, "Answer initQuestion " + model.getIndex().getValue());
        Question question = App.getInstance().getStorage().listQ.get(index);
        model.setQuestion(question);
        binding.tvTitle.setText(String.format("Câu %s", index + 1));
        binding.tvQuestion.setText(question.question);
        binding.tvAnsa.setText(String.format("A : %s", question.caseA));
        binding.tvAnsb.setText(String.format("B : %s", question.caseB));
        binding.tvAnsc.setText(String.format("C : %s", question.caseC));
        binding.tvAnsd.setText(String.format("D : %s", question.caseD));
        MediaManeger.getInstance().playGame(ID_SONG_Q[index], mp -> model.setPaused(false));
    }
    private void resetBackground() {
        binding.tvAnsa.getBackground().setLevel(0);
        binding.tvAnsb.getBackground().setLevel(0);
        binding.tvAnsc.getBackground().setLevel(0);
        binding.tvAnsd.getBackground().setLevel(0);
    }
}