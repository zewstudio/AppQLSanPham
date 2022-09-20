package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.LoginAccount.DangNhap;
import com.example.duan1_pro1121_nhom2.LoginAccount.StepDangKi;
import com.example.duan1_pro1121_nhom2.R;

import java.util.Calendar;

public class Fragment_TaiKhoanCaNhan extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_taikhoancanhan,container,false);
        EditText edHoTen,edSDT,edDiaChi;
        TextView txtNgaySinh;
        Button btnSua;
        RadioButton rdNam,rdNu;
        edHoTen = view.findViewById(R.id.edHT_NV_CN);
        edDiaChi = view.findViewById(R.id.edDC_NV_CN);
        edSDT = view.findViewById(R.id.edSDT_NV_CN);
        rdNam = view.findViewById(R.id.rdGT_NV_Nam_CN);
        rdNu = view.findViewById(R.id.rdGT_NV_Nu_CN);
        txtNgaySinh =view.findViewById(R.id.txtNS_NV_CN);
        btnSua = view.findViewById(R.id.btnSuaAcount);
        SQLife sqLife = new SQLife(view.getContext());
        NhanVien nhanVien = sqLife.getOneNV(getMANV()+"");
        edHoTen.setText(nhanVien.getTenNV());
        edDiaChi.setText(nhanVien.getDiaChi());
        edSDT.setText(nhanVien.getNumberPhone()+"");
        txtNgaySinh.setText(nhanVien.getNgaySinh());
        if ((nhanVien.getGioiTinh().toString()).equals("Nam"))
        {
            rdNam.setChecked(true);
        }
        if ((nhanVien.getGioiTinh().toString()).equals("Nữ"))
        {
            rdNu.setChecked(true);
        }
        txtNgaySinh.setOnClickListener(new View.OnClickListener() {
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
                                txtNgaySinh.setText(nam + "-" + (thang+1) + "-" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );

                dialog.show();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Hoten = edHoTen.getText().toString();
                String DiaChi = edDiaChi.getText().toString();
                String SDT = edSDT.getText().toString();
                String NgaySinh = txtNgaySinh.getText().toString();
                String GioiTinh = "Nam";
                if (Hoten.length()==0||DiaChi.length()==0||SDT.length()==0){
                    Toast.makeText(v.getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rdNam.isChecked()==true)
                {
                    GioiTinh = "Nam";
                }
                if (rdNu.isChecked()==true)
                {
                    GioiTinh = "Nữ";
                }

                NhanVien nhanVien1 = new NhanVien(nhanVien.getMaNV(), nhanVien.getUserName(),nhanVien.getPassWord(),Hoten,SDT,DiaChi,NgaySinh,GioiTinh,nhanVien.getVaiTro(),nhanVien.getImageNV());
                sqLife.UpdateNV(nhanVien1);
            }
        });
        return view;
    }

    int MANV;

    public Fragment_TaiKhoanCaNhan(int MANV) {
        this.MANV = MANV;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }
}
