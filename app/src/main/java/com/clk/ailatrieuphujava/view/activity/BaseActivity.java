package com.clk.ailatrieuphujava.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;


import com.clk.ailatrieuphujava.OnActCallBack;
import com.clk.ailatrieuphujava.OnMainCallBack;
import com.clk.ailatrieuphujava.OnUpdateUi;
import com.clk.ailatrieuphujava.R;
import com.clk.ailatrieuphujava.view.fragment.BaseFragment;
import com.clk.ailatrieuphujava.viewmodel.BaseViewModel;

import java.lang.reflect.Constructor;

public abstract class BaseActivity<T extends ViewBinding, VM extends BaseViewModel>
        extends AppCompatActivity implements View.OnClickListener, OnActCallBack, OnUpdateUi, OnMainCallBack {
    protected T binding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(initClassVM());
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract Class<VM> initClassVM();

    protected abstract void initViews();

    protected abstract T initViewBinding();

    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        // do nothiing
    }

    public void showFragment(String tag, Object data, boolean isBacked) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) constructor.newInstance();
            fragment.setCallBack(this);
            fragment.setData(data);
            fragment.setEventUi(this);
            fragment.setCallBackMain(this);
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                            R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.ln_main, fragment, tag);
            if (isBacked) {
                trans.addToBackStack(null);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backToPrevious() {
        onBackPressed();
    }

    public void callBack(String key, Object data) {
    }

    @Override
    public void execOnMainThread(Runnable runnable) {
        runOnUiThread(runnable);
    }

    @Override
    public void upDateUI(Runnable runnable) {
        runOnUiThread(runnable);
    }
}
