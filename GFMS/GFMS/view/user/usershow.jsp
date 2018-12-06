
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
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
    <base href="<%=basePath%>">
    
    <title>用户主页面</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
   
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
   
  </head>
  <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			  
  			    String juese = baseUserContext.getJuese();
    		%>
	
    <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
  
		 <%@ include file="/view/message.jsp"%>
   <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		<CENTER><input type="button" name="新增省级用户" value="新增省级用户" class="button" onclick='javascript:addBmNew(); '>
		
		<%
   if(juese.equals("超级管理员")){
   }else{
   %><input type="button" name="新增地区用户" value="新增地区用户" class="button" onclick='javascript:addBzNew(); '><%
   }
   %>
		
		
		
		</CENTER>
			
    <TABLE   Width="90%" class="tableframe" align="center">
    <tr  class="listtitle">
    <td align="center" class="listcelltitle">序</td>
    <td align="center" class="listcelltitle">用户名</td>
    <td align="center" class="listcelltitle">所属地区</td>
    <td align="center" class="listcelltitle">开始时间</td>
    <td align="center" class="listcelltitle">结束时间</td>
    <td align="center" class="listcelltitle">角色</td>
    <td align="center" class="listcelltitle">账号分类</td>
    <td align="center" class="listcelltitle">操作</td>
    </tr>
    <logic:iterate id="user" name="USER" type="com.fuguo.dto.UserDTO" scope="request">
    <tr onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
    <td  onclick="showOne(<bean:write name="user" property="id" />);"  align="center" class="listcellrow"><bean:write name="user" property="id"/> </td>
    <td  onclick="showOne(<bean:write name="user" property="id" />);" align="center" class="listcellrow"><bean:write name="user" property="xm"/> </td>
    <td  onclick="showOne(<bean:write name="user" property="id" />);" align="center" class="listcellrow"><bean:write name="user" property="dwname"/></td>
    <td  onclick="showOne(<bean:write name="user" property="id" />);" align="center" class="listcellrow"><bean:write name="user" property="datestart"   format="yyyy-MM-dd"/> </td>
    <td  onclick="showOne(<bean:write name="user" property="id" />);" align="center" class="listcellrow"><bean:write name="user" property="dateend"   format="yyyy-MM-dd"/> </td>
    <td  onclick="showOne(<bean:write name="user" property="id" />);" align="center" class="listcellrow"><bean:write name="user" property="otherflag"/> </td>
    <td  onclick="showOne(<bean:write name="user" property="id" />);" align="center" class="listcellrow"><bean:write name="user" property="wx"/> </td>
    
    
    <td align="center" class="listcellrow">
    <input type="button" name="删除" value="删除" class="button" onclick="javascript:userDelete('<bean:write name="user" property="id"/>','<bean:write name="user" property="xm"/>'); ">
    
    </td>
    
    </tr>
    
    </logic:iterate>
    
    </TABLE>
    
   
  </body>
  <script language="javascript">
	
		function userDelete(id,xm){
			if(confirm("您确认删除用户："+xm)){
			
			targetForm.action ="<%=path%>/userDelete.do?id="+id; 
			 targetForm.submit();
			}
			
		}
		
		function addBmNew(){
			targetForm.action ="<%=path%>/view/user/userbmadd.jsp"; 
			 targetForm.submit();
		}
		function addBzNew(){
			targetForm.action ="<%=path%>/view/user/userbzadd.jsp"; 
			 targetForm.submit();
		}
		function addQjNew(){
			targetForm.action ="<%=path%>/view/user/userqjadd.jsp"; 
			 targetForm.submit();
		}
		function showOne(id){
		
			targetForm.action ="<%=path%>/userShowOne.do?id="+id; 
			 targetForm.submit();
		
		
		}
		
	</script>
</html>
