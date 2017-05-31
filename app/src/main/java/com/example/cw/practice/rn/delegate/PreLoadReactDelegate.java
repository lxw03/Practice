package com.example.cw.practice.rn.delegate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.cw.practice.rn.preload.ReactNativePreLoader;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionListener;

import javax.annotation.Nullable;


/**
 * Created by cw on 2017/5/31.
 */

public class PreLoadReactDelegate {

    private static final String TAG = "PreLoadReactDelegate";
    private final int REQUEST_OVERLAY_PERMISSION_CODE = 1111;
    private static final String REDBOX_PERMISSION_GRANTED_MESSAGE = "Overlay permissions have been granted.";
    private static final String REDBOX_PERMISSION_MESSAGE = "Overlay permissions needs to be granted in order for react native apps to run in dev mode";
    @Nullable
    private final Activity mActivity;
    @Nullable
    private final FragmentActivity mFragmentActivity;
    @Nullable
    private final String mMainComponentName;
    @Nullable
    private ReactRootView mReactRootView;
    @Nullable
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    @Nullable
    private PermissionListener mPermissionListener;
    @Nullable
    private Callback mPermissionsCallback;

    public PreLoadReactDelegate(Activity activity, @Nullable String mainComponentName) {
        this.mActivity = activity;
        this.mMainComponentName = mainComponentName;
        this.mFragmentActivity = null;
    }

    public PreLoadReactDelegate(FragmentActivity fragmentActivity, @Nullable String mainComponentName) {
        this.mFragmentActivity = fragmentActivity;
        this.mMainComponentName = mainComponentName;
        this.mActivity = null;
    }

    @Nullable
    protected Bundle getLaunchOptions() {
        return null;
    }

    protected ReactRootView createRootView() {
        return new ReactRootView(this.getContext());
    }

    protected ReactNativeHost getReactNativeHost() {
        return ((ReactApplication)this.getPlainActivity().getApplication()).getReactNativeHost();
    }

    public ReactInstanceManager getReactInstanceManager() {
        return this.getReactNativeHost().getReactInstanceManager();
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean needsOverlayPermission = false;
        if(this.getReactNativeHost().getUseDeveloperSupport() && Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this.getContext())) {
            needsOverlayPermission = true;
            Intent serviceIntent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + this.getContext().getPackageName()));
            FLog.w("React", "Overlay permissions needs to be granted in order for react native apps to run in dev mode");
            Toast.makeText(this.getContext(), "Overlay permissions needs to be granted in order for react native apps to run in dev mode", Toast.LENGTH_LONG).show();
            ((Activity)this.getContext()).startActivityForResult(serviceIntent, 1111);
        }

        if(this.mMainComponentName != null && !needsOverlayPermission) {
//            // 1.从缓存中获取RootView
//            mReactRootView = ReactNativePreLoader.getReactRootView(mMainComponentName);
//
//            if(mReactRootView == null) {
//
//                // 2.缓存中不存在RootView,直接创建
//                Log.d(TAG, "ReactRootView -- begin: " + System.currentTimeMillis());
//                mReactRootView = new ReactRootView(mActivity);
//                Log.d(TAG, "ReactRootView -- end: " + System.currentTimeMillis());
//                Log.d(TAG, "startReactApplication -- begin: " + System.currentTimeMillis());
//                mReactRootView.startReactApplication(
//                        getReactInstanceManager(),
//                        mMainComponentName,
//                        null);
//                Log.d(TAG, "startReactApplication -- end: " + System.currentTimeMillis());
//            }
//            // 3.将RootView设置到Activity布局
//            this.getPlainActivity().setContentView(this.mReactRootView);
            Log.d(TAG, "loadApp: -- begin" + System.currentTimeMillis());
            this.loadApp(mMainComponentName);
            Log.d(TAG, "loadApp: -- end" + System.currentTimeMillis());
        }

        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    }

    public void loadApp(String appKey) {
        if(this.mReactRootView != null) {
            throw new IllegalStateException("Cannot loadApp while app is already running.");
        } else {
            this.mReactRootView = this.createRootView();
            this.mReactRootView.startReactApplication(this.getReactNativeHost().getReactInstanceManager(), appKey, this.getLaunchOptions());
            this.getPlainActivity().setContentView(this.mReactRootView);
        }
    }

    public void onPause() {
        if(this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onHostPause(this.getPlainActivity());
        }

    }

    public void onResume() {
        if(this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onHostResume(this.getPlainActivity(), (DefaultHardwareBackBtnHandler)this.getPlainActivity());
        }

        if(this.mPermissionsCallback != null) {
            this.mPermissionsCallback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }

    }

    public void onDestroy() {
        if(this.mReactRootView != null) {
            this.mReactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }

        if(this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onHostDestroy(this.getPlainActivity());
        }

        //清除view
        ReactNativePreLoader.detachRootView(mMainComponentName);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onActivityResult(this.getPlainActivity(), requestCode, resultCode, data);
        } else if(requestCode == 1111 && Build.VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(this.getContext())) {
            if(this.mMainComponentName != null) {
                this.loadApp(this.mMainComponentName);
            }

            Toast.makeText(this.getContext(), "Overlay permissions have been granted.", Toast.LENGTH_LONG).show();
        }

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(this.getReactNativeHost().hasInstance() && this.getReactNativeHost().getUseDeveloperSupport()) {
            if(keyCode == 82) {
                this.getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
                return true;
            }

            boolean didDoubleTapR = ((DoubleTapReloadRecognizer)Assertions.assertNotNull(this.mDoubleTapReloadRecognizer)).didDoubleTapR(keyCode, this.getPlainActivity().getCurrentFocus());
            if(didDoubleTapR) {
                this.getReactNativeHost().getReactInstanceManager().getDevSupportManager().handleReloadJS();
                return true;
            }
        }

        return false;
    }

    public boolean onBackPressed() {
        if(this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onBackPressed();
            return true;
        } else {
            return false;
        }
    }

    public boolean onNewIntent(Intent intent) {
        if(this.getReactNativeHost().hasInstance()) {
            this.getReactNativeHost().getReactInstanceManager().onNewIntent(intent);
            return true;
        } else {
            return false;
        }
    }

    @TargetApi(23)
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        this.mPermissionListener = listener;
        this.getPlainActivity().requestPermissions(permissions, requestCode);
    }

    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        this.mPermissionsCallback = new Callback() {
            public void invoke(Object... args) {
                if(PreLoadReactDelegate.this.mPermissionListener != null && PreLoadReactDelegate.this.mPermissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
                    PreLoadReactDelegate.this.mPermissionListener = null;
                }

            }
        };
    }

    private Context getContext() {
        return (Context)(this.mActivity != null?this.mActivity:(Context) Assertions.assertNotNull(this.mFragmentActivity));
    }

    private Activity getPlainActivity() {
        return (Activity)this.getContext();
    }
}
