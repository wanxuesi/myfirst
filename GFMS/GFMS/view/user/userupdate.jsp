
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>\
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>用户修改功能</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    <base href="<%=basePath%>">
	<script type="text/javascript" src="dwr/interface/danweibo.js"></script>  
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script>  
    <script language="javascript"> 

	</script>
  </head>
  <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    String bmname = baseUserContext.getDwname();
  			    int bmId = baseUserContext.getDwId();
    		%>
  <body  onload='dwr.util.useLoadingMessage();getBanzuIds()'>
   <center> 用户修改功能
    <form id="add" action="<%=path%>/userUpdate.do" method="POST">
         <table align="center" >
           
    	   <tr>
    	      <td   class="listcellrow">  
    		     用户名
    		   </td>
    		   <td   class="listcellrow">
    		    <input type="hidden" id="id" name="id" value="<bean:write name="USER" property="id" scope="request"/>">
    		    <input type="text" id="xm" name="xm"  class="text" value="<bean:write name="USER" property="xm" scope="request"/>" readonly="readonly"><br>
    		   </td>
    		</tr>
    		<tr>
    	      <td   class="listcellrow">  
    		     开始有效时间
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="text" id="datestart" name="datestart" size="20" class="text" value="<bean:write name="USER" property="datestart" scope="request"  format="yyyy-MM-dd"/>"  readonly="readonly">
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    		<tr>
    	      <td   class="listcellrow">  
    		     结束有效时间
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="text" id="dateend" name="dateend" size="20" class="text" value="<bean:write name="USER" property="dateend" scope="request"  format="yyyy-MM-dd"/>"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    		<tr>
              <td >
    		     角色
    		   </td>
    		   <td>
    		   <input type="hidden" id="tmpotherflag" name="tmpotherflag" value="<bean:write name="USER" property="otherflag" scope="request"/>">
    		    <select id="otherflag" name="otherflag" class="text">
    		   <OPTION value="普通用户" >普通用户</OPTION>
    		   <OPTION value="银牌用户" >银牌用户</OPTION>
    		   <OPTION value="金牌用户" >金牌用户</OPTION>
    		   <OPTION value="钻石用户" >钻石用户</OPTION>
    		   
    		   </select>
    		    </td>
    	   </tr>
    	   <tr>
              <td >
    		     账号分类
    		   </td>
    		   <td>
    		   <input type="hidden" id="tmpwx" name="tmpwx" value="<bean:write name="USER" property="wx" scope="request"/>">
    		   
    		     <select id="wx" name="wx" class="text">
    		   <OPTION value="实际">实际</OPTION>
    		   <OPTION value="虚拟" >虚拟</OPTION>
    		   
    		   
    		   </select>
    		   
    		 
    		    </td>
    	   </tr>   
    		    <tr>
    		        <td >
    		              <input type="button" value="提交" onclick="chickme();">
    		        </td>
    		        <td >
    		              <input type="reset" value="重置">
    		        </td>
    		    </tr>
    		  </table>
    	</form>
    	</center>
  </body>
  
  <script language="javascript">
  
   $("otherflag").value= $("tmpotherflag").value;
   $("wx").value= $("tmpwx").value;
	function chickme(){
		
		var dateend=document.all.add.dateend.value;
				
		
		if(dateend.length<4){
			alert("结束有效时间不能为空！");
		}
		else{		
        document.all.add.submit();
        } 
    }   		
</script>
</html>
