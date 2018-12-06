
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.fuguo.dto.ListDTO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${ZQMC}(${ZQDM})交易分类及盈亏分析页面</title>
    
   <meta charset="utf-8"><link rel="icon" href="https://static.jianshukeji.com/highcharts/images/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            /* css 代码  */
        </style>
        <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
        <script src="https://img.hcharts.cn/highcharts/highcharts-3d.js"></script>
        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
        <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
  </head>
   

   <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    <SCRIPT language="javascript">	
		function showOnePic(gpdm){
			
			
				targetForm.action ="<%=path%>/showOnePic.do?gpdm="+gpdm; 
			
			 	targetForm.submit();
			
		
		}
		function hb(zqdm){
			
			if(confirm("您确定将以前的亏损都合并到当前该股票的盈亏中??\n 确认后，当前成本价将覆盖掉，并且将无法撤销！！")){
		
				targetForm.action ="<%=path%>/hb.do?zqdm="+zqdm; 
			
			 	targetForm.submit();
			}
		
		}
		
		function showMyJL(){
			
				
				
				targetForm.action ="<%=path%>/jiluSpecalQuery.do"; 
			    
			 	targetForm.submit();
			
		
		}
		
		function showMyZXT(){
			
				
				
				targetForm.action ="<%=path%>/ykfxZxtOneStock.do"; 
			    
			 	targetForm.submit();
			
		
		}
	</SCRIPT>
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
   
		 <%@ include file="/view/message.jsp"%>
   
  <FORM id="targetForm" name="targetForm" target="_blank" method="post">
  
  <input type="hidden" id="zqdm" name="zqdm" value=${ZQDM}>
  <input type="hidden" id="zqmc" name="zqmc" value=${ZQMC}>
  </FORM>
    <center><font color=red>${ZQMC}</font>&nbsp;&nbsp;交易分类及盈亏分析页面</center><br>
    <center><input type="button" value="查看历史交易记录" class="button" onclick="showMyJL();">
    
    &nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="该股盈亏折线图" class="button" onclick="showMyZXT();">
    &nbsp;&nbsp;&nbsp;&nbsp; 混合成本价：<bean:write name="NEWCBJ" format=".000" filter="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="合并历史盈亏" class="button" onclick="hb(${ZQDM});">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="K线及解禁相关" class="button" onclick="showOnePic(${ZQDM})"/> 
    </center>
		<br>
		 <center>	
			<logic:iterate id="jilu" name="JILU"  indexId="index" type="com.fuguo.dto.JiluDTO" scope="request">
				
				<bean:write name="jilu" property="jifl"  filter="false"/>&nbsp;交易盈亏：
				
				<logic:greaterEqual value="0"  name="jilu" property="qsje">
					<font color="red"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font>
				</logic:greaterEqual>
				<logic:lessThan value="0" name="jilu" property="qsje">
					<font color="green"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font>
				</logic:lessThan>
				
				&nbsp;
				
	</logic:iterate>
	</center>
	<TABLE Width="850" class="tableframe" align="center">
			<tr class="listtitle">
			
				<td Width="50" align="center" class="listcelltitle">
					ID
				</td>
				<td Width="100" align="center" class="listcelltitle">
					证券名称
				</td>
				<td Width="100" align="center" class="listcelltitle">
					证券代码
				</td>
				<td Width="100" align="center" class="listcelltitle">
					持有数量
				</td>
				
				<td Width="100" align="center" class="listcelltitle">
					交易分类
				</td>
				
			</tr>
			<logic:iterate id="order" name="LISTS"  indexId="index" type="com.fuguo.dto.ListDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					
					<td Width="50" align="center" class="listcellrow"  >
					
					${pageScope.index+1}
					</td>
					
					
				<td Width="100" align="center" class="listcelltitle" >
					<bean:write name="order" property="zqmc"  filter="false"/>
				</td>
				<td Width="100" align="center" class="listcellrow" >
					<bean:write name="order" property="zqdm"  filter="false"/>
					</td>
				<td Width="100" align="center" class="listcelltitle" >
					<bean:write name="order" property="cysl"  filter="false"/>
				</td>
				
				<td Width="100" align="center" class="listcelltitle" >
					<bean:write name="order" property="jifl"  filter="false"/>
				</td>
				
				
				</tr>
			</logic:iterate>
				
		</TABLE>
		
		<br>
		<div id="container" style="min-width:450px;height:450px"></div>

	<script>
        
      
        
            var chart = Highcharts.chart('container', {
		chart: {
				type: 'pie',
				options3d: {
						enabled: true,
						alpha: 45,
						beta: 0
				}
		},
		title: {
				text: '${ZQMC}交易类型占比'
		},
		credits: {
		            enabled: false // 商标去掉
		    },
		tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
				pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						depth: 35,
						dataLabels: {
								enabled: true,
								format: '{point.name}</b>: {point.percentage:.1f} %',
								style: {
									color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
								}
						},
						showInLegend: true
				}
		},
		series: [{
				type: 'pie',
				name: '占比',
				data: ${dataStr}
		}]
});
        </script>
  </body>
</html>