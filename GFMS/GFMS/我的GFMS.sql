CREATE DATABASE GFMS USING CODESET GBK TERRITORY ZH_CN;
--连接相关数据库；
connect to GFMS ;
--建立缓冲池
CREATE Bufferpool USER8 SIZE 100000 PAGESIZE 8K ;
CREATE Bufferpool USER16 SIZE 1000 PAGESIZE 16 K ;
CREATE Bufferpool USER32 SIZE 1000 PAGESIZE 32 K ;

--建立表空间
CREATE REGULAR TABLESPACE TS_USER8 PAGESIZE 8K MANAGED BY SYSTEM USING ('FSMS_8K_1') BUFFERPOOL USER8;
CREATE REGULAR TABLESPACE TS_USER16 PAGESIZE 16K MANAGED BY SYSTEM USING ('FSMS_16K_1') BUFFERPOOL USER16;
CREATE REGULAR TABLESPACE TS_USER32 PAGESIZE 32K MANAGED BY SYSTEM USING ('FSMS_32K_1') BUFFERPOOL USER32;


--将系统临时表空间修改到C盘去(需要在C盘建立文件tmpgfms_tsp32??在服务器上直接镜像的时候，不要。如果是单纯的修改，则需要 )
create system temporary tablespace tmpgfms_tbsp32
PAGESIZE 32K
managed by system
using ('c:\tmpgfms_tsp32')
BUFFERPOOL USER32;

CREATE TABLE danwei (
  id			 integer not null,
  name varchar(255) default NULL,
parent int  default 0,
BZ varchar (10),
  PRIMARY KEY  (id)
);










CREATE TABLE USER
      (  id			 integer not null,
       dwId             int,
       banzu			varchar(20) default '',
       xm                  VARCHAR(20) default '',
       kl                  VARCHAR(60) default '',
       
       nx                  VARCHAR(17) default '',
       wx                  VARCHAR(17) default '',
       sj                  VARCHAR(12) default '', 
       --角色
       otherFlag	   varchar(20) default '',
       
       --新密码临时存放
       otherFlag2	   varchar(20) default '',
       datestart	timestamp,
   	   dateend	timestamp,
       PRIMARY KEY (id)
);

--ALTER TABLE ADMINISTRATOR."USER" ADD COLUMN "DATESTART" TIMESTAMP ;
--ALTER TABLE ADMINISTRATOR."USER" ADD COLUMN "DATEEND" TIMESTAMP ;






--FUNCTION系统功能表
--drop table administrator.FUNCTION;

CREATE TABLE administrator.FUNCTION
(
   FUNCTIONCODE 	VARCHAR(50) NOT NULL,
   FUNCNAME 	        VARCHAR(128) NOT NULL,
   PRIMARY KEY (FUNCTIONCODE)
);


CREATE TABLE administrator.JUESE
      (id			 integer not null,
        name                   	VARCHAR(50),
       otherFlag	   VARCHAR(50),
       PRIMARY KEY (id)
);

--drop table administrator.JUESEFUNCTION;
CREATE TABLE administrator.JUESEFUNCTION
      (id			 integer not null,
        JUESENAME                   	VARCHAR(50),
        FUNCTIONCODE				VARCHAR(50),
       otherFlag	   VARCHAR(50),
       PRIMARY KEY (id)
);




-------==============================================================

CREATE TABLE bodyfile
      (otherFlag               VARCHAR(20)  NOT NULL,
       fileName     varchar(100)  NOT NULL,
       BODY			BLOB,
 	   PRIMARY KEY (otherFlag)
);



-----------------------------------2015-5-7
CREATE TABLE sun
     (id	integer not null,
	timeId	varchar(40),--附件用id
    
	bdz     varchar(60),
	sblx     varchar(20),
	sbmc     varchar(60),
	dydj	varchar(15),
	gzqxxx	varchar(1500),
	clgc		varchar(1500),
	fssj	timestamp, 
	clsj	timestamp,
	ts	int  default 0,
		
	fxr  varchar(20),
	hbr varchar(20),
	qxr varchar(20),
	ysr varchar(20),
	tbr varchar(20),
	banzu varchar(60),
	
	bz	varchar(200),
	jhzt	varchar(15),
	
	
	flag1	varchar(20),
	flag2	varchar(20),
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),
	flag6	varchar(50),--故障设备
	flag7	varchar(50),
	flag8	varchar(50),
	flag9	varchar(100),
	
	flag10	varchar(200),

       
       PRIMARY KEY (id)
) IN "TS_USER16" ;


CREATE TABLE jilu
      (id	integer not null,
	timeId	varchar(40),
	jysj	timestamp,
	zqdm	varchar(20),
	zqmc     varchar(60),
	mmflag	varchar(10)  default '',
	cjjg    double,
	cjsl	int,
	cjje    double,
	qsje    double,
	ywmc	varchar(60),
	zjzh	varchar(30),
	jysmc	varchar(30),
	khdm    varchar(30),
	gdmc	varchar(20),
	bz		varchar(300),
    jifl    varchar(10)  default '高频',
    --计划状态
	flag1	varchar(20),
	
	--双击确认后，后变色/变亮
	flag2	varchar(20),
	
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),
	flag6	varchar(50),
	flag7	varchar(50),
	flag8	varchar(50),
	flag9	varchar(100),
	flag10	varchar(200),

       
       PRIMARY KEY (id)
) IN "TS_USER16" ; 

