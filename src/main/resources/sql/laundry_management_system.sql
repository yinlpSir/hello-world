DROP DATABASE IF EXISTS laundry_management_system;

CREATE DATABASE laundry_management_system;

USE laundry_management_system;

DROP TABLE IF EXISTS t_clothing;
DROP TABLE IF EXISTS t_address;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_laundry_record;

CREATE TABLE t_clothing (
  id BIGINT PRIMARY KEY auto_increment,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  size VARCHAR(20) NOT NULL,
  status ENUM('未取', '已取', '已洗', '已收') NOT NULL DEFAULT '已收',
  price DECIMAL(10,2),
	user_id BIGINT,
	FOREIGN KEY (user_id) REFERENCES t_user(id)
);

INSERT INTO t_clothing VALUES
  (1, '衬衫', '白色长袖衬衫', 'L', '已取', 25.00),
  (2, 'T恤', '黑色短袖T恤', 'M', '未取', 18.50),
  (3, '裙子', '粉色连衣裙', 'S', '已洗', 45.00),
  (4, '裤子', '黑色休闲长裤', 'L', '已收', 55.00),
  (5, '外套', '红色羽绒服', 'XL', '已取', 120.00),
  (6, '羊毛衫', '灰色羊毛衫', 'M', '已洗', 80.00),
  (7, '运动裤', '蓝色运动裤', 'L', '已收', 35.00),
  (8, '短裤', '白色短裤', 'S', '未取', 15.00),
  (9, '外套', '黑色风衣', 'XL', '已取', 90.00),
  (10, '牛仔裤', '蓝色牛仔裤', 'M', '已洗', 60.00);

CREATE TABLE t_address (
  id BIGINT PRIMARY KEY auto_increment,
  district VARCHAR(50) NOT NULL,
  address_detail VARCHAR(255) NOT NULL,
  zip_code VARCHAR(10) NOT NULL
);

INSERT INTO t_address VALUES
  (1, '浦东新区', '上海市浦东新区世纪大道100号', '200120'),
  (2, '海淀区', '北京市海淀区中关村大街27号', '100080'),
  (3, '福田区', '广东省深圳市福田区深南中路123号', '518000'),
  (4, '鼓楼区', '江苏省南京市鼓楼区汉口路888号', '210008'),
  (5, '西湖区', '浙江省杭州市西湖区文三路88号', '310008'),
  (6, '市南区', '山东省青岛市市南区香港中路20号', '266001'),
  (7, '锦江区', '四川省成都市锦江区红星路三段88号', '610021'),
  (8, '金水区', '河南省郑州市金水区花园路95号', '450003'),
  (9, '岳麓区', '湖南省长沙市岳麓区岳麓大道952号', '410006'),
  (10, '雨花区', '湖南省长沙市雨花区万家丽南路68号', '410005');

# role 1 is 管理员
# role 0 is 用户
# password 存hash串
CREATE TABLE t_user (
    id BIGINT PRIMARY KEY auto_increment,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(200),
    phone_number VARCHAR(20),
		role TINYINT NOT NULL DEFAULT 0,
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES t_address(id)
);

INSERT INTO t_user (id, username, password, phone_number, role, address_id)
VALUES
(1, 'admin', 'admin', '13811111111', 1, 1),
(2, 'user2', 'password2', '13822222222', 0, 2),
(3, 'user3', 'password3', '13833333333', 0, 3),
(4, 'user4', 'password4', '13844444444', 0, 4),
(5, 'user5', 'password5', '13855555555', 0, 5),
(6, 'user6', 'password6', '13866666666', 0, 6),
(7, 'user7', 'password7', '13877777777', 0, 7),
(8, 'user8', 'password8', '13888888888', 0, 8),
(9, 'user9', 'password9', '13899999999', 0, 9),
(10, 'user10', 'password10', '13800000000',0, 10);

CREATE TABLE t_laundry_record (
    id BIGINT PRIMARY KEY auto_increment,
    clothing_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    pickup_time DATETIME NOT NULL COMMENT '取衣时间',
    wash_time DATETIME NOT NULL COMMENT '洗衣时间',
    delivery_time DATETIME NOT NULL COMMENT '送衣时间',
    wash_method VARCHAR(50) NOT NULL COMMENT '洗衣方式',
    FOREIGN KEY (clothing_id) REFERENCES t_clothing(id),
    FOREIGN KEY (user_id) REFERENCES t_user(id)
);

INSERT INTO t_laundry_record (clothing_id, user_id, pickup_time, wash_time, delivery_time, wash_method)
VALUES
(1, 2, '2023-05-02 10:00:00', '2023-05-03 10:00:00', '2023-05-04 10:00:00', '普通洗涤'),
(2, 3, '2023-05-02 11:00:00', '2023-05-03 11:00:00', '2023-05-04 11:00:00', '干洗'),
(3, 4, '2023-05-02 12:00:00', '2023-05-03 12:00:00', '2023-05-04 12:00:00', '脱水'),
(4, 5, '2023-05-02 13:00:00', '2023-05-03 13:00:00', '2023-05-04 13:00:00', '普通洗涤'),
(5, 6, '2023-05-02 14:00:00', '2023-05-03 14:00:00', '2023-05-04 14:00:00', '干洗'),
(6, 7, '2023-05-02 15:00:00', '2023-05-03 15:00:00', '2023-05-04 15:00:00', '脱水'),
(7, 8, '2023-05-02 16:00:00', '2023-05-03 16:00:00', '2023-05-04 16:00:00', '普通洗涤'),
(8, 9, '2023-05-02 17:00:00', '2023-05-03 17:00:00', '2023-05-04 17:00:00', '干洗'),
(9, 10, '2023-05-02 18:00:00', '2023-05-03 18:00:00', '2023-05-04 18:00:00', '脱水');
