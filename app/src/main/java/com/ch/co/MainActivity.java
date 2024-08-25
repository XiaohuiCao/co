package com.ch.co;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ch.co.animal7.AnimalMainActivity;
import com.ch.co.bitmap12.BitMapMainActivity;
import com.ch.co.databinding.ActivityMainBinding;
import com.ch.co.drawable6.DrawableMainActivity;
import com.ch.co.ipc.IpcMainActivity;
import com.ch.co.sqlDemo.SqlMainActivity;
import com.ch.co.threadpool11.ThreadPoolMainActivity;
import com.ch.co.view345.event.ViewEventMainActivity;
import com.ch.co.view345.principle.ViewPrincipleMainActivity;
import com.ch.co.view345.remote.ViewRemoteMainActivity;
import com.ch.co.window8.WindowMainActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
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
        binding.btnBook2.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, SqlMainActivity.class);
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
