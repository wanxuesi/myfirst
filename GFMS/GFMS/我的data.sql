delete from danwei;
insert into danwei(id,name,parent) values
(1,'����ʡ',0),
(2,'����',1),
(3,'������',1),
(5,'������',1),
(6,'������',1),
(7,'������',1),
(8,'̩����',1),
(9,'��ͨ��',1),
(10,'�γ���',1),
(11,'������',1),
(12,'��Ǩ��',1),
(13,'������',1),
(14,'���Ƹ���',1),
(15,'�Ͼ���',1),
(0,'�й�',-1),
(16,'ɽ��ʡ',0),
(17,'����ʡ',0),
(18,'������ʡ',0);



delete from user;
insert into user(id,dwId,xm,banzu) values
(1,3,'½�','����һ��');
insert into user(id,dwId,xm,banzu) values
(2,3,'��ܲ','���޶���');
insert into user(id,dwId,xm,banzu) values
(3,14,'��ѧ˼','��������');




delete from juese;
insert into juese(id,name) values
(1,'��������Ա'),
(2,'ҵ������Ա'),
(3,'���Ź���Ա'),
(4,'�����û�'),
(5,'��ѯ�û�');

delete from Function;
--��ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('banAdd','�����'),
('banDelete','��ɾ��'),
('banShow','����ʾ'),
('banUpdate','���޸�');
--���鰲��ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('bzapAdd','���鰲�����'),
('bzapDelete','���鰲��ɾ��'),
('bzapShow','���鰲����ʾ'),
('bzapPreUpdate','���鰲��Ԥ�޸�'),
('bzapUpdate','���鰲���޸�'),
('bzapShowOne','���鰲����ϸ');
--�¶ȼƻ�ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthplanAdd','�¶ȼƻ����'),
('monthplanDelete','�¶ȼƻ�ɾ��'),
('monthplanDeleteMore','�¶ȼƻ�����ɾ��'),
('monthplanShow','�¶ȼƻ���ʾ'),
('monthplanPreUpdate','�¶ȼƻ�Ԥ�޸�'),
('monthplanUpdate','�¶ȼƻ��޸�'),
('monthplanShowOne','�¶ȼƻ���ϸ'),
('monthplanQuery','�¶ȼƻ���ѯ'),
('monthplanUpdateMoreToYi','�¶ȼƻ���Ϊ�ѱ���'),
('monthplanPrint','�¶ȼƻ�������EXCEL');
--��ͣ��ƻ�ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekplanAdd','��ͣ��ƻ����'),
('weekplanDelete','��ͣ��ƻ�ɾ��'),
('weekplanDeleteMore','��ͣ��ƻ�����ɾ��'),
('weekplanShow','��ͣ��ƻ���ʾ'),
('weekplanPreUpdate','��ͣ��ƻ�Ԥ�޸�'),
('weekplanUpdate','��ͣ��ƻ��޸�'),
('weekplanShowOne','��ͣ��ƻ���ϸ'),
('weekplanQuery','��ͣ��ƻ���ѯ'),
('weekplanUpdateMoreToYi','��ͣ��ƻ���Ϊ�ѱ���'),
('weekplanPrint','��ͣ��ƻ�������EXCEL');

--�ܲ�ͣ��ƻ�ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weeknoplanAdd','�ܲ�ͣ��ƻ����'),
('weeknoplanDelete','�ܲ�ͣ��ƻ�ɾ��'),
('weeknoplanDeleteMore','�ܲ�ͣ��ƻ�����ɾ��'),
('weeknoplanShow','�ܲ�ͣ��ƻ���ʾ'),
('weeknoplanPreUpdate','�ܲ�ͣ��ƻ�Ԥ�޸�'),
('weeknoplanUpdate','�ܲ�ͣ��ƻ��޸�'),
('weeknoplanShowOne','�ܲ�ͣ��ƻ���ϸ'),
('weeknoplanQuery','�ܲ�ͣ��ƻ���ѯ'),
('weeknoplanUpdateMoreToYi','�ܲ�ͣ��ƻ���Ϊ�ѱ���'),
('weeknoplanPrint','�ܲ�ͣ��ƻ�������EXCEL');


