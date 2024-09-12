package com.ch.wheel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DataRepository {

    private static DataRepository instance;
    private final MutableLiveData<Float> angleLiveData = new MutableLiveData<>();

    private DataRepository() {}

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public LiveData<Float> getAngleLiveData() {
        return angleLiveData;
    }

    public void setAngle(float angle) {
        angleLiveData.setValue(angle);
    }
}