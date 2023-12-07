package View;

import Model.HoaDon;
import Model.NhanVien;
import Model.SanPham;
import Model.TaiKhoan;
import Model.Vourcher;
import Repository.Auth;
import Repository.MsgBox;
import Repository.XDate;
import Service.HoaDonService;
import Service.NhanVienService;
import Service.SanPhamService;
import Service.TaiKhoanService;
import Service.VoucherService;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import Model.ThongKe;
import Service.ThongKeService;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;

public class BanAoSoMi extends javax.swing.JFrame implements Runnable, ThreadFactory {

    SanPhamService dao = new SanPhamService();
    TaiKhoanService tk = new TaiKhoanService();
    HoaDonService hd = new HoaDonService();
    NhanVienService nv = new NhanVienService();
    ThongKeService doanhThu = new ThongKeService();
    VoucherService km = new VoucherService();
    private JTable selectedTable;

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private final Executor executor = Executors.newSingleThreadExecutor(this);

    public BanAoSoMi() {
        initComponents();
        if (!Auth.isManager()) {
            DOANHTHU.removeTabAt(0); // 0 là vị trí của tab "Danh sách"
            mnVC.setVisible(false); // Ẩn JPanel "voucher"
            mnTKhoan1.setVisible(false); // Ẩn JPanel "voucher"
            mnNV.setVisible(false); // Ẩn JPanel "voucher"
        }
        LoadTable();
        this.setTitle("7Clothes");

        URL urlIconLogo = BanAoSoMi.class.getClassLoader().getResource("Image/Logo.jpg");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconLogo);
        this.setIconImage(img);
        initHD();
        txtNgayLap.setText(XDate.toString(XDate.now(), "dd-MM-yyyy"));
        txtMaNV.setEditable(false);
        moDialogDangNhap();
        //////////////////// HINH THUC THANH TOAN 
        cboHinhThucThanhToan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = cboHinhThucThanhToan.getSelectedItem().toString();
                    // Kiểm tra lựa chọn và vô hiệu hóa nhập liệu vào txtTienKhachDua khi chọn "Chuyển khoản" hoặc "Quẹt thẻ"
                    if (selectedOption.equals("Chuyển khoản") || selectedOption.equals("Quẹt thẻ")) {
                        txtTienKhachDua.setEditable(false);
                        txtTienKhachDua.setText(lblTongTien.getText());
                    } else {
                        txtTienKhachDua.setEditable(true);
                    }
                }
            }
        });
        /////////////////////////////
        loadComboBoxData();
        init();
        rdoBangCTDT.setSelected(true);
        rdoBangCTDT.setVisible(false);
        rdoBieuDoCTDT.setVisible(false);
        rdoHD.setSelected(true);
        rdoNV.setSelected(true);
        jLabel48.setVisible(false);
        updatekmHETHAN();
        btnAn.setVisible(false);
        jButton2.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        container = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mnTK = new javax.swing.JLabel();
        mnSP = new javax.swing.JLabel();
        mnHD = new javax.swing.JLabel();
        mnVC = new javax.swing.JLabel();
        mnTKhoan = new javax.swing.JLabel();
        mnThoat = new javax.swing.JLabel();
        mnLS = new javax.swing.JLabel();
        mnNV = new javax.swing.JLabel();
        mnTKhoan1 = new javax.swing.JLabel();
        home = new javax.swing.JPanel();
        thongke = new javax.swing.JPanel();
        DOANHTHU = new javax.swing.JTabbedPane();
        doanhTHU = new javax.swing.JPanel();
        BANG = new javax.swing.JPanel();
        bang = new javax.swing.JScrollPane();
        tblChiTietDoanhThu = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        cbbNamCTDT = new javax.swing.JComboBox<>();
        rdoBangCTDT = new javax.swing.JRadioButton();
        rdoBieuDoCTDT = new javax.swing.JRadioButton();
        jLabel48 = new javax.swing.JLabel();
        bieudo = new javax.swing.JPanel();
        bd = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblChiTietSanPhamDoanhThu = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        sanpham = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        txtMaCT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        cbbLoaiSP = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cbbChatLieuj = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblThongTinSP = new javax.swing.JTable();
        txtTim1 = new javax.swing.JTextField();
        btnSua1 = new javax.swing.JButton();
        btnThem1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnAn = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtThemThuocTinh = new javax.swing.JTextField();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        rdoTenLoaiSP = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        btnSuaTTSP = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        mausac = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        kichthuoc = new javax.swing.JScrollPane();
        tblKichThuoc = new javax.swing.JTable();
        chatlieu = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        tenloaisanpham = new javax.swing.JScrollPane();
        tblTenLoaiSanPham = new javax.swing.JTable();
        banhang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachHoaDon = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDanhSachSP = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTienTraLai = new javax.swing.JLabel();
        lblTienTraKhach = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtGhichu = new javax.swing.JTextArea();
        txtMaHD = new javax.swing.JTextField();
        txtTimkiem = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        panelCam = new javax.swing.JPanel();
        voucher = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        jPanel21 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtMAKM = new javax.swing.JTextField();
        txtTENKM = new javax.swing.JTextField();
        txtGIAMGIA = new javax.swing.JTextField();
        rdoHD = new javax.swing.JRadioButton();
        rdoHHD = new javax.swing.JRadioButton();
        dateNgayBD = new com.toedter.calendar.JDateChooser();
        dateNgayKT = new com.toedter.calendar.JDateChooser();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblDanhSachSanPhamKhuyenMai = new javax.swing.JTable();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblDanhSachKhuyenMai = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        jPanel22 = new javax.swing.JPanel();
        txtTimSP = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        cbbLoaiSanPham = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtTIMKM = new javax.swing.JTextField();
        jScrollPane23 = new javax.swing.JScrollPane();
        jPanel23 = new javax.swing.JPanel();
        txtLOAISP = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtMAKHUYENMAI = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtGIAGIAMKHUYENMAI = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        txtMALOAI = new javax.swing.JTextField();
        taikhoan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtMaTk = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtThemTaiK = new javax.swing.JButton();
        txtSuaTaiK = new javax.swing.JButton();
        txtXoaTaiK = new javax.swing.JButton();
        txtTimTaiK = new javax.swing.JButton();
        txtTimTK = new javax.swing.JTextField();
        rdoNV = new javax.swing.JRadioButton();
        rdoQL = new javax.swing.JRadioButton();
        txtLoadTaiK = new javax.swing.JButton();
        nhanvien = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMNV = new javax.swing.JTextField();
        txtHOTEN = new javax.swing.JTextField();
        txtNGAYSINH = new javax.swing.JTextField();
        txtDIACHI = new javax.swing.JTextField();
        txtEMAIL = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtTIMNV = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        lichsu = new javax.swing.JPanel();
        tblLichS = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblSanPhamLichSu = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtMaNVLS = new javax.swing.JTextField();
        txtTenKHLS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGiaTienLS = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtNgayLapLS = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTrangThaiLS = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtGhiChuLS = new javax.swing.JTextArea();
        txtHinhThucTTLS = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btnInHoaDon = new javax.swing.JButton();
        txtTimKhachHang = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        lblDTNGAY = new javax.swing.JLabel();
        lblHOADONNgay = new javax.swing.JLabel();
        lblSOHANGngay = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        lblDTNam = new javax.swing.JLabel();
        lblHOADONNAM = new javax.swing.JLabel();
        lblSOHANGNAM = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        lblDTThang = new javax.swing.JLabel();
        lblHOADONTHANG = new javax.swing.JLabel();
        lblSOHANGTHANG = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cbbLOAITHOIGIAN = new javax.swing.JComboBox<>();
        ngaybatdau = new com.toedter.calendar.JDateChooser();
        ngayketthuc = new com.toedter.calendar.JDateChooser();
        btnTIMLOAITIME = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm "));

        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Màu sắc ", "Chất liệu", "SIZE", "Số lượng ", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblDSSP);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        container.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Logo.jpg"))); // NOI18N

        mnTK.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnTK.setText("             Thống kê");
        mnTK.setToolTipText("");
        mnTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnTKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnTKMouseEntered(evt);
            }
        });

        mnSP.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnSP.setText("            Sản phẩm");
        mnSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnSPMouseClicked(evt);
            }
        });

        mnHD.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnHD.setText("             Bán hàng");
        mnHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnHDMouseClicked(evt);
            }
        });

        mnVC.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnVC.setText("          Khuyến mại ");
        mnVC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnVCMouseClicked(evt);
            }
        });

        mnTKhoan.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnTKhoan.setText("          Đổi mật khẩu");
        mnTKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnTKhoanMouseClicked(evt);
            }
        });

        mnThoat.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnThoat.setText("            Đăng xuất");
        mnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnThoatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnThoatMouseEntered(evt);
            }
        });

        mnLS.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnLS.setText("           Lịch sử bán ");
        mnLS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnLSMouseClicked(evt);
            }
        });

        mnNV.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnNV.setText("            Nhân viên ");
        mnNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnNVMouseClicked(evt);
            }
        });

        mnTKhoan1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        mnTKhoan1.setText("            Tài khoản");
        mnTKhoan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnTKhoan1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mnTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnVC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnTKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnTKhoan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnThoat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mnLS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(33, 33, 33))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(mnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnHD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnLS, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnVC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnTKhoan1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnTKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        home.setLayout(new java.awt.CardLayout());

        BANG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblChiTietDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tháng", "Số sản phẩm bán", "Tổng giá bán", "Tổng giá giảm giá", "Doanh thu "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bang.setViewportView(tblChiTietDoanhThu);
        if (tblChiTietDoanhThu.getColumnModel().getColumnCount() > 0) {
            tblChiTietDoanhThu.getColumnModel().getColumn(0).setResizable(false);
            tblChiTietDoanhThu.getColumnModel().getColumn(1).setResizable(false);
            tblChiTietDoanhThu.getColumnModel().getColumn(2).setResizable(false);
            tblChiTietDoanhThu.getColumnModel().getColumn(3).setResizable(false);
            tblChiTietDoanhThu.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setText("Chi tiết doanh thu ");

        javax.swing.GroupLayout BANGLayout = new javax.swing.GroupLayout(BANG);
        BANG.setLayout(BANGLayout);
        BANGLayout.setHorizontalGroup(
            BANGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bang)
            .addGroup(BANGLayout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(jLabel41)
                .addContainerGap(305, Short.MAX_VALUE))
        );
        BANGLayout.setVerticalGroup(
            BANGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BANGLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(bang, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setText("Năm ");

        cbbNamCTDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023" }));
        cbbNamCTDT.setBorder(null);

        buttonGroup3.add(rdoBangCTDT);
        rdoBangCTDT.setText("Bảng");
        rdoBangCTDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoBangCTDTActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoBieuDoCTDT);
        rdoBieuDoCTDT.setText("Biểu đồ");
        rdoBieuDoCTDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoBieuDoCTDTActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setText("Lựa chọn hiện thị ");

        bd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout bdLayout = new javax.swing.GroupLayout(bd);
        bd.setLayout(bdLayout);
        bdLayout.setHorizontalGroup(
            bdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        bdLayout.setVerticalGroup(
            bdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bieudoLayout = new javax.swing.GroupLayout(bieudo);
        bieudo.setLayout(bieudoLayout);
        bieudoLayout.setHorizontalGroup(
            bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
            .addGroup(bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bieudoLayout.setVerticalGroup(
            bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
            .addGroup(bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout doanhTHULayout = new javax.swing.GroupLayout(doanhTHU);
        doanhTHU.setLayout(doanhTHULayout);
        doanhTHULayout.setHorizontalGroup(
            doanhTHULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, doanhTHULayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(doanhTHULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(cbbNamCTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoBieuDoCTDT)
                    .addGroup(doanhTHULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoBangCTDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(BANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(doanhTHULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, doanhTHULayout.createSequentialGroup()
                    .addGap(0, 261, Short.MAX_VALUE)
                    .addComponent(bieudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        doanhTHULayout.setVerticalGroup(
            doanhTHULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BANG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(doanhTHULayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbNamCTDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoBangCTDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoBieuDoCTDT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(doanhTHULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bieudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DOANHTHU.addTab("Doanh thu", doanhTHU);

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblChiTietSanPhamDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Loại SP", "Màu ", "Kích thước", "Chất liệu", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(tblChiTietSanPhamDoanhThu);
        if (tblChiTietSanPhamDoanhThu.getColumnModel().getColumnCount() > 0) {
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(0).setResizable(false);
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(1).setResizable(false);
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(2).setResizable(false);
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(3).setResizable(false);
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(4).setResizable(false);
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(5).setResizable(false);
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(6).setResizable(false);
            tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel47.setText("Chi tiết thông tin sản phẩm ");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jLabel47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnIn.setText("In Doanh thu ngày ");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(106, 106, 106)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnIn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DOANHTHU.addTab("Sản phẩm", jPanel16);

        javax.swing.GroupLayout thongkeLayout = new javax.swing.GroupLayout(thongke);
        thongke.setLayout(thongkeLayout);
        thongkeLayout.setHorizontalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DOANHTHU)
        );
        thongkeLayout.setVerticalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DOANHTHU, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        home.add(thongke, "card2");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý sản phẩm"));

        jLabel11.setText("Mã sản phẩm");

        jLabel17.setText("Tên sản phẩm");

        cbbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel32.setText("Loại sản phẩm");

        jLabel33.setText("Đơn giá");

        jLabel34.setText("Số lượng");

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        jLabel35.setText("Màu sắc");

        cbbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKichThuocActionPerformed(evt);
            }
        });

        jLabel36.setText("Kích thước ");

        jLabel37.setText("Chất liệu");

        cbbChatLieuj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChatLieuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChatLieujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel11))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaCT)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbLoaiSP, 0, 283, Short.MAX_VALUE)
                            .addComponent(txtDonGia))))
                .addGap(69, 69, 69)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbChatLieuj, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(28, 28, 28)
                        .addComponent(cbbKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35))
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36))
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbChatLieuj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37))
                    .addComponent(jLabel33))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel8);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tblThongTinSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên SP", "Loại SP", "Đơn giá", "Số lượng", "Màu sắc", "Kích thước", "Chất liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinSPMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblThongTinSP);
        if (tblThongTinSP.getColumnModel().getColumnCount() > 0) {
            tblThongTinSP.getColumnModel().getColumn(0).setResizable(false);
            tblThongTinSP.getColumnModel().getColumn(1).setResizable(false);
            tblThongTinSP.getColumnModel().getColumn(2).setResizable(false);
            tblThongTinSP.getColumnModel().getColumn(3).setResizable(false);
            tblThongTinSP.getColumnModel().getColumn(4).setResizable(false);
            tblThongTinSP.getColumnModel().getColumn(5).setResizable(false);
            tblThongTinSP.getColumnModel().getColumn(6).setResizable(false);
            tblThongTinSP.getColumnModel().getColumn(7).setResizable(false);
        }

        txtTim1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTim1MouseClicked(evt);
            }
        });
        txtTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTim1ActionPerformed(evt);
            }
        });
        txtTim1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTim1KeyReleased(evt);
            }
        });

        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Tìm sản phẩm");

        btnAn.setText("Ẩn");
        btnAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTim1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186)
                .addComponent(btnThem1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAn, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addGap(116, 116, 116))
            .addComponent(jScrollPane7)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem1)
                        .addComponent(btnSua1)
                        .addComponent(btnAn))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTim1)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel9);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chi tiết sản phẩm", jPanel3);

        jScrollPane13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thuộc tính sản phẩm"));

        jLabel2.setText("Tên thuộc tính ");

        buttonGroup2.add(rdoMauSac);
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoKichThuoc);
        rdoKichThuoc.setText("Kích thước ");
        rdoKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKichThuocActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoChatLieu);
        rdoChatLieu.setText("Chất liệu");
        rdoChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatLieuActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoTenLoaiSP);
        rdoTenLoaiSP.setText("Tên loại sản phẩm");
        rdoTenLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTenLoaiSPActionPerformed(evt);
            }
        });

        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnSuaTTSP.setText("Sửa");
        btnSuaTTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTSPActionPerformed(evt);
            }
        });

        txtID.setEnabled(false);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaTTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(698, 698, 698))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(rdoMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoKichThuoc)
                        .addGap(18, 18, 18)
                        .addComponent(rdoTenLoaiSP)))
                .addGap(70, 70, 70))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdoKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdoChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdoTenLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaTTSP)
                            .addComponent(jButton4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane13.setViewportView(jPanel2);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Loại thuộc tính ", "Tên thuộc tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        mausac.setViewportView(tblMauSac);
        if (tblMauSac.getColumnModel().getColumnCount() > 0) {
            tblMauSac.getColumnModel().getColumn(0).setResizable(false);
            tblMauSac.getColumnModel().getColumn(1).setResizable(false);
            tblMauSac.getColumnModel().getColumn(2).setResizable(false);
        }

        tblKichThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Loại thuộc tính", "Tên thuộc tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocMouseClicked(evt);
            }
        });
        kichthuoc.setViewportView(tblKichThuoc);
        if (tblKichThuoc.getColumnModel().getColumnCount() > 0) {
            tblKichThuoc.getColumnModel().getColumn(0).setResizable(false);
            tblKichThuoc.getColumnModel().getColumn(1).setResizable(false);
            tblKichThuoc.getColumnModel().getColumn(2).setResizable(false);
        }

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Loại thuộc tính", "Tên thuộc tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        chatlieu.setViewportView(tblChatLieu);
        if (tblChatLieu.getColumnModel().getColumnCount() > 0) {
            tblChatLieu.getColumnModel().getColumn(0).setResizable(false);
            tblChatLieu.getColumnModel().getColumn(1).setResizable(false);
            tblChatLieu.getColumnModel().getColumn(2).setResizable(false);
        }

        tblTenLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Loại thuộc tính", "Tên thuộc tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTenLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTenLoaiSanPhamMouseClicked(evt);
            }
        });
        tenloaisanpham.setViewportView(tblTenLoaiSanPham);
        if (tblTenLoaiSanPham.getColumnModel().getColumnCount() > 0) {
            tblTenLoaiSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblTenLoaiSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblTenLoaiSanPham.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mausac, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(kichthuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(chatlieu, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tenloaisanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mausac, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(kichthuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(chatlieu, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tenloaisanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính sản phẩm ", jPanel7);

        javax.swing.GroupLayout sanphamLayout = new javax.swing.GroupLayout(sanpham);
        sanpham.setLayout(sanphamLayout);
        sanphamLayout.setHorizontalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        sanphamLayout.setVerticalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Sản phẩm");

        home.add(sanpham, "card3");

        banhang.setPreferredSize(new java.awt.Dimension(1012, 450));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        tblDanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Ngày Lập", "Mã NV", "Trạng thái"
            }
        ));
        tblDanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachHoaDon);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng "));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Size", "Số Lượng", "Tiền", "Giảm giá", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblGioHang);
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(0).setResizable(false);
            tblGioHang.getColumnModel().getColumn(1).setResizable(false);
            tblGioHang.getColumnModel().getColumn(2).setResizable(false);
            tblGioHang.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblGioHang.getColumnModel().getColumn(3).setResizable(false);
            tblGioHang.getColumnModel().getColumn(4).setResizable(false);
            tblGioHang.getColumnModel().getColumn(5).setResizable(false);
            tblGioHang.getColumnModel().getColumn(6).setResizable(false);
        }

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm "));

        tblDanhSachSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Màu sắc ", "Chất liệu", "SIZE", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSPMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblDanhSachSP);
        if (tblDanhSachSP.getColumnModel().getColumnCount() > 0) {
            tblDanhSachSP.getColumnModel().getColumn(0).setResizable(false);
            tblDanhSachSP.getColumnModel().getColumn(1).setResizable(false);
            tblDanhSachSP.getColumnModel().getColumn(2).setResizable(false);
            tblDanhSachSP.getColumnModel().getColumn(3).setResizable(false);
            tblDanhSachSP.getColumnModel().getColumn(4).setResizable(false);
            tblDanhSachSP.getColumnModel().getColumn(5).setResizable(false);
            tblDanhSachSP.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo hóa đơn"));

        jLabel18.setText("Tên Khách Hàng");

        jLabel20.setText("Mã NV");

        txtMaNV.setBackground(new java.awt.Color(242, 242, 242));
        txtMaNV.setEnabled(false);

        jLabel22.setText("Ngày Lập");

        txtNgayLap.setEnabled(false);
        txtNgayLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayLapActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(0, 255, 51));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 51, 51));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel24.setText("Hình thứ than toán");

        cboHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Quẹt thẻ" }));
        cboHinhThucThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboHinhThucThanhToanMouseClicked(evt);
            }
        });

        jLabel25.setText("Tổng tiền tt");

        lblTongTien.setText("-");

        jLabel26.setText("Tiền Khách Đưa");

        txtTienKhachDua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTienKhachDuaFocusLost(evt);
            }
        });
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        lblTienTraLai.setText("Tiền trả Khách");

        lblTienTraKhach.setText("-");

        jLabel29.setText("Mã HD");

        jButton3.setBackground(new java.awt.Color(51, 102, 255));
        jButton3.setText("Tạo hóa đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Ghi chú");

        txtGhichu.setColumns(20);
        txtGhichu.setRows(5);
        jScrollPane9.setViewportView(txtGhichu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                    .addComponent(txtMaNV)
                                    .addComponent(txtNgayLap)
                                    .addComponent(txtMaHD)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnHuy)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTienTraLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cboHinhThucThanhToan, 0, 249, Short.MAX_VALUE)
                                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnThanhToan});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienTraLai)
                    .addComponent(lblTienTraKhach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnThanhToan});

        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tìm sản phẩm");

        panelCam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelCam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCamMouseClicked(evt);
            }
        });
        panelCam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout banhangLayout = new javax.swing.GroupLayout(banhang);
        banhang.setLayout(banhangLayout);
        banhangLayout.setHorizontalGroup(
            banhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banhangLayout.createSequentialGroup()
                .addGroup(banhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(banhangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane8)
                    .addGroup(banhangLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCam, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        banhangLayout.setVerticalGroup(
            banhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banhangLayout.createSequentialGroup()
                .addGroup(banhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCam, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(banhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        home.add(banhang, "card4");

        jScrollPane18.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mại"));
        jScrollPane18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane18MouseClicked(evt);
            }
        });

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KM", "Tên KM", "Ngày BĐ", "Ngày KT", "Giảm giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tblKhuyenMai);
        if (tblKhuyenMai.getColumnModel().getColumnCount() > 0) {
            tblKhuyenMai.getColumnModel().getColumn(0).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(1).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(2).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(3).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(4).setResizable(false);
            tblKhuyenMai.getColumnModel().getColumn(5).setResizable(false);
        }

        jScrollPane19.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý KM"));

        jLabel50.setText("Tên KM");

        jLabel51.setText("Ngày bắt đầu");

        jLabel52.setText("Ngày kết thúc");

        jLabel53.setText("Giảm giá");

        jLabel54.setText("Trạng thái");

        txtMAKM.setEditable(false);
        txtMAKM.setBackground(new java.awt.Color(255, 255, 255));
        txtMAKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMAKMActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdoHD);
        rdoHD.setText("Hoạt động");

        buttonGroup4.add(rdoHHD);
        rdoHHD.setText("Hết hoạt động");

        dateNgayBD.setDateFormatString("yyyy-MM-dd");

        dateNgayKT.setDateFormatString("yyyy-MM-dd");

        jButton8.setText("Tạo KM");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Cập nhật");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Mới");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(txtMAKM, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(rdoHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHHD, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTENKM)
                    .addComponent(dateNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGIAMGIA)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(txtTENKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel51))
                    .addComponent(dateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52)
                    .addComponent(dateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtGIAMGIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(rdoHD)
                    .addComponent(rdoHHD))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtMAKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jScrollPane19.setViewportView(jPanel21);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );

        jTabbedPane3.addTab("Chương trình khuyến mại", jPanel19);

        jScrollPane20.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblDanhSachSanPhamKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Loại", "Đơn giá", "Số lượng", "Màu săc", "SIZE", "Chất liệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane20.setViewportView(tblDanhSachSanPhamKhuyenMai);
        if (tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumnCount() > 0) {
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(0).setResizable(false);
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(1).setResizable(false);
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(2).setResizable(false);
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(3).setResizable(false);
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(4).setResizable(false);
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(5).setResizable(false);
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(6).setResizable(false);
            tblDanhSachSanPhamKhuyenMai.getColumnModel().getColumn(7).setResizable(false);
        }

        jScrollPane21.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mại còn hoạt động"));

        tblDanhSachKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã KM", "Tên KM", "Ngày BD", "Ngày KT", "Giá giảm", "Trạng thái"
            }
        ));
        tblDanhSachKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tblDanhSachKhuyenMai);

        jScrollPane22.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm sản phẩm"));

        txtTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimSPActionPerformed(evt);
            }
        });
        txtTimSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSPKeyReleased(evt);
            }
        });

        jLabel49.setText("Tìm sản phẩm ");

        cbbLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiSanPhamActionPerformed(evt);
            }
        });

        jLabel55.setText("Chọn loại SP");

        jLabel59.setText("Tìm khuyến mại ");

        txtTIMKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTIMKMKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbLoaiSanPham, javax.swing.GroupLayout.Alignment.TRAILING, 0, 268, Short.MAX_VALUE)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTIMKM))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimSP)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLoaiSanPham)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtTIMKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jScrollPane22.setViewportView(jPanel22);

        jScrollPane23.setBorder(javax.swing.BorderFactory.createTitledBorder("Áp dụng khuyến mại"));

        txtLOAISP.setEditable(false);
        txtLOAISP.setBackground(new java.awt.Color(255, 255, 255));
        txtLOAISP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLOAISPActionPerformed(evt);
            }
        });

        jLabel56.setText("Loại sản phẩm ");

        txtMAKHUYENMAI.setEditable(false);
        txtMAKHUYENMAI.setBackground(new java.awt.Color(255, 255, 255));

        jLabel57.setText("Mã khuyến mại ");

        jLabel58.setText("Giá giảm ");

        jButton11.setText("Áp dụng ");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Hủy ");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                        .addGap(61, 61, 61)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addComponent(txtGIAGIAMKHUYENMAI, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMAKHUYENMAI, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtLOAISP, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtMALOAI, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLOAISP)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMAKHUYENMAI)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGIAGIAMKHUYENMAI)
                    .addComponent(jLabel58))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(txtMALOAI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jScrollPane23.setViewportView(jPanel23);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane21)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane22)
                    .addComponent(jScrollPane23))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Áp dụng khuyến mại", jPanel20);

        javax.swing.GroupLayout voucherLayout = new javax.swing.GroupLayout(voucher);
        voucher.setLayout(voucherLayout);
        voucherLayout.setHorizontalGroup(
            voucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(voucherLayout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        voucherLayout.setVerticalGroup(
            voucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        home.add(voucher, "card5");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Danh sách tài khoản"));

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Mật khẩu", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTaiKhoan);
        if (tblTaiKhoan.getColumnModel().getColumnCount() > 0) {
            tblTaiKhoan.getColumnModel().getColumn(0).setResizable(false);
            tblTaiKhoan.getColumnModel().getColumn(1).setResizable(false);
            tblTaiKhoan.getColumnModel().getColumn(2).setResizable(false);
            tblTaiKhoan.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel6.setText("Mã");

        jLabel19.setText("Mật khẩu");

        jLabel21.setText("Vai trò");

        txtThemTaiK.setText("Thêm");
        txtThemTaiK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThemTaiKActionPerformed(evt);
            }
        });

        txtSuaTaiK.setText("Sửa");
        txtSuaTaiK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSuaTaiKActionPerformed(evt);
            }
        });

        txtXoaTaiK.setText("Xóa");
        txtXoaTaiK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXoaTaiKActionPerformed(evt);
            }
        });

        txtTimTaiK.setText("Tìm");
        txtTimTaiK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimTaiKActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNV);
        rdoNV.setText("Nhân viên");

        buttonGroup1.add(rdoQL);
        rdoQL.setText("Quản lý");

        txtLoadTaiK.setText("LoadV");
        txtLoadTaiK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoadTaiKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taikhoanLayout = new javax.swing.GroupLayout(taikhoan);
        taikhoan.setLayout(taikhoanLayout);
        taikhoanLayout.setHorizontalGroup(
            taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taikhoanLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(taikhoanLayout.createSequentialGroup()
                        .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaTk, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNV)
                            .addComponent(rdoQL)))
                    .addGroup(taikhoanLayout.createSequentialGroup()
                        .addComponent(txtTimTK)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimTaiK))
                    .addGroup(taikhoanLayout.createSequentialGroup()
                        .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSuaTaiK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtThemTaiK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLoadTaiK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtXoaTaiK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(93, 93, 93)))
                .addGap(19, 19, 19))
        );
        taikhoanLayout.setVerticalGroup(
            taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(taikhoanLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimTaiK)
                    .addComponent(txtTimTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaTk))
                .addGap(29, 29, 29)
                .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtMatKhau))
                .addGap(34, 34, 34)
                .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(rdoNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoQL)
                .addGap(44, 44, 44)
                .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThemTaiK)
                    .addComponent(txtXoaTaiK))
                .addGap(31, 31, 31)
                .addGroup(taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSuaTaiK)
                    .addComponent(txtLoadTaiK))
                .addGap(95, 95, 95))
        );

        home.add(taikhoan, "card6");

        nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhanvienMouseClicked(evt);
            }
        });

        jScrollPane14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin nhân viên"));
        jScrollPane14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane14MouseClicked(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Họ tên", "Ngày sinh ", "Địa chỉ", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(1).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(2).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(3).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(4).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(5).setResizable(false);
        }

        jScrollPane15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Quản lý nhân viên"));

        jLabel5.setText("Mã NV");

        jLabel10.setText("Họ tên");

        jLabel12.setText("Ngày sinh");

        jLabel13.setText("Địa chỉ");

        jLabel14.setText("Email");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Sửa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel15.setText("Tìm NV");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTIMNV, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMNV)
                            .addComponent(txtHOTEN)
                            .addComponent(txtNGAYSINH)
                            .addComponent(txtDIACHI)
                            .addComponent(txtEMAIL, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(36, 36, 36))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTIMNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(42, 42, 42)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtHOTEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNGAYSINH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtEMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jScrollPane15.setViewportView(jPanel10);

        javax.swing.GroupLayout nhanvienLayout = new javax.swing.GroupLayout(nhanvien);
        nhanvien.setLayout(nhanvienLayout);
        nhanvienLayout.setHorizontalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );
        nhanvienLayout.setVerticalGroup(
            nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        home.add(nhanvien, "card7");

        lichsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lichsuMouseClicked(evt);
            }
        });

        tblLichS.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));
        tblLichS.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblLichSAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblLichS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSMouseClicked(evt);
            }
        });

        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HD", "Ngày tạo", "Mã NV", "Trạng thái", "Tên KH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuMouseClicked(evt);
            }
        });
        tblLichS.setViewportView(tblLichSu);
        if (tblLichSu.getColumnModel().getColumnCount() > 0) {
            tblLichSu.getColumnModel().getColumn(0).setResizable(false);
            tblLichSu.getColumnModel().getColumn(1).setResizable(false);
            tblLichSu.getColumnModel().getColumn(2).setResizable(false);
            tblLichSu.getColumnModel().getColumn(3).setResizable(false);
            tblLichSu.getColumnModel().getColumn(4).setResizable(false);
            tblLichSu.getColumnModel().getColumn(5).setResizable(false);
        }

        jScrollPane11.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tblSanPhamLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng ", "Giá", "Giảm giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblSanPhamLichSu);
        if (tblSanPhamLichSu.getColumnModel().getColumnCount() > 0) {
            tblSanPhamLichSu.getColumnModel().getColumn(0).setResizable(false);
            tblSanPhamLichSu.getColumnModel().getColumn(1).setResizable(false);
            tblSanPhamLichSu.getColumnModel().getColumn(2).setResizable(false);
            tblSanPhamLichSu.getColumnModel().getColumn(3).setResizable(false);
            tblSanPhamLichSu.getColumnModel().getColumn(4).setResizable(false);
            tblSanPhamLichSu.getColumnModel().getColumn(5).setResizable(false);
            tblSanPhamLichSu.getColumnModel().getColumn(6).setResizable(false);
        }

        jScrollPane12.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel8.setText("Mã NV");

        txtMaNVLS.setEditable(false);
        txtMaNVLS.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNVLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVLSActionPerformed(evt);
            }
        });

        txtTenKHLS.setEditable(false);
        txtTenKHLS.setBackground(new java.awt.Color(255, 255, 255));
        txtTenKHLS.setOpaque(true);

        jLabel9.setText("Tên KH");

        txtGiaTienLS.setEditable(false);
        txtGiaTienLS.setBackground(new java.awt.Color(255, 255, 255));
        txtGiaTienLS.setOpaque(true);

        jLabel23.setText("Giá tiền");

        txtNgayLapLS.setEditable(false);
        txtNgayLapLS.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayLapLS.setOpaque(true);

        jLabel27.setText("Ngày lập");

        txtTrangThaiLS.setEditable(false);
        txtTrangThaiLS.setBackground(new java.awt.Color(255, 255, 255));
        txtTrangThaiLS.setOpaque(true);

        jLabel28.setText("Trạng thái");

        jLabel30.setText("Ghi chú");

        txtGhiChuLS.setEditable(false);
        txtGhiChuLS.setBackground(new java.awt.Color(255, 255, 255));
        txtGhiChuLS.setColumns(20);
        txtGhiChuLS.setRows(5);
        jScrollPane10.setViewportView(txtGhiChuLS);

        txtHinhThucTTLS.setEditable(false);
        txtHinhThucTTLS.setBackground(new java.awt.Color(255, 255, 255));
        txtHinhThucTTLS.setOpaque(true);

        jLabel31.setText("Hình thức tt");

        btnInHoaDon.setBackground(new java.awt.Color(51, 51, 255));
        btnInHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnInHoaDon.setText("In hóa đơn ");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtMaNVLS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTenKHLS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTienLS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayLapLS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrangThaiLS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHinhThucTTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaNVLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTenKHLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtGiaTienLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtNgayLapLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtTrangThaiLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtHinhThucTTLS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        jScrollPane12.setViewportView(jPanel4);

        txtTimKhachHang.setText(" ");
        txtTimKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKhachHangKeyReleased(evt);
            }
        });

        jLabel45.setText("Tìm khách hàng ");

        javax.swing.GroupLayout lichsuLayout = new javax.swing.GroupLayout(lichsu);
        lichsu.setLayout(lichsuLayout);
        lichsuLayout.setHorizontalGroup(
            lichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lichsuLayout.createSequentialGroup()
                .addGroup(lichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tblLichS, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addComponent(jScrollPane11)
                    .addGroup(lichsuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        lichsuLayout.setVerticalGroup(
            lichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lichsuLayout.createSequentialGroup()
                .addComponent(tblLichS, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(lichsuLayout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        home.add(lichsu, "card8");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 51, 51));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Tổng doanh thu  ");

        lblDTNGAY.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDTNGAY.setForeground(new java.awt.Color(255, 255, 255));
        lblDTNGAY.setText(" ");

        lblHOADONNgay.setForeground(new java.awt.Color(255, 255, 255));
        lblHOADONNgay.setText(" ");

        lblSOHANGngay.setForeground(new java.awt.Color(255, 255, 255));
        lblSOHANGngay.setText(" ");

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/day.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblSOHANGngay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(lblHOADONNgay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblDTNGAY, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42))
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(lblDTNGAY, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHOADONNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSOHANGngay)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(0, 0, 255));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Tổng doanh thu năm ");

        lblDTNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDTNam.setForeground(new java.awt.Color(255, 255, 255));
        lblDTNam.setText(" ");

        lblHOADONNAM.setForeground(new java.awt.Color(255, 255, 255));
        lblHOADONNAM.setText(" ");

        lblSOHANGNAM.setForeground(new java.awt.Color(255, 255, 255));
        lblSOHANGNAM.setText(" ");

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/year2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblSOHANGNAM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                .addComponent(lblHOADONNAM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblDTNam, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43))
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lblDTNam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHOADONNAM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSOHANGNAM)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 153, 0));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Tổng doanh thu tháng ");

        lblDTThang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDTThang.setForeground(new java.awt.Color(255, 255, 255));
        lblDTThang.setText(" ");

        lblHOADONTHANG.setForeground(new java.awt.Color(255, 255, 255));
        lblHOADONTHANG.setText(" ");

        lblSOHANGTHANG.setForeground(new java.awt.Color(255, 255, 255));
        lblSOHANGTHANG.setText(" ");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/month.png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblSOHANGTHANG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addComponent(lblHOADONTHANG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16))
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblDTThang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHOADONTHANG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSOHANGTHANG)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setText("Loại thời gian ");

        cbbLOAITHOIGIAN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ngày", "Hôm nay" }));
        cbbLOAITHOIGIAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLOAITHOIGIANActionPerformed(evt);
            }
        });

        ngaybatdau.setBackground(new java.awt.Color(255, 255, 255));
        ngaybatdau.setDateFormatString("yyyy-MM-dd");

        ngayketthuc.setBackground(new java.awt.Color(255, 255, 255));
        ngayketthuc.setDateFormatString("yyyy-MM-dd");

        btnTIMLOAITIME.setText("Tìm");
        btnTIMLOAITIME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTIMLOAITIMEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLOAITHOIGIAN, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(ngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(ngayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnTIMLOAITIME, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(207, 207, 207))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(cbbLOAITHOIGIAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(ngayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTIMLOAITIME))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTKMouseClicked
        thongke.setVisible(true);
        sanpham.setVisible(false);
        banhang.setVisible(false);
        voucher.setVisible(false);
        taikhoan.setVisible(false);
        nhanvien.setVisible(false);
        lichsu.setVisible(false);
        if (webcam != null) { // Kiểm tra xem webcam đã được khởi tạo
            stopWebCam();
        }
    }//GEN-LAST:event_mnTKMouseClicked

    private void mnSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnSPMouseClicked
        thongke.setVisible(false);
        sanpham.setVisible(true);
        banhang.setVisible(false);
        voucher.setVisible(false);
        taikhoan.setVisible(false);
        nhanvien.setVisible(false);
        lichsu.setVisible(false);
        if (webcam != null) { // Kiểm tra xem webcam đã được khởi tạo
            stopWebCam();
        }
    }//GEN-LAST:event_mnSPMouseClicked

    private void mnHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnHDMouseClicked
        thongke.setVisible(false);
        sanpham.setVisible(false);
        banhang.setVisible(true);
        voucher.setVisible(false);
        taikhoan.setVisible(false);
        nhanvien.setVisible(false);
        lichsu.setVisible(false);
        initWebCam();
    }//GEN-LAST:event_mnHDMouseClicked

    private void mnVCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnVCMouseClicked
        thongke.setVisible(false);
        sanpham.setVisible(false);
        banhang.setVisible(false);
        voucher.setVisible(true);
        taikhoan.setVisible(false);
        nhanvien.setVisible(false);
        lichsu.setVisible(false);
        if (webcam != null) { // Kiểm tra xem webcam đã được khởi tạo
            stopWebCam();
        }
    }//GEN-LAST:event_mnVCMouseClicked

    private void mnTKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTKhoanMouseClicked
        new DoiMatKhauJdialog(this, true).setVisible(true);
    }//GEN-LAST:event_mnTKhoanMouseClicked

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        int row = tblTaiKhoan.getSelectedRow();
        loadtk(row);
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void tblDanhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachHoaDonMouseClicked
        int row = tblDanhSachHoaDon.getSelectedRow();
        loadhd(row);
    }//GEN-LAST:event_tblDanhSachHoaDonMouseClicked
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        // Load lại dữ liệu từ tblDanhSachSanPham vào tblGioHang
        returnQuantitiesToDanhSachSanPham(); // Gọi phương thức để load dữ liệu
        clearFormHD();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void mnLSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnLSMouseClicked
        thongke.setVisible(false);
        sanpham.setVisible(false);
        banhang.setVisible(false);
        voucher.setVisible(false);
        taikhoan.setVisible(false);
        nhanvien.setVisible(false);
        lichsu.setVisible(true);
        if (webcam != null) { // Kiểm tra xem webcam đã được khởi tạo
            stopWebCam();
        }
    }//GEN-LAST:event_mnLSMouseClicked

    private void mnNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnNVMouseClicked
        thongke.setVisible(false);
        sanpham.setVisible(false);
        banhang.setVisible(false);
        voucher.setVisible(false);
        taikhoan.setVisible(false);
        nhanvien.setVisible(true);
        lichsu.setVisible(false);
        if (webcam != null) { // Kiểm tra xem webcam đã được khởi tạo
            stopWebCam();
        }
    }//GEN-LAST:event_mnNVMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblDanhSachSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSPMouseClicked
        if (evt.getClickCount() == 2) {
            int selectedRow = tblDanhSachSP.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
                String maSp = (String) model.getValueAt(selectedRow, 0);
                String tenSp = (String) model.getValueAt(selectedRow, 1);
                int soLuongHienTai = (int) model.getValueAt(selectedRow, 5);

                int newQuantity = promptForNewQuantity(tenSp, soLuongHienTai);
                if (newQuantity != -1) {
                    if (newQuantity <= soLuongHienTai) {
                        addToShoppingCart(maSp, tenSp, newQuantity);
                        int updatedQuantity = soLuongHienTai - newQuantity;
                        model.setValueAt(updatedQuantity, selectedRow, 5); // Cập nhật số lượng mới trong bảng tblDSSP
                    } else {
                        JOptionPane.showMessageDialog(null, "Số lượng nhập vào lớn hơn số lượng hiện có.");
                    }
                }
            }
        }
    }//GEN-LAST:event_tblDanhSachSPMouseClicked

    private void txtNgayLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayLapActionPerformed

    }//GEN-LAST:event_txtNgayLapActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void txtTienKhachDuaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachDuaFocusLost
