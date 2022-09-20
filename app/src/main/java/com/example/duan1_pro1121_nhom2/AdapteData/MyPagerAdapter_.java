package com.example.duan1_pro1121_nhom2.AdapteData;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_DoanhThu;
import com.example.duan1_pro1121_nhom2.FragmentQL.Fragment_Top;

public class MyPagerAdapter_ extends FragmentStateAdapter {

    public MyPagerAdapter_(@NonNull Fragment fragment,int MANV_) {
        super(fragment);
        MANV = MANV_;
    }
    int MANV;

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0:
                return new Fragment_DoanhThu(getMANV()+"");

            case 1:
                return new Fragment_Top();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
