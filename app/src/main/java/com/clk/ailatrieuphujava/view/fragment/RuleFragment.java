package com.clk.ailatrieuphujava.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clk.ailatrieuphujava.MediaManeger;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.FragmentRuleBinding;
import com.clk.ailatrieuphujava.dialog.InfromReady;
import com.clk.ailatrieuphujava.viewmodel.CommonVM;

public class RuleFragment extends BaseFragment<FragmentRuleBinding, CommonVM> {
    public static final String TAG = RuleFragment.class.getName();
    @Override
    protected Class<CommonVM> initClassModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        MediaManeger.getInstance().playGame(R.raw.song_rrule, mp -> MediaManeger.getInstance()
                .playGame(R.raw.ready, mp1 -> showReadyDialog()));
        binding.btnHide.setOnClickListener(v -> showReadyDialog());
    }

    private void showReadyDialog() {
        InfromReady infromReady = new InfromReady(context, (key, data) -> {
            if (key.equals(InfromReady.KEY_BACK)) {
                doBack();
            } else if (key.equals(InfromReady.KEY_READY)) {
                doReady();
            }
        });
        infromReady.show();
    }

    private void doReady() {
        MediaManeger.getInstance().stopGame();
        callBackMain.showFragment(PlayFragment.TAG, null, true);
    }

    private void doBack() {
        MediaManeger.getInstance().stopGame();
        callBackMain.backToPrevious();
    }

    @Override
    protected FragmentRuleBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentRuleBinding.inflate(inflater, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MediaManeger.getInstance().stopGame();
    }
}