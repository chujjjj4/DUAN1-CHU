package Service;

import Model.HoaDon;
import Model.SanPham;
import Repository.DAO;
import Repository.Getconnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class HoaDonService extends DAO<HoaDon, Integer> {

    @Override
    public void insert(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updtae(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDon> getAllHD() {
        String SQL = """
                      select a.*, b.MaHDCT,b.MaSP,b.SoLuong, b.GiaTien, b.MaVoucher, b.TrangThai, b.GhiChu 
                                            from HoaDon a join HoaDonChiTiet b 
                                            on a.MaHD=b.MaHD""";
        List<HoaDon> list = new ArrayList<>();

        Connection conn = null;
        try {
            conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setTenKH(rs.getString("TenKH"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setMaNv(rs.getString("MaNV"));
                hd.setTongTien(rs.getInt("TongTien"));
                hd.setMaHDCT(rs.getInt("MaHDCT"));
                hd.setMaSp(rs.getString("MaSP"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setGiaTien(rs.getInt("GiaTien"));
                hd.setMaVoucher(rs.getInt("MaVoucher"));
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setGhiChu(rs.getString("GhiChu"));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HoaDon> selectAll() {
        String SQL = "SELECT * FROM HoaDon";
        List<HoaDon> list = new ArrayList<>();

        Connection conn = null;
        try {
            conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt("MaHD"),
                        rs.getDate("NgayLap"),
                        rs.getString("MaNV"),
                        rs.getBoolean("TrangThai"));
                list.add(hd);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPham> DSSanPham() {
        String SQL = "SELECT sp.MaSP, sp.TenSP, ms.TenMau AS TenMau, cl.TenChatLieu AS TenChatLieu, sz.MaSIZE, spct.SoLuong, spct.Gia "
                + "FROM SanPham sp "
                + "JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP "
                + "JOIN MauSac ms ON spct.MauSac = ms.ID "
                + "JOIN ChatLieu cl ON spct.ChatLieu = cl.ID "
                + "JOIN SIZE sz ON spct.Size = sz.ID";
        List<SanPham> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSp(rs.getString("MaSP"));
                sp.setTenSp(rs.getString("TenSP"));
                sp.setMauSac(rs.getString("TenMau"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                sp.setSize(rs.getString("MaSIZE"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getInt("Gia"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public HoaDon selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(sql);

            // Set các tham số cho truy vấn (nếu có)
            for (int i = 0; i < args.length; i++) {
                sttm.setObject(i + 1, args[i]);
            }

            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setMaNv(rs.getString("MaNV"));
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    ////////////////////////////////// phần fix của Chủ
    String insert_sql = """
                        INSERT INTO [dbo].[HoaDon]
                                              ([NgayLap]
                                              ,[MaNV])
                             VALUES
                                   (?,?)
                        """;

    public void inserthd(HoaDon entity) {
        Getconnection.update(insert_sql,
                entity.getNgayLap(),
                entity.getMaNv());
    }
    ///////////////////////////// hóa đơn

    public void updateHoaDonStatus(int maHD, boolean trangThai) {
        String updateSql = "UPDATE HoaDon SET TrangThai = ? WHERE MaHD = ?";
        Getconnection.update(updateSql, trangThai, maHD);
    }

    public HoaDon selectByIdhd(int maHD) {
        String selectByIdSql = "SELECT * FROM HoaDon WHERE MaHD = ?";
        List<HoaDon> result = selectBySql(selectByIdSql, maHD);
        return result.isEmpty() ? null : result.get(0);
    }

    ////////////////////////// hóa đơn chi tiết 
    private final String selectAllHDCT = "SELECT * FROM HoaDonChiTiet";
    private final String insertHDCT = "INSERT INTO HoaDonChiTiet (MaHD, TenKH, MaSP, SoLuong, GiaTien, HinhThucTT, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public List<HoaDon> selectAllhdct() {
        return this.selectBySqlhdct(selectAllHDCT);
    }

    protected List<HoaDon> selectBySqlhdct(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            var rs = Getconnection.query(sql, args);
            while (rs.next()) {
                var hv = new HoaDon();
                hv.setMaHD(rs.getInt("MaHD"));
                hv.setTenKH(rs.getString("TenKH"));
                hv.setMaSp(rs.getString("MaSP"));
                hv.setSoLuong(rs.getInt("SoLuong"));
                hv.setGiaTien(rs.getInt("GiaTien"));
                hv.setHinhThucTT(rs.getString("HinhThucTT"));
                hv.setGhiChu(rs.getString("GhiChu"));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void inserthdct(HoaDon entity) {
        Getconnection.update(insertHDCT,
                entity.getMaHD(), entity.getTenKH(),
                entity.getMaSp(), entity.getSoLuong(), entity.getGiaTien(), entity.getHinhThucTT(), entity.getGhiChu());
    }

//////////////////////////////////////////////////////////////////////////////////// lịch sử
    private final String selectLICHSU = "SELECT\n"
            + "    HD.MaHD,\n"
            + "    HD.NgayLap,\n"
            + "    HD.MaNV,\n"
            + "    HD.TrangThai,\n"
            + "    HDCT.TenKH\n"
            + "FROM\n"
            + "    HoaDon HD\n"
            + "INNER JOIN\n"
            + "    HoaDonChiTiet HDCT ON HD.MaHD = HDCT.MaHD;";

    public List<HoaDon> selectAlLICHSU() {
        return this.selectBySqlLICHSU(selectLICHSU);
    }

    protected List<HoaDon> selectBySqlLICHSU(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            var rs = Getconnection.query(sql, args);
            while (rs.next()) {
                var hv = new HoaDon();
                hv.setMaHD(rs.getInt("MaHD"));
                hv.setNgayLap(rs.getDate("NgayLap"));
                hv.setMaNv(rs.getString("MaNV"));
                hv.setTrangThai(rs.getBoolean("TrangThai"));
                hv.setTenKH(rs.getString("TenKH"));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////// LỊCH SỬ

    public HoaDon layThongTinHoaDonTheoMaHD(int maHD) {
        String sql = "SELECT HD.MaNV, HDCT.TenKH, HD.NgayLap, HD.TrangThai, HDCT.HinhThucTT, HDCT.GhiChu, HDCT.GiaTien\n"
                + "FROM HoaDon HD\n"
                + "INNER JOIN HoaDonChiTiet HDCT ON HD.MaHD = HDCT.MaHD\n"
                + "WHERE HD.MaHD = ?\n"
                + "GROUP BY HD.MaNV, HDCT.TenKH, HD.NgayLap, HD.TrangThai, HDCT.HinhThucTT, HDCT.GhiChu, HDCT.GiaTien;";

        List<HoaDon> hoaDons = selectBySqlLS(sql, maHD);

        // Tính tổng giá tiền của tất cả sản phẩm trong hóa đơn
        int tongGiaTien = tinhTongGiaTienHoaDon(maHD); // Hàm tính tổng giá tiền

        if (!hoaDons.isEmpty()) {
            HoaDon hoaDon = hoaDons.get(0);
            hoaDon.setGiaTien(tongGiaTien); // Set giá trị tổng giá tiền vào đối tượng HoaDon
            return hoaDon;
        }
        return null;
    }

    private int tinhTongGiaTienHoaDon(int maHD) {
        String sqlTongGiaTien = "SELECT SUM(GiaTien) AS TongGiaTien FROM HoaDonChiTiet WHERE MaHD = ?";
        int tongGiaTien = 0;
        Connection conn = null;
        try {
            conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(sqlTongGiaTien);
            sttm.setInt(1, maHD);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                tongGiaTien = rs.getInt("TongGiaTien");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tongGiaTien;
    }

    protected List<HoaDon> selectBySqlLS(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(sql);

            // Set các tham số cho truy vấn (nếu có)
            for (int i = 0; i < args.length; i++) {
                sttm.setObject(i + 1, args[i]);
            }

            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaNv(rs.getString("MaNV"));
                hd.setTenKH(rs.getString("TenKH"));
                hd.setGiaTien(rs.getInt("GiaTien"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setHinhThucTT(rs.getString("HinhThucTT"));
                hd.setGhiChu(rs.getString("GhiChu"));
                list.add(hd);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Map<Integer, HoaDon> mergeByMaHD(List<HoaDon> list) {
        Map<Integer, HoaDon> mergedMap = new HashMap<>();

        for (HoaDon hd : list) {
            int maHD = hd.getMaHD();
            if (!mergedMap.containsKey(maHD)) {
                mergedMap.put(maHD, hd);
            } else {
                // Logic gộp dữ liệu theo MaHD
                HoaDon existingHoaDon = mergedMap.get(maHD);
                existingHoaDon.setNgayLap(hd.getNgayLap());
                existingHoaDon.setMaNv(hd.getMaNv());
                existingHoaDon.setTrangThai(hd.getTrangThai());
                // Cập nhật các thông tin khác nếu cần thiết
            }
        }
        return mergedMap;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

    public List<HoaDon> searchProductsByKeywordSP(String keyword) {
        List<HoaDon> filteredProducts = new ArrayList<>();
        try {
            Connection connection = Getconnection.getConnection();
            String query = "SELECT hd.MaHD, hd.NgayLap, hd.MaNV, hd.TrangThai, hdct.TenKH\n"
                    + "FROM HoaDon hd\n"
                    + "INNER JOIN HoaDonChiTiet hdct ON hd.MaHD = hdct.MaHD\n"
                    + "WHERE hdct.TenKH LIKE ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                HoaDon sanPham = new HoaDon();
                sanPham.setMaHD(rs.getInt("MaHD"));
                sanPham.setNgayLap(rs.getDate("NgayLap"));
                sanPham.setMaNv(rs.getString("MaNV"));
                sanPham.setTrangThai(rs.getBoolean("TrangThai"));
                sanPham.setTenKH(rs.getString("TenKH"));
                filteredProducts.add(sanPham);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }
        return filteredProducts;
    }
}
