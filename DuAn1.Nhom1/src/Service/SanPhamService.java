package Service;

import Model.SanPham;
import Repository.Getconnection;
import java.awt.image.SampleModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamService {

    public void insertIntoSanPhamAndSanPhamChiTiet(SanPham entity) {
        try {
            Connection connection = Getconnection.getConnection();
            connection.setAutoCommit(false);

            // Thêm dữ liệu vào bảng SanPham
            String insertSanPhamQuery = "INSERT INTO SanPham (TenSP, MoTa) VALUES (?, ?)";
            PreparedStatement pstmtSanPham = connection.prepareStatement(insertSanPhamQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtSanPham.setString(1, entity.getTenSp());
            pstmtSanPham.setString(2, entity.getMoTa());
            pstmtSanPham.executeUpdate();

            // Lấy mã sản phẩm vừa được thêm vào bảng SanPham
            ResultSet generatedKeys = pstmtSanPham.getGeneratedKeys();
            int maSP = -1;
            if (generatedKeys.next()) {
                maSP = generatedKeys.getInt(1);
            }

            // Lấy ID từ các bảng tham chiếu (MauSac, ChatLieu, SIZE, LOAI) dựa trên các tên (TenChatLieu, TenMau, MaSize, TenLoai)
            int mauSacID = getIDFromTenMau(entity.getMauSac());
            int chatLieuID = getIDFromTenChatLieu(entity.getChatLieu());
            int sizeID = getIDFromMaSize(entity.getSize());
            int loaiID = getIDFromLoai(entity.getLoai());

            // Thêm dữ liệu vào bảng SanPhamChiTiet
            String insertSanPhamChiTietQuery = "INSERT INTO SanPhamChiTiet (MaSP, MauSac, ChatLieu, Size, Loai, SoLuong, Gia) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmtSanPhamChiTiet = connection.prepareStatement(insertSanPhamChiTietQuery);
            pstmtSanPhamChiTiet.setInt(1, maSP);
            pstmtSanPhamChiTiet.setInt(2, mauSacID);
            pstmtSanPhamChiTiet.setInt(3, chatLieuID);
            pstmtSanPhamChiTiet.setInt(4, sizeID);
            pstmtSanPhamChiTiet.setInt(5, loaiID);
            pstmtSanPhamChiTiet.setObject(6, entity.getSoLuong());
            pstmtSanPhamChiTiet.setObject(7, entity.getGia());
            pstmtSanPhamChiTiet.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            pstmtSanPham.close();
            pstmtSanPhamChiTiet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }
    }

    public void updateSanPhamAndSanPhamChiTiet(SanPham entity) {
        try {
            Connection connection = Getconnection.getConnection();
            connection.setAutoCommit(false);

            // Update data in the 'SanPham' table
            String updateSanPhamQuery = "UPDATE SanPham SET TenSP = ?, MoTa = ? WHERE MaSP = ?";
            PreparedStatement pstmtSanPham = connection.prepareStatement(updateSanPhamQuery);
            pstmtSanPham.setString(1, entity.getTenSp());
            pstmtSanPham.setString(2, entity.getMoTa());
            pstmtSanPham.setString(3, entity.getMaSp());
            pstmtSanPham.executeUpdate();

            // Retrieve ID from reference tables (MauSac, ChatLieu, SIZE, LOAI) based on names (TenChatLieu, TenMau, MaSize, TenLoai)
            int mauSacID = getIDFromTenMau(entity.getMauSac());
            int chatLieuID = getIDFromTenChatLieu(entity.getChatLieu());
            int sizeID = getIDFromMaSize(entity.getSize());
            int loaiID = getIDFromLoai(entity.getLoai());

            // Update data in the 'SanPhamChiTiet' table
            String updateSanPhamChiTietQuery = "UPDATE SanPhamChiTiet SET MauSac = ?, ChatLieu = ?, Size = ?, Loai = ?, SoLuong = ?, Gia = ? WHERE MaSP = ?";
            PreparedStatement pstmtSanPhamChiTiet = connection.prepareStatement(updateSanPhamChiTietQuery);
            pstmtSanPhamChiTiet.setInt(1, mauSacID);
            pstmtSanPhamChiTiet.setInt(2, chatLieuID);
            pstmtSanPhamChiTiet.setInt(3, sizeID);
            pstmtSanPhamChiTiet.setInt(4, loaiID);
            pstmtSanPhamChiTiet.setObject(5, entity.getSoLuong());
            pstmtSanPhamChiTiet.setObject(6, entity.getGia());
            pstmtSanPhamChiTiet.setString(7, entity.getMaSp());
            pstmtSanPhamChiTiet.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            pstmtSanPham.close();
            pstmtSanPhamChiTiet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions or error messages if needed
        }
    }

    /////////////////////// ẨN SẢN PHẨM .//////////////////////////////////////////
    public void anSanPham(String maSPToHide) {
        try {
            Connection connection = Getconnection.getConnection();
            connection.setAutoCommit(false);

            // Update the IsHidden column of the product to mark it as hidden
            String hideProductQuery = "SELECT sp.MaSP, sp.TenSP, l.TenLoai AS Loai, spct.Gia, spct.SoLuong, "
                    + "ms.TenMau AS MauSac, s.MaSIZE AS Size, cl.TenChatLieu AS ChatLieu "
                    + "FROM SanPham sp "
                    + "INNER JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP "
                    + "INNER JOIN MauSac ms ON spct.MauSac = ms.ID "
                    + "INNER JOIN ChatLieu cl ON spct.ChatLieu = cl.ID "
                    + "INNER JOIN SIZE s ON spct.Size = s.ID "
                    + "INNER JOIN LOAI l ON spct.Loai = l.ID "
                    + "WHERE sp.MaSP = ?";
            PreparedStatement pstmtHideProduct = connection.prepareStatement(hideProductQuery);
            pstmtHideProduct.setString(1, maSPToHide);
            pstmtHideProduct.executeUpdate();
            pstmtHideProduct.close();

            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions or show error messages if needed
        }
    }

    private int getIDFromTenMau(String tenMau) {
        int mauSacID = -1;

        try {
            Connection connection = Getconnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT ID FROM MauSac WHERE TenMau = ?");
            pstmt.setString(1, tenMau);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mauSacID = rs.getInt("ID");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacID;
    }

    private int getIDFromTenChatLieu(String tenMau) {
        int mauSacID = -1;

        try {
            Connection connection = Getconnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT ID FROM ChatLieu WHERE TenChatLieu = ?");
            pstmt.setString(1, tenMau);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mauSacID = rs.getInt("ID");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacID;
    }

    private int getIDFromMaSize(String tenMau) {
        int mauSacID = -1;

        try {
            Connection connection = Getconnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT ID FROM SIZE WHERE MaSIZE = ?");
            pstmt.setString(1, tenMau);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mauSacID = rs.getInt("ID");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacID;
    }

    private int getIDFromLoai(String tenMau) {
        int mauSacID = -1;

        try {
            Connection connection = Getconnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT ID FROM LOAI WHERE TenLoai = ?");
            pstmt.setString(1, tenMau);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mauSacID = rs.getInt("ID");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacID;
    }

//////////////////////////////////// TÌM SẢN PHẨM THEO TÊN ////////////////////////////////////
    public List<SanPham> searchProductsByKeyword(String keyword) {
        List<SanPham> filteredProducts = new ArrayList<>();
        try {
            Connection connection = Getconnection.getConnection();
            String query = "SELECT sp.MaSP, sp.TenSP, l.TenLoai AS Loai, spct.Gia, spct.SoLuong, "
                    + "ms.TenMau AS MauSac, s.MaSIZE AS Size, cl.TenChatLieu AS ChatLieu "
                    + "FROM SanPham sp "
                    + "INNER JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP "
                    + "INNER JOIN MauSac ms ON spct.MauSac = ms.ID "
                    + "INNER JOIN ChatLieu cl ON spct.ChatLieu = cl.ID "
                    + "INNER JOIN SIZE s ON spct.Size = s.ID "
                    + "INNER JOIN LOAI l ON spct.Loai = l.ID "
                    + "WHERE sp.TenSP LIKE ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSp(rs.getString("MaSP"));
                sanPham.setTenSp(rs.getString("TenSP"));
                sanPham.setLoai(rs.getString("Loai"));
                sanPham.setGia(rs.getInt("Gia"));
                sanPham.setSoLuong(rs.getInt("SoLuong"));
                sanPham.setMauSac(rs.getString("MauSac"));
                sanPham.setSize(rs.getString("Size"));
                sanPham.setChatLieu(rs.getString("ChatLieu"));
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

    public List<SanPham> selectAll12() {
        List<SanPham> sanPhams = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            String selectAllQuery = "SELECT sp.MaSP, sp.TenSP, l.TenLoai AS Loai , spct.Gia, spct.SoLuong, "
                    + "ms.TenMau AS MauSac, "
                    + "s.MaSize AS Size, cl.TenChatLieu AS ChatLieu "
                    + "FROM SanPham sp "
                    + "INNER JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP "
                    + "INNER JOIN MauSac ms ON spct.MauSac = ms.ID "
                    + "INNER JOIN ChatLieu cl ON spct.ChatLieu = cl.ID "
                    + "INNER JOIN SIZE s ON spct.Size = s.ID "
                    + "INNER JOIN LOAI l ON spct.Loai = l.ID";

            PreparedStatement pstmt = connection.prepareStatement(selectAllQuery);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSp(rs.getString("MaSP"));
                sanPham.setTenSp(rs.getString("TenSP"));
                sanPham.setLoai(rs.getString("Loai"));
                sanPham.setGia(rs.getInt("Gia"));
                sanPham.setSoLuong(rs.getInt("SoLuong"));
                sanPham.setMauSac(rs.getString("MauSac"));
                sanPham.setSize(rs.getString("Size"));
                sanPham.setChatLieu(rs.getString("ChatLieu"));

                sanPhams.add(sanPham);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return sanPhams;
    }

    /////////////////////////////////// CẬP NHẬT COMBOBOX /////////////////////////////////////////
    public List<String> getMauSacData() {
        List<String> mauSacList = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TenMau FROM MauSac");

            while (rs.next()) {
                mauSacList.add(rs.getString("TenMau"));
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacList;
    }

    public List<String> getLoaiData() {
        List<String> mauSacList = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TenLoai FROM Loai");

            while (rs.next()) {
                mauSacList.add(rs.getString("TenLoai"));
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacList;
    }

    public List<String> getKichThuocData() {
        List<String> mauSacList = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MaSIZE FROM SIZE");

            while (rs.next()) {
                mauSacList.add(rs.getString("MaSIZE"));
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacList;
    }

    public List<String> getChatLieuData() {
        List<String> mauSacList = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TenChatLieu FROM ChatLieu");

            while (rs.next()) {
                mauSacList.add(rs.getString("TenChatLieu"));
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return mauSacList;
    }

///////////////////////////////////////Giỏ hàng ////////////////////////////////////////////////////////////
    private final String select_all1 = """
    SELECT * FROM SanPham
""";
    private final String update_sql = """
        UPDATE SanPham
        SET TenSP = ?, MoTa = ?
        WHERE MaSP = ?
    """;
    private final String delete_sql = """
        DELETE FROM SanPham
        WHERE MaSP = ?
    """;

    String selectById = """
                    SELECT [TenSP]
                           ,[MoTa]
                    FROM SanPham 
                    WHERE MaSP = ?
                    """;
//////////////////////////////////////////////////
    private final String select_all11 = """
    SELECT * FROM SanPhamChiTiet
""";
    private final String update_sql1 = """
        UPDATE SanPhamChiTiet
        SET MaSP = ?, MauSac = ?, ChatLieu = ?, SoLuong = ?, Gia = ?, SALE = ?
        WHERE MaSPCT = ?
    """;
    private final String delete_sql1 = """
        DELETE FROM SanPhamChiTiet
        WHERE MaSPCT = ?
    """;
    String insert_sql1 = """
                        INSERT INTO [dbo].[SanPhamChiTiet]
                                              ([MaSPCT]
                                                ,[MaSP]
                                              ,[MauSac]
                                              ,[ChatLieu]
                                              ,[SoLuong]
                                              ,[Gia]
                                              ,[SALE])
                             VALUES
                                   (?,?,?,?,?,?,?)
                        """;

    String selectById1 = """
                    SELECT [MaSP]
                           ,[MauSac]
                           ,[ChatLieu]
                           ,[SoLuong]
                           ,[Gia]
                           ,[SALE]
                    FROM SanPhamChiTiet 
                    WHERE MaSPCT = ?
                    """;

    String insert_sql = """
                        INSERT INTO [dbo].[SanPham]
                                              ([MaSP]
                                              ,[TenSP]
                                              ,[SIZE]
                                              ,[MoTa])
                             VALUES
                                   (?,?,?,?)
                        """;

    public void insert(SanPham entity) {
        Getconnection.update(insert_sql,
                entity.getMaSp(), entity.getTenSp(),
                entity.getSize(), entity.getMoTa());
    }

    public List<SanPham> selectAll() {
        return this.selectBySql(select_all1);
    }

    public void update(SanPham entity) {
        Getconnection.update(update_sql,
                entity.getTenSp(), entity.getSize(), entity.getMoTa(), entity.getMaSp());
    }

    public void delete(String id) {
        Getconnection.update(delete_sql, id);
    }

    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            var rs = Getconnection.query(sql, args);
            while (rs.next()) {
                var hv = new SanPham();
                hv.setMaSp(rs.getString("MaSP"));
                hv.setTenSp(rs.getString("TenSP"));
                hv.setSize(rs.getString("SIZE"));
                hv.setMoTa(rs.getString("MoTa"));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPham selectById(String id) {
        List<SanPham> list = this.selectBySql(selectById, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public List<SanPham> findProductByMaSP(String maSP) {
        String sql = "SELECT * FROM SanPham WHERE MaSP = ?";
        return selectBySql(sql, maSP);
    }
////////////////////////////////////////////////////////////////////////////////

    public void insert1(SanPham entity) {
        Getconnection.update(insert_sql1,
                entity.getMaSPCT(), entity.getMaSp(),
                entity.getMauSac(), entity.getChatLieu(),
                entity.getSoLuong(), entity.getGia(), entity.getSale());
    }

    public List<SanPham> selectAll1() {
        return this.selectBySql1(select_all11);
    }

    public void update1(SanPham entity) {
        Getconnection.update(update_sql1,
                entity.getMaSp(),
                entity.getMauSac(), entity.getChatLieu(),
                entity.getSoLuong(), entity.getGia(), entity.getSale(), entity.getMaSPCT());
    }

    public void delete1(String id) {
        Getconnection.update(delete_sql1, id);
    }

    protected List<SanPham> selectBySql1(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            var rs = Getconnection.query(sql, args);
            while (rs.next()) {
                var hv = new SanPham();
                hv.setMaSPCT(rs.getString("MaSPCT"));
                hv.setMaSp(rs.getString("MaSP"));
                hv.setMauSac(rs.getString("MauSac"));
                hv.setChatLieu(rs.getString("ChatLieu"));
                hv.setSoLuong(rs.getInt("SoLuong"));
                hv.setGia(rs.getInt("Gia"));
                hv.setSale(rs.getInt("SALE"));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPham selectById1(String id) {
        List<SanPham> list = this.selectBySql1(selectById1, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public List<SanPham> findProductByMaSP1(String maSPCT) {
        String sql = "SELECT * FROM SanPhamChiTiet WHERE MaSPCT = ? OR MaSP = ?";
        return selectBySql1(sql, maSPCT, maSPCT);
    }
//////////////////////////////////////////////////////

    private final String select_by_maSP = """
        SELECT * FROM GioHang_View WHERE MaSP = ?
        """;

    public SanPham getItemDetails(String maSP) {
        List<SanPham> list = selectBySqlGioHangView(select_by_maSP, maSP);
        if (!list.isEmpty()) {
            return list.get(0); // Trả về sản phẩm đầu tiên trong danh sách (nếu có)
        }
        return null; // Trả về null nếu không tìm thấy sản phẩm
    }

    protected List<SanPham> selectBySqlGioHangView(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            var rs = Getconnection.query(sql, args);
            while (rs.next()) {
                var item = new SanPham();
                item.setMaSp(rs.getString("MaSP"));
                item.setTenSp(rs.getString("TenSP"));
                item.setSize(rs.getString("SIZE"));
                item.setSoLuong(rs.getInt("SoLuong"));
                item.setGia(rs.getInt("Gia"));
                item.setSale(rs.getInt("GiamGia"));
                item.setThanhTien(rs.getInt("ThanhTien"));
                list.add(item);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPham getGiaByMaSP(String maSP) {
        SanPham giaSanPham = new SanPham(); // Tạo đối tượng GiaSanPham để lưu thông tin

        try {
            Connection connection = Getconnection.getConnection(); // Lấy kết nối đến cơ sở dữ liệu của bạn

            // Chuẩn bị truy vấn SQL để lấy thông tin từ bảng sản phẩm dựa trên mã sản phẩm
            String query = "SELECT Gia, MaSIZE, GiamGia FROM GioHang_View WHERE MaSP = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, maSP);

            // Thực thi truy vấn và lấy kết quả
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                giaSanPham.setGia(rs.getInt("Gia"));
                giaSanPham.setSize(rs.getString("MaSIZE"));
                giaSanPham.setGiamGia(rs.getInt("GiamGia"));
            }

            // Đóng các đSối tượng ResultSet, PreparedStatement và kết nối
            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý hoặc báo lỗi nếu có vấn đề trong quá trình truy vấn
        }

        return giaSanPham; // Trả về đối tượng GiaSanPham chứa thông tin về giá, size và giảm giá của sản phẩm
    }
////////////////////////////////////////  JPANEL hoadon

    public void updateSoLuong(String maSP, int newQuantity) {
        try {
            String update_sqlSP = "UPDATE SanPhamChiTiet SET SoLuong = ? WHERE MaSP = ?";
            PreparedStatement ps = Getconnection.getConnection().prepareStatement(update_sqlSP);
            ps.setInt(1, newQuantity);
            ps.setString(2, maSP);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi cập nhật số lượng sản phẩm
        }
    }

    public int getSoLuongByMaSP(String maSP) {
        int soLuong = 0;
        try {
            String query = "SELECT SoLuong FROM SanPhamChiTiet WHERE MaSP = ?";
            PreparedStatement ps = Getconnection.getConnection().prepareStatement(query);
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi truy vấn dữ liệu số lượng sản phẩm
        }
        return soLuong;
    }
///////////////////////////////////////////////////////////////////////////

    public List<SanPham> laySanPhamTheoMaHD(int maHD) {
        List<SanPham> productList = new ArrayList<>();
        try {
            // Prepare SQL query to fetch products based on Invoice ID from HoaDonChiTiet table
            String query = "SELECT sp.MaSP, sp.TenSP, hdct.SoLuong, spct.Gia, km.GiamGia, hdct.GiaTien \n"
                    + "FROM SanPham sp\n"
                    + "INNER JOIN HoaDonChiTiet hdct ON sp.MaSP = hdct.MaSP\n"
                    + "INNER JOIN SanPhamChiTiet spct ON spct.MaSP = sp.MaSP\n"
                    + "LEFT JOIN KhuyenMai km ON spct.KhuyenMai = km.MaKM\n"
                    + "WHERE hdct.MaHD = ?;";

            // Get a connection and execute the query
            PreparedStatement pstmt = Getconnection.getConnection().prepareStatement(query);
            pstmt.setInt(1, maHD);
            ResultSet rs = pstmt.executeQuery();

            // Process the results and populate the productList
            while (rs.next()) {
                SanPham product = new SanPham();
                product.setMaSp(rs.getString("MaSP"));
                product.setTenSp(rs.getString("TenSP"));
                product.setSoLuong(rs.getInt("SoLuong"));
                product.setGia(rs.getInt("Gia"));
                product.setSale(rs.getInt("GiamGia"));
                product.setThanhTien(rs.getInt("GiaTien"));
                productList.add(product);
            }

            // Close resources
            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions or errors if any occur during the process
        }

        return productList;
    }
////////////////////////////////////// TÌM SẢN PHẨM BÊN BÁN HÀNG ///////////////////////////////////////////////

    public List<SanPham> searchProductsByKeywordSP(String keyword) {
        List<SanPham> filteredProducts = new ArrayList<>();
        try {
            Connection connection = Getconnection.getConnection();
            String query = "SELECT sp.MaSP, sp.TenSP, ms.TenMau AS MauSac, cl.TenChatLieu AS ChatLieu, s.MaSIZE AS Size, spct.SoLuong, spct.Gia "
                    + "FROM SanPham sp "
                    + "INNER JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP "
                    + "INNER JOIN MauSac ms ON spct.MauSac = ms.ID "
                    + "INNER JOIN ChatLieu cl ON spct.ChatLieu = cl.ID "
                    + "INNER JOIN SIZE s ON spct.Size = s.ID "
                    + "WHERE sp.TenSP LIKE ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSp(rs.getString("MaSP"));
                sanPham.setTenSp(rs.getString("TenSP"));
                sanPham.setMauSac(rs.getString("MauSac"));
                sanPham.setChatLieu(rs.getString("ChatLieu"));
                sanPham.setSize(rs.getString("Size"));
                sanPham.setSoLuong(rs.getInt("SoLuong"));
                sanPham.setGia(rs.getInt("Gia"));
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

    /////////////////////////// THUỘC TÍNH SẢN PHẨM //////////////////////////////////
    // Màu sắc 
    public List<SanPham> selectAllMauSac() {
        List<SanPham> mauSacList = new ArrayList<>();
        String query = "SELECT ID, TenMau FROM MauSac";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SanPham mauSac = new SanPham();
                mauSac.setID(rs.getInt("ID"));
                mauSac.setTenMau(rs.getString("TenMau"));
                mauSacList.add(mauSac);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mauSacList;
    }

    public void insertIntoMauSac(SanPham entity) {
        String tenMau = entity.getTenMau();
        if (tenMau != null && !tenMau.isEmpty()) {
            String query = "INSERT INTO MauSac (TenMau) VALUES (?)";

            try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setString(1, tenMau);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Giá trị TenMau không hợp lệ");
        }
    }

    public void updateMauSac(SanPham entity) {
        String query = "UPDATE MauSac SET TenMau = ? WHERE ID = ?";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, entity.getTenMau());
            pstmt.setInt(2, entity.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // chất liệu
    public List<SanPham> selectAllChatLieu() {
        List<SanPham> chatLieuList = new ArrayList<>();
        String query = "SELECT ID, TenChatLieu FROM ChatLieu";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SanPham chatLieu = new SanPham();
                chatLieu.setID(rs.getInt("ID"));
                chatLieu.setTenChatLieu(rs.getString("TenChatLieu"));
                chatLieuList.add(chatLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatLieuList;
    }

    public void insertIntoChatLieu(SanPham entity) {
        String query = "INSERT INTO ChatLieu (TenChatLieu) VALUES (?)";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, entity.getTenChatLieu());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateChatLieu(SanPham entity) {
        String query = "UPDATE ChatLieu SET TenChatLieu = ? WHERE ID = ?";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, entity.getTenChatLieu());
            pstmt.setInt(2, entity.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /// SIZE
    public List<SanPham> selectAllSIZE() {
        List<SanPham> sizeList = new ArrayList<>();
        String query = "SELECT ID, MaSIZE FROM SIZE";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SanPham size = new SanPham();
                size.setID(rs.getInt("ID"));
                size.setTenSize(rs.getString("MaSIZE"));
                sizeList.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sizeList;
    }

    public void insertIntoSIZE(SanPham entity) {
        String tenSize = entity.getTenSize();
        if (tenSize != null && !tenSize.isEmpty()) {
            String query = "INSERT INTO SIZE (MaSIZE) VALUES (?)";

            try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setString(1, tenSize);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Giá trị MaSIZE không hợp lệ");
        }
    }

    public void updateSIZE(SanPham entity) {
        String query = "UPDATE SIZE SET MaSIZE = ? WHERE ID = ?";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, entity.getTenSize());
            pstmt.setInt(2, entity.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /// LOAI SP
    public List<SanPham> selectAllLOAI() {
        List<SanPham> loaiList = new ArrayList<>();
        String query = "SELECT ID, TenLoai FROM LOAI";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SanPham loai = new SanPham();
                loai.setID(rs.getInt("ID"));
                loai.setTenLoai(rs.getString("TenLoai"));
                loaiList.add(loai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loaiList;
    }

    public void insertIntoLOAI(SanPham entity) {
        String tenLoai = entity.getTenLoai();
        if (tenLoai != null && !tenLoai.isEmpty()) {
            String query = "INSERT INTO LOAI (TenLoai) VALUES (?)";

            try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setString(1, tenLoai);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Giá trị TenLoai không hợp lệ");
        }
    }

    public void updateLOAI(SanPham entity) {
        String query = "UPDATE LOAI SET TenLoai = ? WHERE ID = ?";

        try ( Connection connection = Getconnection.getConnection();  PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, entity.getTenLoai());
            pstmt.setInt(2, entity.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SanPham> selectAllCTSPDT() {
        List<SanPham> sanPhams = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            String selectAllQuery = "SELECT sp.MaSP, sp.TenSP, l.TenLoai AS Loai, "
                    + "ms.TenMau AS MauSac, "
                    + "s.MaSize AS Size, cl.TenChatLieu AS ChatLieu,spct.SoLuong "
                    + "FROM SanPham sp "
                    + "INNER JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP "
                    + "INNER JOIN MauSac ms ON spct.MauSac = ms.ID "
                    + "INNER JOIN ChatLieu cl ON spct.ChatLieu = cl.ID "
                    + "INNER JOIN SIZE s ON spct.Size = s.ID "
                    + "INNER JOIN LOAI l ON spct.Loai = l.ID";

            PreparedStatement pstmt = connection.prepareStatement(selectAllQuery);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSp(rs.getString("MaSP"));
                sanPham.setTenSp(rs.getString("TenSP"));
                sanPham.setLoai(rs.getString("Loai"));
                sanPham.setMauSac(rs.getString("MauSac"));
                sanPham.setSize(rs.getString("Size"));
                sanPham.setChatLieu(rs.getString("ChatLieu"));
                sanPham.setSoLuong(rs.getInt("SoLuong"));

                sanPhams.add(sanPham);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return sanPhams;
    }
//////////////////////////////////////////////////////////////////////////////////

    public List<SanPham> selectAll13(String loai) {
        List<SanPham> sanPhams = new ArrayList<>();

        try {
            Connection connection = Getconnection.getConnection();
            String selectAllQuery = "SELECT sp.MaSP, sp.TenSP, l.TenLoai AS Loai , spct.Gia, spct.SoLuong, "
                    + "ms.TenMau AS MauSac, "
                    + "s.MaSize AS Size, cl.TenChatLieu AS ChatLieu "
                    + "FROM SanPham sp "
                    + "INNER JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP "
                    + "INNER JOIN MauSac ms ON spct.MauSac = ms.ID "
                    + "INNER JOIN ChatLieu cl ON spct.ChatLieu = cl.ID "
                    + "INNER JOIN SIZE s ON spct.Size = s.ID "
                    + "INNER JOIN LOAI l ON spct.Loai = l.ID "
                    + "WHERE l.TenLoai = ?"; // Sửa điều kiện WHERE để lọc theo loại sản phẩm

            PreparedStatement pstmt = connection.prepareStatement(selectAllQuery);
            pstmt.setString(1, loai); // Thiết lập giá trị cho tham số loại

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSp(rs.getString("MaSP"));
                sanPham.setTenSp(rs.getString("TenSP"));
                sanPham.setLoai(rs.getString("Loai"));
                sanPham.setGia(rs.getInt("Gia"));
                sanPham.setSoLuong(rs.getInt("SoLuong"));
                sanPham.setMauSac(rs.getString("MauSac"));
                sanPham.setSize(rs.getString("Size"));
                sanPham.setChatLieu(rs.getString("ChatLieu"));

                sanPhams.add(sanPham);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return sanPhams;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateSanPhamByMaKM(int loai, int maKM) {
        String updateQuery = "UPDATE SanPhamChiTiet SET KhuyenMai = ? WHERE Loai = ?";
        try ( Connection connection = Getconnection.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setInt(2, loai);
            preparedStatement.setInt(1, maKM);

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

    public int getLoaiID(String tenLoai) {
        int loaiID = -1; // Giá trị mặc định khi không tìm thấy

        try {
            Connection connection = Getconnection.getConnection();
            String query = "SELECT ID FROM Loai WHERE TenLoai = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, tenLoai);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                loaiID = rs.getInt("ID");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ hoặc thông báo lỗi nếu cần
        }

        return loaiID;
    }

}
