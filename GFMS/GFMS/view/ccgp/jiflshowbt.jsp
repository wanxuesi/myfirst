
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
    <body  onLoad="alertBJMessage();getOneGpyl();">
    <%@ include file="/view/message.jsp"%>
    <input type="hidden" id="baojingmsg" name="baojingmsg" value='${BAOJINGMESSAGE}'>  
        <div id="container" style="min-width:500px;height:500px"></div>
        <script>
        
        function alertBJMessage(){
            var baojingmsg  =document.getElementById("baojingmsg").value;
            //alert(baojingmsg);
      		if(baojingmsg.length>7){
      		alert(baojingmsg);
      		}
      
      
      }
        
            Highcharts.chart('container', {
		chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false,
				type: 'pie',
				options3d: {
						enabled: true,
						alpha: 45,
						beta: 0
				}
		},
		title: {
				text: '持仓股票数据分析[交易分类]'
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
							format: '<b>{point.name}</b>: {point.percentage:.1f} %',
							style: {
								color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							}
						},
						showInLegend: true
				}
		},
		series: [{
				name: 'Brands',
				colorByPoint: true,
				data: ${dataStr}
		}]
}); 
        </script>
    </body> 
    
</html>
