/**
 * <p>标题: BinaryStar右键菜单JS模型</p>
 * <p>功能描述: 模拟右键菜单的功能。</p>
 * <p>实现方法: 一个右键菜单对象由多个右键菜单块组成。每个需要右键功能的对象直接关联一个菜单块，
 *             同时一个菜单块可以配多个对象关联。
 *             每个菜单块包含多个菜单项。每个菜单项可能包含多个子菜单项。
 * <p>作者: BinaryStar</p>
 * <p>版本: 0.1</p>
 * <p>创建日期: 2005-12-21</p>
 */

/**
 * <p>标题: BSRightItemArea</p>
 * <p>功能描述: 右键菜单块。</p>
 * <p>作者: BinaryStar</p>
 * <p>版本: 0.1</p>
 * <p>创建日期: 2005-12-21</p>
 */
function BSRightItemArea(pid, index, text){
  this.pid = pid||"BSRightMenu_1";//ID
  this.index = index;
  this.id = this.pid + "_ItemArea_" + this.index;//ID
  this.leftText = text||"BS 制作";//左边显示的文字
  this.itemList = new Array();//右键菜单集合
  this.thisItemIndex = -1;

  //添加一个右键根菜项
  this.addRootItem = function (text, jsfun, img, disabled){
    return this.addItem(-1, text, jsfun, img, disabled);
  }

  //添加一个右键菜项
  this.addItem = function (pIndex, text, jsfun, img, disabled){
    var newItem = new BSRightItem(this.pid, this.index, this.id, pIndex, this.itemList.length, text, jsfun, img, disabled);
    if (text.Trim() == "-" || text.Trim() == "" || text.Trim() == "sperator"){
      newItem.isSperator = true;
    }
    //设置父节点的子项目
    if (pIndex >= 0){
      var pobj = eval(this.pid);
      this.itemList[pIndex].childList.length++;
      this.itemList[pIndex].childList[this.itemList[pIndex].childList.length-1] = newItem.index;
      newItem.level = this.itemList[pIndex].level+1;
      pobj.setMaxLevel(newItem.level);
    }
    this.itemList.length++;
    this.itemList[this.itemList.length-1] = newItem;
    return newItem;
  }

  //菜单项块的展现
  this.show = function(){
    var htmlStr = "<table border='0' cellspacing='0'>";
    htmlStr += "<tr><td valign=\"top\"  bgcolor=\"#000000\" onclick=\"window.event.cancelBubble=true;\" class=\"bs_rm_info_td\"><nobr><div class=\"bs_rm_info\" onselectstart=\"window.event.returnValue=false;\">"+this.leftText+"</div></nobr>";
    htmlStr += "</td><td style='padding: 1' valign='bottom'>";
    htmlStr += "<table width='100%' border='0' cellspacing='0'>";
    for (var i=0; i<this.itemList.length; i++){
      if (this.itemList[i].pIndex < 0){
        htmlStr += this.itemList[i].show();
      }
    }
    htmlStr += "</table></td></tr></table>";
    return htmlStr;
  }

  //设置选中的菜单项
  this.setIndexItem = function (in_pIndex, thisIndex){
    if (in_pIndex < 0 && thisIndex != this.thisItemIndex){
      if (this.thisItemIndex >= 0){
        var thisDiv = document.getElementById(this.itemList[this.thisItemIndex].id+"_tr");
        if (thisDiv != null){
          thisDiv.className = "bs_rm_out";
          this.itemList[this.thisItemIndex].setImgSelect(thisDiv, false);
          this.itemList[this.thisItemIndex].childIsShow = false;
        }
      }
      this.thisItemIndex = thisIndex;
    }
    else if (in_pIndex >= 0){
      var pItem = this.itemList[in_pIndex];
      this.setFatherSel(in_pIndex);
      if (thisIndex == pItem.thisItemIndex){
        return;
      }
      if (pItem.thisItemIndex >= 0){
        var thisDiv = document.getElementById(this.itemList[pItem.thisItemIndex].id+"_tr");
        if (thisDiv != null){
          thisDiv.className = "bs_rm_out";
          pItem.childIsShow = false;
          pItem.setImgSelect(thisDiv, false);
        }
      }
      pItem.thisItemIndex = thisIndex;
    }
  }

  this.setFatherSel = function (thisIndex){
      if (thisIndex < 0){
        return;
      }
      var thisItem = this.itemList[thisIndex];
      var thisDiv = document.getElementById(this.itemList[thisIndex].id+"_tr");
      if (thisDiv != null){
        thisDiv.className = "bs_rm_over";
        thisItem.setImgSelect(thisDiv, true);
      }
      if (thisItem.pIndex > 0){
        this.setFatherSel(thisItem.pIndex);
      }
  }
}

