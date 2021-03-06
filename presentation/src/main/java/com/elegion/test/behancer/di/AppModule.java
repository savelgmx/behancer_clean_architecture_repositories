package com.elegion.test.behancer.di;

import android.arch.persistence.room.Room;

import com.elegion.data.database.BehanceDao;
import com.elegion.test.behancer.AppDelegate;
import com.elegion.data.Storage;
import com.elegion.data.database.BehanceDatabase;

import javax.inject.Inject;

import toothpick.config.Module;


/**
 * Created by tanchuev on 23.04.2018.
 */


public class AppModule extends Module {
    @Inject
    BehanceDao mBehanceDao;

    private final AppDelegate mApp;

    public AppModule(AppDelegate mApp) {
        this.mApp = mApp;
        bind(AppDelegate.class).toInstance(provideApp());
        bind(Storage.class).toInstance(provideStorage());
        bind(BehanceDao.class).toInstance(provideDao());
    }

/*
    BehanceDatabase provideDatabase() {
        return Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    BehanceDao provideBehanceDao(BehanceDatabase database) {
        return database.getBehanceDao();
    }

    Storage provideStorage(BehanceDao dao) {
        return new Storage(dao);
    }
*/

    AppDelegate provideApp() {
        return mApp;
    }

    Storage provideStorage() {
        final BehanceDatabase database = Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();

        return new Storage(database.getBehanceDao());
    }
    BehanceDao provideDao() {
        final BehanceDatabase database = Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();

        return database.getBehanceDao();

    }

}
