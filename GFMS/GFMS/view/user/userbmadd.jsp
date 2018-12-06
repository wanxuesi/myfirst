
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>userbmadd.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

 
	<script type="text/javascript" src="dwr/interface/danweibo.js"></script> 
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script>  
    <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    String bmname = baseUserContext.getDwname();
  			    if(bmname==null){
  			   		 bmname="";
  			    }
  			    int bmId = 0;
  			    String juese = baseUserContext.getJuese();
  			    if(!juese.equals("超级管理员")){
  			   bmId =  baseUserContext.getDwId();
  			    }
    		%>
    <script language="javascript"> 
  <%
   if(juese.equals("超级管理员")){
   
   %>

	function getDanweiIds(){
    	danweibo.getDanweiIds(callbackDanweiIds);    
   	}
    function callbackDanweiIds(data){     
        
      dwr.util.removeAllOptions("dwId");
      dwr.util.addOptions("dwId", data);
      
   }
   
   <%
   }else{
   
   }
   %>
	</script>
  </head>
  
  
  <body  onload='dwr.util.useLoadingMessage();  
  <%
   if(juese.equals("超级管理员")){
    %>getDanweiIds();<%
   }else{
  
   }
   %>
'>
   <center> 地区下新用户添加功能
    <form id="add" action="<%=path%>/userAdd.do" method="POST">
         <table align="center" >
           
    	   <tr>
    	      <td >  
    		     用户名
    		   </td>
    		   <td>
    		      <input type="text" id="xm" name="xm"  class="text"><br>
    		   </td>
    		</tr>
    		
    		<tr>
    	      <td >  
    		     所属地区
    		   </td>
    		   <td>
    		   
    		   <%
			   if(juese.equals("超级管理员")){
			    %>
			 <select id="dwId" name="dwId" class="text">
    		   
    		   </select>
				<%
			   }else{
			  	%><input type="text" id="bm" name="bm"  value="<%=bmname%>" class="text" readonly="readonly">
    		      <input type="hidden" id="dwId" name="dwId" value="<%=bmId%>"><br><%
			   }
			   %>
    		   
    		   
    		   
    		   
    		   </td>
    		</tr>
    		<tr>
              <td >
    		     角色
    		   </td>
    		   <td>
    		     <select id="otherflag" name="otherflag" class="text">
    		   <OPTION value="普通用户"   selected>普通用户</OPTION>
    		   <OPTION value="银牌用户" >银牌用户</OPTION>
    		   <OPTION value="金牌用户" >金牌用户</OPTION>
    		   <OPTION value="钻石用户" >钻石用户</OPTION>
    		   
    		    <OPTION value="省级管理员">省级管理员</OPTION>
    		   
    		   
    		   </select>
    		   
    		 
    		    </td>
    	   </tr>
    	   <tr>
              <td >
    		     账号分类
    		   </td>
    		   <td>
    		     <select id="wx" name="wx" class="text">
    		   <OPTION value="实际"   selected>实际</OPTION>
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
function chickme(){
		
		var xm=document.all.add.xm.value;
		var dwId=document.all.add.dwId.value;
		
		
		if(xm.length<1){
			alert("用户名不能为空！");
		}else
		if(dwId.length<1){
			alert("所属部门名称不能为空！");
		}
		else{		
        document.all.add.submit();
        } 
    }   		
</script>
</html>
