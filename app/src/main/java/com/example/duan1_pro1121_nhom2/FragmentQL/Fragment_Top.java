package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_pro1121_nhom2.AdapteData.Top10_adapter;
import com.example.duan1_pro1121_nhom2.ClassProduct.SanPham;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;

public class Fragment_Top extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_top10,container,false);
        SQLife sqLife = new SQLife(view.getContext());
        ListView listView = view.findViewById(R.id.lvTop);
        ArrayList<SanPham> sanPhams = sqLife.getTopSP();
        Top10_adapter top10_adapter = new Top10_adapter(sanPhams);
        listView.setAdapter(top10_adapter);
        return view;
    }
}
