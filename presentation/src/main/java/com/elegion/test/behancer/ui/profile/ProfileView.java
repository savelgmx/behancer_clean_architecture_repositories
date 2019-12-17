package com.elegion.test.behancer.ui.profile;

import android.support.annotation.NonNull;

import com.elegion.domain.model.user.User;
import com.elegion.test.behancer.common.BaseView;


public interface ProfileView extends BaseView {

    void showProfile(@NonNull User user);

    void bind(@NonNull User user);
}
