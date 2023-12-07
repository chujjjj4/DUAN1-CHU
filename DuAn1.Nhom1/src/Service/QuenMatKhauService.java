/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Repository.Getconnection;
import Repository.RandomSNN;
import Repository.XEmail;
import java.sql.*;

/**
 *
 * @author hoanh
 */
public class QuenMatKhauService {
    RandomSNN random = new RandomSNN();
    
    public boolean checkuser(String email){
        try {
            String SQL="select * from nhanvien where email like ?";
            Connection conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL);
            
            sttm.setObject(1, email);
            ResultSet rs = sttm.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count ==1){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateMK(String email){
        int check=0;
        String SQL_doimk ="""
                      update TaiKhoan set MatKhau =? from TaiKhoan join NhanVien on 
                            TaiKhoan.MaNV= NhanVien.MaNV where NhanVien.Email = ?""";
        try {
            Connection conn = Getconnection.getConnection();
            PreparedStatement sttm = conn.prepareStatement(SQL_doimk);
            String Password = random.soNgauNhienString(6);
            
            sttm.setObject(1, Password);
            sttm.setObject(2, email);
            
            XEmail em = new XEmail();
            em.sendMail(email, Password);
            
            check = sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check>0;
    }
    
    
}
