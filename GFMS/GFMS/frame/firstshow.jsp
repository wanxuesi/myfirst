
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

  
    
    <%@ include file="/view/firstshowsub.jsp"%>
   <script language="javascript">
  
   		dwr.engine.setAsync(false);
   
   
   		//alert("开始更新历史数据。。。");
  		
    	lsjgbo.autoUpdateLsjgsFromSina(callbacklsjgs);
  		
   		function callbacklsjgs(data){     
         	//为1表示更新成功！
         	if(data==1){
         		alert("更新成功！");
         	}
         	if(data==0){
         	alert("更新失败！网路连接异常！");
         	}
         	
   		}  
   </script>  
