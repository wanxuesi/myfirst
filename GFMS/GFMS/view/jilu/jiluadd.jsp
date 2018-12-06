
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>添加交易记录功能</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
       <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">


	<script type="text/javascript" src="dwr/interface/databo.js"></script>
 	<script type="text/javascript" src="dwr/interface/listbo.js"></script> 
 	<script type="text/javascript" src="dwr/interface/gpmcbo.js"></script>
 	
 	<script type="text/javascript" src="dwr/interface/lxbo.js"></script>
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
   	
	
   <script language="javascript">
   function setTongbu(){
   dwr.engine.setAsync(false);
   
   }
   
   function getLxs(){
    lxbo.getLxs(callbackLxs);    
   }
    function callbackLxs(data){     
        
      //dwr.util.removeAllOptions("jifl");
      //dwr.util.addOptions("jifl",["全部"]);
      dwr.util.addOptions("jifl", data);
   }
   
   </script>
  </head>
  <%
  			java.util.Date d = new Date();
  			java.text.SimpleDateFormat   sdf   =   new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");   
			String rq = sdf.format(d);
  		%>
  <body  onload='dwr.util.useLoadingMessage();setTongbu();getKYZJ();getLxs();' >
   <center> <br>
    <form id="add" name="add" action="<%=path%>/jiluAdd.do" method="post">
         <table  Width="750" class="tableframe" align="center">
         <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    String zjzh = baseUserContext.getNx();
  			    int id = baseUserContext.getId();
  			    String idStr = Integer.toString(id);
    		%>
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        添加交易记录功能
    		        
    		        </td>
    		</tr>
           
    		<tr>
    	      <td   class="listcellrow">  
    		     交易时间
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="text" id="jysj" name="jysj" size="20" class="text" value="<%=rq%>"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    	  
    	   
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     证券名称
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="zqmc" name="zqmc" value="" class="text"  onchange="getChangeZqmcAndJys();getCysl();"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     证券代码
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="zqdm" name="zqdm" value="" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     交易分类
				</td>
    		   <td  class="listcellrow">
    		  
    		   <select id="jifl" name="jifl" class="text" onchange="getCysl();">
    		   
			   </select>
  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     买卖标志
				</td>
    		   <td  class="listcellrow">
    		  
    		   <select id="mmflag" name="mmflag" class="text" onchange="getCysl();">
    		   <OPTION value="买入" selected="selected">买入</OPTION>
    		   <OPTION value="卖出">卖出</OPTION>
    		  
			   </select>
  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     成交价格
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="cjjg" name="cjjg" value=""   onchange="getChangeCjje();"  class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     成交数量
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="cjsl" name="cjsl" value=""  onchange="getChangeCjje();" ondblclick="setCjsl();" class="text"> 
    		    <font color=red>双击为全部</font>&nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    <input type="hidden" id="KYZJ" name="KYZJ" value="">
    		    最大可交易数量<input type="text" id="cjsltmp" name="cjsltmp" value="" class="text">
    		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     成交金额
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="cjje" name="cjje" value="" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  Width="100" class="listcellrow">
    		     交易所名称
				</td>
    		   <td  class="listcellrow">
    		  
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
    		    <input type="text" id="zjzh" name="zjzh" value="<%=zjzh%>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     客户代码
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="khdm" name="khdm" value="<%=idStr%>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	    <tr>
              <td  Width="100" class="listcellrow">
    		     股东名称
				</td>
    		   <td  class="listcellrow">
    		    <input type="text" id="gdmc" name="gdmc" value="<%=xm%>" class="text"> &nbsp;&nbsp;&nbsp;&nbsp;  		    
    		    </td>
    	   </tr>
    	   
    		<tr>

    	      <td   class="listcellrow">  
    		     备注
    		   </td>
    		   <td  class="listcellrow">
    		   &nbsp;&nbsp;<TEXTAREA id="bz" name="bz" cols="100" rows=8 style="BACKGROUND-COLOR: #ffffdd;border: solid 1px #888888;"></TEXTAREA>  
    		   </td>
    		</tr>
    		
    		 
    		
    		
    		 <tr>
              <td  Width="100" class="listcellrow">
    		     交易状态
				</td>
    		   <td  class="listcellrow">
    		    <select id="flag1" name="flag1" class="text">
    		   <!--  
    		   
    		   <OPTION value="未处理" >未处理</OPTION>
    		   
    		   -->
    		   <OPTION value="已处理" selected >已处理</OPTION>
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
  function setCjsl(){
  	$("cjsl").value = $("cjsltmp").value;
  	getChangeCjje();
  	
  }
  function getKYZJ(){
  		
    	databo.getKYZJ(callbackKYZJ);
  
  }
   function callbackKYZJ(data){     
        
     	$("KYZJ").value=data;
     	
     	//alert($("KYZJ").value);
     	
   }
 
    function callbackDiqus(data){     
        
      dwr.util.removeAllOptions("diqu");
      dwr.util.addOptions("diqu", data);
   }
  function getChangeZqmcAndJys(){
  		var zqmc = $("zqmc").value;
    	gpmcbo.getZqdmAndJysByZqmc(zqmc,callbackZqmc);
  
  }
   function callbackZqmc(data){     
        //alert(data.length);
     	//alert(data[0]);
     	$("zqdm").value=data[0];
     	//alert(data[1]);
     	$("jysmc").value=data[1];
     	
     	$("cjjg").value=data[2];
     	
   }
   
   function getCysl(){
  		var zqdm = $("zqdm").value;
  		
  		var jifl = $("jifl").value;
  		var mmflag = $("mmflag").value;
  		//alert(zqdm);
  		//alert(jifl);
  		//alert(mmflag);
  		if(mmflag=="买入"){
  		//alert(mmflag);
  			$("cjsl").value="";
  			$("cjje").value="";
  			//通过当前价和KYZJ 得到最大成交数量
  			$("cjsltmp").value=parseFloat($("KYZJ").value)/parseFloat($("cjjg").value);
  			$("cjsltmp").value=parseInt((parseInt($("cjsltmp").value)/100))*100;
  			
  		}else{
  			 if(zqdm!="" && zqdm!="null"){
    			getCyslByZqdmAndJifl(zqdm,jifl);
    		}
    	
    	}
  
  }
  function getCyslByZqdmAndJifl(zqdm,jifl){
  				
    	listbo.getCyslByZqdmAndJifl(zqdm,jifl,callbackCysl);
  
  }
   function callbackCysl(data){     
        //alert(data.length);
     	//alert(data);
     	$("cjsltmp").value=data;
     	$("cjsl").value="";
  		$("cjje").value="";
     	
     	
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

	if(parseInt($("cjsl").value)>parseInt($("cjsltmp").value)){
		alert("成交数量大于最大可交易数量，请重新填写成交数量！");
		return;
	}
	if(confirm("您确定'"+$("mmflag").value+"'"+$("zqmc").value+"("+$("zqdm").value+","+$("cjjg").value+"元,"+$("cjsl").value+"股,"+$("jifl").value+")??\n 确认后，将无法撤销！！")){
		
 		   document.getElementById("add").submit();	
       } 
} 
    


</script>
</html>