--�չ���ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('dayplanShowOne','�չ�����ϸ'),
('dayplanQuery','�չ�����ѯ'),
('dayplanPrint','�չ���������EXCEL');

--�û�ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('userAdd','�û����'),
('userDelete','�û�ɾ��'),
('userPwdUpdate','�û������޸�'),
('userShow','�û���ʾ'),
('userShowOne','�û���ϸ');

insert into Function(FUNCTIONCODE,FUNCNAME) values
('juesefunctionManage','��ɫ�������'),
('juesefunctionPreManage','��ɫ����Ԥ���');

--insert into juesefunction(id,JUESENAME,FUNCTIONCODE) values (1,'�����û�','dayplanQuery');
--insert into juesefunction(id,JUESENAME,FUNCTIONCODE) values (2,'�����û�','weekplanQuery');


insert into administrator.zxtm(id,name) values
(1,'2.1)����ȼƻ�����ʵʩ����Ŀ'),
(2,'2.2)����ȼƻ�����ʵʩ����Ŀ��ָ��������ȼƻ��ڣ������ԭ�����ʵʩʱ�䣬�ƻ��ڱ���ʵʩ�ģ�'),
(3,'2.3)��������ȼƻ�ʵʩ����Ŀ����ȼƻ���û�У�����ȱ�����롢������ԭ�����ʵʩ�ģ���Ӧ��Ͽ�չ�ļ�����ĿҲ�������ڣ����������δ��׼��ȡ������ĿҲ�������ڣ�'),
(4,'3.0)����ȼƻ�����δ������ʵʩ����Ŀ�����������������δ��׼��ȡ������ĿҲ�������ڣ�'),
(5,'4.0)����ȼƻ���Ŀ��ָ��ȼƻ���û�е��豸������Ŀ��������������������ȼƻ�ʵʩ����Ŀ��');



insert into administrator.Function(FUNCTIONCODE,FUNCNAME) values
('monthplanUpdateMoreToTianxie','�¶ȼƻ���Ϊ��д��');


insert into Function(FUNCTIONCODE,FUNCNAME) values
('month2planAdd','�¶�110kV���������'),
('month2planDelete','�¶�110kV������ɾ��'),
('month2planDeleteMore','�¶�110kV����������ɾ��'),
('month2planShow','�¶�110kV��������ʾ'),
('month2planPreUpdate','�¶�110kV������Ԥ�޸�'),
('month2planUpdate','�¶�110kV�������޸�'),
('month2planShowOne','�¶�110kV��������ϸ'),
('month2planQuery','�¶�110kV�����²�ѯ'),
('month2planUpdateMoreToYi','�¶�110kV��������Ϊ�ѱ���'),
('month2planPrint','�¶�110kV�����µ�����EXCEL'),
('month2planUpdateMoreToTianxie','�¶�110kV��������Ϊ��д��');


insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekplanUpdateMoreToYi2','��ͣ��ƻ���Ϊ������');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthplanDaoru','220kV�������¶ȵ��빦��');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('month2planDaoru','110kV�������¶ȵ��빦��');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weeknoplanDaoru','�ⵥλ��ͣ�繤�����빦��');

insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekplanPrintBz','�����ܼƻ���������');

-------------------------------------------------------------------2012-12-13
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekno2planAdd','������Ա�ܲ�ͣ��ƻ�����');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekno2planDelete','������Ա�ܲ�ͣ��ƻ�ɾ��'),

('weekno2planPreUpdate','������Ա�ܲ�ͣ��ƻ�Ԥ�޸�'),
('weekno2planUpdate','������Ա�ܲ�ͣ��ƻ��޸�'),
('weekno2planShowOne','������Ա�ܲ�ͣ��ƻ���ϸ');

insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthMeOnly','�¶ȼƻ����޸ĺ�ɾ������');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('weekMeOnly','�ܼƻ����޸ĺ�ɾ������');
-------------------------------------------------------------------
insert into Function(FUNCTIONCODE,FUNCNAME) values
('monthplanTjPrint','220kV�����ϰ���ͳ��');




