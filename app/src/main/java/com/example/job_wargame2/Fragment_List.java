package com.example.job_wargame2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_List extends Fragment {


    private ArrayList<Player> topTenPlayers;
    private ListView fragment_List_view_top10;

    private CallBack_Top callBack_top;

    public void setCallBack_top(CallBack_Top _callBack_top) {
        this.callBack_top = _callBack_top;
    }

    private void findViews(View view) {
        fragment_List_view_top10=view.findViewById(R.id.fragment_List_view_top10);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_list,container,false);
        findViews(view);
        initViews(view);
        return view;
    }


    public void initViews(View v) {

        List<Map<String,String>> data=new ArrayList<>();
        int topTenSize=topTenPlayers.size()-1;
        for (int i = topTenSize; i >=0 ; i--) {
            Map<String,String> record=new HashMap<>();
            record.put("name",topTenPlayers.get(i).getName());
            if(topTenPlayers.get(i).getScore()==-1){
                record.put("score","");
            }
            else {
                record.put("score", topTenPlayers.get(i).getScore()+"");
            }
            data.add(record);
        }
        SimpleAdapter adapter= new SimpleAdapter(v.getContext(),data,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "score"},
                new int[]{android.R.id.text1,android.R.id.text2});
        fragment_List_view_top10.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (callBack_top!=null){
                    callBack_top.displayLocation(topTenPlayers.get(topTenSize-position).getLatitude(),
                            topTenPlayers.get(topTenSize-position).getLongitude());
                }
            }
        });
        fragment_List_view_top10.setAdapter(adapter);
    }


    public void setTopTenPlayers(TopTen topTen){
        topTenPlayers = topTen.getTopPlayers();
    }
}
