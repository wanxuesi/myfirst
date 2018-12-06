
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人配置中心</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

	<script type="text/javascript" src="dwr/interface/userdao.js"></script> 
     <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
		
<script language="javascript">   
     
  
   
   
 
   
	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();'>
  	<form name="targetForm" action="" method="post">
  		<input type="hidden" id="userid" name="userid" value="<bean:write name="CONFIG" property="userid" scope="request"/>">
  	</form>
   <center>  <br>
  
		
		&nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT type="button" value="修改" class="button"  onclick="mypreupdate();">
    <form id="add" name="add" action="" method="post">
         <table Width="750" class="tableframe" align="center">
  		<input type="hidden" id="userid" name="userid" value="<bean:write name="CONFIG" property="userid" scope="request"/>">
           <tr  class="listtitle">
    		        <td colspan="2">
    		        个人配置中心
    		        </td>
    		</tr>
    		
    		<tr>
              <td  Width="150" class="listcellrow">
    		    报警开(1)关(0)
    		   </td>
    		   <td class="listcellrow">
    		     <bean:write name="CONFIG" property="onoff" scope="request"/>
    		    </td>
    	   </tr>
           <tr>
              <td  class="listcellrow">
    		     个股亏损报警条件
    		    
    		   </td>
    		  <td  class="listcellrow">
    		  
    		     <bean:write name="CONFIG" property="confirmbfb" scope="request"/>
    		     <br>
    		    </td>
    	   </tr>
    	   <tr>
              <td class="listcellrow">
    		     印花税（买入时）
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="yinhuashuibuy" scope="request"/>
    		    </td>
    	   </tr>
    	   <tr>
              <td   class="listcellrow">
    		     印花税（卖出时）
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="yinhuashuisell" scope="request"/>
    		    </td>
    	   </tr>
    		<tr>
              <td   class="listcellrow">
    		     单只股票仓位报警
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="onegupiaocangweibfb" scope="request"/>
    		    </td>
    	   </tr>
    	   <tr>
              <td  class="listcellrow">
    		     交易分类仓位报警
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="onejiflcangweibfb" scope="request"/>
    		    </td>
    	   </tr>
    	   <tr>
              <td   class="listcellrow">
    		     券商佣金
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="yongjin" scope="request"/>
    		    </td>
    	   </tr>
    	    <tr>
              <td   class="listcellrow">
    		     券商佣金最低
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="yongjinmin" scope="request"/>
    		    </td>
    	   </tr>
    	    <tr>
              <td   class="listcellrow">
    		     过户费（上海A股）
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="guohufei" scope="request"/>
    		    </td>
    	   </tr>
    	   <tr>
              <td   class="listcellrow">
    		     过户费最低（上海A股）
    		   </td>
    		   <td class="listcellrow">
				<bean:write name="CONFIG" property="guohufeimin" scope="request"/>
    		    </td>
    	   </tr>
    	   
    	   <tr>
              <td   class="listcellrow">
    		     是否隐藏T后对应数据
    		   </td>
    		   <td class="listcellrow">
    		   
    		   
    		   <logic:equal value="" name="CONFIG" property="flag1">否</logic:equal>
    		   
    		   <logic:notEqual value="" name="CONFIG" property="flag1"><bean:write name="CONFIG" property="flag1" scope="request"/></logic:notEqual>
				
				
				
    		    </td>
    	   </tr>
    	</table>	
    	
    	</form>
    	</center>
  </body>
  <script language="javascript"> 
  
  function mypreupdate(){
	targetForm.action ="<%=path%>/configPreUpdate.do"; 
	targetForm.submit();
 }
 
 

</script>
</html>