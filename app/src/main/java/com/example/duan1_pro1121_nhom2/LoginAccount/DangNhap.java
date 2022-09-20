package com.example.duan1_pro1121_nhom2.LoginAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.DataSQL.SQLife;
import com.example.duan1_pro1121_nhom2.MainActivity;
import com.example.duan1_pro1121_nhom2.R;

import java.io.Serializable;
import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    Button btnDangNhap;
    EditText edTaiKhoan,edMatKhau;
    TextView tvDangKi;
    CheckBox chk_remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        tvDangKi = findViewById(R.id.tvDangKi);
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        edMatKhau = findViewById(R.id.edMatKhau);
        chk_remember = findViewById(R.id.chk_remember);


        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edTaiKhoan.setText(preferences.getString("USERNAME", ""));
        edMatKhau.setText(preferences.getString("PASSWORD", ""));
        chk_remember.setChecked(preferences.getBoolean("REMEMBER", false));
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TaiKhoan = edTaiKhoan.getText().toString();
                String MatKhau = edMatKhau.getText().toString();
                SQLife sqLife = new SQLife(getApplicationContext());
                ArrayList<NhanVien> nhanViens = new ArrayList<>();
                nhanViens = sqLife.getALLNV();
                boolean check = false;
                if(TaiKhoan.length()==0||MatKhau.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Không Được Để Trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i =0;i<nhanViens.size();i++)
                {
                    String TaiKhoan2 = nhanViens.get(i).getUserName();
                    String MatKhau2 = nhanViens.get(i).getPassWord();

                    if (TaiKhoan.toString().equals(TaiKhoan2.toString())==true)
                    {
                        if (MatKhau.toString().equals(MatKhau2.toString())==true)
                        {
                            String VAITRO = nhanViens.get(i).getVaiTro();
                            NhanVien nhanVienput = nhanViens.get(i);
                            rememberUser(TaiKhoan2, MatKhau2, chk_remember.isChecked());
                            Intent intent = new Intent(DangNhap.this, MainActivity.class);
                            intent.putExtra("VAITRO",VAITRO);
                            intent.putExtra("infomation", (Serializable) nhanVienput);

                            ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);

                            startActivity(intent);
                            return;
                        }
                        if (MatKhau.toString().equals(MatKhau2.toString())==false)
                        {
                            Toast.makeText(DangNhap.this, "Mật Khẩu Chưa Chính Xác", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    check = false;


                }
                if (check==false)
                {
                    Toast.makeText(DangNhap.this, "Tài Khoản Không Tồn Tại", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        tvDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this,DangKi.class);
                startActivity(intent);
            }
        });
    }

    public void rememberUser(String user, String pass, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", user);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }
}