//        txtTienKhachDua.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                try {
//                    double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
//                    double tongTien = Double.parseDouble(lblTongTien.getText());
//                    double tienTraKhach = tienKhachDua - tongTien;
//                    lblTienTraKhach.setText(String.valueOf(tienTraKhach));
//                } catch (NumberFormatException ex) {
//                    lblTienTraKhach.setText("0"); // hoặc hiển thị thông báo lỗi
//                }
//            }
//        });
    }//GEN-LAST:event_txtTienKhachDuaFocusLost

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn ko", "Thanh toán", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (check == JOptionPane.YES_OPTION) {
            insertHoaDonChiTiet();
            MsgBox.alert(this, "Thanh toán và in hóa đơn thành công!");
        } else {
            insertHoaDonChiTiet();
            MsgBox.alert(this, "Thanh toán thành công!");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtXoaTaiKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXoaTaiKActionPerformed
        deletetk();
    }//GEN-LAST:event_txtXoaTaiKActionPerformed

    private void txtSuaTaiKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSuaTaiKActionPerformed
        updatetk();
    }//GEN-LAST:event_txtSuaTaiKActionPerformed

    private void txtLoadTaiKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoadTaiKActionPerformed
        Tabletk();
    }//GEN-LAST:event_txtLoadTaiKActionPerformed

    private void txtTimTaiKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimTaiKActionPerformed
        try {
            String maNV = txtTimTK.getText();
            if (maNV.isEmpty()) {
                MsgBox.alert(this, "Mã sản phẩm không được để trống.");
                return;
            }
            List<TaiKhoan> productList = tk.findProductByMatk(maNV);

            if (productList.isEmpty()) {
                MsgBox.alert(this, "Không tìm thấy sản phẩm có mã " + maNV);
            } else {
                updateTableTaiKhoan(productList);
                MsgBox.alert(this, "Tìm kiếm thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Tìm kiếm thất bại! Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_txtTimTaiKActionPerformed

    private void txtThemTaiKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThemTaiKActionPerformed
        inserttk();
    }//GEN-LAST:event_txtThemTaiKActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inserthd();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cboHinhThucThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboHinhThucThanhToanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboHinhThucThanhToanMouseClicked

    private void nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanvienMouseClicked

    }//GEN-LAST:event_nhanvienMouseClicked

    private void lichsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lichsuMouseClicked

    }//GEN-LAST:event_lichsuMouseClicked

    private void tblLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuMouseClicked
        int selectedRow = tblLichSu.getSelectedRow();
        if (selectedRow != -1) { // Kiểm tra xem đã chọn dòng nào chưa
            Object maHD = tblLichSu.getValueAt(selectedRow, 1); // Giả sử MaHD ở cột đầu tiên, cột 0
            HoaDon hoaDon = hd.layThongTinHoaDonTheoMaHD((int) maHD);
            if (hoaDon != null) {
                txtMaNVLS.setText(hoaDon.getMaNv());
                txtTenKHLS.setText(hoaDon.getTenKH());
                txtGiaTienLS.setText(String.valueOf(hoaDon.getGiaTien()));
                txtGhiChuLS.setText(hoaDon.getGhiChu());
                txtNgayLapLS.setText(String.valueOf(hoaDon.getNgayLap()));
                String trangThaiText = hoaDon.getTrangThai() ? "Đã thanh toán" : "Đang tạo";
                txtTrangThaiLS.setText(trangThaiText);
                txtHinhThucTTLS.setText(hoaDon.getHinhThucTT());
            }
            List<SanPham> danhSachSanPham = dao.laySanPhamTheoMaHD((int) maHD);
            DefaultTableModel model = (DefaultTableModel) tblSanPhamLichSu.getModel();
            model.setRowCount(0); // Xóa hết dữ liệu cũ trong bảng
            int i = 1;
            for (SanPham sp : danhSachSanPham) {
                // Thêm từng dòng dữ liệu vào bảng tblSanPhamLichSu
                model.addRow(new Object[]{
                    i++,
                    sp.getMaSp(),
                    sp.getTenSp(),
                    sp.getSoLuong(),
                    sp.getGia(),
                    sp.getSale(),
                    // Tính toán TongTien
                    (sp.getGia() * sp.getSoLuong()) - (sp.getSale() * sp.getSoLuong())
                });
            }
        }
    }//GEN-LAST:event_tblLichSuMouseClicked

    private void tblLichSAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblLichSAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLichSAncestorAdded

    private void tblLichSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLichSMouseClicked

    private void mnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnThoatMouseClicked
        if (MsgBox.confirm(this, "Bạn có chắc đăng xuât?")) {
            DangNhapJFrame dangNhapJFrame = new DangNhapJFrame();
            dangNhapJFrame.show();
            dispose();
        }
    }//GEN-LAST:event_mnThoatMouseClicked

    private void rdoChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatLieuActionPerformed
        mausac.setVisible(false);
        kichthuoc.setVisible(false);
        chatlieu.setVisible(true);
        tenloaisanpham.setVisible(false);
        selectedTable = tblChatLieu;
    }//GEN-LAST:event_rdoChatLieuActionPerformed

    private void rdoKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKichThuocActionPerformed
        mausac.setVisible(false);
        kichthuoc.setVisible(true);
        chatlieu.setVisible(false);
        tenloaisanpham.setVisible(false);
        selectedTable = tblKichThuoc;
    }//GEN-LAST:event_rdoKichThuocActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền thêm !");
        } else {
            insert();
            fillTableDSSP(listAllSP);
            LoadTable();
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền sửa !");
        } else {
            update();
            fillTableDSSP(listAllSP);
            LoadTable();
        }
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void cbbKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbKichThuocActionPerformed

    private void cbbChatLieujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChatLieujActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbChatLieujActionPerformed

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        mausac.setVisible(true);
        kichthuoc.setVisible(false);
        chatlieu.setVisible(false);
        tenloaisanpham.setVisible(false);
        selectedTable = tblMauSac;
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void rdoTenLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTenLoaiSPActionPerformed
        mausac.setVisible(false);
        kichthuoc.setVisible(false);
        chatlieu.setVisible(false);
        tenloaisanpham.setVisible(true);
        selectedTable = tblTenLoaiSanPham;
    }//GEN-LAST:event_rdoTenLoaiSPActionPerformed

    private void btnSuaTTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTSPActionPerformed
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền sửa !");
        } else {
            if (selectedTable != null) {
                if (selectedTable == tblChatLieu) {
                    updateCHATL();
                } else if (selectedTable == tblMauSac) {
                    updateMAU();
                } else if (selectedTable == tblTenLoaiSanPham) {
                    updateLOAI();
                } else if (selectedTable == tblKichThuoc) {
                    updateSIZE();
                }
            }
            loadComboBoxData();
            LoadTable();
        }
    }//GEN-LAST:event_btnSuaTTSPActionPerformed

    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed

    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void txtTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTim1ActionPerformed

    }//GEN-LAST:event_txtTim1ActionPerformed

    private void txtTim1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTim1KeyReleased
        String keyword = txtTim1.getText().trim();
        if (!keyword.isEmpty()) {
            // Lọc dữ liệu từ cơ sở dữ liệu dựa trên từ khóa keyword
            List<SanPham> filteredProducts = dao.searchProductsByKeyword(keyword);

            // Hiển thị kết quả tìm kiếm trên JTable
            DefaultTableModel model = (DefaultTableModel) tblThongTinSP.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong table
            for (SanPham sp : filteredProducts) {
                Object[] row = {
                    sp.getMaSp(),
                    sp.getTenSp(),
                    sp.getLoai(),
                    sp.getGia(),
                    sp.getSoLuong(),
                    sp.getMauSac(),
                    sp.getSize(),
                    sp.getChatLieu()
                };
                model.addRow(row);
            }
        } else {
            // Xử lý trường hợp không có từ khóa tìm kiếm
        }
    }//GEN-LAST:event_txtTim1KeyReleased

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        String keyword = txtTimkiem.getText().trim();
        if (!keyword.isEmpty()) {
            // Lọc dữ liệu từ cơ sở dữ liệu dựa trên từ khóa keyword
            List<SanPham> filteredProducts = dao.searchProductsByKeywordSP(keyword);

            // Hiển thị kết quả tìm kiếm trên JTable
            DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong table
            for (SanPham sp : filteredProducts) {
                Object[] row = {
                    sp.getMaSp(),
                    sp.getTenSp(),
                    sp.getMauSac(),
                    sp.getChatLieu(),
                    sp.getSize(),
                    sp.getSoLuong(),
                    sp.getGia()
                };
                model.addRow(row);
            }
        } else {
            // Xử lý trường hợp không có từ khóa tìm kiếm
        }
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void btnAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnActionPerformed

    private void jScrollPane14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane14MouseClicked

    }//GEN-LAST:event_jScrollPane14MouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int row = tblNhanVien.getSelectedRow();
        loadnv(row);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertnv();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        updatenv();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        deletenv();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền thêm !");
        } else {
            if (selectedTable != null) {
                if (selectedTable == tblChatLieu) {
                    insertCHATL();
                } else if (selectedTable == tblMauSac) {
                    insertMAU();
                } else if (selectedTable == tblKichThuoc) {
                    insertSIZE();
                } else if (selectedTable == tblTenLoaiSanPham) {
                    insertLOAI();
                }
            }
            loadComboBoxData();
            LoadTable();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblTenLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTenLoaiSanPhamMouseClicked
        int row = tblTenLoaiSanPham.getSelectedRow();
        loadLOAI(row);
    }//GEN-LAST:event_tblTenLoaiSanPhamMouseClicked

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        int row = tblChatLieu.getSelectedRow();
        loadCHATLIEU(row);
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void tblKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocMouseClicked
        int row = tblKichThuoc.getSelectedRow();
        loadSIZE(row);
    }//GEN-LAST:event_tblKichThuocMouseClicked

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        int row = tblMauSac.getSelectedRow();
        loadMAU(row);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed

    }//GEN-LAST:event_txtIDActionPerformed

    private void mnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnThoatMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mnThoatMouseEntered

    private void txtTim1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTim1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblThongTinSP.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tblThongTinSP.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtTim1.getText()));
    }//GEN-LAST:event_txtTim1MouseClicked

    private void mnTKhoan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTKhoan1MouseClicked
        thongke.setVisible(false);
        sanpham.setVisible(false);
        banhang.setVisible(false);
        voucher.setVisible(false);
        taikhoan.setVisible(true);
        nhanvien.setVisible(false);
        lichsu.setVisible(false);
        if (webcam != null) { // Kiểm tra xem webcam đã được khởi tạo
            stopWebCam();
        }
    }//GEN-LAST:event_mnTKhoan1MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        updatekm();
        TablekmHD();
        LoadTable();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        insertkm();
        TablekmHD();
        LoadTable();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jScrollPane18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane18MouseClicked

    }//GEN-LAST:event_jScrollPane18MouseClicked

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        int row = tblKhuyenMai.getSelectedRow();
        loadkm(row);
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void txtMAKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMAKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMAKMActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        clearFormkm();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtTimSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSPKeyReleased
        String keyword = txtTimSP.getText().trim();
        if (!keyword.isEmpty()) {
            // Lọc dữ liệu từ cơ sở dữ liệu dựa trên từ khóa keyword
            List<SanPham> filteredProducts = dao.searchProductsByKeyword(keyword);

            // Hiển thị kết quả tìm kiếm trên JTable
            DefaultTableModel model = (DefaultTableModel) tblDanhSachSanPhamKhuyenMai.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong table
            for (SanPham sp : filteredProducts) {
                Object[] row = {
                    sp.getMaSp(),
                    sp.getTenSp(),
                    sp.getLoai(),
                    sp.getGia(),
                    sp.getSoLuong(),
                    sp.getMauSac(),
                    sp.getSize(),
                    sp.getChatLieu()
                };
                model.addRow(row);
            }
        } else {
            // Xử lý trường hợp không có từ khóa tìm kiếm
        }
    }//GEN-LAST:event_txtTimSPKeyReleased

    private void cbbLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiSanPhamActionPerformed
        String selectedLoai = cbbLoaiSanPham.getSelectedItem().toString();
        txtLOAISP.setText(selectedLoai);

        int loaiID = dao.getLoaiID(selectedLoai); // Lấy ID của TenLoai được chọn
        // Set giá trị ID vào JTextField txtLOAISP
        txtMALOAI.setText(String.valueOf(loaiID));
        // Thực hiện truy vấn để lấy các sản phẩm có cùng loại
        List<SanPham> sanPhamsWithSameLoai = dao.selectAll13(selectedLoai);
        // Hiển thị danh sách sản phẩm có cùng loại trong bảng hoặc list view
        // Ví dụ: Hiển thị danh sách trong một bảng hoặc list view
        DefaultTableModel model = (DefaultTableModel) tblDanhSachSanPhamKhuyenMai.getModel();
        model.setRowCount(0); // Xóa các hàng hiện có

        for (SanPham sp : sanPhamsWithSameLoai) {
            Object[] row = {
                sp.getMaSp(),
                sp.getTenSp(),
                sp.getLoai(),
                sp.getGia(),
                sp.getSoLuong(),
                sp.getMauSac(),
                sp.getSize(),
                sp.getChatLieu()
            };
            model.addRow(row);
        }
    }//GEN-LAST:event_cbbLoaiSanPhamActionPerformed

    private void tblDanhSachKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKhuyenMaiMouseClicked
        int row = tblDanhSachKhuyenMai.getSelectedRow();
        loaddskm(row);
    }//GEN-LAST:event_tblDanhSachKhuyenMaiMouseClicked

    private void txtTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimSPActionPerformed

    private void txtTIMKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTIMKMKeyReleased
        String keyword = txtTIMKM.getText().trim();
        if (!keyword.isEmpty()) {
            // Lọc dữ liệu từ cơ sở dữ liệu dựa trên từ khóa keyword
            List<Vourcher> filteredProducts = km.searchProductsByKeyword(keyword);

            // Hiển thị kết quả tìm kiếm trên JTable
            DefaultTableModel model = (DefaultTableModel) tblDanhSachKhuyenMai.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong table
            for (Vourcher sp : filteredProducts) {
                if (sp.getTrangThai()) { // Kiểm tra trạng thái là "Hoạt động"
                    String trangThaiText = (sp.getTrangThai()) ? "Hoạt động" : "Hết hoạt động";
                    Object[] row = {
                        sp.getMaVourcher(),
                        sp.getTenVoucher(),
                        sp.getNgayBatDau(),
                        sp.getNgayKetThuc(),
                        sp.getGiamGia(),
                        trangThaiText
                    };
                    model.addRow(row);
                }
            }
        } else {
            // Xử lý trường hợp không có từ khóa tìm kiếm
        }
    }//GEN-LAST:event_txtTIMKMKeyReleased

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        clearFormADKM();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        updateKhuyenMai();
        LoadTable();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtLOAISPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLOAISPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLOAISPActionPerformed

    private void cbbLOAITHOIGIANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLOAITHOIGIANActionPerformed
        String selectedOption = (String) cbbLOAITHOIGIAN.getSelectedItem();

        if (selectedOption.equals("Theo ngày")) {
            ngaybatdau.setVisible(true);
            ngayketthuc.setVisible(true);
            btnTIMLOAITIME.setVisible(true);
            lblDTNGAY.setText("0 $");
            lblHOADONNgay.setText("Số hóa đơn: 0");
            lblSOHANGngay.setText("Số lượng hàng: 0");
        } else if (selectedOption.equals("Hôm nay")) {
            ngaybatdau.setVisible(false);
            ngayketthuc.setVisible(false);
            btnTIMLOAITIME.setVisible(false);
            hienThiDoanhTHUTHEONGAY();
        }
    }//GEN-LAST:event_cbbLOAITHOIGIANActionPerformed

    private void btnTIMLOAITIMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTIMLOAITIMEActionPerformed
        Date startDate = new Date(ngaybatdau.getDate().getTime());
        Date endDate = new Date(ngayketthuc.getDate().getTime());
        hienThiDoanhTHUTHEONGAYKHITIM();
    }//GEN-LAST:event_btnTIMLOAITIMEActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách");

            // Creating header row
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0, CellType.STRING).setCellValue("STT");
            headerRow.createCell(1, CellType.STRING).setCellValue("Mã SP");
            headerRow.createCell(2, CellType.STRING).setCellValue("Số lượng bán");
            headerRow.createCell(3, CellType.STRING).setCellValue("Tổng giá bán");
            headerRow.createCell(4, CellType.STRING).setCellValue("Tổng giá giảm");
            headerRow.createCell(5, CellType.STRING).setCellValue("Doanh thu");

            List<ThongKe> data = doanhThu.selectAllXHD();

            for (int i = 0; i < data.size(); i++) {
                ThongKe item = data.get(i);
                XSSFRow row = sheet.createRow(i + 1);

                // Inserting data into respective cells within the row
                row.createCell(0, CellType.NUMERIC).setCellValue(i + 1); // Assuming STT is a serial number
                row.createCell(1, CellType.NUMERIC).setCellValue(item.getMaSP());
                row.createCell(2, CellType.NUMERIC).setCellValue(item.getSoLuongBan());
                row.createCell(3, CellType.NUMERIC).setCellValue(item.getTongGiaBan());
                row.createCell(4, CellType.NUMERIC).setCellValue(item.getTongGiaGiam());
                row.createCell(5, CellType.NUMERIC).setCellValue(item.getDoanhThu());
            }

            File f = new File("D:\\ThongKe2.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(this, "In thành công");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi mở hoặc ghi file");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi xảy ra");
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void panelCamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCamMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_panelCamMouseClicked

    private void txtMaNVLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVLSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVLSActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        try {
            XWPFDocument document = new XWPFDocument();
            // Lấy tên khách hàng từ textfield
            String tenKH = txtTenKHLS.getText();
            String fileName = tenKH.isEmpty() ? "Unknown" : tenKH;

            FileOutputStream out = new FileOutputStream(new File("D:\\" + fileName + ".docx"));

            XWPFParagraph title = document.createParagraph();
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Thông tin hóa đơn");
            titleRun.setFontSize(16);
            titleRun.setBold(true);
            titleRun.addBreak(); // Thêm một dòng mới

            // Lấy dữ liệu từ các textfield khác
            String maNV = txtMaNVLS.getText();
            String giaTien = txtGiaTienLS.getText();
            String ghiChu = txtGhiChuLS.getText();
            String ngayLap = txtNgayLapLS.getText();
            String trangThai = txtTrangThaiLS.getText();
            String hinhThucTT = txtHinhThucTTLS.getText();

            // Thêm dữ liệu từ textfield vào tệp Word
            XWPFTable table = document.createTable(1, 7);
            XWPFTableRow row = table.getRow(0);

            row.getCell(0).setText("MaNV: " + maNV);
            row.getCell(1).setText("TenKH: " + tenKH);
            row.getCell(2).setText("GiaTien: " + giaTien);
            row.getCell(3).setText("GhiChu: " + ghiChu);
            row.getCell(4).setText("NgayLap: " + ngayLap);
            row.getCell(5).setText("TrangThai: " + trangThai);
            row.getCell(6).setText("HinhThucTT: " + hinhThucTT);

            document.write(out);
            out.close();

//            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");

            // Mở tệp Word sau khi thông báo hiển thị
            Desktop.getDesktop().open(new File("D:\\" + fileName + ".docx"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void rdoBieuDoCTDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoBieuDoCTDTActionPerformed
        BANG.setVisible(false);
        bieudo.setVisible(true);
    }//GEN-LAST:event_rdoBieuDoCTDTActionPerformed

    private void rdoBangCTDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoBangCTDTActionPerformed
        BANG.setVisible(true);
        bieudo.setVisible(false);
    }//GEN-LAST:event_rdoBangCTDTActionPerformed

    private void tblThongTinSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinSPMouseClicked
        int row = tblThongTinSP.getSelectedRow();
        load(row);
    }//GEN-LAST:event_tblThongTinSPMouseClicked

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        txtTienKhachDua.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
                    double tongTien = Double.parseDouble(lblTongTien.getText());
                    double tienTraKhach = tienKhachDua - tongTien;
                    lblTienTraKhach.setText(String.valueOf(tienTraKhach));
                } catch (NumberFormatException ex) {
                    lblTienTraKhach.setText("0"); // hoặc hiển thị thông báo lỗi
                }
            }
        });
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void mnTKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTKMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mnTKMouseEntered

    private void txtTimKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKhachHangKeyReleased
        String keyword = txtTimKhachHang.getText().trim();
        if (!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong table
            List<HoaDon> list = hd.searchProductsByKeywordSP(keyword);
            list.sort(Comparator.comparing(HoaDon::getNgayLap).reversed());
            Map<Integer, HoaDon> mergedData = hd.mergeByMaHD(list);

            int i = 1;
            for (HoaDon hd : mergedData.values()) {
                String trangThai = hd.getTrangThai() ? "Đã thanh toán" : "Đang tạo";
                Object[] row = {i++, hd.getMaHD(), hd.getNgayLap(), hd.getMaNv(), trangThai, hd.getTenKH()};
                model.addRow(row);
            }
        } else {
        }
    }//GEN-LAST:event_txtTimKhachHangKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanAoSoMi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            BanAoSoMi frame = new BanAoSoMi();
            frame.setSize(1220, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BANG;
    private javax.swing.JTabbedPane DOANHTHU;
    private javax.swing.JScrollPane bang;
    private javax.swing.JPanel banhang;
    private javax.swing.JPanel bd;
    private javax.swing.JPanel bieudo;
    private javax.swing.JButton btnAn;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSuaTTSP;
    private javax.swing.JButton btnTIMLOAITIME;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbbChatLieuj;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbLOAITHOIGIAN;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> cbbLoaiSanPham;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNamCTDT;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JScrollPane chatlieu;
    private javax.swing.JPanel container;
    private com.toedter.calendar.JDateChooser dateNgayBD;
    private com.toedter.calendar.JDateChooser dateNgayKT;
    private javax.swing.JPanel doanhTHU;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JScrollPane kichthuoc;
    private javax.swing.JLabel lblDTNGAY;
    private javax.swing.JLabel lblDTNam;
    private javax.swing.JLabel lblDTThang;
    private javax.swing.JLabel lblHOADONNAM;
    private javax.swing.JLabel lblHOADONNgay;
    private javax.swing.JLabel lblHOADONTHANG;
    private javax.swing.JLabel lblSOHANGNAM;
    private javax.swing.JLabel lblSOHANGTHANG;
    private javax.swing.JLabel lblSOHANGngay;
    private javax.swing.JLabel lblTienTraKhach;
    private javax.swing.JLabel lblTienTraLai;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel lichsu;
    private javax.swing.JScrollPane mausac;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel mnHD;
    private javax.swing.JLabel mnLS;
    private javax.swing.JLabel mnNV;
    private javax.swing.JLabel mnSP;
    private javax.swing.JLabel mnTK;
    private javax.swing.JLabel mnTKhoan;
    private javax.swing.JLabel mnTKhoan1;
    private javax.swing.JLabel mnThoat;
    private javax.swing.JLabel mnVC;
    private com.toedter.calendar.JDateChooser ngaybatdau;
    private com.toedter.calendar.JDateChooser ngayketthuc;
    private javax.swing.JPanel nhanvien;
    private javax.swing.JPanel panelCam;
    private javax.swing.JRadioButton rdoBangCTDT;
    private javax.swing.JRadioButton rdoBieuDoCTDT;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoHHD;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JRadioButton rdoQL;
    private javax.swing.JRadioButton rdoTenLoaiSP;
    private javax.swing.JPanel sanpham;
    private javax.swing.JPanel taikhoan;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblChiTietDoanhThu;
    private javax.swing.JTable tblChiTietSanPhamDoanhThu;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTable tblDanhSachHoaDon;
    private javax.swing.JTable tblDanhSachKhuyenMai;
    private javax.swing.JTable tblDanhSachSP;
    private javax.swing.JTable tblDanhSachSanPhamKhuyenMai;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTable tblKichThuoc;
    private javax.swing.JScrollPane tblLichS;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblSanPhamLichSu;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTable tblTenLoaiSanPham;
    private javax.swing.JTable tblThongTinSP;
    private javax.swing.JScrollPane tenloaisanpham;
    private javax.swing.JPanel thongke;
    private javax.swing.JTextField txtDIACHI;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtEMAIL;
    private javax.swing.JTextField txtGIAGIAMKHUYENMAI;
    private javax.swing.JTextField txtGIAMGIA;
    private javax.swing.JTextArea txtGhiChuLS;
    private javax.swing.JTextArea txtGhichu;
    private javax.swing.JTextField txtGiaTienLS;
    private javax.swing.JTextField txtHOTEN;
    private javax.swing.JTextField txtHinhThucTTLS;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLOAISP;
    private javax.swing.JButton txtLoadTaiK;
    private javax.swing.JTextField txtMAKHUYENMAI;
    private javax.swing.JTextField txtMAKM;
    private javax.swing.JTextField txtMALOAI;
    private javax.swing.JTextField txtMNV;
    private javax.swing.JTextField txtMaCT;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNVLS;
    private javax.swing.JTextField txtMaTk;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNGAYSINH;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtNgayLapLS;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JButton txtSuaTaiK;
    private javax.swing.JTextField txtTENKM;
    private javax.swing.JTextField txtTIMKM;
    private javax.swing.JTextField txtTIMNV;
    private javax.swing.JTextField txtTenKHLS;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JButton txtThemTaiK;
    private javax.swing.JTextField txtThemThuocTinh;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTim1;
    private javax.swing.JTextField txtTimKhachHang;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JTextField txtTimTK;
    private javax.swing.JButton txtTimTaiK;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtTrangThaiLS;
    private javax.swing.JButton txtXoaTaiK;
    private javax.swing.JPanel voucher;
    // End of variables declaration//GEN-END:variables
//////////////////////// ĐĂNG NHẬP //////////////////////////////////////
    private void moDialogDangNhap() {
        if (Auth.isLogin() && Auth.User != null) {
            txtMaNV.setText(Auth.User.getMaNv()); // Gán giá trị MaNV vào JTextField txtMaNV
        }
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Ngăn chặn việc đóng cửa sổ bằng nút "X"
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ketThuc(); // 
            }
        });
    }

    private void ketThuc() {
        if (MsgBox.confirm(this, "Bạn muốn kết thúc ứng dựng?")) {
            System.exit(0);
        }
    }
    /////////////////////////////////// LOAD TABLE /////////////////////////////////////

    private void LoadTable() {
        Table();
        Tabletk();
        Tablenv();
        TableCHATLIEU();
        TableMAU();
        TableSIZE();
        TableLOAI();
        fillTableLICHSU(listAll);
        rdoMauSac.setSelected(true);
        TableDoanhThu();
        TableCTSPDoanhThu();
        Tablekm();
        TablekmHD();
        Tablekmsp();
        hienThiDoanhThu();
        lblDTNGAY.setText("0 $");
        lblHOADONNgay.setText("Số hóa đơn: 0");
        lblSOHANGngay.setText("Số lượng hàng: 0");
    }
