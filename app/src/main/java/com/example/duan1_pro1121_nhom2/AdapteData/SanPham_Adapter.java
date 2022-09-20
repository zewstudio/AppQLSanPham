package com.example.duan1_pro1121_nhom2.AdapteData;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.ClassProduct.SanPham;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;
import com.example.duan1_pro1121_nhom2.SelectTable.AdapterSelect_LSP;

import java.util.ArrayList;
import java.util.Calendar;

public class SanPham_Adapter extends BaseAdapter {
    ArrayList<SanPham> sanPhams;

    public SanPham_Adapter(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sanpham,parent,false);
        TextView txtRowTenSP,txtRowSLSP,txtRowTrangThai;
        ImageView imgRowSP;
        txtRowTenSP = view.findViewById(R.id.txtRowNameSP);
        txtRowSLSP = view.findViewById(R.id.txtRowSoluongSP);
        imgRowSP = view.findViewById(R.id.imgRowSanPham);
        txtRowTrangThai = view.findViewById(R.id.txtRowTrangThaiSP);
        SanPham sanPham = sanPhams.get(position);
        String TENSP = sanPham.getNAMESP();
        int SLSP_ = sanPham.getSOLUONGNHAP();
        int IMG = sanPham.getIMAGE();
        txtRowTenSP.setText(TENSP);
        txtRowSLSP.setText(SLSP_+"");
        imgRowSP.setImageResource(IMG);
        if (SLSP_==0)
        {
            txtRowTrangThai.setText("Hết Hàng");
            txtRowTrangThai.setTextColor(Color.parseColor("#ED2213"));
        }
        if(SLSP_>0)
        {
            txtRowTrangThai.setText("Còn Hàng");
            txtRowTrangThai.setTextColor(Color.parseColor("#4CAF50"));
        }
        LinearLayout linearLayout = view.findViewById(R.id.linear_SanPham);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(parent.getContext());
                View showSP = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_sp,parent,false);
                builder.setView(showSP);
                Dialog dialog= builder.create();
                dialog.show();
                SanPham sanPham1 = sanPhams.get(position);
                TextView tvNgayNhap,tvMaLoai,tvSLB;
                EditText edTenSP_show,edGiaNhap_show,edSLN_show;
                ImageView imgSP_Show;
                Button btnSua,btnXoa,btnHuyShow;


                edTenSP_show = showSP.findViewById(R.id.ed_TenSP_Show);
                edGiaNhap_show = showSP.findViewById(R.id.ed_GiaNhap_Show);
                tvNgayNhap = showSP.findViewById(R.id.tvNgayNhap);
                edSLN_show = showSP.findViewById(R.id.ed_SLN_Show);
                tvSLB = showSP.findViewById(R.id.tvSLB_show);
                tvMaLoai =showSP.findViewById(R.id.tvMaLoai);
                imgSP_Show = showSP.findViewById(R.id.imgSP_Show);

                btnSua = showSP.findViewById(R.id.btnSuaSP);
                btnXoa = showSP.findViewById(R.id.btnXoaSP);
                btnHuyShow = showSP.findViewById(R.id.btnHuyShowSP);

                edTenSP_show.setText(sanPham1.getNAMESP());
                edGiaNhap_show.setText(""+sanPham1.getGIANHAP());
                tvNgayNhap.setText(""+sanPham1.getNGAYNHAP());
                edSLN_show.setText(""+sanPham1.getSOLUONGNHAP());
                tvSLB.setText(""+sanPham1.getSOLUONGDABAN());
                tvMaLoai.setText(""+sanPham1.getMALOAISP());
                imgSP_Show.setImageResource(IMG);
                tvNgayNhap.setOnClickListener(new View.OnClickListener() {
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

                                        tvNgayNhap.setText(nam + "-" + (thang + 1) + "-" + ngay);
                                    }
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE)
                        );

                        dialog.show();
                    }
                });

                tvMaLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        View view1 = LayoutInflater.from(v.getContext()).inflate(R.layout.select_table_maloai,parent,false);
                        builder1.setView(view1);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        SQLife sqLife = new SQLife(view1.getContext());
                        ArrayList<LoaiSP> loaiSPS = sqLife.getALL_LSP();
                        AdapterSelect_LSP adapterSelect_lsp = new AdapterSelect_LSP(loaiSPS);
                        ListView lvShowLoaiSP = view1.findViewById(R.id.lvShowSelectLSP);
                        lvShowLoaiSP.setAdapter(adapterSelect_lsp);
                        lvShowLoaiSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                tvMaLoai.setText(loaiSPS.get(position).getMaLoai()+"");
                                dialog1.dismiss();
                            }
                        });
                    }
                });

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try
                        {
                            int MASP1 = sanPhams.get(position).getMASP();
                            String TENSP1 = edTenSP_show.getText().toString();
                            int GIANHAP1 = Integer.parseInt(edGiaNhap_show.getText().toString());
                            String NGAYNHAP1 = tvNgayNhap.getText().toString();
                            int SLN1 = Integer.parseInt(edSLN_show.getText().toString());
                            int MALOAI1 = Integer.parseInt(tvMaLoai.getText().toString());
                            int SLB1 = Integer.parseInt(tvSLB.getText().toString());
                            if (TENSP1.length()==0){
                                Toast.makeText(v.getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (NGAYNHAP1.equals("0000-00-00")==true)
                            {
                                Toast.makeText(v.getContext(), "Vui Lòng Chọn Ngày", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            SanPham sanPham1 = new SanPham(MASP1,TENSP1,GIANHAP1,NGAYNHAP1,SLN1,SLB1,MALOAI1,IMG);
                            SQLife sqLife = new SQLife(v.getContext());
                            sqLife.UpdateSP(sanPham1);
                            sanPhams.clear();
                            sanPhams.addAll(sqLife.getALLSP());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e)
                        {
                            Toast.makeText(v.getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                            return;
                        }



                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLife sqLife = new SQLife(v.getContext());
                        int MASP1 = sanPhams.get(position).getMASP();
                        sqLife.DeleteSP(MASP1+"");
                        sanPhams.clear();
                        sanPhams.addAll(sqLife.getALLSP());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnHuyShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return sanPhams.size();
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
