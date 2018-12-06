
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>添加系统改进意见功能</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
       <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">


	
 	<script type="text/javascript" src="dwr/interface/sblxmcbo.js"></script> 
 	
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
   	
	
   
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();' >
   <center> <br>
    <form id="add" name="add" action="<%=path%>/sunAdd.do" method="post">
         <table  Width="750" class="tableframe" align="center">
         <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    String dwname = baseUserContext.getDwname();
  			    String banzu = baseUserContext.getBanzu();
    		%>
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        添加系统改进意见功能
    		        </td>
    		</tr>
           
    		
    	  
    		<tr>

    	      <td   class="listcellrow">  
    		     问题及改进意见
    		   </td>
    		   <td  class="listcellrow">
    		   &nbsp;&nbsp;<TEXTAREA id="clgc" name="clgc" cols="100" rows=8 style="BACKGROUND-COLOR: #ffffdd;border: solid 1px #888888;"></TEXTAREA>  
    		   </td>
    		</tr>
    		
    		<tr>
    	      <td   class="listcellrow">  
    		     发生时间
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="text" id="fssj" name="fssj" size="16" class="text"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    		<tr>
    	      <td   class="listcellrow">  
    		     处理时间
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="text" id="clsj" name="clsj" size="16" class="text"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"><br>
    		      
    		   </td>
    		</tr>
    		
    		
    		<tr>
              <td  Width="100" class="listcellrow">
    		     发现人
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="fxr" name="fxr" value="" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;
    		   
    		   
    		   
    		    
    		    </td>
    	   </tr>
    	   
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     验收人
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="ysr" name="ysr" value="" class="text">
    		    
    		    </td>
    	   </tr>
    	   
    	
    	   
    	  
    	   
    	   
    	   
    		
    		 <tr>
    	      <td   class="listcellrow">  
    		     备注 
    		   </td>
    		   <td  class="listcellrow">
    		   &nbsp;&nbsp;<TEXTAREA id="bz" name="bz" cols="100" rows=3 style="BACKGROUND-COLOR: #ffffdd;border: solid 1px #888888;"></TEXTAREA>
    		   </td>
    		</tr>
    		 <tr>

    	      <td   class="listcellrow">  
    		     填报人
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="text" id="tbr" name="tbr" value="<%=xm%>" class="text" readonly>&nbsp;&nbsp;&nbsp;&nbsp;
    		    <input type="hidden" id="jhzt" name="jhzt" value="未处理"> 
    		   
    		   </td>
    		</tr>
    		
    		
    		
    	
    		
    	  
    		 <tr class="listtitle">
    		        <td colspan="2">
    		              <input type="button" value="保存" class="button" onclick="chickme();">&nbsp;&nbsp;<input type="reset" value="重置" class="button">
    		        </td>
    		    </tr>
    		  </table>
    	</form>
    	</center>
  </body>
  <script language="javascript">
  
   String.prototype.toDate = function()
  {
        try
        {
                return new Date(this.replace(/-/g, "\/"));
        }
        catch(e)
        {
                return null;
        }
  }
  
  function getLastDay(oldda){
    var newda=oldda;
  	newda.setMonth(newda.getMonth()+1);   
  	newda.setDate(0);
  	newda.setHours(00);//可以不要，根据情况
  	newda.setMinutes(00);//可以不要，根据情况
  	return newda;
  }
  Date.prototype.format   =   function(format)   
  {   
      var   o   =   {   
          "M+"   :   this.getMonth()+1,   //month   
          "d+"   :   this.getDate(),         //day   
          "h+"   :   this.getHours(),       //hour   
          "m+"   :   this.getMinutes(),   //minute   
          "s+"   :   this.getSeconds(),   //second   
          "q+"   :   Math.floor((this.getMonth()+3)/3),     //quarter   
          "S"   :   this.getMilliseconds()   //millisecond   
      }   
      if(/(y+)/.test(format))   format=format.replace(RegExp.$1,   
          (this.getFullYear()+"").substr(4   -   RegExp.$1.length));   
      for(var   k   in   o)if(new   RegExp("("+   k   +")").test(format))   
          format   =   format.replace(RegExp.$1,   
              RegExp.$1.length==1   ?   o[k]   :     
                  ("00"+   o[k]).substr((""+   o[k]).length));   
      return   format;   
  }
   function DateNumber(){
  		var fssj=document.getElementById("add").fssj.value;
		var clsj=document.getElementById("add").clsj.value;
		
		
		if(fssj==""||clsj==""){
			alert("请填写发生时间和结束时间！\n工作时间未定时为月初和月末！");
		}else
		if(fssj>clsj){
			alert("发生时间晚于结束时间，两者可能颠倒！");
		}else{
			var ts = DateDiff(fssj,clsj)+1;
			document.getElementById("add").ts.value=ts;
		}
  		
  
  
  
  }
  
  function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
       var  aDate,  oDate1,  oDate2,  iDays  
      
      //split 可以算出绝对时间 精确到分。
       //aDate  =  sDate1.split("-")  
       //oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
       //aDate  =  sDate2.split("-")  
      //oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
       
     //根据天数就可以了。将String 转换成date 不带时间 然后进行比较。
       oDate1=sDate1.toDate();
       oDate1.setHours(00);//可以不要，根据情况
  	   oDate1.setMinutes(00);//可以不要，根据情况
  	   
  	   oDate2=sDate2.toDate();
       oDate2.setHours(00);//可以不要，根据情况
  	   oDate2.setMinutes(00);//可以不要，根据情况
       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
       return  iDays  
   }  
   
    function  TimeValues(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
       var  aDate,  oDate1,  oDate2, ohours1,ohours2, ohours  
      
  
     //根据天数就可以了。将String 转换成date 不带时间 然后进行比较。
       oDate1=sDate1.toDate();
       ohours1=oDate1.getHours();//可以不要，根据情况
  	   //oDate1.setMinutes(00);//可以不要，根据情况
  	   
  	   oDate2=sDate2.toDate();
       ohours2=oDate2.getHours();//可以不要，根据情况
  	   //oDate2.setMinutes(00);//可以不要，根据情况
       ohours  =  parseInt(ohours2-ohours1);
       return  ohours  
   }
  
  
  
function chickme(){
		
 		document.getElementById("add").submit();	
         
} 
    


</script>
</html>