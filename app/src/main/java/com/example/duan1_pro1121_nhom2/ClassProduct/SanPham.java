package com.example.duan1_pro1121_nhom2.ClassProduct;

public class SanPham {
    int MASP;
    String NAMESP;
    int GIANHAP;
    String NGAYNHAP;
    int SOLUONGNHAP;
    int SOLUONGDABAN;
    int MALOAISP;
    int IMAGE;

    public SanPham() {
    }

    public SanPham(int MASP, String NAMESP, int GIANHAP, String NGAYNHAP, int SOLUONGNHAP, int SOLUONGDABAN, int MALOAISP, int IMAGE) {
        this.MASP = MASP;
        this.NAMESP = NAMESP;
        this.GIANHAP = GIANHAP;
        this.NGAYNHAP = NGAYNHAP;
        this.SOLUONGNHAP = SOLUONGNHAP;
        this.SOLUONGDABAN = SOLUONGDABAN;
        this.MALOAISP = MALOAISP;
        this.IMAGE = IMAGE;
    }

    public int getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(int IMAGE) {
        this.IMAGE = IMAGE;
    }

    public int getSOLUONGDABAN() {
        return SOLUONGDABAN;
    }

    public void setSOLUONGDABAN(int SOLUONGDABAN) {
        this.SOLUONGDABAN = SOLUONGDABAN;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public String getNAMESP() {
        return NAMESP;
    }

    public void setNAMESP(String NAMESP) {
        this.NAMESP = NAMESP;
    }

    public int getGIANHAP() {
        return GIANHAP;
    }

    public void setGIANHAP(int GIANHAP) {
        this.GIANHAP = GIANHAP;
    }

    public String getNGAYNHAP() {
        return NGAYNHAP;
    }

    public void setNGAYNHAP(String NGAYNHAP) {
        this.NGAYNHAP = NGAYNHAP;
    }

    public int getSOLUONGNHAP() {
        return SOLUONGNHAP;
    }

    public void setSOLUONGNHAP(int SOLUONGNHAP) {
        this.SOLUONGNHAP = SOLUONGNHAP;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }
}
