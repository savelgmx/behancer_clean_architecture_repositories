package com.elegion.test.behancer.di;

import com.elegion.data.repository.ProfileDBRepository;
import com.elegion.data.repository.ProfileServerRepository;
import com.elegion.data.repository.ProjectDBRepository;
import com.elegion.data.repository.ProjectServerRepository;
import com.elegion.domain.repository.ProfileRepository;
import com.elegion.domain.repository.ProjectRepository;

import toothpick.config.Module;


/**
 * Created by tanchuev on 23.04.2018.
 */


public class RepositoryModule extends Module {

    ProjectDBRepository     mProjectDBRepository;
    ProjectServerRepository mProjectServerRepository;


    public RepositoryModule(){
        //bind(IFoo.class).toProvider(FooProvider.class); // case 3
        //Каждый  @Inject IFoo будет ассоциирован с новой реализацией Foo, 
        // которую в свою очередь порождает создание нового объекта FooProvider.
        bind(ProjectServerRepository.class).toInstance(provideProjectServerRepository());
        bind(ProfileDBRepository.class).toInstance(provideProjectDBRepository(mProjectDBRepository));
    }


    /*   @Provides
       @Singleton
       @Named(ProjectRepository.SERVER)
   */    ProjectRepository provideProjectServerRepository(ProjectServerRepository repository) {
        return repository;
    }

/*
    @Provides
    @Singleton
    @Named(ProjectRepository.DB)
*/
    ProjectRepository provideProjectDBRepository(ProjectDBRepository repository) {
        return repository;
    }
/*
    @Provides
    @Singleton
    @Named(ProfileRepository.DB)
*/
    ProfileDBRepository provideProfileDBRepository(ProfileDBRepository repository) {
        return repository;
    }


/*    @Provides
    @Singleton
    @Named(ProfileRepository.SERVER)
 */
    ProfileRepository provideProfileServerRepository(ProfileServerRepository repository) {
        return repository;
    }
}
