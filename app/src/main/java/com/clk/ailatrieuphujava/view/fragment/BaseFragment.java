package com.clk.ailatrieuphujava.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.clk.ailatrieuphujava.OnActCallBack;
import com.clk.ailatrieuphujava.OnMainCallBack;
import com.clk.ailatrieuphujava.OnUpdateUi;
import com.clk.ailatrieuphujava.viewmodel.BaseViewModel;

public abstract class BaseFragment<T extends ViewBinding, VM extends BaseViewModel> extends Fragment implements View.OnClickListener {
    protected T binding;
    protected VM model;
    protected Object data;
    protected Context context;
    protected OnActCallBack callBack;
    protected OnMainCallBack callBackMain;

    public void setCallBackMain(OnMainCallBack callBackMain) {
        this.callBackMain = callBackMain;
    }

    protected OnUpdateUi eventUi;

    public void setEventUi(OnUpdateUi eventUi) {
        this.eventUi = eventUi;
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }

    public void setCallBack(OnActCallBack callBack) {
        this.callBack = callBack;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater, container);
        model = new ViewModelProvider(this).get(initClassModel());
        initViews();
        return binding.getRoot();
    }

    protected abstract Class<VM> initClassModel();


    protected abstract void initViews();

    protected abstract T initViewBinding(LayoutInflater inflater, ViewGroup container);


    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        // do Nothing
    }
}
