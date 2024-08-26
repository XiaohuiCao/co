package com.ch.fo.fragmentDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.ch.co.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseFragment extends Fragment {
    private static final String COURSE_ID = "course_id";
    private static final String DIALOG_DATE = "dialogDate";
    private static final String EXTRA_DATE = "com.ch.co.fragmentDemo.intent.date";
    private static final int REQUEST_DATE = 0;

    private Course course;
    private EditText cTitle;
    private Button dateButton;
    private CheckBox checkBox;
    private Date mDate = null;

    public static CourseFragment newInstance(String courseId) {
        Bundle args = new Bundle();
        args.putSerializable(COURSE_ID, courseId);
        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String courseId = (String) getArguments().getSerializable(COURSE_ID);
        course = CourseCamp.get(getActivity()).getCourse(courseId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container,false);
        cTitle = view.findViewById(R.id.course_title);
        cTitle.setText(course.getCourseTitle());
        cTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                course.setCourseTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        dateButton = view.findViewById(R.id.crime_date);
        updateDate();
        dateButton.setOnClickListener(view1 -> {
            FragmentManager fragmentManager = getFragmentManager();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            String establishedDateStr = course.getEstablishedDate();
            try {
                mDate = sdf.parse(establishedDateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            DatePickerFragment dialog = DatePickerFragment.newInstance(mDate);
            dialog.setTargetFragment(this, REQUEST_DATE);
            dialog.show(fragmentManager, DIALOG_DATE);
        });


        checkBox = view.findViewById(R.id.course_complete);
        checkBox.setChecked(course.isComplete());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                course.setComplete(b);
            }
        });
        return view;
    }

    private void updateDate() {
        dateButton.setText(course.getEstablishedDate());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (resultCode == REQUEST_DATE) {
            Date eDate = (Date) intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Bundle bundle = new Bundle();
            Date newDate = (Date) bundle.getSerializable(EXTRA_DATE);
            String eDateStr = sdf.format(newDate);
            course.setEstablishedDate(eDateStr);
            updateDate();
        }
    }
}
