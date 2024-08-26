package com.ch.fo.fragmentDemo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ch.co.databinding.FragmentMainBinding;

public class FragmentMainActivity extends Activity {
    private FragmentMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
