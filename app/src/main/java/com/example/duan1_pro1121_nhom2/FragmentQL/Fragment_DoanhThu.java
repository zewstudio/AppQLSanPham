package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDonCT;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_DoanhThu extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    String MaNhanVien;
    public Fragment_DoanhThu(String maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_doanhthu,container,false);
        TextView tvStartDate,tvEndDate,tvDoanhThu;
        Button btnStartDate,btnEndDate,btnTimKiem;
        tvStartDate = view.findViewById(R.id.tvStartDate);
        tvEndDate = view.findViewById(R.id.tvEndDate);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnTimKiem = view.findViewById(R.id.btnTimKiem);

        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis( System.currentTimeMillis() );


                DatePickerDialog dialog = new DatePickerDialog(
                        v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                int nam = i;
                                int thang = i1;
                                int ngay = i2;
                                tvStartDate.setText(nam + "-" + (thang + 1) + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );

                dialog.show();
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis( System.currentTimeMillis() );


                DatePickerDialog dialog = new DatePickerDialog(
                        v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                int nam = i;
                                int thang = i1;
                                int ngay = i2;
                                tvEndDate.setText(nam + "-" + (thang + 1) + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );

                dialog.show();
            }
        });
        tvDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis( System.currentTimeMillis() );


                DatePickerDialog dialog = new DatePickerDialog(
                        v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                int nam = i;
                                int thang = i1;
                                int ngay = i2;
                                tvEndDate.setText(nam + "-" + (thang + 1) + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );

                dialog.show();
            }
        });
        SQLife sqLife = new SQLife(view.getContext());

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String StartDate = tvStartDate.getText().toString();
                String EndDate = tvEndDate.getText().toString();
                if(StartDate.equals("0000-00-00")==true||EndDate.equals("0000-00-00")==true)
                {
                    Toast.makeText(v.getContext(), "Vui Lòng Chọn Ngày Bắt Đầu và Ngày Kết Thúc", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<HoaDonCT> hoaDonCTS = sqLife.getALLHDCT_DATE(getMaNhanVien()+"",tvStartDate.getText().toString(),tvEndDate.getText().toString());
                int DoanhThu_ = 0;
                for (int i =0;i<hoaDonCTS.size();i++)
                {
                    DoanhThu_ += hoaDonCTS.get(i).getSOTIENTHANHTOAN();
                }
                tvDoanhThu.setText("Tổng Doanh Thu: "+DoanhThu_+" VNĐ ");
            }
        });

        return view;
    }



}
