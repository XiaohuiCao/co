package com.ch.co.utils;

import android.os.Environment;

public class MyConstants {
    public static final String CHAPTER_2_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/singwhatiwanna/chapter_2/";

    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";
    public static final String REMOTE_ACTION = "com.ch.co.action_REMOTE";
    public static final String EXTRA_REMOTE_VIEWS = "extra_remoteViews";
    public static final String REMOTE_VIEW_MSG_ID = "remoteViewId";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;


}
