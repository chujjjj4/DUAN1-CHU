package Service;

import Model.ThongKe;
import Repository.Getconnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThongKeService {

    private final String select_all1 = """
    SELECT Thang,SoLuongBan,TongGiaBan,TongGiaGiamGia,DoanhThu FROM DoanhThuThang
""";

    public List<ThongKe> selectAllDT() {
        return this.selectBySqlDT(select_all1);
    }

    protected List<ThongKe> selectBySqlDT(String sql, Object... args) {
        List<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = Getconnection.query(sql, args);
            while (rs.next()) {
                ThongKe nv = new ThongKe();
                nv.setThang(rs.getInt("Thang"));
                nv.setSoLuongBan(rs.getInt("SoLuongBan"));
                nv.setTongGiaBan(rs.getInt("TongGiaBan"));
                nv.setTongGiaGiam(rs.getInt("TongGiaGiamGia"));
                nv.setDoanhThu(rs.getInt("DoanhThu"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getNamCTDT() {
        List<Integer> namList = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT Nam FROM DoanhThuThang");

            while (rs.next()) {
                namList.add(rs.getInt("Nam"));
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return namList;
    }
//////////////////////////////////////////// CÁC PANE TRÊN CÙNG //////////////////////////////////////////////////////////////
//    SELECT DoanhThu
//FROM DoanhThuThang
//WHERE Nam = YEAR(GETDATE()) AND Thang = MONTH(GETDATE())

    public List<Integer> getDoanhThuNam() {
        List<Integer> doanhThuList = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DoanhThu FROM DoanhThuThang WHERE Nam = YEAR(GETDATE())");
            while (rs.next()) {
                doanhThuList.add(rs.getInt("DoanhThu"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuList;
    }

    public List<Integer> getDoanhThuThang() {
        List<Integer> doanhThuThang = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DoanhThu FROM DoanhThuThang WHERE Thang = MONTH(GETDATE())");
            while (rs.next()) {
                doanhThuThang.add(rs.getInt("DoanhThu"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuThang;
    }

    public List<Integer> getSOHANGNAM() {
        List<Integer> doanhThuThang = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SoLuongBan FROM DoanhThuThang WHERE Nam = YEAR(GETDATE())");
            while (rs.next()) {
                doanhThuThang.add(rs.getInt("SoLuongBan"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuThang;
    }

    public List<Integer> getSOHANGTHANG() {
        List<Integer> doanhThuThang = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SoLuongBan FROM DoanhThuThang WHERE Thang = MONTH(GETDATE())");
            while (rs.next()) {
                doanhThuThang.add(rs.getInt("SoLuongBan"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuThang;
    }

    public List<Integer> getHOADONNAM() {
        List<Integer> doanhThuThang = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS SoHoaDon FROM HoaDon WHERE YEAR(NgayLap) = YEAR(GETDATE())");
            while (rs.next()) {
                doanhThuThang.add(rs.getInt("SoHoaDon"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuThang;
    }

    public List<Integer> getHOADONTHANG() {
        List<Integer> doanhThuThang = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS SoHoaDon FROM HoaDon WHERE MONTH(NgayLap) = MONTH(GETDATE())");
            while (rs.next()) {
                doanhThuThang.add(rs.getInt("SoHoaDon"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuThang;
    }
///////////////////////////////////////////////////////////////////////////////////////

    public List<Integer> getDOANHTHUNGAY() {
        List<Integer> doanhTHUNGAY = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DoanhThu FROM DoanhThuVaDonHangHomNay WHERE CONVERT(DATE, NgayLap) = CONVERT(DATE, GETDATE())");
            while (rs.next()) {
                doanhTHUNGAY.add(rs.getInt("DoanhThu"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhTHUNGAY;
    }

    public List<Integer> getDONHANGNGAY() {
        List<Integer> doanhTHUNGAY = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS SoHoaDon FROM HoaDon WHERE CONVERT(DATE, NgayLap) = CONVERT(DATE, GETDATE())");
            while (rs.next()) {
                doanhTHUNGAY.add(rs.getInt("SoHoaDon"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhTHUNGAY;
    }

    public List<Integer> getSOLUONGNGAY() {
        List<Integer> doanhThuThang = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SoLuongBan FROM DoanhThuVaDonHangHomNay WHERE CONVERT(DATE, NgayLap) = CONVERT(DATE, GETDATE())");
            while (rs.next()) {
                doanhThuThang.add(rs.getInt("SoLuongBan"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuThang;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public List<Integer> getDOANHTHUNGAYTIM(Date startDate, Date endDate) {
        List<Integer> doanhTHUNGAY = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String startDateStr = sdf.format(startDate);
            String endDateStr = sdf.format(endDate);

            String query = "SELECT DoanhThu FROM DoanhThuKhiTim WHERE CONVERT(DATE, NgayTN) BETWEEN '" + startDateStr + "' AND '" + endDateStr + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                doanhTHUNGAY.add(rs.getInt("DoanhThu"));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhTHUNGAY;
    }

    public List<Integer> getDONHANGNGAYTIM(Date startDate, Date endDate) {
        List<Integer> doanhTHUNGAY = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String startDateStr = sdf.format(startDate);
            String endDateStr = sdf.format(endDate);

            String query = "SELECT COUNT(*) AS SoHoaDon FROM HoaDon WHERE CONVERT(DATE, NgayLap) BETWEEN '" + startDateStr + "' AND '" + endDateStr + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                doanhTHUNGAY.add(rs.getInt("SoHoaDon"));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhTHUNGAY;
    }

    public List<Integer> getSOLUONGNGAYTIM(Date startDate, Date endDate) {
        List<Integer> doanhThuThang = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String startDateStr = sdf.format(startDate);
            String endDateStr = sdf.format(endDate);

            String query = "SELECT SoLuongBan FROM DoanhThuKhiTim WHERE CONVERT(DATE, NgayTN) BETWEEN '" + startDateStr + "' AND '" + endDateStr + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                doanhThuThang.add(rs.getInt("SoLuongBan"));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuThang;
    }

    public List<Integer> XuatDoanhThu() {
        List<Integer> doanhThuList = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MaSP, SoLuongBan, TongGiaBan, TongGiaGiamGia, DoanhThu FROM XuatDoanhThu");
            while (rs.next()) {
                doanhThuList.add(rs.getInt("MaSP"));
                doanhThuList.add(rs.getInt("SoLuongBan"));
                doanhThuList.add(rs.getInt("TongGiaBan"));
                doanhThuList.add(rs.getInt("TongGiaGiamGia"));
                doanhThuList.add(rs.getInt("DoanhThu"));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhThuList;
    }

    private final String select_allxhd = "SELECT MaSP, SoLuongBan, TongGiaBan, TongGiaGiamGia, DoanhThu FROM XuatDoanhThu";

    public List<ThongKe> selectAllXHD() {
        return this.selectBySqlXHD(select_allxhd);
    }

    protected List<ThongKe> selectBySqlXHD(String sql, Object... args) {
        List<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = Getconnection.query(sql, args);
            while (rs.next()) {
                ThongKe nv = new ThongKe();
                nv.setMaSP(rs.getInt("MaSP"));
                nv.setSoLuongBan(rs.getInt("SoLuongBan"));
                nv.setTongGiaBan(rs.getInt("TongGiaBan"));
                nv.setTongGiaGiam(rs.getInt("TongGiaGiamGia"));
                nv.setDoanhThu(rs.getInt("DoanhThu"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
