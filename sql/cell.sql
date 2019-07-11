
create database sell charset utf8mb4;

use sell;

CREATE TABLE product_info (
    product_id bigint NOT NULL PRIMARY KEY,
    product_name VARCHAR(64) NOT NULL COMMENT '商品名称',
    product_price DECIMAL(8 , 2 ) NOT NULL COMMENT '商品名称',
    product_description varchar(512) not null comment '商品描述',
    product_stock INT NOT NULL COMMENT '库存',
    product_icon VARCHAR(512) NOT NULL COMMENT '小图',
    product_status TINYINT NOT NULL DEFAULT 1 COMMENT '商品状态:1 正常,0下架',
    category_type INT NOT NULL COMMENT '类目编号',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
)  COMMENT '商品';



CREATE TABLE product_category (
    category_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(64) NOT NULL COMMENT '类目名称',
    category_type INT NOT NULL COMMENT '类目编号',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    unique key `uqe_category_type` (category_type)
)  COMMENT '类目表';


CREATE TABLE order_master (
    order_id bigint NOT NULL PRIMARY KEY,
    buyer_name VARCHAR(32) NOT NULL COMMENT '买家姓名',
    buyer_phone VARCHAR(32) NOT NULL COMMENT '买家电话',
    buyer_address VARCHAR(128) NOT NULL COMMENT '买家地址',
    buyer_openid VARCHAR(64) NOT NULL COMMENT '买家微信id',
    buyer_amount DECIMAL(8 , 2 ) NOT NULL COMMENT '订单总金额',
    order_status TINYINT NOT NULL DEFAULT 0 COMMENT '订单状态,默认新下单',
    pay_status TINYINT NOT NULL DEFAULT 0 COMMENT '支付状态, 默认未支付',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    KEY `idx_buyer_openid` (buyer_openid)
)  COMMENT '订单表';




CREATE TABLE order_detail (
    detail_id BIGINT NOT NULL PRIMARY KEY,
    order_id BIGINT NOT NULL COMMENT '订单id',
    product_id BIGINT NOT NULL COMMENT '商品id',
    product_name VARCHAR(32) NOT NULL COMMENT '商品名称',
    product_price DECIMAL(8 , 2 ) NOT NULL COMMENT '商品价格',
    product_quantity INT NOT NULL COMMENT '商品数量',
    product_icon VARCHAR(512) NOT NULL COMMENT '商品图片',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    KEY `idx_order_id` (order_id)
)  COMMENT '订单详情';




