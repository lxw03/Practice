package com.example.cw.practice.music.music;

import android.support.annotation.Nullable;

import com.example.cw.practice.music.base.BasePresenter;
import com.example.cw.practice.music.base.BaseView;
import com.example.cw.practice.music.model.Song;

/**
 * Created by eengoo on 17/3/20.
 */

public interface MusicPlayerContract {

    interface Presenter extends BasePresenter{

        void retrieveLastPlayMode();

        void setSongAsFavorite(Song song, boolean favorite);

        void bindPlaybackService();

        void unbindPlaybackService();
    }

    interface View extends BaseView<Presenter>{

        void handlerError(Throwable error);

        // TODO: 17/3/20
        void onPlaybackServiceBound();

        void onPlaybackServiceUnbound();

        void onSongSetAsFavorite(@Nullable Song song);

        void onSongUpdated(@Nullable Song song);

        // TODO: 17/3/20
        void updatePlayMode();

        void updatePlayToggle(boolean play);

        void updateFavoriteToggle(boolean favorite);
    }
}