/////////////////////////////////////// CAPJ NHẬT COMBOBOX ////////////////////////

    private void loadComboBoxData() {
        List<String> mauSacList = dao.getMauSacData();
        DefaultComboBoxModel<String> mauSacModel = new DefaultComboBoxModel<>(mauSacList.toArray(new String[0]));
        cbbMauSac.setModel(mauSacModel);

        // Tương tự, cập nhật cho các combobox khác (ChatLieu, SIZE, LOAI)
        List<String> chatLieuList = dao.getChatLieuData();
        DefaultComboBoxModel<String> chatLieuModel = new DefaultComboBoxModel<>(chatLieuList.toArray(new String[0]));
        cbbChatLieuj.setModel(chatLieuModel);

        List<String> kichthuoc = dao.getKichThuocData();
        DefaultComboBoxModel<String> kichthuocModel = new DefaultComboBoxModel<>(kichthuoc.toArray(new String[0]));
        cbbKichThuoc.setModel(kichthuocModel);

        List<String> LoaiList = dao.getLoaiData();
        DefaultComboBoxModel<String> loaiModel = new DefaultComboBoxModel<>(LoaiList.toArray(new String[0]));
        cbbLoaiSP.setModel(loaiModel);

        List<String> LoaIadsp = dao.getLoaiData();
        DefaultComboBoxModel<String> loaisp = new DefaultComboBoxModel<>(LoaIadsp.toArray(new String[0]));
        cbbLoaiSanPham.setModel(loaisp);
    }
