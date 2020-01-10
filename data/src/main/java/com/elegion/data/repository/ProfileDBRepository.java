package com.elegion.data.repository;

import com.elegion.domain.model.user.User;
import com.elegion.domain.repository.ProfileRepository;

import io.reactivex.Single;

public class ProfileDBRepository implements ProfileRepository {
    @Override
    public Single<User> getUser() {
        return null;
    }
}
