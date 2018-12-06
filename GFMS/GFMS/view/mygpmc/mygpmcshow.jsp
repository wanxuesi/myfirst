
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
    <script type="text/javascript" src="js/engine.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="dwr/interface/lxbo.js"></script> 
   
    
    <title>自选股显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    
    
    
     
    
    
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getLxsList();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>

    <CENTER>自选股显示</CENTER>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
    <form name="myForm" id="myForm" action="" method="post"  target="_blank">
    <table Width="90%" align="center">
			<tr >
			<td align="center" colspan="4">
			 
			 
			 <input type="button" name="新增自选股" value="新增自选股" class="button" onclick='javascript:addNew(); '>
					&nbsp;&nbsp;&nbsp;
					<input type="button" value="全选" class="button" onclick="checkAll('ids')"/>
					
					&nbsp;&nbsp;&nbsp;
				<input type="button" value="取消选中" class="button" onclick="clearAll('ids')"/>	
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="批量删除" class="button" onclick="deleteMore()"/>
				&nbsp;&nbsp;&nbsp;
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/mygpmcShow.do';">
			
			</td>
			</tr>
			<tr >
			<td align="center">
			&nbsp;
			</td>
			<td align="center">
			&nbsp;
			</td>
			<td align="center">
			&nbsp;
			</td>
			<td align="center">
			&nbsp;
			</td>
			</tr>
			<tr >
			<td align="center"  colspan="4">
			 
			 请选择历史时间段
			 <input type="text" id="start_rq" name="start_rq" size="10" class="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    		 ～
    	<input type="text" id="end_rq" name="end_rq" size="10" class="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
    	
			 		&nbsp;&nbsp;&nbsp;
					
				
			交易分类&nbsp;<input type="text" size="40" id="lx" name="lx" value="">&nbsp;&nbsp;<input type="button" name="自动模拟买卖" value="自动模拟买卖" class="button" onclick='javascript:queryGo(); '>
			</td>
			</tr>
		</table>
		
		
		
		
		<TABLE   Width="900"  class="tableframe" align="center">
			<tr class="listtitle">
				<td Width="60" align="center" class="listcelltitle">
					ID
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					证券名称
				</td>
				
				
				<td Width="60" align="center" class="listcelltitle">
					证券代码
				</td>
				
				<td Width="100" align="center" class="listcelltitle">
					证券所
				</td>
				<td Width="100" align="center" class="listcelltitle">
					市场价
				</td>
				<td Width="100" align="center" class="listcelltitle">
					开始更新日期(K线)
				</td>
				<td Width="100" align="center" class="listcelltitle">
					截止更新日期
				</td>
				<td Width="50" align="center" class="listcelltitle">
					操作
				</td>
				
				
				
				
				
			</tr>
			<logic:iterate id="gpmc" name="MYGPMC" indexId="index"  type="com.fuguo.dto.MygpmcDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="60" align="center" class="listcellrow">
						<input type="checkbox" id="ids"  name="ids" value="<bean:write name="gpmc" property="id" />"/>
					
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="gpmc" property="zqmc" />
					</td>
					

				<td Width="60" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="zqdm" />
				</td>


					<td  Width="100" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="flag1" />
				</td>
				
					<td  Width="100" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="scj" />
				</td>
					<td  Width="100" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="datestart" format="yyyy-MM-dd"/>
				</td>
				
					<td  Width="100" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="date" format="yyyy-MM-dd"/>
				</td>
				<td Width="50" class="listcellrow"  align="center">
						 <span style="cursor:hand"  onclick="javascript:if(confirm('您确定删除<bean:write name="gpmc" property="zqmc" />？')){document.location.href='<%=path%>/mygpmcDelete.do?id=<bean:write name="gpmc" property="id" />';}">
							<img name="update_mode_img" src="<%=path%>/images/delete.gif" style="Width:18;height:18" border="0" alt="删除"></span>
					</td>

				

			</logic:iterate>
		</TABLE>
		
		</form>
		
			
			
		</body>
		<script language="javascript">
	
	
	function getLxsList(){
    lxbo.getLxsList(callbackLxsList);    
   }
    function callbackLxsList(data){
         
		var tmplx="";
        
      for(var i=0;i<data.length;i++){
     	
    	tmplx+=data[i].name+";";

     }
     //alert(tmplx);
     $("lx").value=tmplx;
     
    
     
     
     
   }	
	 function checkAll(checkBoxId) {     
		    var el = document.getElementsByTagName('input');     
		    var len = el.length;     
		    for(var i=0; i<len; i++){         
		    	if((el[i].type=="checkbox") && (el[i].id==checkBoxId)){
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
		function addNew(){
			targetForm.action ='<%=path%>/view/mygpmc/mygpmcadd.jsp'; 
			 targetForm.submit();
		}
			
		function deleteMore(){
		
			if(confirm("是否确定删除选中自选股？")){
				document.getElementById("myForm").action="mygpmcDeleteMore.do";	
				document.getElementById("myForm").submit();
			}
		}
		
	
   
   function queryGo(){
   		
   		//比较两个日期，如果开始日期大于结束日期，后面的自动换。
  	 var start_rq =document.getElementById("start_rq").value;
   	 var end_rq   =document.getElementById("end_rq").value;
   	 //alert("aa");
   	 if(start_rq.length<6 ||end_rq.length<6){
   	 alert("请填写日期！");
   	 return false;
   	 }
   	if(start_rq>end_rq){
   		document.getElementById("end_rq").value=start_rq;
   	}	
		var lx =document.getElementById('lx').value;
		if(lx.length<2){
		alert("请选择交易类型！");
		return false;
		}
		if(confirm("自动模拟买卖前将清空虚拟账号的所有记录，您确认进行虚拟模拟？")){	
			document.getElementById("myForm").action="mygpmcAutomncz.do";	
			document.getElementById("myForm").submit();	
		}
	}
	</script>
	
	
    
</html>