/**
 * <p>标题: BSRightItem</p>
 * <p>功能描述: 右键菜单项。</p>
 * <p>作者: BinaryStar</p>
 * <p>版本: 0.1</p>
 * <p>创建日期: 2005-12-21</p>
 */
function BSRightItem(pid, areaIndex, areaId,  pIndex, index, text, jsfun, img, disabled){
  this.pid = pid||"BSRightMenu_1";//BS右键菜单对象ID
  this.areaIndex = areaIndex;//右键块索引
  this.areaId = areaId||"";//BS右键菜单块ID
  this.pIndex = pIndex;//父菜单项索引

  this.level = 0;//树的深度
  this.index = index;//本菜单项索引
  this.id = this.areaId + "_item_" + this.index;//ID
  this.text = text || "BS菜单项";//菜单项文字
  this.jsfun = jsfun;//菜单项js函数
  this.img = img||"";//菜单项图片
  this.childList = new Array();//该菜单项包含的子菜单项
  this.isSperator = false;//是否是分隔符
  this.thisItemIndex = -1;//当前鼠标所在的子菜单项索引。
  this.disabled = disabled || false;//该菜单项是否可用
  this.childIsShow = false;//孩子菜单正在展现

  this.setDisabled = function (flag){
    if (flag){
      this.disabled = true;
    }
    else{
      this.disabled = false;
    }
  }

  //添加子菜单项
  this.addItem = function (text, jsfun, img, disabled){
  	var rmObj = eval(this.pid);
	return rmObj.itemAreaList[this.areaIndex].addItem(this.index, text, jsfun, img, disabled);
  }

  //菜单项的展现
  this.show = function(){
  	var rmObj = eval(this.pid);
    var htmlStr = "";
    htmlStr += "<tr id=\""+this.id+"_tr\" class=\"bs_rm_out\"";
    htmlStr += " onmouseover=\""+this.pid+".itemAreaList["+this.areaIndex+"].itemList["+this.index+"].doOnmouseover(this)\"";
    htmlStr += " onmouseout=\""+this.pid+".itemAreaList["+this.areaIndex+"].itemList["+this.index+"].doOnmouseout(this)\"";
    if (!this.isSperator && !this.disabled){
      htmlStr += " onmouseup=\""+this.pid+".itemAreaList["+this.areaIndex+"].itemList["+this.index+"].doOnmouseup(this)\"";
      htmlStr += " onmousedown=\""+this.pid+".itemAreaList["+this.areaIndex+"].itemList["+this.index+"].doOnmousedown(this)\"";
    }
    else{
      htmlStr += " onmouseup=\"window.event.cancelBubble=true;return false;\"";
      htmlStr += " onmousedown=\"window.event.cancelBubble=true;return false;\"";
    }
    htmlStr += " onclick=window.event.cancelBubble=true;return false;";
    htmlStr += ">";
    //图片
    if (!this.isSperator){
      htmlStr += "<td align=\"right\" ";
      if (this.img.Trim() != ""){
        htmlStr += "style=\"width:22px;height:22px;";
        htmlStr += "background: url("+rmObj.imagePath+this.img+");";
        htmlStr += "background-repeat: no-repeat;";
        htmlStr += "background-attachment: no-fixed;";
        htmlStr += "background-position: right;\"";
        htmlStr += " valign=\"middle\">&nbsp;&nbsp;&nbsp;</td>";
      }
      else{
        htmlStr += "style=\"width:2px;height:22px;";
        htmlStr += " valign=\"middle\">&nbsp;</td>";
      }
      //文字
      htmlStr += "<td valign=\"middle\"";
      if (this.disabled){
        htmlStr += " style=\"color:Gray;\"";
      }
      htmlStr += "><nobr>"+this.text+"&nbsp;&nbsp;</nobr></td>";
      //存在孩子时显示箭头。
      htmlStr += "<td style='font-family: webdings;'>";
      if (this.childList.length > 0){
        htmlStr += "4";
      }
        htmlStr += "</td>";
    }
    else{
      //分隔符
      htmlStr += "<td colspan=\"3\" height=\"5\"><hr class=\"bs_rm_sperator\"/></td>";
    }
    htmlStr += "</tr>";
    return htmlStr;
  }

  //设置图片的背景色
  this.setImgSelect = function(elmObj, flag){
    if (this.img.Trim() != ""){
      if (flag){
        elmObj.cells[0].style.backgroundColor = "#8989bc";
      }
      else{
        elmObj.cells[0].style.backgroundColor = "";
      }
    }
  }

  //展现下一级菜单
  this.showChildren = function (elmObj){
    var pobj = eval(this.pid);
    pobj.hiddenAll(this.level+1);

    if (this.childList.length > 0 && !this.disabled){
      var htmlStr = "<table border='0' cellspacing='0'>";
      for (var i=0; i<this.childList.length; i++){
        htmlStr += pobj.itemAreaList[this.areaIndex].itemList[this.childList[i]].show();
      }
      htmlStr += "</table>";
      var thisDiv = document.getElementById(this.pid+"_rm_"+(this.level));
      var left = document.body.scrollLeft+thisDiv.offsetLeft+thisDiv.offsetWidth-4;
      var top = thisDiv.offsetTop+elmObj.offsetTop+1;
      var levelObj = null;
      levelObj = document.getElementById(this.pid+"_rm_"+(this.level+1));
      //level+1层没有创建
      if (levelObj == null){
        levelObj = document.createElement("div");
        levelObj.id = this.pid+"_rm_"+(this.level+1);
        levelObj.className = "bs_rm_div";
        document.body.appendChild(levelObj);
      }
      levelObj.innerHTML = htmlStr;
      levelObj.style.display = "block";
      //定位
      if((left+levelObj.offsetWidth) > document.body.scrollLeft+document.body.clientWidth){
        left -= (elmObj.offsetWidth + levelObj.offsetWidth-4);
      }
        window.status = ("top:"+top+ " thisDiv.offsetHeight:"+thisDiv.offsetHeight+" levelObj.offsetWidth:"+levelObj.offsetHeight+ " all:" + (top+levelObj.offsetHeight) +" max:"+(document.body.scrollLeft+document.body.clientHeight));
      if((top+levelObj.offsetHeight) > document.body.scrollTop+document.body.clientHeight){
        top -= (levelObj.offsetHeight-elmObj.offsetHeight);
      }
      levelObj.style.left = left;
      levelObj.style.top = top;
      this.childIsShow = true;
    }
  }

  //鼠标按下动作
  this.doOnmousedown = function(elmObj){
    window.event.cancelBubble=true;
    if (this.childList.length <= 0){
      elmObj.setCapture();
    }
    return false;
  }
  //鼠标弹起动作
  this.doOnmouseup = function(elmObj){
    window.event.cancelBubble=true;
    if (this.childList.length <= 0){
      var pobj = eval(this.pid);
      pobj.setRMIndex(this.areaIndex, this.index);
      elmObj.releaseCapture();
      pobj.hiddenAll(0);
      if (this.jsfun.Trim() != ""){
        eval(this.jsfun);
      }
    }
    return false;
  }
  //鼠标移入动作
  this.doOnmouseover = function(elmObj){
    window.event.cancelBubble=true;
    if (!this.isSperator){
      elmObj.className = "bs_rm_over";
      this.setImgSelect(elmObj, true);
    }
    //改变其他的项的高选
    var pobj = eval(this.pid);
    pobj.itemAreaList[this.areaIndex].setIndexItem(this.pIndex, this.index);
    this.showChildren(elmObj);
    return false;
  }

  //鼠标移出动作
  this.doOnmouseout = function(elmObj){
    window.event.cancelBubble=true;
    if (!this.isSperator && !this.childIsShow){
      elmObj.className = "bs_rm_out";
      this.setImgSelect(elmObj, false);
    }
    return false;
  }
}

