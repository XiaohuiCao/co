package com.ch.fo.fragmentDemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ch.co.R;
import java.util.List;

public class CourseListFragment extends Fragment {
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container,false);
        recyclerView = view.findViewById(R.id.course_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CourseCamp courseCamp = CourseCamp.get(getActivity());
        List<Course> courseList = courseCamp.getCourseList();
        if (courseAdapter == null) {
            courseAdapter = new CourseAdapter(courseList, getActivity());
            recyclerView.setAdapter(courseAdapter);
        } else {
            courseAdapter.notifyDataSetChanged();
        }
    }
}
