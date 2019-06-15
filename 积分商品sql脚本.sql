-- Create table
create table INTEGRAL_FILE
(
  ID          NUMBER(8) not null,
  FILE_PATH   VARCHAR2(1000),
  STATUS      VARCHAR2(50),
  SUN_ID      NUMBER(8),
  CREATE_DATE TIMESTAMP(6),
  GOODS_ID    NUMBER(8)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table INTEGRAL_FILE
  is '文件表';
-- Add comments to the columns 
comment on column INTEGRAL_FILE.FILE_PATH
  is '路径';
comment on column INTEGRAL_FILE.STATUS
  is '状态';
comment on column INTEGRAL_FILE.SUN_ID
  is '父ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_FILE
  add constraint PK_INTEGRAL_FILE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

-----------------------------------------------------

-- Create table
create table INTEGRAL_GOODS
(
  ID                   NUMBER(8) not null,
  GOODS_NAME           VARCHAR2(200),
  PRICE                VARCHAR2(200),
  INTEGRAL_SUPPLIER_ID NUMBER(8),
  COUNT                NUMBER(8),
  PAY_POINTS           VARCHAR2(200),
  REMARK               VARCHAR2(1000),
  INTEGRAL_FILE_ID     NUMBER(8),
  STATUS               VARCHAR2(20),
  CREATE_DATE          TIMESTAMP(6),
  UPDATE_DATE          TIMESTAMP(6),
  TYPE_NAME            VARCHAR2(30),
  POSTAGE              VARCHAR2(300)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table INTEGRAL_GOODS
  is '商品表';
-- Add comments to the columns 
comment on column INTEGRAL_GOODS.GOODS_NAME
  is '名称';
comment on column INTEGRAL_GOODS.PRICE
  is '价格';
comment on column INTEGRAL_GOODS.INTEGRAL_SUPPLIER_ID
  is '供货商';
comment on column INTEGRAL_GOODS.COUNT
  is '数量';
comment on column INTEGRAL_GOODS.PAY_POINTS
  is '所需积分';
comment on column INTEGRAL_GOODS.REMARK
  is '说明';
comment on column INTEGRAL_GOODS.INTEGRAL_FILE_ID
  is '图片路径';
comment on column INTEGRAL_GOODS.STATUS
  is '状态';
comment on column INTEGRAL_GOODS.TYPE_NAME
  is '商品名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_GOODS
  add constraint PK_INTEGRAL_GOODS primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


----------------------------------------------------
-- Create table
create table INTEGRAL_GOODS_TYPE
(
  ID           NUMBER(8) not null,
  TYPE_NAME    VARCHAR2(30),
  PICTURE_PATH VARCHAR2(500)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table INTEGRAL_GOODS_TYPE
  is '商品种类表';
-- Add comments to the columns 
comment on column INTEGRAL_GOODS_TYPE.TYPE_NAME
  is '商品种类';
comment on column INTEGRAL_GOODS_TYPE.PICTURE_PATH
  is '图片路径';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_GOODS_TYPE
  add constraint PK_INTEGRAL_GOODS_TYPE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
--------------------------------------------------------------------------------
-- Create table
create table INTEGRAL_HISTORY
(
  ID                     NUMBER(8) not null,
  POINTS                 VARCHAR2(300),
  ACTION                 VARCHAR2(50),
  INTEGRAL_MEMBER_CCS_ID NUMBER(8),
  CREATE_DATE            TIMESTAMP(6),
  INTEGRAL_ITEM_ID       NUMBER(8)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table INTEGRAL_HISTORY
  is '积分历史表';
-- Add comments to the columns 
comment on column INTEGRAL_HISTORY.ID
  is 'id';
comment on column INTEGRAL_HISTORY.POINTS
  is '积分';
comment on column INTEGRAL_HISTORY.ACTION
  is '动作';
comment on column INTEGRAL_HISTORY.INTEGRAL_MEMBER_CCS_ID
  is '会员ID';
comment on column INTEGRAL_HISTORY.CREATE_DATE
  is '创建时间';
comment on column INTEGRAL_HISTORY.INTEGRAL_ITEM_ID
  is '订单id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_HISTORY
  add constraint PK_INTEGRAL_HISTORY primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

--------------------------------------------------------
-- Create table
create table INTEGRAL_ITEM
(
  ID                     NUMBER(8) not null,
  INTEGRAL_GOODS_ID      NUMBER(8),
  INTEGRAL_MEMBER_CCS_ID NUMBER(8),
  PAY_POINTS             VARCHAR2(200),
  STATUS                 VARCHAR2(50),
  CREATE_DATE            TIMESTAMP(6),
  UPDATE_DATE            TIMESTAMP(6),
  REMARK                 VARCHAR2(1000),
  ITEM_CODE              VARCHAR2(300)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table INTEGRAL_ITEM
  is '订单表';
-- Add comments to the columns 
comment on column INTEGRAL_ITEM.INTEGRAL_GOODS_ID
  is '商品id';
comment on column INTEGRAL_ITEM.INTEGRAL_MEMBER_CCS_ID
  is '会员id';
comment on column INTEGRAL_ITEM.PAY_POINTS
  is '支付总积分';
comment on column INTEGRAL_ITEM.STATUS
  is '状态';
comment on column INTEGRAL_ITEM.CREATE_DATE
  is '创建时间';
comment on column INTEGRAL_ITEM.UPDATE_DATE
  is '更改时间';
comment on column INTEGRAL_ITEM.REMARK
  is '备注';
comment on column INTEGRAL_ITEM.ITEM_CODE
  is '订单编码';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_ITEM
  add constraint PK_INTEGRAL_ITEM primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
----------------------------------------------------------

-- Create table
create table INTEGRAL_ITEM_CCS
(
  ID                NUMBER not null,
  INTEGRAL_ITEM_ID  NUMBER(8),
  COUNT             NUMBER(8),
  ADDRESSEE         VARCHAR2(200),
  ADRESS            VARCHAR2(500),
  PHONE             VARCHAR2(100),
  SENDER            VARCHAR2(100),
  SENDER_PHONE      VARCHAR2(100),
  SEND_ADDRESS      VARCHAR2(500),
  POSTAGE           VARCHAR2(10),
  LOGISTICS_COMPANY VARCHAR2(300),
  COURIER_NUMBER    VARCHAR2(200),
  CREATE_DATE       TIMESTAMP(6),
  UPDATE_DATE       TIMESTAMP(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table INTEGRAL_ITEM_CCS
  is '订单扩展';
-- Add comments to the columns 
comment on column INTEGRAL_ITEM_CCS.INTEGRAL_ITEM_ID
  is '订单id';
comment on column INTEGRAL_ITEM_CCS.COUNT
  is '总数';
comment on column INTEGRAL_ITEM_CCS.ADDRESSEE
  is '收件人';
comment on column INTEGRAL_ITEM_CCS.ADRESS
  is '地址';
comment on column INTEGRAL_ITEM_CCS.PHONE
  is '收件人联系电话';
comment on column INTEGRAL_ITEM_CCS.SENDER
  is '寄件人';
comment on column INTEGRAL_ITEM_CCS.SENDER_PHONE
  is '寄件人电话';
comment on column INTEGRAL_ITEM_CCS.SEND_ADDRESS
  is '发货地址';
comment on column INTEGRAL_ITEM_CCS.POSTAGE
  is '邮费';
comment on column INTEGRAL_ITEM_CCS.LOGISTICS_COMPANY
  is '快递公司';
comment on column INTEGRAL_ITEM_CCS.COURIER_NUMBER
  is '快递单号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_ITEM_CCS
  add constraint PK_INTEGRAL_ITEM_CCS primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
------------------------------------------------------------
-- Create table
create table INTEGRAL_MEMBER_CCS
(
  ID            NUMBER(8) not null,
  Z_MEMBER_ID   VARCHAR2(30),
  COUNT_POINTS  NUMBER(5),
  ENABLE_POINTS NUMBER(5),
  CREATE_DATE   TIMESTAMP(6),
  STATUS        VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table INTEGRAL_MEMBER_CCS
  is '会员积分';
-- Add comments to the columns 
comment on column INTEGRAL_MEMBER_CCS.Z_MEMBER_ID
  is '会员id';
comment on column INTEGRAL_MEMBER_CCS.COUNT_POINTS
  is '总积分';
comment on column INTEGRAL_MEMBER_CCS.ENABLE_POINTS
  is '可用积分';
comment on column INTEGRAL_MEMBER_CCS.STATUS
  is '状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_MEMBER_CCS
  add constraint PK_INTEGRAL_MEMBER_CCS primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-------------------------------------------------------------
-- Create table
create table INTEGRAL_RECEIPTINFO
(
  ID                     NUMBER(8) not null,
  INTEGRAL_MEMBER_CCS_ID NUMBER(8),
  ADDRESS                VARCHAR2(500),
  PHONE                  VARCHAR2(200),
  ADDRESSEE              VARCHAR2(200),
  POSTAL_CODE            VARCHAR2(200),
  CREATE_DATE            TIMESTAMP(6),
  STATUS                 VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table INTEGRAL_RECEIPTINFO
  is '收件信息表';
-- Add comments to the columns 
comment on column INTEGRAL_RECEIPTINFO.INTEGRAL_MEMBER_CCS_ID
  is '会员id';
comment on column INTEGRAL_RECEIPTINFO.ADDRESS
  is '地址';
comment on column INTEGRAL_RECEIPTINFO.PHONE
  is '联系电话';
comment on column INTEGRAL_RECEIPTINFO.ADDRESSEE
  is '收件人';
comment on column INTEGRAL_RECEIPTINFO.POSTAL_CODE
  is '邮政编码';
comment on column INTEGRAL_RECEIPTINFO.STATUS
  is '状态';

------------------------------------------------------------------------
-- Create table
create table INTEGRAL_SPECIFICATIONS
(
  ID                  NUMBER(8) not null,
  SPECIFICATIONS_NAME VARCHAR2(200),
  GOODS_TYPE          NUMBER(3),
  GOODS_LEVEL         NUMBER(3)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table INTEGRAL_SPECIFICATIONS
  is '规格表';
-- Add comments to the columns 
comment on column INTEGRAL_SPECIFICATIONS.SPECIFICATIONS_NAME
  is '规格名称';
comment on column INTEGRAL_SPECIFICATIONS.GOODS_LEVEL
  is '规格值';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_SPECIFICATIONS
  add constraint PK_INTEGRAL_SPECIFICATIONS primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

-----------------------------------------------------------------------

-- Create table
create table INTEGRAL_SPECIFICATIONS_GOODS
(
  ID                      NUMBER(8) not null,
  SPECIFICATIONS_VALUE_ID NUMBER(8),
  INTEGRAL_GOODS_ID       NUMBER(8),
  GOODS_COUNT             NUMBER(3)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table INTEGRAL_SPECIFICATIONS_GOODS
  is '规格和商品关联表';
-- Add comments to the columns 
comment on column INTEGRAL_SPECIFICATIONS_GOODS.SPECIFICATIONS_VALUE_ID
  is '规格id';
comment on column INTEGRAL_SPECIFICATIONS_GOODS.INTEGRAL_GOODS_ID
  is '商品id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_SPECIFICATIONS_GOODS
  add constraint PK_INTEGRAL_SPECIFICATIONS_GOO primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;


----------------------------------------------------------------------------------
-- Create table
create table INTEGRAL_SPECIFICATIONS_VALUE
(
  ID                         NUMBER(8),
  VALUE                      VARCHAR2(300),
  INTEGRAL_SPECIFICATIONS_ID NUMBER(8)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table INTEGRAL_SPECIFICATIONS_VALUE
  is '规格值表';

-----------------------------------------------------------------------

-- Create table
create table INTEGRAL_SUPPLIER
(
  ID            NUMBER(8) not null,
  USER_NAME     VARCHAR2(100),
  PWD           VARCHAR2(100),
  SUPPLIER_NAME VARCHAR2(300),
  POINTS_COUNT  VARCHAR2(500),
  ADDRESS       VARCHAR2(500),
  PERSON_LIABLE VARCHAR2(200),
  PHONE         VARCHAR2(100),
  CREATE_DATE   TIMESTAMP(6),
  STATUS        VARCHAR2(50),
  EMAIL         VARCHAR2(300)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table INTEGRAL_SUPPLIER
  is '供货商';
-- Add comments to the columns 
comment on column INTEGRAL_SUPPLIER.USER_NAME
  is '账号';
comment on column INTEGRAL_SUPPLIER.PWD
  is '密码';
comment on column INTEGRAL_SUPPLIER.SUPPLIER_NAME
  is '供货商名称';
comment on column INTEGRAL_SUPPLIER.POINTS_COUNT
  is '总积分';
comment on column INTEGRAL_SUPPLIER.ADDRESS
  is '地址';
comment on column INTEGRAL_SUPPLIER.PERSON_LIABLE
  is '责任人';
comment on column INTEGRAL_SUPPLIER.PHONE
  is '责任人电话';
comment on column INTEGRAL_SUPPLIER.STATUS
  is '状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTEGRAL_SUPPLIER
  add constraint PK_INTEGRAL_SUPPLIER primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

------------------------------------------自增触发器----------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_FILE
BEFORE INSERT ON INTEGRAL_FILE  FOR EACH ROW
when (NEW.ID is null)
begin
  select SEQ_INTEGRAL_FILE.nextval into:NEW.ID from dual;
end;

---------------------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_GOODS
BEFORE INSERT ON INTEGRAL_GOODS  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_INTEGRAL_GOODS.nextval into:NEW.ID from dual;
end;
-----------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_HISTORY
BEFORE INSERT ON INTEGRAL_HISTORY  FOR EACH ROW
when (NEW.ID is null)
begin
  select SEQ_INTEGRAL_HISTORY.nextval into:NEW.ID from dual;
end;
-------------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_ITEM
BEFORE INSERT ON INTEGRAL_ITEM  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_INTEGRAL_ITEM.nextval into:NEW.ID from dual;
end;

------------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_ITEM_CCS
BEFORE INSERT ON INTEGRAL_ITEM_CCS  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_INTEGRAL_ITEM_CCS.nextval into:NEW.ID from dual;
end;

---------------------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_MEMBER_CCS
BEFORE INSERT ON INTEGRAL_MEMBER_CCS  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_INTEGRAL_MEMBER_CCS.nextval into:NEW.ID from dual;
end;

------------------------------------------------------------------

CREATE OR REPLACE TRIGGER INTEGRAL_RECEIPTINFO
BEFORE INSERT ON INTEGRAL_RECEIPTINFO  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_INTEGRAL_RECEIPTINFO.nextval into:NEW.ID from dual;
end;

------------------------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_SPECIFICATIONS
BEFORE INSERT ON INTEGRAL_SPECIFICATIONS  FOR EACH ROW
when (NEW.ID is null)
begin
  select SEQ_INTEGRAL_SPECIFICATIONS.nextval into:NEW.ID from dual;
end;

----------------------------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_SPECIFICATIONS_GOODS
BEFORE INSERT ON INTEGRAL_SPECIFICATIONS_GOODS  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_SPECIFICATIONS_GOODS.nextval into:NEW.ID from dual;
end;

---------------------------------------------------------------------
CREATE OR REPLACE TRIGGER INTEGRAL_SPECIFICATIONS_VALUE
BEFORE INSERT ON INTEGRAL_SPECIFICATIONS_VALUE  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_SPECIFICATIONS_VALUE.nextval into:NEW.ID from dual;
end;

--------------------------------------------------------------------
CREATE OR REPLACE TRIGGER SUPPLIER_INS_ID
BEFORE INSERT ON INTEGRAL_SUPPLIER  FOR EACH ROW
when (NEW.ID is null)
begin
	select SEQ_SUPPLIER.nextval into:NEW.ID from dual;
end;

---------------------------------------------------------------------序列---------------------------------
-- Create sequence 
create sequence SEQ_GOODS
minvalue 1
maxvalue 9999999999999999999999999999
start with 361
increment by 1
cache 20;

----------------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_FILE
minvalue 1
maxvalue 99999999
start with 97
increment by 1
nocache;
------------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_GOODS
minvalue 1
maxvalue 99999999
start with 23
increment by 1
nocache;

----------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_HISTORY
minvalue 1
maxvalue 99999999
start with 10
increment by 1
nocache;
------------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_ITEM
minvalue 1
maxvalue 99999999
start with 5
increment by 1
nocache;
-------------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_ITEM
minvalue 1
maxvalue 99999999
start with 5
increment by 1
nocache;
--------------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_ITEM_CCS
minvalue 1
maxvalue 99999999
start with 4
increment by 1
nocache;
-------------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_MEMBER_CCS
minvalue 1
maxvalue 99999999
start with 25
increment by 1
nocache;
----------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_RECEIPTINFO
minvalue 1
maxvalue 99999999
start with 7
increment by 1
nocache;
--------------------------------------------
-- Create sequence 
create sequence SEQ_INTEGRAL_SPECIFICATIONS
minvalue 1
maxvalue 99999999
start with 16
increment by 1
nocache;
-------------------------------------------
-- Create sequence 
create sequence SEQ_SPECIFICATIONS_GOODS
minvalue 1
maxvalue 99999999
start with 5
increment by 1
nocache;
--------------------------------------------
-- Create sequence 
create sequence SEQ_SPECIFICATIONS_VALUE
minvalue 1
maxvalue 99999999
start with 5
increment by 1
nocache;
---------------------------------------------
-- Create sequence 
create sequence SEQ_SUPPLIER
minvalue 1
maxvalue 99999999
start with 20
increment by 1
nocache;

