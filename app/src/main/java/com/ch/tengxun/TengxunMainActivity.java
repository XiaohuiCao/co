//package com.ch.tengxun;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import com.tencent.tencentmap.mapsdk.maps.MapView;
//import com.tencent.tencentmap.mapsdk.maps.TencentMap;
//
//public class TengxunMainActivity extends AppCompatActivity {
//
//    private MapView mapView;
//    private TencentMap tencentMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.tengxun_activity_main);
//
//        // 初始化 MapView
//        mapView = findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//
//        // 获取 TencentMap 对象
//        tencentMap = mapView.getMap();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//}