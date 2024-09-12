package com.ch.wheel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends AndroidViewModel {
    private final MutableLiveData<Float> angleLiveData = new MutableLiveData<>();

    public SharedViewModel(Application application) {
        super(application);
    }

    public void setAngle(float angle) {
        angleLiveData.setValue(angle);
    }

    public LiveData<Float> getAngleLiveData() {
        return angleLiveData;
    }
}