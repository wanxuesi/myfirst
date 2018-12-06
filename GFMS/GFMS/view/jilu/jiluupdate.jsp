
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>修改交易记录功能</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
       <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">


	
 	 
 	<script type="text/javascript" src="dwr/interface/lxbo.js"></script>
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
   	
	<script language="javascript">
   
   
   function getLxs(){
    lxbo.getLxs(callbackLxs);    
   }
    function callbackLxs(data){     
        
      //dwr.util.removeAllOptions("jifl");
      //dwr.util.addOptions("jifl",["全部"]);
      dwr.util.addOptions("jifl", data);
      $("jifl").value= $("tmpjifl").value;
   }
   
   </script>
   
  </head>
  <%
  			java.util.Date d = new Date();
  			java.text.SimpleDateFormat   sdf   =   new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");   
			String rq = sdf.format(d);
  		%>
  <body  onload='dwr.util.useLoadingMessage();getLxs();' >
   <center> <br>
    <form id="add" name="add" action="<%=path%>/jiluUpdate.do" method="post">
         <table  Width="750" class="tableframe" align="center">
         <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    //String zjzh = baseUserContext.getNx();
  			    //String khdm = baseUserContext.getWx();
    		%>
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        修改交易记录功能
    		        </td>
    		</tr>
           
    		<tr>
    	      <td   class="listcellrow">  
    		     交易时间
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="hidden" id="id" name="id" value="<bean:write name="JILU" property="id" scope="request"/>">
    		   <input type="hidden" id="timeid" name="timeid" value="<bean:write name="JILU" property="timeid" scope="request"/>">
    		   <input type="text" id="jysj" name="jysj" size="20" class="text" value="<bean:write name="JILU" property="jysj" scope="request"  format="yyyy-MM-dd HH:mm"/>"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    	 
    	   
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     证券名称
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="zqmc" name="zqmc" value="<bean:write name="JILU" property="zqmc" scope="request"/>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     证券代码
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="zqdm" name="zqdm" value="<bean:write name="JILU" property="zqdm" scope="request"/>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     交易分类
				</td>
    		   <td  class="listcellrow">
    		   <input type="hidden" id="tmpjifl" name="tmpjifl" value="<bean:write name="JILU" property="jifl" scope="request"/>">
    		   <select id="jifl" name="jifl" class="text">
    		   
			   </select>
  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     买卖标志
				</td>
    		   <td  class="listcellrow">
    		   <input type="hidden" id="tmpmmflag" name="tmpmmflag" value="<bean:write name="JILU" property="mmflag" scope="request"/>">
    		   <select id="mmflag" name="mmflag" class="text">
    		   <OPTION value="买入">买入</OPTION>
    		   <OPTION value="卖出" selected="selected">卖出</OPTION>
    		  
			   </select>
  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     成交价格
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="cjjg" name="cjjg" value="<bean:write name="JILU" property="cjjg" scope="request"/>"   onchange="getChangeCjje();"  class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     成交数量
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="cjsl" name="cjsl" value="<bean:write name="JILU" property="cjsl" scope="request"/>"  onchange="getChangeCjje();" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     成交金额
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="cjje" name="cjje" value="<bean:write name="JILU" property="cjje" scope="request"/>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     交易所名称
				</td>
    		   <td  class="listcellrow">
    		   <input type="hidden" id="tmpjysmc" name="tmpjysmc" value="<bean:write name="JILU" property="jysmc" scope="request"/>">
    		   <select id="jysmc" name="jysmc" class="text">
    		   <OPTION value="上海A股" selected>上海A股</OPTION>
    		   <OPTION value="深圳A股">深圳A股</OPTION>
    		  
			   </select>
  		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     资金账号
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="zjzh" name="zjzh" value="<bean:write name="JILU" property="zjzh" scope="request"/>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     客户代码
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="khdm" name="khdm" value='<bean:write name="JILU" property="khdm" scope="request"/>' class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     股东名称
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="gdmc" name="gdmc" value="<bean:write name="JILU" property="gdmc" scope="request"/>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   
    		<tr>

    	      <td   class="listcellrow">  
    		     备注
    		   </td>
    		   <td  class="listcellrow">
    		   &nbsp;&nbsp;<TEXTAREA id="bz" name="bz" cols="100" rows=8 style="BACKGROUND-COLOR: #ffffdd;border: solid 1px #888888;"><bean:write name="JILU" property="bz" scope="request"/></TEXTAREA>  
    		   </td>
    		</tr>
    		
    		
    		
    		
    		 <tr>
              <td  Width="100" class="listcellrow">
    		     交易状态
				</td>
    		   <td  class="listcellrow">
    		   <input type="hidden" id="tmpflag1" name="tmpflag1" value="<bean:write name="JILU" property="flag1" scope="request"/>">
    		    <select id="flag1" name="flag1" class="text">
    		   <OPTION value="未处理" selected>未处理</OPTION>
    		   <!--
    		   <OPTION value="已处理">已处理</OPTION>
    		    -->
			   </select> &nbsp;&nbsp;&nbsp;&nbsp;  		    
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
  
  
  $("mmflag").value= $("tmpmmflag").value;
  $("jysmc").value= $("tmpjysmc").value;

  $("flag1").value= $("tmpflag1").value;
  
  var cjsl = $("cjsl").value;
  if(parseInt(cjsl)<0){
  	$("cjsl").value=(-1)*parseInt(cjsl);
  }
  
   function getChangeCjje(){
  	var cjjg = $("cjjg").value;
  	var cjsl = $("cjsl").value;
  	if(cjjg==""||cjjg=="0"||cjsl==""){
  		$("cjje").value="";
  	}else{
  		var result = parseFloat(cjjg)*parseInt(cjsl); 	
  		$("cjje").value=result;
  	
    }
  }
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