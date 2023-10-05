package com.clk.ailatrieuphujava;


public interface OnMainCallBack {
    void showFragment(String tag, Object data, boolean isBacked);
    void backToPrevious();
    void execOnMainThread(Runnable runnable);

    void initDB();
}
