package com.alocar.frontend.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;

public class BaseActivity extends Activity {
    boolean keyboardOn = false;
    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new KeyboardListener();

    private boolean keyboardListenersAttached = false;
    private ViewGroup rootLayout;

    protected void onShowKeyboard(int keyboardHeight) {}
    protected void onHideKeyboard() {}

    protected void attachKeyboardListeners(View view) {
        if (keyboardListenersAttached) {
            return;
        }

        rootLayout = (ViewGroup) view;
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (keyboardListenersAttached) {
            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(keyboardLayoutListener);
        }
    }

    private class KeyboardListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            int heightDiff = rootLayout.getRootView().getHeight() - rootLayout.getHeight();
            int contentViewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

            LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(BaseActivity.this);

            if(heightDiff <= contentViewTop){
                if (keyboardOn) {
                    onHideKeyboard();

                    Intent intent = new Intent("KeyboardWillHide");
                    broadcastManager.sendBroadcast(intent);
                    keyboardOn = false;
                }
            } else {
                if (!keyboardOn) {
                    int keyboardHeight = heightDiff - contentViewTop;
                    onShowKeyboard(keyboardHeight);

                    Intent intent = new Intent("KeyboardWillShow");
                    intent.putExtra("KeyboardHeight", keyboardHeight);
                    broadcastManager.sendBroadcast(intent);
                    keyboardOn = true;
                }
            }
        }
    }
}