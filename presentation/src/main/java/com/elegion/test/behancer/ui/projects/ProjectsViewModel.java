package com.elegion.test.behancer.ui.projects;


import android.support.v4.widget.SwipeRefreshLayout;
import com.elegion.domain.model.project.Project;
import com.elegion.domain.service.ProjectService;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectsViewModel {

/*
    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mBehanceApi;
*/

    @Inject
    ProjectService mService;

    private Disposable mDisposable;
    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
/*
    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private ObservableArrayList<Project> mProjects = new ObservableArrayList<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::loadProjects;
*/

    @Inject
    public ProjectsViewModel() {
    }

 /*   public void getProjects() {
        mCompositeDisposable.add(
                mService.getProjects()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mView.showRefresh())
                        .doFinally(mView::hideRefresh)
                        .subscribe(
                                response -> mView.showProjects(response),
                                throwable -> mView.showError())
        );
    }

*/
/*
    public void loadProjects() {
        mDisposable = mService.getProjects()
                .doOnSuccess(response -> mService.insertProjects(response))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            mProjects.addAll(response.getProjects());
                        },
                        throwable -> mIsErrorVisible.set(true));
    }
*/

    public void dispatchDetach() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

/*
    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public ObservableArrayList<Project> getProjects() {
        return mProjects;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
*/
}
