package com.example.job_wargame2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Fragment_List extends Fragment {

    private Button[] topTenButtons = new Button[10];
    private TopTen topTenPlayers;

    private CallBack_Top callBack_top;

    public void setCallBack_top(CallBack_Top _callBack_top) {
        this.callBack_top = _callBack_top;
    }

    private void findviews(View view) {
        topTenButtons[0]=view.findViewById(R.id.main_topTen_1);
        topTenButtons[1]=view.findViewById(R.id.main_topTen_2);
        topTenButtons[2]=view.findViewById(R.id.main_topTen_3);
        topTenButtons[3]=view.findViewById(R.id.main_topTen_4);
        topTenButtons[4]=view.findViewById(R.id.main_topTen_5);
        topTenButtons[5]=view.findViewById(R.id.main_topTen_6);
        topTenButtons[6]=view.findViewById(R.id.main_topTen_7);
        topTenButtons[7]=view.findViewById(R.id.main_topTen_8);
        topTenButtons[8]=view.findViewById(R.id.main_topTen_9);
        topTenButtons[9]=view.findViewById(R.id.main_topTen_10);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_list,container,false);
        findviews(view);
        initViews(view);
        return view;
    }

    private void initViews(View v) {
        topTenButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(0).getName());
                }
            }
        });
        topTenButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(1).getName());
                }
            }
        });
        topTenButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(2).getName());
                }
            }
        });
        topTenButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(3).getName());
                }
            }
        });
        topTenButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(4).getName());
                }
            }
        });
        topTenButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(5).getName());
                }
            }
        });
        topTenButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(6).getName());
                }
            }
        });
        topTenButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(7).getName());
                }
            }
        });
        topTenButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(8).getName());
                }
            }
        });
        topTenButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(topTenPlayers.getTopPlayers().get(9).getName());
                }
            }
        });
    }

    public void setNamesToButtons() {
          topTenButtons[0].setText(topTenPlayers.getTopPlayers().get(0).toString());
          topTenButtons[1].setText(topTenPlayers.getTopPlayers().get(1).toString());
        topTenButtons[2].setText(topTenPlayers.getTopPlayers().get(2).toString());
        topTenButtons[3].setText(topTenPlayers.getTopPlayers().get(3).toString());
        topTenButtons[4].setText(topTenPlayers.getTopPlayers().get(4).toString());
        topTenButtons[5].setText(topTenPlayers.getTopPlayers().get(5).toString());
        topTenButtons[6].setText(topTenPlayers.getTopPlayers().get(6).toString());
        topTenButtons[7].setText(topTenPlayers.getTopPlayers().get(7).toString());
        topTenButtons[8].setText(topTenPlayers.getTopPlayers().get(8).toString());
        topTenButtons[9].setText(topTenPlayers.getTopPlayers().get(9).toString());
    }

    public void setTopTenPlayers(TopTen topTen){
        topTenPlayers = topTen;
    }
}
