package Service;

import Model.SanPham;
import Model.Vourcher;
import Repository.Getconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoucherService {

    public List<Vourcher> selectAll() {
        String SQL = "SELECT * FROM KhuyenMai";
        List<Vourcher> list = new ArrayList<>();

        Connection conn = null;
        try {
            conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                Vourcher hd = new Vourcher(rs.getInt("MaKM"),
                        rs.getString("TenKM"), rs.getDate("NgayBatDau"),
                        rs.getDate("NgayKetThuc"), rs.getInt("GiamGia"),
                        rs.getBoolean("TrangThai"));
                list.add(hd);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //////////////////////////////////////
    String insert_sql = """
                        INSERT INTO [dbo].[KhuyenMai]
                                              ([TenKM]
                                              ,[NgayBatDau],[NgayKetThuc],[GiamGia])
                             VALUES
                                   (?,?,?,?)
                        """;

    public void inserthd(Vourcher entity) {
        Getconnection.update(insert_sql,
                entity.getTenVoucher(),
                entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.getGiamGia());
    }

    //////////////////////////////////////
    private final String update_sql = """
        UPDATE KhuyenMai
        SET  TenKM = ?, NgayBatDau = ?, NgayKetThuc = ?, GiamGia = ?, TrangThai = ?
        WHERE MaKM = ?
    """;

    public void updatevc(Vourcher entity) {
        Getconnection.update(update_sql,
                entity.getTenVoucher(), entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.getGiamGia(), entity.getTrangThai(),
                entity.getMaVourcher());
    }

    //////////////////////////////////////////////////////////
    public List<Vourcher> searchProductsByKeyword(String keyword) {
        List<Vourcher> filteredProducts = new ArrayList<>();
        try {
            Connection connection = Getconnection.getConnection();
            String query = "SELECT * FROM KhuyenMai "
                    + "WHERE TenKM LIKE ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Vourcher sanPham = new Vourcher();
                sanPham.setMaVourcher(rs.getInt("MaKM"));
                sanPham.setTenVoucher(rs.getString("TenKM"));
                sanPham.setNgayBatDau(rs.getDate("NgayBatDau"));
                sanPham.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                sanPham.setGiamGia(rs.getInt("GiamGia"));
                sanPham.setTrangThai(rs.getBoolean("TrangThai"));
                filteredProducts.add(sanPham);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredProducts;
    }
////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateSanPhamByMaKM(int loai, int maKM) {
        String updateQuery = "UPDATE SanPhamChiTiet SET KhuyenMai = ? WHERE Loai = ?";
        try ( Connection connection = Getconnection.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, maKM);
            preparedStatement.setInt(2, loai);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật dữ liệu thành công!");
            } else {
                System.out.println("Không có dữ liệu nào được cập nhật.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHoaDonStatus(int maKM, int loai) {
        String updateSql = "UPDATE SanPhamChiTiet SET Loai = ? WHERE KhuyenMai = ?";
        Getconnection.update(updateSql, loai, maKM);
    }

    private final String update_sqlTrangThai = """
       UPDATE KhuyenMai
        SET TrangThai = 0
        WHERE NgayKetThuc <= GETDATE()
    """;

    public void updateTrangThai(Vourcher entity) {
        Getconnection.update(update_sqlTrangThai);
    }

}
