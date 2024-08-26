package com.ch.fo.fragmentDemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.ch.co.R;
import java.util.List;

public class CoursePageActivity extends FragmentActivity {
    public static final String EXTRA_COURSE_ID =  "com.ch.co.fragmentDemo.intent.course_date";
    private ViewPager mViewPager;
    private List<Course> courseList;

    public static Intent newIntent(Context packageContext, String courseId) {
        Intent intent = new Intent(packageContext, CoursePageActivity.class);
        intent.putExtra(EXTRA_COURSE_ID, courseId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        String courseId = (String) getIntent().getSerializableExtra(EXTRA_COURSE_ID);
        courseList = CourseCamp.get(this).getCourseList();
        mViewPager = findViewById(R.id.activity_course_page_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Course course = courseList.get(position);
                return CourseFragment.newInstance(course.getCourseId());
            }

            @Override
            public int getCount() {
                return courseList.size();
            }
        });
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseId().equals(courseId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
