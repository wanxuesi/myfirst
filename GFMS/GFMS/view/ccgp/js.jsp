
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
    
    <title>股票计算器</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
  </head>
 
  <body onload="chushihua();getOneGpyl();">
      
		 <%@ include file="/view/message.jsp"%>
    
     <center> 
    <form id="add" name="add" action="" target="_blank" method="post">
         <table  Width="500" class="tableframe" align="center">
         <%
    		
    		
    		%>
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        股票计算器
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
    		     当前买入价  		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="dqj" name="dqj" value="<bean:write name="ORDER" property="scj" scope="request"  format=".00"/>"  class="text" onchange="getChange();" >
    		    &nbsp;最低回本价:
    		    <input type="text" id="zdhbj" name="zdhbj" value="" readonly="readonly" class="text"><br>
    		    </td>
    	   </tr>
    	   <tr>
              <td  class="listcellrow">
    		     计划买入股数  		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="planNumb" name="planNumb" value="1000" class="text"  onchange="getChange();" ><br>
    		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  class="listcellrow">
    		     计划动用资金  		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="syzj" name="syzj" value="" class="text" onchange="getChangeZj();" ><br>
    		    
    		    </td>
    	   </tr>
           <tr>
              <td  class="listcellrow">
    		     混合成本价  		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="hunhecbj" name="hunhecbj" value="" class="text" onchange="getChangeHunhecbj();"><br>
    		    
    		    </td>
    	   </tr>
    	     <tr>
              <td  class="listcellrow">
    		     混合股数  		     
    		     </td>    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="hunhegs" name="hunhegs" value="" class="text" onchange="getChangeHunhegs();"><br>
    		    
    		    </td>
    	   </tr> 	
    		  </table>
    	</form>
    	</center>
  </body>
  
</html>
<script language="javascript">
  function getZdhbj(){
  		var yongJin=parseFloat($("dqj").value)*parseInt($("planNumb").value)*0.0003;
		if(yongJin<5){yongJin=5;}
		
		$("zdhbj").value=(parseFloat($("dqj").value)*parseInt($("planNumb").value)+(parseFloat($("dqj").value)*parseInt($("planNumb").value)*0.00002)*2+yongJin*2+(parseFloat($("dqj").value)*parseInt($("planNumb").value)*0.001))/parseInt($("planNumb").value);//最低回本价。
		
  
  }
	
  function getChange(){
  
    $("syzj").value=parseInt($("planNumb").value)*parseFloat($("dqj").value);
  	$("hunhegs").value=parseInt($("planNumb").value)+parseInt($("numb").value);
 	$("hunhecbj").value=(parseFloat($("syzj").value)+parseInt($("numb").value)*parseFloat($("cbj").value))/(parseInt($("planNumb").value)+parseInt($("numb").value));
 		
 	getZdhbj();	 
  }
  
  function getChangeZj(){
  
    $("planNumb").value=parseFloat($("syzj").value)/parseFloat($("dqj").value);
  	$("hunhegs").value=parseInt($("planNumb").value)+parseInt($("numb").value);
 	$("hunhecbj").value=(parseFloat($("syzj").value)+parseInt($("numb").value)*parseFloat($("cbj").value))/(parseInt($("planNumb").value)+parseInt($("numb").value));
 	getZdhbj();	
  }
  
  function getChangeHunhegs(){
    $("planNumb").value = parseInt($("hunhegs").value)-parseInt($("numb").value);
     $("syzj").value=parseInt($("planNumb").value)*parseFloat($("dqj").value);
 	$("hunhecbj").value=(parseFloat($("syzj").value)+parseInt($("numb").value)*parseFloat($("cbj").value))/(parseInt($("planNumb").value)+parseInt($("numb").value));
 	getZdhbj();	
  }
  
  function getChangeHunhecbj(){
  
  		$("syzj").value = (parseFloat($("dqj").value)*parseInt($("numb").value)*parseFloat($("hunhecbj").value)-parseFloat($("cbj").value)*parseInt($("numb").value)*parseFloat($("dqj").value))/(parseFloat($("dqj").value)-parseFloat($("hunhecbj").value));
        $("planNumb").value = parseFloat($("syzj").value)/parseFloat($("dqj").value);
        $("hunhegs").value=parseInt($("planNumb").value)+parseInt($("numb").value);
 		getZdhbj();	
  }
 
 function chushihua(){ 
 
 $("syzj").value=parseInt($("planNumb").value)*parseFloat($("dqj").value);
 $("hunhegs").value=parseInt($("planNumb").value)+parseInt($("numb").value);
 $("hunhecbj").value=(parseFloat($("syzj").value)+parseInt($("numb").value)*parseFloat($("cbj").value))/(parseInt($("planNumb").value)+parseInt($("numb").value));
 getZdhbj();	
 
 }
 
 
 </script>
