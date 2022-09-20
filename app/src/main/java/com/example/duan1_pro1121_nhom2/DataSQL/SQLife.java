package com.example.duan1_pro1121_nhom2.DataSQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDon;
import com.example.duan1_pro1121_nhom2.ClassProduct.HoaDonCT;
import com.example.duan1_pro1121_nhom2.ClassProduct.KhachHang;
import com.example.duan1_pro1121_nhom2.ClassProduct.LoaiSP;
import com.example.duan1_pro1121_nhom2.ClassProduct.NhanVien;
import com.example.duan1_pro1121_nhom2.ClassProduct.SanPham;

import java.util.ArrayList;

public class SQLife extends SQLiteOpenHelper {
    Context contextT;
    public SQLife(@Nullable Context context) {
        super(context, "database.txt", null, 1);
        contextT  = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
        db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
        db.execSQL("DROP TABLE IF EXISTS LOAISP");
        db.execSQL("DROP TABLE IF EXISTS SANPHAM");
        db.execSQL("DROP TABLE IF EXISTS HOADON");
        db.execSQL("DROP TABLE IF EXISTS HOADONCT");
        db.execSQL("CREATE TABLE NHANVIEN(MANV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT NOT NULL," +
                "PASSWORD TEXT NOT NULL," +
                "NAMENV TEXT NOT NULL," +
                "NUMBERPHONE TEXT NOT NULL," +
                "DIACHI TEXT NOT NULL," +
                "NGAYSINH TEXT NOT NULL," +
                "GIOITINH TEXT NOT NULL," +
                "VAITRO TEXT NOT NULL," +
                "IMAGE INTEGER NOT NULL) ");
        db.execSQL("CREATE TABLE KHACHHANG(MAKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMEKH TEXT NOT NULL," +
                "NUMBERPHONE TEXT NOT NULL," +
                "DIACHI TEXT NOT NULL," +
                "NGAYSINH TEXT NOT NULL," +
                "GIOITINH TEXT NOT NULL," +
                "IMAGE INTEGER NOT NULL) ");
        db.execSQL("CREATE TABLE LOAISP(MALOAISP INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMELOAISP TEXT NOT NULL," +
                "IMAGE INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE SANPHAM(MASP INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAMESP TEXT NOT NULL," +
                "GIANHAP INTEGER NOT NULL," +
                "NGAYNHAP TEXT NOT NULL," +
                "SOLUONGNHAP INTEGER NOT NULL," +
                "SOLUONGDABAN INTEGER NOT NULL,"+
                "MALOAISP INTEGER REFERENCES LOAISP(MALOAISP)," +
                "IMAGE INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE HOADON(MAHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MAKH INTEGER REFERENCES KHACHHANG(MAKH)," +
                "NGAYMUAHANG TEXT NOT NULL," +
                "MASP TEXT NOT NULL," +
                "NAMESP TEXT NOT NULL," +
                "TONGTIENHANG INTEGER NOT NULL," +
                "TRANGTHAI TEXT NOT NULL," +
                "MANV INTEGER REFERENCES NHANVIEN(MANV))");
        db.execSQL("CREATE TABLE HOADONCT(MAHDCT INTEGER PRIMARY KEY NOT NULL," +
                "MASP TEXT NOT NULL," +
                "NAMESP TEXT NOT NULL," +
                "MAKH INTEGER REFERENCES KHACHHANG(MAKH)," +
                "TENKH TEXT NOT NULL," +
                "NUMBERPHONE TEXT NOT NULL," +
                "GIATIENHANG INTEGER NOT NULL," +
                "SOTIENTHANHTOAN INTEGER NOT NULL," +
                "NGAYXUAT DATE NOT NULL," +
                "MANV INTEGER REFERENCES NHANVIEN(MANV))");

    }
    public void AddNhanVien(NhanVien nhanVien)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME",nhanVien.getUserName());
        contentValues.put("PASSWORD",nhanVien.getPassWord());
        contentValues.put("NAMENV",nhanVien.getTenNV());
        contentValues.put("NUMBERPHONE",nhanVien.getNumberPhone());
        contentValues.put("DIACHI",nhanVien.getDiaChi());
        contentValues.put("NGAYSINH",nhanVien.getNgaySinh());
        contentValues.put("GIOITINH",nhanVien.getGioiTinh());
        contentValues.put("VAITRO",nhanVien.getVaiTro());
        contentValues.put("IMAGE",nhanVien.getImageNV());
        SQLiteDatabase database = getReadableDatabase();
            database.insert("NHANVIEN",null,contentValues);


    }
    public NhanVien getOneNV(String MANV_)
    {
        SQLiteDatabase database = getReadableDatabase();
        NhanVien nhanVien = new NhanVien();
        Cursor cursor = database.rawQuery("SELECT * FROM NHANVIEN WHERE MANV = '"+MANV_+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MANV = cursor.getInt(0);
                String USERNAME = cursor.getString(1);
                String PASSWORD = cursor.getString(2);
                String NAMENV = cursor.getString(3);
                String NUMBERPHONE = cursor.getString(4);
                String DIACHI = cursor.getString(5);
                String NGAYSINH = cursor.getString(6);
                String GIOITINH = cursor.getString(7);
                String VAITRO = cursor.getString(8);
                int IMAGENV = cursor.getInt(9);
                nhanVien = new NhanVien(MANV,USERNAME,PASSWORD,NAMENV,NUMBERPHONE,DIACHI,NGAYSINH,GIOITINH,VAITRO,IMAGENV);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return nhanVien;
    }
    public ArrayList<NhanVien> getALLNV()
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM NHANVIEN",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MaNV = cursor.getInt(0);
                String USERNAME = cursor.getString(1);
                String PASSWORD = cursor.getString(2);
                String TENNV = cursor.getString(3);
                String NUMBERPHONE = cursor.getString(4);
                String DIACHI = cursor.getString(5);
                String NgaySinh = cursor.getString(6);
                String GIOITINH = cursor.getString(7);
                String VaiTro = cursor.getString(8);
                int IMAGENV = cursor.getInt(9);
                NhanVien nhanVien = new NhanVien(MaNV,USERNAME,PASSWORD,TENNV,NUMBERPHONE,DIACHI,NgaySinh,GIOITINH,VaiTro,IMAGENV);
                nhanViens.add(nhanVien);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return nhanViens;
    }
    public void DeleteNV(String MANV1)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("NHANVIEN","MANV=?",new String[]{MANV1+""});
    }
    public void UpdateNV(NhanVien nhanVien)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME",nhanVien.getUserName());
        contentValues.put("PASSWORD",nhanVien.getPassWord());
        contentValues.put("NAMENV",nhanVien.getTenNV());
        contentValues.put("NUMBERPHONE",nhanVien.getNumberPhone());
        contentValues.put("DIACHI",nhanVien.getDiaChi());
        contentValues.put("NGAYSINH",nhanVien.getNgaySinh());
        contentValues.put("GIOITINH",nhanVien.getGioiTinh());
        contentValues.put("VAITRO",nhanVien.getVaiTro());
        contentValues.put("IMAGE",nhanVien.getImageNV());
        database.update("NHANVIEN",contentValues,"MANV=?",new String[]{nhanVien.getMaNV()+""});
    }
