package com.ch.co.view.event;

import static com.ch.co.utils.MyUtils.*;
import java.util.ArrayList;
import com.ch.co.R;
import com.ch.co.view.event.ui.InterInterceptView;
import com.ch.co.view.event.ui.ListViewOverrideDispatch;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 场景2:存在同向滑动，同为竖向或者横向滑动，需判断为内部滑动还是外部滑动
 *
 * 解决方法：
 * 必须重写ListView的dispatchTouchEvent()方法，
 */
public class ViewInterInterceptAct extends Activity {
    private static final String TAG = "view event DemoActivity_2";
    private InterInterceptView mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event_inter_intercept_act);
        Log.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = findViewById(R.id.container);
        final int screenWidth = getScreenMetrics(this).widthPixels;
        final int screenHeight = getScreenMetrics(this).heightPixels;
        int pageNum = 5;
        for (int i = 0; i < pageNum; i++) {

            // 在layout中使用自定义的ListView，并重写了其dispatch()方法
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.view_event_layout_inter, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(setColor(i));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListViewOverrideDispatch listView = layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("内部拦截 name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.adapter_content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setInterInterceptView(mListContainer);
        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(ViewInterInterceptAct.this, "click item",
                Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent action:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent action:" + event.getAction());
        return super.onTouchEvent(event);
    }
}
