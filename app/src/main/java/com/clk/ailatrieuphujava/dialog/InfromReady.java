package com.clk.ailatrieuphujava.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.clk.ailatrieuphujava.OnActCallBack;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.ViewReadyBinding;

public class InfromReady extends Dialog {
    public static final String KEY_BACK = "KEY_BACK";
    public static final String KEY_READY = "KEY_READY";
    private final OnActCallBack callBack;
    private final ViewReadyBinding binding;

    public InfromReady(@NonNull Context context, OnActCallBack callBack) {
        super(context, R.style.Theme_AiLaTrieuPhu_Dialog);
        this.callBack = callBack;
        binding = ViewReadyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        binding.btnReady.setOnClickListener(v -> doReady());
        binding.btnBack.setOnClickListener(v -> doBack());
    }

    private void doBack() {
        callBack.callBack(KEY_BACK, null);
        dismiss();
    }

    private void doReady() {
        callBack.callBack(KEY_READY, null);
        dismiss();
    }

    public void setInfromReady(String notification, String textOk, String textCancle) {
        binding.tvNotice.setText(notification);
        binding.btnBack.setText(textCancle);
        binding.btnReady.setText(textOk);
    }
}
