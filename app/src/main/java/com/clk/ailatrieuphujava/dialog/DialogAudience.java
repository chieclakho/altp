package com.clk.ailatrieuphujava.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.clk.ailatrieuphujava.App;
import com.clk.ailatrieuphujava.OnActCallBack;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.DialogAudienceBinding;
import com.clk.ailatrieuphujava.db.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DialogAudience extends Dialog {
    public static final String TAG = DialogAudience.class.getName();
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private final Handler handler = new Handler(msg -> {
        Object[] objects = (Object[]) msg.obj;
        int maxHeight = (int) objects[0];
        int i = (int) objects[1];
        int hView = (int) objects[2];
        View viewColumn = (View) objects[3];
        TextView tvPercent = (TextView) objects[4];
        if (i <= hView) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewColumn.getLayoutParams();
            params.height = i;
            int percent = i * 100 / maxHeight;
            viewColumn.setLayoutParams(params);
            viewColumn.postInvalidate();
            tvPercent.setText(String.format("%s%%", percent));
        }
        return false;
    });
    private final DialogAudienceBinding binding;
    private final OnActCallBack callBack;

    public DialogAudience(@NonNull Context context, OnActCallBack callBack) {
        super(context, R.style.Theme_AiLaTrieuPhu_Dialog);
        this.callBack = callBack;
        binding = DialogAudienceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        initViews();
    }


    protected void initViews() {
        createValueABCD();
        drawColumn();
    }

    private void drawColumn() {
        Question question = App.getInstance().getStorage().question;
        List<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        String answer = question.trueCase;
        options.remove(answer);
        int maxHeight = (int) getContext().getResources().getDimension(R.dimen.d_400);
        int hA = 0, hB = 0, hC = 0, hD = 0;
        hA = maxHeight * a / 100;
        hB = maxHeight * b / 100;
        hC = maxHeight * c / 100;
        hD = maxHeight * d / 100;
        if (!options.contains("2")) {
            hA = maxHeight * b / 100;
            hB = maxHeight * a / 100;
        } else if (!options.contains("3")) {
            hA = maxHeight * c / 100;
            hC = maxHeight * a / 100;
        } else if (!options.contains("4")) {
            hA = maxHeight * d / 100;
            hD = maxHeight * a / 100;
        }
        int finalHA = hA;
        int finalHB = hB;
        int finalHC = hC;
        int finalHD = hD;
        new Thread(() -> {
            for (int i = 0; i < maxHeight; i+=3) {
                updateColumnView(maxHeight, i, finalHA, binding.viewA, binding.tvA);
                updateColumnView(maxHeight, i, finalHB, binding.viewB, binding.tvB);
                updateColumnView(maxHeight, i, finalHC, binding.viewC, binding.tvC);
                updateColumnView(maxHeight, i, finalHD, binding.viewD, binding.tvD);
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void updateColumnView(int maxHeight, int i, int hView, View viewColumn, TextView tvPercent) {
        Message msg = new Message();
        msg.obj = new Object[]{
                maxHeight, i, hView, viewColumn, tvPercent
        };
        msg.setTarget(handler);
        msg.sendToTarget();
    }

    private void createValueABCD() {
        Random rd = new Random();
        int x = rd.nextInt(70);
        a = 101 - x;
        b = rd.nextInt(101 - a);
        if (a + b < 100) {
            c = rd.nextInt(101 - a - b);
        }
        if (a + b + c < 100) {
            d = 100 - a - b - c;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        callBack.callBack("KEY", null);
    }
}
