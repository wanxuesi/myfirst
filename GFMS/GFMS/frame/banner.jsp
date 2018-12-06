<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="css/banner.css" rel="stylesheet"  type="text/css">
<SCRIPT language="JavaScript">
function showinfo(tem) {

	if (tem==1)
	    self.top.location.href="../index.jsp";
	if (tem==2)
	    parent.window.close(); 
	   // window.top.close();也可以
	if (tem==3)
	    window.showModalDialog("passupdata.jsp","","dialogWidth:480px;dialogHeight:360px;status:no;help:yes;");
	if (tem==4)
	    window.showModalDialog("about.html","","dialogWidth:480px;dialogHeight:370px;status:no;help:yes;");

}

function newImage(arg) {

	if (document.images) {
		rslt = new Image();
		rslt.src = arg;
		return rslt;
	}
	
}

function changeImages() {

	if (document.images && (preloadFlag == true)) {
		for (var i=0; i<changeImages.arguments.length; i+=2) {
			document[changeImages.arguments[i]].src = changeImages.arguments[i+1];
		}
	}

}

var preloadFlag = false;

function preloadImages() {
	if (document.images) {
		mune1_01_Over = newImage("images/menu_01-over.gif");
		mune1_02_Over = newImage("images/menu_02-over.gif");
		mune1_03_Over = newImage("images/menu_06-over.gif");
		mune1_04_Over = newImage("images/menu_05-over.gif");
		mune1_05_Over = newImage("images/menu_02-over.gif");
		preloadFlag = true;
	}
}
</SCRIPT>

<TITLE></TITLE>

</HEAD>
<%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    //String xm =	baseUserContext.getXm().trim();
  			    //int my_dwbm_id = baseUserContext.getDwbm_id();  		
    			String xm = baseUserContext.getXm().trim();
    			String dwname = baseUserContext.getDwname();
    			if(dwname==null){
    			dwname="中国";
    			}
    			String banzu = baseUserContext.getBanzu();
    			if(banzu==null){
    			banzu="";
    			}
    			
    		%>
<BODY class="nav">
<TABLE class="nav" width="100%" cellSpacing="0" cellPadding="0" border="0" background="images/top-bg.gif">
	<TBODY>
		<TR>
		
			<TD  >
			    <TABLE width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
			    <TBODY>
				    <TR>
				        <td width="43%"><img src="images/top-left.gif" width="329" ></td>
				        <TD  width="100%" height="65" nowrap>&nbsp;</TD>
				          <td width="80%"><table width="100%" height="100%"  border="0" align="right" cellpadding="0" cellspacing="0">
				         <tr>
				          
					        <td width="21%"><a href="#"><img src="images/menu_01.gif" alt="重新登录" onclick="showinfo(1);" name="Image4" width="44" height="65" border="0"></a></td>
					        <td width="19%"><a href="#"><img src="images/menu_02.gif" alt="退出" onclick="showinfo(2);" name="Image5" width="40" height="65" border="0"></a></td>
					        <td width="9%"></td>
					        <td width="9%"><a href="#"><img src="images/menu_05.gif" alt="技术支持" onclick="showinfo(4)" name="Image8" width="42" height="65" border="0"></a></td>
					     	
					     	<TD class="plainText"  height="65"   nowrap>当前用户：<%=dwname%>_<%=banzu%>_<%=xm%>&nbsp;</TD>
					      
					      </tr>  
				   
					    </table></td>
				        <td width="32%"><div align="right"><img src="images/top-right.gif"  height="65"></div></td>
				        
					</TR>

	            </TBODY>
                </TABLE>
            </TD>
		</TR>
	</TBODY>
</TABLE>
</BODY>
</HTML>
