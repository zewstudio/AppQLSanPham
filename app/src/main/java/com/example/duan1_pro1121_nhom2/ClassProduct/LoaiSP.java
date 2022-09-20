package com.example.duan1_pro1121_nhom2.ClassProduct;

public class LoaiSP {
    int MaLoai;
    String TenLoai;
    int IMAGE_;

    public LoaiSP(int maLoai, String tenLoai, int IMAGE_) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        this.IMAGE_ = IMAGE_;
    }

    public int getIMAGE_() {
        return IMAGE_;
    }

    public void setIMAGE_(int IMAGE_) {
        this.IMAGE_ = IMAGE_;
    }

    public LoaiSP() {
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
