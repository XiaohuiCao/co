package com.ch.co.view345.event;

import static com.ch.co.utils.MyUtils.setColor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ch.co.R;
import com.ch.co.ui.ExternHorizontalScrollInterceptViewEx;
import com.ch.co.utils.MyUtils;

import java.util.ArrayList;

/**
 * 场景1:外部滑动方向和内部滑动方向不一致
 */
public class ViewExternInterceptAct extends Activity {
    private static final String TAG = "ViewExternInterceptAct";

    private ExternHorizontalScrollInterceptViewEx mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event_extern_intercept_activity);
        Log.d(TAG, "onCreate");
        initView();
    }

    // 使用外部拦截机制，此处列表使用ListView，内部拦截机制，使用自定义的ListViewEx
    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = findViewById(R.id.container);
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        int pageNum = 6;
        for (int i = 0; i < pageNum; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.view_event_layout_extern, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("外部拦截 page " + (i + 1));
            layout.setBackgroundColor(setColor(i));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        String showText = "";
        for (int i = 0; i < 50; i++) {
            showText = "外部拦截name " + i;
            datas.add(showText);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.adapter_content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        String finalShowText = showText;
        listView.setOnItemClickListener((parent, view, position, id)
                -> Toast.makeText(ViewExternInterceptAct.this, finalShowText,
                Toast.LENGTH_SHORT).show());
    }
}
