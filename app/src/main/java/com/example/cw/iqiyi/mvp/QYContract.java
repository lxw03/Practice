package com.example.cw.iqiyi.mvp;

import com.example.cw.iqiyi.base.IBasePresenter;
import com.example.cw.iqiyi.base.IBaseView;

/**
 * Created by cw on 2017/5/3.
 */

public interface QYContract {

    interface IView extends IBaseView<IPresenter>{

        void showOrHideLoadingView(boolean show);

        void showExceptionTips(boolean netWork);

        boolean isFinished();

    }

    interface IPresenter extends IBasePresenter{

        void initTabs();
    }
}
