package com.dinhthikimthoa.de02_onthi.Model;

public class Product {
    String MaSp;
    String TenSp;
    double GiaSp;

    public Product(String maSp, String tenSp, double giaSp) {
        MaSp = maSp;
        TenSp = tenSp;
        GiaSp = giaSp;
    }

    public String getMaSp() {
        return MaSp;
    }

    public void setMaSp(String maSp) {
        MaSp = maSp;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String tenSp) {
        TenSp = tenSp;
    }

    public double getGiaSp() {
        return GiaSp;
    }

    public void setGiaSp(double giaSp) {
        GiaSp = giaSp;
    }
}