=======================================================================13-4-6
--��ȼƻ�ģ��
insert into Function(FUNCTIONCODE,FUNCNAME) values
('yearplanAdd','��ȼƻ����'),
('yearplanDelete','��ȼƻ�ɾ��'),
('yearplanDeleteMore','��ȼƻ�����ɾ��'),
('yearplanShow','��ȼƻ���ʾ'),
('yearplanPreUpdate','��ȼƻ�Ԥ�޸�'),
('yearplanUpdate','��ȼƻ��޸�'),
('yearplanShowOne','��ȼƻ���ϸ'),
('yearplanQuery','��ȼƻ���ѯ'),
('yearplanUpdateMoreToYi','��ȼƻ���Ϊ�ѱ���'),
('yearplanPrint','��ȼƻ�������EXCEL');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('yearplanDaoru','��ȵ��빦��');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('yearMeOnly','��ȼƻ����޸ĺ�ɾ������');

insert into administrator.Function(FUNCTIONCODE,FUNCNAME) values
('yearplanUpdateMoreToTianxie','��ȼƻ���Ϊ��д��');

========================================================================13-6-7

insert into administrator.Function(FUNCTIONCODE,FUNCNAME) values
('weeknoplanPreCopy','�ܲ�ͣ��ƻ����ƹ���');

--�����û����ͳ�������Ա��Add,������Ҫ��ӡ�

---==================================================================14-8-4

delete from sblxmc;
insert into sblxmc(id,name,parent) values
(1,'һ���豸',0),
(2,'�����豸',0);



insert into sblxmc(id,name,parent) values
(3,'����',1),
(4,'��·��',1),
(5,'���뿪��',1),
(6,'����',1),
(7,'ѹ��',1),
(8,'������',1),
(9,'�翹��',1),
(10,'������',1),
(11,'���ñ�',1),
(12,'������Ȧ',1),
(13,'ĸ��',1),
(14,'����',1);

insert into sblxmc(id,name,parent) values
(15,'����',2),
(16,'���',2),
(17,'ֱ��ϵͳ',2),
(18,'����ϵͳ',2),
(19,'����¼��',2),
(20,'��ѹ����',2),
(21,'����',2);



insert into Function(FUNCTIONCODE,FUNCNAME) values
('gzqxAdd','���ϼ�ȱ������'),
('gzqxDelete','���ϼ�ȱ��ɾ��'),
('gzqxDeleteMore','���ϼ�ȱ������ɾ��'),
('gzqxPreUpdate','���ϼ�ȱ��Ԥ�޸�'),
('gzqxUpdate','���ϼ�ȱ���޸�'),
('gzqxShowOne','���ϼ�ȱ����ϸ'),
('gzqxQuery','���ϼ�ȱ�ݲ���'),
('gzqxUpdateMoreToYi','���ϼ�ȱ���趨Ϊ�Ѵ���');

--- ����ɫ�������Ϲ��ܡ�

===================================================================
insert into Function(FUNCTIONCODE,FUNCNAME) values
('bodyFile','���ϼ�ȱ��ͼƬ�ϴ�'),
('bodyFileDelete','���ϼ�ȱ�ݵ�ͼƬɾ��');


========================================================================

insert into Function(FUNCTIONCODE,FUNCNAME) values
('sunAdd','ϵͳ�Ľ��������'),
('sunDelete','ϵͳ�Ľ����ɾ��'),
('sunDeleteMore','ϵͳ�Ľ��������ɾ��'),
('sunPreUpdate','ϵͳ�Ľ����Ԥ�޸�'),
('sunUpdate','ϵͳ�Ľ�����޸�'),
('sunShowOne','ϵͳ�Ľ������ϸ'),
('sunQuery','ϵͳ�Ľ��������'),
('sunUpdateMoreToYi','ϵͳ�Ľ�����趨Ϊ�Ѵ���');
insert into Function(FUNCTIONCODE,FUNCNAME) values
('sunBodyFileDelete','ϵͳ�Ľ������ͼƬɾ��');

=======================================================================
--���������ʷ��¼��
delete from lsjgdate where zqdm not in(select distinct  zqdm from jilu);
delete from lsjg where zqdm not in(select distinct  zqdm from jilu);