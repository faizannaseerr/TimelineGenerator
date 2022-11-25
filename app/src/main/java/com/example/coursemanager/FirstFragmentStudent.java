package com.example.coursemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.coursemanager.databinding.FragmentFirstStudentBinding;
import com.example.coursemanager.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstFragmentStudent extends Fragment {

    private FragmentFirstStudentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstStudentBinding.inflate(inflater, container, false);
        MainActivityStudent activity = (MainActivityStudent) getActivity();
        String username = activity.getUsername();
        binding.textviewFirst.setText("Welcome, " + activity.getUsername() + "!");
        takenCoursesTable();
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragmentStudent.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.studentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    protected void takenCoursesTable(){
        DatabaseReference ref = FirebaseDatabase
                .getInstance("https://course-manager-b07-default-rtdb.firebaseio.com/")
                .getReference();
        DataSnapshot snapshot;
        TableLayout table = binding.takenTable;
        for (int i = 0; i < 3; i ++) {
            TableRow row = new TableRow(getActivity());
            TableRow.LayoutParams params =
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(params);
            row.setId(1000+i);
            TextView course = new TextView(getActivity());
            course.setId(2000+i);
            row.addView(course);
            Button edit = new Button(getActivity());
            edit.setId(3000+i);
            row.addView(edit);
            course.setText("Hello" + i);
            edit.setText("Edit");
            table.addView(row, i);
        }
    }

}