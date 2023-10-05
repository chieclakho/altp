package com.clk.ailatrieuphujava.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import com.clk.ailatrieuphujava.App;
import com.clk.ailatrieuphujava.CommonUtils;
import com.clk.ailatrieuphujava.MediaManeger;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.databinding.DialogSettingsBinding;

public class SettingsDialog extends Dialog implements View.OnClickListener {
    private final DialogSettingsBinding binding;
    private static final String KEY_SOUND = "KEY_SOUND";
    private static final String KEY_SOUNDBG = "KEY_SOUNDBG";
    private boolean isSound, isSoundBG;

    public SettingsDialog(@NonNull Context context) {
        super(context);
        binding = DialogSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        String tvSound = CommonUtils.getInstance().getPref(KEY_SOUND);
        String tvSoundBG = CommonUtils.getInstance().getPref(KEY_SOUNDBG);
        if (tvSoundBG != null) isSoundBG = Boolean.parseBoolean(tvSoundBG);
        if (tvSound != null) isSound = Boolean.parseBoolean(tvSound);
        if (isSound) {
            binding.ivSound.setBackgroundResource(R.drawable.toggle_button_on);
        } else {
            binding.ivSound.setBackgroundResource(R.drawable.toggle_button_off);
        }
        if (isSoundBG) {
            binding.ivMusicBG.setBackgroundResource(R.drawable.toggle_button_on);
        } else {
            binding.ivMusicBG.setBackgroundResource(R.drawable.toggle_button_off);
        }
        binding.tbSound.setOnClickListener(this);
        binding.tbSoundBg.setOnClickListener(this);
    }

    private void setImage(String key) {
        if (key.equals(KEY_SOUND)) {
            if (isSound) {
                isSound = false;
                binding.ivSound.setBackgroundResource(R.drawable.toggle_button_off);
            } else {
                isSound = true;
                binding.ivSound.setBackgroundResource(R.drawable.toggle_button_on);
            }
        } else if (key.equals(KEY_SOUNDBG)){
            if (isSoundBG) {
                isSoundBG = false;
                MediaManeger.getInstance().stopBG();
                binding.ivMusicBG.setBackgroundResource(R.drawable.toggle_button_off);
            } else {
                isSoundBG = true;
                MediaManeger.getInstance().playBG(R.raw.bgmusic);
                binding.ivMusicBG.setBackgroundResource(R.drawable.toggle_button_on);
            }
        }
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(App.getInstance(), androidx.appcompat.R.anim.abc_fade_in));
        if (v.getId() == R.id.tb_soundBg) {
            setImage(KEY_SOUNDBG);
        } else if (v.getId() == R.id.tb_sound) {
            setImage(KEY_SOUND);
        }
    }

    @Override
    protected void onStop() {
        CommonUtils.getInstance().savePref(KEY_SOUNDBG, "" + isSoundBG);
        CommonUtils.getInstance().savePref(KEY_SOUND, "" + isSound);
        super.onStop();
    }
}
