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
   		
   		
   		String _flag = request.getParameter("flag");   		
   		//编码转换工作。
   		byte[] tempByflag = _flag.getBytes("ISO-8859-1");  		
   		String flag="";
   		flag = new String(tempByflag,"gbk");  
   %>
		<title><%=zqdm%>动态数据实时分析</title>

		


		<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
		<style type="text/css">
${demo.css}
		</style>
		<script type="text/javascript">
	
	
		
		
	
	
	
		
$(function () {
	
	var price = [];	// 价格
	var EMa3 = [];
	var preEMa3 = 0;
	
	var EMa5 = [];
	var preEMa5 = 0;
	
	var EMa10 = [];
	var preEMa10 = 0;
	
	var ma20 = [];
	var sum20 = 0;
	
	var ma60 = [];
	var sum60 = 0;
	
	var flag=<%=flag%>;
	
    var remindTime ="";

	var timestamp;
	var dataUrl = '<%=path%>/ssfenxiJson.do?zqdm=<%=zqdm%>&flag1=<%=flag%>';
	
	//alert(dataUrl);
	// 装填数据
			$.ajax({
				async : false,
				type : "GET",
				url : dataUrl,
				complete : function(msg) {
					//alert('complete');
				},
				success : function(resp) {
					//data返回的是json字符串。需要转换成json对象   
					var data = eval(resp);
					//alert(data.length);
					
					//json变量现在就是一个数组对象，直接读取每个数组对象。结合属性进行输出   
					for (var i = 0; i < data.length; i++) {
						remindTime =data[i].day; 
						if(i<3)
						alert(new Date(remindTime).getTime());
						data[i].day = new Date(remindTime).getTime();
						if(i<3){
							alert("日期："+data[i].day+"开盘价"+data[i].open+"最高价"+data[i].high+"最低价"+data[i].low+"收盘价"+data[i].close);
						}
			            price.push([
							data[i].day, // the date
							data[i].open, // open
							data[i].high, // high
							data[i].low, // low
							data[i].close // close
						]);

						if(i<3){
							alert("price.push进去的数据中的day值为："+price[i][0]);
						}

			         // 短期均线用ema更好，更加灵敏，且不容易金叉死叉 // Y=［2*X+（N-1）*Y’］/（N+1）
						if(i == 0){
			            	preEMa3 =  data[i].close;
			            	EMa3.push([data[i].day, preEMa3]);
			            } else {
			            	preEMa3 = (2*data[i].close + (3 - 1)* preEMa3) / (3 + 1);		                
			            	EMa3.push([data[i].day, preEMa3]);
			            }
	
						if(i == 0){
			            	preEMa5 =  data[i].close;
			            	EMa5.push([data[i].day, preEMa5]);
			            } else {
			            	preEMa5 = (2*data[i].close + (5 - 1)* preEMa5) / (5 + 1);		                
			            	EMa5.push([data[i].day, preEMa5]);
			            }
			            
						if(i == 0){
			            	preEMa10 =  data[i].close;
			            	EMa10.push([data[i].day, preEMa10]);
			            } else {
			            	preEMa10 = (2*data[i].close + (10 - 1)* preEMa10) / (10 + 1);		                
			            	EMa10.push([data[i].day, preEMa10]);
			            }
						
						sum20 += data[i].close;
			            if(i >= 20){
			                sum20 -=  data[i-20].close;
			           		ma20.push([data[i].day, sum20 /20]);
			            }
						
						sum60 += data[i].close;
			            if(i >= 60){
			                sum60 -=  data[i-60].close;
			           		ma60.push([data[i].day, sum60 /60]);
			            }
					
					
					 }// end for
					 //alert(price.length);
					
					 //alert(ma60.length);
				//这边插入highstock代码，合适；
				
		
				
				
		  // create the chart
        $('#container').highcharts('StockChart', {


            rangeSelector : {
                selected : 1
            },

            title : {
                text : '交易记录K线图'
            },

            series : [{
                type : 'candlestick',
                name : 'K线价格',
                data : data,
                color: 'green',
                lineColor: 'green',
                upColor: 'red',
                upLineColor: 'red',
                navigatorOptions: {
                    color: Highcharts.getOptions().colors[0]
                }
                
            }]
        });
				
				
				
				
				
		}// end  success : function(resp) 
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

