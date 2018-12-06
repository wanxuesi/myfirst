/**
 * <p>����: BinaryStar�Ҽ��˵�JSģ��</p>
 * <p>��������: ģ���Ҽ��˵��Ĺ��ܡ�</p>
 * <p>ʵ�ַ���: һ���Ҽ��˵������ɶ���Ҽ��˵�����ɡ�ÿ����Ҫ�Ҽ����ܵĶ���ֱ�ӹ���һ���˵��飬
 *             ͬʱһ���˵�������������������
 *             ÿ���˵����������˵��ÿ���˵�����ܰ�������Ӳ˵��
 * <p>����: BinaryStar</p>
 * <p>�汾: 0.1</p>
 * <p>��������: 2005-12-21</p>
 */

/**
 * <p>����: BSRightItemArea</p>
 * <p>��������: �Ҽ��˵��顣</p>
 * <p>����: BinaryStar</p>
 * <p>�汾: 0.1</p>
 * <p>��������: 2005-12-21</p>
 */
function BSRightItemArea(pid, index, text){
  this.pid = pid||"BSRightMenu_1";//ID
  this.index = index;
  this.id = this.pid + "_ItemArea_" + this.index;//ID
  this.leftText = text||"BS ����";//�����ʾ������
  this.itemList = new Array();//�Ҽ��˵�����
  this.thisItemIndex = -1;

  //���һ���Ҽ�������
  this.addRootItem = function (text, jsfun, img, disabled){
    return this.addItem(-1, text, jsfun, img, disabled);
  }

  //���һ���Ҽ�����
  this.addItem = function (pIndex, text, jsfun, img, disabled){
    var newItem = new BSRightItem(this.pid, this.index, this.id, pIndex, this.itemList.length, text, jsfun, img, disabled);
    if (text.Trim() == "-" || text.Trim() == "" || text.Trim() == "sperator"){
      newItem.isSperator = true;
    }
    //���ø��ڵ������Ŀ
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

  //�˵�����չ��
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

  //����ѡ�еĲ˵���
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
 * <p>����: BSRightItem</p>
 * <p>��������: �Ҽ��˵��</p>
 * <p>����: BinaryStar</p>
 * <p>�汾: 0.1</p>
 * <p>��������: 2005-12-21</p>
 */
function BSRightItem(pid, areaIndex, areaId,  pIndex, index, text, jsfun, img, disabled){
  this.pid = pid||"BSRightMenu_1";//BS�Ҽ��˵�����ID
  this.areaIndex = areaIndex;//�Ҽ�������
  this.areaId = areaId||"";//BS�Ҽ��˵���ID
  this.pIndex = pIndex;//���˵�������

  this.level = 0;//�������
  this.index = index;//���˵�������
  this.id = this.areaId + "_item_" + this.index;//ID
  this.text = text || "BS�˵���";//�˵�������
  this.jsfun = jsfun;//�˵���js����
  this.img = img||"";//�˵���ͼƬ
  this.childList = new Array();//�ò˵���������Ӳ˵���
  this.isSperator = false;//�Ƿ��Ƿָ���
  this.thisItemIndex = -1;//��ǰ������ڵ��Ӳ˵���������
  this.disabled = disabled || false;//�ò˵����Ƿ����
  this.childIsShow = false;//���Ӳ˵�����չ��

  this.setDisabled = function (flag){
    if (flag){
      this.disabled = true;
    }
    else{
      this.disabled = false;
    }
  }

  //����Ӳ˵���
  this.addItem = function (text, jsfun, img, disabled){
  	var rmObj = eval(this.pid);
	return rmObj.itemAreaList[this.areaIndex].addItem(this.index, text, jsfun, img, disabled);
  }

  //�˵����չ��
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
    //ͼƬ
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
      //����
      htmlStr += "<td valign=\"middle\"";
      if (this.disabled){
        htmlStr += " style=\"color:Gray;\"";
      }
      htmlStr += "><nobr>"+this.text+"&nbsp;&nbsp;</nobr></td>";
      //���ں���ʱ��ʾ��ͷ��
      htmlStr += "<td style='font-family: webdings;'>";
      if (this.childList.length > 0){
        htmlStr += "4";
      }
        htmlStr += "</td>";
    }
    else{
      //�ָ���
      htmlStr += "<td colspan=\"3\" height=\"5\"><hr class=\"bs_rm_sperator\"/></td>";
    }
    htmlStr += "</tr>";
    return htmlStr;
  }

  //����ͼƬ�ı���ɫ
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

  //չ����һ���˵�
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
      //level+1��û�д���
      if (levelObj == null){
        levelObj = document.createElement("div");
        levelObj.id = this.pid+"_rm_"+(this.level+1);
        levelObj.className = "bs_rm_div";
        document.body.appendChild(levelObj);
      }
      levelObj.innerHTML = htmlStr;
      levelObj.style.display = "block";
      //��λ
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

  //��갴�¶���
  this.doOnmousedown = function(elmObj){
    window.event.cancelBubble=true;
    if (this.childList.length <= 0){
      elmObj.setCapture();
    }
    return false;
  }
  //��굯����
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
  //������붯��
  this.doOnmouseover = function(elmObj){
    window.event.cancelBubble=true;
    if (!this.isSperator){
      elmObj.className = "bs_rm_over";
      this.setImgSelect(elmObj, true);
    }
    //�ı���������ĸ�ѡ
    var pobj = eval(this.pid);
    pobj.itemAreaList[this.areaIndex].setIndexItem(this.pIndex, this.index);
    this.showChildren(elmObj);
    return false;
  }

  //����Ƴ�����
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
 * <p>����: BSRightMenu</p>
 * <p>��������: BS�Ҽ��˵�����װ�����е��Ҽ��˵���</p>
 * <p>����: BinaryStar</p>
 * <p>�汾: 0.1</p>
 * <p>��������: 2005-12-21</p>
 */
function BSRightMenu(id){
  this.id = id||"BSRightMenu_1";//ID
  this.itemAreaList = new Array();//�Ҽ�����
  this.showItemAreaIndex = -1;//��ǰ��ʾ���Ҽ��˵���
  this.clickItemIndex = -1;//��ǰ��굥�����ڵ��Ӳ˵���������
  this.maxLevel = 0;//�˵������
  var rmlist = null;
  this.imagePath = "";//ȱʡ��·��

  this.setImagesPath = function(inPath){
    this.imagePath = inPath;
  }
  //����������ֵ
  this.setMaxLevel = function (inLevel){
    if (inLevel > this.maxLevel){
      this.maxLevel = inLevel;
    }
  }
  //���һ���Ҽ��˵���
  this.addItemArea = function (text){
    var area = new BSRightItemArea(this.id, this.itemAreaList.length, text);
    this.itemAreaList.length++;
    this.itemAreaList[this.itemAreaList.length-1] = area;
    return area;
  }

  //�����Ҽ��˵�
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

  //����ָ�����������չ�ֵĲ˵�
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

  //���������Ҽ��˵�
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

  //����Ҽ��˵�
  this.addBSRMList = function(inID){
    var rmlist = document.getElementById("BS_RightMenu_List").value.split(",");
    for (var i=0; i<rmlist.length; i++){
      if (rmlist[0].Trim() == inID.Trim()){
        return;
      }
    }
    document.getElementById("BS_RightMenu_List").value += ("," + inID);
  }

  //�õ���ǰ��ʾ�Ĳ˵���
  this.getThisRMArea = function(){
    return this.itemAreaList[this.showItemAreaIndex];
  }

  //�õ���ǰ��ʾ�Ĳ˵���
  this.getThisRMItem = function(){
    return this.getThisRMArea().itemList[this.clickItemIndex];
  }

}
