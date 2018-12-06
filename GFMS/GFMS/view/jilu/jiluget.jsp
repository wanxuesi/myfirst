
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
    
    <title>交易记录明细</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
    <script language="javascript" type="text/javascript" src="<%=path%>/js/datepicker/WdatePicker.js" charset="gb2312"></script>
<link href="<%=path%>/js/datepicker/default/datepicker.css" rel="stylesheet" type="text/css" charset="gb2312">
    
		
	
    <script language="javascript">   

	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();'>
  
   <form name="targetForm" action="" method="post">
  		<input type="hidden" id="id" name="id" value="<bean:write name="JILU" property="id" scope="request"/>">
  		  		<input type="hidden" id="parentid" name="parentid" value="<bean:write name="JILU" property="timeid" scope="request"/>">
  	</form>
  	
	<form name="targetForm2" action="" target="_blank" method="post">
	<input type="hidden" id="id" name="id" value="<bean:write name="JILU" property="id" scope="request"/>">
		
  		<input type="hidden" id="parentid" name="timeid" value="<bean:write name="JILU" property="timeid" scope="request"/>">
  	</form>	
  	
  	
   <center>  <br>
   
		<INPUT type="button" value="修改" class="button" onclick="mypreupdate();"  <logic:equal value="已处理" name="JILU" property="flag1"> disabled="disabled" </logic:equal> >
		&nbsp;&nbsp;&nbsp;&nbsp;
		
				<INPUT type="button" value="删除" class="button" onclick="mydelete();" <logic:equal value="已处理" name="JILU" property="flag1"> disabled="disabled" </logic:equal>>
		
		
		
		&nbsp;&nbsp;&nbsp;&nbsp;
		
		<INPUT type="button" value="逻辑删除" class="button" onclick="mydeleteLogic();" <logic:equal value="未处理" name="JILU" property="flag1"> disabled="disabled" </logic:equal>>
		
	</center>
   <center> <br>
    <form id="add" name="add" action="" method="post">
         <table  Width="750" class="tableframe" align="center">
        
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        交易记录明细
    		        </td>
    		</tr>
           
    		
    	   
    		<tr>

    	      <td   class="listcellrow">  
    		     交易时间    		     
    		   </td>
    		   <td  class="listcellrow">
    		   <bean:write name="JILU" property="jysj" scope="request"  format="yyyy-MM-dd HH:mm"/>
    		   </td>
    		</tr>
    		
    		<tr>
    	      <td   class="listcellrow">  
    		     证券名称
    		   </td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="JILU" property="zqmc" scope="request" />

    		      
    		   </td>
    		</tr>
    		<tr>
    	      <td   class="listcellrow">  
    		     证券代码
    		   </td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="JILU" property="zqdm" scope="request"/>
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    		
    		
    		 <tr>

    	      <td   class="listcellrow">  
    		     交易分类
    		   </td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="JILU" property="jifl" scope="request"/>
		       
    		   </td>
    		</tr>
    		<tr>
              <td  Width="100" class="listcellrow">
    		     买卖标志
				</td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="JILU" property="mmflag" scope="request"/>

    		   </td>
    		   
    		</tr>
    		
    		<tr>
              <td  Width="100" class="listcellrow"> 
    		  成交价格
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="JILU" property="cjjg" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    	   
    	   <tr>
              <td  Width="100" class="listcellrow"> 
    		  成交数量
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="JILU" property="cjsl" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    	 <tr>
              <td  Width="100" class="listcellrow"> 
    		  成交金额
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="JILU" property="cjje" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    	   
    	  <tr>
              <td  Width="100" class="listcellrow"> 
    		  交易所名称
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="JILU" property="jysmc" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow"> 
    		  资金账号
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="JILU" property="zjzh" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow"> 
    		  客户代码
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="JILU" property="khdm" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow"> 
    		  股东名称
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="JILU" property="gdmc" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    		
    		 <tr>
    	      <td   class="listcellrow">  
    		     备注 
    		   </td>
    		   <td  class="listcellrow">   		  
    		       		   
    		   <TEXTAREA id="bz" name="bz" cols="100" rows=3 style="BACKGROUND-COLOR: #ffffdd;border: solid 1px #888888;"><bean:write name="JILU" property="bz" scope="request"/></TEXTAREA>
    		   </td>
    		</tr>
    		
    		   		 <tr>

    	      <td   class="listcellrow">  
    		     交易状态
    		   </td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="JILU" property="flag1" scope="request"/>
		       
    		   </td>
    		</tr>
    		
    	
    	
    		
    	  
    		  </table>
    	</form>
    	</center>
  </body>
  <script language="javascript">
  
 
   function mydelete(){
	if(confirm('您确定删除？'))
	{
	
	targetForm.action ="<%=path%>/jiluDelete.do";
	targetForm.submit();
	
	}
  }
  
   function mydeleteLogic(){
	if(confirm('您确定做逻辑上的删除？'))
	{
	
	targetForm.action ="<%=path%>/jiluLogicDelete.do";
	targetForm.submit();
	
	}
  }
 
  function mypreupdate(){
	targetForm.action ="<%=path%>/jiluPreUpdate.do";
	targetForm.submit();
	}
 
  
  


	
</script>
</html>