package com.example.job_wargame2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment_Map extends Fragment {
    private TextView map_LBL_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_map,container,false);
        findviews(view);
        initViews();
        return view;
    }

    private void findviews(View view) {
        map_LBL_name= view.findViewById(R.id.map_LBL_name);
    }

    private void initViews() {


    }
    public void showLocation(String name){
        map_LBL_name.setText(name);
    }

}
