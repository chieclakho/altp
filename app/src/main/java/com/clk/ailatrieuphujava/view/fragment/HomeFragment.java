package com.clk.ailatrieuphujava.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clk.ailatrieuphujava.CommonUtils;
import com.clk.ailatrieuphujava.MediaManeger;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.FragmentHomeBinding;
import com.clk.ailatrieuphujava.dialog.AboutDialog;
import com.clk.ailatrieuphujava.dialog.SettingsDialog;
import com.clk.ailatrieuphujava.viewmodel.CommonVM;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, CommonVM> {
    private static final String KEY_SOUNDBG = "KEY_SOUNDBG";
    public static String TAG = HomeFragment.class.getName();

    @Override
    protected Class<CommonVM> initClassModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        String textSoundBG = CommonUtils.getInstance().getPref(KEY_SOUNDBG);
        if (textSoundBG != null){
            if (textSoundBG.equals("true")) {
                MediaManeger.getInstance().playBG(R.raw.bgmusic);
            } else if (textSoundBG.equals("false")) {
                MediaManeger.getInstance().stopBG();
            }
        }
        binding.ivPlay.setOnClickListener(this);
        binding.ivAbout.setOnClickListener(this);
        binding.ivSetting.setOnClickListener(this);
        binding.ivArchvement.setOnClickListener(this);
    }

    @Override
    protected FragmentHomeBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.iv_play) {
            MediaManeger.getInstance().stopBG();
            callBackMain.showFragment(RuleFragment.TAG, null, true);
        } else if (v.getId() == R.id.iv_about) {
            showDiglogAbout();
        } else if (v.getId() == R.id.iv_setting) {
            showDiglogSetting();
        }else if (v.getId() == R.id.iv_archvement) {
           callBackMain.showFragment(FragmentHighScore.TAG , null , true);
        }
    }

    private void showDiglogSetting() {
        SettingsDialog settingsDialog = new SettingsDialog(context);
        settingsDialog.setCancelable(true);
        settingsDialog.setCanceledOnTouchOutside(true);
        settingsDialog.show();
    }

    private void showDiglogAbout() {
        AboutDialog aboutDialog = new AboutDialog(context);
        aboutDialog.setCancelable(true);
        aboutDialog.setCanceledOnTouchOutside(true);
        aboutDialog.show();
    }
}