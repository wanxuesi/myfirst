<%@ page language="java" import="java.sql.*" pageEncoding="GBK"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%

				BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
				
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<TITLE>tree_MZ.jsp</TITLE>
		<script language="JavaScript" src="../mztree/MzTreeView10.js"></script>
		<link rel="stylesheet" type="text/css" href="../mztree/styles.css">
	</HEAD>
	<BODY>

		<%
				out.println("<SCRIPT type=text/javascript>");
				out
				.println("var tree = new MzTreeView('tree');tree.icons[\"book\"]  = \"book.gif\";tree.iconsExpand[\"book\"] = \"bookopen.gif\";");
				
				out.println("tree.setIconPath('../mztree/');");
				//���ͼƬ�� icons ������ --���
					
					//�����õ�
					//System.out.println(value);
				out.println("tree.nodes['0_001'] = 'text:���׼�¼;icon:book;'");
				
				out.println("tree.nodes['001_001001'] = 'text:���׼�¼��ѯ;icon:book;url:../view/jilu/jiluquery.jsp;'");
				out.println("tree.nodes['001_001002'] = 'text:ӯ��������ʾ;icon:book;url:../ykfxShow.do;'");
				
				out.println("tree.nodes['0_002'] = 'text:�ֲֹ�Ʊ;icon:book;'");
				
				out.println("tree.nodes['002_002001'] = 'text:��ǰ��Ʊ�ʲ���ʾ;icon:book;url:../orderShow.do;'");
				out.println("tree.nodes['002_002002'] = 'text:���ݷ���[���׷���];icon:book;url:../jiflShowBT.do;'");
				out.println("tree.nodes['002_002003'] = 'text:������;icon:book;url:../view/ccgp/jsjs.jsp;'");
				
				out.println("tree.nodes['0_005'] = 'text:ʵʱ����;icon:book;'");
				
				out.println("tree.nodes['005_002001'] = 'text:����/ָ��ʵʱ����;icon:book;url:../view/shishifenxi/shishifenxi.jsp;'");
				out.println("tree.nodes['005_002002'] = 'text:��ҵ�ȵ����;icon:book;url:../redianShow.do;'");
				
				
				out.println("tree.nodes['0_004'] = 'text:��ɫѡ��;icon:book;'");
				
				out.println("tree.nodes['004_004001'] = 'text:��ͨ��ѡ��;icon:book;url:../view/tsxg/tsxgquery.jsp;'");
				
				out.println("tree.nodes['004_004002'] = 'text:VOL�۲��;icon:book;url:../volShow.do;'");
				
				out.println("tree.nodes['0_006'] = 'text:��ݷ���;icon:book;'");
				out.println("tree.nodes['006_006001'] = 'text:��ʷ��������;icon:book;url:../view/firstshowsub.jsp;'");
				
				
				out.println("tree.nodes['0_003'] = 'text:ϵͳ����;icon:book;'");
				
				out.println("tree.nodes['003_003001'] = 'text:��Ʊ����ά��;icon:book;url:../gpmcShow.do;'");
				
				
				out.println("tree.nodes['003_0030014'] = 'text:��ѡ��/��ʷ�۸��Զ�ģ��;icon:book;url:../mygpmcShow.do;'");
				
				//if(baseUserContext.isSuperUser() && baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003002'] = 'text:��Ʊ��ʷ�۸����;icon:book;url:../view/lsjg/lsjgquery.jsp;'");
				//}
				
				//if(baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003003'] = 'text:��ʷ�۸����ڸ������;icon:book;url:../lsjgdateShow.do;'");
				//}
				
				
				//if(baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003004'] = 'text:�ʽ�/��Ϣά��;icon:book;url:../dataShow.do;'");
				//}
				//if(baseUserContext.isSuperUser() && baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003005'] = 'text:��¼/����/����;icon:book;url:../gpylShow.do;'");
				//}
				out.println("tree.nodes['003_003006'] = 'text:���׷���ά��;icon:book;url:../lxShow.do;'");
				
				out.println("tree.nodes['003_003007'] = 'text:����ά��;icon:book;url:../banShow.do;'");
				
				out.println("tree.nodes['003_003008'] = 'text:ϵͳ��������;icon:book;url:../systemconfigShow.do;'");
				
				out.println("tree.nodes['003_003009'] = 'text:������������;icon:book;url:../configShowOne.do;'");
				
				out.println("tree.nodes['003_0030010'] = 'text:��ɫά��;icon:book;url:../view/juesefunction/jueseshow.jsp;'");
				
				out.println("tree.nodes['003_0030011'] = 'text:�û���ɫά��;icon:book;url:../userShow.do;'");
				
				out.println("tree.nodes['003_0030012'] = 'text:��ǰ�û���Ϣά��;icon:book;url:../view/user/renyuanweihuindex.jsp;'");
				
				out.println("tree.nodes['003_0030013'] = 'text:ϵͳ���⼰�Ľ����;icon:book;url:../view/sun/sunquery.jsp;'");
				

				out.println("tree.setTarget('contextFrame');document.write(tree.toString());</SCRIPT>");
			
        	
        	

		%>


	</BODY>
</HTML>
