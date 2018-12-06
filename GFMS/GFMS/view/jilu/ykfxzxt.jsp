
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
    
    <title>折线图展示页面</title>
    
    
   <meta charset="utf-8"><link rel="icon" href="https://static.jianshukeji.com/highcharts/images/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
         <script src="https://img.hcharts.cn/highcharts/highcharts-3d.js"></script>
        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
        <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/oldie.js"></script>
  </head>
  		
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
   
		 <%@ include file="/view/message.jsp"%>
   <br>
   <center>${MAXMINMessageStr}</center><br>
   
   
    <br>
 <div id="container" style="min-width:500px;height:500px">  
   
 <script>
 Highcharts.chart('container', {
	chart: {
		type: 'line'
	},
	title: {
			text: '${queryDateSql}'
		},
		credits: {
		            enabled: false // 商标去掉
		    },
	subtitle: {
		text: '折线点为每月收盘价'
	},
	xAxis: {
		categories: ${dateStr}
	},
	yAxis: {
		title: {
			text: '盈亏（元）'
		}
	},
	plotOptions: {
		line: {
			dataLabels: {
				// 开启数据标签
				enabled: true          
			},
			// 关闭鼠标跟踪，对应的提示框、点击事件会失效
			enableMouseTracking: true
		}
	},
	series: [{
		name: '累计盈亏值',
		data: ${valueStr}
	}]
});
 
 </script>
     
  </body>
</html>
