package com.elegion.data.repository;

import com.elegion.data.api.BehanceApi;
import com.elegion.domain.model.user.User;
import com.elegion.domain.model.user.UserResponse;
import com.elegion.domain.repository.ProfileRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ProfileServerRepository implements ProfileRepository {

    @Inject
    BehanceApi mApi;
    private String username;

    @Inject
    public ProfileServerRepository(){}


    @Override
    public void insertUser(User user) {
        //ничего не делаем ибо в нет в серверном АПИ такой функции
        //а прописана она у нас только совместимости ради

    }

    @Override
    public Single<User> getUserInfo(String username) {
        return mApi.getUserInfo(username).map(new Function<UserResponse, User>() {
            @Override
            public User apply(UserResponse userResponse) throws Exception {
                return userResponse.getUser(); // null
            }
        });
    }

}
