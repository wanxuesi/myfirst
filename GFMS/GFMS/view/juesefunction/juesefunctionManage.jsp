
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'juesefunctionManage.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  	<script language="JavaScript">
	
	
	 function func_add(){	
			//alert("aa");
				//判断“可选功能”下拉列表框的选项个数
				var myFunctioncodeCount = document.all.myFunctioncode.length;
				//从“可选功能”下拉列表框的最后一个选项开始循环，到第1个选项为止
				for(i=myFunctioncodeCount-1;i>-1;i--){
					//如果该选项处于选择状态，则执行以下代码
					//还要判断functioncodes中有没有   
					//2008 6-5修改
					var _options = document.all.functioncodes.options;
										
					if (document.all.myFunctioncode[i].selected){
						//定义一个判断是否选择的变量--默认为未选择
						var functioncodes_hasSelected=false;
							//注：添加的时候不需要判断2008-06-26
								//看functioncodes里有没有这个，如果有，就将functioncodes_hasSelected置为true
								//for(var x=0;x<_options.length;x++){
								//	if(document.all.functioncodes[i].value==_options[x].value){		
								//	//alert("你已经选择了此权限！");
								//	functioncodes_hasSelected=true;
								//	break;
								//	}
								//}
						
						//根据my_dwbm_id_hasSelected情况，确定是否将该项添加到my_dwbm_idoption中去。		
						if(functioncodes_hasSelected==false){
							//创建一个新的Option对象
							//该对象的text与value属性值为当前“可选功能”下拉列表框的选项的text与value属性值
							var myOption = new Option(document.all.myFunctioncode[i].text,document.all.myFunctioncode[i].value);
							//在“已选权限”下拉列表框中添加一个选项
							document.all.functioncodes.options[document.all.functioncodes.options.length]=myOption;
							//删除当前选项
							document.all.myFunctioncode.remove(i);
						}
					}						
				}	
			}
		
		
		 function func_remove(){
		 
			var functioncodesCount = document.all.functioncodes.length - 1;
				for(i=functioncodesCount;i>-1;i--)
				{
					if (document.all.functioncodes[i].selected)
					{
						var myOption = new Option(document.all.functioncodes[i].text,document.all.functioncodes[i].value);
						document.all.myFunctioncode.options[document.all.myFunctioncode.options.length]=myOption;
						document.all.functioncodes.remove(i);
					}
				}
		}
		
		function func_addAll(){
				var _options = document.all.myFunctioncode.options;
				//var _value = new String[_options.length];
				//alert(_options.length);
				for(var i=0;i<_options.length;i++){
					_options[i].selected=true;
				}
				
				func_add();
			
			
			}
			
			function func_removeAll(){
				var _options = document.all.functioncodes.options;
				//var _value = new String[_options.length];
				//alert(_options.length);
				for(var i=0;i<_options.length;i++){
					_options[i].selected=true;
				}
				
				func_remove();
			
			
			}
		
	
	function addManage(){
		
		var _options = document.all.functioncodes.options;
		//var _value = new String[_options.length];
		//alert(_options.length);
		for(var i=0;i<_options.length;i++){
			_options[i].selected=true;
			//var _value = _options[i].value;
			//alert(_value);
			//var obj = document.createElement("hidden");
			
			//obj.name = "functions";
			//obj.value = _value;
			//document.all.myForm.applyElement(obj);
			//alert(obj.value);
			//document.writeln("<input type='hidden' name='function' value='" +_value + "'>");
		}
		//alert('aa');
		 document.all.myForm.action="<%=path%>/juesefunctionManage.do";
		 document.all.myForm.submit();
		
	}
	
	</script>
	</head>

	<body>
		<CENTER>
			<FORM name="myForm"  method="post">
				<table border="0" width="90%" cellpadding="2" cellspacing="0">
					<TR>
						<TD align="center" colspan="4">
						<br>
							 <font size="5" face="黑体">角色功能管理</font>
						</TD>
					</TR>
					<TR>
						<TD align="center" colspan="4" valign="top" bgcolor="#cccccc">	</TD>
					</TR>
					<TR>
						<TD width="18%">
							 角色：
						</TD>
						<TD width="18%">
							<input type="text" name="juesename" value="<%=(String)request.getAttribute("juesename")%>" readOnly>
						</TD>
						<TD width="18%"></TD>
						<TD>角色已有功能</TD>
					</TR>
					<TR>
						<TD width="18%">
							 角色所有功能
						</TD>
						<TD width="18%">

							<select name="myFunctioncode" size="10" ondblclick="func_add()" style="width:200"  multiple="multiple">
									<logic:iterate id="myFunctions" name="FUNCTIONDTOS" scope="request" type="com.fuguo.dto.FunctionDTO">
									<OPTION value="<bean:write name='myFunctions' property='functioncode'/>">
										<bean:write name="myFunctions" property="funcname" />
									</OPTION>
								</logic:iterate>
							</SELECT>
						</TD>
						<td align="center">
							<input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;>>&nbsp;&nbsp;&nbsp;&nbsp;" onclick="func_add()"><br>
							<input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;<<&nbsp;&nbsp;&nbsp;&nbsp;" onclick="func_remove()"><br>
							<input type="button" value="&nbsp;&nbsp;&nbsp;>>>>&nbsp;&nbsp;&nbsp;" onclick="func_addAll()"><br>
							<input type="button" value="&nbsp;&nbsp;&nbsp;<<<<&nbsp;&nbsp;&nbsp;" onclick="func_removeAll()">
						
						</td>
						<td width="30%" align="center">
							<select name="functioncodes" size="10" ondblclick="func_remove()" style="width:200" multiple="multiple">
							<logic:iterate id="functions" name="MYFUNCTIONDTOS" scope="request" type="com.fuguo.dto.FunctionDTO">
									<OPTION value="<bean:write name='functions' property='functioncode'/>">
										<bean:write name="functions" property="funcname" />
									</OPTION>
								</logic:iterate>							
							</select>
						</td>
					</TR>

					<TR>
						<TD align="center" colspan="4" valign="top" bgcolor="#cccccc">	</TD>
					</TR>
					<TR>
						<TD align="right" colspan="2"><INPUT  type="button" value="提交" onClick="addManage()">	</TD>
						<TD align="left" colspan="2"><input  type="button" value="返回" onclick="javacript:history.back()">	</TD>
					</TR>
				</table>
					
			</FORM>
		</CENTER>
	</body>
	<script language="JavaScript">
	//除去myFunctioncode中的部分功能（因为functioncodes中已有）
      //判断“已选权限”functioncodes下拉列表框的选项个数
      			//alert("aaa");
				var functioncodesCount = document.all.functioncodes.length;
				//从“已选权限”下拉列表框的最后一个选项开始循环，到第1个选项为止
				for(i=functioncodesCount-1;i>-1;i--){
					var _options = document.all.myFunctioncode.options;
						//看myFunctioncode里有没有这个，如果有，就将myFunctioncode去掉
						for(var x=0;x<_options.length;x++){
							if(document.all.functioncodes[i].value==_options[x].value){		
							//从myFunctioncode中去掉
							//删除当前选项
							document.all.myFunctioncode.remove(x);
							break;
							}
						}	
					
				}
	
	
	</script>
	
	
</html>
