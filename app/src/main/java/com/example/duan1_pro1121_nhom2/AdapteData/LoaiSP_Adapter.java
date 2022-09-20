package com.example.duan1_pro1121_nhom2.AdapteData;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;

public class LoaiSP_Adapter extends BaseAdapter {
    ArrayList<LoaiSP> loaiSPS;

    public LoaiSP_Adapter(ArrayList<LoaiSP> loaiSPS) {
        this.loaiSPS = loaiSPS;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loaisp,parent,false);
        TextView txtRowTenLoaiSP;
        ImageView imageLSP;
        txtRowTenLoaiSP = view.findViewById(R.id.txtRowNameLoaiSP);
        imageLSP = view.findViewById(R.id.imgRowLoai);
        LoaiSP loaiSP = loaiSPS.get(position);
        int MALOAISP =loaiSP.getMaLoai();
        String TENLOAISP = loaiSP.getTenLoai();
        int IMG = loaiSP.getIMAGE_();
        txtRowTenLoaiSP.setText(TENLOAISP);
        imageLSP.setImageResource(IMG);
        LinearLayout linearLayout = view.findViewById(R.id.linear_LoaiSanPham);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(parent.getContext());
                View showSP = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_lsp,parent,false);
                builder.setView(showSP);
                Dialog dialog= builder.create();
                dialog.show();
                TextView txtMaSP_show;
                EditText edTenSP_show;
                ImageView imageLSP_show;
                Button btnSua,btnXoa;
                edTenSP_show = showSP.findViewById(R.id.ed_NameLoaiSP_Show);
                imageLSP_show = showSP.findViewById(R.id.imgMaLoai_Show);
                btnSua = showSP.findViewById(R.id.btnSuaLoaiSP);
                btnXoa = showSP.findViewById(R.id.btnXoaLoaiSP);
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MALSP1 = MALOAISP;
                        String TENLSP = edTenSP_show.getText().toString();
                        if (TENLSP.length()==0)
                        {
                            Toast.makeText(v.getContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        LoaiSP loaiSP1 = new LoaiSP(MALSP1,TENLSP,IMG);
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.UpdateLSP(loaiSP1);
                        loaiSPS.clear();
                        loaiSPS.addAll(sqLife.getALL_LSP());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int MALSP1 = MALOAISP;
                        String TENLSP = edTenSP_show.getText().toString();
                        LoaiSP loaiSP1 = new LoaiSP(MALSP1,TENLSP,IMG);
                        SQLife sqLife = new SQLife(v.getContext());
                        sqLife.DeleteLSP(loaiSP1);
                        loaiSPS.clear();
                        loaiSPS.addAll(sqLife.getALL_LSP());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                edTenSP_show.setText(TENLOAISP);
                imageLSP_show.setImageResource(IMG);
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return loaiSPS.size();
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
