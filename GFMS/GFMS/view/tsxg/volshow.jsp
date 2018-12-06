
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
   
   <base href="<%=basePath%>">
    
    <title>VOL观察池显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>

    <CENTER>VOL观察池显示</CENTER>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
    <form name="myForm" id="myForm" action="" method="post"  target="_blank">
    <table Width="90%" align="center">
			<tr >
			<td align="center" colspan="4">
			 
			 
			 <input type="button" name="新增观察股" value="新增观察股" class="button" onclick='javascript:addNew(); '>
					&nbsp;&nbsp;&nbsp;
					<input type="button" value="全选" class="button" onclick="checkAll('ids')"/>
					
					&nbsp;&nbsp;&nbsp;
				<input type="button" value="取消选中" class="button" onclick="clearAll('ids')"/>	
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="批量删除" class="button" onclick="deleteMore()"/>
				&nbsp;&nbsp;&nbsp;
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/volShow.do';">
			
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
			<td  colspan="4" align="center">
			<input type="button" name="VOL快捷批量更新" value="VOL快捷批量更新" class="button" onclick='javascript:lsjgpi(); '>[<FONT color=red>6个月</FONT>]
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="进入历史价格、成交量更新页面" value="进入历史价格、VOL更新页面" class="button" onclick='javascript:inlsjg(); '>
			
				&nbsp;&nbsp;&nbsp;&nbsp;	
				
			<input type="button" name="VOL动态分析" value="VOL动态分析" class="button" onclick='javascript:volAutoFx(); '>[<FONT color=red>默认6个月内成交量最低的3天</FONT>]&nbsp;&nbsp;符合要求的日期如果在当前日期四个工作日内标记为红色</td>
    		
			</tr>
		</table>
		
		
		<CENTER><font color='blue'>提示：点击记录即可显示该股票的相关总市值，流通市值，解禁日期，解禁数量，日K线-VOL图</font></CENTER>
		
		<TABLE   Width="850"  class="tableframe" align="center">
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
				
				<td Width="200" align="center" class="listcelltitle">
					操作
				</td>
				
				
				
				
				
			</tr>
			<logic:iterate id="gpmc" name="GPMC" indexId="index"  type="com.fuguo.dto.GpmcDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="60" align="center" class="listcellrow">
						<input type="checkbox" id="ids"  name="ids" value="<bean:write name="gpmc" property="zqdm" />"/>
					
					</td>
					<td  Width="60"  align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
						<bean:write name="gpmc" property="zqmc" />
					</td>
					

				<td Width="60" align="center" class="listcelltitle" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
					<bean:write name="gpmc" property="zqdm" />
				</td>


					<td  Width="100" align="center" class="listcelltitle" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
					<bean:write name="gpmc" property="flag1" />
				</td>
				

				<td Width="200" class="listcellrow"  align="center">
						 <span style="cursor:hand"  onclick="javascript:if(confirm('您确定剔除<bean:write name="gpmc" property="zqmc" />？')){document.location.href='<%=path%>/volDelete.do?zqdm=<bean:write name="gpmc" property="zqdm" />';}">
							<img name="update_mode_img" src="<%=path%>/images/delete.gif" style="Width:18;height:18" border="0" alt="剔除"></span>
					</td>

				

			</logic:iterate>
		</TABLE>
		
		</form>
			
		</body>
		<script language="javascript">
		
	//	function myrefresh(){
   	//		window.location.reload();
	//	}
	//	setTimeout('myrefresh()',3000); //指定1秒刷新一次
		
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
			targetForm.action ='<%=path%>/view/tsxg/voladd.jsp'; 
			 targetForm.submit();
		}
		function inlsjg(){
			targetForm.action ='<%=path%>/lsjgdateShow.do'; 
			 targetForm.submit();
		}
		function lsjgpi(){
			targetForm.action ='<%=path%>/lsjgVolAutoAdds.do'; 
			 targetForm.submit();
		}
			
		function deleteMore(){
		
			if(confirm("是否确定删除选中的观察股？")){
				document.getElementById("myForm").action="volDeleteMore.do";	
				document.getElementById("myForm").submit();
			}
		}
		
	
   
   function volAutoFx(){
   		
   		
		
		
			document.getElementById("myForm").action="volAutoFx.do";	
			document.getElementById("myForm").submit();	
	
	}
	
	function showOnePic(gpdm){
			
			
				targetForm.action ="<%=path%>/showOnePic.do?gpdm="+gpdm; 
			
			 	targetForm.submit();
			
		
		}
	</script>
</html>
