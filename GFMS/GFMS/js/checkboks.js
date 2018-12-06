      var zindex=1;
var SelectDIV=function(fatherID/*???*/,name/*??*/,css/*??*/,clew/*??????*/){
	this.DIVvalue="";
	this.name=name;
	this.css=css;//?????
	this.owidth = "50px"; //option???
	this.cwidth = "30px"	//checkbox???
	//?????
	this.init=function(){
		//??????
		for(i=0;i<SelectDIV.ArrayName.length;i++){
			if(SelectDIV.ArrayName[i]==name){
				alert("??"+name+"?????");
				throw e;
			}
		}
		//??????
		var strInit = new SelectDIV.StringBuild();
		strInit.push("<input type='text' style='width:50px' name='n"+name+"' readonly='readonly'>");
		strInit.push(" <input type='hidden'  id='id"+name+"' value=''>");
		//strInit.push("&nbsp;");
		strInit.push("<img src='../../img/common/query.gif' alt='"+clew+"' width='16' height='16' align='absmiddle' id='img"+name+"'>");
		//alert(document.getElementById(fatherID));
		if(fatherID!=""){
			var fobj=document.getElementById(fatherID);
			
			try{
				if(fobj==null){
					throw "Err1"
				}
				fobj.insertAdjacentHTML("beforeEnd",strInit.toString());}
			catch(e){
				if(e=="Err1"){
					alert(fatherID+"????");
				}
			}
		}
		else{
			var divObj = document.createElement("div");
			divObj.innerHTML = strInit.toString();
			document.body.appendChild(divObj);
		}	
		//???????DIV?
		var divInit = new SelectDIV.StringBuild();
		divInit.push("<div id='d1"+name+"' style='position:absolute;display:none;z-index:50;width:50px; background: #ffffff;' align='left'>");
		divInit.push("<div id='d2"+name+"'>");
		divInit.push("</div>");
		divInit.push("<div id='d3"+name+"' style='height:25px;width:50px;margin-top:2px;margin-bottom:1px;' align='right'>");
		divInit.push(" <input style='width:100px' class='input' type='hidden' id='but"+name+"' />");
		divInit.push("</div></div>");
		var divObj = document.createElement("div");
		divObj.innerHTML = divInit.toString();
		document.body.appendChild(divObj);
		this.setCheck();
		this.setImg();
		//????
		this.setCss();
		SelectDIV.ArrayD.push(document.getElementById("d1"+name));
		SelectDIV.ArrayName.push(name);
		if(document.getElementById("d1"+name).style.zIndex>zindex){
			zindex=document.getElementById("d1"+name).style.zIndex;
		}
	};
	this.init();
	};
