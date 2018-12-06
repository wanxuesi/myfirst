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
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	
	<%
   		String _zqdm = request.getParameter("zqdm");   		
   		//编码转换工作。
   		byte[] tempByzqdm = _zqdm.getBytes("ISO-8859-1");  		
   		String zqdm="";
   		zqdm = new String(tempByzqdm,"gbk");  
   		
   		
   		String _title = request.getParameter("title");   		
   		//编码转换工作。
   		byte[] tempBytitle = _title.getBytes("ISO-8859-1");  		
   		String title="";
   		title = new String(tempBytitle,"gbk");  
   %>
		<title><%=title%>K线图</title>

		


		<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
		<style type="text/css">
${demo.css}
		</style>
		<script type="text/javascript">
	
	
		
		
	var price = [];	// 价格
	
	var ma5 = [];
	var sum5 = 0;
	
	var ma10 = [];
	var sum10 = 0;
	
	var ma20 = [];
	var sum20 = 0;
	
	var ma60 = [];
	var sum60 = 0;
	
	var ma120 = [];
	var sum120 = 0;
	
	var inflection_triangle_down = [];
	var flags_line = [];//BS隐藏线
		
$(function () {

	$.getJSON('<%=path%>/jiluShowJson.do?zqdm=<%=zqdm%>', function (dataJson) {
	  		   	//alert(dataJson.length);
	  		   	var preTimeLong=0;
	  		   	
	        	//json变量现在就是一个数组对象，直接读取每个数组对象。结合属性进行输出   
		        for(var i=0; i < dataJson.length; i++){		
						timeLong = dataJson[i][0];	
						inflection_triangle_down.push({
							x : dataJson[i][0],
							title : dataJson[i][1],
							text : '成交价:' + dataJson[i][3]+';交易分类:' + dataJson[i][2]
						});
						if(timeLong!=preTimeLong){
						
							flags_line.push([dataJson[i][0],dataJson[i][3]]);
							
							preTimeLong = timeLong;
						}
			            				
		    	}  
    });



    $.getJSON('<%=path%>/lsjgShowJson.do?zqdm=<%=zqdm%>', function (data) {
    		//json变量现在就是一个数组对象，直接读取每个数组对象。结合属性进行输出   
				//alert(data.length);
		        for(var i=0; i < data.length; i++){	        	
		        	

		            price.push([
						data[i][0], // the date
						data[i][1], // open
						data[i][2], // high
						data[i][3], // low
						data[i][4] // close
					]);


		         // 短期均线用ema更好，更加灵敏，且不容易金叉死叉 // Y=［2*X+（N-1）*Y’］/（N+1）
					sum5 += data[i][4];
		            if(i >= 5){
		                sum5 -=  data[i-5][4];
		           		ma5.push([data[i][0], sum5 /5]);
		            }
					
					sum10 += data[i][4];
		            if(i >= 10){
		                sum10 -=  data[i-10][4];
		           		ma10.push([data[i][0], sum10 /10]);
		            }
					
					
					
					sum20 += data[i][4];
		            if(i >= 20){
		                sum20 -=  data[i-20][4];
		           		ma20.push([data[i][0], sum20 /20]);
		            }
					
					sum60 += data[i][4];
		            if(i >= 60){
		                sum60 -=  data[i-60][4];
		           		ma60.push([data[i][0], sum60 /60]);
		            }
					
					sum120 += data[i][4];
		            if(i >= 120){
		                sum120 -=  data[i-120][4];
		           		ma120.push([data[i][0], sum120 /120]);
		            }
					
					
					

		        }// end for
    
    	// create the chart
			Highcharts.setOptions({
				
				global: {
					useUTC: false//中文区时间格式
				}
				// 中文时间显示字符串替换
				,								
				lang: {
					months: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
					shortMonths : ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
					weekdays: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
					thousandsSep:''// 去掉千位的逗号
				}
			});
    		

        // create the chart
        $('#container').highcharts('StockChart', {

			
            rangeSelector: {
		        	allButtonsEnabled: true,
			        buttons: [{
			        	type: 'week',
			        	count: 4,
			        	text: '4周'
			        }, {
			        	type: 'week',
			        	count: 8,
			        	text: '8周'
			        }, {
			        	type: 'month',
			        	count: 3,
			        	text: '3月'
			        }, {
			        	type: 'month',
			        	count: 6,
			        	text: '6月'
			        }, {
			        	type: 'month',
			        	count: 9,
			        	text: '9月'
			        }, {
			        	type: 'year',
			        	count: 1,
			        	text: '1年'
			        }, {
			        	type: 'year',
			        	count: 2,
			        	text: '2年'
			        }, {
			        	type: 'all',
			        	text: 'All'
			        }],
			        selected: 1
			},


            title : {
                text : '<%=title%>交易记录K线图'
            },
            credits: {
		            enabled: false // 商标去掉
		    },
            
            xAxis:{
			    	tickColor: 'black',
		            tickWidth: 2,
		            tickPosition: 'outside',  	
			    	startOfWeek:1,//周一开始
			    	dateTimeLabelFormats:{
	    				second: '%Y/%m/%d %H:%M:%S',
	    				minute: '%Y/%m/%d %H:%M',
	    				hour: '%Y/%m/%d %H:%M',
	    				day: '%Y/%m/%d',
	    				week: '%Y/%m/%e',
	    				month: '%Y/%m',
	    				year: '%Y' 
				    },
				    // 只显示周的x坐标，好把各周区分出来
		            gridLineWidth: 1,//显示竖线
		            gridLineColor:'#E8E8E8',
	            	labels: {
				    	rotation:0//禁止旋转
	                },
		            type: 'datetime',
                    minTickInterval: 14 * 24 * 3600000,//time in milliseconds  7day
                    minRange:  14 * 24 * 3600000 // 14 days                   
			},
            yAxis: {
                title: {
                    text: '价格'
                }
            },
            
            
            
            
            
            
			dateTimeLabelFormats: {
        	millisecond: '%H:%M:%S.%L',
        	second: '%H:%M:%S',
        	minute: '%H:%M',
        	hour: '%H:%M',
        	day: '%e. %b',
        	week: '%e. %b',
       		month: '%b \'%y',
        	year: '%Y'
			},
			
			
            series : [{
                type : 'candlestick',
                name : 'K线价格',
                data : price,
                color: 'green',
                lineColor: 'green',
                upColor: 'red',
                upLineColor: 'red',
                navigatorOptions: {
                    color: Highcharts.getOptions().colors[0]
                },
                
                
            }  ,{
		        type: 'spline',//线
		        name: 'ma5',
		        data: ma5,
		        color:'black',
		        lineWidth:1,
		        zIndex: 20,
		        tooltip: {
		        	valueDecimals: 2//2位小数
		        }
		   },{
		        type: 'spline',//线
		        name: 'ma10',
		        data: ma10,
		        color:'#000000',
		        dashStyle : 'shortdash',
		        lineWidth:2,
		        zIndex: 20,
		        tooltip: {
		        	valueDecimals: 2//2位小数
		        }
		   },{
			        type: 'spline',//线
			        name: 'ma20',
			        data: ma20,
			        color:'red',
			        lineWidth:1,
			        zIndex: 20,
			        tooltip: {
			        	valueDecimals: 2//2位小数
			        }
			},{
			        type: 'spline',//线
			        name: 'ma60',
			        data: ma60,
			        color:'green',
			        lineWidth:1,
			        zIndex: 20,
			        tooltip: {
			        	valueDecimals: 2//2位小数
			        }
			},{
		        type: 'spline',//线
		        name: 'ma120',
		        data: ma120,
		        //color:'#4572A7',
		        color:'MediumBlue',
		        dashStyle : 'Dash',
		        lineWidth:2,
		        zIndex: 20,
		        tooltip: {
		        	valueDecimals: 2//2位小数
		        }
			},{
				    type: 'line',//线
				    name: '拐点',
				    data: flags_line,
				    lineWidth:0,
				    zIndex: 0,
				    tooltip: {
				  		enabled:false,
				    	valueDecimals: 2
				    },
					id:'flags_line'
			},{
			        type: 'flags',//BS
			        name: 'BS',
			        data: inflection_triangle_down,
			        lineWidth: 0,
			        color:'blue',
			        onSeries : 'flags_line',
			        //onSeries: 'dataseries',
			        //shape : 'triangle-down',
			        shape : 'squarepin',//squarepin
					width : 20,
					height: 11,
					fillColor:'#90EE90',//transparent
		  			verticalAlign:'middle',
				  	style: {
				   		color: 'black',
				  		fontSize: '10px',
				  		textAlign: 'center'
				  	},
					zIndex: 0,
					y:-35
			  	}
			
			]
            
            
        });
    });
});

		</script>
		
	</head>

	<body>
		<form name="mainForm"></form>
		<script src="../../js/highstock.js"></script>
		<script src="../../js/exporting.js"></script>

<div id="container" style="height: 750px; min-width: 300px"></div>
		
	</body>
</html>

