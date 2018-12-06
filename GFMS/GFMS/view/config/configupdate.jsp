
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
  	
   <center>  <br>
  
		
		&nbsp;&nbsp;&nbsp;&nbsp;
		
    <form id="add" name="add" action="<%=path%>/configUpdate.do" method="post">
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
    		   <input type="text" id="onoff" name="onoff" value="<bean:write name="CONFIG" property="onoff" scope="request"/>" class="text">
    		     
    		    </td>
    	   </tr>
           <tr>
              <td  class="listcellrow">
    		     个股亏损报警条件
    		    
    		   </td>
    		  <td  class="listcellrow">
    		     <input type="text" id="confirmbfb" name="confirmbfb" value="<bean:write name="CONFIG" property="confirmbfb" scope="request"/>" class="text">  		    
    		     
    		     <br>
    		    </td>
    	   </tr>
    	   <tr>
              <td    class="listcellrow">
    		     印花税（买入时）
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="yinhuashuibuy" name="yinhuashuibuy" value="<bean:write name="CONFIG" property="yinhuashuibuy" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    	   <tr>
              <td    class="listcellrow">
    		     印花税（卖出时）
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="yinhuashuisell" name="yinhuashuisell" value="<bean:write name="CONFIG" property="yinhuashuisell" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    		<tr>
              <td  class="listcellrow">
    		     单只股票仓位报警
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="onegupiaocangweibfb" name="onegupiaocangweibfb" value="<bean:write name="CONFIG" property="onegupiaocangweibfb" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    	   <tr>
              <td    class="listcellrow">
    		     交易分类仓位报警
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="onejiflcangweibfb" name="onejiflcangweibfb" value="<bean:write name="CONFIG" property="onejiflcangweibfb" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    	   <tr>
              <td  class="listcellrow">
    		     券商佣金
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="yongjin" name="yongjin" value="<bean:write name="CONFIG" property="yongjin" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    	    <tr>
              <td    class="listcellrow">
    		     券商佣金最低
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="yongjinmin" name="yongjinmin" value="<bean:write name="CONFIG" property="yongjinmin" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    	    <tr>
              <td     class="listcellrow">
    		     过户费（上海A股）
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="guohufei" name="guohufei" value="<bean:write name="CONFIG" property="guohufei" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    	   <tr>
              <td   class="listcellrow">
    		     过户费最低（上海A股）
    		   </td>
    		   <td class="listcellrow">
    		   <input type="text" id="guohufeimin" name="guohufeimin" value="<bean:write name="CONFIG" property="guohufeimin" scope="request"/>" class="text">
				
    		    </td>
    	   </tr>
    	   <tr>
              <td     class="listcellrow">
    		     是否隐藏T后对应数据
    		   </td>
    		   <td class="listcellrow">
    		   <logic:equal value="" name="CONFIG" property="flag1"><input type="text" id="flag1" name="flag1" value="否" class="text"></logic:equal>
    		   
    		   <logic:notEqual value="" name="CONFIG" property="flag1"><input type="text" id="flag1" name="flag1" value="<bean:write name="CONFIG" property="flag1" scope="request"/>" class="text"></logic:notEqual>
				
    		  
    		   
				
    		    </td>
    	   </tr>
    	   <tr class="listtitle">
    		        <td colspan="2">
    		              <input type="button" value="保存" class="button" onclick="chickme();">&nbsp;&nbsp;<input type="reset" value="重置" class="button">
    		        </td>
    		    </tr>
    	</table>	
    	
    	</form>
    	</center>
  </body>
  <script language="javascript"> 
  
  
 function chickme(){
 		document.getElementById("add").submit();	
         
} 
 

</script>
</html>