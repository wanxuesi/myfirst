
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="bsw.fwk.BaseUserContext"%>
<%@ page import="com.fuguo.util.DateUtil"%>
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


	   
	<script language="javascript">   
   
	
	
  
   
   


   
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
    		
    		<%
  			java.util.Date d = new Date();
  			DateUtil dateUtil = new DateUtil();
  			java.util.Date afterDate = dateUtil.getAfertNMonth(d,24);
  			java.text.SimpleDateFormat   sdf   =   new   java.text.SimpleDateFormat("yyyy-MM-dd");   
			String rq = sdf.format(afterDate);
  		%>
 <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
	<%@ include file="/view/message.jsp"%>  
         <INPUT name="qp" id="qp" type="button" value="&nbsp;&nbsp;全&nbsp;屏&nbsp;&nbsp;"  class="button" onClick="javascript:quanping();">
         <br>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
	</FORM>
    <form name="myForm" id="myForm" action="tsxgQuery.do" target="_blank" method="post">
			<table width="98%"  class="tableframe" align="center">
			<tr  class="listtitle">
    		        <td colspan="7">  &nbsp;&nbsp; 特色选股查询功能 </td>
    		</tr>
			
			<tr>
		<td colspan="2">
		
		
		
		
		总市值&le;<input type="text" id="zsz" name="zsz" value="100" size="5"  class="text"><font color=red>亿</font>元
		</td>
		<td colspan="6">		
		日期<input type="text" id="queryDate" name="queryDate" size="10" class="text"  value="<%=rq%>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">时
    	流通市值 &le;<input type="text" id="ltsz" name="ltsz" value="15" size="5"  class="text"><font color=red>亿</font>元
    	&nbsp;&nbsp;<font color=red>注：日期默认为当天时，流通市值即实际流通市值（流通市值栏不能为空）</font>
		</td>
		</tr>
		
		
		<tr class="listtitle">
					<td colspan="7" >
										
					<INPUT name="a" id="a" type="button" value="&nbsp;&nbsp;执行选股&nbsp;&nbsp;" class="button" onclick="javascript:queryGo();">
					
					

					</td>
					
		</tr>
	</table>
		</form>
  </body>
  <script language="javascript">
  
  
		 		
		
	function queryGo(){
	
		if($("ltsz").value==""){
		alert("请填写流通市值！");
		return;
		}
		if($("queryDate").value==""){
		alert("请填写日期！");
		return;
		}
		
		document.all.myForm.submit();
	}
function quanping(){
	
			targetForm.action ="<%=path%>/view/tsxg/tsxgquery.jsp"; 
			 targetForm.submit();		
		
	}
</script>
</html>
