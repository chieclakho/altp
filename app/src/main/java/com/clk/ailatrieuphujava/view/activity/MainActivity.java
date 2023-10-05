package com.clk.ailatrieuphujava.view.activity;


import android.view.View;
import android.widget.Toast;

import com.clk.ailatrieuphujava.App;
import com.clk.ailatrieuphujava.MediaManeger;
import com.clk.ailatrieuphujava.OnActCallBack;
import com.clk.ailatrieuphujava.databinding.ActivityMainBinding;
import com.clk.ailatrieuphujava.db.Question;
import com.clk.ailatrieuphujava.dialog.InfromReady;
import com.clk.ailatrieuphujava.view.fragment.HomeFragment;
import com.clk.ailatrieuphujava.viewmodel.CommonVM;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, CommonVM> {
    private List<Question> listDB;

    @Override
    protected Class<CommonVM> initClassVM() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        initDB();
        gotoManiScreen();
    }

    public void initDB() {
        new Thread(() -> {
            try {
                listDB = App.getInstance().getDb().getQuestionDAO().getAllQuestion();
                App.getInstance().getStorage().setListQuestion(listDB);
            } catch (Exception e) {
                runOnui((key, data) -> showAlert());
            }
        }).start();
    }

    private void gotoManiScreen() {
        binding.ivLogo.setVisibility(View.GONE);
        binding.progressbar.setVisibility(View.GONE);
        showFragment(HomeFragment.TAG, null, false);
    }

    private void showAlert() {
        Toast.makeText(this, "Không lấy được dữ liệu câu hỏi ", Toast.LENGTH_SHORT).show();
    }

    private void runOnui(OnActCallBack callBack) {
        runOnUiThread(() -> callBack.callBack(null, null));
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onStop() {
        super.onStop();
        MediaManeger.getInstance().pauseSong();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaManeger.getInstance().playSong();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            showDialog();
            return;
        }
        super.onBackPressed();
    }

    private void showDialog() {
        InfromReady infromReady = new InfromReady(this, (key, data) -> {
            if (key.equals(InfromReady.KEY_READY)) {
                MainActivity.super.onBackPressed();
            }
        });
        infromReady.setInfromReady("Bạn có muốn thoát game", "Ok", "NO");
        infromReady.show();
    }
}