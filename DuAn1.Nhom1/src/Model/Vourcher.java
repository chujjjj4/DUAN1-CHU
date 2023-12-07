package Model;

import java.util.Date;

public class Vourcher {

    private int MaVoucher;
    private String TenVoucher;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private int GiamGia;
    private Boolean TrangThai;

    public Vourcher() {
    }

    public Vourcher(int MaVoucher, String TenVoucher, Date NgayBatDau, Date NgayKetThuc, int GiamGia, Boolean TrangThai) {
        this.MaVoucher = MaVoucher;
        this.TenVoucher = TenVoucher;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GiamGia = GiamGia;
        this.TrangThai = TrangThai;
    }

    public int getMaVourcher() {
        return MaVoucher;
    }

    public void setMaVourcher(int MaVourcher) {
        this.MaVoucher = MaVourcher;
    }

    public String getTenVoucher() {
        return TenVoucher;
    }

    public void setTenVoucher(String TenVoucher) {
        this.TenVoucher = TenVoucher;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "Vourcher{" + "TenVoucher=" + TenVoucher + ", GiamGia=" + GiamGia + '}';
    }

}
