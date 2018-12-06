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
				//添加图片到 icons 集合中 --完成
					
					//测试用的
					//System.out.println(value);
				out.println("tree.nodes['0_001'] = 'text:交易记录;icon:book;'");
				
				out.println("tree.nodes['001_001001'] = 'text:交易记录查询;icon:book;url:../view/jilu/jiluquery.jsp;'");
				out.println("tree.nodes['001_001002'] = 'text:盈亏分析显示;icon:book;url:../ykfxShow.do;'");
				
				out.println("tree.nodes['0_002'] = 'text:持仓股票;icon:book;'");
				
				out.println("tree.nodes['002_002001'] = 'text:当前股票资产显示;icon:book;url:../orderShow.do;'");
				out.println("tree.nodes['002_002002'] = 'text:数据分析[交易分类];icon:book;url:../jiflShowBT.do;'");
				out.println("tree.nodes['002_002003'] = 'text:计算器;icon:book;url:../view/ccgp/jsjs.jsp;'");
				
				out.println("tree.nodes['0_005'] = 'text:实时分析;icon:book;'");
				
				out.println("tree.nodes['005_002001'] = 'text:个股/指数实时分析;icon:book;url:../view/shishifenxi/shishifenxi.jsp;'");
				out.println("tree.nodes['005_002002'] = 'text:行业热点跟踪;icon:book;url:../redianShow.do;'");
				
				
				out.println("tree.nodes['0_004'] = 'text:特色选股;icon:book;'");
				
				out.println("tree.nodes['004_004001'] = 'text:流通盘选股;icon:book;url:../view/tsxg/tsxgquery.jsp;'");
				
				out.println("tree.nodes['004_004002'] = 'text:VOL观察池;icon:book;url:../volShow.do;'");
				
				out.println("tree.nodes['0_006'] = 'text:快捷访问;icon:book;'");
				out.println("tree.nodes['006_006001'] = 'text:历史感悟再现;icon:book;url:../view/firstshowsub.jsp;'");
				
				
				out.println("tree.nodes['0_003'] = 'text:系统管理;icon:book;'");
				
				out.println("tree.nodes['003_003001'] = 'text:股票名称维护;icon:book;url:../gpmcShow.do;'");
				
				
				out.println("tree.nodes['003_0030014'] = 'text:自选股/历史价格自动模拟;icon:book;url:../mygpmcShow.do;'");
				
				//if(baseUserContext.isSuperUser() && baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003002'] = 'text:股票历史价格相关;icon:book;url:../view/lsjg/lsjgquery.jsp;'");
				//}
				
				//if(baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003003'] = 'text:历史价格日期更新情况;icon:book;url:../lsjgdateShow.do;'");
				//}
				
				
				//if(baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003004'] = 'text:资金/股息维护;icon:book;url:../dataShow.do;'");
				//}
				//if(baseUserContext.isSuperUser() && baseUserContext.isAdmin()){
					out.println("tree.nodes['003_003005'] = 'text:语录/文章/感悟;icon:book;url:../gpylShow.do;'");
				//}
				out.println("tree.nodes['003_003006'] = 'text:交易分类维护;icon:book;url:../lxShow.do;'");
				
				out.println("tree.nodes['003_003007'] = 'text:地区维护;icon:book;url:../banShow.do;'");
				
				out.println("tree.nodes['003_003008'] = 'text:系统配置中心;icon:book;url:../systemconfigShow.do;'");
				
				out.println("tree.nodes['003_003009'] = 'text:个人配置中心;icon:book;url:../configShowOne.do;'");
				
				out.println("tree.nodes['003_0030010'] = 'text:角色维护;icon:book;url:../view/juesefunction/jueseshow.jsp;'");
				
				out.println("tree.nodes['003_0030011'] = 'text:用户角色维护;icon:book;url:../userShow.do;'");
				
				out.println("tree.nodes['003_0030012'] = 'text:当前用户信息维护;icon:book;url:../view/user/renyuanweihuindex.jsp;'");
				
				out.println("tree.nodes['003_0030013'] = 'text:系统问题及改进意见;icon:book;url:../view/sun/sunquery.jsp;'");
				

				out.println("tree.setTarget('contextFrame');document.write(tree.toString());</SCRIPT>");
			
        	
        	

		%>


	</BODY>
</HTML>
