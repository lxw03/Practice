package com.example.cw.practice.googleMusic.ui;

/**
 * Created by cw on 2017/3/21.
 */

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cw.practice.R;
import com.example.cw.practice.googleMusic.MusicService;
import com.example.cw.practice.googleMusic.utils.AlbumArtCache;


/**
 * A class that shows the media queue to the user
 */
public class PlaybackControlsFragment extends Fragment {

    private ImageButton mPlayPause;
    private TextView mTitle;
    private TextView mSubtitle;
    private TextView mExtraInfo;
    private ImageView mAlbumArt;
    private String mArtUrl;

    //receive callbacks from the mediaController. Here we update our state such as which queue is being shown,
    // the current title and description and the PlaybackState.
    private final MediaControllerCompat.Callback mCallback = new MediaControllerCompat.Callback() {
        @Override
        public void onPlaybackStateChanged(PlaybackStateCompat state) {
            super.onPlaybackStateChanged(state);
            PlaybackControlsFragment.this.onPlaybackStateChanged(state);
        }

        @Override
        public void onMetadataChanged(MediaMetadataCompat metadata) {
            super.onMetadataChanged(metadata);
            PlaybackControlsFragment.this.onMetadataChanged(metadata);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_playback_controls, container, false);
        mPlayPause = (ImageButton) rootView.findViewById(R.id.play_pause);
        mPlayPause.setEnabled(true);
        mPlayPause.setOnClickListener(mButtonListener);

        mTitle = (TextView) rootView.findViewById(R.id.title);
        mSubtitle = (TextView) rootView.findViewById(R.id.artist);
        mExtraInfo = (TextView) rootView.findViewById(R.id.extra_info);
        mAlbumArt = (ImageView) rootView.findViewById(R.id.album_art);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FullScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
                MediaMetadataCompat metadata = controller.getMetadata();
                if (metadata != null){
                    intent.putExtra(MusicPlayerActivity.EXTRA_START_FULLSCREEN, metadata);
                }
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
        if (controller != null){
            onConnected();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
        if (controller != null){
            controller.unregisterCallback(mCallback);
        }
    }

    public void onConnected(){
        MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
        if (controller != null){
            onMetadataChanged(controller.getMetadata());
            onPlaybackStateChanged(controller.getPlaybackState());
            controller.registerCallback(mCallback);
        }
    }

    private void onMetadataChanged(MediaMetadataCompat metadata){
        if (getActivity() == null){
            return;
        }
        if (metadata == null){
            return;
        }
        mTitle.setText(metadata.getDescription().getTitle());
        mSubtitle.setText(metadata.getDescription().getSubtitle());
        String artUrl = null;
        if (metadata.getDescription().getIconUri() != null){
            artUrl = metadata.getDescription().getIconUri().toString();
        }
        if (!TextUtils.equals(artUrl, mArtUrl)){
            mArtUrl = artUrl;
            Bitmap art = metadata.getDescription().getIconBitmap();
            AlbumArtCache cache = AlbumArtCache.getInstance();
            if (art == null){
                art = cache.getIconImage(mArtUrl);
            }
            if (art != null){
                mAlbumArt.setImageBitmap(art);
            }else {
                cache.fetch(artUrl, new AlbumArtCache.FetchListener() {
                    @Override
                    public void onFetched(String artUrl, Bitmap bigImage, Bitmap iconImage) {
                        if (iconImage != null){
                            if (isAdded()){
                                mAlbumArt.setImageBitmap(iconImage);
                            }
                        }
                    }
                });
            }
        }
    }

    private void onPlaybackStateChanged(PlaybackStateCompat state){
        if (getActivity() == null){
            return;
        }
        if (state == null){
            return;
        }
        boolean enablePlay = false;
        switch (state.getState()){
            case PlaybackStateCompat.STATE_PAUSED:
            case PlaybackStateCompat.STATE_STOPPED:
                enablePlay =true;
                break;
            case PlaybackStateCompat.STATE_ERROR:
                Toast.makeText(getActivity(), state.getErrorMessage(), Toast.LENGTH_LONG).show();
                break;
        }

        if (enablePlay){
            mPlayPause.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_play_arrow_black_36dp));
        }else {
            mPlayPause.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_pause_black_36dp));
        }

        MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
        String extraInfo = null;
        if (controller != null && controller.getExtras() != null){
            String castName = controller.getExtras().getString(MusicService.EXTRA_CONNECTED_CAST);
            if (castName != null){
                extraInfo = getResources().getString(R.string.casting_to_device, castName);
            }
        }
        setExtraInfo(extraInfo);
    }

    public void setExtraInfo(String extraInfo){
        if (extraInfo == null){
            mExtraInfo.setVisibility(View.GONE);
        }else {
            mExtraInfo.setText(extraInfo);
            mExtraInfo.setVisibility(View.VISIBLE);
        }
    }

    private final View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
            PlaybackStateCompat stateObj = controller.getPlaybackState();
            final int state = stateObj == null? PlaybackStateCompat.STATE_NONE: stateObj.getState();
            switch (v.getId()){
                case R.id.play_pause:{
                    Log.d("PlaybackControls","Button pressed in state:"+ state);
                    if (state == PlaybackStateCompat.STATE_NONE ||
                            state == PlaybackStateCompat.STATE_PAUSED||
                            state == PlaybackStateCompat.STATE_STOPPED){
                        playMedia();
                    }else if (state == PlaybackStateCompat.STATE_PLAYING ||
                            state == PlaybackStateCompat.STATE_BUFFERING ||
                            state == PlaybackStateCompat.STATE_CONNECTING){
                        pauseMedia();
                    }
                }
            }
        }
    };

    private void playMedia(){
        MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
        if (controller != null){
            controller.getTransportControls().play();
        }
    }

    private void pauseMedia(){
        MediaControllerCompat controller = ((FragmentActivity)getActivity()).getSupportMediaController();
        if (controller != null){
            controller.getTransportControls().pause();
        }
    }
}
