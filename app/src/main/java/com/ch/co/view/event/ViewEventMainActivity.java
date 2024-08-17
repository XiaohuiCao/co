package com.ch.co.view.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ch.co.R;

public class ViewEventMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event_main_activity);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.viewBasic) {
            Intent intent = new Intent(this, CommonActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.scroll_view_conflict_extern_interception) {
            Intent intent = new Intent(this, ViewExternInterceptAct.class);
            startActivity(intent);
        } else if (v.getId() == R.id.scroll_view_conflict_internal_interception) {
            Intent intent = new Intent(this, ViewInterInterceptAct.class);
            startActivity(intent);
        }
    }
}
