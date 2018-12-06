
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
    
    <title>日折线图展示页面</title>
    
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
   
   <center>${MAXMINMessageStr}</center><br>
   
    <br>
<div id="container" style="min-width:500px;height:500px"></div>
        <script>
        
        Highcharts.setOptions({
        	global:{ useUTC:false}
        
        
        });
             
            chart = Highcharts.chart('container', {
		chart: {
			zoomType: 'x'
		},
		title: {
			text: '${queryDateSql}'
		},
		credits: {
		            enabled: false // 商标去掉
		    },
		subtitle: {
			text: document.ontouchstart === undefined ?
			'鼠标拖动可以进行缩放' : '手势操作进行缩放'
		},
		xAxis: {
			type: 'datetime',
			dateTimeLabelFormats: {
				millisecond: '%H:%M:%S.%L',
				second: '%H:%M:%S',
				minute: '%H:%M',
				hour: '%H:%M',
				day: '%m-%d',
				week: '%m-%d',
				month: '%Y-%m',
				year: '%Y'
			}
		},
		tooltip: {
			dateTimeLabelFormats: {
				millisecond: '%H:%M:%S.%L',
				second: '%H:%M:%S',
				minute: '%H:%M',
				hour: '%H:%M',
				day: '%Y-%m-%d',
				week: '%m-%d',
				month: '%Y-%m',
				year: '%Y'
			}
		},
		yAxis: {
			title: {
				text: '盈亏值'
			}
		},
		legend: {
			enabled: false
		},
		plotOptions: {
			area: {
				fillColor: {
					linearGradient: {
						x1: 0,
						y1: 0,
						x2: 0,
						y2: 1
					},
					stops: [
						[0, Highcharts.getOptions().colors[0]],
						[1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
					]
				},
				marker: {
					radius: 2
				},
				lineWidth: 1,
				states: {
					hover: {
						lineWidth: 1
					}
				},
				threshold: null
			}
		},
		series: [{
			type: 'area',
			name: '累计盈亏',
			data: ${dataStr}
		}]
	});

        </script>
  </body>
</html>
