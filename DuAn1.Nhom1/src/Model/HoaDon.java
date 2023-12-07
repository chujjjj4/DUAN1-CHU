package Model;

import java.util.Date;

public class HoaDon {

    private int MaHD;
    private int MaHDCT;
    private String TenKH;
    private Date NgayLap;
    private String MaNv;
    private int TongTien;
    private String MaSp;
    private int SoLuong;
    private int GiaTien;
    private int MaVoucher;
    private Boolean TrangThai;
    private String GhiChu;
    private String HinhThucTT;

    public String getHinhThucTT() {
        return HinhThucTT;
    }

    public void setHinhThucTT(String HinhThucTT) {
        this.HinhThucTT = HinhThucTT;
    }

    public HoaDon() {
    }

    public HoaDon(int MaHD, Date NgayLap, String MaNv, Boolean TrangThai) {
        this.MaHD = MaHD;
        this.NgayLap = NgayLap;
        this.MaNv = MaNv;
        this.TrangThai = TrangThai;
    }

    public HoaDon(int MaHD, int MaHDCT, String TenKH, Date NgayLap, String MaNv, int TongTien, String MaSp, int SoLuong, int GiaTien, int MaVoucher, Boolean TrangThai, String GhiChu) {
        this.MaHD = MaHD;
        this.MaHDCT = MaHDCT;
        this.TenKH = TenKH;
        this.NgayLap = NgayLap;
        this.MaNv = MaNv;
        this.TongTien = TongTien;
        this.MaSp = MaSp;
        this.SoLuong = SoLuong;
        this.GiaTien = GiaTien;
        this.MaVoucher = MaVoucher;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaHDCT() {
        return MaHDCT;
    }

    public void setMaHDCT(int MaHDCT) {
        this.MaHDCT = MaHDCT;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public String getMaNv() {
        return MaNv;
    }

    public void setMaNv(String MaNv) {
        this.MaNv = MaNv;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public String getMaSp() {
        return MaSp;
    }

    public void setMaSp(String MaSp) {
        this.MaSp = MaSp;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int GiaTien) {
        this.GiaTien = GiaTien;
    }

    public int getMaVoucher() {
        return MaVoucher;
    }

    public void setMaVoucher(int MaVoucher) {
        this.MaVoucher = MaVoucher;
    }

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    private String TenSp;
    private String Size;
    private String MoTa;
    private String MaSPCT;
    private String MauSac;
    private String ChatLieu;
    private int Gia;
    private int Sale;

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String TenSp) {
        this.TenSp = TenSp;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(String MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public String getChatLieu() {
        return ChatLieu;
    }

    public void setChatLieu(String ChatLieu) {
        this.ChatLieu = ChatLieu;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    public int getSale() {
        return Sale;
    }

    public void setSale(int Sale) {
        this.Sale = Sale;
    }
    
}
