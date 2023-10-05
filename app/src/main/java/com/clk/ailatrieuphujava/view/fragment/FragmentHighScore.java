package com.clk.ailatrieuphujava.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clk.ailatrieuphujava.adapter.HighScoreAdapter;
import com.clk.ailatrieuphujava.databinding.FragmentHighScoreBinding;
import com.clk.ailatrieuphujava.db.HighScore;
import com.clk.ailatrieuphujava.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;

public class FragmentHighScore extends BaseFragment<FragmentHighScoreBinding, CommonVM> {

    public static String TAG = FragmentHighScore.class.getName();

    @Override
    protected Class<CommonVM> initClassModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        List<HighScore> scoreList = new ArrayList<>();
        scoreList.add(new HighScore(1, "Hao", 120000));
        scoreList.add(new HighScore(2, "ha", 12354));
        scoreList.add(new HighScore(3, "rth", 563563));
        scoreList.add(new HighScore(4, "trh", 445345453));
        HighScoreAdapter adapter = new HighScoreAdapter(scoreList, context);
        binding.rcHighScore.setAdapter(adapter);
    }

    @Override
    protected FragmentHighScoreBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHighScoreBinding.inflate(inflater, container, false);
    }
}
