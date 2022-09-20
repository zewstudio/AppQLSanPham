package com.example.duan1_pro1121_nhom2.ClassProduct;

public class KhachHang {
    int MaKH;
    String TenKH;
    String NumberPhone;
    String DiaChi;
    String NgaySinh;
    String GioiTinh;
    int ImageKH;

    public KhachHang(int maKH, String tenKH, String numberPhone, String diaChi, String ngaySinh, String gioiTinh, int imageKH) {
        MaKH = maKH;
        TenKH = tenKH;
        NumberPhone = numberPhone;
        DiaChi = diaChi;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        ImageKH = imageKH;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public KhachHang() {
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public int getImageKH() {
        return ImageKH;
    }

    public void setImageKH(int imageKH) {
        ImageKH = imageKH;
    }
}