//??????
SelectDIV.StringBuild = function(){
	this.arr = new Array();
	this.push = function(str){
		this.arr.push(str);
	};
	this.toString = function(){
		return this.arr.join("");
	};
};
//??DIV???
SelectDIV.ArrayD = new Array();
//???????
SelectDIV.ArrayName=new Array();
//??
SelectDIV.IsOK=function(bdiv,tdiv,checks,nname,but,img,nvalue,tname){
		but.onclick=img.onclick=function (){
		bdiv.style.zIndex=++zindex;
		if(bdiv.style.display==""){
			bdiv.style.display='none'
			nname.value="";
			nvalue.value="";
			var length=tdiv.getElementsByTagName("input");
			if(length==null){
				return;
			}
			for(i=0;i<length.length;i++){
				if   (length[i].checked ==1){
					nvalue.value+=length[i].value+",";
					var a=length[i].value;
					var aa=document.getElementById("o"+tname+a);
					nname.value+=aa.value+";";	
				}
			}
			if(nvalue.value.length>0){
				var strs=nvalue.value.split(",");
				var leng=strs.length;
				nvalue.value="";
				for(i=0;i<leng-2;i++){
					nvalue.value+=strs[i]+",";
				}
				nvalue.value+=strs[leng-2];
			}
		}
		else{
			var tt=nname;
			var  ttop    =  tt.offsetTop;          //TT???????
			var  thei    =  tt.clientHeight;    //TT??????
			var  tleft  =  tt.offsetLeft;        //TT???????
			while  (tt  =  tt.offsetParent){ttop+=tt.offsetTop;  tleft+=tt.offsetLeft;}
			bdiv.style.top=ttop+thei+6;
			bdiv.style.left=tleft  +  1;
			//document.all.d1.style.width=document.all.textfield.width;
			var a=tdiv.getElementsByTagName("input");;
			//document.all.d1.style.z-index='1';
			if(a.length > 7){
				tdiv.style.overflowY = "scroll";
				tdiv.style.height = 150;
			}
			else{
				tdiv.style.overflowY = "hidden";
				tdiv.style.height = null;
			}
			bdiv.style.display="";
		}
		};
};
//????
SelectDIV.prototype.setCheck=function (){
		var bdiv=document.getElementById("d1"+this.name);
		var tdiv=document.getElementById("d2"+this.name);
		var checks=document.getElementById("a1"+this.name);
		var nname=document.getElementById("n"+this.name);
		var but=document.getElementById("but"+this.name);
		var img=document.getElementById("img"+this.name);
		var nvalue=document.getElementById("id"+this.name);
		new SelectDIV.IsOK(bdiv,tdiv,checks,nname,but,img,nvalue,this.name);
	};
//????
SelectDIV.prototype.addOption=function (name,value){
	var tdiv=document.getElementById("d2"+this.name);
	var divInit = new SelectDIV.StringBuild();
	divInit.push("<input type='checkbox' id='a1"+this.name+"' style='width:50px;' value='"+value+"'> <option id='o"+this.name+value+"' value='"+name+"'  style='");
	divInit.push("width: "+this.owidth+";'>"+name+"</option><br/>");
	tdiv.insertAdjacentHTML("beforeEnd",divInit.toString());
};
//???
SelectDIV.prototype.getValue=function (){
	return document.getElementById("id"+this.name).value;
};
//???????
SelectDIV.prototype.setImg=function (){
	if(typeof this.css != "undefined"){
	if(typeof this.css.img != "undefined")
		var img=document.getElementById("img"+this.name).src=this.css.img;	
	}
};
//????
SelectDIV.prototype.setCss=function (){
		var obj = document.getElementById("d1"+this.name);
		var tdiv=document.getElementById("d2"+this.name);
		var thdiv=document.getElementById("d3"+this.name);
		var nname=document.getElementById("n"+this.name);
		var but=document.getElementById("but"+this.name);
		var img=document.getElementById("img"+this.name);
	if(typeof this.css != "undefined"){	
		if(typeof this.css.width != "undefined"){
			var nwidth=parseInt(this.css.width);
			var widthdw=this.css.width.substring(this.css.width.length-2);
			obj.style.width = nwidth*0.9+widthdw;	
			tdiv.style.width = nwidth*0.9+widthdw;
			thdiv.style.width = nwidth*0.9+widthdw;
			nname.style.width = nwidth*0.9+widthdw;
			//but.style.width = nwidth*0.2+widthdw;
			//img.style.width = nwidth*0.2+widthdw;
			this.cwidth=nwidth*0.25+widthdw;
			this.owidth=nwidth*0.7+widthdw;
		}
		if(typeof this.css.height != "undefined"){
			var nheight=parseInt(this.css.height);
			var heightdw=this.css.height.substring(this.css.height.length-2)
			obj.style.height = this.css.height; 
			tdiv.style.height = this.css.height;
			nname.style.height = nheight*0.9+heightdw;
			//but.style.height = nheight*0.2+heightdw;
		}
		if(typeof this.css.zindex != "undefined"){
			obj.style.zIndex=this.css.zindex;
		}
	}
};