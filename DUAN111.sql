     CREATE DATABASE DuAn111;
     GO
     USE DuAn111;
     GO

--DROP DATABASE DuAn111

    CREATE TABLE  NhanVien
    (
        MaNV VARCHAR(10) PRIMARY KEY,
        HoTen NVARCHAR(50),
        NgaySinh DATE,
        DiaChi NVARCHAR(100),
        Email VARCHAR(50)
    )

	
    CREATE TABLE TaiKhoan
    (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        MaNV VARCHAR(10) NOT NULL,
        MatKhau VARCHAR(25) NOT NULL,
        VaiTro bit NOT NULL
        FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
    );
GO

CREATE TABLE KhuyenMai
(
    MaKM INT IDENTITY(1,1) PRIMARY KEY,
    TenKM NVARCHAR(25),
    NgayBatDau DATE,
    NgayKetThuc DATE,
    GiamGia INT,
    TrangThai BIT DEFAULT 1 -- Giá trị mặc định là 1
);


GO

    CREATE TABLE MauSac
    (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        TenMau NVARCHAR(20) 
    );
GO

    CREATE TABLE ChatLieu
    (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        TenChatLieu NVARCHAR(10)
    );
GO

    CREATE TABLE SIZE
    (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        MaSIZE VARCHAR(3)
    );
GO


    CREATE TABLE LOAI
    (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        TenLoai NVARCHAR(50)
    );
GO
--drop table sanpham

    CREATE TABLE SanPham
    (
        MaSP INT IDENTITY(1,1) PRIMARY KEY,
        TenSP NVARCHAR(50),        
        MoTa NVARCHAR(255)
    );
	-- IsHidden BIT DEFAULT 0 // đánh dấu ẩn 
GO
--drop table SanPhamchitiet
    CREATE TABLE SanPhamChiTiet
    (
        MaSPCT INT IDENTITY(1,1) PRIMARY KEY,
        MaSP INT,
        MauSac INT,
        ChatLieu INT,
        Size INT,
        Loai INT,
        SoLuong INT,
        Gia INT,
        KhuyenMai INT,
        FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP),
        FOREIGN KEY (MauSac) REFERENCES MauSac(ID),
        FOREIGN KEY (ChatLieu) REFERENCES ChatLieu(ID),
        FOREIGN KEY (Size) REFERENCES SIZE(ID),
        FOREIGN KEY (Loai) REFERENCES LOAI(ID),
		FOREIGN KEY (KhuyenMai) REFERENCES KhuyenMai(MaKM)

    );
GO


     CREATE TABLE HoaDon
    (
    MaHD INT IDENTITY(1,1) PRIMARY KEY,
    NgayLap DATE NOT NULL,
    MaNV VARCHAR(10),
    TrangThai BIT DEFAULT 0 , -- Thiết lập giá trị mặc định là 0 cho cột TrangThai
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
    );
GO 
--drop table HoaDonchitiet
    CREATE TABLE HoaDonChiTiet
    (
        MaHDCT INT IDENTITY(1,1) PRIMARY KEY,
        MaHD INT,
	    TenKH NVARCHAR(50),
        MaSP int,
        SoLuong INT NOT NULL,
        GiaTien INT NOT NULL,
		HinhThucTT NVARCHAR(50),
        GhiChu NVARCHAR(50),
        FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
        FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP),
    );
  
 -- DROP TABLE HoaDonChiTiet
SELECT * FROM TaiKhoan
SELECT * FROM HoaDon
SELECT * FROM HoaDonChiTiet
SELECT * FROM SanPham
SELECT * FROM SanPhamChiTiet
SELECT * FROM KhuyenMai
SELECT * FROM MauSac
SELECT * FROM ChatLieu
SELECT * FROM LOAI
SELECT * FROM SIZE
INSERT INTO NhanVien(MaNV,HoTen,NgaySinh,DiaChi,Email)
VALUES('NV001', N'Trần Xuân Hoành','2000-09-26','Ha Noi','tranxuanhoanh13@gmail.com'),
      ('NV002', N'Nguyễn Thị B','2003-01-01','Ha Noi','abc@gmail.com'),
      ('NV003', N'Trần Văn C','1999-03-03','Ha Noi','bcd@gmail.com'),
      ('NV004',N'Phạm Văn E','2002-05-05','Ha Noi', 'efg@gmail.com');


INSERT INTO TaiKhoan (MaNV, MatKhau, VaiTro)
VALUES ('NV001', '', 1),
       ('NV002', '111', 0),
       ('NV003', '222', 1),
       ('NV004', '333', 0);


