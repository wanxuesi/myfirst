function BSTreeView(name,fomename,showType,style,father){
  this.name = name||"BSTreeView";
  this.fomename=fomename||"frmBusiness";
  this.showType = showType||false;
  this.style = style||"";
  this.nodeList = new Array();
  this.rootList = new Array();
  this.thisDeepNo = 0;
  this.htmlStr = "";
  this.clickID = -1;
  this.isFinish = true;
  this.rmObj = null;
  this.imagePath = "/"+getDomain()+"/common/images/";//缺省的路径
  this.showLine = true;//是否显示连线
  this.showAddImg = true;//是否显示+-
  this.showNodeImg = true;//是否现实图片
  this.father = father||"";
  this.freshJsfun = "";
  this.thisOppNode = null;//记录当前即点即查的节点
  this.isBinaryStar = false;
  //添加右键
  try{
    this.rmObj = eval(this.name + "_rm");//右键菜单
  }
  catch(e){
    this.rmObj = null;
  }

  /*****get/set方法开始*****/
  //设置刷新状态时的方法(限于BinaryStar框架使用)
  this.setFreshJsfun = function(inFreshJsfun){
    this.freshJsfun = inFreshJsfun;
  }

  //设置图片的路径
  this.setImagesPath = function(inPath){
    this.imagePath = inPath;
  }

  //是否显示线
  this.setShowLine = function(showFlg){
    this.showLine = showFlg;
  }

  //是否显示节点图片
  this.setShowNodeImg = function(showNodeImgFlg){
    this.showNodeImg = showNodeImgFlg;
  }

  //是否显示+-图片
  this.setShowAddImg = function(showAddImgFlg){
    this.showAddImg = showAddImgFlg;
  }

  //设置右键菜单
  this.setRigthMenu = function (inRmObj){
    this.rmObj = rmOBJ;
  }

  //得到树的深度
  this.getDeep = function (){
  	return this.thisDeepNo;
	}
  /*****get/set方法结束*****/

  /*****节点操作方法开始*****/
  //添加跟节点
  this.addRootNode = function (name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg){
    return this.addNode(-1, 0, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg);
  }

  //添加节点
  this.addNode = function(pid, deepID, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg){
    var inNode = new BSNode(this.nodeList.length, pid, deepID, this.name, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex);
    //设置图片
    if (openImg != null && openImg.Trim() != ""){
      inNode.openImg = openImg;
    }
    if (closeImg != null && closeImg.Trim() != ""){
      inNode.closeImg = closeImg;
    }
    if (nodeImg != null && nodeImg.Trim() != ""){
      inNode.nodeImg = nodeImg;
    }
    if (pid >= 0){
      this.nodeList[pid].addChildItem(this.nodeList.length);
    }
    else{
      this.rootList.length++;
      this.rootList[this.rootList.length-1] = this.nodeList.length;
    }
    this.nodeList.length++;
    this.nodeList[this.nodeList.length-1] = inNode;

    if (deepID > this.thisDeepNo){
      this.thisDeepNo = deepID;
    }
    if (document.getElementById(this.name+"_main") != null){
      //树已生成时动态添加
      this.showAddNode(inNode.id);
    }
    return inNode;
  }

  //添加单个节点对象(用于调度)
  this.addOneNode = function (pid, inNode){
    if (inNode == null){
      alert("没有可添加的节点");
      return;
    }
    if (pid >= 0){
      this.nodeList[pid].addChildItem(inNode.id);
    }
    else{
      this.rootList.length++;
      this.rootList[this.rootList.length-1] = inNode.id;
    }
    inNode.pid = pid;
    //重设深度
    inNode.deepID = this.nodeList[pid].deepID+1;
    if (inNode.deepID > this.thisDeepNo){
      this.thisDeepNo = inNode.deepID;
    }
    if (document.getElementById(this.name+"_main") != null){
      //树已生成
      this.showAddNode(inNode.id);
    }
    return inNode;
  }

  //更新节点
  this.updateNode = function (id, inNode){
    if (inNode == null){
      alert("没有可更改的节点");
      return;
    }
    var thisNode = this.nodeList[id];
    inNode.pid = thisNode.pid;
    //重设深度
 	  inNode.id = thisNode.id;
    inNode.deepID = thisNode.pid.deepID;
    this.nodeList[id] = inNode;
    if (document.getElementById(this.name+"_main") != null){
      //树已生成
	  var thisdiv = document.getElementById(this.name+"_"+inNode.id+"_node");
	  //重画父节点
	  var strTemp = "";
	  strTemp += "<nobr>";
	  strTemp += this.DrawLink(inNode.id);
	  strTemp += this.DrawShowStr(inNode.id);
	  strTemp += "</nobr>";
	  thisdiv.innerHTML = strTemp;
    }
    return inNode;
  }

  //打开父亲节点
  this.openParent = function(id){
    if (id >= 0){
      var node = this.nodeList[id];
      var div = document.getElementById(this.name+"_"+id+"_div");
      var thisdiv = document.getElementById(this.name+"_"+node.id+"_node");
      div.style.display = "block";
      node.isOpen = true;
      node.isDoOpen = true;
      //重画父节点
      var strTemp = "";
      strTemp += "<nobr>";
      strTemp += this.DrawLink(node.id);
      strTemp += this.DrawShowStr(node.id);
      strTemp += "</nobr>";
      thisdiv.innerHTML = strTemp;
      this.openParent(node.pid);
    }
  }

  //动态添加节点
  this.showAddNode = function(id){
    var node = this.nodeList[id];
    //得到父节点对象
    this.openParent(node.pid);
    var p_node = this.nodeList[node.pid];
    //得到父节点的元素
    var div = document.getElementById(this.name+"_"+p_node.id+"_div");

    //重画兄弟和本身
    strTemp = "";
    if (p_node.childList.length > 0){
      var prevNode = node.prev();
      if (prevNode != null){
        div.removeChild(document.getElementById(this.name+"_"+prevNode.id+"_node"));
        div.removeChild(document.getElementById(this.name+"_"+prevNode.id+"_div"));
        strTemp += this.DrawNode(prevNode.getId());
      }
      strTemp += this.DrawNode(id);
      div.innerHTML = (div.innerHTML+strTemp);

    }
    if (this.isBinaryStar){
      this.optionFrame(true);
    }
  }

  //节点打开操作
  this.doOpen = function(id){
    if (!this.isFinish){
      alert("上一动作尚未完成，或出现错误！");
      return;
    }
    this.isFinish = false;
    var node = this.nodeList[id];
    var div = document.getElementById(this.name+"_"+id+"_div");
    var str = document.getElementById(this.name+"_"+id);
    var imgo = document.getElementById(this.name+"_"+id+"_o");
    var imgf = document.getElementById(this.name+"_"+id+"_f");
    try{
      var thisForm = eval(this.fomename);
    }
    catch(e){
      var thisForm = null;
    }
    if (this.isBinaryStar){
      this.optionFrame(true);
      thisForm.target = "BSTree_frame";
    }

    if (node.isOpen){
      div.style.display = "none";
      if (imgo!=null){
        imgo.src = imgo.src.replace("minus.gif", "plus.gif");
    	}
      if (imgf!=null){
	      imgf.src = imgf.src.replace(node.openImg, node.closeImg);
    	}
      node.isOpen = false;
      this.setTreeNodeID(id);
      if (this.getChgFlg(id)){
        str.focus();
        this.changeClickID(id);
      }
      if (this.showType && node.isDoOpen){
        this.setTreeNodeID(id);
        if (this.isBinaryStar && this.freshJsfun != null && this.freshJsfun.Trim() != ""){
          try{
            eval(this.freshJsfun);
          }
          catch(e){
            alert("*^_^*恭喜你中招了!\n\r "+e.name+":"+e.message+" \n\r刷新节点状态的方法\n\r"+this.freshJsfun+"\n\r发生严重错误！");
          }
        }
      }
      this.isFinish = true;
    }
    else{
      if (node.childList.length > 0){
        div.style.display = "block";
				node.isOpen = true;
      }
      if (imgf!=null){
	      imgf.src = imgf.src.replace(node.closeImg, node.openImg);
    	}
      if (imgo!=null){
        imgo.src = imgo.src.replace("plus.gif", "minus.gif");
    	}
      this.setTreeNodeID(id);
      //判断是否是即点即查
      if (this.showType && !node.isDoOpen && node.childList.length==0){
        node.isDoOpen = true;
        if (node.openjs != ""){
          //弹出提示框
          showBSMinDialogBox("Loadiong……", "");
          try{
            eval(node.openjs);
          }
          catch(e){
            alert("*^_^*恭喜你中招了!\n\r "+e.name+":"+e.message+" \n\r打开节点的方法\n\r"+node.openjs+"\n\r发生严重错误！");
            this.isFinish = true;
          }
        }
        else if(node.childList.length == 0){
          if (this.isBinaryStar && this.freshJsfun != null && this.freshJsfun.Trim() != ""){
            try{
              eval(this.freshJsfun);
            }
            catch(e){
              alert("*^_^*恭喜你中招了!\n\r "+e.name+":"+e.message+" \n\r刷新节点状态的方法 "+this.freshJsfun+" \n\r发生严重错误！");
              this.isFinish = true;
            }
          }
          this.isFinish = true;
        }
      }
      else if (this.showType && node.isDoOpen){
        if (this.isBinaryStar && this.freshJsfun != null && this.freshJsfun.Trim() != ""){
          try{
            eval(this.freshJsfun);
          }
          catch(e){
            alert("*^_^*恭喜你中招了!\n\r "+e.name+":"+e.message+" \n\r刷新节点状态的方法 "+this.freshJsfun+" \n\r发生严重错误！");
            this.isFinish = true;
          }
        }
        this.isFinish = true;
      }
      else{
        this.isFinish = true;
      }
      //调整父元素的滚动条
      var mainDiv = document.getElementById(this.name+"_main");
      var pNode = mainDiv.parentNode;
      if (pNode != null){
      	var curH = div.offsetTop - pNode.scrollTop;//该节点在相对高度
      	var difH = pNode.offsetHeight-curH-(str.offsetHeight);
      	var addH = 0;
		if ((curH + div.offsetHeight) > pNode.offsetHeight){
			addH = div.offsetHeight-difH;
		}
		if ((curH-addH) < 0){
			addH = curH-(str.offsetHeight+2);
		}
		pNode.scrollTop = pNode.scrollTop+addH;
      }
    }
  }

  //删除节点
  this.removeNode = function(inId){
    if (inId >= 0 && inId < this.nodeList.length){
      this.changeClickID("-1");
      this.setTreeNodeID("-1");
      var thisNode = this.nodeList[inId];
      if (document.getElementById(this.name+"_"+inId+"_node")!= null){
        var pnodeElm = document.getElementById(this.name+"_"+inId+"_node").parentNode;
        pnodeElm.removeChild(document.getElementById(this.name+"_"+inId+"_node"));
        pnodeElm.removeChild(document.getElementById(this.name+"_"+inId+"_div"));
      }

      //父亲节点的重画
      var prevNode = thisNode.prev();
      var nextNode = thisNode.next()
      thisNode.deleteOneChildNode();
      var p_node = this.nodeList[thisNode.pid];
      var div = document.getElementById(this.name+"_"+p_node.id+"_div");
      var thisdiv = document.getElementById(this.name+"_"+p_node.id+"_node");
      var strTemp = "";
      strTemp += "<nobr>";
      strTemp += this.DrawLink(p_node.id);
      strTemp += this.DrawShowStr(p_node.id);
      strTemp += "</nobr>";
      thisdiv.innerHTML = strTemp;

      //重画兄弟和本身
      strTemp = "";
      div.style.display = "none";
      if (p_node.childList.length > 0){
        div.style.display = "block";
        if (prevNode != null){
          strTemp = "";
          var prevDiv = document.getElementById(this.name+"_"+prevNode.id+"_node");
          strTemp += "<nobr>";
          strTemp += this.DrawLink(prevNode.id);
          strTemp += this.DrawShowStr(prevNode.id);
          strTemp += "</nobr>";
          prevDiv.innerHTML = strTemp;
        }
        if (nextNode != null){
          strTemp = "";
          var nextDiv = document.getElementById(this.name+"_"+nextNode.id+"_node");
          strTemp += "<nobr>";
          strTemp += this.DrawLink(nextNode.id);
          strTemp += this.DrawShowStr(nextNode.id);
          strTemp += "</nobr>";
          nextDiv.innerHTML = strTemp;
        }
      }
    }
  }

  //删除根节点
  this.removeRoot = function(){
    if (document.getElementById(this.name+"_main")!= null){
      var pnodeElm = document.getElementById(this.name+"_main").parentNode;
      pnodeElm.removeChild(document.getElementById(this.name+"_main"));
      this.nodeList = new Array();
      this.rootList = new Array()
      this.thisDeepNo = 0;
      this.htmlStr = "";
      this.clickID = -1;
      this.isFinish = true;
      this.rmObj = null;
    }
  }
  /*****节点操作方法结束*****/

  /*****树的绘制方法开始*****/
  //画树
  this.DrawTree = function(in_showType){
    var type = in_showType || false;
    this.htmlStr = "<div id=\""+this.name+"_main\">";
    this.htmlStr += this.initTree();
    for (var i=0; i<this.rootList.length; i++){
      if (!this.rootList[i].isDelete){
        this.htmlStr += this.DrawNode(this.rootList[i]);
      }
    }
    //alert(this.htmlStr);
    this.htmlStr += "</div>";
    if (type){
      if (this.father != "" && document.getElementById(this.father)!=null){
      	var fatObj = document.getElementById(this.father);
       	fatObj.innerHTML = this.htmlStr;
      }
      else{
 	     document.writeln(this.htmlStr);
      }
      this.setTreeNodeID("-1");
    }
    return this.htmlStr;
  }

  //画节点
  this.DrawNode = function(id){
    var strTemp = "";
    var node = this.nodeList[id];
    var display = "none";
    if (node.isOpen){
      display = "block";
    }
    //Draw this node
    strTemp += "<div class=\"tree_node\" id=\""+(this.name+"_"+id+"_node")+"\"><nobr>";
    strTemp += this.DrawLink(id);
    strTemp += this.DrawShowStr(id);
    strTemp += "</nobr></div>";
    //Draw children
    strTemp += "<div id=\""+(this.name+"_"+id+"_div")+"\" style=\"display:"+display+"\">";
    if (node.childList.length > 0){
      for (var i=0; i<node.childList.length; i++){
        if (!node.childList[i].isDelete){
          strTemp += this.DrawNode(node.childList[i]);
        }
      }
    }
    strTemp += "</div>";
    return strTemp;
  }

  //画线
  this.DrawLink = function(id){
    var strTemp = "";
    var node = this.nodeList[id];
    var oi = "Lplus.gif";
    var of = "close.gif";
    var mclick = "";

    if (!this.showAddImg){
    	this.showLine = false;
    }
    //递归画上一层的图片
    if (node.pid >= 0){
      strTemp += this.DrawPLink(node.pid);
    }

    //设置有孩子节点的图片设置;
    if (node.childList.length > 0 || (this.showType && node.isDoOpen==false && node.openjs!="")){
      if (node.isOpen){
        of = "open.gif";
        oi = "minus.gif";
        if (node.openImg != null && node.openImg != ""){
          of = node.openImg;
        }
      }
      else{
        of = "close.gif";
        oi = "plus.gif";
        if (node.closeImg != null && node.closeImg != ""){
          of = node.closeImg;
        }
      }
      if (node.pid < 0){//root
        if (this.rootList[this.rootList.length-1] != id){
          oi = ("T"+oi);
        }
        else{
          oi = ("L"+oi);
        }
      }
      else{
        if (this.nodeList[node.pid].childList[this.nodeList[node.pid].childList.length-1] != id){
          oi = ("T"+oi);
        }
        else{
          oi = ("L"+oi);
        }
      }
    }
    else{
    	//设置无孩子节点的图片
      if (node.pid >= 0){
        if (this.showLine){
          if (this.nodeList[node.pid].childList[this.nodeList[node.pid].childList.length-1] != id){
            oi = "T.gif";
          }
          else{
            oi = "L.gif";
          }
        }
	    	else{
					oi = "empty.gif";
		    }
      }
      else {
      	if (this.showLine){
	        if (this.rootList[this.rootList.length-1] == id){
	        	oi = "L.gif";
	        }
	        else if (this.rootList[0] == id){
	        	oi = "P.gif";
	        }
	        else{
	        	oi = "T.gif";
	        }
	      }
	    	else{
					oi = "empty.gif";
		    }
      }
      of = "jsdoc.gif";
      if (node.nodeImg != null && node.nodeImg != ""){
        of = node.nodeImg;
      }
    }
    //画+-图片
    if (this.showAddImg){
      strTemp += "<img class=\"node_img\" style=\"cursor:hand;\" onclick=\""+this.name+".doOpen("+id+")\" id=\""+this.name+"_"+id+"_o\" align=\"absmiddle\" alt=\"\" src=\""+this.imagePath+oi+"\" border=\"0\"/>";
    }
    //画节点图片
    if (this.showNodeImg){
	    strTemp += "<img align=\"absmiddle\" style=\"cursor:hand;\" id=\""+this.name+"_"+id+"_f\" alt=\"\" onclick=\""+this.name+".doOpen("+id+")\" src=\""+this.imagePath+of+"\" border=\"0\"/>";
    }
    return strTemp;
  }

  //画父节点的线
  this.DrawPLink = function(id){
    var strTemp = "";
    var node = this.nodeList[id];
    //Draw pid
    if (node.pid >= 0){
      strTemp += this.DrawPLink(node.pid);
    }
    if (!this.showLine){
      strTemp += "<img align=\"absmiddle\" alt=\"\" src=\""+this.imagePath+"empty.gif\" border=\"0\"/>";
    }
    else{
      if (node.pid < 0){
        if (this.rootList[this.rootList.length-1] != id){
          strTemp += "<img align=\"absmiddle\" alt=\"\" src=\""+this.imagePath+"I.gif\" border=\"0\"/>";
        }
        else{
          strTemp += "<img align=\"absmiddle\" alt=\"\" src=\""+this.imagePath+"empty.gif\" border=\"0\"/>";
        }
      }
      else{
        if (this.nodeList[node.pid].childList[this.nodeList[node.pid].childList.length-1] != id){
          strTemp += "<img align=\"absmiddle\" alt=\"\" src=\""+this.imagePath+"I.gif\" border=\"0\"/>";
        }
        else{
          strTemp += "<img align=\"absmiddle\" alt=\"\" src=\""+this.imagePath+"empty.gif\" bswebrder=\"0\"/>";
        }
      }
    }
    return strTemp;
  }

  //输出文字
  this.DrawShowStr = function(id){
    var node = this.nodeList[id];
    var tclass = "tree_a";
    if (node.id == this.clickID){
    	tclass = "tree_node_onfocus";
    }
    var strTemp = "&nbsp;<a title=\""+node.getTitle()+"\" id=\""+(this.name+"_"+id)+"\"  href=\"#\" class=\""+tclass+"\" onfocus=\"this.blur();\" ";
    strTemp += "onmousedown=\""+this.name+".doJSFun("+id+")\" ";
    strTemp += "onclick=\"window.event.returnValue=false;\" ";
    if (this.rmObj != null){
      strTemp += "onmouseup=\""+this.name+".showRM("+id+")\"";
    }
    strTemp += ">"+node.showStr+"</a>";
    return strTemp;
  }
  /*****树的绘制方法结束*****/

  /*****设置树节点状态方法开始*****/
  //设置当前激活的节点
  this.changeClickID = function(id){
    if (!(this.clickID < 0 || this.clickID == id)){
      var str = document.getElementById(this.name+"_"+this.clickID);
        str.className = "tree_node_onblur";
    }
    if (id >= 0 && id < this.nodeList.length){
      var str = document.getElementById(this.name+"_"+id);
      str.className = "tree_node_onfocus";
    }
    this.clickID = id;
  }

  //节点收起时，判断是否存在激活状态的孩子节点
  this.getChgFlg = function(id){
    var node = this.nodeList[id];
    for (var i=0; i<node.childList.length; i++){
      var cnode_id = node.childList[i];
      if (this.getChgFlg(cnode_id)){
        return true;
      }
      else if (this.clickID == cnode_id){
        return true;
      }
    }
    return false;
  }
  /*****设置树节点状态方法结束*****/

  /*****树的附加动作开始*****/
  //执行点击节点的JS方法。
  this.doJSFun = function(id){
    window.event.cancelBubble=true;
    this.changeClickID(id);
    this.setTreeNodeID(id);
    if(window.event.button == 1){
      var node = this.nodeList[id];
      if (node.jsfun != ""){
        try{
          eval(node.jsfun);
        }
        catch(e){
          //alert("*^_^*恭喜你中招了!\n\r "+e.name+":"+e.message+" \n\r点击节点的方法: "+node.jsfun+" \n\r发生严重错误！");
         	this.isFinish = true;
        }
      }
    }
  }

  //初始化树
  this.initTree = function(){
    var strTemp = "";
    if (document.getElementById(this.name+"_thisTreeNodeID") == null){
      strTemp += "<input type=\"hidden\" id=\""+this.name+"_thisTreeNodeID\" name=\""+this.name+"_thisTreeNodeID\" value=\"\">";
      strTemp += "<input type=\"hidden\" name=\"thisTreeName\" value=\"\">";
    }
    return strTemp;
  }

  //展现右键
  this.showRM = function (id){
    if(window.event.button == 2) {
      var node = this.nodeList[id];
      if (this.rmObj!=null && this.rmObj.itemAreaList.length > node.rmAreaIndex){
        this.rmObj.doRightMenu(node.rmAreaIndex);
      }
    }
  }

  //创建或删除内部桢(用于BinaryStar的即点即查树)
  this.optionFrame = function (optionType){
    if (!this.showType){
      return true;
    }
    var frame = document.getElementById("BSTree_frame_tab");
    var tree = document.getElementById(this.name+"_main");
    if (tree == null){
      return false;
    }
    if (optionType){
      if (frame == null){
        //创建
        var tree_tab = document.createElement("table");
        tree_tab.id = "BSTree_frame_tab";
        tree_tab.style.cssText = "width:1px;height:1px;";
        var row = tree_tab.insertRow(0);
        var sell = row.insertCell(0);
        sell.style.cssText = "width:1px;height:1px";
        sell.innerHTML = "<iframe name=\"BSTree_frame\" id=\"BSTree_frame\" src=\"\" style=\"margin:0px\" height=\"1px\" width=\"1px\" scrolling=\"no\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>";
        tree.appendChild(tree_tab);
      }
    }
    else {
      if (frame != null){
        //删除
        tree.removeChild(frame);
      }
    }
    return true;
  }

  //设置选中的操作
  this.doTreeNodeRef = function (){
    var node = this.nodeList[this.clickID];
    //刷新
    node.childList = new Array();
    var thisForm = eval(this.fomename);
    if (this.isBinaryStar){
      this.optionFrame(true);
      thisForm.target = "BSTree_frame";
    }
    eval(node.openjs);
  }
  /*****树的附加动作结束*****/

  /*****用户使用JS函数开始*****/
  //设置指定节点的选中状态
  this.setNodeActiveById = function (inId){
    if (inId == null){
      alert("请输入一个数字！");
      return;
    }
    if (inId >= 0 && inId < this.nodeList.length){
      var node = this.nodeList[inId];
      this.openParent(node.pid);
      this.changeClickID(inId);
      this.setTreeNodeID(inId);
      return this.nodeList[inId];
    }
    return null;
  }
  this.setNodeActiveByName = function (inName){
    if (inName == null || inName == ""){
      alert("请输入正确的节点名！");
      return ;
    }
    var node = this.getNodeByName(inName);
    if (node != null){
      this.openParent(node.id);
      this.changeClickID(node.id);
      this.setTreeNodeID(node.id);
    }
    return node;
  }

  //打开/关闭指定节点
  this.expandById = function (inId){
    if (inId == null){
      alert("请输入一个数字！");
      return ;
    }
    if (inId >= 0 && inId < this.nodeList.length){
	    this.openParent(this.nodeList[inId].pid);
      this.doOpen(inId);
      return this.nodeList[inId];
    }
    return null;
  }
  this.expandByName = function (inName){
    if (inName == null || inName == ""){
      alert("请输入正确的节点名！");
      return ;
    }
    var node = this.getNodeByName(inName);
    if (node != null){
      this.expandById(node.id);
    }
    return node;
  }

  //打开指定节点
  this.openById = function (inId){
    if (inId == null){
      alert("请输入一个数字！");
      return ;
    }
    if (inId >= 0 && inId < this.nodeList.length){
    	if (!this.nodeList[inId].openFlag()){
	      this.openParent(this.nodeList[inId].pid);
	      this.doOpen(inId);
    	}
      return this.nodeList[inId];
    }
    return null;
  }
  this.openByName = function (inName){
    if (inName == null || inName == ""){
      alert("请输入正确的节点名！");
      return ;
    }
    var node = this.getNodeByName(inName);
    if (node != null){
	     this.openById(node.id);
    }
    return node;
  }

  //关闭指定节点
  this.closeById = function (inId){
    if (inId == null){
      alert("请输入一个数字！");
      return ;
    }
    if (inId >= 0 && inId < this.nodeList.length){
    	if (this.nodeList[inId].openFlag()){
	      this.doOpen(inId);
    	}
      return this.nodeList[inId];
    }
    return null;
  }
  this.closeByName = function (inName){
    if (inName == null || inName == ""){
      alert("请输入正确的节点名！");
      return ;
    }
    var node = this.getNodeByName(inName);
    if (node != null){
	     this.closeById(node.id);
    }
    return node;
  }

  //设置重复点击判断(用户即点即查)
  this.setFinish = function(flg){
    if (flg == null || flg == "false" || !flg){
      this.isFinish = false;
      if (this.showType){
        this.thisOppNode = this.getSelectNode();
      }
    }
    else{
      this.isFinish = true;
      closeDialog();
      if (this.showType && this.thisOppNode.childList.length <= 0){
        this.thisOppNode.updateNode(this.thisOppNode);
      }
      this.thisOppNode = null;
    }
  }
  this.getFinish = function (){
    return this.isFinish;
  }

  //根据节点显示的内容模糊查询
  this.searcNodesByText = function (inText){
    if (inText == null || inText == ""){
      alert("请输入要匹配的字符串！");
      return ;
    }
    var resNodes = new Array();
    for (var i=0; i<this.nodeList.length; i++){
      if (this.nodeList[i].showStr.Trim().indexOf(inText) >= 0){
        resNodes.length++;
        resNodes[resNodes.length-1] = this.nodeList[i];
      }
    }
    if (resNodes.length <= 0){
      alert("没有找到匹配的节点！");
    }
    return resNodes;
  }

  //根据节点关键字模糊查询
  this.searcNodesByName = function (inName){
    if (inName == null || inName == ""){
      alert("请输入要匹配的字符串！");
      return ;
    }
    var resNodes = new Array();
    for (var i=0; i<this.nodeList.length; i++){
      if (this.nodeList[i].name.Trim().indexOf(inName) >= 0){
        resNodes.length++;
        resNodes[resNodes.length-1] = this.nodeList[i];
      }
    }
    if (resNodes.length <= 0){
      alert("没有找到匹配的节点！");
    }
    return resNodes;
  }

  //根据节点关键字检索节点
  this.getNodeByName = function (inName){
    for (var i=0; i<this.nodeList.length; i++){
      if (this.nodeList[i].getName() == inName){
        return this.nodeList[i];
      }
    }
    return null;
  }

  //根据节点索引检索节点
  this.getNodeById = function (inId){
    if (inId >= 0 && inId < this.nodeList.length){
      return this.nodeList[inId];
    }
    return null;
  }

  //设置当前选中树节点
  this.setTreeNodeID = function (in_id){
    document.getElementById(this.name+"_thisTreeNodeID").value = in_id;
    document.getElementById("thisTreeName").value = this.name;
  }

  this.getSelectNode = function (){
    if (this.getTreeNodeID() >= 0 && this.getTreeNodeID() < this.nodeList.length){
      return this.nodeList[this.getTreeNodeID()];
    }
    else{
      alert("没有选中的节点！");
      return null;
    }
  }

  this.getTreeNodeID = function (){
    return document.getElementById(this.name+"_thisTreeNodeID").value;
  }

  //得到当前选中的树节点ID
  this.getTreeNodePid = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].pid;
    return reStr;
  }

  //得到当前选中的树节点的附属参数
   this.getTreeNodePara = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].paras;
    return reStr;
  }

  //得到当前选中的树节点的名称
  this.getTreeNodeName = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].name;
    return reStr;
  }

  //得到当前选中的树节点的显示内容
  this.getTreeNodeShowStr = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].showStr;
    return reStr;
  }

  //得到当前选中的树节点的JS动作名
  this.getTreeNodeJsFun = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].jsfun;
    return reStr;
  }

  //根据附属参数名得到当前选中的树节点的对应附属参数值
  this.getTreeOneParaByName = function (in_paraName){
    var reStr = this.getTreeNodePara();
    if (reStr != null && reStr != ""){
      var temp1 = reStr;
      var a = temp1.split("&");
      for(var i=0;i<a.length;i++){
        var t = a[i].split("=");
        if (t[0] == in_paraName){
          return t[1];
        }
      }
    }
  }
  /*****用户使用JS函数结束*****/
}

