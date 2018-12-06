
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="bsw.fwk.BaseUserContext"%>
<html>
  <head>
    
    
    <title>My JSP 'query.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
      <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
	<script type="text/javascript" src="js/engine.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	<script type="text/javascript" src="dwr/interface/danweibo.js"></script> 
 	
 	<script type="text/javascript" src="dwr/interface/lxbo.js"></script>
	<script type="text/javascript" src="js/checkboks.js"></script>
	   
	<script language="javascript">   
   
	function getLxs(){
    lxbo.getLxs(callbackLxs);    
   }
    function callbackLxs(data){     
        
      //dwr.util.removeAllOptions("jifl");
      //dwr.util.addOptions("jifl",["全部"]);
      dwr.util.addOptions("jifl", data);
   }
  
   
   


   
   function bijiao(){
   	 //比较两个日期，如果开始日期大于结束日期，后面的自动换。
  	 var start_rq =document.getElementById("start_rq").value;
   	 var end_rq   =document.getElementById("end_rq").value;
   	 //alert("aa");
   	if(start_rq>end_rq){
   		document.getElementById("end_rq").value=start_rq;
   	}
   }
   </script>
   
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
	
	
  </head>
  
  <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    String dwname = baseUserContext.getDwname();
  			    if(dwname==null){
  			    dwname="";
  			    }else{
  			    dwname=dwname+";";
  			    }
  			   
    		%>
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();getLxs();'>
     
		 <%@ include file="/view/message.jsp"%>
  
         <INPUT name="qp" id="qp" type="button" value="&nbsp;&nbsp;全&nbsp;屏&nbsp;&nbsp;"  class="button" onClick="javascript:quanping();">
         <br>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
	</FORM>
    <form name="myForm" id="myForm" action="jiluQuery.do" method="post">
			<table width="98%"  class="tableframe" align="center">
			<tr  class="listtitle">
    		        <td colspan="7">  &nbsp;&nbsp; 交易记录查询功能 </td>
    		</tr>
			
			<tr>
		<td colspan="2" class="listcellrow">
		
		
		
		
		证券名称&nbsp;<input type="text" id="zqmc" name="zqmc" value="" class="text">
		证券代码&nbsp;<input type="text" id="zqdm" name="zqdm" value="" class="text">
		</td>
		
		<td colspan="4" class="listcellrow">
		 交易时间
		<select id="weekFlag" name="weekFlag" class="text">
    		   
    		   
    		   <OPTION value="2" selected>&nbsp;本周&nbsp;</OPTION>
    		   <OPTION value="1" >&nbsp;上周&nbsp;</OPTION>
    		   <OPTION value="4">&nbsp;本月&nbsp;</OPTION>
    		   <OPTION value="0"  selected>&nbsp;全部&nbsp;</OPTION>
    		   
    		   
		</select>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" id="start_rq" name="start_rq" size="10" class="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		 ～
    	<input type="text" id="end_rq" name="end_rq" size="10" class="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		
		
		</td>
			<tr>
		<td colspan="2" class="listcellrow">
		
		
		
		 记录状态
		 <select id="flag1" name="flag1" class="text">
    		   <OPTION value="" selected>&nbsp;全部&nbsp;</OPTION>
    		    <OPTION value="未处理" >未处理</OPTION>
    		   <OPTION value="已处理">已处理</OPTION>
    		   
		</select>
		
		</td>
		
		<td colspan="4" class="listcellrow">
		
		 买卖标志
		 <select id="mmflag" name="mmflag" class="text">
    		   <OPTION value="" selected>&nbsp;全部&nbsp;</OPTION>
    		    <OPTION value="买入" >&nbsp;买入&nbsp;</OPTION>
    		   <OPTION value="卖出">&nbsp;卖出&nbsp;</OPTION>
    		   
		</select>&nbsp;&nbsp;
		 交易分类&nbsp;&nbsp;
		 <select id="jifl" name="jifl" class="text">
    		   <OPTION value="" selected>&nbsp;全部&nbsp;</OPTION>
    		   
    		   
		</select>
		</td>
		<tr class="listtitle">
					
					<td colspan="2" >					
					<INPUT name="a" id="a" type="button" value="&nbsp;&nbsp;查&nbsp;找&nbsp;&nbsp;"  class="button" onClick="javascript:queryGo();">
					</td>
					<td  colspan="4"  align="left"><input type="button" name="新增交易记录" value="新增交易记录" class="button" onclick='javascript:addNew(); ' >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" name="批量导入" value="批量导入" class="button" onclick='javascript:addPiNew(); '>
					</td>
					
		</tr>
	</table>
		</form>
  </body>
  <script language="javascript">
  
  
		 		
	function addNew(){
			targetForm.action ="<%=path%>/view/jilu/jiluadd.jsp"; 
			 targetForm.submit();
		}
	function addPiNew(){
			targetForm.action ="<%=path%>/view/jilu/jiluuploadview.jsp"; 
			 targetForm.submit();
		}	
			
	function queryGo(){
		
		if(  $("zqdm").value==""||(parseInt($("zqdm").value)>=0 && parseInt($("zqdm").value)<=700000)){
			document.all.myForm.submit();
		
		}else{
			alert("证券代码不符合格式！");
		}
		
		
		
	}
function quanping(){
	
			targetForm.action ="<%=path%>/view/jilu/jiluquery.jsp"; 
			 targetForm.submit();		
		
	}
</script>
</html>
