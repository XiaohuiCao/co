package com.ch.co.ipc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.ch.co.R;
import com.ch.co.ipc.aidl.Book;
import com.ch.co.ipc.manager.UserManager;
import com.ch.co.ipc.model.User;
import com.ch.co.utils.MyConstants;
import com.ch.co.utils.MyUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class IpcMainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipc_activity_main);
        UserManager.sUserId = 2;
        findViewById(R.id.to_act_b).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(IpcMainActivity.this, BActivity.class);
            User user = new User(0, "jake", true);
            user.book = new Book();
            intent.putExtra("extra_user", (Serializable) user);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "UserManage.sUserId=" + UserManager.sUserId);
        persistToFile();
        super.onStart();
    }

    private void persistToFile() {
        new Thread(() -> {
            User user = new User(1, "hello world", false);
            File dir = new File(MyConstants.CHAPTER_2_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(cachedFile));
                objectOutputStream.writeObject(user);
                Log.d(TAG, "persist user:" + user);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                MyUtils.close(objectOutputStream);
            }
        }).start();
    }
}
