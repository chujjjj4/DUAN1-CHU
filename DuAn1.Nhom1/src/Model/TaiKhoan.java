package Model;

public class TaiKhoan {

    private String MaNv;
    private String HoTen;
    private String MatKhau;
    private Boolean VaiTro;

    public TaiKhoan() {
    }

    public TaiKhoan(String MaNv, String HoTen, String MatKhau, Boolean VaiTro) {
        this.MaNv = MaNv;
        this.HoTen = HoTen;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
    }

    public String getMaNv() {
        return MaNv;
    }

    public void setMaNv(String MaNv) {
        this.MaNv = MaNv;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public Boolean getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(Boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

}
