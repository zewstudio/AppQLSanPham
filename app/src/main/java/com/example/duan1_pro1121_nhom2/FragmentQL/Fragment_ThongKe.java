package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1_pro1121_nhom2.AdapteData.MyPagerAdapter_;
import com.example.duan1_pro1121_nhom2.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_ThongKe extends Fragment {
    MyPagerAdapter_ myPagerAdapter;
    ViewPager2 viewPager2;
    int MANV;

    public Fragment_ThongKe(int MANV_) {
        this.MANV = MANV_;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV_) {
        this.MANV = MANV_;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_ = inflater.inflate(R.layout.layout_fragment_thongke,container,false);
        return view_;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myPagerAdapter = new MyPagerAdapter_(this,getMANV());
        viewPager2 = view.findViewById(R.id.viewPager_);
        viewPager2.setAdapter(myPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout_);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2,true,true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position)
                {
                    case 0:

                        tab.setText("Doanh Thu");
                        break;
                    case 1:
                        tab.setText("Top 10 Sản Phẩm");
                        break;
                }
            }
        });
        mediator.attach();
    }
}