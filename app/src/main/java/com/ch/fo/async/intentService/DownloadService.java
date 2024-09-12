package com.ch.fo.async.intentService;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class DownloadService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public DownloadService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
