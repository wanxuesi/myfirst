
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
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
    
    <title><bean:write name="TITLE" scope="request"/>���׼�¼��ѯ���</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
   <SCRIPT language="javascript">
   
	function clearAll(checkBoxName) {
		   var el = document.getElementsByTagName('input');
		   var len = el.length;     
		   for(var j=0; j<len; j++){         
			   if((el[j].type=="checkbox") && (el[j].name==checkBoxName)){             
			   	   el[j].checked = false;        
			   }     
		   } 
	   } 
	
		
	
	function showMyJLKXT(){
						
				targetForm.action ="<%=path%>/view/highstock/indexjson.jsp?title=<bean:write name="TITLE" scope="request"/>"; 
			    
			 	targetForm.submit();		
	}
		
	function jiluupdatespc(id){
			
				targetForm.action ="<%=path%>/jiluUpdateSpc.do?id="+id; 
			
			 targetForm.submit();
		
		
		}

	function jiluupdatespcs(){
			
				updateMoreForm.action ="<%=path%>/jiluUpdateSpcs.do"; 
			
			 updateMoreForm.submit();
		
		
		}
		
	function configUpdateSpc(){
			
				targetForm2.action ="<%=path%>/configUpdateSpc.do"; 
			
			 targetForm2.submit();
		
		
		}	
		
	</SCRIPT>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
   
		 <%@ include file="/view/message.jsp"%>
   
    <form id="queryDateSqlForm" name="queryDateSqlForm"  action="" target="_blank" method="post">
  		<input type="hidden" id="queryDateSql" name="queryDateSql" value="<bean:write name="queryDateSql" scope="session"/>">
  							
  	</form>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		<input type="hidden" id="zqdm" name="zqdm" value="<bean:write name="ZQDM" scope="request"/>">
		
	</FORM>
	<FORM id="targetForm2" name="targetForm2" target="_self" method="post">
		<input type="hidden" id="zqdm" name="zqdm" value="<bean:write name="ZQDM" scope="request"/>">
				
	</FORM>
		
		<form method="post" id="updateMoreForm" action="" target="_blank">
		<CENTER>��ֻ��Ʊ���׼�¼��ѯ���</CENTER>
	<br>
	<center>
	<input type="button" value="���ɽ��׼�¼K��ͼ" class="button" onclick="showMyJLKXT();"><br>
	<font color="blue">˫�����׼�¼������ͨ����ɫ���T��Ʊ���Ӧ״̬����ɫ���Ѷ�Ӧ����ɫ����δ��Ӧ����</font><br><br>
	<font color="red"><bean:write name="MESSAGE" /></font>
	</center>
	<TABLE Width="700"  align="center">
  
    		<TR>
					<TD colspan="3" align="center">
					<input type="button" value="ȡ��ѡ��" class="button" onclick="clearAll('ids')"/>
					</TD>
					<TD colspan="3" align="center">
					<input type="button" value="ȷ������T" class="button" onclick="jiluupdatespcs()"/>
					</TD>
					<TD colspan="3" align="left">
					<input type="button" name="ˢ��" value="&nbsp;&nbsp;&nbsp;ˢ&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;" class="button" onclick="javascript:location.href='<%=path%>/jiluSpecalQuery.do?zqdm=<bean:write name="ZQDM" scope="request"/>';">
					</TD>
					
					<TD colspan="3" align="left">
					<input type="button" value="��Լ/��ϸ" class="button"  onclick="configUpdateSpc()"/>
					</TD>
					
					
			</TR>
	</TABLE>
    <TABLE Width="1000" class="tableframe" align="center">
			<tr class="listtitle">
				<TD  Width="20" align="center" class="listcelltitle">ѡ��</TD>
				
				<td Width="24" align="center" class="listcelltitle">
					��
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					֤ȯ����
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					����ʱ��
				</td>
				<td Width="60" align="center" class="listcelltitle">
					������־
				</td>
				<td Width="60" align="center" class="listcelltitle">
					�ɽ��۸�
				</td>
				<td Width="60" align="center" class="listcelltitle">
					�ɽ�����
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					�ɽ����
				</td>
				<td Width="60" align="center" class="listcelltitle">
					������
				</td>
				<td Width="*" align="center" class="listcelltitle">
					���׷���
				</td>
				<td Width="35" align="center" class="listcelltitle">
					T��
				</td>
				
				
			</tr>
			<logic:iterate id="jilu" name="JILU"  indexId="index" type="com.fuguo.dto.JiluDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''" ondblclick="jiluupdatespc(<bean:write name="jilu" property="id" />);">
					<TD Width="20" align="center"  class="listcellrow">
					<input type="checkbox" id="<bean:write name="jilu" property="flag1" />"  name="ids" value="<bean:write name="jilu" property="id" />"/>
					<input type="hidden"  name="allIds" value="<bean:write name="jilu" property="id" />"/>
					</TD>
					<td Width="24" align="center" class="listcellrow"  >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue">${pageScope.index+1}</font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2">${pageScope.index+1}</logic:equal>
					</td>
					
					
				<td Width="60" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="zqmc"  filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="zqmc"  filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcellrow" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="jysjStr"   filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="jysjStr"   filter="false"/></logic:equal>
					
					</td>
				<td Width="60" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="mmflag"  filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="mmflag"  filter="false"/></logic:equal>
					
				</td>
				
				<td Width="60" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="cjjg" format=".00"  filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="cjjg" format=".00"  filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="cjsl"  filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="cjsl"  filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="cjje" format=".00" filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="cjje"  format=".00" filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="qsje"  format=".00" filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="jifl"  filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="jifl"  filter="false"/></logic:equal>
					
				</td>
				<td Width="35" align="center" class="listcelltitle" >
					<logic:equal value="1" name="jilu" property="flag2"><font color="blue"><bean:write name="jilu" property="flag3"  filter="false"/></font></logic:equal>
					<logic:equal value="" name="jilu" property="flag2"><bean:write name="jilu" property="flag3"  filter="false"/></logic:equal>
					
				</td>
				
				
					
				</tr>

			</logic:iterate>
				
		</TABLE>
		</form>
  </body>
</html>
