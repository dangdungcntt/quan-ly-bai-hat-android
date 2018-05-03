package com.nddcoder.quanlybaihat;

public class BaiHat {
    private String maBaiHat;
    private String tenBaiHat;
    private String tenCaSi;
    private String thoiLuong;

    public String getMaBaiHat() {
        return maBaiHat;
    }

    public void setMaBaiHat(String maBaiHat) {
        this.maBaiHat = maBaiHat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public BaiHat(String maBaiHat, String tenBaiHat, String tenCaSi, String thoiLuong) {

        this.maBaiHat = maBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.tenCaSi = tenCaSi;
        this.thoiLuong = thoiLuong;
    }
}
