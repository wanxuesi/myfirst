
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
     <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">
    
    <title>股票除权相关</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
  </head>
 <%
  			java.util.Date d = new Date();
  			java.text.SimpleDateFormat   sdf   =   new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");   
			String rq = sdf.format(d);
  		%>
  <body onload="getOneGpyl();">
      
		 <%@ include file="/view/message.jsp"%>
    
     <center> 
    <form id="add" name="add" action="<%=path%>/cq.do" target="_self" method="post">
         <table  Width="500" class="tableframe" align="center">
         
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        股票除权相关
    		        </td>
    		</tr>
    		<tr>
              <td  class="listcellrow">
    		     证券名称   		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="zqmc" name="zqmc" value="<bean:write name="ORDER" property="zqmc" scope="request"/>" readonly="readonly"  class="text"><br>
    		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  class="listcellrow">
    		     证券代码    		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="zqdm" name="zqdm" value="<bean:write name="ORDER" property="zqdm" scope="request"/>" readonly="readonly" class="text"><br>  		    
    		    </td>
    	   </tr>
    	   
    	   <tr>
              <td  class="listcellrow">
    		     持仓数量   		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="numb" name="numb" value="<bean:write name="ORDER" property="cysl" scope="request"/>" readonly="readonly"  class="text"><br>
    		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  class="listcellrow">
    		     成本价  		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="cbj" name="cbj" value="<bean:write name="ORDER" property="cbj" scope="request"  format=".000" />" readonly="readonly"  class="text"><br>
    		    
    		    </td>
    	   </tr>
    	   	 <tr>
              <td  class="listcellrow">
    		        		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		     
    		    </td>
    	   </tr>
    	    <tr>
              <td  class="listcellrow">
    		     除权设置  		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    每&nbsp; &nbsp;<input type="text" id="baseNumb" name="baseNumb" value="10" size="5" class="text">股 &nbsp;<br>
    		    送/转<input type="text" id="songNumb" name="songNumb" value="0" size="5" class="text">股 &nbsp;
    		    派<input type="text" id="paiNumb" name="paiNumb" value="0" size="5" class="text">元 <br>
    		    除权日期<input type="text" id="jysj" name="jysj" size="20" class="text" value="<%=rq%>"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
    		  
    		    
    		    </td>
    	   </tr>
    	   
    	    <tr class="listtitle">
    		        <td colspan="2">
    		              <input type="button" value="确定除权" class="button" onclick="chickme();">&nbsp;&nbsp;<input type="reset" value="重置" class="button">
    		        </td>
    		    </tr>
    		  </table>
    	</form>
    	</center>
  </body>
  
</html>
<script language="javascript">
  
 function chickme(){

	if(parseInt($("baseNumb").value)<0 ||parseInt($("songNumb").value)<0||parseInt($("paiNumb").value)<0){
		alert("请重新填写正确的格式！");
		return;
	}
	if(confirm("您确定'"+$("zqmc").value+"'进行每"+$("baseNumb").value+"股，送/转("+$("songNumb").value+"股，在再派"+$("paiNumb").value+"元现金的设置？！\n 确认后，将无法撤销！！")){
		
 		   document.getElementById("add").submit();	
       } 
} 
 
 </script>