INSERT INTO KhuyenMai(TenKM, NgayBatDau, NgayKetThuc, GiamGia, TrangThai)
VALUES (N'Voucher1', '2023-11-01', '2023-11-30', 10, 1),
       (N'Voucher2', '2023-12-01', '2023-12-31', 20, 1),
       (N'Voucher3', '2023-10-01', '2023-10-31', 15, 1),
       (N'Voucher4', '2023-11-15', '2023-11-30', 5, 1),
       (N'Voucher5', '2023-12-15', '2023-12-31', 25, 1);

--drop table KhuyenMai

INSERT INTO MauSac(TenMau)VALUES(N'Đỏ'),(N'Tím'),(N'Xanh'),(N'Trắng');
INSERT INTO ChatLieu(TenChatLieu)VALUES('Cotton'),(N'Lụa'),('Bamboo');
INSERT INTO SIZE(MaSize)VALUES('S'),('M'),('L'),('XL'),('XXL');
INSERT INTO LOAI(TenLoai) VALUES(N'Basic'),(N'Tay ngắn'),(N'Cuban'),(N'Linen');

INSERT INTO SanPham ( TenSP, MoTa)
VALUES (N'Sản phẩm 1', N'Mô tả sản phẩm 1'),
       ( N'Sản phẩm 2', N'Mô tả sản phẩm 2'),
       ( N'Sản phẩm 3', N'Mô tả sản phẩm 3'),
       ( N'Sản phẩm 4', N'Mô tả sản phẩm 4'),
       ( N'Sản phẩm 5', N'Mô tả sản phẩm 5');


INSERT INTO SanPhamChiTiet ( MaSP, MauSac, ChatLieu, Size, Loai, SoLuong, Gia)
VALUES (1, 1, 1, 1,1, 10,12000),
       ( 2, 2, 2, 2,2, 20,13000),
       ( 3, 3, 3, 3,3, 30,14000),
       (4, 4, 1, 4,4, 40,15000),
       ( 5, 1, 2, 5,4, 30,16000);


INSERT INTO HoaDon (NgayLap, MaNV) 
VALUES 
('2023-11-13', 'NV001')

INSERT INTO HoaDonChiTiet (MaHD,TenKH, MaSP, SoLuong, GiaTien, HinhThucTT,GhiChu) VALUES
(1, N'Hoàng Ngọc Hùng',1, 2, 1000,N'Tiền mặt', ''),
(1, N'Hoàng Ngọc Hùng',2, 1, 800,N'Tiền mặt', '');

go
	   


CREATE VIEW SanPham_View AS
SELECT
   sp.MaSP AS MaSP_SanPham,
   sp.TenSP,
   spct.MauSac,
   spct.ChatLieu,
   spct.Size,
   spct.SoLuong,
   spct.Gia
FROM
   SanPham sp
JOIN
   SanPhamChiTiet spct ON sp.MaSP = spct.MaSP;

--SELECT * FROM SanPham_View;


--	drop view GioHang_View1
------------------------
CREATE VIEW GioHang_View AS
SELECT
   spct.MaSP,
   sp.TenSP,
   sz.MaSIZE,
   spct.SoLuong,
   spct.Gia AS Gia,
   km.GiamGia AS GiamGia,
   ((spct.Gia * spct.SoLuong) - (spct.SoLuong * km.GiamGia)) AS ThanhTien 
FROM
   SanPham sp
JOIN
   SanPhamChiTiet spct ON sp.MaSP = spct.MaSP
JOIN
   SIZE sz ON spct.Size = sz.ID
LEFT JOIN 
   KhuyenMai km ON spct.KhuyenMai = km.MaKM;

--SELECT * FROM GioHang_View;




SELECT sp.MaSP, sp.TenSP, l.TenLoai AS Loai , spct.Gia, spct.SoLuong,ms.TenMau AS MauSac,s.MaSize AS Size, cl.TenChatLieu AS ChatLieu
FROM SanPham sp
INNER JOIN SanPhamChiTiet spct ON sp.MaSP = spct.MaSP 
INNER JOIN MauSac ms ON spct.MauSac = ms.ID 
INNER JOIN ChatLieu cl ON spct.ChatLieu = cl.ID 
INNER JOIN SIZE s ON spct.Size = s.ID
INNER JOIN LOAI l ON spct.Loai = l.ID

UPDATE ChatLieu SET TenChatLieu = N'Nhung tăm' WHERE TenChatLieu = N'Bamboo' 


CREATE VIEW DoanhThuThang AS
SELECT
    YEAR(HD.NgayLap) AS Nam, -- Thêm thông tin Năm vào VIEW
    MONTH(HD.NgayLap) AS Thang,
    SUM(HDCT.SoLuong) AS SoLuongBan,
    SUM(HDCT.SoLuong * HDCT.GiaTien) AS TongGiaBan,
    SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS TongGiaGiamGia,
    SUM(HDCT.SoLuong * HDCT.GiaTien) - SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS DoanhThu
FROM
    HoaDon HD
INNER JOIN
    HoaDonChiTiet HDCT ON HD.MaHD = HDCT.MaHD
