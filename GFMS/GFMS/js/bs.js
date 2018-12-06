function getDomain(){
	str=window.location.pathname;
	arr=str.split("/");
        if (arr[0] == ""){
          return arr[1];
        }
        else{
          return arr[0];
        }

}
function getNewPageName(){
  date = new Date();
  var winName = "BSIndexWindow_"+date.getFullYear();
    winName += date.getMonth() + 1;
    winName += date.getDate();
    winName += date.getHours();
    winName += date.getMinutes();
    winName += date.getSeconds();
    winName += date.getMilliseconds();
  return winName;
}
function point(x, y){
  this.x = x || 0;
  this.y = y || 0;
}

/**
* <p>方法名称：GetDefineX</p>
* <p>方法功能描述：得到网页中任意一个对象的绝对坐标的X值</p>
* <p>输入参数描述：
* ObjectID：输入的对象。
* </p>
* <p>输出参数描述:
* 对象的绝对坐标的X值
* </p>
*/
function GetDefineX(ObjectID)
{
	var iPositionX=ObjectID.offsetLeft;
	while(ObjectID=ObjectID.offsetParent)
	{
		iPositionX+=ObjectID.offsetLeft-ObjectID.scrollLeft;
	}
	return iPositionX;
}

/**
* <p>方法名称：GetDefineY</p>
* <p>方法功能描述：得到网页中任意一个对象的绝对坐标的Y值</p>
* <p>输入参数描述：
* ObjectID：输入的对象。
* </p>
* <p>输出参数描述:
* 对象的绝对坐标的Y值
* </p>
*/
function GetDefineY(ObjectID)
{
	var iPositionY=ObjectID.offsetTop;
	while(ObjectID=ObjectID.offsetParent)
	{
   		iPositionY+=ObjectID.offsetTop-ObjectID.scrollTop;
	}
	return iPositionY;
}
/*    	function doExit(){
            window.frmBusiness.action=vActionExit;
            window.frmBusiness.submit();
    	}
*/
/*---去空格---*/
String.prototype.Trim = function()
{
return this.replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.LTrim = function()
{
return this.replace(/(^\s*)/g, "");
}

String.prototype.RTrim = function()
{
return this.replace(/(\s*$)/g, "");
}

//对话框
function showBSDialogBox(showString, paras){
  var jsObj = document.getElementById("bsDialogBox_showString");
  if (jsObj != null){
    jsObj.innerHTML = showString;
    return true;
  }
  var httpStr = "";
  var xMax = screen.availWidth;
  var yMax = screen.availHeight;
  var width = 300;
  var height = 150;
  var top = (yMax-height)/2;
  var left = (xMax-width)/2;
  jsObj = document.createElement("div");
  jsObj.id = "bsDialogBox_div";
  jsObj.style.cssText = "position:absolute;left:"+(left)+";top:"+(top)+";width:"+(width)+";height:"+(height)+";border:0;z-index:100;";
  httpStr += "<table valign=\"middle\" onselectstart=\"return false;\" style=\"width:300px;background:white;\" class=\"bseditframe_showtools\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\""+jsObj.id+"_table\">";
  httpStr += "<tr id=\""+jsObj.id+"_tr\" class=\"bseditframe_showtools_td1\"><td id=\""+jsObj.id+"_table_td\" align=\"left\" valign=\"middle\" width=100%><nobr id=\""+jsObj.id+"_table_br\"><b id=\""+jsObj.id+"_table_b\">BinaryStar对话框</b></nobr></td>";
  httpStr += "<td align=\"right\" style=\"cursor:hand;\" class=\"frame_c_t_close\" title=\"关闭\" onclick=\"closeDialog()\"><nobr>&nbsp;&nbsp;&nbsp;&nbsp;</nobr>";
  httpStr += "</tr>";
  httpStr += "<tr valign=\"buttom\"><td colspan=\"2\" valign=\"middle\" align=\"center\" style=\"width:100%;height:100%;background:buttonface;border-top: background 1px solid;\">";
  httpStr += "<table border=\"0\" valign=\"middle\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:90%;height:100%;\">";
  httpStr += "<tr valign=\"middle\">";
  httpStr += "<td style=\"width:100px;height:100%;background: url(../images/dialogWarning.gif);background-position:center;background-position: center;background-repeat: no-repeat;background-attachment: fixed;\" align=\"center\" valign=\"middle\">"
  httpStr += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
  httpStr += "<td style=\"width:100%;height:100%;\" align=\"left\" valign=\"middle\">"
  httpStr += "<div valign=\"middle\" id=\"bsDialogBox_showString\" style=\"width:280px;height:50px;overflow:auto;\">";
  httpStr += showString;
  httpStr += "</div>";
  httpStr += "</td></tr>";
  httpStr += "</table>";
  httpStr += "</td>";
  httpStr += "</tr>";
  httpStr += "<tr><td colspan=\"2\" align=\"center\" style=\"background:buttonface;height:30px;\">";
  httpStr += "<input type=\"button\" value=\"&nbsp;&nbsp;确定&nbsp;&nbsp;\" onclick=\"closeDialog()\">&nbsp;";
  httpStr += "</td></tr>";
  httpStr += "</table>";
  jsObj.innerHTML = httpStr;
  document.body.appendChild(jsObj);
}

function getOpenLeft(width){
  return xOffset = (getXMax()-width)/2;
}

function getOpenTop(height){
  return yOffset = (getYMax()-height)/2;
}

function getOpenWidth(inWidth){
}

function getOpenHeight(inHeight){
}

function getXMax(){
  var xMax = "";
  if (document.all){
    var xMax = screen.width;
  }
  else{
    if (document.layers){
      var xMax = window.outerWidth;
    }
    else{
      var xMax = screen.width;
    }
  }
  return xMax;
}

function getYMax(){
  var yMax = "";
  if (document.all){
    var yMax = screen.height;
  }
  else{
    if (document.layers){
      var yMax = window.outerHeight;
    }
    else{
      var yMax = screen.height;
    }
  }
  return yMax;
}

//对话框
function showBSMinDialogBox(showString, paras){
  var jsObj = document.getElementById("bsDialogBox_showString");
  if (jsObj != null){
    jsObj.innerHTML = showString;
    return true;
  }
  //得到鼠标点击的控件
  var httpStr = "";
  var mouseObj = event.srcElement;
  var left = GetDefineX(mouseObj)-document.body.scrollLeft;
  var top = (GetDefineY(mouseObj)+mouseObj.offsetHeight)-document.body.scrollTop;

  jsObj = document.createElement("div");
  jsObj.id = "bsDialogBox_div";
  jsObj.style.cssText = "position:absolute;left:"+(left)+";top:"+(top)+";width:1px;height:1px;border:0;z-index:100;";
  httpStr += "<table valign=\"middle\" onselectstart=\"return false;\" style=\"width:100%;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\""+jsObj.id+"_table\">";
  httpStr += "<tr valign=\"buttom\"><td valign=\"middle\" align=\"left\" style=\"width:100%;height:100%;background:buttonface;border:blue 0px solid;\">";
  httpStr += "<nobr style=\"color:blue;\">" + showString + "</nobr>";
  httpStr += "</td></tr>";
  httpStr += "</table>";
  jsObj.innerHTML = httpStr;
  document.body.appendChild(jsObj);
}

function closeDialog(){
  var obj = document.getElementById("bsDialogBox_div");
  if (obj != null){
    document.body.removeChild(obj);
  }
}