CREATE TABLE gpyl (
  id			 integer not null,
  --类型
  lx   varchar(20),
  --标题
  title varchar(255) default NULL,
  --内容
  content varchar(5000),
  flag1	varchar(20),
  flag2	varchar(20),
  PRIMARY KEY  (id)
) IN "TS_USER16" ;


CREATE TABLE order
      (id	integer not null,
	zqdm	varchar(20),
	zqmc     varchar(60),
	
	--持有数量
	cysl	int,
	
	--成本价
	cbj    double,
	
	
	
	flag1	varchar(20),--userid
	flag2	varchar(20),
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),

       
       PRIMARY KEY (id)
);


CREATE TABLE list
      (id	integer not null,
	zqdm	varchar(20),
	zqmc     varchar(60),
	
	--持有数量
	cysl	int,
	
	--交易分类
	jifl   varchar(20),
	
	
	flag1	varchar(20),--userid
	flag2	varchar(20),
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),

       
       PRIMARY KEY (id)
);

--数据表
CREATE TABLE data (
  id			 integer not null,
  name varchar(255) default NULL,
  --数据可用现金，股息红利等。
  shuju  double,
  fene   double default 0.00,
  date	timestamp,
  flag1	varchar(20),
  --存放userId
  flag2	varchar(20),
  PRIMARY KEY  (id)
);
--ALTER TABLE ADMINISTRATOR."DATA" ADD COLUMN "DATE" TIMESTAMP ;
--ALTER TABLE ADMINISTRATOR."DATA" ADD COLUMN "FENE" DOUBLE ;


CREATE TABLE gpmc (
   zqdm 	VARCHAR(50) NOT NULL,
   zqmc 	        VARCHAR(128) NOT NULL,
   flag1	varchar(20),
   
   --//0基金   1股票 
   flag2	varchar(20),
   --存放以万为单位的数据；
   zgbnum   double default 0.00,
   volflag  integer,
   PRIMARY KEY (zqdm)

);

ALTER TABLE ADMINISTRATOR."GPMC" ADD COLUMN "ZGBNUM" double default 0.00 ;

ALTER TABLE ADMINISTRATOR."GPMC" ADD COLUMN "VOLFLAG" integer default 0;
--历史价格表
CREATE TABLE lsjg (
  id			 integer not null,
  zqdm varchar(60) default NULL,
  zqmc varchar(60) default NULL,
  date	timestamp,
  open	double,
  high	double,
  low	double,
  close	double,
  volume double,	
  adjclose double,
  fqyz double default 0.00,
  
  --标记为tmp，表示临时记录；
  flag1	varchar(20),
  flag2	varchar(20),
  PRIMARY KEY  (id)
);

ALTER TABLE ADMINISTRATOR."LSJG" ADD COLUMN "FQYZ" double default 0.00 ;
--存放历史记录的更新日期
CREATE TABLE lsjgdate (
   zqdm 	VARCHAR(50) NOT NULL,
   datestart	timestamp,
   date	timestamp,
   flag1	varchar(20),
   flag2	varchar(20),
   PRIMARY KEY (zqdm)

);
--ALTER TABLE ADMINISTRATOR."LSJGDATE" ADD COLUMN "DATESTART" TIMESTAMP ;

--交易类型表
CREATE TABLE lx (
  id			 integer not null,
  name varchar(255) default NULL,
  flag1	varchar(20),
  flag2	varchar(20),
  PRIMARY KEY  (id)
);


--config
CREATE TABLE config (
  userid	varchar(20)  NOT NULL,
  onoff     integer default 0,
  confirmbfb double,
  yinhuashuibuy double,
  yinhuashuisell double,
  onegupiaocangweibfb double,
  onejiflcangweibfb   double,
  
  yongjin double,
  yongjinmin int default 5,
  guohufei double,
  guohufeimin int default 1,
  

     
  flag1	varchar(20),--是否隐藏T过后对应的数据？修改后，为否；
  flag2	varchar(20),
  flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),
  PRIMARY KEY  (userid)
);


--systemconfig
CREATE TABLE systemconfig (
  functioncode	varchar(20)  NOT NULL,

  functionname  varchar(60),

  functionvalue varchar(20),  
  flag1	varchar(20),
  flag2	varchar(20),

  PRIMARY KEY  (functioncode)
);

CREATE TABLE mygpmc (
   id			 integer not null,
   zqdm 	VARCHAR(50) NOT NULL,
   zqmc 	        VARCHAR(128) NOT NULL,
   flag1	varchar(20),
   flag2	varchar(20),
   PRIMARY KEY (id)

);

--------
一对多的CRUD操作测试
---TestOrder，TestList


CREATE TABLE testorder
      (id	integer not null,
	zqdm	varchar(20),
	zqmc     varchar(60),
	
	--持有数量
	cysl	int,
	
	--成本价
	cbj    double,
	
	
	
	flag1	varchar(20),
	flag2	varchar(20),
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),

       
       PRIMARY KEY (id)
);


CREATE TABLE testlist
      (id	integer not null,
	zqdm	varchar(20),
	zqmc     varchar(60),
	
	--持有数量
	cysl	int,
	
	--交易分类
	jifl   varchar(20),
	
	--testorderID外键
	testorderid int,
	flag1	varchar(20),
	flag2	varchar(20),
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),

       
       PRIMARY KEY (id)
);



