package com.clk.ailatrieuphujava.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.clk.ailatrieuphujava.App;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.FragmentAudienceBinding;
import com.clk.ailatrieuphujava.db.Question;
import com.clk.ailatrieuphujava.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentAudience extends BaseFragment<FragmentAudienceBinding, CommonVM> {
    public static final String TAG = FragmentAudience.class.getName();
    private int a, b, c, d;

    @Override
    protected Class<CommonVM> initClassModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        createValueABCD();
        drawColumn();
    }

    private void drawColumn() {
        Question question = (Question) data;
        List<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        String answer = question.trueCase;
        options.remove(answer);
        int maxHeight = (int) getResources().getDimension(R.dimen.d_400);
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
            for (int i = 0; i < maxHeight; i++) {
                updateColumnView(maxHeight, i, finalHA, binding.viewA, binding.tvA);
                updateColumnView(maxHeight, i, finalHB, binding.viewB, binding.tvB);
                updateColumnView(maxHeight, i, finalHC, binding.viewC, binding.tvC);
                updateColumnView(maxHeight, i, finalHD, binding.viewD, binding.tvD);

                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void updateColumnView(int maxHeight, int i, int hView, View viewColumn, TextView tvPercent) {
        if (i <= hView) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewColumn.getLayoutParams();
            params.height = i;
            int percent = i * 100 / maxHeight;
            callBackMain.execOnMainThread(() -> {
                viewColumn.setLayoutParams(params);
                viewColumn.postInvalidate();
                tvPercent.setText(String.format("%s%%", percent));
            });
        }
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
    protected FragmentAudienceBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentAudienceBinding.inflate(inflater, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getInstance().getStorage().startThread(true);
    }
}
