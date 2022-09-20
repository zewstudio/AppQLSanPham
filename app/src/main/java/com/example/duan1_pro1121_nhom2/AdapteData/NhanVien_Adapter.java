package com.example.duan1_pro1121_nhom2.AdapteData;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.MainActivity;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class NhanVien_Adapter extends BaseAdapter {
    ArrayList<NhanVien> nhanViens;
    NhanVien nhanVien1;
    public NhanVien_Adapter(ArrayList<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nv,parent,false);
        TextView txtRowTenNV,txtRowGioiTinh,txtRowVaiTro;
        CircleImageView circleImageView ;

        txtRowTenNV = view.findViewById(R.id.txtRowTenNV);
        txtRowGioiTinh = view.findViewById(R.id.txtRowGioiTinhNV);
        txtRowVaiTro = view.findViewById(R.id.txtRowVaiTroNV);
        circleImageView = view.findViewById(R.id.RowImageNV);
        NhanVien nhanVien = nhanViens.get(position);
        int MANV = nhanVien.getMaNV();
        String TENNV = nhanVien.getTenNV();
        String NumberPhone = nhanVien.getNumberPhone();
        String DiaChi = nhanVien.getDiaChi();
        String GioiTinh = nhanVien.getGioiTinh();
        String NgaySinh = nhanVien.getNgaySinh();
        String UserName = nhanVien.getUserName();
        String PassWord = nhanVien.getPassWord();
        String VaiTro = nhanVien.getVaiTro();
        int IMGNV = nhanVien.getImageNV();
        txtRowTenNV.setText(""+TENNV);
        txtRowGioiTinh.setText(""+GioiTinh);
        txtRowVaiTro.setText(""+VaiTro);
        circleImageView.setImageResource(IMGNV);
        LinearLayout linearLayout = view.findViewById(R.id.linear_rownv);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(parent.getContext());
                View showNV = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_nv,parent,false);
                builder.setView(showNV);
                Dialog dialog= builder.create();
                dialog.show();
                TextView txtNgaySinh;
                EditText edTenNV_show,edDiaChiNV_show,edNumberPhoneNV_show;
                Button btnSua,btnXoa,btnHuy;
                RadioButton rdNam,rdNu;
                txtNgaySinh = showNV.findViewById(R.id.txtNS_NV_Show);
                edTenNV_show = showNV.findViewById(R.id.edHT_NV_Show);
                edDiaChiNV_show = showNV.findViewById(R.id.edDC_NV_Show);
                edNumberPhoneNV_show = showNV.findViewById(R.id.edSDT_NV_Show);
                btnSua = showNV.findViewById(R.id.btnSuaNV);
                btnXoa = showNV.findViewById(R.id.btnXoaNV);
                btnHuy = showNV.findViewById(R.id.btnHuyShowNV);
                rdNam = showNV.findViewById(R.id.rdGT_NV_Nam_Show);
                rdNu = showNV.findViewById(R.id.rdGT_NV_Nu_Show);
                edTenNV_show.setText(TENNV);
                edDiaChiNV_show.setText(DiaChi);
                edNumberPhoneNV_show.setText(NumberPhone+"");
                if ((txtRowGioiTinh.getText().toString()).equals("Nam")==true)
                {
                    rdNam.setChecked(true);
                }
                if ((txtRowGioiTinh.getText().toString()).equals("Nữ")==true)
                {
                    rdNu.setChecked(true);
                }
                txtNgaySinh.setText(NgaySinh);
                txtNgaySinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis( System.currentTimeMillis() );


                        DatePickerDialog dialog = new DatePickerDialog(v.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                                        int nam = i;
                                        int thang = i1;
                                        int ngay = i2;

                                        txtNgaySinh.setText(nam + "-" + (thang + 1) + "-" + ngay);
                                    }
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );

                        dialog.show();
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MANV1 = MANV;
                        String TENNV1 = edTenNV_show.getText().toString();
                        String NumberPhone1 = edNumberPhoneNV_show.getText().toString();
                        String DiaChi1 = edDiaChiNV_show.getText().toString();
                        String NgaySinh1 = txtNgaySinh.getText().toString();
                        String GioiTinh1 = txtRowGioiTinh.getText().toString();
                        if (TENNV1.length()==0||NumberPhone1.length()==0||DiaChi1.length()==0)
                        {
                            Toast.makeText(v.getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(rdNam.isChecked()==true)
                        {
                            GioiTinh1 ="Nam";
                            Toast.makeText(v.getContext(), ""+GioiTinh1, Toast.LENGTH_SHORT).show();
                        }
                        if (rdNu.isChecked()==true)
                        {
                            GioiTinh1 ="Nữ";
                            Toast.makeText(v.getContext(), ""+GioiTinh1, Toast.LENGTH_SHORT).show();
                        }
                        nhanVien1 = new NhanVien(MANV1,UserName,PassWord,TENNV1,NumberPhone1,DiaChi1,NgaySinh1,GioiTinh1,VaiTro,IMGNV);
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.UpdateNV(nhanVien1);
                        nhanViens.clear();
                        nhanViens.addAll(sqLife.getALLNV());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MANV1 = MANV;
                        if (VaiTro.equals("Quản Trị")==true)
                        {

                            SQLife sqLife = new SQLife(v.getContext());
                            sqLife.DeleteNV(MANV1+"");
                            nhanViens.clear();
                            nhanViens.addAll(sqLife.getALLNV());
                            if (nhanViens.size()==0)
                            {
                                nhanViens.clear();
                                nhanViens.addAll(sqLife.getALLNV());
                                notifyDataSetChanged();



                            }
                            if (nhanViens.size()>0)
                            {
                                nhanVien1 = nhanViens.get(0);
                                nhanVien1.setVaiTro("Quản Trị");
                                sqLife.UpdateNV(nhanVien1);
                                nhanViens.clear();
                                nhanViens.addAll(sqLife.getALLNV());
                                notifyDataSetChanged();
                            }
                            dialog.dismiss();
                        }

                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.DeleteNV(MANV1+"");
                        nhanViens.clear();
                        nhanViens.addAll(sqLife.getALLNV());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return nhanViens.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void checkVaiTro(NhanVien nhanVien)
    {

    }

}
