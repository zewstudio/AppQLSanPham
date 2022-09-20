package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duan1_pro1121_nhom2.AdapteData.KhachHang_Adapter;

import com.example.duan1_pro1121_nhom2.ClassProduct.KhachHang;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.LoginAccount.StepDangKi;
import com.example.duan1_pro1121_nhom2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_KhachHang extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_kh,container,false);
        Button btnADD;
        btnADD = view.findViewById(R.id.btnAddKH);
        ListView listView = view.findViewById(R.id.lvKH);
        SQLife sqLife = new SQLife(view.getContext());
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        khachHangs.addAll(sqLife.getALLKH());
        KhachHang_Adapter khachHang_adapter = new KhachHang_Adapter(khachHangs);
        listView.setAdapter(khachHang_adapter);

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view1 = inflater.inflate(R.layout.show_add_kh,container,false);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                EditText edHoTen,edSDT,edDiaChi;
                TextView tvNgaySinh;
                Button btnSave;
                RadioButton rdNam,rdNu;
                edHoTen = view1.findViewById(R.id.edHT_KH_Add);
                edSDT = view1.findViewById(R.id.edSDT_KH_Add);
                edDiaChi = view1.findViewById(R.id.edDC_KH_Add);
                tvNgaySinh = view1.findViewById(R.id.txtNS_KH_Add);
                btnSave = view1.findViewById(R.id.btnAddKH_);
                rdNam = view1.findViewById(R.id.rdGT_KH_Nam_Add);
                rdNu = view1.findViewById(R.id.rdGT_KH_Nu_Add);
                tvNgaySinh.setOnClickListener(new View.OnClickListener() {
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

                                        tvNgaySinh.setText(nam + "-" + (thang + 1) + "-" + ngay);
                                    }
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );

                        dialog.show();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            String TENKH = edHoTen.getText().toString();
                            String SDT = edSDT.getText().toString();
                            String DiaChi = edDiaChi.getText().toString();
                            String NgaySinh = tvNgaySinh.getText().toString();
                            String GioiTinh = "Nam";
                            if (TENKH.length()==0||SDT.length()==0||DiaChi.length()==0)
                            {
                                Toast.makeText(v.getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (NgaySinh.equals("0000-00-00")==true||NgaySinh.equals("")==true)
                            {
                                Toast.makeText(v.getContext(), "Vui Lòng Chọn Ngày Sinh", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(rdNam.isChecked()==true)
                            {
                                GioiTinh ="Nam";
                            }
                            if (rdNu.isChecked()==true)
                            {
                                GioiTinh ="Nữ";
                            }
                            int IMGKH = R.drawable.ic_person;
                            KhachHang khachHang = new KhachHang(0,TENKH,SDT,DiaChi,NgaySinh,GioiTinh,IMGKH);
                            sqLife.AddKhachhang(khachHang);
                            khachHangs.clear();
                            khachHangs.addAll(sqLife.getALLKH());
                            KhachHang_Adapter khachHang_adapter_ = new KhachHang_Adapter(khachHangs);
                            listView.setAdapter(khachHang_adapter_);
                            khachHang_adapter_.notifyDataSetChanged();
                            dialog.dismiss();

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        return view;
    }
}