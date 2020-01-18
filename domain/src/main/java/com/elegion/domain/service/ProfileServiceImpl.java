package com.elegion.domain.service;

import com.elegion.domain.ApiUtils;
import com.elegion.domain.model.user.User;
import com.elegion.domain.repository.ProfileRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class ProfileServiceImpl implements ProfileService{
    @Inject
    @Named(ProfileRepository.SERVER)
    ProfileRepository mProfileServerRepository;
    @Inject
    @Named(ProfileRepository.DB)
    ProfileRepository mProfileDBRepository;
    @Inject
    public ProfileServiceImpl(){

    }

    public Single getUser() {
        return mProfileServerRepository.getUser()
                .doOnSuccess(mProfileDBRepository::insertUser)
                .onErrorReturn(throwable ->
                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())
                        ? mProfileDBRepository.getUser().blockingGet()
                        :null);
    }
    public void insertUser(User user){
         mProfileDBRepository.insertUser(user);
    }
}
