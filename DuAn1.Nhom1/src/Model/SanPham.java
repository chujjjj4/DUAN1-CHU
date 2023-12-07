package Model;

public class SanPham {

    private String MaSp;
    private String TenSp;
    private String Size;
    private String MoTa;
    private String MaSPCT;
    private String MauSac;
    private String ChatLieu;
    private int SoLuong;
    private int Gia;
    private int Sale;
    private String Loai;

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public SanPham(String Size, int Gia, int GiamGia) {
        this.Size = Size;
        this.Gia = Gia;
        this.GiamGia = GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }
    private int GiamGia;
    private int ThanhTien;

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public SanPham() {
    }

    public SanPham(String MaSp, String TenSp, String Size, String MoTa, String MaSPCT, String MauSac, String ChatLieu, int SoLuong, int Gia, int Sale) {
        this.MaSp = MaSp;
        this.TenSp = TenSp;
        this.Size = Size;
        this.MoTa = MoTa;
        this.MaSPCT = MaSPCT;
        this.MauSac = MauSac;
        this.ChatLieu = ChatLieu;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.Sale = Sale;
    }

    public String getMaSp() {
        return MaSp;
    }

    public void setMaSp(String MaSp) {
        this.MaSp = MaSp;
    }

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

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
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

    private String TenMau;
    private String TenLoai;
    private String TenChatLieu;
    private String TenSize;
    private int ID;
    private int IDMAU;
    private int IDSIZE;
    private int IDLOAI;

    public int getIDMAU() {
        return IDMAU;
    }

    public void setIDMAU(int IDMAU) {
        this.IDMAU = IDMAU;
    }

    public int getIDSIZE() {
        return IDSIZE;
    }

    public void setIDSIZE(int IDSIZE) {
        this.IDSIZE = IDSIZE;
    }

    public int getIDLOAI() {
        return IDLOAI;
    }

    public void setIDLOAI(int IDLOAI) {
        this.IDLOAI = IDLOAI;
    }

    public String getTenMau() {
        return TenMau;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public String getTenChatLieu() {
        return TenChatLieu;
    }

    public void setTenChatLieu(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }
    private int loaiKM;

    public int getLoaiKM() {
        return loaiKM;
    }

    public void setLoaiKM(int loaiKM) {
        this.loaiKM = loaiKM;
    }

}
