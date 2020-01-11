package com.elegion.data.repository;

import com.elegion.data.database.BehanceDao;
import com.elegion.domain.model.user.User;
import com.elegion.domain.repository.ProfileRepository;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProfileDBRepository implements ProfileRepository {
    private User user;
    private String username;

    @Inject
    BehanceDao mBehanceDao;
    @Override
    public Single<User> getUser() {

        return Single.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                user = mBehanceDao.getUserByName(username);
                return user; //null
            }
        });
    }

    public void insertUser(){
        //do nothing
    }

}
