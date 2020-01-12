package com.elegion.data.repository;

import com.elegion.data.database.BehanceDao;
import com.elegion.domain.model.user.Image;
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
                Image image = mBehanceDao.getImageFromUser(user.getId());
                user.setImage(image);
                return user; //null
            }
        });
    }

    public void insertUser(){
        //копируем код из Storage.inserUser и пределваем его без UserResponce
        user = mBehanceDao.getUserByName(username);
        Image image  = user.getImage();
        image.setId(user.getId());
        image.setUserId(user.getId());

        mBehanceDao.insertUser(user);
        mBehanceDao.insertImage(image);

    }

    /*
        public void insertUser(UserResponse response) {
        User user = response.getUser();
        Image image = user.getImage();
        image.setId(user.getId());
        image.setUserId(user.getId());

        mBehanceDao.insertUser(user);
        mBehanceDao.insertImage(image);
    }

    public UserResponse getUser(String username) {
        User user = mBehanceDao.getUserByName(username);
        Image image = mBehanceDao.getImageFromUser(user.getId());
        user.setImage(image);

        UserResponse response = new UserResponse();
        response.setUser(user);

        return response;
    }

     */
}