LEFT JOIN
    SanPhamChiTiet SPCT ON HDCT.MaSP = SPCT.MaSP
LEFT JOIN
    KhuyenMai KM ON SPCT.KhuyenMai = KM.MaKM
WHERE
    HD.TrangThai = 1 -- Chỉ lấy các hóa đơn có TrangThai là 1
GROUP BY
    YEAR(HD.NgayLap), -- Thêm năm vào phần GROUP BY
    MONTH(HD.NgayLap)


	--select * from DoanhThuThang
	--SELECT DoanhThu  FROM DoanhThuThang where Nam = 2023

	--DROP VIEW DoanhThuThang

	UPDATE SanPhamChiTiet SET KhuyenMai = null WHERE Loai = 5

---	select * from taikhoan

----------------------
-- Lấy doanh thu theo ngày hôm nay và đơn hàng ngày hôm nay
CREATE VIEW DoanhThuVaDonHangHomNay AS
SELECT
    HD.MaHD,
    HD.NgayLap,
    SUM(HDCT.SoLuong) AS SoLuongBan,
    SUM(HDCT.SoLuong * HDCT.GiaTien) AS TongGiaBan,
    SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS TongGiaGiamGia,
    SUM(HDCT.SoLuong * HDCT.GiaTien) - SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS DoanhThu
FROM
    HoaDon HD
INNER JOIN
    HoaDonChiTiet HDCT ON HD.MaHD = HDCT.MaHD
LEFT JOIN
    SanPhamChiTiet SPCT ON HDCT.MaSP = SPCT.MaSP
LEFT JOIN
    KhuyenMai KM ON SPCT.KhuyenMai = KM.MaKM
WHERE
    HD.TrangThai = 1
    AND CONVERT(DATE, HD.NgayLap) = CONVERT(DATE, GETDATE()) -- Lọc theo ngày hôm nay
GROUP BY
    HD.MaHD, HD.NgayLap;
		--DROP VIEW DoanhThuVaDonHangHomNay

	-------------- Khi tìm

CREATE VIEW DoanhThuKhiTim AS
SELECT
    HD.NgayLap AS NgayTN, -- Thêm thông tin Năm vào VIEW
    SUM(HDCT.SoLuong) AS SoLuongBan,
    SUM(HDCT.SoLuong * HDCT.GiaTien) AS TongGiaBan,
    SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS TongGiaGiamGia,
    SUM(HDCT.SoLuong * HDCT.GiaTien) - SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS DoanhThu
FROM
    HoaDon HD
INNER JOIN
    HoaDonChiTiet HDCT ON HD.MaHD = HDCT.MaHD
LEFT JOIN
    SanPhamChiTiet SPCT ON HDCT.MaSP = SPCT.MaSP
LEFT JOIN
    KhuyenMai KM ON SPCT.KhuyenMai = KM.MaKM
WHERE
    HD.TrangThai = 1 -- Chỉ lấy các hóa đơn có TrangThai là 1
GROUP BY
   HD.NgayLap-- Thêm năm vào phần GROUP BY

   SELECT SoLuongBan  FROM  DoanhThuKhiTim WHERE NgayTN BETWEEN '2023-01-01' AND '2023-11-01'

   SELECT COUNT(*) AS SoHoaDon FROM HoaDon WHERE CONVERT(DATE, NgayLap) = CONVERT(DATE, GETDATE())

   		--DROP VIEW DoanhThuKhiTim

----------------------------------------------------------

CREATE VIEW XuatDoanhThu  AS
SELECT
    HD.MaHD,
    HD.NgayLap,
    HDCT.MaSP, -- Added MaSP
    SUM(HDCT.SoLuong) AS SoLuongBan,
    SUM(HDCT.SoLuong * HDCT.GiaTien) AS TongGiaBan,
    SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS TongGiaGiamGia,
    SUM(HDCT.SoLuong * HDCT.GiaTien) - SUM(ISNULL(KM.GiamGia, 0) * HDCT.SoLuong) AS DoanhThu
FROM
    HoaDon HD
INNER JOIN
    HoaDonChiTiet HDCT ON HD.MaHD = HDCT.MaHD
LEFT JOIN
    SanPhamChiTiet SPCT ON HDCT.MaSP = SPCT.MaSP
LEFT JOIN
    KhuyenMai KM ON SPCT.KhuyenMai = KM.MaKM
WHERE
    HD.TrangThai = 1
    AND CONVERT(DATE, HD.NgayLap) = CONVERT(DATE, GETDATE()) -- Lọc theo ngày hôm nay
GROUP BY
    HD.MaHD, HD.NgayLap, HDCT.MaSP; -- Added HDCT.MaSP


	select * from XuatDoanhThu
--DROP VIEW XuatDoanhThu

UPDATE KhuyenMai
        SET  TrangThai = 0
        WHERE MaKM = 8