function BSNode(id, pid, deepID, treeName, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex){
  this.id=id;//节点索引。
  this.pid=pid;//父节点索引,为-1表示是根。
  this.deepID=deepID;//节点深度。
  this.showStr=showStr||"BSNode_"+this.id;//节点显示的文字内容
  this.jsfun=jsfun||""//该节点点击的JS操作
  this.openjs=openjs||""//即点即查，该节点点击展开的JS操作。
  this.treeName=treeName||"BSTreeView";//树对象实例名。
  this.name=name?name:"BSNode";//节点名。
  this.paras=paras||"";//节点其他参数。
  this.childList = new Array();
  this.isOpen = isOpen||false;
  this.isDoOpen = isDoOpen||false;
  this.openImg = "open.gif";
  this.closeImg = "close.gif";
  this.nodeImg = "jsdoc.gif";
  this.rmAreaIndex = rmAreaIndex;
  this.body = null;
  this.isDelete = false;
  this.title = "";//节点的title

  /*****get/set方法开始*****/
  this.getId = function (){
    return this.id;
  }
  this.setId = function (inId){
    this.id = inId;
  }

  this.getName = function (){
    return this.name;
  }
  this.setName = function (inName){
    this.name = inName;
  }

  this.setBody = function (inBody){
    this.body = inBody;
  }
  this.getBody = function (){
    return this.body;
  }

	//更新节点显示的文字
  this.setShowStr = function (inStr){
    this.showStr = inStr;
    if (document.getElementById(this.treeName+"_"+this.id)!= null){
      document.getElementById(this.treeName+"_"+this.id).innerHTML = this.showStr;
    }
  }
  this.getShowStr = function (){
    return this.showStr;
  }

  this.setTitle = function(inTitle){
  	this.title = inTitle;
  }
  this.getTitle = function(){
  	return this.title;
  }

  this.setRmAreaIndex = function(inIndex){
  	this.rmAreaIndex = inIndex;
  }

  //得到右键菜单
  this.getRmArea = function(){
    var tempTree = eval(this.treeName);
  	if ((tempTree.rmObj!=null && tempTree.itemAreaList.length>0) && (this.rmAreaIndex>=0 && this.rmAreaIndex<tempTree.itemAreaList.length)){
	  	return tempTree.itemAreaList[this.rmAreaIndex];
  	}
  	return null;
  }

  this.openFlag = function(){
  	return this.isOpen;
  }

  this.getDeep = function(){
  	return this.deepID;
  }

  /*****get/set方法结束*****/

  /*****节点操作方法开始*****/
  this.addChildItem = function(id){
    this.childList.length++;
    this.childList[this.childList.length - 1] = id;
  }

	//更新一个节点
  this.updateNode = function (inNode){
    var tempTree = eval(this.treeName);
    return tempTree.updateNode(this.id, inNode);
  }

  //添加子节点
  this.addNode = function(name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg){
    var tempTree = eval(this.treeName);
    return tempTree.addNode(this.id, (this.deepID+1), name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg);
  }

  this.addOneNode = function(inId){
     var tempTree = eval(this.treeName);
     return tempTree.addOneNode(this.id, tempTree.getNodeById(inId));
  }

  this.deleteOneChildNode = function(){
    var tempTree = eval(this.treeName);
    var p=-1;
    if (this.pid < 0){
      //单个根节点（暂不提供）
    }
    else{
      var p_node = tempTree.nodeList[this.pid];
      for (var i=0; i<p_node.childList.length; i++){
        //得到孩子位置
        if (p_node.childList[i] == this.id){
          p = i;
        }
        if (p >= 0 && i <= p_node.childList.length-2){
          p_node.childList[i] = p_node.childList[i+1];
        }
      }
      if (p >= 0){
        p_node.childList.length--;
      }
    }
  }

  //删除本节点
  this.remove = function(){
    var tempTree = eval(this.treeName);
    tempTree.removeNode(this.id);
  }

  //激活该节点
  this.setNodeActive = function (){
    var tempTree = eval(this.treeName);
    tempTree.setNodeActiveById(this.id);
  }

  //打开/关闭本节点
  this.expand = function (){
    var tempTree = eval(this.treeName);
    tempTree.expandById(this.id);
  }

  //打开本节点
  this.open = function (){
  	if (!this.isOpen){
	    var tempTree = eval(this.treeName);
	    tempTree.openById(this.id);
  	}
  }
  //关闭本节点
  this.close = function (){
  	if (this.isOpen){
	    var tempTree = eval(this.treeName);
	    tempTree.closeById(this.id);
  	}
  }

  //删除本节点的所有孩子节点
  this.removeAllChildren = function(){
    var tempTree = eval(this.treeName);
    var t_length = this.childList.length
    for (var i=0; i<t_length; i++){
      tempTree.removeNode(this.childList[0]);
    }
    this.setNodeActive();
  }
  /*****节点操作方法开始*****/

  /*****亲属节点开始*****/
  //得到上一个兄弟
  this.prev = function (){
    var tempTree = eval(this.treeName);
    var p_node = tempTree.nodeList[this.pid];
    for (var i=0; i<p_node.childList.length; i++){
      if (p_node.childList[i] == this.id && i>0){
          return tempTree.nodeList[p_node.childList[i-1]];
      }
    }
    return null;
  }

  //得到下一个兄弟
  this.next = function (){
    var tempTree = eval(this.treeName);
    var p_node = tempTree.nodeList[this.pid];
    for (var i=0; i<p_node.childList.length; i++){
      if (p_node.childList[i] == this.id && i<p_node.childList.length-1){
          return tempTree.nodeList[p_node.childList[i+1]];
      }
    }
    return null;
  }

  //得到第一个兄弟
  this.first = function (){
    var tempTree = eval(this.treeName);
    var p_node = tempTree.nodeList[this.pid];
    return tempTree.nodeList[p_node.childList[0]];
  }

  //得到最后一个兄弟
  this.last = function (){
    var tempTree = eval(this.treeName);
    var p_node = tempTree.nodeList[this.pid];
    return tempTree.nodeList[p_node.childList[p_node.childList.length-1]];
  }

  //得到父亲
  this.parent = function (){
    var tempTree = eval(this.treeName);
    if (this.pid >= 0){
    	return tempTree.nodeList[this.pid];
    }
    return null;
  }

  //得到孩子节点集合
  this.children = function(){
    var tempTree = eval(this.treeName);
  	var tempList = new Array(this.childList.length);
  	for (var i=0; i<this.childList.length; i++){
  		tempList[i] = tempTree.nodeList[this.childList[i]];
  	}
  	return tempList;
  }
  /*****亲属节点结束*****/

  /*****用户使用JS函数开始*****/
  //根据附属参数名得到本节点的对应附属参数值
  this.getTreeOneParaByName = function (paraName){
    if (this.paras != null && this.paras != ""){
      var temp1 = this.paras;
      var a = temp1.split("&");
      for(var i=0;i<a.length;i++){
        var t = a[i].split("=");
        if (t[0] == paraName){
          return t[1];
        }
      }
    }
  }

  //根据节点显示的内容模糊查询子节点
  this.searcNodesByText = function (inText){
    if (inText == null || inText == ""){
      alert("请输入要匹配的字符串！");
      return ;
    }
    var tempTree = eval(this.treeName);
    var resNodes = new Array();
    this.searchChildrenNodeByText(resNodes, inText);
    if (resNodes.length <= 0){
      alert("没有找到匹配的节点！");
    }
    return resNodes;
  }

  //根据节点的关键字模糊查询
  this.searcNodesByName = function (inName){
    if (inName == null || inName == ""){
      alert("请输入要匹配的字符串！");
      return ;
    }
    var tempTree = eval(this.treeName);
    var resNodes = new Array();
    this.searchChildrenNodeByName(resNodes, inName);
    if (resNodes.length <= 0){
      alert("没有找到匹配的节点！");
    }
    return resNodes;
  }
  /*****用户使用JS函数结束*****/

  //查找匹配的孩子节点(私有)
  this.searchChildrenNodeByName = function(resNodes, inName){
    var tempTree = eval(this.treeName);
    for (var i=0; i<this.childList.length; i++){
      var thisNode = tempTree.nodeList[this.childList[i]];
      if (thisNode.name.Trim().indexOf(inName) >= 0){
        resNodes.length++;
        resNodes[resNodes.length-1] = thisNode;
      }
      //查找孩子的孩子
      if (thisNode.childList.length > 0){
        thisNode.searchChildrenNodeByName(resNodes, inName);
      }
    }
  }

  //查找匹配的孩子节点(私有)
  this.searchChildrenNodeByText = function(resNodes, inText){
    var tempTree = eval(this.treeName);
    for (var i=0; i<this.childList.length; i++){
      var thisNode = tempTree.nodeList[this.childList[i]];
      if (thisNode.showStr.Trim().indexOf(inText) >= 0){
        resNodes.length++;
        resNodes[resNodes.length-1] = thisNode;
      }
      //查找孩子的孩子
      if (thisNode.childList.length > 0){
        thisNode.searchChildrenNodeByText(resNodes, inText);
      }
    }
  }
}
