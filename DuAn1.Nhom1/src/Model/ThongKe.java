package Model;

import java.util.Date;

public class ThongKe {

    private int Thang;
    private int soLuongBan;
    private int tongGiaBan;
    private int tongGiaGiam;
    private int doanhThu;

    private int Nam;
    private int MaSP;

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }

    public ThongKe() {
    }

    public ThongKe(int Thang, int soLuongBan, int tongGiaBan, int tongGiaGiam, int doanhThu) {
        this.Thang = Thang;
        this.soLuongBan = soLuongBan;
        this.tongGiaBan = tongGiaBan;
        this.tongGiaGiam = tongGiaGiam;
        this.doanhThu = doanhThu;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public int getTongGiaBan() {
        return tongGiaBan;
    }

    public void setTongGiaBan(int tongGiaBan) {
        this.tongGiaBan = tongGiaBan;
    }

    public int getTongGiaGiam() {
        return tongGiaGiam;
    }

    public void setTongGiaGiam(int tongGiaGiam) {
        this.tongGiaGiam = tongGiaGiam;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }

}
