package com.ch.co.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.ch.co.ipc.aidl.IPerson;

import androidx.annotation.Nullable;

public class PersonService extends Service {
    IPerson.Stub stub = new IPerson.Stub() {

        @Override
        public String hello(String someone) throws RemoteException {
            return someone;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
