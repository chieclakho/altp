package com.clk.ailatrieuphujava.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.clk.ailatrieuphujava.R;

public class AboutDialog extends Dialog {

    public AboutDialog(@NonNull Context context) {
        super(context, R.style.Theme_AiLaTrieuPhu_Dialog);
        setContentView(R.layout.about_dialog);
        findViewById(R.id.tv_close).setOnClickListener(v -> dismiss());
    }
}
