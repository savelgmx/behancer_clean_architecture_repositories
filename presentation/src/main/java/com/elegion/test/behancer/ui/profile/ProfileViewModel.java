package com.elegion.test.behancer.ui.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;


import com.elegion.data.Storage;
import com.elegion.data.api.BehanceApi;
import com.elegion.domain.ApiUtils;
import com.elegion.domain.model.user.User;
import com.elegion.domain.service.ProfileService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {
/*
    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mBehanceApi;
*/

    @Inject
    ProfileService mService;

    private Disposable mDisposable;
    private String mUsername;
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private ObservableField<User> mUser = new ObservableField<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::loadProfile;

    @Inject
    public ProfileViewModel() {
    }

    public void loadProfile() {
        mDisposable = mService.getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            mUser.set(response.getUser());
                        },
                        throwable -> mIsErrorVisible.set(true));
    }

    public void dispatchDetach() {
        mService =null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public ObservableField<User> getUser() {
        return mUser;
    }
}
