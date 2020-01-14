package com.elegion.domain.service;

import com.elegion.domain.repository.ProfileRepository;

import javax.inject.Inject;
import javax.inject.Named;

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


}
