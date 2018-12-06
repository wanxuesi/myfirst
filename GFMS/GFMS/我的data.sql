delete from danwei;
insert into danwei(id,name,parent) values
(1,'江苏省',0),
(2,'镇江市',1),
(3,'常州市',1),
(5,'无锡市',1),
(6,'苏州市',1),
(7,'扬州市',1),
(8,'泰州市',1),
(9,'南通市',1),
(10,'盐城市',1),
(11,'淮安市',1),
(12,'宿迁市',1),
(13,'徐州市',1),
(14,'连云港市',1),
(15,'南京市',1),
(0,'中国',-1),
(16,'山东省',0),
(17,'安徽省',0),
(18,'黑龙江省',0);



delete from user;
insert into user(id,dwId,xm,banzu) values
(1,3,'陆歆','检修一班');
insert into user(id,dwId,xm,banzu) values
(2,3,'徐懿','检修二班');
insert into user(id,dwId,xm,banzu) values
(3,14,'万学思','检修三班');




delete from juese;
insert into juese(id,name) values
(1,'超级管理员'),
(2,'业务处理人员'),
(3,'部门管理员'),
(4,'班组用户'),
(5,'查询用户');

delete from Function;
--班模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('banAdd','班添加'),
('banDelete','班删除'),
('banShow','班显示'),
('banUpdate','班修改');
--班组安排模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('bzapAdd','班组安排添加'),
('bzapDelete','班组安排删除'),
('bzapShow','班组安排显示'),
('bzapPreUpdate','班组安排预修改'),
('bzapUpdate','班组安排修改'),
('bzapShowOne','班组安排明细');
--月度计划模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthplanAdd','月度计划添加'),
('monthplanDelete','月度计划删除'),
('monthplanDeleteMore','月度计划批量删除'),
('monthplanShow','月度计划显示'),
('monthplanPreUpdate','月度计划预修改'),
('monthplanUpdate','月度计划修改'),
('monthplanShowOne','月度计划明细'),
('monthplanQuery','月度计划查询'),
('monthplanUpdateMoreToYi','月度计划设为已报送'),
('monthplanPrint','月度计划导出到EXCEL');
--周停电计划模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekplanAdd','周停电计划添加'),
('weekplanDelete','周停电计划删除'),
('weekplanDeleteMore','周停电计划批量删除'),
('weekplanShow','周停电计划显示'),
('weekplanPreUpdate','周停电计划预修改'),
('weekplanUpdate','周停电计划修改'),
('weekplanShowOne','周停电计划明细'),
('weekplanQuery','周停电计划查询'),
('weekplanUpdateMoreToYi','周停电计划设为已报送'),
('weekplanPrint','周停电计划导出到EXCEL');

--周不停电计划模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weeknoplanAdd','周不停电计划添加'),
('weeknoplanDelete','周不停电计划删除'),
('weeknoplanDeleteMore','周不停电计划批量删除'),
('weeknoplanShow','周不停电计划显示'),
('weeknoplanPreUpdate','周不停电计划预修改'),
('weeknoplanUpdate','周不停电计划修改'),
('weeknoplanShowOne','周不停电计划明细'),
('weeknoplanQuery','周不停电计划查询'),
('weeknoplanUpdateMoreToYi','周不停电计划设为已报送'),
('weeknoplanPrint','周不停电计划导出到EXCEL');


--日工作模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('dayplanShowOne','日工作明细'),
('dayplanQuery','日工作查询'),
('dayplanPrint','日工作导出到EXCEL');

--用户模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('userAdd','用户添加'),
('userDelete','用户删除'),
('userPwdUpdate','用户密码修改'),
('userShow','用户显示'),
('userShowOne','用户明细');

insert into Function(FUNCTIONCODE,FUNCNAME) values
('juesefunctionManage','角色功能添加'),
('juesefunctionPreManage','角色功能预添加');

--insert into juesefunction(id,JUESENAME,FUNCTIONCODE) values (1,'班组用户','dayplanQuery');
--insert into juesefunction(id,JUESENAME,FUNCTIONCODE) values (2,'班组用户','weekplanQuery');


insert into administrator.zxtm(id,name) values
(1,'2.1)按年度计划正常实施的项目'),
(2,'2.2)按年度计划调整实施的项目（指包含在年度计划内，因各种原因调整实施时间，计划在本月实施的）'),
(3,'2.3)算作按年度计划实施的项目（年度计划中没有，因消缺、反措、市政等原因必须实施的，相应配合开展的检修项目也可算在内，因各级调度未批准而取消的项目也可算在内）'),
(4,'3.0)按年度计划本月未能正常实施的项目（不包括因各级调度未批准而取消的项目也可算在内）'),
(5,'4.0)非年度计划项目（指年度计划中没有的设备检修项目，不包含上述算作按年度计划实施的项目）');



insert into administrator.Function(FUNCTIONCODE,FUNCNAME) values
('monthplanUpdateMoreToTianxie','月度计划设为填写中');


