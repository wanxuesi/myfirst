
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
    
    <title>特色选股结果显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
 <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>
    <CENTER><bean:write name="queryDate" scope="session"/></CENTER>
    <table Width="90%" align="center">
			<tr >
			<td align="center">	
			<input type="button" value="全选" class="button" onclick="checkAll('ids')"/>&nbsp;&nbsp;&nbsp;
			<input type="button" value="取消选中" class="button" onclick="clearAll('ids')"/>&nbsp;&nbsp;	
			<input type="button" value="加入VOL观察池" class="button" onclick="addVOLPi()"/> 
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/tsxgQuery.do';">
			</td>
			</tr>
		</table>
		<CENTER><font color='blue'>提示：点击记录即可显示日K线-VOL图、解禁详情</font></CENTER>
	<FORM id="targetForm" name="targetForm" target="_blank" method="post">
		
		
		
		
		<TABLE   Width="900"  class="tableframe" align="center">
			<tr class="listtitle">
				<td Width="30" align="center" class="listcelltitle">
					选择
				</td>
				<td Width="30" align="center" class="listcelltitle">
					序
				</td>
				<td Width="40" align="center" class="listcelltitle">
					证券代码
				</td>
				
				
				<td Width="70" align="center" class="listcelltitle">
					证券名称
				</td>
				<td Width="60" align="center" class="listcelltitle">
					市场价
				</td>
				<td Width="120" align="center" class="listcelltitle">
					流通股本/总股本
				</td>
				<td Width="120" align="center" class="listcelltitle">
					流通市值/总市值
				</td>
				<td Width="60" align="center" class="listcelltitle">
					Date流通股本
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					Date流通市值
				</td>
				<td Width="60" align="center" class="listcelltitle">
					预期解禁市值
				</td>
				
				
				
				
				
				
				
				
			</tr>
			<logic:iterate id="gpmc" name="LIST" indexId="index"  type="com.fuguo.dto.GpmcDTO" scope="request">
				<tr onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="30" align="center" class="listcellrow">
						<input type="checkbox" id="ids"  name="ids" value="<bean:write name="gpmc" property="zqdm" />"/>
					
					</td>
					<td Width="30" align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
						${pageScope.index+1}
					</td>
					<td  Width="40"  align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
						<bean:write name="gpmc" property="zqdm" />
					</td>
					<td  Width="70"  align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
					<logic:equal value="1" name="gpmc" property="newFlagInt"><font color="blue">[新]</font></logic:equal>
					
					<logic:equal value="-1" name="gpmc" property="newFlagInt">
					<img src="<%=path%>/images/hfold_close.gif"  alt="喜欢" /></logic:equal><bean:write name="gpmc" property="zqmc" />
					</td>
					<td  Width="60"  align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
						<bean:write name="gpmc" property="scj"/>
					</td>
					<td  Width="120"  align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
						<bean:write name="gpmc" property="dqyltnumStr"/>/&nbsp;<bean:write name="gpmc" property="zgbStr"/>
					</td>
					
					<td  Width="120"  align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
						<FONT color='red'><bean:write name="gpmc" property="dqyltszStr"/></FONT>/&nbsp;<bean:write name="gpmc" property="zszStr"/>
					</td>
					<td  Width="60"  align="center" class="listcellrow" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
						<bean:write name="gpmc" property="dateLtgfNumberStr"/>
					</td>

				
				<td Width="60" align="center" class="listcelltitle" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
					<FONT color='red'><bean:write name="gpmc" property="dateLtszStr"/></FONT>
				</td>
				<td Width="60" align="center" class="listcelltitle" onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');">
					<bean:write name="gpmc" property="dateYqjjszStr"/>
				</td>
				

				

				

			</logic:iterate>
		</TABLE>
		</FORM>
			
		</body>
		<script language="javascript">
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
	   
		function addVOLPi(){
		
			
				document.getElementById("targetForm").action="volAdds.do";	
				document.getElementById("targetForm").submit();
			
		}
		
		function showOnePic(gpdm){
			
			
				targetForm.action ="<%=path%>/showOnePic.do?gpdm="+gpdm; 
			
			 	targetForm.submit();
			
		
		}
	</script>
</html>
