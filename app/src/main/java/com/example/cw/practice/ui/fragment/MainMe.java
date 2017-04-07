package com.example.cw.practice.ui.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cw.practice.R;
import com.example.cw.practice.practice.SecurityCode.SecurityCodeActivity;
import com.example.cw.practice.practice.animation.AnimationActivity;
import com.example.cw.practice.practice.animation.TypeEvaluatorActivity;
import com.example.cw.practice.practice.danmaku.DanmakuActivity;
import com.example.cw.practice.practice.download.DownloadActivity;
import com.example.cw.practice.practice.faceTest.FaceTestActivity;
import com.example.cw.practice.practice.notification.NotificationActivity;
import com.example.cw.practice.practice.percentLayout.PercentRelativeLayoutActivity;
import com.example.cw.practice.practice.remoteViews.RemoteViewActivity;
import com.example.cw.practice.practice.shader.RadarActivity;
import com.example.cw.practice.practice.snackbar.SnackbarActivity;
import com.example.cw.practice.practice.soundRecorder.SoundRecorderActivity;
import com.example.cw.practice.practice.statusBar.StatusBarActivity;
import com.example.cw.practice.practice.windowManager.WindowManagerActivity;

/**
 * Created by chenwei on 17/2/6.
 */

public class MainMe extends Fragment{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_main, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        btn1 = (Button) view.findViewById(R.id.me_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AnimationActivity.class);
                startActivity(intent);
            }
        });

        btn2 = (Button) view.findViewById(R.id.me_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TypeEvaluatorActivity.class);
                startActivity(intent);
            }
        });

        btn3 = (Button) view.findViewById(R.id.me_btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        btn4 = (Button) view.findViewById(R.id.me_btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StatusBarActivity.class);
                startActivity(intent);
            }
        });
        btn5 = (Button) view.findViewById(R.id.me_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SnackbarActivity.class);
                startActivity(intent);
            }
        });
        btn6 = (Button) view.findViewById(R.id.me_btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanmakuActivity.class);
                startActivity(intent);
            }
        });
        btn7 = (Button) view.findViewById(R.id.me_btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PercentRelativeLayoutActivity.class);
                startActivity(intent);
            }
        });
        btn8 = (Button) view.findViewById(R.id.me_btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();
            }
        });
        btn9 = (Button) view.findViewById(R.id.me_btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWindowPermission();
            }
        });
        btn10 = (Button) view.findViewById(R.id.me_btn10);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RemoteViewActivity.class);
                startActivity(intent);
            }
        });
        btn11 = (Button) view.findViewById(R.id.me_btn11);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoundRecorderActivity.class);
                startActivity(intent);
            }
        });
        btn12 = (Button) view.findViewById(R.id.me_btn12);
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                startActivity(intent);
            }
        });
        btn13 = (Button) view.findViewById(R.id.me_btn13);
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecurityCodeActivity.class);
                startActivity(intent);
            }
        });
        btn14 = (Button) view.findViewById(R.id.me_btn14);
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RadarActivity.class);
                startActivity(intent);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkWindowPermission(){
//        int windowPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SYSTEM_ALERT_WINDOW);
//        if (windowPermission == PackageManager.PERMISSION_GRANTED){
//            Intent intent = new Intent(getActivity(), WindowManagerActivity.class);
//            startActivity(intent);
//        }else {
//            MainMe.this.requestPermissions(new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 2);
//        }
        if (! Settings.canDrawOverlays(getActivity())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent,10);
        }else {
            Intent intent = new Intent(getActivity(), WindowManagerActivity.class);
            startActivity(intent);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkCameraPermission(){
//        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.CAMERA)){
//                new AlertDialog.Builder(getActivity())
//                        .setMessage("申请相机权限")
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
//                                Intent intent = new Intent(getContext(), FaceTestActivity.class);
//                                startActivity(intent);
//                            }
//                        }).show();
//            }else {
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
//                Intent intent = new Intent(getContext(), FaceTestActivity.class);
//                startActivity(intent);
//            }
//        }

        int cameraPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        if (cameraPermission == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(getActivity(), "已经有这个权限", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), FaceTestActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getActivity(), "还没有这个权限", Toast.LENGTH_SHORT).show();
            //去申请权限
            //fragment中申请权限
            MainMe.this.requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            //区别
            //在Activity中申请权限
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getContext(), FaceTestActivity.class);
                    startActivity(intent);
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                        Toast.makeText(getActivity(), "相机权限被禁止", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Settings.canDrawOverlays(getActivity())) {

            }
        }
    }
}
