-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 28, 2019 lúc 05:16 PM
-- Phiên bản máy phục vụ: 10.4.8-MariaDB
-- Phiên bản PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `cnpm`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `accounts`
--

CREATE TABLE `accounts` (
  `maTK` int(10) NOT NULL,
  `maKH` int(10) NOT NULL,
  `tenTK` varchar(50) NOT NULL,
  `mk` varchar(50) NOT NULL,
  `trangThai` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `accounts`
--

INSERT INTO `accounts` (`maTK`, `maKH`, `tenTK`, `mk`, `trangThai`) VALUES
(1, 1, 'sieusaopolo15@gmail.com', 'Leonel1234', 3),
(2, 2, 'sieusaopolo78@gmail.com', 'Leonel1234', 4),
(3, 3, 'huynhbabao@gmail.com', '123456789', 3),
(4, 4, 'im.phamminhduc@gmail.com', 'Leonel1234', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `accounts_employee`
--

CREATE TABLE `accounts_employee` (
  `maTK` int(10) NOT NULL,
  `maNV` int(10) NOT NULL,
  `tenTK` varchar(20) NOT NULL,
  `mk` varchar(40) NOT NULL,
  `Quyen` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `accounts_employee`
--

INSERT INTO `accounts_employee` (`maTK`, `maNV`, `tenTK`, `mk`, `Quyen`) VALUES
(1, 1, 'NV_001', '19990505', 1),
(2, 2, 'NV_002', '19991109', 3),
(3, 3, 'NV_003', '20011114', 5),
(4, 4, 'NV_004', '19991012', 5),
(5, 5, 'NV_005', '19990920', 2),
(6, 6, 'NV_006', '19990104', 5),
(7, 7, 'NV_007', '19990204', 5),
(8, 8, 'NV_008', '19990811', 5),
(9, 9, 'NV_009', '19990714', 3),
(10, 10, 'NV_010', '19990518', 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `maLoai` int(10) NOT NULL,
  `tenLoai` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`maLoai`, `tenLoai`) VALUES
(1, 'CPU - Bộ Vi Xử Lý'),
(2, 'RAM'),
(3, 'Mainboard - Bo Mạch Chủ'),
(4, 'VGA - Card Màn Hình'),
(5, 'Tản Nhiệt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `maKH` int(10) NOT NULL,
  `hoKH` varchar(80) NOT NULL,
  `gioiTinh` varchar(15) NOT NULL,
  `ngaySinh` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`maKH`, `hoKH`, `gioiTinh`, `ngaySinh`) VALUES
(1, 'Huỳnh Tuấn Đạt', 'Nam', '1999-05-05'),
(2, 'Huỳnh Thế Kiệt', 'Nam', '1996-07-07'),
(3, 'Huỳnh Tuấn Đạt', 'Nam', '1999-05-05'),
(4, 'Phạm Minh Đức', 'Nam', '1999-11-09');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `maNV` int(10) NOT NULL,
  `ChucVu` int(10) NOT NULL,
  `hoNV` varchar(50) NOT NULL,
  `tenNV` varchar(30) NOT NULL,
  `ngaySinh` varchar(30) NOT NULL,
  `ngayVaoLam` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`maNV`, `ChucVu`, `hoNV`, `tenNV`, `ngaySinh`, `ngayVaoLam`) VALUES
(1, 1, 'Huỳnh Tuấn', 'Đạt', '1999-05-05', '2019-07-20'),
(2, 3, 'Phạm Minh', 'Đức', '1999-11-09', '2019-11-19'),
(3, 4, 'ABC', 'EFG', '2001-11-14', '2019-11-27'),
(4, 4, 'Huỳnh Bá', 'Bảo', '1999-10-12', '2019-11-26'),
(5, 2, 'Trương Chí', 'Linh', '1999-09-20', '2019-11-26'),
(6, 5, 'Đinh Duy', 'Phát', '1999-01-04', '2019-11-13'),
(7, 5, 'Đặng Văn', 'Linh', '1999-02-04', '2019-11-19'),
(8, 5, 'Lê Độ Quang', 'Huy', '1999-08-11', '2019-11-11'),
(9, 3, 'Nguyễn Văn', 'A', '1999-07-14', '2019-11-14'),
(10, 5, 'Nguyễn Hoàng', 'Phong', '1999-05-18', '2019-11-18');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `export`
--

CREATE TABLE `export` (
  `maPhieu` int(10) NOT NULL,
  `maSP` int(10) NOT NULL,
  `maNV` int(10) NOT NULL,
  `soLuongXuat` int(10) NOT NULL,
  `ngayXuat` varchar(60) NOT NULL,
  `lyDoXuat` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `export`
--

INSERT INTO `export` (`maPhieu`, `maSP`, `maNV`, `soLuongXuat`, `ngayXuat`, `lyDoXuat`) VALUES
(1, 1, 2, 5, '2019-11-17 08:47:16', 'Sản phẩm 002 thiếu hàng bên đại lý');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `import`
--

CREATE TABLE `import` (
  `maPhieu` int(10) NOT NULL,
  `maKho` int(10) NOT NULL,
  `maSP` int(10) NOT NULL,
  `maNCC` int(10) NOT NULL,
  `maNV` int(10) NOT NULL,
  `soLuong` int(10) NOT NULL,
  `ngayNhap` varchar(60) NOT NULL,
  `donGia` varchar(30) NOT NULL,
  `tongTien` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `import`
--

INSERT INTO `import` (`maPhieu`, `maKho`, `maSP`, `maNCC`, `maNV`, `soLuong`, `ngayNhap`, `donGia`, `tongTien`) VALUES
(1, 1, 1, 3, 2, 3, '2019-11-21 07:20:51', '4.850.000', '14.550.000'),
(2, 1, 1, 2, 2, 5, '2019-11-16 20:57:10', '4.850.000', '24.250.000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `maDH` int(10) NOT NULL,
  `maKH` int(10) NOT NULL,
  `NgayLap` varchar(35) NOT NULL,
  `tongTien` varchar(20) NOT NULL,
  `maNVGiaoHang` int(10) NOT NULL,
  `trangThai` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`maDH`, `maKH`, `NgayLap`, `tongTien`, `maNVGiaoHang`, `trangThai`) VALUES
(1, 1, '2019-11-28', '4.850.000', 6, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `maDH` int(11) NOT NULL,
  `maSP` int(11) NOT NULL,
  `nguoiNhan` varchar(45) NOT NULL,
  `diaChiGiaoHang` varchar(70) NOT NULL,
  `sdt` varchar(13) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `donGia` varchar(20) NOT NULL,
  `tongTien` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`maDH`, `maSP`, `nguoiNhan`, `diaChiGiaoHang`, `sdt`, `soLuong`, `donGia`, `tongTien`) VALUES
(1, 1, 'ABC', 'ABC', '12345', 1, '4.850.000', '4.850.000 đ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permission`
--

CREATE TABLE `permission` (
  `maQuyen` int(11) NOT NULL,
  `tenQuyen` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `permission`
--

INSERT INTO `permission` (`maQuyen`, `tenQuyen`) VALUES
(1, 'Tất Cả'),
(2, 'Quản Lý Khách Hàng, Quản Lý Đơn Hàng'),
(3, 'Quản Lý Kho, Quản Lý Sản Phẩm, Quản Lý Nhà Cung Cấp'),
(4, 'Quản Lý Khách Hàng'),
(5, 'Xem Chi Tiết Đơn Hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `maSP` int(10) NOT NULL,
  `tenSP` varchar(50) NOT NULL,
  `loaiSP` int(10) NOT NULL,
  `thuongHieu` varchar(30) NOT NULL,
  `hinhAnh` varchar(70) NOT NULL,
  `soLuongConLai` int(10) NOT NULL,
  `giaTien` varchar(40) NOT NULL,
  `trangThai` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`maSP`, `tenSP`, `loaiSP`, `thuongHieu`, `hinhAnh`, `soLuongConLai`, `giaTien`, `trangThai`) VALUES
(1, 'CPU Intel Core I3 - 7100', 1, 'Intel', '../Images/CPU/i3-7-1.jpg', 4, '4.850.000', 6),
(2, 'Mainboard ASUS EX - H310M - V3', 3, 'ASUS', '../Images/Mainboard/mainboard-asus-ex-h310m.jpg', 5, '1.620.000', 6),
(3, 'RAM Desktop Kingmax', 2, 'Kingmax', '../Images/RAM/ram-desktop-kingmax.jpg', 5, '750.000', 6),
(4, 'XIGMATEK Galaxy Premium RGB', 5, 'XIGMATEK', '../Images/TanNhiet/xigmatek-galaxy-premium.jpg', 5, '139.000', 6),
(5, 'ASUS GeForce RTX 2080Ti 11GB', 1, 'ASUS', '../Images/VGA/asus-geForce-RTX-2080Ti.jpg', 5, '36.990.000', 6),
(6, 'ASUS GeForce GTX 1660 6GB', 1, 'ASUS', '../Images/VGA/asus-geForce-GTX-1660.jpg', 5, '6.500.000', 6),
(7, 'AMD Ryzen 5 3600', 1, 'AMD', '../Images/CPU/amd_ryzen_5_3600.jpg', 5, '5.190.000', 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products_detail`
--

CREATE TABLE `products_detail` (
  `maSP` int(10) NOT NULL,
  `chiTietSP` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `products_detail`
--

INSERT INTO `products_detail` (`maSP`, `chiTietSP`) VALUES
(1, 'Socket: LGA 1151, Intel Core Thế Hệ Thứ 7, Tốc độ xử lý: 3.9Hz (2 nhân,4 luồng), Bộ nhớ đệm: 3MB, Đồ họa tích hợp: Intel HD Graphics 630'),
(2, 'Chuẩn Mainboard: Micro-ATX, Socket: LGA 1151-v2, Chipset: H310, Hỗ Trợ RAM: DDR4,tối đa 32GB, cổng cấm lưu trữ: 3 x SATA 3 6Gb/s, Cổng Xuất Hình: 1 x VGA/D-sub'),
(3, 'Dung Lượng: 1 x 4GB, Thế Hệ: DDR4, BUS: 2400MHz, Cas: 17, Voltage: 1.2V'),
(4, 'Kích Thước Quạt: 120mm, Số vòng quay: 2100 RPM, Lưu lượng không khí: 51CFM, Độ ồn (dBA): 20.3dBA, Đèn LED: RGB'),
(5, 'Bộ Nhớ: 11GB GDDR (352-bit), GPU Clock OC Mode - GPU Boost Clock: 1665MHz,GPU Base Clock: 1350MHz Gaming Mode (Default) - GPU Boost Clock: 1650MHz,GPU Base Clock: 1350 MHz, Nguồn Phụ: 2 x 8-pin, Số Lượng Đơn Vị Xử Lý: 4352 CUDA Cores, Cổng Kết Nối: 2 x HDMI 2.0b,2 x DisplayPort 1.4'),
(6, 'Bộ Nhớ: 6GB GDDR5 (192-bit), GPU Clock OC Mode - GPU Boost Clock: 1815MHz,GPU Base Clock: 1560MHz Gaming Mode (Default) - GPU Boost Clock: 1785MHz,GPU Base Clock: 1530MHz, Nguồn Phụ: 1 x 8-pin, Số Lượng Đơn Vị Xử Lý: 1408 CUDA Cores, Cổng Kết Nối: 1 x HDMI 2.0b,1 x DisplayPort 1.4,1 x DVI-D\r\n'),
(7, 'Socket: AM4,AMD Ryzen thế hệ thứ 3, Tốc độ xử lý: 3.6GHz - 4.2 GHz (6 nhân,12 luồng), Bộ nhớ đệm: 32MB');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_ID` int(10) NOT NULL,
  `tenRole` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_ID`, `tenRole`) VALUES
(1, 'Quản Lý'),
(2, 'Thu Ngân'),
(3, 'Kiểm Kho'),
(4, 'Nhân Viên Hỗ Trợ'),
(5, 'Nhân Viên Giao Hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `status`
--

CREATE TABLE `status` (
  `maTrangThai` int(10) NOT NULL,
  `tenTrangThai` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `status`
--

INSERT INTO `status` (`maTrangThai`, `tenTrangThai`) VALUES
(1, 'Đã Phê Duyệt'),
(2, 'Chưa Phê Duyệt'),
(3, 'Đang Kích Hoạt'),
(4, 'Vô Hiệu Hóa'),
(5, 'Bị Hủy'),
(6, 'Còn Hàng'),
(7, 'Hết Hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `storage`
--

CREATE TABLE `storage` (
  `maKho` int(10) NOT NULL,
  `tenKho` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `storage`
--

INSERT INTO `storage` (`maKho`, `tenKho`) VALUES
(1, 'Kho Chính'),
(2, 'Kho Đại Lý');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `maNCC` int(10) NOT NULL,
  `tenNCC` varchar(40) NOT NULL,
  `SDT` varchar(13) NOT NULL,
  `DiaChi` varchar(65) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `supplier`
--

INSERT INTO `supplier` (`maNCC`, `tenNCC`, `SDT`, `DiaChi`) VALUES
(1, 'Bá Bảo', '0906813432', 'null'),
(2, 'Tuấn Đạt', '0902777586', '177 Cao Xuân Dục P11 Q8'),
(3, 'Minh Đức', '0993523889', 'null'),
(4, 'Chí Linh', '0767345427', 'null'),
(5, 'Duy Phát', '0773771954', 'null'),
(6, 'Chi Pu', '1234567810', 'null');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`maTK`),
  ADD UNIQUE KEY `tenTK` (`tenTK`),
  ADD KEY `trangThai` (`trangThai`),
  ADD KEY `maKH` (`maKH`);

--
-- Chỉ mục cho bảng `accounts_employee`
--
ALTER TABLE `accounts_employee`
  ADD PRIMARY KEY (`maTK`),
  ADD KEY `Quyen` (`Quyen`),
  ADD KEY `FK_AccountEmployee` (`maNV`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`maLoai`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`maKH`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`maNV`),
  ADD KEY `ChucVu` (`ChucVu`);

--
-- Chỉ mục cho bảng `export`
--
ALTER TABLE `export`
  ADD PRIMARY KEY (`maPhieu`),
  ADD KEY `maSP` (`maSP`,`maNV`),
  ADD KEY `FK_ExportEmployee` (`maNV`);

--
-- Chỉ mục cho bảng `import`
--
ALTER TABLE `import`
  ADD PRIMARY KEY (`maPhieu`),
  ADD KEY `maKho` (`maKho`,`maSP`,`maNCC`,`maNV`),
  ADD KEY `FK_ImportProduct` (`maSP`),
  ADD KEY `FK_ImportSupplier` (`maNCC`),
  ADD KEY `FK_ImportEmployee` (`maNV`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`maDH`),
  ADD KEY `maKH` (`maKH`),
  ADD KEY `trangThai` (`trangThai`),
  ADD KEY `FK_Delievery` (`maNVGiaoHang`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`maDH`),
  ADD KEY `maDH` (`maDH`),
  ADD KEY `maSP` (`maSP`);

--
-- Chỉ mục cho bảng `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`maQuyen`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`maSP`),
  ADD KEY `loaiSP` (`loaiSP`),
  ADD KEY `trangThai` (`trangThai`);

--
-- Chỉ mục cho bảng `products_detail`
--
ALTER TABLE `products_detail`
  ADD PRIMARY KEY (`maSP`),
  ADD KEY `maSP` (`maSP`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_ID`);

--
-- Chỉ mục cho bảng `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`maTrangThai`);

--
-- Chỉ mục cho bảng `storage`
--
ALTER TABLE `storage`
  ADD PRIMARY KEY (`maKho`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`maNCC`) USING BTREE;

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `accounts`
--
ALTER TABLE `accounts`
  MODIFY `maTK` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `maKH` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `maNV` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `maDH` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `maSP` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `FK_AccountCustomer` FOREIGN KEY (`maKH`) REFERENCES `customer` (`maKH`),
  ADD CONSTRAINT `FK_AccountStatus` FOREIGN KEY (`trangThai`) REFERENCES `status` (`maTrangThai`);

--
-- Các ràng buộc cho bảng `accounts_employee`
--
ALTER TABLE `accounts_employee`
  ADD CONSTRAINT `FK_AccountEmployee` FOREIGN KEY (`maNV`) REFERENCES `employee` (`maNV`),
  ADD CONSTRAINT `FK_Role` FOREIGN KEY (`Quyen`) REFERENCES `permission` (`maQuyen`);

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK_EmployeeRole` FOREIGN KEY (`ChucVu`) REFERENCES `role` (`role_ID`);

--
-- Các ràng buộc cho bảng `export`
--
ALTER TABLE `export`
  ADD CONSTRAINT `FK_ExportEmployee` FOREIGN KEY (`maNV`) REFERENCES `employee` (`maNV`),
  ADD CONSTRAINT `FK_ExportProduct` FOREIGN KEY (`maSP`) REFERENCES `products` (`maSP`);

--
-- Các ràng buộc cho bảng `import`
--
ALTER TABLE `import`
  ADD CONSTRAINT `FK_ImportEmployee` FOREIGN KEY (`maNV`) REFERENCES `employee` (`maNV`),
  ADD CONSTRAINT `FK_ImportProduct` FOREIGN KEY (`maSP`) REFERENCES `products` (`maSP`),
  ADD CONSTRAINT `FK_ImportStorage` FOREIGN KEY (`maKho`) REFERENCES `storage` (`maKho`),
  ADD CONSTRAINT `FK_ImportSupplier` FOREIGN KEY (`maNCC`) REFERENCES `supplier` (`maNCC`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_Customer` FOREIGN KEY (`maKH`) REFERENCES `customer` (`maKH`),
  ADD CONSTRAINT `FK_Delievery` FOREIGN KEY (`maNVGiaoHang`) REFERENCES `employee` (`maNV`),
  ADD CONSTRAINT `FK_Status` FOREIGN KEY (`trangThai`) REFERENCES `status` (`maTrangThai`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK_Novice` FOREIGN KEY (`maDH`) REFERENCES `orders` (`maDH`),
  ADD CONSTRAINT `FK_NoviceProduct` FOREIGN KEY (`maSP`) REFERENCES `products` (`maSP`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FK_Category` FOREIGN KEY (`loaiSP`) REFERENCES `category` (`maLoai`),
  ADD CONSTRAINT `FK_ProductStatus` FOREIGN KEY (`trangThai`) REFERENCES `status` (`maTrangThai`);

--
-- Các ràng buộc cho bảng `products_detail`
--
ALTER TABLE `products_detail`
  ADD CONSTRAINT `FK_Product` FOREIGN KEY (`maSP`) REFERENCES `products` (`maSP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
