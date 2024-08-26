package com.ch.fo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.ch.co.databinding.ActivityMainFoBinding;
import com.ch.co.sqlDemo.SqlMainActivity;
import com.ch.fo.fragmentDemo.FragmentMainActivity;

public class FoMainActivity extends Activity {
    private static final String TAG = "FoMainActivity";
    private ActivityMainFoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainFoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.sqlDemo.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, SqlMainActivity.class);
            startActivity(intent);
        });
        binding.fragmentDemo.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, FragmentMainActivity.class);
            startActivity(intent);
        });
    }
}