// Start Bang Loai San Pham
    public void AddLoaiSP(LoaiSP loaiSP)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMELOAISP",loaiSP.getTenLoai());
        contentValues.put("IMAGE",loaiSP.getIMAGE_());
        SQLiteDatabase database = getWritableDatabase();
        database.insert("LOAISP",null,contentValues);
    }
    public ArrayList<LoaiSP> getALL_LSP()
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<LoaiSP> loaiSPS = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM LOAISP",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MALSP = cursor.getInt(0);
                String NAMELOAISP = cursor.getString(1);
                int IMAGE = cursor.getInt(2);
                LoaiSP loaiSP = new LoaiSP(MALSP,NAMELOAISP,IMAGE);
                loaiSPS.add(loaiSP);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiSPS;
    }

    public void DeleteLSP(LoaiSP loaiSP)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("LOAISP","MALOAISP=?",new String[]{loaiSP.getMaLoai()+""});
    }
    public void UpdateLSP(LoaiSP loaiSP)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMELOAISP",loaiSP.getTenLoai());
        contentValues.put("IMAGE",loaiSP.getIMAGE_());
        database.update("LOAISP",contentValues,"MALOAISP=?",new String[]{loaiSP.getMaLoai()+""});
    }
    // End Bang Loai San Pham
    // Start Bang San Pham
    public void AddSP(SanPham sanPham)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMESP",sanPham.getNAMESP());
        contentValues.put("GIANHAP",sanPham.getGIANHAP());
        contentValues.put("NGAYNHAP",sanPham.getNGAYNHAP());
        contentValues.put("SOLUONGNHAP",sanPham.getSOLUONGNHAP());
        contentValues.put("SOLUONGDABAN",sanPham.getSOLUONGDABAN());
        contentValues.put("MALOAISP",sanPham.getMALOAISP());
        contentValues.put("IMAGE",sanPham.getIMAGE());
        SQLiteDatabase database = getWritableDatabase();
        database.insert("SANPHAM",null,contentValues);
    }
    public ArrayList<SanPham> getALLSP()
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SANPHAM",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MASP = cursor.getInt(0);
                String NAMESP = cursor.getString(1);
                int GIANHAP = cursor.getInt(2);
                String NGAYNHAP = cursor.getString(3);
                int SLN = cursor.getInt(4);
                int SLB = cursor.getInt(5);
                int MALOAI = cursor.getInt(6);
                int IMG = cursor.getInt(7);
                SanPham sanPham = new SanPham(MASP,NAMESP,GIANHAP,NGAYNHAP,SLN,SLB,MALOAI,IMG);
                sanPhams.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sanPhams;
    }
    public ArrayList<SanPham> getTopSP()
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SANPHAM ORDER BY SOLUONGDABAN DESC LIMIT 10",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MASP = cursor.getInt(0);
                String NAMESP = cursor.getString(1);
                int GIANHAP = cursor.getInt(2);
                String NGAYNHAP = cursor.getString(3);
                int SLN = cursor.getInt(4);
                int SLB = cursor.getInt(5);
                int MALOAI = cursor.getInt(6);
                int IMG = cursor.getInt(7);
                SanPham sanPham = new SanPham(MASP,NAMESP,GIANHAP,NGAYNHAP,SLN,SLB,MALOAI,IMG);
                sanPhams.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sanPhams;
    }
    public ArrayList<SanPham> SearchSP(String name_)
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SANPHAM WHERE NAMESP LIKE '%"+name_+"%'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MASP = cursor.getInt(0);
                String NAMESP = cursor.getString(1);
                int GIANHAP = cursor.getInt(2);
                String NGAYNHAP = cursor.getString(3);
                int SLN = cursor.getInt(4);
                int SLB = cursor.getInt(5);
                int MALOAI = cursor.getInt(6);
                int IMG = cursor.getInt(7);
                SanPham sanPham = new SanPham(MASP,NAMESP,GIANHAP,NGAYNHAP,SLN,SLB,MALOAI,IMG);
                sanPhams.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sanPhams;
    }
    public void DeleteSP(String MASP)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("SANPHAM","MASP=?",new String[]{MASP});
    }
    public void UpdateSP(SanPham sanPham)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMESP",sanPham.getNAMESP());
        contentValues.put("GIANHAP",sanPham.getGIANHAP());
        contentValues.put("NGAYNHAP",sanPham.getNGAYNHAP());
        contentValues.put("SOLUONGNHAP",sanPham.getSOLUONGNHAP());
        contentValues.put("SOLUONGDABAN",sanPham.getSOLUONGDABAN());
        contentValues.put("MALOAISP",sanPham.getMALOAISP());
        contentValues.put("IMAGE",sanPham.getIMAGE());
        database.update("SANPHAM",contentValues,"MASP=?",new String[]{sanPham.getMASP()+""});
    }
    // End Bang San Pham
    // start Bang Khach Hang
    public void AddKhachhang(KhachHang khachHang)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMEKH",khachHang.getTenKH());
        contentValues.put("NUMBERPHONE",khachHang.getNumberPhone());
        contentValues.put("DIACHI",khachHang.getDiaChi());
        contentValues.put("NGAYSINH",khachHang.getNgaySinh());
        contentValues.put("GIOITINH",khachHang.getGioiTinh());
        contentValues.put("IMAGE",khachHang.getImageKH());
        SQLiteDatabase database = getWritableDatabase();
        database.insert("KHACHHANG",null,contentValues);
    }
    public KhachHang getOneKH(String MAKH_)
    {
        SQLiteDatabase database = getReadableDatabase();
        KhachHang khachHang = new KhachHang();
        Cursor cursor = database.rawQuery("SELECT * FROM KHACHHANG WHERE MAKH = '"+MAKH_+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MAKH = cursor.getInt(0);
                String NAMEKH = cursor.getString(1);
                String NUMBERPHONE = cursor.getString(2);
                String DIACHI = cursor.getString(3);
                String NgaySinh = cursor.getString(4);
                String GIOITINH = cursor.getString(5);
                int IMAGENV = cursor.getInt(6);
                khachHang = new KhachHang(MAKH,NAMEKH,NUMBERPHONE,DIACHI,NgaySinh,GIOITINH,IMAGENV);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khachHang;
    }
    public ArrayList<KhachHang> getALLKH()
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM KHACHHANG",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MAKH = cursor.getInt(0);
                String NAMEKH = cursor.getString(1);
                String NUMBERPHONE = cursor.getString(2);
                String DIACHI = cursor.getString(3);
                String NgaySinh = cursor.getString(4);
                String GIOITINH = cursor.getString(5);
                int IMAGENV = cursor.getInt(6);
                KhachHang khachHang = new KhachHang(MAKH,NAMEKH,NUMBERPHONE,DIACHI,NgaySinh,GIOITINH,IMAGENV);
                khachHangs.add(khachHang);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khachHangs;
    }
    public void DeleteKH(String MAKH)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("KHACHHANG","MAKH=?",new String[]{MAKH+""});
    }
    public void UpdateKH(KhachHang khachHang)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMEKH",khachHang.getTenKH());
        contentValues.put("NUMBERPHONE",khachHang.getNumberPhone());
        contentValues.put("DIACHI",khachHang.getDiaChi());
        contentValues.put("NGAYSINH",khachHang.getNgaySinh());
        contentValues.put("GIOITINH",khachHang.getGioiTinh());
        contentValues.put("IMAGE",khachHang.getImageKH());
        SQLiteDatabase database = getWritableDatabase();
        database.update("KHACHHANG",contentValues,"MAKH=?",new String[]{khachHang.getMaKH()+""});
    }
    // End Bang Khach hang
    // start Bang HoaDon

    public void AddHoaDon(HoaDon hoaDon)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAKH",hoaDon.getMaKH());
        contentValues.put("NGAYMUAHANG",hoaDon.getNgayMuaHang());
        contentValues.put("MASP",hoaDon.getMaSP());
        contentValues.put("NAMESP",hoaDon.getTenSP());
        contentValues.put("TONGTIENHANG",hoaDon.getTongTienHang());
        contentValues.put("TRANGTHAI",hoaDon.getTrangThai());
        contentValues.put("MANV",hoaDon.getMANV());
        SQLiteDatabase database = getWritableDatabase();
        database.insert("HOADON",null,contentValues);
    }
    public void DeleteHD(String MAHD)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("HOADON","MAHD=?",new String[]{MAHD});
    }
    public void UpdateHoaDon(HoaDon hoaDon)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAKH",hoaDon.getMaKH());
        contentValues.put("NGAYMUAHANG",hoaDon.getNgayMuaHang());
        contentValues.put("MASP",hoaDon.getMaSP());
        contentValues.put("NAMESP",hoaDon.getTenSP());
        contentValues.put("TONGTIENHANG",hoaDon.getTongTienHang());
        contentValues.put("TRANGTHAI",hoaDon.getTrangThai());
        contentValues.put("MANV",hoaDon.getMANV());
        SQLiteDatabase database = getWritableDatabase();
        database.update("HOADON",contentValues,"MAHD=?",new String[]{hoaDon.getMaHD()+""});
    }
    public ArrayList<HoaDon> getALLHoaDon(String MANV)
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<HoaDon> hoaDons = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM HOADON WHERE MANV LIKE '"+MANV+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MAHD = cursor.getInt(0);
                int MAKH = cursor.getInt(1);
                String NGAYMUA = cursor.getString(2);
                String MASP = cursor.getString(3);
                String NAMESP = cursor.getString(4);
                int TONGTIENHANG = cursor.getInt(5);
                String TrangThai = cursor.getString(6);
                int MANV_ = cursor.getInt(7);
                HoaDon hoaDon = new HoaDon(MAHD,MAKH,NGAYMUA,MASP,NAMESP,TONGTIENHANG,TrangThai,MANV_);
                hoaDons.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hoaDons;

    }
    // End Bang HoaDon
    // start Bang HoaDonCT
    public void AddHoaDonCT(HoaDonCT hoaDonCT)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAHDCT",hoaDonCT.getMAHDCT());
        contentValues.put("MASP",hoaDonCT.getMASP());
        contentValues.put("NAMESP",hoaDonCT.getNAMESP());
        contentValues.put("MAKH",hoaDonCT.getMAKH());
        contentValues.put("TENKH",hoaDonCT.getTENKH());
        contentValues.put("NUMBERPHONE",hoaDonCT.getNUMBERPHONE());
        contentValues.put("GIATIENHANG",hoaDonCT.getGIATIENHANG());
        contentValues.put("SOTIENTHANHTOAN",hoaDonCT.getSOTIENTHANHTOAN());
        contentValues.put("NGAYXUAT",hoaDonCT.getNGAYXUAT());
        contentValues.put("MANV",hoaDonCT.getMANV());
        SQLiteDatabase database = getWritableDatabase();
        database.insert("HOADONCT",null,contentValues);
    }
    public void DeleteHDCT(String MAHDCT)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("HOADONCT","MAHDCT=?",new String[]{MAHDCT});
    }
    public void UpdateHoaDonCT(HoaDonCT hoaDonCT)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAHDCT",hoaDonCT.getMAHDCT());
        contentValues.put("MASP",hoaDonCT.getMASP());
        contentValues.put("NAMESP",hoaDonCT.getNAMESP());
        contentValues.put("MAKH",hoaDonCT.getMAKH());
        contentValues.put("TENKH",hoaDonCT.getTENKH());
        contentValues.put("NUMBERPHONE",hoaDonCT.getNUMBERPHONE());
        contentValues.put("GIATIENHANG",hoaDonCT.getGIATIENHANG());
        contentValues.put("SOTIENTHANHTOAN",hoaDonCT.getSOTIENTHANHTOAN());
        contentValues.put("NGAYXUAT",hoaDonCT.getNGAYXUAT());
        contentValues.put("MANV",hoaDonCT.getMANV());
        SQLiteDatabase database = getWritableDatabase();
        database.update("HOADONCT",contentValues,"MAHDCT=?",new String[]{hoaDonCT.getMAHDCT()+""});
    }

    public ArrayList<HoaDonCT> getALLHDCT()
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<HoaDonCT> hoaDonCTS = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM HOADONCT",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MAHDCT = cursor.getInt(0);
                String MASP = cursor.getString(1);
                String NAMESP = cursor.getString(2);
                int MAKH = cursor.getInt(3);
                String NAMEKH = cursor.getString(4);
                String NUMBERPHONE = cursor.getString(5);
                int GIATIENHANG = cursor.getInt(6);
                int SOTIENTHANHTOAN = cursor.getInt(7);
                String NGAYXUAT = cursor.getString(8);
                int MANV = cursor.getInt(9);
                HoaDonCT hoaDonCT = new HoaDonCT(MAHDCT,MASP,NAMESP,MAKH,NAMEKH,NUMBERPHONE,GIATIENHANG,SOTIENTHANHTOAN,NGAYXUAT,MANV);
                hoaDonCTS.add(hoaDonCT);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hoaDonCTS;

    }
    public int DoanhThuDate(String manv,String StartDate,String EndDate)
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<HoaDonCT> hoaDonCTS = new ArrayList<>();
        int DoanhThu =0;
        Cursor cursor = database.rawQuery("SELECT sum(SOTIENTHANHTOAN) FROM HOADONCT WHERE MANV = '"+manv+"'",null);
        DoanhThu = cursor.getInt(0);
        return DoanhThu;
    }
    public ArrayList<HoaDonCT> getALLHDCT_NV(String MaNV_)
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<HoaDonCT> hoaDonCTS = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM HOADONCT WHERE MANV LIKE '"+MaNV_+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MAHDCT = cursor.getInt(0);
                String MASP = cursor.getString(1);
                String NAMESP = cursor.getString(2);
                int MAKH = cursor.getInt(3);
                String NAMEKH = cursor.getString(4);
                String NUMBERPHONE = cursor.getString(5);
                int GIATIENHANG = cursor.getInt(6);
                int SOTIENTHANHTOAN = cursor.getInt(7);
                String NGAYXUAT = cursor.getString(8);
                int MANV = cursor.getInt(9);
                HoaDonCT hoaDonCT = new HoaDonCT(MAHDCT,MASP,NAMESP,MAKH,NAMEKH,NUMBERPHONE,GIATIENHANG,SOTIENTHANHTOAN,NGAYXUAT,MANV);
                hoaDonCTS.add(hoaDonCT);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hoaDonCTS;

    }
    public ArrayList<HoaDonCT> getALLHDCT_DATE(String MaNV_,String StartDate,String EndDate)
    {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<HoaDonCT> hoaDonCTS = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM HOADONCT WHERE MANV LIKE '"+MaNV_+"' AND NGAYXUAT BETWEEN '"+StartDate+"' AND '"+EndDate+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                int MAHDCT = cursor.getInt(0);
                String MASP = cursor.getString(1);
                String NAMESP = cursor.getString(2);
                int MAKH = cursor.getInt(3);
                String NAMEKH = cursor.getString(4);
                String NUMBERPHONE = cursor.getString(5);
                int GIATIENHANG = cursor.getInt(6);
                int SOTIENTHANHTOAN = cursor.getInt(7);
                String NGAYXUAT = cursor.getString(8);
                int MANV = cursor.getInt(9);
                HoaDonCT hoaDonCT = new HoaDonCT(MAHDCT,MASP,NAMESP,MAKH,NAMEKH,NUMBERPHONE,GIATIENHANG,SOTIENTHANHTOAN,NGAYXUAT,MANV);
                hoaDonCTS.add(hoaDonCT);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hoaDonCTS;

    }
    // End Bang HoaDonCT
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
