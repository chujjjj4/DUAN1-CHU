package Service;

import Model.NhanVien;
import Repository.Getconnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienService {

    private final String select_all1 = """
    SELECT * FROM NhanVien
""";
    private final String update_sql = """
        UPDATE NhanVien
        SET  HoTen = ?, NgaySinh = ?, DiaChi = ?, Email = ?
        WHERE MaNV = ?
    """;
    private final String delete_sql = """
        DELETE FROM NhanVien
        WHERE MaNV = ?
    """;
    String insert_sql = """
                        INSERT INTO [dbo].[NhanVien]
                                              ([MaNV]
                                              ,[HoTen]
                                              ,[NgaySinh],[DiaChi],[Email])
                             VALUES
                                   (?,?,?,?,?)
                        """;

    String selectById = """
                    SELECT [MaNV]
                           ,[[HoTen]
                       ,[NgaySinh],[DiaChi],[Email]
                    FROM NhanVien 
                    WHERE MaNV = ?
                    """;

    public void inserttk(NhanVien entity) {
        Getconnection.update(insert_sql,
                entity.getMaNV(),
                entity.getHoTen(), entity.getNgaySinh(), entity.getDiaChi(), entity.getEmail());
    }

    public List<NhanVien> selectAlltk() {
        return this.selectBySqltk(select_all1);
    }

    public void updatetk(NhanVien entity) {
        Getconnection.update(update_sql,
                entity.getHoTen(), entity.getNgaySinh(), entity.getDiaChi(), entity.getEmail(),
                entity.getMaNV());
    }

    public void deletetk(String id) {
        Getconnection.update(delete_sql, id);
    }

    protected List<NhanVien> selectBySqltk(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = Getconnection.query(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public NhanVien selectByIdtk(String id) {
        List<NhanVien> list = this.selectBySqltk(selectById, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public List<NhanVien> findProductByMatk(String maSP) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
        return selectBySqltk(sql, maSP);
    }
}
