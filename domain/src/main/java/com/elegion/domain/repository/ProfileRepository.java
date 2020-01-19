package com.elegion.domain.repository;

import com.elegion.domain.model.user.User;

import io.reactivex.Single;

public interface ProfileRepository {
    String SERVER = "SERVER";
    String DB = "DB";

    void insertUser(User user);

    Single<User> getUserInfo(String username);
}
