package com.clk.ailatrieuphujava.dialog;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;

import com.clk.ailatrieuphujava.App;
import com.clk.ailatrieuphujava.OnActCallBack;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.DialogCallBinding;
import com.clk.ailatrieuphujava.db.Question;

public class CallDialog extends Dialog {
    private DialogCallBinding binding;
    private OnActCallBack callBack;

    public CallDialog(@NonNull Context context, OnActCallBack callBack) {
        super(context, R.style.Theme_AiLaTrieuPhu_Dialog);
        this.callBack = callBack;
        binding = DialogCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        binding.btnClose.setOnClickListener(v -> {
            callBack.callBack(null, null);
            dismiss();
        });
        initViews();
    }

    private void initViews() {
        Question question = App.getInstance().getStorage().question;
        String textAnswer = "";
        String answer = question.trueCase;
        switch (answer) {
            case "1":
                textAnswer = "A: " + question.caseA;
                break;
            case "2":
                textAnswer = "B: " + question.caseB;
                break;
            case "3":
                textAnswer = "C: " + question.caseC;
                break;
            case "4":
                textAnswer = "D: " + question.caseD;
                break;
        }
        binding.tvQuestion.setText(String.format("Theo tôi đáp án đúng là : \n%s", textAnswer));
    }
}
