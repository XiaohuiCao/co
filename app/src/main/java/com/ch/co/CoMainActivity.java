package com.ch.co;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.navigation.ui.AppBarConfiguration;
import com.ch.co.animal7.AnimalMainActivity;
import com.ch.co.bitmap12.BitMapMainActivity;
import com.ch.co.databinding.ActivityMainCoBinding;
import com.ch.co.drawable6.DrawableMainActivity;
import com.ch.co.ipc.IpcMainActivity;
import com.ch.co.threadpool11.ThreadPoolMainActivity;
import com.ch.co.view345.event.ViewEventMainActivity;
import com.ch.co.view345.principle.ViewPrincipleMainActivity;
import com.ch.co.view345.remote.ViewRemoteMainActivity;
import com.ch.co.window8.WindowMainActivity;

public class CoMainActivity extends Activity {
    private static final String TAG = "CoMainActivity";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainCoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainCoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ipc2.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, IpcMainActivity.class);
            startActivity(intent);});
        binding.viewEvent3.setOnClickListener(view ->  {
            Intent intent = new Intent();
            intent.setClass(this, ViewEventMainActivity.class);
            startActivity(intent);});
        binding.viewPrinciple4.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, ViewPrincipleMainActivity.class);
            startActivity(intent);} );
        binding.viewRemote5.setOnClickListener(view ->  {
            Intent intent = new Intent();
            intent.setClass(this, ViewRemoteMainActivity.class);
            startActivity(intent);});
        binding.drawable6.setOnClickListener(view ->  {
            Intent intent = new Intent();
            intent.setClass(this, DrawableMainActivity.class);
            startActivity(intent);});
        binding.animal7.setOnClickListener(view ->  {
            Intent intent = new Intent();
            intent.setClass(this, AnimalMainActivity.class);
            startActivity(intent);});
        binding.window8.setOnClickListener(view ->  {
            Intent intent = new Intent();
            intent.setClass(this, WindowMainActivity.class);
            startActivity(intent);});
        binding.threadPoll11.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, ThreadPoolMainActivity.class);
            startActivity(intent);
        });
        binding.bitmap12.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, BitMapMainActivity.class);
            startActivity(intent);
        });
    }
}
