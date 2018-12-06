
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.fuguo.util.DateUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    <base href="<%=basePath%>">
    
    <title>盈亏分析主页面显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
  <%
  	SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd");
	Date nowDate = new Date();
	DateUtil dUtil = new DateUtil();
	Date fistDate = dUtil.getBeforeNDay(nowDate,30);
	
	String fistDateStr = sdfymd.format(fistDate);
	String nowDateStr = sdfymd.format(nowDate);
   %>
    <script language="javascript">   
		//
		
		
		
   function getYear(){
		var date = new Date();
		//alert(date.getFullYear());
		$("year").value=date.getFullYear()-1;
		$("year2").value=date.getFullYear();
		
		var tmp_month = date.getMonth()+2;
		if(tmp_month<=12){
			$("start_month").value=tmp_month;
		}else{
		$("start_month").value=1;
		}
		$("end_month").value=date.getMonth()+1;
	}
    function bijiao(){
    var year =parseInt(document.getElementById("year").value);
     var year2 =parseInt(document.getElementById("year2").value);
     
  	 var start_month =parseInt(document.getElementById("start_month").value);
   	 var end_month   =parseInt(document.getElementById("end_month").value);
   	if(year<year2){
   		
   	}else{
   	
   		document.getElementById("year2").value=year;
   		if(start_month>end_month){
   		   	
   			document.getElementById("end_month").value=start_month;
   		}
   	}
   }
   
	 function bijiao2(){
   	 //比较两个日期，如果开始日期大于结束日期，后面的自动换。
  	 var start_rq =document.getElementById("start_rq").value;
   	 var end_rq   =document.getElementById("end_rq").value;
   	 //alert("aa");
   	if(start_rq>end_rq){
   		document.getElementById("end_rq").value=start_rq;
   	}
   }	
	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getYear();getOneGpyl();'>
  
  
		 <%@ include file="/view/message.jsp"%>
   
  <center>
   盈亏分析主页面显示
	</center>
	总累计盈亏：<logic:greaterEqual value="0"  name="ZLJYK">
					<font color="red"><bean:write name="ZLJYK" scope="request"/></font>
				</logic:greaterEqual>
				<logic:lessThan value="0"  name="ZLJYK">
					<font color="green"><bean:write name="ZLJYK" scope="request"/></font>
				</logic:lessThan>&nbsp;&nbsp;
		股票盈利：<bean:write name="LJYK" scope="request"/>&nbsp;&nbsp;
	股息红利等：<bean:write name="GXHL" scope="request"/>&nbsp;&nbsp;
	<font color="#cccccc">累计交易费：<bean:write name="LJJYF" scope="request"/></font><br>			
				<form name="myForm" action="" target="_blank" method="post">
				<INPUT type="hidden" id="flag1" name= "flag1" value="<bean:write name="GXHL" scope="request"/>">
				<SELECT id="year" name="year" class="text" onclick="javascript:bijiao();">
    		   <OPTION value="2009">&nbsp;2009&nbsp;</OPTION>
    		   <OPTION value="2010">&nbsp;2010&nbsp;</OPTION>
    		   <OPTION value="2011">&nbsp;2011&nbsp;</OPTION>
    		   <OPTION value="2012">&nbsp;2012&nbsp;</OPTION>
    		   <OPTION value="2013">&nbsp;2013&nbsp;</OPTION>
    		   
    		   
    		   <OPTION value="2014">&nbsp;2014&nbsp;</OPTION>
    		   <OPTION value="2015">&nbsp;2015&nbsp;</OPTION>
    		   <OPTION value="2016">&nbsp;2016&nbsp;</OPTION>
    		   <OPTION value="2017">&nbsp;2017&nbsp;</OPTION>
    		   <OPTION value="2018">&nbsp;2018&nbsp;</OPTION>
    		   <OPTION value="2019">&nbsp;2019&nbsp;</OPTION>
    		   </SELECT>年
    		   <SELECT id="start_month" name="start_month" class="text" onclick="javascript:bijiao();">
				
    		   <OPTION value="1" selected>一 月</OPTION>
    		   <OPTION value="2">二 月</OPTION>
    		   <OPTION value="3">三 月</OPTION>
    		   <OPTION value="4">四 月</OPTION>
    		   <OPTION value="5">五 月</OPTION>
    		   <OPTION value="6">六 月</OPTION>
    		   <OPTION value="7">七 月</OPTION>
    		   <OPTION value="8">八 月</OPTION>
    		   <OPTION value="9">九 月</OPTION>
    		   <OPTION value="10">十 月</OPTION>
    		   <OPTION value="11">十一月</OPTION>
    		   <OPTION value="12">十二月</OPTION>
    		   </SELECT>～
    		   <SELECT id="year2" name="year2" class="text" onclick="javascript:bijiao();">
    		   <OPTION value="2009">&nbsp;2009&nbsp;</OPTION>
    		   <OPTION value="2010">&nbsp;2010&nbsp;</OPTION>
    		   <OPTION value="2011">&nbsp;2011&nbsp;</OPTION>
    		   <OPTION value="2012">&nbsp;2012&nbsp;</OPTION>
    		   <OPTION value="2013">&nbsp;2013&nbsp;</OPTION>
    		   
    		   <OPTION value="2014">&nbsp;2014&nbsp;</OPTION>
    		   <OPTION value="2015">&nbsp;2015&nbsp;</OPTION>
    		   <OPTION value="2016">&nbsp;2016&nbsp;</OPTION>
    		   <OPTION value="2017">&nbsp;2017&nbsp;</OPTION>
    		   <OPTION value="2018">&nbsp;2018&nbsp;</OPTION>
    		   <OPTION value="2019">&nbsp;2019&nbsp;</OPTION>
    		   </SELECT>年
    		   <SELECT id="end_month" name="end_month"  class="text" onclick="javascript:bijiao();">
    		   <OPTION value="1" selected>一 月</OPTION>
    		   <OPTION value="2">二 月</OPTION>
    		   <OPTION value="3">三 月</OPTION>
    		   <OPTION value="4">四 月</OPTION>
    		   <OPTION value="5">五 月</OPTION>
    		   <OPTION value="6">六 月</OPTION>
    		   <OPTION value="7">七 月</OPTION>
    		   <OPTION value="8">八 月</OPTION>
    		   <OPTION value="9">九 月</OPTION>
    		   <OPTION value="10">十 月</OPTION>
    		   <OPTION value="11">十一月</OPTION>
    		   <OPTION value="12">十二月</OPTION>
    		   </SELECT><INPUT name="b" id="b" type="button" value="盈亏分析月折线图"  class="button" onClick="javascript:ykfxZxt();">
				</form>
				<br>
				
				<form name="myForm2" action="" target="_blank" method="post">
				<INPUT type="hidden" id="flag1" name= "flag1" value="<bean:write name="GXHL" scope="request"/>">
				
				<input type="text" id="start_rq" name="start_rq" size="10" class="text" value="<%=fistDateStr%>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		～
    	<input type="text" id="end_rq" name="end_rq" size="10" class="text" value="<%=nowDateStr%>"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    	
    		   
				<INPUT name="b" id="b" type="button" value="盈亏分析日折线图"  class="button" onClick="javascript:ykfxZxt2();">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<INPUT name="c" id="c" type="button" value="总资产-市值-盈亏折线图"  class="button" onClick="javascript:zzcGpszZxt();">
				
				</form>
				<br>

	<form name="targetForm" id="targetForm" action="" target="_blank" method="post">
	<input type="button" value="全选" class="button" onclick="checkAll('ids')"/>&nbsp;&nbsp;&nbsp;
	<input type="button" value="取消选中" class="button" onclick="clearAll('ids')"/>&nbsp;&nbsp;&nbsp;
	<input type="button" value="盈亏分析" class="button" onclick="getYkfxs();"/>
	<br>
	
	
	<%
	
			Map zqmcMap = (Map)request.getAttribute("ZQMCS");
			Iterator it = zqmcMap.entrySet().iterator(); 
			int i=1;
     		while (it.hasNext()) { 
				Map.Entry entry = (Map.Entry) it.next(); 
				Object key = entry.getKey(); 
				Object value = entry.getValue(); 
	%>
	<input type= "checkbox"  id="ids" name="ids" value='<%=key%>'/><%=value%>&nbsp;&nbsp;
	
	<%	
				if(i%5==0){
				%>
				<br>
				<% }
				i++;			
			}     	
    %>
	
  	</form>	
  	
  	
   
   <center> <br>
    
    	</center>
  </body>
  <script language="javascript">
  function ykfxZxt(){
		
		document.all.myForm.action ="<%=path%>/ykfxZxt.do"; 
		
		
		document.all.myForm.submit();
	}
	
	 function ykfxZxt2(){
		bijiao2();
		document.all.myForm2.action ="<%=path%>/ykfxZxt2.do"; 
		
		
		document.all.myForm2.submit();
	}
	 function zzcGpszZxt(){
		bijiao2();
		document.all.myForm2.action ="<%=path%>/zzcGpszZxt.do"; 
		
		
		document.all.myForm2.submit();
	}
	
	function getYkfxs(){
			document.all.targetForm.action="<%=path%>/ykfx.do";
			document.all.targetForm.submit();
	}
  function checkAll(checkBoxName) {     
		    var el = document.getElementsByTagName('input');     
		    var len = el.length;     
		    for(var i=0; i<len; i++){         
		    	if((el[i].type=="checkbox") && (el[i].name==checkBoxName)){
		    		el[i].checked = true;         
		        }     
		    } 
  } 
  function clearAll(checkBoxName) {
		   var el = document.getElementsByTagName('input');
		   var len = el.length;     
		   for(var j=0; j<len; j++){         
			   if((el[j].type=="checkbox") && (el[j].name==checkBoxName)){             
			   	   el[j].checked = false;        
			   }     
		   } 
  } 
 
   
 
  
  


	
</script>
</html>