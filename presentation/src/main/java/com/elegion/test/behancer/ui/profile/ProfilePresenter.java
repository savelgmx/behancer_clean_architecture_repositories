package com.elegion.test.behancer.ui.profile;

import com.elegion.data.Storage;
import com.elegion.data.api.BehanceApi;
import com.elegion.test.behancer.common.BasePresenter;

import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter extends BasePresenter {

    private ProfileView mView;
    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;

    @Inject
    public ProfilePresenter(Storage storage, BehanceApi behanceApi) {
        mStorage = storage;
        mApi = behanceApi;
    }

    public void setView(ProfileView view) {
        mView = view;
    }


    public void getProfile(String username) {
        mCompositeDisposable.add(mApi.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(username) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response -> {
                            mView.showProfile(response.getUser());
                            mView.bind(response.getUser());
                        },
                        throwable -> {
                            mView.showError();
                        }));
    }
}
