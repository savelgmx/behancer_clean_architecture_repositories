package com.elegion.test.behancer.di;

import com.elegion.domain.service.ProfileService;
import com.elegion.domain.service.ProfileServiceImpl;
import com.elegion.domain.service.ProjectService;
import com.elegion.domain.service.ProjectServiceImpl;

import javax.inject.Singleton;

import toothpick.config.Module;

/**
 * Created by tanchuev on 23.04.2018.
 */

public class ServiceModule extends Module  {

    ProfileServiceImpl profileService;
    ProjectServiceImpl projectService;
    public ServiceModule(){

        bind(ProjectService.class).to(ProjectServiceImpl.class);
        bind(ProfileService.class).to(ProfileServiceImpl.class);
    }



    ProjectService provideProjectService(ProjectServiceImpl projectService) {
        return projectService;
    }
    ProfileService provideProfileService(ProfileServiceImpl profileService){
        return profileService;
    }
}
