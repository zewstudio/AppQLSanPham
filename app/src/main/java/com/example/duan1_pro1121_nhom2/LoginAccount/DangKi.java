package com.example.duan1_pro1121_nhom2.LoginAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.R;

import java.util.ArrayList;

public class DangKi extends AppCompatActivity {
    EditText edTaiKhoan,edMatKhau,edMatKhau2;
    Button btnNext;
    CheckBox checkBox;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        edTaiKhoan = findViewById(R.id.edAddTaiKhoan);
        edMatKhau = findViewById(R.id.edAddMatKhau);
        edMatKhau2 = findViewById(R.id.edNhapLaiMK);
        btnNext = findViewById(R.id.btnNext_DK);
        checkBox = findViewById(R.id.cbdieukhoan);
        textView = findViewById(R.id.tv_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKi.this,DangNhap.class));
                // Quay Ve man hinh dang nhap
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TaiKhoan = edTaiKhoan.getText().toString();
                String MatKhau = edMatKhau.getText().toString();
                String MatKhau2 = edMatKhau2.getText().toString();
                SQLife sqLife = new SQLife(getApplicationContext());
                ArrayList<NhanVien> nhanViens = new ArrayList<>();
                nhanViens = sqLife.getALLNV();
                for (int i =0;i<nhanViens.size();i++)
                {
                    String TaiKhoan2 = nhanViens.get(i).getUserName();
                    if (TaiKhoan.equals(TaiKhoan2)==true)
                    {
                        Toast.makeText(DangKi.this, "T??i Kho???n ???? T???n T???i" , Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(TaiKhoan.length()==0||MatKhau.length()==0||MatKhau2.length()==0)
                {
                    Toast.makeText(DangKi.this, "Kh??ng ???????c ????? Tr???ng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MatKhau.equals(MatKhau2)==false)
                {
                    Toast.makeText(DangKi.this, "M???t Kh???u Kh??ng Kh???p", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(checkBox.isChecked()==false)
                {
                    Toast.makeText(DangKi.this, "B???n Ch??a ?????ng ?? V???i ??i???u Kho???n C???a Ch??ng T??i", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(DangKi.this,StepDangKi.class);
                intent.putExtra("TaiKhoan",TaiKhoan);
                intent.putExtra("MatKhau",MatKhau);
                startActivity(intent);
            }
        });
    }
}