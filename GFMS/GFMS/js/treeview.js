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
  this.imagePath = "/"+getDomain()+"/common/images/";//ȱʡ��·��
  this.showLine = true;//�Ƿ���ʾ����
  this.showAddImg = true;//�Ƿ���ʾ+-
  this.showNodeImg = true;//�Ƿ���ʵͼƬ
  this.father = father||"";
  this.freshJsfun = "";
  this.thisOppNode = null;//��¼��ǰ���㼴��Ľڵ�
  this.isBinaryStar = false;
  //����Ҽ�
  try{
    this.rmObj = eval(this.name + "_rm");//�Ҽ��˵�
  }
  catch(e){
    this.rmObj = null;
  }

  /*****get/set������ʼ*****/
  //����ˢ��״̬ʱ�ķ���(����BinaryStar���ʹ��)
  this.setFreshJsfun = function(inFreshJsfun){
    this.freshJsfun = inFreshJsfun;
  }

  //����ͼƬ��·��
  this.setImagesPath = function(inPath){
    this.imagePath = inPath;
  }

  //�Ƿ���ʾ��
  this.setShowLine = function(showFlg){
    this.showLine = showFlg;
  }

  //�Ƿ���ʾ�ڵ�ͼƬ
  this.setShowNodeImg = function(showNodeImgFlg){
    this.showNodeImg = showNodeImgFlg;
  }

  //�Ƿ���ʾ+-ͼƬ
  this.setShowAddImg = function(showAddImgFlg){
    this.showAddImg = showAddImgFlg;
  }

  //�����Ҽ��˵�
  this.setRigthMenu = function (inRmObj){
    this.rmObj = rmOBJ;
  }

  //�õ��������
  this.getDeep = function (){
  	return this.thisDeepNo;
	}
  /*****get/set��������*****/

  /*****�ڵ����������ʼ*****/
  //��Ӹ��ڵ�
  this.addRootNode = function (name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg){
    return this.addNode(-1, 0, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg);
  }

  //��ӽڵ�
  this.addNode = function(pid, deepID, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex, openImg, closeImg, nodeImg){
    var inNode = new BSNode(this.nodeList.length, pid, deepID, this.name, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex);
    //����ͼƬ
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
      //��������ʱ��̬���
      this.showAddNode(inNode.id);
    }
    return inNode;
  }

  //��ӵ����ڵ����(���ڵ���)
  this.addOneNode = function (pid, inNode){
    if (inNode == null){
      alert("û�п���ӵĽڵ�");
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
    //�������
    inNode.deepID = this.nodeList[pid].deepID+1;
    if (inNode.deepID > this.thisDeepNo){
      this.thisDeepNo = inNode.deepID;
    }
    if (document.getElementById(this.name+"_main") != null){
      //��������
      this.showAddNode(inNode.id);
    }
    return inNode;
  }

  //���½ڵ�
  this.updateNode = function (id, inNode){
    if (inNode == null){
      alert("û�пɸ��ĵĽڵ�");
      return;
    }
    var thisNode = this.nodeList[id];
    inNode.pid = thisNode.pid;
    //�������
 	  inNode.id = thisNode.id;
    inNode.deepID = thisNode.pid.deepID;
    this.nodeList[id] = inNode;
    if (document.getElementById(this.name+"_main") != null){
      //��������
	  var thisdiv = document.getElementById(this.name+"_"+inNode.id+"_node");
	  //�ػ����ڵ�
	  var strTemp = "";
	  strTemp += "<nobr>";
	  strTemp += this.DrawLink(inNode.id);
	  strTemp += this.DrawShowStr(inNode.id);
	  strTemp += "</nobr>";
	  thisdiv.innerHTML = strTemp;
    }
    return inNode;
  }

  //�򿪸��׽ڵ�
  this.openParent = function(id){
    if (id >= 0){
      var node = this.nodeList[id];
      var div = document.getElementById(this.name+"_"+id+"_div");
      var thisdiv = document.getElementById(this.name+"_"+node.id+"_node");
      div.style.display = "block";
      node.isOpen = true;
      node.isDoOpen = true;
      //�ػ����ڵ�
      var strTemp = "";
      strTemp += "<nobr>";
      strTemp += this.DrawLink(node.id);
      strTemp += this.DrawShowStr(node.id);
      strTemp += "</nobr>";
      thisdiv.innerHTML = strTemp;
      this.openParent(node.pid);
    }
  }

  //��̬��ӽڵ�
  this.showAddNode = function(id){
    var node = this.nodeList[id];
    //�õ����ڵ����
    this.openParent(node.pid);
    var p_node = this.nodeList[node.pid];
    //�õ����ڵ��Ԫ��
    var div = document.getElementById(this.name+"_"+p_node.id+"_div");

    //�ػ��ֵܺͱ���
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

  //�ڵ�򿪲���
  this.doOpen = function(id){
    if (!this.isFinish){
      alert("��һ������δ��ɣ�����ִ���");
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
            alert("*^_^*��ϲ��������!\n\r "+e.name+":"+e.message+" \n\rˢ�½ڵ�״̬�ķ���\n\r"+this.freshJsfun+"\n\r�������ش���");
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
      //�ж��Ƿ��Ǽ��㼴��
      if (this.showType && !node.isDoOpen && node.childList.length==0){
        node.isDoOpen = true;
        if (node.openjs != ""){
          //������ʾ��
          showBSMinDialogBox("Loadiong����", "");
          try{
            eval(node.openjs);
          }
          catch(e){
            alert("*^_^*��ϲ��������!\n\r "+e.name+":"+e.message+" \n\r�򿪽ڵ�ķ���\n\r"+node.openjs+"\n\r�������ش���");
            this.isFinish = true;
          }
        }
        else if(node.childList.length == 0){
          if (this.isBinaryStar && this.freshJsfun != null && this.freshJsfun.Trim() != ""){
            try{
              eval(this.freshJsfun);
            }
            catch(e){
              alert("*^_^*��ϲ��������!\n\r "+e.name+":"+e.message+" \n\rˢ�½ڵ�״̬�ķ��� "+this.freshJsfun+" \n\r�������ش���");
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
            alert("*^_^*��ϲ��������!\n\r "+e.name+":"+e.message+" \n\rˢ�½ڵ�״̬�ķ��� "+this.freshJsfun+" \n\r�������ش���");
            this.isFinish = true;
          }
        }
        this.isFinish = true;
      }
      else{
        this.isFinish = true;
      }
      //������Ԫ�صĹ�����
      var mainDiv = document.getElementById(this.name+"_main");
      var pNode = mainDiv.parentNode;
      if (pNode != null){
      	var curH = div.offsetTop - pNode.scrollTop;//�ýڵ�����Ը߶�
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

  //ɾ���ڵ�
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

      //���׽ڵ���ػ�
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

      //�ػ��ֵܺͱ���
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

  //ɾ�����ڵ�
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
  /*****�ڵ������������*****/

  /*****���Ļ��Ʒ�����ʼ*****/
  //����
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

  //���ڵ�
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

  //����
  this.DrawLink = function(id){
    var strTemp = "";
    var node = this.nodeList[id];
    var oi = "Lplus.gif";
    var of = "close.gif";
    var mclick = "";

    if (!this.showAddImg){
    	this.showLine = false;
    }
    //�ݹ黭��һ���ͼƬ
    if (node.pid >= 0){
      strTemp += this.DrawPLink(node.pid);
    }

    //�����к��ӽڵ��ͼƬ����;
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
    	//�����޺��ӽڵ��ͼƬ
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
    //��+-ͼƬ
    if (this.showAddImg){
      strTemp += "<img class=\"node_img\" style=\"cursor:hand;\" onclick=\""+this.name+".doOpen("+id+")\" id=\""+this.name+"_"+id+"_o\" align=\"absmiddle\" alt=\"\" src=\""+this.imagePath+oi+"\" border=\"0\"/>";
    }
    //���ڵ�ͼƬ
    if (this.showNodeImg){
	    strTemp += "<img align=\"absmiddle\" style=\"cursor:hand;\" id=\""+this.name+"_"+id+"_f\" alt=\"\" onclick=\""+this.name+".doOpen("+id+")\" src=\""+this.imagePath+of+"\" border=\"0\"/>";
    }
    return strTemp;
  }

  //�����ڵ����
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

  //�������
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
  /*****���Ļ��Ʒ�������*****/

  /*****�������ڵ�״̬������ʼ*****/
  //���õ�ǰ����Ľڵ�
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

  //�ڵ�����ʱ���ж��Ƿ���ڼ���״̬�ĺ��ӽڵ�
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
  /*****�������ڵ�״̬��������*****/

  /*****���ĸ��Ӷ�����ʼ*****/
  //ִ�е���ڵ��JS������
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
          //alert("*^_^*��ϲ��������!\n\r "+e.name+":"+e.message+" \n\r����ڵ�ķ���: "+node.jsfun+" \n\r�������ش���");
         	this.isFinish = true;
        }
      }
    }
  }

  //��ʼ����
  this.initTree = function(){
    var strTemp = "";
    if (document.getElementById(this.name+"_thisTreeNodeID") == null){
      strTemp += "<input type=\"hidden\" id=\""+this.name+"_thisTreeNodeID\" name=\""+this.name+"_thisTreeNodeID\" value=\"\">";
      strTemp += "<input type=\"hidden\" name=\"thisTreeName\" value=\"\">";
    }
    return strTemp;
  }

  //չ���Ҽ�
  this.showRM = function (id){
    if(window.event.button == 2) {
      var node = this.nodeList[id];
      if (this.rmObj!=null && this.rmObj.itemAreaList.length > node.rmAreaIndex){
        this.rmObj.doRightMenu(node.rmAreaIndex);
      }
    }
  }

  //������ɾ���ڲ���(����BinaryStar�ļ��㼴����)
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
        //����
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
        //ɾ��
        tree.removeChild(frame);
      }
    }
    return true;
  }

  //����ѡ�еĲ���
  this.doTreeNodeRef = function (){
    var node = this.nodeList[this.clickID];
    //ˢ��
    node.childList = new Array();
    var thisForm = eval(this.fomename);
    if (this.isBinaryStar){
      this.optionFrame(true);
      thisForm.target = "BSTree_frame";
    }
    eval(node.openjs);
  }
  /*****���ĸ��Ӷ�������*****/

  /*****�û�ʹ��JS������ʼ*****/
  //����ָ���ڵ��ѡ��״̬
  this.setNodeActiveById = function (inId){
    if (inId == null){
      alert("������һ�����֣�");
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
      alert("��������ȷ�Ľڵ�����");
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

  //��/�ر�ָ���ڵ�
  this.expandById = function (inId){
    if (inId == null){
      alert("������һ�����֣�");
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
      alert("��������ȷ�Ľڵ�����");
      return ;
    }
    var node = this.getNodeByName(inName);
    if (node != null){
      this.expandById(node.id);
    }
    return node;
  }

  //��ָ���ڵ�
  this.openById = function (inId){
    if (inId == null){
      alert("������һ�����֣�");
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
      alert("��������ȷ�Ľڵ�����");
      return ;
    }
    var node = this.getNodeByName(inName);
    if (node != null){
	     this.openById(node.id);
    }
    return node;
  }

  //�ر�ָ���ڵ�
  this.closeById = function (inId){
    if (inId == null){
      alert("������һ�����֣�");
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
      alert("��������ȷ�Ľڵ�����");
      return ;
    }
    var node = this.getNodeByName(inName);
    if (node != null){
	     this.closeById(node.id);
    }
    return node;
  }

  //�����ظ�����ж�(�û����㼴��)
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

  //���ݽڵ���ʾ������ģ����ѯ
  this.searcNodesByText = function (inText){
    if (inText == null || inText == ""){
      alert("������Ҫƥ����ַ�����");
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
      alert("û���ҵ�ƥ��Ľڵ㣡");
    }
    return resNodes;
  }

  //���ݽڵ�ؼ���ģ����ѯ
  this.searcNodesByName = function (inName){
    if (inName == null || inName == ""){
      alert("������Ҫƥ����ַ�����");
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
      alert("û���ҵ�ƥ��Ľڵ㣡");
    }
    return resNodes;
  }

  //���ݽڵ�ؼ��ּ����ڵ�
  this.getNodeByName = function (inName){
    for (var i=0; i<this.nodeList.length; i++){
      if (this.nodeList[i].getName() == inName){
        return this.nodeList[i];
      }
    }
    return null;
  }

  //���ݽڵ����������ڵ�
  this.getNodeById = function (inId){
    if (inId >= 0 && inId < this.nodeList.length){
      return this.nodeList[inId];
    }
    return null;
  }

  //���õ�ǰѡ�����ڵ�
  this.setTreeNodeID = function (in_id){
    document.getElementById(this.name+"_thisTreeNodeID").value = in_id;
    document.getElementById("thisTreeName").value = this.name;
  }

  this.getSelectNode = function (){
    if (this.getTreeNodeID() >= 0 && this.getTreeNodeID() < this.nodeList.length){
      return this.nodeList[this.getTreeNodeID()];
    }
    else{
      alert("û��ѡ�еĽڵ㣡");
      return null;
    }
  }

  this.getTreeNodeID = function (){
    return document.getElementById(this.name+"_thisTreeNodeID").value;
  }

  //�õ���ǰѡ�е����ڵ�ID
  this.getTreeNodePid = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].pid;
    return reStr;
  }

  //�õ���ǰѡ�е����ڵ�ĸ�������
   this.getTreeNodePara = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].paras;
    return reStr;
  }

  //�õ���ǰѡ�е����ڵ������
  this.getTreeNodeName = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].name;
    return reStr;
  }

  //�õ���ǰѡ�е����ڵ����ʾ����
  this.getTreeNodeShowStr = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].showStr;
    return reStr;
  }

  //�õ���ǰѡ�е����ڵ��JS������
  this.getTreeNodeJsFun = function (){
    var reStr = this.nodeList[this.getTreeNodeID()].jsfun;
    return reStr;
  }

  //���ݸ����������õ���ǰѡ�е����ڵ�Ķ�Ӧ��������ֵ
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
  /*****�û�ʹ��JS��������*****/
}

