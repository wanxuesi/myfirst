
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'banupdate.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="dwr/interface/danweibo.js"></script>  
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script>  
    <script language="javascript"> 
   //获取所有的省。  
   function getDanweiIds(){
    danweibo.getDanweiIds(callbackDanweiIds);    
   }
   
   function callbackDanweiIds(data){     
      //DWRUtil.addOptions("oid2",["--请选择--"]);   
      //DWRUtil.removeAllOptions("oid1");
      //DWRUtil.addOptions("oid1", data);
      //dwr.util.addOptions("oid2",["--请选择--"]);   
      dwr.util.removeAllOptions("parent");
      dwr.util.addOptions("parent", data);
	  $("parent").value=$("tmp_parent").value;
   }
	</script>
  </head>
  
   <body   onload='dwr.util.useLoadingMessage();getDanweiIds()'>
   <%
   		String tmp_id = request.getParameter("id");
   		
   		String tmp_name = request.getParameter("name");
   		String tmp_parent = request.getParameter("parent");
   		//编码转换工作。
   		byte[] tempByteId = tmp_id.getBytes("ISO-8859-1");
   		
   		byte[] tempBytename =tmp_name.getBytes("ISO-8859-1");
   		byte[] tempByteparent =tmp_parent.getBytes("ISO-8859-1");
   		String id="";
   		id = new String(tempByteId,"gbk");
   		
   		String name="";
   		name = new String(tempBytename,"gbk");
   		String parent="";
   		parent = new String(tempByteparent,"gbk");
   
   
   %>
   <center> 地区修改功能
    <form id="add" action="<%=path%>/banUpdate.do" method="POST">
         <table align="center" >
          
    	   <tr>
    	      <td >  
    		     地区名称
    		   </td>
    		   <td>
    		    <input type="hidden" id="id" name="id" value="<%=id%>">
    		      <input type="text" id="name" name="name" value="<%=name%>"><br>
    		   </td> 		   
    		</tr> 
    		<tr>
    	      <td >  
    		     所属省会
    		   </td>
    		   <input type="hidden" name="tmp_parent" id="tmp_parent" value="<%=parent%>"/>
    		   <td>
    		      <select id="parent" name="parent"></select><br>
    		   </td>
    		</tr> 
    		
    		 
    		    <tr>
    		        <td >
    		              <input type="button" value="提交" onclick="chickme();">
    		        </td>
    		        <td >
    		              <input type="reset" value="重置">
    		        </td>
    		    </tr>
    		  </table>
    	</form>
    	</center>
  </body>
  
  <script language="javascript">
  $("bz").value=$("tmpbz").value;
function chickme(){
		
		var name=document.all.add.name.value;
		var parent=document.all.add.parent.value;
		
		if(name.length<1){
			alert("地区名称不能为空！");
		}else
		if(parent.length<1){
			alert("所属省会名称不能为空！");
		}else{		
        document.all.add.submit();
        } 
    } 
    
      		
</script>

</html>
