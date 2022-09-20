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

import com.example.duan1_pro1121_nhom2.ClassProduct.KhachHang;
import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class KhachHang_Adapter extends BaseAdapter {
    ArrayList<KhachHang> khachHangs;
    KhachHang khachHang1;
    public KhachHang_Adapter(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kh,parent,false);
        TextView txtRowMaKH,txtRowTenKH,txtRowGioiTinh,txtRowPhoneKH;
        CircleImageView circleImageView ;
        txtRowTenKH = view.findViewById(R.id.txtRowTenKH);
        txtRowGioiTinh = view.findViewById(R.id.txtRowGioiTinhKH);
        txtRowPhoneKH = view.findViewById(R.id.txtRowPhoneKH);
        circleImageView = view.findViewById(R.id.RowImageKH);
        KhachHang khachHang = khachHangs.get(position);
        int MAKH = khachHang.getMaKH();
        String TENKH = khachHang.getTenKH();
        String NumberPhone = khachHang.getNumberPhone();
        String DiaChi = khachHang.getDiaChi();
        String GioiTinh = khachHang.getGioiTinh();
        String NgaySinh = khachHang.getNgaySinh();
        int IMGKH = khachHang.getImageKH();
        txtRowTenKH.setText(""+TENKH);
        txtRowGioiTinh.setText(""+GioiTinh);
        txtRowPhoneKH.setText(""+NumberPhone);
        circleImageView.setImageResource(IMGKH);
        LinearLayout linearLayout = view.findViewById(R.id.linear_rowkh);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(parent.getContext());
                View showKH = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_kh,parent,false);
                builder.setView(showKH);
                Dialog dialog= builder.create();
                dialog.show();
                TextView txtNgaySinh;
                EditText edTenKH_show,edDiaChiKH_show,edNumberPhoneKH_show;
                Button btnSua,btnXoa,btnHuy;
                RadioButton rdNam,rdNu;
                txtNgaySinh = showKH.findViewById(R.id.txtNS_KH_Show);
                edTenKH_show = showKH.findViewById(R.id.edHT_KH_Show);
                edDiaChiKH_show = showKH.findViewById(R.id.edDC_KH_Show);
                edNumberPhoneKH_show = showKH.findViewById(R.id.edSDT_KH_Show);
                btnSua = showKH.findViewById(R.id.btnSuaKH);
                btnXoa = showKH.findViewById(R.id.btnXoaKH);
                btnHuy = showKH.findViewById(R.id.btnHuyShowKH);
                edTenKH_show.setText(TENKH);
                edDiaChiKH_show.setText(DiaChi);
                edNumberPhoneKH_show.setText(NumberPhone+"");
                txtNgaySinh.setText(NgaySinh);
                rdNam = showKH.findViewById(R.id.rdGT_KH_Nam_Show);
                rdNu = showKH.findViewById(R.id.rdGT_KH_Nu_Show);
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
                if ((txtRowGioiTinh.getText().toString()).equals("Nam")==true)
                {
                    rdNam.setChecked(true);
                    rdNu.setChecked(false);
                }
                if ((txtRowGioiTinh.getText().toString()).equals("Nữ")==true)
                {
                    rdNu.setChecked(true);
                    rdNam.setChecked(false);
                }
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MAKH1 = MAKH;
                        String TENKH1 = edTenKH_show.getText().toString();
                        String NumberPhone1 = edNumberPhoneKH_show.getText().toString();
                        String DiaChi1 = edDiaChiKH_show.getText().toString();
                        String NgaySinh1 = txtNgaySinh.getText().toString();
                        String GioiTinh1 = txtRowGioiTinh.getText().toString();
                        if (TENKH1.length()==0||NumberPhone1.length()==0||DiaChi1.length()==0)
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
                        khachHang1 = new KhachHang(MAKH1,TENKH1,NumberPhone1,DiaChi1,NgaySinh1,GioiTinh1,IMGKH);
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.UpdateKH(khachHang1);
                        khachHangs.clear();
                        khachHangs.addAll(sqLife.getALLKH());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MAKH1 = MAKH;
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.DeleteKH(MAKH1+"");
                        khachHangs.clear();
                        khachHangs.addAll(sqLife.getALLKH());
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
        return khachHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
