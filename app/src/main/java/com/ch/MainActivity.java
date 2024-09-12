package com.ch;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ch.co.CoMainActivity;
import com.ch.co.R;
import com.ch.co.databinding.ActivityMainBinding;
import com.ch.fo.FoMainActivity;
import com.ch.radar.RadarMapMainActivity;
import com.ch.wheel.GameActivity1;
import com.ch.wheel.GameActivity2;
import com.ch.wheel.GameActivity3;
import com.ch.wheel.GameActivity5;
import com.ch.wheel.GameActivity6;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.coMain.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, CoMainActivity.class);
            startActivity(intent);
        });
        binding.foMain.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, FoMainActivity.class);
            startActivity(intent);
        });
        binding.radarMain.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, RadarMapMainActivity.class);
            startActivity(intent);
        });
        binding.wheelPanMain.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, GameActivity6.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
