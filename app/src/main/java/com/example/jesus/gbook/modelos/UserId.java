package com.example.jesus.gbook.modelos;

import android.support.annotation.NonNull;

public class UserId {

    public String userId;

    public <T extends UserId> T withId(@NonNull final String id){

        this.userId = userId;
        return (T) this;
    }
}