function BSNode(id, pid, deepID, treeName, name, showStr, jsfun, openjs, paras, isOpen, isDoOpen, rmAreaIndex){
  this.id=id;//�ڵ�������
  this.pid=pid;//���ڵ�����,Ϊ-1��ʾ�Ǹ���
  this.deepID=deepID;//�ڵ���ȡ�
  this.showStr=showStr||"BSNode_"+this.id;//�ڵ���ʾ����������
  this.jsfun=jsfun||""//�ýڵ�����JS����
  this.openjs=openjs||""//���㼴�飬�ýڵ���չ����JS������
  this.treeName=treeName||"BSTreeView";//������ʵ������
  this.name=name?name:"BSNode";//�ڵ�����
  this.paras=paras||"";//�ڵ�����������
  this.childList = new Array();
  this.isOpen = isOpen||false;
  this.isDoOpen = isDoOpen||false;
  this.openImg = "open.gif";
  this.closeImg = "close.gif";
  this.nodeImg = "jsdoc.gif";
  this.rmAreaIndex = rmAreaIndex;
  this.body = null;
  this.isDelete = false;
  this.title = "";//�ڵ��title

  /*****get/set������ʼ*****/
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

	//���½ڵ���ʾ������
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

  //�õ��Ҽ��˵�
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

  /*****get/set��������*****/

  /*****�ڵ����������ʼ*****/
  this.addChildItem = function(id){
    this.childList.length++;
    this.childList[this.childList.length - 1] = id;
  }

	//����һ���ڵ�
  this.updateNode = function (inNode){
    var tempTree = eval(this.treeName);
    return tempTree.updateNode(this.id, inNode);
  }

  //����ӽڵ�
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
      //�������ڵ㣨�ݲ��ṩ��
    }
    else{
      var p_node = tempTree.nodeList[this.pid];
      for (var i=0; i<p_node.childList.length; i++){
        //�õ�����λ��
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

  //ɾ�����ڵ�
  this.remove = function(){
    var tempTree = eval(this.treeName);
    tempTree.removeNode(this.id);
  }

  //����ýڵ�
  this.setNodeActive = function (){
    var tempTree = eval(this.treeName);
    tempTree.setNodeActiveById(this.id);
  }

  //��/�رձ��ڵ�
  this.expand = function (){
    var tempTree = eval(this.treeName);
    tempTree.expandById(this.id);
  }

  //�򿪱��ڵ�
  this.open = function (){
  	if (!this.isOpen){
	    var tempTree = eval(this.treeName);
	    tempTree.openById(this.id);
  	}
  }
  //�رձ��ڵ�
  this.close = function (){
  	if (this.isOpen){
	    var tempTree = eval(this.treeName);
	    tempTree.closeById(this.id);
  	}
  }

  //ɾ�����ڵ�����к��ӽڵ�
  this.removeAllChildren = function(){
    var tempTree = eval(this.treeName);
    var t_length = this.childList.length
    for (var i=0; i<t_length; i++){
      tempTree.removeNode(this.childList[0]);
    }
    this.setNodeActive();
  }
  /*****�ڵ����������ʼ*****/

  /*****�����ڵ㿪ʼ*****/
  //�õ���һ���ֵ�
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

  //�õ���һ���ֵ�
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

  //�õ���һ���ֵ�
  this.first = function (){
    var tempTree = eval(this.treeName);
    var p_node = tempTree.nodeList[this.pid];
    return tempTree.nodeList[p_node.childList[0]];
  }

  //�õ����һ���ֵ�
  this.last = function (){
    var tempTree = eval(this.treeName);
    var p_node = tempTree.nodeList[this.pid];
    return tempTree.nodeList[p_node.childList[p_node.childList.length-1]];
  }

  //�õ�����
  this.parent = function (){
    var tempTree = eval(this.treeName);
    if (this.pid >= 0){
    	return tempTree.nodeList[this.pid];
    }
    return null;
  }

  //�õ����ӽڵ㼯��
  this.children = function(){
    var tempTree = eval(this.treeName);
  	var tempList = new Array(this.childList.length);
  	for (var i=0; i<this.childList.length; i++){
  		tempList[i] = tempTree.nodeList[this.childList[i]];
  	}
  	return tempList;
  }
  /*****�����ڵ����*****/

  /*****�û�ʹ��JS������ʼ*****/
  //���ݸ����������õ����ڵ�Ķ�Ӧ��������ֵ
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

  //���ݽڵ���ʾ������ģ����ѯ�ӽڵ�
  this.searcNodesByText = function (inText){
    if (inText == null || inText == ""){
      alert("������Ҫƥ����ַ�����");
      return ;
    }
    var tempTree = eval(this.treeName);
    var resNodes = new Array();
    this.searchChildrenNodeByText(resNodes, inText);
    if (resNodes.length <= 0){
      alert("û���ҵ�ƥ��Ľڵ㣡");
    }
    return resNodes;
  }

  //���ݽڵ�Ĺؼ���ģ����ѯ
  this.searcNodesByName = function (inName){
    if (inName == null || inName == ""){
      alert("������Ҫƥ����ַ�����");
      return ;
    }
    var tempTree = eval(this.treeName);
    var resNodes = new Array();
    this.searchChildrenNodeByName(resNodes, inName);
    if (resNodes.length <= 0){
      alert("û���ҵ�ƥ��Ľڵ㣡");
    }
    return resNodes;
  }
  /*****�û�ʹ��JS��������*****/

  //����ƥ��ĺ��ӽڵ�(˽��)
  this.searchChildrenNodeByName = function(resNodes, inName){
    var tempTree = eval(this.treeName);
    for (var i=0; i<this.childList.length; i++){
      var thisNode = tempTree.nodeList[this.childList[i]];
      if (thisNode.name.Trim().indexOf(inName) >= 0){
        resNodes.length++;
        resNodes[resNodes.length-1] = thisNode;
      }
      //���Һ��ӵĺ���
      if (thisNode.childList.length > 0){
        thisNode.searchChildrenNodeByName(resNodes, inName);
      }
    }
  }

  //����ƥ��ĺ��ӽڵ�(˽��)
  this.searchChildrenNodeByText = function(resNodes, inText){
    var tempTree = eval(this.treeName);
    for (var i=0; i<this.childList.length; i++){
      var thisNode = tempTree.nodeList[this.childList[i]];
      if (thisNode.showStr.Trim().indexOf(inText) >= 0){
        resNodes.length++;
        resNodes[resNodes.length-1] = thisNode;
      }
      //���Һ��ӵĺ���
      if (thisNode.childList.length > 0){
        thisNode.searchChildrenNodeByText(resNodes, inText);
      }
    }
  }
}
