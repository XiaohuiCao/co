package com.ch.fo.fragmentDemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ch.co.R;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    private List<Course> mCourseList;
    private static Context mContext;

    public CourseAdapter(List<Course> courseList, Context context) {
        mCourseList = courseList;
        mContext = context;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_item_course, parent, false);
        CourseHolder courseHolder = new CourseHolder(view);
        return courseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        Course course =mCourseList.get(position);
        holder.bindCourse(course);
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    static class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cTitle;
        private TextView eDate;
        private CheckBox mCheck;
        private Course mCourse;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cTitle = itemView.findViewById(R.id.list_item_title);
            eDate = itemView.findViewById(R.id.list_item_date);
            mCheck = itemView.findViewById(R.id.list_item_check_box);
        }

        public void bindCourse(Course course) {
            mCourse = course;
            cTitle.setText(mCourse.getCourseTitle());
            eDate.setText("课程立项时间" + mCourse.getCourseDate());
            mCheck.setChecked(mCourse.isComplete());
        }

        @Override
        public void onClick(View view) {
            Intent intent = CoursePageActivity.newIntent(mContext, mCourse.getCourseId());
            mContext.startActivity(intent);
        }
    }
}