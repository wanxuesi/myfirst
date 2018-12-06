
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<script type="text/javascript" src="jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="option_check.js"></script> 

<base href="<%=basePath%>">
  
<script type="text/javascript"> 
function getCheckBox(){ 
	Jselect($("#txt_wbk"),{ 
	bindid:'txt_wbk', 	
	optionsbind:function(){return hqhtml();} 
	});
}

var option = new Array('第一项','第二项','第三项','第四项','第五项','第六项');


function hqhtml(){
var optionshtml="<table style='width:100%; background-color: #fff; border:1px solid #000;' cellpadding=\"0\" cellspacing=\"0\" >";
					for(var i=0;i<option.length;i++){
    　　　　　　　　　　optionshtml+="<tr><td style='width:20px'><input type=\"checkbox\" value='"+i+"' /></td><td>"+option[i]+"</td></tr>"
					}
					optionshtml+="</table>"; 

return optionshtml; 
}

</script> 

	<input id="txt_wbk" type="text" style="width: 200px;cursor:hand;margin-top:-15;" readonly="readonly"/>
	<span style="border: 5px solid #fff; border-top: 5px solid #000; width:0px;margin-top:15px;"></span>
	
<BODY onload='getCheckBox();'></BODY>
</html>