// /////////////////////////////////////san pham ///////////////////////////////////////////

    private void setForm(SanPham cd) {
        txtMaCT.setText(cd.getMaSp());
        txtTenSP.setText(cd.getTenSp());
        cbbLoaiSP.setSelectedItem(cd.getLoai());
        txtDonGia.setText(Integer.toString(cd.getGia()));
        txtSoLuong.setText(Integer.toString(cd.getSoLuong()));
        cbbMauSac.setSelectedItem(cd.getMauSac());
        cbbKichThuoc.setSelectedItem(cd.getSize());
        cbbChatLieuj.setSelectedItem(cd.getChatLieu());
    }

    private void clearForm() {
        SanPham sp = new SanPham();
        this.setForm(sp);
    }

    public void load(int row) {
        if (row != -1) {
            String ma = (tblThongTinSP.getValueAt(row, 0) != null) ? tblThongTinSP.getValueAt(row, 0).toString() : "";
            String ten = (tblThongTinSP.getValueAt(row, 1) != null) ? tblThongTinSP.getValueAt(row, 1).toString() : "";
            String loai = (tblThongTinSP.getValueAt(row, 2) != null) ? tblThongTinSP.getValueAt(row, 2).toString() : "";
            String donG = (tblThongTinSP.getValueAt(row, 3) != null) ? tblThongTinSP.getValueAt(row, 3).toString() : "";
            String soL = (tblThongTinSP.getValueAt(row, 4) != null) ? tblThongTinSP.getValueAt(row, 4).toString() : "";
            String mauSac = (tblThongTinSP.getValueAt(row, 5) != null) ? tblThongTinSP.getValueAt(row, 5).toString() : "";
            String kichT = (tblThongTinSP.getValueAt(row, 6) != null) ? tblThongTinSP.getValueAt(row, 6).toString() : "";
            String chatL = (tblThongTinSP.getValueAt(row, 7) != null) ? tblThongTinSP.getValueAt(row, 7).toString() : "";

            txtMaCT.setText(ma);
            txtTenSP.setText(ten);
            cbbLoaiSP.setSelectedItem(loai);
            txtDonGia.setText(donG);
            txtSoLuong.setText(soL);
            cbbMauSac.setSelectedItem(mauSac);
            cbbKichThuoc.setSelectedItem(kichT);
            cbbChatLieuj.setSelectedItem(chatL);
        }
    }

    private void Table() {
        if (tblThongTinSP != null) {
            DefaultTableModel model = (DefaultTableModel) tblThongTinSP.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<SanPham> list = dao.selectAll12();

                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getMaSp(),
                        sp.getTenSp(),
                        sp.getLoai(),
                        sp.getGia(),
                        sp.getSoLuong(),
                        sp.getMauSac(),
                        sp.getSize(),
                        sp.getChatLieu()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private SanPham getForm() {
        SanPham cd = null;

        try {
            String tenSp = txtTenSP.getText().trim();
            String giaText = txtDonGia.getText().trim();
            String soLuongText = txtSoLuong.getText().trim();

            if (tenSp.isEmpty() && giaText.isEmpty() && soLuongText.isEmpty()) {
                MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin.");
            } else {
                int gia = Integer.parseInt(giaText);
                int soLuong = Integer.parseInt(soLuongText);

                if (gia <= 0 || soLuong <= 0) {
                    MsgBox.alert(this, "Đơn giá và số lượng phải lớn hơn 0.");
                } else if (tenSp.isEmpty()) {
                    MsgBox.alert(this, "Vui lòng nhập tên sản phẩm.");

                } else {
                    cd = new SanPham();
                    cd.setMaSp(txtMaCT.getText());
                    cd.setTenSp(tenSp);
                    cd.setLoai(cbbLoaiSP.getSelectedItem().toString());
                    cd.setGia(gia);
                    cd.setSoLuong(soLuong);
                    cd.setMauSac(cbbMauSac.getSelectedItem().toString());
                    cd.setSize(cbbKichThuoc.getSelectedItem().toString());
                    cd.setChatLieu(cbbChatLieuj.getSelectedItem().toString());
                }
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Vui lòng nhập số cho đơn giá và số lượng.");
        }

        return cd;
    }

    private void insert() {
        try {
            SanPham cd = getForm();
            if (cd != null) {
                dao.insertIntoSanPhamAndSanPhamChiTiet(cd);
                MsgBox.alert(this, "Thêm mới thành công!");
                this.Table();
                this.clearForm();
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Vui lòng nhập số cho đơn giá và số lượng.");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void update() {
        try {
            SanPham cd = getForm();
            if (cd != null) {
                dao.updateSanPhamAndSanPhamChiTiet(cd);
                MsgBox.alert(this, "Cập nhật thành công!");
                this.Table();
                this.clearForm();
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Vui lòng nhập số cho đơn giá và số lượng.");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật thất bại! Lỗi: " + e.getMessage());
        }
    }

//----//----THUỘC TÍNH SẢN PHẨM ----//----//
    // chất liệu
    private SanPham getFormCHATLIEU() {
        SanPham cl = new SanPham();
        cl.setTenChatLieu(txtThemThuocTinh.getText());
        cl.setID(Integer.parseInt(txtID.getText()));
        return cl;
    }

    private void setFormCHATLIEU(SanPham cd) {
        txtThemThuocTinh.setText(cd.getTenChatLieu());
        txtID.setText(String.valueOf(cd.getID()));
    }

    private void clearFormCHATLIEU() {
        SanPham sp = new SanPham();
        this.setFormCHATLIEU(sp);
    }

    public void loadCHATLIEU(int row) {
        if (row != -1) {
            String id = (tblMauSac.getValueAt(row, 0) != null) ? tblMauSac.getValueAt(row, 0).toString() : "";
            txtID.setText(id);
            String tenCL = (tblChatLieu.getValueAt(row, 2) != null) ? tblChatLieu.getValueAt(row, 2).toString() : "";
            txtThemThuocTinh.setText(tenCL);
        }
    }

    private void TableCHATLIEU() {
        if (tblChatLieu != null) {
            DefaultTableModel model = (DefaultTableModel) tblChatLieu.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<SanPham> list = dao.selectAllChatLieu();
                String chatlieu = "Chất liệu";
                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getID(),
                        chatlieu,
                        sp.getTenChatLieu()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private void insertCHATL() {
        try {
            SanPham chatLieu = getFormCHATLIEU();
            dao.insertIntoChatLieu(chatLieu);
            this.TableCHATLIEU(); // Cập nhật lại dữ liệu trên giao diện
            this.clearFormCHATLIEU(); // Xóa trắng các trường nhập liệu
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updateCHATL() {
        try {
            SanPham chatLieu = getFormCHATLIEU();
            dao.updateChatLieu(chatLieu);
            this.TableCHATLIEU();
            this.clearFormCHATLIEU();
            MsgBox.alert(this, "Cập nhật mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    //màu sắc 
    private SanPham getFormMAU() {
        SanPham ms = new SanPham();
        ms.setTenMau(txtThemThuocTinh.getText());
        ms.setID(Integer.parseInt(txtID.getText()));
        return ms;
    }

    private void setFormMAU(SanPham cdm) {
        txtThemThuocTinh.setText(cdm.getTenMau());
        txtID.setText(String.valueOf(cdm.getID()));
    }

    private void clearFormMAU() {
        SanPham sp = new SanPham();
        this.setFormMAU(sp);
    }

    public void loadMAU(int row) {
        if (row != -1) {
            String id = (tblMauSac.getValueAt(row, 0) != null) ? tblMauSac.getValueAt(row, 0).toString() : "";
            txtID.setText(id);
            String tenCL = (tblMauSac.getValueAt(row, 2) != null) ? tblMauSac.getValueAt(row, 2).toString() : "";
            txtThemThuocTinh.setText(tenCL);
        }
    }

    private void TableMAU() {
        if (tblMauSac != null) {
            DefaultTableModel model = (DefaultTableModel) tblMauSac.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<SanPham> list = dao.selectAllMauSac();
                String chatlieu = "Màu sắc";
                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getID(),
                        chatlieu,
                        sp.getTenMau()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private void insertMAU() {
        try {
            SanPham ms = getFormMAU();
            dao.insertIntoMauSac(ms);
            this.TableMAU();
            this.clearFormMAU();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updateMAU() {
        try {
            SanPham ms = getFormMAU();
            dao.updateMauSac(ms);
            this.TableMAU();
            this.clearFormMAU();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    //SIZE
    private SanPham getFormSIZE() {
        SanPham cl = new SanPham();
        cl.setTenSize(txtThemThuocTinh.getText());
        cl.setID(Integer.parseInt(txtID.getText()));

        return cl;
    }

    private void setFormSIZE(SanPham cd) {
        txtThemThuocTinh.setText(cd.getTenSize());
        txtID.setText(String.valueOf(cd.getID()));

    }

    private void clearFormSIZE() {
        SanPham sp = new SanPham();
        this.setFormSIZE(sp);
    }

    public void loadSIZE(int row) {
        if (row != -1) {
            String id = (tblKichThuoc.getValueAt(row, 0) != null) ? tblKichThuoc.getValueAt(row, 0).toString() : "";
            txtID.setText(id);
            String tenCL = (tblKichThuoc.getValueAt(row, 2) != null) ? tblKichThuoc.getValueAt(row, 2).toString() : "";
            txtThemThuocTinh.setText(tenCL);
        }
    }

    private void TableSIZE() {
        if (tblKichThuoc != null) {
            DefaultTableModel model = (DefaultTableModel) tblKichThuoc.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<SanPham> list = dao.selectAllSIZE();
                String chatlieu = "Kích thước";
                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getID(),
                        chatlieu,
                        sp.getTenSize()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private void insertSIZE() {
        try {
            SanPham cd = getFormSIZE();
            dao.insertIntoSIZE(cd);
            this.TableSIZE();
            this.clearFormSIZE();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updateSIZE() {
        try {
            SanPham cd = getFormSIZE();
            dao.updateSIZE(cd);
            this.TableSIZE();
            this.clearFormSIZE();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    // Loai
    private SanPham getFormLOAI() {
        SanPham cl = new SanPham();
        cl.setTenLoai(txtThemThuocTinh.getText());
        cl.setID(Integer.parseInt(txtID.getText()));
        return cl;
    }

    private void setFormLOAI(SanPham cd) {
        txtThemThuocTinh.setText(cd.getTenLoai());
        txtID.setText(String.valueOf(cd.getID()));

    }

    private void clearFormLOAI() {
        SanPham sp = new SanPham();
        this.setFormLOAI(sp);
    }

    public void loadLOAI(int row) {
        if (row != -1) {
            String id = (tblTenLoaiSanPham.getValueAt(row, 0) != null) ? tblTenLoaiSanPham.getValueAt(row, 0).toString() : "";
            txtID.setText(id);
            String tenCL = (tblTenLoaiSanPham.getValueAt(row, 2) != null) ? tblTenLoaiSanPham.getValueAt(row, 2).toString() : "";
            txtThemThuocTinh.setText(tenCL);
        }
    }

    private void TableLOAI() {
        if (tblTenLoaiSanPham != null) {
            DefaultTableModel model = (DefaultTableModel) tblTenLoaiSanPham.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<SanPham> list = dao.selectAllLOAI();
                String chatlieu = "Loại sản phẩm";
                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getID(),
                        chatlieu,
                        sp.getTenLoai()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private void insertLOAI() {
        try {
            SanPham cd = getFormLOAI();
            dao.insertIntoLOAI(cd);
            this.TableLOAI();
            this.clearFormLOAI();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updateLOAI() {
        try {
            SanPham cd = getFormLOAI();
            dao.updateLOAI(cd);
            this.TableLOAI();
            this.clearFormLOAI();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    ///////////////////////////////////////////////////TÀI KHOẢN////////////////////////////////////////////////////////
    private void setFormtk(TaiKhoan cd) {
        txtMaTk.setText(cd.getMaNv());
        txtMatKhau.setText(cd.getMatKhau());

        Boolean vaiTro = cd.getVaiTro();
        if (vaiTro != null) {
            if (vaiTro) {
                rdoNV.setSelected(true);
                rdoQL.setSelected(false);
            } else {
                rdoNV.setSelected(false);
                rdoQL.setSelected(true);
            }
        } else {
            rdoNV.setSelected(false);
            rdoQL.setSelected(false);
        }
    }

    private void clearFormtk() {
        TaiKhoan sp = new TaiKhoan();
        this.setFormtk(sp);
    }

    public void loadtk(int row) {
        if (row != -1) {
            String manv = (tblTaiKhoan.getValueAt(row, 1) != null) ? tblTaiKhoan.getValueAt(row, 1).toString() : "";
            String matK = (tblTaiKhoan.getValueAt(row, 2) != null) ? tblTaiKhoan.getValueAt(row, 2).toString() : "";
            String vaiT = (tblTaiKhoan.getValueAt(row, 3) != null) ? tblTaiKhoan.getValueAt(row, 3).toString() : "";

            txtMaTk.setText(manv);
            txtMatKhau.setText(matK);

            if ("Quản lý".equals(vaiT)) {
                rdoQL.setSelected(true);
                rdoNV.setSelected(false);
            } else if ("Nhân viên".equals(vaiT)) {
                rdoQL.setSelected(false);
                rdoNV.setSelected(true);
            }
        }
    }

    private void Tabletk() {
        if (tblTaiKhoan != null) {
            DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<TaiKhoan> list = tk.selectAlltk();
                int i = 1;
                for (TaiKhoan sp : list) {
                    String vaiTroText = (sp.getVaiTro()) ? "Quản lý" : "Nhân viên";
                    Object[] row = {
                        i++,
                        sp.getMaNv(),
                        sp.getMatKhau(),
                        vaiTroText // Sử dụng vaiTroText thay vì sp.getVaiTro()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private TaiKhoan getFormtk() throws Exception {
        TaiKhoan cd = new TaiKhoan();

        String maNv = txtMaTk.getText().trim();
        if (!maNv.isEmpty()) {
            cd.setMaNv(maNv);
        } else {
            throw new Exception("Vui lòng nhập mã nhân viên.");
        }

        String matKhau = txtMatKhau.getText().trim();
        if (!matKhau.isEmpty()) {
            cd.setMatKhau(matKhau);
        } else {
            throw new Exception("Vui lòng nhập mật khẩu.");
        }

        cd.setVaiTro(rdoQL.isSelected()); // True nếu Quản lý, False nếu Nhân viên

        return cd;
    }

    private void inserttk() {
        try {
            TaiKhoan cd = getFormtk();
            tk.inserttk(cd);
            this.Tabletk();
            this.clearFormtk();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updatetk() {
        try {
            TaiKhoan cd = getFormtk();
            tk.updatetk(cd);
            this.Tabletk();
            this.clearFormtk();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void deletetk() {
        try {
            String maNV = txtMaTk.getText();
            if (maNV.isEmpty()) {
                MsgBox.alert(this, "Mã sản phẩm không được để trống.");
                return;
            } else {
                // Thực hiện xóa chuyên đề trong cơ sở dữ liệu
                tk.deletetk(maNV);
                this.Tabletk();
                this.clearFormtk();
                MsgBox.alert(this, "Xóa thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Xóa thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updateTableTaiKhoan(List<TaiKhoan> productList) {
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        int i = 1;
        for (TaiKhoan sp : productList) {
            String vaiTroText = (sp.getVaiTro()) ? "Quản lý" : "Nhân viên";

            Object[] row = {
                i++,
                sp.getMaNv(),
                sp.getMatKhau(),
                vaiTroText // Sử dụng vaiTroText thay vì sp.getVaiTro()
            };
            model.addRow(row);
        }
    }
////////////////////////////////////////// HÓA ĐƠN //////////////////////////////////////////////////

    private void initHD() {
        fillTableDSHoaDon(listAll);
        fillTableDSSP(listAllSP);
    }
    HoaDonService hdservice = new HoaDonService();
    List<HoaDon> listAll = hdservice.selectAll();
    List<SanPham> listAllSP = hdservice.DSSanPham();

    private void fillTableDSHoaDon(List<HoaDon> list) {
        DefaultTableModel model = (DefaultTableModel) tblDanhSachHoaDon.getModel();
        model.setRowCount(0);
        // Lấy danh sách hóa đơn từ service hoặc nguồn dữ liệu phù hợp
        list = hdservice.selectAll();
        // Thêm những hóa đơn có trạng thái là "Đang tạo" vào bảng
        for (HoaDon hd : list) {
            if (!hd.getTrangThai()) { // Trạng thái là false (0 - Đang tạo)
                String trangThai = hd.getTrangThai() ? "Đã thanh toán" : "Đang tạo";
                Object[] row = {hd.getMaHD(), hd.getNgayLap(), hd.getMaNv(), trangThai};
                model.addRow(row);
            }
        }
    }

    public void loadhd(int row) {
        if (row != -1) {
            String mahd = (tblDanhSachHoaDon.getValueAt(row, 0) != null) ? tblDanhSachHoaDon.getValueAt(row, 0).toString() : "";

            txtMaHD.setText(mahd);

        }
    }

    void fillTableDSSP(List<SanPham> list) {
        DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
        model.setRowCount(0);

        list = hdservice.DSSanPham();

        for (SanPham sp : list) {
            Object[] row = {sp.getMaSp(), sp.getTenSp(), sp.getMauSac(), sp.getChatLieu(), sp.getSize(), sp.getSoLuong(), sp.getGia()};
            model.addRow(row);
        }
        Table();
    }

    void setformHD(int index) {
        List<HoaDon> listAll = hdservice.getAllHD();
        HoaDon hd = listAll.get(index);
        txtMaHD.setText(hd.getMaHD() + "");
        txtTenKhachHang.setText(hd.getTenKH());
        txtMaNV.setText(hd.getMaNv());
        txtNgayLap.setText(hd.getNgayLap() + "");
        cboHinhThucThanhToan.setSelectedItem(hd.getMaVoucher());
        lblTongTien.setText(hd.getTongTien() + "");
    }

    void clearFormHD() {
        txtMaHD.setText("");
        txtTenKhachHang.setText("");
        txtMaNV.setText("");
        txtNgayLap.setText(XDate.toString(XDate.now(), "dd-MM-yyyy"));
        cboHinhThucThanhToan.setSelectedIndex(0);
        lblTongTien.setText("-");
        lblTienTraKhach.setText("-");
        txtGhichu.setText("");
        txtTienKhachDua.setText("");
    }

    private HoaDon getFormhd() {
        HoaDon cd = new HoaDon();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date parsedDate = dateFormat.parse(txtNgayLap.getText());
            cd.setNgayLap(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace(); // Xử lý hoặc báo lỗi nếu có vấn đề trong quá trình chuyển đổi
        }
        cd.setMaNv(txtMaNV.getText());
        return cd;
    }

    private void inserthd() {
        try {
            HoaDon cd = getFormhd();
            hd.inserthd(cd);
            this.fillTableDSHoaDon(listAll);
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private int promptForNewQuantity(String productName, int currentQuantity) {
        String input = JOptionPane.showInputDialog(null, "Nhập số lượng mới cho sản phẩm " + productName + ":",
                "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);
        try {
            if (input != null) {
                int newQuantity = Integer.parseInt(input);
                if (newQuantity >= 0) {
                    return newQuantity;
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng không âm.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên hợp lệ.");
        }
        return -1; // Trả về -1 nếu có lỗi hoặc người dùng hủy bỏ
    }

/////////////////////////////////////////////////////////Gio hang////////////////////////////////////////////////////
    private void addToShoppingCart(String maSp, String tenSp, int quantity) {
        try {
            // Lấy thông tin sản phẩm từ cơ sở dữ liệu dựa trên mã sản phẩm
            SanPham sanPham = dao.getGiaByMaSP(maSp);
            int gia = sanPham.getGia(); // Lấy giá từ đối tượng SanPham
            String size = sanPham.getSize(); // Lấy thông tin về size
            int giamGia = sanPham.getGiamGia(); // Lấy thông tin về giảm giá
            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
            DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
            boolean found = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                String maSanPham = model.getValueAt(i, 0).toString(); // Lấy mã sản phẩm từ bảng giỏ hàng
                if (maSanPham.equals(maSp)) {
                    // Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng mới cho sản phẩm
                    int currentQuantity = Integer.parseInt(model.getValueAt(i, 3).toString());
                    int newQuantity = currentQuantity + quantity;
                    int thanhTien = newQuantity * gia - newQuantity * giamGia; // Tính toán ThanhTien mới
                    model.setValueAt(newQuantity, i, 3); // Cập nhật số lượng mới cho sản phẩm
                    model.setValueAt(thanhTien, i, 6); // Cập nhật ThanhTien mới cho sản phẩm
                    found = true;
                    break;
                }
            }
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm hàng mới vào bảng giỏ hàng
            if (!found) {
                Object[] row = {maSp, tenSp, size, quantity, gia, giamGia, quantity * gia - quantity * giamGia}; // Thêm thông tin size và giảm giá vào hàng mới
                model.addRow(row); // Thêm hàng mới vào bảng giỏ hàng
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu giỏ hàng!");
        }
        calculateTotal();
    }

    private void calculateTotal() {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int rowCount = model.getRowCount();
        int total = 0;
        // Tính tổng số tiền từ cột ThanhTien trong tblGioHang
        for (int i = 0; i < rowCount; i++) {
            int thanhTien = Integer.parseInt(model.getValueAt(i, 6).toString()); // ThanhTien ở cột thứ 6
            total += thanhTien;
        }
        // Hiển thị tổng số tiền trên lblTongTien
        lblTongTien.setText(String.valueOf(total));
    }

    // NÚT HỦY TẠO HÓA ĐƠN
    private void returnQuantitiesToDanhSachSanPham() {
        DefaultTableModel modelGioHang = (DefaultTableModel) tblGioHang.getModel();
        DefaultTableModel modelDanhSachSanPham = (DefaultTableModel) tblDanhSachSP.getModel();

        for (int i = 0; i < modelGioHang.getRowCount(); i++) {
            String maSPGioHang = modelGioHang.getValueAt(i, 0).toString();
            int soLuongGioHang = Integer.parseInt(modelGioHang.getValueAt(i, 3).toString());
            // Tìm và cập nhật số lượng sản phẩm trong tblDanhSachSanPham
            for (int j = 0; j < modelDanhSachSanPham.getRowCount(); j++) {
                String maSPDanhSachSanPham = modelDanhSachSanPham.getValueAt(j, 0).toString();

                if (maSPGioHang.equals(maSPDanhSachSanPham)) {
                    int soLuongHienTai = Integer.parseInt(modelDanhSachSanPham.getValueAt(j, 5).toString());
                    int soLuongMoi = soLuongHienTai + soLuongGioHang;
                    modelDanhSachSanPham.setValueAt(soLuongMoi, j, 5); // Cập nhật số lượng mới
                    break;
                }
            }
        }
        // Cập nhật lại hiển thị của tblDanhSachSanPham (nếu cần)
        fillTableDSSP(listAllSP);
    }

    ////  /////////////////////////// THANH TOAN (HOA DON CHI TIET) //////////////////////////////////////////////////////
    private void insertHoaDonChiTiet() {
        try {
            String maHD = txtMaHD.getText();
            String tenKhachHang = txtTenKhachHang.getText();
            String hinhThucThanhToan = cboHinhThucThanhToan.getSelectedItem().toString();
            String ghiChu = txtGhichu.getText();
            if (maHD.isEmpty() || tenKhachHang.isEmpty() || hinhThucThanhToan.isEmpty()) {
                MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin!");
                return; // Dừng việc thực hiện thêm dữ liệu nếu thiếu thông tin
            }
            int tongTienvld = Integer.parseInt(lblTongTien.getText());
            int tienKhachTravld = Integer.parseInt(txtTienKhachDua.getText());
            if (tienKhachTravld < tongTienvld) {
                MsgBox.alert(this, "Số tiền khách đưa không đủ!");
                return; // Dừng việc thực hiện thanh toán
            }
            DefaultTableModel modelGioHang = (DefaultTableModel) tblGioHang.getModel();
            for (int i = 0; i < modelGioHang.getRowCount(); i++) {
                String maSP = modelGioHang.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 3).toString());
                int tongTien = Integer.parseInt(modelGioHang.getValueAt(i, 6).toString());
                // Thực hiện insert dữ liệu vào bảng HoaDonChiTiet từ giá trị thu thập được
                HoaDon hoaDonChiTiet = new HoaDon();
                hoaDonChiTiet.setMaHD(Integer.parseInt(maHD));
                hoaDonChiTiet.setTenKH(tenKhachHang);
                hoaDonChiTiet.setMaSp(maSP);
                hoaDonChiTiet.setSoLuong(soLuong);
                hoaDonChiTiet.setGiaTien(tongTien);
                hoaDonChiTiet.setHinhThucTT(hinhThucThanhToan);
                hoaDonChiTiet.setGhiChu(ghiChu);
                // Gọi phương thức insertHoaDonChiTiet trong HoaDonChiTietService để thêm dữ liệu vào cơ sở dữ liệu
                hd.inserthdct(hoaDonChiTiet);
                HoaDon hoaDon = hd.selectByIdhd(Integer.parseInt(maHD));
                if (hoaDon != null) {
                    hoaDon.setTrangThai(true);
                    hd.updateHoaDonStatus(hoaDon.getMaHD(), true);
                    fillTableDSHoaDon(listAll);
                }
                fillTableLICHSU(listAll);
            }
            updateSanPhamQuantity(tblGioHang);
            DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
            model.setRowCount(0);
            clearFormHD();
            Table();
            TableCTSPDoanhThu();
            TableDoanhThu();
            LoadTable();
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi khi thêm dữ liệu vào bảng HoaDonChiTiet!");
        }
    }

    private void updateSanPhamQuantity(JTable tblGioHang) {
        SanPhamService dao = new SanPhamService(); // Khai báo và khởi tạo đối tượng SanPhamService

        try {
            DefaultTableModel modelGioHang = (DefaultTableModel) tblGioHang.getModel();

            for (int i = 0; i < modelGioHang.getRowCount(); i++) {
                String maSP = modelGioHang.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 3).toString());

                int currentQuantity = dao.getSoLuongByMaSP(maSP);

                if (currentQuantity >= soLuong) {
                    int newQuantity = currentQuantity - soLuong;
                    dao.updateSoLuong(maSP, newQuantity);
                } else {
                    MsgBox.alert(this, "Sản phẩm không đủ số lượng!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi cập nhật dữ liệu bảng SanPham
        }
    }
    ///////////////////////////////////////////////// Lịch sử ////////////////////////////////////////////////////

//    private void fillTableLICHSU(List<HoaDon> list) {
//        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
//        model.setRowCount(0);
//        // Lấy danh sách hóa đơn từ service hoặc nguồn dữ liệu phù hợp
//        list = hdservice.selectAlLICHSU();
//        Map<Integer, HoaDon> mergedData = hd.mergeByMaHD(list); // Gọi phương thức từ service
//        // Thêm những hóa đơn có trạng thái là "Đang tạo" vào bảng
//            list.sort(Comparator.comparing(HoaDon::getNgayLap).reversed());
//
//        int i = 1;
//        for (HoaDon hd : mergedData.values()) {
//            String trangThai = hd.getTrangThai() ? "Đã thanh toán" : "Đang tạo";
//            Object[] row = {i++, hd.getMaHD(), hd.getNgayLap(), hd.getMaNv(), trangThai, hd.getTenKH()};
//            model.addRow(row);
//        }
//    }
    private void fillTableLICHSU(List<HoaDon> list) {
        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
        model.setRowCount(0);
        // Lấy danh sách hóa đơn từ service hoặc nguồn dữ liệu phù hợp
        list = hdservice.selectAlLICHSU();
        // Sắp xếp hóa đơn theo thời gian giảm dần
        list.sort(Comparator.comparing(HoaDon::getNgayLap).reversed());
        // Gọi phương thức từ service để hợp nhất các hóa đơn có cùng mã hóa đơn
        Map<Integer, HoaDon> mergedData = hd.mergeByMaHD(list);
        // Thêm những hóa đơn có trạng thái là "Đang tạo" vào bảng
        int i = 1;
        for (HoaDon hd : mergedData.values()) {
            String trangThai = hd.getTrangThai() ? "Đã thanh toán" : "Đang tạo";
            Object[] row = {i++, hd.getMaHD(), hd.getNgayLap(), hd.getMaNv(), trangThai, hd.getTenKH()};
            model.addRow(row);
        }
    }

    //////////////////////////////////////// NHAN VIEN //////////////////////////////////////////////////////////////////
    private void setFormnv(NhanVien cd) {
        txtMNV.setText(cd.getMaNV());
        txtHOTEN.setText(cd.getHoTen());
        txtNGAYSINH.setText(cd.getNgaySinh() + "");
        txtDIACHI.setText(cd.getDiaChi());
        txtEMAIL.setText(cd.getEmail());
    }

    private void clearFormnv() {
        NhanVien sp = new NhanVien();
        this.setFormnv(sp);
    }

    public void loadnv(int row) {
        if (row != -1) {
            String manv = (tblNhanVien.getValueAt(row, 1) != null) ? tblNhanVien.getValueAt(row, 1).toString() : "";
            String hoten = (tblNhanVien.getValueAt(row, 2) != null) ? tblNhanVien.getValueAt(row, 2).toString() : "";
            String ngayssinh = (tblNhanVien.getValueAt(row, 3) != null) ? tblNhanVien.getValueAt(row, 3).toString() : "";
            String diachi = (tblNhanVien.getValueAt(row, 4) != null) ? tblNhanVien.getValueAt(row, 4).toString() : "";
            String email = (tblNhanVien.getValueAt(row, 5) != null) ? tblNhanVien.getValueAt(row, 5).toString() : "";

            txtMNV.setText(manv);
            txtHOTEN.setText(hoten);
            txtNGAYSINH.setText(ngayssinh);
            txtDIACHI.setText(diachi);
            txtEMAIL.setText(email);
        }
    }

    private void Tablenv() {
        if (tblNhanVien != null) {
            DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<NhanVien> list = nv.selectAlltk();
                int i = 1;
                for (NhanVien sp : list) {
                    Object[] row = {
                        i++,
                        sp.getMaNV(),
                        sp.getHoTen(),
                        sp.getNgaySinh(),
                        sp.getDiaChi(),
                        sp.getEmail()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tblNhanVien là null.");
        }
    }

    private NhanVien getFormnv() throws Exception {
        NhanVien cd = new NhanVien();

        String maNV = txtMNV.getText().trim();
        if (!maNV.isEmpty()) {
            cd.setMaNV(maNV);
        } else {
            throw new Exception("Vui lòng nhập mã nhân viên.");
        }

        String hoTen = txtHOTEN.getText().trim();
        if (!hoTen.isEmpty()) {
            cd.setHoTen(hoTen);
        } else {
            throw new Exception("Vui lòng nhập họ tên nhân viên.");
        }

        String ngaySinhText = txtNGAYSINH.getText().trim();
        if (!ngaySinhText.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date parsedDate = dateFormat.parse(ngaySinhText);
                cd.setNgaySinh(parsedDate);
            } catch (ParseException e) {
                throw new Exception("Ngày sinh không hợp lệ. Nhập theo định dạng ngày-tháng-năm (dd-MM-yyyy).");
            }
        } else {
            throw new Exception("Vui lòng nhập ngày sinh.");
        }

        String diaChi = txtDIACHI.getText().trim();
        if (!diaChi.isEmpty()) {
            cd.setDiaChi(diaChi);
        } else {
            throw new Exception("Vui lòng nhập địa chỉ nhân viên.");
        }

        String email = txtEMAIL.getText().trim();
        if (!email.isEmpty()) {
            cd.setEmail(email);
        } else {
            throw new Exception("Vui lòng nhập email nhân viên.");
        }

        return cd;
    }

    private void insertnv() {
        try {
            NhanVien cd = getFormnv();
            nv.inserttk(cd);
            this.Tablenv();
            this.clearFormtk();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updatenv() {
        try {
            NhanVien cd = getFormnv();
            nv.updatetk(cd);
            this.Tablenv();
            this.clearFormtk();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void deletenv() {
        try {
            String maNV = txtMNV.getText();
            if (maNV.isEmpty()) {
                MsgBox.alert(this, "Mã sản phẩm không được để trống.");
                return;
            } else {
                // Thực hiện xóa chuyên đề trong cơ sở dữ liệu
                nv.deletetk(maNV);
                this.Tablenv();
                this.clearFormtk();
                MsgBox.alert(this, "Xóa thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Xóa thất bại! Lỗi: " + e.getMessage());
        }
    }
///////////////////////////////// DOANH THU (THỐNG KÊ) /////////////////////////////////////////////////
//////////////////////////////////// BIỂU ĐỒ //////////////////////////////////////////////////////////
    // Hàm tạo biểu đồ từ dữ liệu của JTable
    double doanhThuDouble;

    private void createChartFromTable() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultTableModel model = (DefaultTableModel) tblChiTietDoanhThu.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String thang = String.valueOf(model.getValueAt(i, 0));
            int doanhThu1 = Integer.parseInt(String.valueOf(model.getValueAt(i, 4)));
            // Chuyển đổi thành kiểu double
            doanhThuDouble = Double.valueOf(doanhThu1);
            // Thêm dữ liệu vào dataset của biểu đồ
            dataset.addValue(doanhThuDouble, "Doanh thu", thang);
        }
        // Tạo biểu đồ cột từ dataset
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ doanh thu theo tháng", // Tiêu đề biểu đồ
                "Tháng", // Label trục x
                "Doanh thu (VNĐ)", // Label trục y
                dataset
        );
        // Tạo ChartPanel từ biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(763, 417));
        bd.removeAll();

        // Thêm biểu đồ vào giao diện
        Object[] row = {
            "Tháng",
            "Số lượng bán",
            "Tổng giá bán",
            "Tổng giá giảm giá",
            doanhThuDouble
        };
        model.addRow(row);
        // Cập nhật lại bieudo để hiển thị biểu đồ
        bd.revalidate();
        bd.repaint();
    }

    private void TableDoanhThu() {
        if (tblChiTietDoanhThu != null) {
            DefaultTableModel model = (DefaultTableModel) tblChiTietDoanhThu.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<ThongKe> list = doanhThu.selectAllDT();
                for (ThongKe sp : list) {
                    Object[] row = {
                        sp.getThang(),
                        sp.getSoLuongBan(),
                        sp.getTongGiaBan(),
                        sp.getTongGiaGiam(),
                        sp.getDoanhThu()
                    };
                    model.addRow(row);
                }
//                createChartFromTable();
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private void TableCTSPDoanhThu() {
        if (tblChiTietSanPhamDoanhThu != null) {
            DefaultTableModel model = (DefaultTableModel) tblChiTietSanPhamDoanhThu.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có

            try {
                List<SanPham> list = dao.selectAllCTSPDT();
                // Sắp xếp danh sách sản phẩm theo số lượng từ thấp đến cao
                Collections.sort(list, Comparator.comparingInt(SanPham::getSoLuong));
                int i = 1;
                for (SanPham sp : list) {
                    Object[] row = {
                        i++,
                        sp.getMaSp(),
                        sp.getTenSp(),
                        sp.getLoai(),
                        sp.getMauSac(),
                        sp.getSize(),
                        sp.getChatLieu(),
                        sp.getSoLuong()
                    };
                    model.addRow(row);

                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(1).setCellRenderer(new CustomRenderer());
                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(2).setCellRenderer(new CustomRenderer());
                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(3).setCellRenderer(new CustomRenderer());
                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(4).setCellRenderer(new CustomRenderer());
                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(5).setCellRenderer(new CustomRenderer());
                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(6).setCellRenderer(new CustomRenderer());
                    tblChiTietSanPhamDoanhThu.getColumnModel().getColumn(7).setCellRenderer(new CustomRenderer());
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    /////////////////////////////////////////////// KHUYẾN MẠI /////////////////////////////////////////////////
    private void setFormkm(Vourcher cd) {
        txtMAKM.setText(String.valueOf(cd.getMaVourcher()));
        txtTENKM.setText(cd.getTenVoucher());
        dateNgayBD.setDate(cd.getNgayBatDau());
        dateNgayKT.setDate(cd.getNgayKetThuc());
        txtGIAMGIA.setText(String.valueOf(cd.getGiamGia()));
        Boolean trangT = cd.getTrangThai();
        if (trangT != null) {
            if (trangT) {
                rdoHD.setSelected(true);
                rdoHHD.setSelected(false);
            } else {
                rdoHD.setSelected(false);
                rdoHHD.setSelected(true);
            }
        } else {
            rdoHD.setSelected(false);
            rdoHHD.setSelected(false);
        }
    }

    private void clearFormkm() {
        Vourcher sp = new Vourcher();
        this.setFormkm(sp);
    }

    public void loadkm(int row) {
        if (row != -1) {
            String makm = (tblKhuyenMai.getValueAt(row, 0) != null) ? tblKhuyenMai.getValueAt(row, 0).toString() : "";
            String tenkm = (tblKhuyenMai.getValueAt(row, 1) != null) ? tblKhuyenMai.getValueAt(row, 1).toString() : "";
            String ngaybd = (tblKhuyenMai.getValueAt(row, 2) != null) ? tblKhuyenMai.getValueAt(row, 2).toString() : "";
            String ngaykt = (tblKhuyenMai.getValueAt(row, 3) != null) ? tblKhuyenMai.getValueAt(row, 3).toString() : "";
            String giamgia = (tblKhuyenMai.getValueAt(row, 4) != null) ? tblKhuyenMai.getValueAt(row, 4).toString() : "";
            String trangthai = (tblKhuyenMai.getValueAt(row, 5) != null) ? tblKhuyenMai.getValueAt(row, 5).toString() : "";

            txtMAKM.setText(makm);
            txtTENKM.setText(tenkm);

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date ngayBatDau = dateFormat.parse(ngaybd);
                Date ngayKetThuc = dateFormat.parse(ngaykt);
                dateNgayBD.setDate(ngayBatDau);
                dateNgayKT.setDate(ngayKetThuc);
            } catch (ParseException ex) {
                // Xử lý ngoại lệ khi chuyển đổi ngày
                ex.printStackTrace();
            }
            txtGIAMGIA.setText(giamgia);
            if ("Hoạt động".equals(trangthai)) {
                rdoHD.setSelected(true);
                rdoHHD.setSelected(false);
            } else if ("Hết hoạt động".equals(trangthai)) {
                rdoHD.setSelected(false);
                rdoHHD.setSelected(true);
            }
        }
    }

    private void Tablekm() {
        if (tblKhuyenMai != null) {
            DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có
            try {
                List<Vourcher> list = km.selectAll();
                for (Vourcher sp : list) {
                    String trangThaiText = (sp.getTrangThai()) ? "Hoạt động" : "Hết hoạt động";
                    Object[] row = {
                        sp.getMaVourcher(),
                        sp.getTenVoucher(),
                        sp.getNgayBatDau(),
                        sp.getNgayKetThuc(),
                        sp.getGiamGia(), trangThaiText
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tblNhanVien là null.");
        }
    }

    private Vourcher getFormkm() throws Exception {
        Vourcher cd = new Vourcher();

        // Kiểm tra và thiết lập MaVourcher
        String maVourcherText = txtMAKM.getText().trim();
        if (!maVourcherText.isEmpty()) {
            cd.setMaVourcher(Integer.parseInt(maVourcherText));
        } else {
            throw new Exception("Vui lòng nhập mã Voucher.");
        }

        // Kiểm tra và thiết lập TenVoucher
        String tenVoucher = txtTENKM.getText().trim();
        if (!tenVoucher.isEmpty()) {
            cd.setTenVoucher(tenVoucher);
        } else {
            throw new Exception("Vui lòng nhập tên Voucher.");
        }

        // Kiểm tra và thiết lập NgayBatDau và NgayKetThuc
        Date ngayBatDau = dateNgayBD.getDate();
        Date ngayKetThuc = dateNgayKT.getDate();
        if (ngayBatDau != null && ngayKetThuc != null && ngayBatDau.before(ngayKetThuc)) {
            cd.setNgayBatDau(ngayBatDau);
            cd.setNgayKetThuc(ngayKetThuc);
        } else {
            throw new Exception("Ngày bắt đầu phải bé hơn ngày kết thúc.");
        }

        // Kiểm tra và thiết lập GiamGia
        String giamGiaText = txtGIAMGIA.getText().trim();
        if (!giamGiaText.isEmpty()) {
            int giamGia = Integer.parseInt(giamGiaText);
            if (giamGia >= 0) {
                cd.setGiamGia(giamGia);
            } else {
                throw new Exception("Giảm giá phải lớn hơn hoặc bằng 0.");
            }
        } else {
            throw new Exception("Vui lòng nhập giảm giá.");
        }

        cd.setTrangThai(rdoHD.isSelected());

        return cd;
    }

    private void insertkm() {
        try {
            Vourcher cd = getFormkm();
            km.inserthd(cd);
            this.Tablekm();
            this.clearFormkm();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    private void updatekm() {
        try {
            Vourcher cd = getFormkm();
            km.updatevc(cd);
            this.Tablekm();
            this.clearFormkm();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    ////////////////////////..... UPDATE KHUYEN MAI KHI HẾT HẠN................./////////////////////
    private Vourcher getFormkm1() {
        Vourcher cd = new Vourcher();
        try {
            if (!txtMAKM.getText().isEmpty()) {
                cd.setMaVourcher(Integer.parseInt(txtMAKM.getText()));
            } else {
                // Xử lý trường hợp khi txtMAKM rỗng
            }

            cd.setTenVoucher(txtTENKM.getText());
            Date ngayBatDau = dateNgayBD.getDate();
            Date ngayKetThuc = dateNgayKT.getDate();
            if (ngayBatDau != null && ngayKetThuc != null && ngayBatDau.before(ngayKetThuc)) {
                cd.setNgayBatDau(ngayBatDau);
                cd.setNgayKetThuc(ngayKetThuc);
            } else {
                // Xử lý thông báo lỗi hoặc cập nhật UI để báo lỗi về ngày không hợp lệ
            }

            if (!txtGIAMGIA.getText().isEmpty()) {
                cd.setGiamGia(Integer.parseInt(txtGIAMGIA.getText()));
            } else {
                // Xử lý trường hợp khi txtGIAMGIA rỗng
            }

            cd.setTrangThai(rdoHD.isSelected());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý lỗi NumberFormatException khi chuyển đổi chuỗi thành số
        }
        return cd;
    }

    private void updatekmHETHAN() {
        try {
            Vourcher cd = getFormkm1();
            km.updateTrangThai(cd);
            this.Tablekm();
            this.clearFormkm();
            LoadTable();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra nguyên nhân
        }
    }
    ////////////////////////////////////KHUYẾN MẠI (ÁP DỤNG KHYẾN MẠI)////////////////////////////////////

    private void TablekmHD() {
        if (tblDanhSachKhuyenMai != null) {
            DefaultTableModel model = (DefaultTableModel) tblDanhSachKhuyenMai.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có
            try {
                List<Vourcher> list = km.selectAll();
                for (Vourcher sp : list) {
                    if (sp.getTrangThai()) { // Kiểm tra trạng thái là "Hoạt động"
                        String trangThaiText = (sp.getTrangThai()) ? "Hoạt động" : "Hết hoạt động";
                        Object[] row = {
                            sp.getMaVourcher(),
                            sp.getTenVoucher(),
                            sp.getNgayBatDau(),
                            sp.getNgayKetThuc(),
                            sp.getGiamGia(),
                            trangThaiText
                        };
                        model.addRow(row);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tblNhanVien là null.");
        }
    }

    public void loaddskm(int row) {
        if (row != -1) {
            String makm = (tblDanhSachKhuyenMai.getValueAt(row, 0) != null) ? tblDanhSachKhuyenMai.getValueAt(row, 0).toString() : "";
            String giagiam = (tblDanhSachKhuyenMai.getValueAt(row, 4) != null) ? tblDanhSachKhuyenMai.getValueAt(row, 4).toString() : "";

            txtMAKHUYENMAI.setText(makm);
            txtGIAGIAMKHUYENMAI.setText(giagiam);
        }
    }

    private void Tablekmsp() {
        if (tblDanhSachSanPhamKhuyenMai != null) {
            DefaultTableModel model = (DefaultTableModel) tblDanhSachSanPhamKhuyenMai.getModel();
            model.setRowCount(0); // Xóa các hàng hiện có
            try {
                List<SanPham> list = dao.selectAll12();
                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getMaSp(),
                        sp.getTenSp(),
                        sp.getLoai(),
                        sp.getGia(),
                        sp.getSoLuong(),
                        sp.getMauSac(),
                        sp.getSize(),
                        sp.getChatLieu()
                    };
                    model.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace(); // In dãy đặc tả đầy đủ để gỡ lỗi
                MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        } else {
            System.out.println("Lỗi: tableSanPham là null.");
        }
    }

    private void clearFormADKM() {
        txtLOAISP.setText(""); // Xóa hoặc đặt giá trị rỗng cho txtLOAISP
        txtMAKHUYENMAI.setText(""); // Xóa hoặc đặt giá trị rỗng cho txtMAKHUYENMAI
        txtGIAGIAMKHUYENMAI.setText(""); // Xóa hoặc đặt giá trị rỗng cho txtGIAGIAMKHUYENMAI
    }

    private SanPham getFormkhuyenmai() throws Exception {
        SanPham cd = new SanPham();

        String loaiKMText = txtMALOAI.getText().trim();
        if (!loaiKMText.isEmpty()) {
            cd.setLoaiKM(Integer.parseInt(loaiKMText));
        } else {
            throw new Exception("Vui lòng nhập mã loại khuyến mãi.");
        }

        String saleText = txtMAKHUYENMAI.getText().trim();
        if (!saleText.isEmpty()) {
            cd.setSale(Integer.parseInt(saleText));
        } else {
            throw new Exception("Vui lòng nhập giá trị khuyến mãi.");
        }

        return cd;
    }

    private void updateKhuyenMai() {
        try {
            SanPham cd = getFormkhuyenmai();
            int loai = cd.getLoaiKM();
            int maKM = cd.getSale();
            dao.updateSanPhamByMaKM(loai, maKM);
            this.clearFormADKM();
            MsgBox.alert(this, "Cập nhật thành công!");
            this.fillTableDSSP(listAllSP);
        } catch (NumberFormatException e) {
            MsgBox.alert(this, "Vui lòng nhập số nguyên cho loại khuyến mãi và giá trị khuyến mãi.");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật thất bại! Lỗi: " + e.getMessage());
        }
    }

/////////////////////////////////////////// CÁC DOANH THU TRÊN CÙNG (4 CỤC MÀU) ///////////////////////////////////
    public void hienThiDoanhThu() {
        List<Integer> nam = doanhThu.getDoanhThuNam();
        int tongDoanhThuN = 0;
        // Tính tổng doanh thu từ danh sách doanh thu đã lấy
        for (Integer doanhThuN : nam) {
            tongDoanhThuN += doanhThuN;
        }
        // Hiển thị tổng doanh thu lên lblDTNam
        lblDTNam.setText(String.valueOf(tongDoanhThuN + " $"));

        List<Integer> thang = doanhThu.getDoanhThuThang();
        int tongDoanhThuT = 0;
        for (Integer doanhThuT : thang) {
            tongDoanhThuT += doanhThuT;
        }
        lblDTThang.setText(String.valueOf(tongDoanhThuT + " $"));

        List<Integer> shnam = doanhThu.getSOHANGNAM();
        int tongshnam = 0;
        for (Integer doanhThuT : shnam) {
            tongshnam += doanhThuT;
        }
        lblSOHANGNAM.setText(String.valueOf("Số lượng hàng: " + tongshnam));

        List<Integer> shthang = doanhThu.getSOHANGTHANG();
        int tongshthang = 0;
        for (Integer doanhThuT : shthang) {
            tongshthang += doanhThuT;
        }
        lblSOHANGTHANG.setText(String.valueOf("Số lượng hàng: " + tongshthang));

        List<Integer> hdnam = doanhThu.getHOADONNAM();
        int tonghdnam = 0;
        for (Integer doanhThuT : hdnam) {
            tonghdnam += doanhThuT;
        }
        lblHOADONNAM.setText(String.valueOf("Số hóa đơn: " + tonghdnam));

        List<Integer> hdthang = doanhThu.getHOADONTHANG();
        int tonghdthang = 0;
        for (Integer doanhThuT : hdthang) {
            tonghdthang += doanhThuT;
        }
        lblHOADONTHANG.setText(String.valueOf("Số hóa đơn: " + tonghdthang));
    }

    ////////////////////////// CÁC CHỨC NĂNG ỔN HẾT DỒI NHÉ ////////////////////////////////////
    public void hienThiDoanhTHUTHEONGAY() {
        List<Integer> ngay = doanhThu.getDOANHTHUNGAY();
        int tongDoanhThuN = 0;
        for (Integer doanhThuN : ngay) {
            tongDoanhThuN += doanhThuN;
        }
        lblDTNGAY.setText(String.valueOf(tongDoanhThuN + " $"));

        List<Integer> hdthang = doanhThu.getDONHANGNGAY();
        int tonghdthang = 0;
        for (Integer doanhThuT : hdthang) {
            tonghdthang += doanhThuT;
        }
        lblHOADONNgay.setText(String.valueOf("Số hóa đơn: " + tonghdthang));

        List<Integer> ngay2 = doanhThu.getSOLUONGNGAY();
        int tongDoanhThuN2 = 0;
        for (Integer doanhThuN : ngay2) {
            tongDoanhThuN2 += doanhThuN;
        }
        lblSOHANGngay.setText(String.valueOf("Số lượng hàng: " + tongDoanhThuN2));

    }

    public void hienThiDoanhTHUTHEONGAYKHITIM() {
        // Lấy ngày bắt đầu và ngày kết thúc từ JDateChooser
        Date startDate = new Date(ngaybatdau.getDate().getTime());
        Date endDate = new Date(ngayketthuc.getDate().getTime());

        // Gọi các phương thức để lấy dữ liệu từ CSDL trong khoảng thời gian được chọn
        List<Integer> doanhThuNgay = doanhThu.getDOANHTHUNGAYTIM(startDate, endDate);
        List<Integer> soHoaDonNgay = doanhThu.getDONHANGNGAYTIM(startDate, endDate);
        List<Integer> soLuongHangNgay = doanhThu.getSOLUONGNGAYTIM(startDate, endDate);

        // Tính tổng doanh thu, số hóa đơn và số lượng hàng từ kết quả truy vấn
        int tongDoanhThuNgay = doanhThuNgay.stream().mapToInt(Integer::intValue).sum();
        int tongSoHoaDonNgay = soHoaDonNgay.stream().mapToInt(Integer::intValue).sum();
        int tongSoLuongHangNgay = soLuongHangNgay.stream().mapToInt(Integer::intValue).sum();

        // Hiển thị kết quả trên các JLabel
        lblDTNGAY.setText(String.valueOf(tongDoanhThuNgay + " $"));
        lblHOADONNgay.setText(String.valueOf("Số hóa đơn: " + tongSoHoaDonNgay));
        lblSOHANGngay.setText(String.valueOf("Số lượng hàng: " + tongSoLuongHangNgay));
    }

    ///////////////////////////////////////// WEB CAM /////////////////////////////////////////////////////
    void initWebCam() {
        Dimension size = WebcamResolution.QVGA.getSize();

        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        panelCam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 110));

        executor.execute(this);
        openWebCam();
    }

    public void run() {
        while (webcam.isOpen()) {
            try {
                BufferedImage image = webcam.getImage();
                if (image != null) {
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    Result result = new MultiFormatReader().decode(bitmap);
                    if (result != null) {
                        String maSPFromQR = result.getText(); // Lấy MaSP từ mã QR
                        int selectedRow = findRowIndexByMaSP(tblDanhSachSP, maSPFromQR); // Tìm dòng chứa mã SP trong bảng tblDanhSachSP

                        if (selectedRow != -1) {
                            DefaultTableModel model = (DefaultTableModel) tblDanhSachSP.getModel();
                            String tenSp = (String) model.getValueAt(selectedRow, 1);
                            int soLuongHienTai = (int) model.getValueAt(selectedRow, 5);

                            int newQuantity = promptForNewQuantity(tenSp, soLuongHienTai);
                            if (newQuantity != -1) {
                                if (newQuantity <= soLuongHienTai) {
                                    addToShoppingCart(maSPFromQR, tenSp, newQuantity);
                                    int updatedQuantity = soLuongHienTai - newQuantity;
                                    model.setValueAt(updatedQuantity, selectedRow, 5); // Cập nhật số lượng mới trong bảng tblDSSP
                                } else {
                                    JOptionPane.showMessageDialog(null, "Số lượng nhập vào lớn hơn số lượng hiện có.");
                                }
                            }
                        }
                    }
                }
            } catch (NotFoundException ex) {
                // Xử lý ngoại lệ nếu cần
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                // Xử lý ngoại lệ nếu cần
            }
        }
    }

    private int findRowIndexByMaSP(JTable table, String maSP) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String maSPInTable = (String) model.getValueAt(i, 0); // Lấy mã SP từ bảng
            try {
                // Kiểm tra xem mã SP có phải là số nguyên hay không
                int maSPInteger = Integer.parseInt(maSPInTable);
                if (maSP.equals(maSPInTable)) {
                    return i; // Trả về chỉ số dòng nếu tìm thấy mã SP
                }
            } catch (NumberFormatException e) {
                // Xử lý nếu mã SP không phải là số nguyên
            }
        }
        JOptionPane.showMessageDialog(null, "Mã sản phẩm không tồn tại trong bảng."); // Thông báo khi không tìm thấy mã SP
        return -1; // Trả về -1 nếu không tìm thấy
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "my Thread");
        t.setDaemon(true);
        return t;
    }

    private void stopWebCam() {
        webcam.close(); // Đóng webcam
    }

    private void openWebCam() {
        webcam.open(); // Đóng webcam
    }
}