/**
 * <p>标题: BSRightMenu</p>
 * <p>功能描述: BS右键菜单对象。装载所有的右键菜单块</p>
 * <p>作者: BinaryStar</p>
 * <p>版本: 0.1</p>
 * <p>创建日期: 2005-12-21</p>
 */
function BSRightMenu(id){
  this.id = id||"BSRightMenu_1";//ID
  this.itemAreaList = new Array();//右键集合
  this.showItemAreaIndex = -1;//当前显示的右键菜单块
  this.clickItemIndex = -1;//当前鼠标单击所在的子菜单项索引。
  this.maxLevel = 0;//菜单树深度
  var rmlist = null;
  this.imagePath = "";//缺省的路径

  this.setImagesPath = function(inPath){
    this.imagePath = inPath;
  }
  //设置最大深度值
  this.setMaxLevel = function (inLevel){
    if (inLevel > this.maxLevel){
      this.maxLevel = inLevel;
    }
  }
  //添加一个右键菜单块
  this.addItemArea = function (text){
    var area = new BSRightItemArea(this.id, this.itemAreaList.length, text);
    this.itemAreaList.length++;
    this.itemAreaList[this.itemAreaList.length-1] = area;
    return area;
  }

  //激发右键菜单
  this.doRightMenu = function (areaIndex){
    window.event.cancelBubble=true;
    var curAreaIndex = 0;
    if (areaIndex != null){
      curAreaIndex = areaIndex;
    }
    this.hiddenAllRM();
    if (areaIndex < 0){
      document.oncontextmenu = null;
      return;
    }
    if(window.event.button == 2){
      document.oncontextmenu = function(){return false;};
      var left = document.body.scrollLeft+window.event.clientX-1;
      var top = document.body.scrollTop+window.event.clientY-1;
      var div = null;
      if (document.getElementById(this.id+"_rm_0") != null){
        div = document.getElementById(this.id+"_rm_0");
      }
      else{
        div = document.createElement("div");
        div.id = this.id+"_rm_0";
        div.className = "bs_rm_div";
        document.body.appendChild(div);
      }
      div.style.display = "block";
      var tempHTML = "<input type=\"hidden\" id=\""+this.id + "_thisItemIndex\" value=\"-1\"/><input type=\"hidden\" id=\""+this.id + "_thisAreaIndex\" value=\"-1\"/>"
      if (document.getElementById("BS_RightMenu_List") == null){
        temprmlist = document.createElement("input");
        temprmlist.id = "BS_RightMenu_List";
        temprmlist.className = "bs_rm_div";
        temprmlist.value = this.id;
        temprmlist.style.display="none";
        temprmlist.type = "hidden";
        document.body.appendChild(temprmlist);
      }
      else {
        this.addBSRMList(this.id);
      }
      div.innerHTML = this.itemAreaList[curAreaIndex].show() + tempHTML;
      if ((left + div.offsetWidth) > document.body.scrollLeft+document.body.clientWidth){
        left -= div.offsetWidth;
      }
      if((top+div.offsetHeight) > document.body.scrollTop+document.body.clientHeight){
        top -= (div.offsetHeight);
      }
      div.style.left = left;
      div.style.top = top;
    }
    else{
      document.oncontextmenu = null;
    }
  }

  this.setRMIndex = function (areaIndex, itemIndex){
    document.getElementById(this.id+"_thisAreaIndex").value = areaIndex;
    this.showItemAreaIndex = areaIndex;
    document.getElementById(this.id+"_thisItemIndex").value = itemIndex;
    this.clickItemIndex = itemIndex;
  }

  //隐藏指定深度下所有展现的菜单
  this.hiddenAll = function(inLevel){
    var tlevel = 0;
    if (inLevel != null){
      var tlevel = inLevel
    }
    for (var i=tlevel; i<this.maxLevel+1; i++){
      var divObj = document.getElementById(this.id+"_rm_"+i);
      if (divObj != null){
        divObj.style.display = "none";
      }
    }
  }

  //隐藏所有右键菜单
  this.hiddenAllRM = function(){
    if (document.getElementById("BS_RightMenu_List") == null){
      return;
    }
    var rmlist = document.getElementById("BS_RightMenu_List").value.split(",");
    for (var i=0; i<rmlist.length; i++){
      if (rmlist[i].Trim() != ""){
        var temp_rm = eval(rmlist[i].Trim());
        temp_rm.hiddenAll(0);
      }
    }
  }

  //检查右键菜单
  this.addBSRMList = function(inID){
    var rmlist = document.getElementById("BS_RightMenu_List").value.split(",");
    for (var i=0; i<rmlist.length; i++){
      if (rmlist[0].Trim() == inID.Trim()){
        return;
      }
    }
    document.getElementById("BS_RightMenu_List").value += ("," + inID);
  }

  //得到当前显示的菜单块
  this.getThisRMArea = function(){
    return this.itemAreaList[this.showItemAreaIndex];
  }

  //得到当前显示的菜单项
  this.getThisRMItem = function(){
    return this.getThisRMArea().itemList[this.clickItemIndex];
  }

}
