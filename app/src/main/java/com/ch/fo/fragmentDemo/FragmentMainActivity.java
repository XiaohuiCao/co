package com.ch.fo.fragmentDemo;

import androidx.fragment.app.Fragment;

public class FragmentMainActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CourseListFragment();
    }
}
