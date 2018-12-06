<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${FULLZQDM}K线图、成交量显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script>
	
	</script>
  </head>
  
  <body>
    <CENTER>
  <br><br><br><br>
			<P>
			<IMG src="http://image.sinajs.cn/newchart/daily/n/${FULLZQDM}.gif">
	
			</p>
			${GPMCDTO.zqmc}(${GPMCDTO.zqdm})&nbsp;市场价：${GPMCDTO.scj}元
			<br><br>
			当前总股本:${GPMCDTO.zgbStr}&nbsp;当前总市值:${GPMCDTO.zszStr}&nbsp;
			<br><br>
			当前已流通股份(包含万):${GPMCDTO.dqyltnumStr}&nbsp;当前已流通市值:${GPMCDTO.dqyltszStr}
			<br><br>
			解禁日期－对应股票数量：${GPMCDTO.jiejinMessageMap}
	</CENTER>
  </body>
</html>
