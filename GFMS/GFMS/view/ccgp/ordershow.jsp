
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.fuguo.dto.OrderDTO"%>
<%@ page import="com.fuguo.dto.ListDTO"%>
<%@ page import="com.fuguo.util.DateUtil"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	
	//获取周数
	//int weekNumber = DateUtil.getweekNum(new java.util.Date());
	//获取随机数
	int intNumber  =(int)(Math.random()*1000);
	
	//System.out.println(intNumber);
	
	//设置三种颜色数组红，蓝，...；
	String[] colors= {"red","blue","yellow","purple","brown"};	
	
	//根据周的求余获得颜色；
	String stateColor = colors[intNumber%colors.length];	
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>持仓股票显示页面</title>
   
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
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
      function alertBJMessage(){
            var baojingmsg  =document.getElementById("baojingmsg").value;
            //alert(baojingmsg);
      		if(baojingmsg.length>7){
      		alert(baojingmsg);
      		}
      
      
      }	
		function showOne(zqdm){
			
				targetForm.action ="<%=path%>/listShowOne.do?zqdm="+zqdm; 
			
			 targetForm.submit();
		
		
		}
		function js(id){
			
				targetForm.action ="<%=path%>/js.do?id="+id; 
			
			 targetForm.submit();
		
		
		}
		
		function cq(id){
			
			 targetForm.action ="<%=path%>/cqPre.do?id="+id; 
			
			 targetForm.submit();
		
		
		}
		
		function addNew(){
			targetForm.action ="<%=path%>/view/jilu/jiluadd.jsp"; 
			 targetForm.submit();
		}
	</SCRIPT>
  <body onLoad="alertBJMessage();getOneGpyl();">
      
		 <%@ include file="/view/message.jsp"%>
    <center style="color:<%=stateColor%>;font-family:黑体;FONT-SIZE: 25pt;">严格按照系统操作，不要乱抄底！即便被套了，也要按照系统的要求加减仓!<br>
只有这样，即刻顿悟，严格执行，才能告别低级，取得进步！<br>千万不要再错上加错！</center>
    <center>
    
    <input type="hidden" id="baojingmsg" name="baojingmsg" value='${BAOJINGMESSAGE}'>
    
    可用资金:${KYZJ}&nbsp;&nbsp;&nbsp;当前股票市值:${GPSZ}(成本${GPZCB},盈亏${GPZYK},盈亏比例${CCYKB})&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;
    <br>
    总资产:${ZZC}&nbsp;&nbsp;&nbsp;&nbsp;当前总仓位:${DQZCW}
    </center><br>
    <CENTER><input type="button" name="新增交易记录" value="新增交易记录" class="button" onclick='javascript:addNew(); ' ></CENTER>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
     <TABLE Width="1040" class="tableframe" align="center">
			<tr class="listtitle">
			
				<td Width="50" align="center" class="listcelltitle">
					ID
				</td>
				<td Width="80" align="center" class="listcelltitle">
					证券名称
				</td>
				<td Width="80" align="center" class="listcelltitle">
					证券代码
				</td>
				<td Width="80" align="center" class="listcelltitle">
					持有股数
				</td>
				
				<td Width="100" align="center" class="listcelltitle">
					成本价
				</td>
				<td Width="100" align="center" class="listcelltitle">
					当前价
				</td>
				<td Width="100" align="center" class="listcelltitle">
					当前市值
				</td>
				<td Width="100" align="center" class="listcelltitle">
					盈亏
				</td>
				<td Width="100" align="center" class="listcelltitle">
					盈亏比例
				</td>
				<td Width="120" align="center" class="listcelltitle">
					交易分类及盈亏
				</td>
				<td Width="130" align="center" class="listcelltitle">
					相关功能
				</td>
			</tr>
			<logic:iterate id="order" name="ORDERS"  indexId="index" type="com.fuguo.dto.OrderDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					
					<td Width="50" align="center" class="listcellrow"  onclick="showOne(<bean:write name="order" property="zqdm" />);">
					
					${pageScope.index+1}
					</td>
					
					
				<td Width="80" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<bean:write name="order" property="zqmc"  filter="false"/>
				</td>
				<td Width="80" align="center" class="listcellrow" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<bean:write name="order" property="zqdm"  filter="false"/>
					</td>
				<td Width="80" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<bean:write name="order" property="cysl"  filter="false"/>
				</td>
				
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<bean:write name="order" property="cbj" format="0.000"  filter="false"/>
				</td>
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<logic:equal value="YES"  name="order" property="flag1"><bean:write name="order" property="scj" format="0.000" filter="false"/></logic:equal>
					
					<logic:equal value="NO"  name="order" property="flag1"><bean:write name="order" property="scj" format="0.00" filter="false"/></logic:equal>
					
			
				</td>
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<bean:write name="order" property="dqsz" format="0.00" filter="false"/>
				</td>
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<bean:write name="order" property="yk" format="0.00" filter="false"/>
				</td>
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					<bean:write name="order" property="ykbl"  filter="false"/>
				</td>		
				
				<td Width="120" align="center" class="listcelltitle" onclick="showOne(<bean:write name="order" property="zqdm" />);">
					&nbsp;请点击该记录&nbsp;
				</td>
				<td Width="130" align="center" class="listcelltitle">
					<input type="button" value="计算器" class="button" onclick="js(<bean:write name="order" property="id" />);">
					&nbsp;
					<input type="button" value="除权" class="button" onclick="cq(<bean:write name="order" property="id" />);">
				</td>
				</tr>
				
				
				<tr>
					
					<td colspan="11" align="left" class="listcellrow">
					&nbsp;<bean:write name="order" property="listStr"  filter="false"/>
					
					</td>				
				
				
				</tr>
			</logic:iterate>
				
		</TABLE>
		<br>
		 <div id="container" style="min-width:450px;height:450px"></div>
        <script>
        
        function alertBJMessage(){
            var baojingmsg  =document.getElementById("baojingmsg").value;
            //alert(baojingmsg);
      		if(baojingmsg.length>7){
      		alert(baojingmsg);
      		}
      
      
      }
        
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
				text: '持仓股票占比'
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
				name: '资金占比',
				data: ${dataStr}
		}]
});
        </script>
  </body>
</html>
