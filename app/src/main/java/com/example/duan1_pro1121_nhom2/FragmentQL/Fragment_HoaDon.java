package com.example.duan1_pro1121_nhom2.FragmentQL;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duan1_pro1121_nhom2.AdapteData.HoaDon_Adapter;
import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDon;
import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDonCT;
import com.example.duan1_pro1121_nhom2.ClassProduct.KhachHang;
import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.ClassProduct.SanPham;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.LoginAccount.StepDangKi;
import com.example.duan1_pro1121_nhom2.R;
import com.example.duan1_pro1121_nhom2.SelectTable.AdapterSelect_KH;
import com.example.duan1_pro1121_nhom2.SelectTable.AdapterSelect_LSP;
import com.example.duan1_pro1121_nhom2.SelectTable.AdapterSelect_SP;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_HoaDon extends Fragment {
    int MANV;
    Context contextm;
    ArrayList<SanPham> sanPhams;
    public Fragment_HoaDon(int MANV) {
        this.MANV = MANV;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    SanPham sanPham_;
    KhachHang khachHang_;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_fragment_hd,container,false);
        contextm = container.getContext();
        Button btnAdd;
        ListView listView = view.findViewById(R.id.lvHD);
        btnAdd = view.findViewById(R.id.btnAddHD);
        SQLife sqLife = new SQLife(container.getContext());
        ArrayList<HoaDon> hoaDons = sqLife.getALLHoaDon(getMANV()+"");
        HoaDon_Adapter hoaDon_adapter = new HoaDon_Adapter(hoaDons,getMANV());
        listView.setAdapter(hoaDon_adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View viewShow = inflater.inflate(R.layout.show_add_hd,container,false);
                builder.setView(viewShow);
                Dialog dialog = builder.create();
                dialog.show();
                SQLife sqLife = new SQLife(viewShow.getContext());
                sanPhams = sqLife.getALLSP();
                TextView tvTENKH,tvNgayMua,tvTENSP,tvTONGTIENHANG,tvTrangThai;
                Button btnSave;
                tvTENKH = viewShow.findViewById(R.id.tvHD_TENKH_Add);
                tvNgayMua = viewShow.findViewById(R.id.tvHD_NGAYMUA_Add);
                tvTENSP = viewShow.findViewById(R.id.tvHD_TENSP_Add);
                tvTONGTIENHANG = viewShow.findViewById(R.id.tvHD_TONGTIEN_Add);
                tvTrangThai = viewShow.findViewById(R.id.tvHD_TRANGTHAI_Add);
                btnSave = viewShow.findViewById(R.id.btnHD_Save);
                tvTrangThai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String TT = tvTrangThai.getText().toString();

                        if(TT.equals("Chưa Thanh Toán")==true)
                        {

                            tvTrangThai.setText("Đã Thanh Toán");
                            Toast.makeText(v.getContext(), "Đã Thay Đổi", Toast.LENGTH_SHORT).show();
                        }
                        if(TT.equals("Đã Thanh Toán")==true)
                        {
                            tvTrangThai.setText("Chưa Thanh Toán");
                            Toast.makeText(v.getContext(), "Đã Thay Đổi", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                tvNgayMua.setOnClickListener(new View.OnClickListener() {
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

                                        tvNgayMua.setText(nam + "-" + (thang + 1) + "-" + ngay);
                                    }
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );

                        dialog.show();
                    }
                });
                tvTENSP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        View view1 = LayoutInflater.from(v.getContext()).inflate(R.layout.select_table_sp,container,false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        AdapterSelect_SP adapterSelect_sp = new AdapterSelect_SP(sanPhams);
                        ListView lvShowSP = view1.findViewById(R.id.lvShowSelectSP);
                        EditText editText = view1.findViewById(R.id.edSL_Select);

                        lvShowSP.setAdapter(adapterSelect_sp);
                        lvShowSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                try {

                                    int SoLuongNhap = Integer.parseInt(editText.getText().toString());
                                    if (SoLuongNhap<0)
                                    {
                                        Toast.makeText(view.getContext(), "Số Lượng Phải >= 0", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    if (SoLuongNhap>(sanPhams.get(position).getSOLUONGNHAP()))
                                    {
                                        Toast.makeText(view.getContext(), "Số Lượng Sản Phẩm Không Đủ", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    sanPham_ = sanPhams.get(position);
                                    String TENSP_ = sanPhams.get(position).getNAMESP() + "("+SoLuongNhap+")";
                                    String TENSPs = tvTENSP.getText().toString();
                                    TENSPs += " + "+TENSP_;
                                    tvTENSP.setText(TENSPs);

                                    int TongTien = SoLuongNhap*(sanPhams.get(position).getGIANHAP());

                                    int TongTienTienHang_ = Integer.parseInt(tvTONGTIENHANG.getText().toString());
                                    TongTienTienHang_ += TongTien;
                                    tvTONGTIENHANG.setText(TongTienTienHang_+"");
                                    sanPhams.get(position).setSOLUONGNHAP((sanPhams.get(position).getSOLUONGNHAP()-SoLuongNhap));
                                    sanPhams.get(position).setSOLUONGDABAN(SoLuongNhap);

                                    dialog1.dismiss();
                                }catch (Exception e)
                                {
                                    Toast.makeText(view.getContext(), "Số Lượng Phải là Số", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                });
                tvTENKH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        View view1 = LayoutInflater.from(v.getContext()).inflate(R.layout.select_table_kh,container,false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        SQLife sqLife = new SQLife(view1.getContext());
                        ArrayList<KhachHang> khachHangs = sqLife.getALLKH();
                        AdapterSelect_KH adapterSelect_kh = new AdapterSelect_KH(khachHangs);
                        ListView lvShowKH = view1.findViewById(R.id.lvShowSelectKH);
                        lvShowKH.setAdapter(adapterSelect_kh);
                        lvShowKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                tvTENKH.setText(khachHangs.get(position).getTenKH());
                                khachHang_ = khachHangs.get(position);
                                dialog1.dismiss();
                            }
                        });

                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MAKH1 = khachHang_.getMaKH();
                        String TENKH1 = tvTENKH.getText().toString();
                        String NGAYMUA1 = tvNgayMua.getText().toString();
                        String MASP1 = sanPham_.getMASP()+"";
                        String TENSP1 = tvTENSP.getText().toString();
                        int TONGTIEN1 = Integer.parseInt(tvTONGTIENHANG.getText().toString());
                        String TrangThai = tvTrangThai.getText().toString();
                        HoaDon hoaDon = new HoaDon(0,MAKH1,NGAYMUA1,MASP1,TENSP1,TONGTIEN1,TrangThai,getMANV());
                        SQLife sqLife1 = new SQLife(v.getContext());
                        sqLife1.AddHoaDon(hoaDon);
                        ArrayList<HoaDon> hoaDons = sqLife.getALLHoaDon(getMANV()+"");
                        KhachHang khachHang = sqLife.getOneKH(MAKH1+"");
                        hoaDon = hoaDons.get(hoaDons.size()-1);
                        if (NGAYMUA1.equals("0000-00-00")==true)
                        {
                            Toast.makeText(v.getContext(), "Vui Lòng Chọn Ngày Mua", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TrangThai.equals("Đã Thanh Toán")==true)
                        {
                            HoaDonCT hoaDonCT = new HoaDonCT(hoaDon.getMaHD(),hoaDon.getMaSP(),hoaDon.getTenSP(),hoaDon.getMaKH(),khachHang.getTenKH(),khachHang.getNumberPhone(),TONGTIEN1,TONGTIEN1,NGAYMUA1,getMANV());
                            sqLife1.AddHoaDonCT(hoaDonCT);
                            EditSanPham();
                        }
                        if (TrangThai.equals("Chưa Thanh Toán")==true)
                        {
                            HoaDonCT hoaDonCT = new HoaDonCT(hoaDon.getMaHD(),hoaDon.getMaSP(),hoaDon.getTenSP(),hoaDon.getMaKH(),khachHang.getTenKH(),khachHang.getNumberPhone(),TONGTIEN1,0,NGAYMUA1,getMANV());
                            sqLife1.AddHoaDonCT(hoaDonCT);
                            EditSanPham();
                        }
                        SQLife sqLife2 = new SQLife(v.getContext());
                        HoaDon_Adapter hoaDon_adapter1 = new HoaDon_Adapter(sqLife2.getALLHoaDon(getMANV()+""),getMANV());
                        listView.setAdapter(hoaDon_adapter1);
                        hoaDon_adapter1.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;
    }
    public void EditSanPham()
    {
        SQLife sqLife = new SQLife(contextm);
        for (int i=0;i<sanPhams.size();i++)
        {
            sqLife.UpdateSP(sanPhams.get(i));
        }
    }
}