insert into Function(FUNCTIONCODE,FUNCNAME) values
('month2planAdd','月度110kV及以下添加'),
('month2planDelete','月度110kV及以下删除'),
('month2planDeleteMore','月度110kV及以下批量删除'),
('month2planShow','月度110kV及以下显示'),
('month2planPreUpdate','月度110kV及以下预修改'),
('month2planUpdate','月度110kV及以下修改'),
('month2planShowOne','月度110kV及以下明细'),
('month2planQuery','月度110kV及以下查询'),
('month2planUpdateMoreToYi','月度110kV及以下设为已报送'),
('month2planPrint','月度110kV及以下导出到EXCEL'),
('month2planUpdateMoreToTianxie','月度110kV及以下设为填写中');


insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekplanUpdateMoreToYi2','周停电计划设为已作废');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthplanDaoru','220kV及以上月度导入功能');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('month2planDaoru','110kV及以下月度导入功能');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weeknoplanDaoru','外单位不停电工作导入功能');

insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekplanPrintBz','班组周计划导出功能');

-------------------------------------------------------------------2012-12-13
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekno2planAdd','管理人员周不停电计划新增');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekno2planDelete','管理人员周不停电计划删除'),

('weekno2planPreUpdate','管理人员周不停电计划预修改'),
('weekno2planUpdate','管理人员周不停电计划修改'),
('weekno2planShowOne','管理人员周不停电计划明细');

insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthMeOnly','月度计划的修改和删除限制');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekMeOnly','周计划的修改和删除限制');
-------------------------------------------------------------------
insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthplanTjPrint','220kV及以上按月统计');




=======================================================================13-4-6
--年度计划模块
insert into Function(FUNCTIONCODE,FUNCNAME) values
('yearplanAdd','年度计划添加'),
('yearplanDelete','年度计划删除'),
('yearplanDeleteMore','年度计划批量删除'),
('yearplanShow','年度计划显示'),
('yearplanPreUpdate','年度计划预修改'),
('yearplanUpdate','年度计划修改'),
('yearplanShowOne','年度计划明细'),
('yearplanQuery','年度计划查询'),
('yearplanUpdateMoreToYi','年度计划设为已报送'),
('yearplanPrint','年度计划导出到EXCEL');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('yearplanDaoru','年度导入功能');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('yearMeOnly','年度计划的修改和删除限制');

insert into administrator.Function(FUNCTIONCODE,FUNCNAME) values
('yearplanUpdateMoreToTianxie','年度计划设为填写中');

========================================================================13-6-7

insert into administrator.Function(FUNCTIONCODE,FUNCNAME) values
('weeknoplanPreCopy','周不停电计划复制功能');

--班组用户，和超级管理员有Add,所以需要添加。

---==================================================================14-8-4

delete from sblxmc;
insert into sblxmc(id,name,parent) values
(1,'一次设备',0),
(2,'二次设备',0);



insert into sblxmc(id,name,parent) values
(3,'主变',1),
(4,'断路器',1),
(5,'隔离开关',1),
(6,'流变',1),
(7,'压变',1),
(8,'避雷器',1),
(9,'电抗器',1),
(10,'电容器',1),
(11,'所用变',1),
(12,'消弧线圈',1),
(13,'母线',1),
(14,'其他',1);

insert into sblxmc(id,name,parent) values
(15,'保护',2),
(16,'测控',2),
(17,'直流系统',2),
(18,'交流系统',2),
(19,'故障录波',2),
(20,'电压并列',2),
(21,'其他',2);



insert into Function(FUNCTIONCODE,FUNCNAME) values
('gzqxAdd','故障及缺陷新增'),
('gzqxDelete','故障及缺陷删除'),
('gzqxDeleteMore','故障及缺陷批量删除'),
('gzqxPreUpdate','故障及缺陷预修改'),
('gzqxUpdate','故障及缺陷修改'),
('gzqxShowOne','故障及缺陷明细'),
('gzqxQuery','故障及缺陷查找'),
('gzqxUpdateMoreToYi','故障及缺陷设定为已处理');

--- 给角色增加以上功能。

===================================================================
insert into Function(FUNCTIONCODE,FUNCNAME) values
('bodyFile','故障及缺陷图片上传'),
('bodyFileDelete','故障及缺陷的图片删除');


========================================================================

insert into Function(FUNCTIONCODE,FUNCNAME) values
('sunAdd','系统改进意见新增'),
('sunDelete','系统改进意见删除'),
('sunDeleteMore','系统改进意见批量删除'),
('sunPreUpdate','系统改进意见预修改'),
('sunUpdate','系统改进意见修改'),
('sunShowOne','系统改进意见明细'),
('sunQuery','系统改进意见查找'),
('sunUpdateMoreToYi','系统改进意见设定为已处理');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('sunBodyFileDelete','系统改进意见的图片删除');

=======================================================================
--清除无用历史记录；
delete from lsjgdate where zqdm not in(select distinct  zqdm from jilu);
delete from lsjg where zqdm not in(select distinct  zqdm from jilu);