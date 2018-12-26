CREATE DATABASE GFMS USING CODESET GBK TERRITORY ZH_CN;
--����������ݿ⣻
connect to GFMS ;
--���������
CREATE Bufferpool USER8 SIZE 100000 PAGESIZE 8K ;
CREATE Bufferpool USER16 SIZE 1000 PAGESIZE 16 K ;
CREATE Bufferpool USER32 SIZE 1000 PAGESIZE 32 K ;

--������ռ�
CREATE REGULAR TABLESPACE TS_USER8 PAGESIZE 8K MANAGED BY SYSTEM USING ('FSMS_8K_1') BUFFERPOOL USER8;
CREATE REGULAR TABLESPACE TS_USER16 PAGESIZE 16K MANAGED BY SYSTEM USING ('FSMS_16K_1') BUFFERPOOL USER16;
CREATE REGULAR TABLESPACE TS_USER32 PAGESIZE 32K MANAGED BY SYSTEM USING ('FSMS_32K_1') BUFFERPOOL USER32;


--��ϵͳ��ʱ��ռ��޸ĵ�C��ȥ(��Ҫ��C�̽����ļ�tmpgfms_tsp32??�ڷ�������ֱ�Ӿ����ʱ�򣬲�Ҫ������ǵ������޸ģ�����Ҫ )
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
       --��ɫ
       otherFlag	   varchar(20) default '',
       
       --��������ʱ���
       otherFlag2	   varchar(20) default '',
       datestart	timestamp,
   	   dateend	timestamp,
       PRIMARY KEY (id)
);

--ALTER TABLE ADMINISTRATOR."USER" ADD COLUMN "DATESTART" TIMESTAMP ;
--ALTER TABLE ADMINISTRATOR."USER" ADD COLUMN "DATEEND" TIMESTAMP ;






--FUNCTIONϵͳ���ܱ�
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
	timeId	varchar(40),--������id
    
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
	flag6	varchar(50),--�����豸
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
    jifl    varchar(10)  default '��Ƶ',
    --�ƻ�״̬
	flag1	varchar(20),
	
	--˫��ȷ�Ϻ󣬺��ɫ/����
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
  --����
  lx   varchar(20),
  --����
  title varchar(255) default NULL,
  --����
  content varchar(5000),
  flag1	varchar(20),
  flag2	varchar(20),
  PRIMARY KEY  (id)
) IN "TS_USER16" ;


CREATE TABLE order
      (id	integer not null,
	zqdm	varchar(20),
	zqmc     varchar(60),
	
	--��������
	cysl	int,
	
	--�ɱ���
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
	
	--��������
	cysl	int,
	
	--���׷���
	jifl   varchar(20),
	
	
	flag1	varchar(20),--userid
	flag2	varchar(20),
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),

       
       PRIMARY KEY (id)
);

--���ݱ�
CREATE TABLE data (
  id			 integer not null,
  name varchar(255) default NULL,
  --���ݿ����ֽ𣬹�Ϣ�����ȡ�
  shuju  double,
  fene   double default 0.00,
  date	timestamp,
  flag1	varchar(20),
  --���userId
  flag2	varchar(20),
  PRIMARY KEY  (id)
);
--ALTER TABLE ADMINISTRATOR."DATA" ADD COLUMN "DATE" TIMESTAMP ;
--ALTER TABLE ADMINISTRATOR."DATA" ADD COLUMN "FENE" DOUBLE ;


CREATE TABLE gpmc (
   zqdm 	VARCHAR(50) NOT NULL,
   zqmc 	        VARCHAR(128) NOT NULL,
   flag1	varchar(20),
   
   --//0����   1��Ʊ 
   flag2	varchar(20),
   --�������Ϊ��λ�����ݣ�
   zgbnum   double default 0.00,
   volflag  integer,
   PRIMARY KEY (zqdm)

);

ALTER TABLE ADMINISTRATOR."GPMC" ADD COLUMN "ZGBNUM" double default 0.00 ;

ALTER TABLE ADMINISTRATOR."GPMC" ADD COLUMN "VOLFLAG" integer default 0;
--��ʷ�۸��
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
  
  --���Ϊtmp����ʾ��ʱ��¼��
  flag1	varchar(20),
  flag2	varchar(20),
  PRIMARY KEY  (id)
);

ALTER TABLE ADMINISTRATOR."LSJG" ADD COLUMN "FQYZ" double default 0.00 ;
--�����ʷ��¼�ĸ�������
CREATE TABLE lsjgdate (
   zqdm 	VARCHAR(50) NOT NULL,
   datestart	timestamp,
   date	timestamp,
   flag1	varchar(20),
   flag2	varchar(20),
   PRIMARY KEY (zqdm)

);
--ALTER TABLE ADMINISTRATOR."LSJGDATE" ADD COLUMN "DATESTART" TIMESTAMP ;

--�������ͱ�
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
  

     
  flag1	varchar(20),--�Ƿ�����T�����Ӧ�����ݣ��޸ĺ�Ϊ��
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
һ�Զ��CRUD��������
---TestOrder��TestList


CREATE TABLE testorder
      (id	integer not null,
	zqdm	varchar(20),
	zqmc     varchar(60),
	
	--��������
	cysl	int,
	
	--�ɱ���
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
	
	--��������
	cysl	int,
	
	--���׷���
	jifl   varchar(20),
	
	--testorderID���
	testorderid int,
	flag1	varchar(20),
	flag2	varchar(20),
	flag3	varchar(20),
	flag4	varchar(20),
	flag5	varchar(20),

       
       PRIMARY KEY (id)
);



