package com.example.taskmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link taskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class taskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "taskTitle";
    private static final String ARG_PARAM2 = "taskBody";
    private static final String ARG_PARAM3 = "taskState";
    private static final String ARG_PARAM4 = "taskTeam";

    // TODO: Rename and change types of parameters
    private String mTaskTitle;
    private String mTaskBody;
    private String mTaskState;
    private String mTaskTeam;

    public taskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mTaskTitle Parameter 1.
     * @param mTaskBody Parameter 2.
     * @param mTaskState Parameter 3.
     * @param mTaskTeam Parameter 4.
     * @return A new instance of fragment taskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static taskFragment newInstance(String mTaskTitle, String mTaskBody,String mTaskState,String mTaskTeam) {
        taskFragment fragment = new taskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mTaskTitle);
        args.putString(ARG_PARAM2, mTaskBody);
        args.putString(ARG_PARAM3, mTaskState);
        args.putString(ARG_PARAM4, mTaskTeam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskTitle = getArguments().getString(ARG_PARAM1);
            mTaskBody = getArguments().getString(ARG_PARAM2);
            mTaskState = getArguments().getString(ARG_PARAM3);
            mTaskTeam = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }
}