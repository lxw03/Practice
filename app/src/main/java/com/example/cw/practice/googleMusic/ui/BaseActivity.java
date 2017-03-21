package com.example.cw.practice.googleMusic.ui;

import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cw on 2017/3/21.
 */

public abstract class BaseActivity extends AppCompatActivity implements MediaBrowserProvider{

    private MediaBrowserCompat mMediaBrowser;
    private PlaybackControlsFragment mControlsFragment;
}
