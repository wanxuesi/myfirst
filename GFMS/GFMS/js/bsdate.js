var BSPopup = window.createPopup();
BSDate.cl_resClickLater	=	'����ҳ����ʾ����֮���ٵ����';
BSDate.cl_resDateDrop	=	'��';
BSDate.cl_resDateTitle	=	'ѡ������';
BSDate.cl_resDateUp	=	'��';
BSDate.cl_resDay	=	'��';
BSDate.cl_resMonth	=	'��';
BSDate.cl_resMonth1	=	'һ��';
BSDate.cl_resMonth10	=	'ʮ��';
BSDate.cl_resMonth11	=	'ʮһ��';
BSDate.cl_resMonth12	=	'ʮ����';
BSDate.cl_resMonth2	=	'����';
BSDate.cl_resMonth3	=	'����';
BSDate.cl_resMonth4	=	'����';
BSDate.cl_resMonth5	=	'����';
BSDate.cl_resMonth6	=	'����';
BSDate.cl_resMonth7	=	'����';
BSDate.cl_resMonth8	=	'����';
BSDate.cl_resMonth9	=	'����';
BSDate.cl_resMonthNext	=	'����';
BSDate.cl_resMonthNextBtn	=	'��';
BSDate.cl_resMonthPrev	=	'����';
BSDate.cl_resMonthPrevBtn	=	'��';
BSDate.cl_resWeek1	=	'һ';
BSDate.cl_resWeek2	=	'��';
BSDate.cl_resWeek3	=	'��';
BSDate.cl_resWeek4	=	'��';
BSDate.cl_resWeek5	=	'��';
BSDate.cl_resWeek6	=	'��';
BSDate.cl_resWeek7	=	'��';
BSDate.cl_resYear	=	'��';
BSDate.dateHTML = "";

BSDate.m_iDatePickerCount=0;
BSDate.cl_dpMaxYear=2150;
BSDate.cl_dpMaxMonth=11;
BSDate.cl_dpMaxDay=31;
BSDate.cl_dpMinYear=1901;
BSDate.cl_dpMinMonth=0;
BSDate.cl_dpMinDay=1;
BSPopup_style_title = "FONT-WEIGHT: normal;FONT-SIZE: 9pt;COLOR: #fffacd;background-COLOR: #00008b;";
BSPopup_style_frameinput = "height:18px;width:15px;BORDER: black 0px solid;FONT-WEIGHT: normal;FONT-SIZE: 9pt;black: blue;LINE-HEIGHT: normal;FONT-STYLE: normal;background-COLOR: transparent;Text-ALIGN: right;FONT-VARIANT: normal;";
BSPopup_style_framebtn = "BORDER-RIGHT: black 1px solid;border-TOP: white 1px solid;FONT-WEIGHT: normal;FONT-SIZE: 9pt;border-LEFT: white 1px solid;width: 16px;COLOR: blue;LINE-HEIGHT: normal;border-BOTTOM: black 1px solid;FONT-STYLE: normal;background-COLOR: silver;FONT-VARIANT: normal;";
BSPopup_style_framebtnOK = "BORDER-RIGHT: black 1px solid;border-TOP: white 1px solid;FONT-WEIGHT: normal;FONT-SIZE: 9pt;border-LEFT: white 1px solid;width: 30px;COLOR: blue;LINE-HEIGHT: normal;border-BOTTOM: black 1px solid;FONT-STYLE: normal;background-COLOR: silver;FONT-VARIANT: normal;";
BSPopup_style_framebtnOK2 = "background-COLOR: silver;BORDER-RIGHT: black 1px solid;border-TOP: white 1px solid;width: 16px;border-LEFT: white 1px solid;border-BOTTOM: black 1px solid;height: 8px;font-family:Webdings;font-size: 7px;line-height: 2px;padding-left: 2px;cursor: default;";
BSPopup_style_weekname = "FONT-WEIGHT: normal;COLOR: #00008b;LINE-HEIGHT: normal;BORDER-BOTTOM: black 1px solid;FONT-SIZE: 9pt;FONT-STYLE: normal;TEXT-ALIGN: center;FONT-VARIANT: normal;";
BSPopup_style_table = "BORDER-TOP: lightgrey 1px solid;BORDER-LEFT: lightgrey 1px solid;CURSOR: default;BORDER-right: black 1px solid;BORDER-BOTTOM: black 1px solid;POSITION: absolute;BACKGROUND-COLOR: #ffffff;";
BSPopup_style_DPCellOther = "FONT-WEIGHT: normal;FONT-SIZE: 9pt;CURSOR: hand;COLOR: silver;LINE-HEIGHT: normal;FONT-STYLE: normal;TEXT-ALIGN: center;FONT-VARIANT: normal;";
BSPopup_style_DPCell = "FONT-WEIGHT: normal;FONT-SIZE: 9pt;CURSOR: hand;COLOR: #00008b;LINE-HEIGHT: normal;FONT-STYLE: normal;TEXT-ALIGN: center;FONT-VARIANT: normal;";
BSPopup_style_DPCellSelect = "FONT-WEIGHT: normal;FONT-SIZE: 9pt;CURSOR: hand;COLOR: #000000;LINE-HEIGHT: normal;FONT-STYLE: normal;TEXT-ALIGN: center;FONT-VARIANT: normal;BACKGROUND-COLOR: #ffa07a;";
//Ч��
function KeyFilter(type)
{
  var berr=false;

  switch(type)
  {
    case 'date':
      if (!(event.keyCode == 45 || event.keyCode == 47 || (event.keyCode>=48 && event.keyCode<=57)))
        berr=true;
      break;
    case 'number':
      if (!(event.keyCode>=48 && event.keyCode<=57))
        berr=true;
      break;
    case 'cy':
      if (!(event.keyCode == 46 || (event.keyCode>=48 && event.keyCode<=57)))
        berr=true;
      break;
    case 'long':
      if (!(event.keyCode == 45 || (event.keyCode>=48 && event.keyCode<=57)))
        berr=true;
      break;
    case 'double':
      if (!(event.keyCode == 45 || event.keyCode == 46 || (event.keyCode>=48 && event.keyCode<=57)))
        berr=true;
      break;
    default:
      if (event.keyCode == 35 || event.keyCode == 37 || event.keyCode==38)
        berr=true;
  }
  return !berr;
}
//���ڱȽ�
function dateCompare(thisDt, dtCompare){
	var hr = -1;
	if(thisDt && dtCompare)
	{
		if(thisDt.getFullYear()>dtCompare.getFullYear())
			hr=1;
		else if(thisDt.getFullYear()<dtCompare.getFullYear())
			hr=-1;
		else if(thisDt.getMonth()>dtCompare.getMonth())
			hr=1;
		else if(thisDt.getMonth()<dtCompare.getMonth())
			hr=-1;
		else if(thisDt.getDate()>dtCompare.getDate())
			hr=1;
		else if(thisDt.getDate()<dtCompare.getDate())
			hr=-1;
	}
	return hr;
}
//���ڸ�ʽ��
function dateFormat(dt, sFormat){
	if(sFormat==null || typeof(sFormat)!="string")
		sFormat="";
	sFormat=sFormat.replace(/yyyy/ig,dt.getFullYear());
	var y=""+dt.getYear();
	if(y.length>2)
	{
		y=y.substring(y.length-2,y.length);
	}
	sFormat=sFormat.replace(/yy/ig,y);
	sFormat=sFormat.replace(/mm/ig,dt.getMonth()+1);
	sFormat=sFormat.replace(/dd/ig,dt.getDate());
	sFormat=sFormat.replace(/hh24/ig,dt.getHours());
	sFormat=sFormat.replace(/mi/ig,dt.getMinutes());
	sFormat=sFormat.replace(/ss/ig,0);
	return sFormat;
}
//��yyyy-mm-dd hh24:mi:ss��ʽ���ַ���ת��Ϊ����ʱ�����
function StringToDate(inStr){
	var dt = new Date();
	if (inStr != null && inStr != ""){
		var dList = null;
		var tList = null;
		if (inStr.indexOf(' ') < 1){
			dList = inStr.split('-');
		}
		else{
			dList = (inStr.substring(0, inStr.indexOf(' '))).split('-');
			tList = (inStr.substring(inStr.indexOf(' ')+1, inStr.length)).split(':');
		}
		dt.setYear(dList[0]);
		dt.setMonth(dList[1]-1);
		dt.setDate(dList[2]);

		if (tList != null){
			dt.setHours(tList[0]);
			dt.setMinutes(tList[1]);
		}
		else{
			dt.setHours(0);
			dt.setMinutes(0);
		}
	}
	return dt;
}

function BSDate(name, elmName, readonly, showTime){
	this.name = name || "bsDate";//���ڶ���ʵ�� 
	this.readonly = readonly || false;//ֻ����־
	this.elmName = elmName || "bsDateObj";//װ�����ڿؼ����ı�������
	this.showTime;//��ʾʱ�䣬false������ʾ��true����ʾ��ȱʡΪtrue��
	if (showTime == null){
		this.showTime = true;
	}
	else{
		this.showTime = showTime;
	}
	
	this.bsdate = new Date();
	//�õ���������
	this.getMonthName = function(lMonth){
		var mnArr=new Array(BSDate.cl_resMonth1,BSDate.cl_resMonth2,BSDate.cl_resMonth3,BSDate.cl_resMonth4,BSDate.cl_resMonth5,BSDate.cl_resMonth6,
			BSDate.cl_resMonth7,BSDate.cl_resMonth8,BSDate.cl_resMonth9,BSDate.cl_resMonth10,BSDate.cl_resMonth11,BSDate.cl_resMonth12);
		return mnArr[lMonth];
	}
	
	//�õ����ڶ���
	this.getDateObj = function (){
		return this.bsdate;
	}
	
	//�õ������ַ���
	this.getDateStr = function (){
		return document.getElementById(this.elmName).value;
	}
	//��ʼ�����޸�����ʱ��
	this.updateDateTime = function(dt, lYear, lMonth, lDay, lHours, lMins){
		if (this.readonly){
			return;
		}
		if (dt == null){
			lYear=parseInt(lYear,10);
			lMonth=parseInt(lMonth,10);
			lDay=parseInt(lDay,10);
			lHours=parseInt(lHours,10);
			lMins=parseInt(lMins,10);

			if(!(isNaN(lYear) || isNaN(lMonth) || isNaN(lDay) || isNaN(lHours) || isNaN(lMins))){
				this.bsdate.setYear(lYear);
				this.bsdate.setMonth(lMonth-1);
				this.bsdate.setDate(lDay);
				this.bsdate.setHours(lHours);
				this.bsdate.setMinutes(lMins);
			}
		}
		else{
			this.bsdate = dt;
		}
		this.setInputDate();
	}
	//��ʼ�����޸�����
	this.updateDate = function(lYear, lMonth, lDay){
		if (this.readonly){
			return;
		}
		lYear=parseInt(lYear,10);
		lMonth=parseInt(lMonth,10);
		lDay=parseInt(lDay,10);

		if(!(isNaN(lYear) || isNaN(lMonth) || isNaN(lDay))){
			this.bsdate.setYear(lYear);
			this.bsdate.setMonth(lMonth-1);
			this.bsdate.setDate(lDay);
		}
		this.setInputDate();
	}
	//��ʼ�����޸�ʱ��
	this.updateTime = function(lHours, lMins){
		if (this.readonly){
			return;
		}
		lHours=parseInt(lHours,10);
		lMins=parseInt(lMins,10);

		if(!(isNaN(lHours) || isNaN(lMins))){
			this.bsdate.setHours(lHours);
			this.bsdate.setMinutes(lMins);
		}
		this.setInputDate();
	}
	
	//����ֻ����־
	this.setReadonly = function(readonly){
		this.readonly = readonly || false;
		if (document.getElementById(this.elmName) != null){
			var col = "black";
			if (this.readonly){
				col = "silver";
			}
      document.getElementById(this.name+"_input_year").readOnly = this.readonly;
      document.getElementById(this.name+"_input_month").readOnly = this.readonly;
      document.getElementById(this.name+"_input_day").readOnly = this.readonly;
      document.getElementById(this.name+"_input_hour").readOnly = this.readonly;
      document.getElementById(this.name+"_input_min").readOnly = this.readonly;
      document.getElementById(this.name+"_btn").style.color = col;
		}
	}

	//����ʱ����ʾ��־
	this.setTimeShow = function(showTime){
		this.showTime = showTime || false;
		if (document.getElementById(this.elmName) != null){
			var col = "none";
			if (this.showTime){
				col = "";
			}

	    var stformt="yyyy-mm-dd hh24:mi:ss";
	    if (!this.showTime){
	    	stformt = "yyyy-mm-dd";
    	}
      document.getElementById(this.name+"_input_hour").style.display = col;
      document.getElementById(this.name+"_input_mh").style.display = col;
      document.getElementById(this.name+"_input_min").style.display = col;
	    document.getElementById(this.elmName).value = dateFormat(this.bsdate, stformt);
		}
	}

	this.setDateHTML = function(){
		this.setInputDate();
		var dt = new Date(this.bsdate.getFullYear(),this.bsdate.getMonth(),1);
		var tempDt=new Date(this.bsdate.getFullYear(),this.bsdate.getMonth(),1-dt.getDay());
		BSDate.dateHTML = "<TABLE style=\""+BSPopup_style_table+"\" id=\"dpDropDownTable\" align=\"center\" CELLSPACING=\"0\" border=0>";
		BSDate.dateHTML += "<TR style=\""+BSPopup_style_title+"\" onselectstart=\"event.cancelBubble=true;window.event.returnValue=false;\">";
		BSDate.dateHTML += "<TD align=\"center\"><span style=\""+BSPopup_style_framebtn+"\" onclick=\"parent."+this.name+".changeMonth(event, true);\" title=\""+BSDate.cl_resMonthPrev+"\">"+BSDate.cl_resMonthPrevBtn+"<span></TD>";
		BSDate.dateHTML += "<TD align=\"center\" colspan=\"5\">"+this.getMonthName(this.bsdate.getMonth())+" "+this.bsdate.getFullYear()+"</TD>";
		BSDate.dateHTML += "<TD align=\"center\"><span style=\""+BSPopup_style_framebtn+"\" onclick=\"parent."+this.name+".changeMonth(event, false);\" title=\""+BSDate.cl_resMonthNext+"\">"+BSDate.cl_resMonthNextBtn+"<span></TD>";
		BSDate.dateHTML += "</TR>";
		BSDate.dateHTML += "<TR onselectstart=\"event.cancelBubble=true;window.event.returnValue=false;\">";
		BSDate.dateHTML += "<TD align=\"center\" style=\""+BSPopup_style_weekname+"\">&nbsp;"+BSDate.cl_resWeek7+"&nbsp;</TD>";
		BSDate.dateHTML += "<TD align=\"center\" style=\""+BSPopup_style_weekname+"\">&nbsp;"+BSDate.cl_resWeek1+"&nbsp;</TD>";
		BSDate.dateHTML += "<TD align=\"center\" style=\""+BSPopup_style_weekname+"\">&nbsp;"+BSDate.cl_resWeek2+"&nbsp;</TD>";
		BSDate.dateHTML += "<TD align=\"center\" style=\""+BSPopup_style_weekname+"\">&nbsp;"+BSDate.cl_resWeek3+"&nbsp;</TD>";
		BSDate.dateHTML += "<TD align=\"center\" style=\""+BSPopup_style_weekname+"\">&nbsp;"+BSDate.cl_resWeek4+"&nbsp;</TD>";
		BSDate.dateHTML += "<TD align=\"center\" style=\""+BSPopup_style_weekname+"\">&nbsp;"+BSDate.cl_resWeek5+"&nbsp;</TD>";
		BSDate.dateHTML += "<TD align=\"center\" style=\""+BSPopup_style_weekname+"\">&nbsp;"+BSDate.cl_resWeek6+"&nbsp;</TD>";
		BSDate.dateHTML += "</TR>";
		//��ʾ����
		var day=tempDt.getDate();
		for(var i=2; i<8; i++){
			BSDate.dateHTML += "<TR onselectstart=\"event.cancelBubble=true;window.event.returnValue=false;\">";
			for(var j=0; j<7; j++){
				BSDate.dateHTML += "<TD ";
				if(tempDt.getMonth()!=this.bsdate.getMonth()){
					BSDate.dateHTML += "style=\""+BSPopup_style_DPCellOther+"\" ";
				}
				else if (tempDt.getDate()!=this.bsdate.getDate()){
					BSDate.dateHTML += "style=\""+BSPopup_style_DPCell+"\" ";
				}
				else{
					BSDate.dateHTML += "style=\""+BSPopup_style_DPCellSelect+"\" ";
				}
				BSDate.dateHTML += (" onclick=\"parent."+this.name+".changeDay(event, "+tempDt.getFullYear()+", "+tempDt.getMonth()+", "+day+");\" ");
				BSDate.dateHTML += (" ondblclick=\"parent.BSPopup.hide();\"");
				BSDate.dateHTML += ">"+day+"</TD>";
				tempDt.setDate(day+1);
				day=tempDt.getDate();
			}
			BSDate.dateHTML += "</TR>";
		}
		BSDate.dateHTML += "<TR><TD colspan=\"7\" style=\"BORDER-TOP: black 1px solid;width:100%;height:20px;\" valign=\"top\">";
		BSDate.dateHTML += "<TABLE style=\"width:100%;height:25px;\">";
		BSDate.dateHTML += "<TR>";
		if (this.showTime){
			BSDate.dateHTML += "<TD style=\"width:15;height:20px;\"><span style=\""+BSPopup_style_frameinput+"\">"+this.bsdate.getHours()+"</span></TD>";
			BSDate.dateHTML += "<TD style=\"width:16px;FONT-SIZE: 9pt;FONT-WEIGHT: normal;\" valign=\"top\">";
			BSDate.dateHTML += "<input type=\"button\" onFocus=\"this.blur();\" style=\""+BSPopup_style_framebtnOK2+"\" onclick=\"parent."+this.name+".changeHour(event, 1)\" id=\"BSPopup_H_A\" value=\"5\"/><br/>";
			BSDate.dateHTML += "<input type=\"button\" onFocus=\"this.blur();\" style=\""+BSPopup_style_framebtnOK2+"\" onclick=\"parent."+this.name+".changeHour(event, -1)\" id=\"BSPopup_H_D\" value=\"6\"/>";
			BSDate.dateHTML += "<TD style=\"width:12px;FONT-SIZE: 9pt;FONT-WEIGHT: normal;\">ʱ</TD>";
			BSDate.dateHTML += "<TD style=\"width:15;height:20px;\"><span style=\""+BSPopup_style_frameinput+"\">"+this.bsdate.getMinutes()+"</span></TD>";
			BSDate.dateHTML += "<TD style=\"width:16px;FONT-SIZE: 9pt;FONT-WEIGHT: normal;\" valign=\"top\">";
			BSDate.dateHTML += "<input type=\"button\" onFocus=\"this.blur();\" style=\""+BSPopup_style_framebtnOK2+"\" onclick=\"parent."+this.name+".changeMin(event, 1)\" value=\"5\"/><br/>";
			BSDate.dateHTML += "<input type=\"button\" onFocus=\"this.blur();\" style=\""+BSPopup_style_framebtnOK2+"\" onclick=\"parent."+this.name+".changeMin(event, -1)\" value=\"6\"/>";
			BSDate.dateHTML += "<TD style=\"width:100%;FONT-SIZE: 9pt;FONT-WEIGHT: normal;\" align=\"left\">��</TD>";
		}
		else{
			BSDate.dateHTML += "<TD style=\"width:100%;FONT-SIZE: 9pt;FONT-WEIGHT: normal;\" align=\"left\">&nbsp;</TD>";
		}
		BSDate.dateHTML += "<TD style=\"height:20px;\" valign=\"top\"><input type=\"button\" tabindex=\"2\" onclick=\"parent."+this.name+".toDay(event)\" style=\""+BSPopup_style_framebtnOK+";height:18px;\" value=\"����\"/></TD>";
		BSDate.dateHTML += "<TD style=\"height:20px;\" valign=\"top\"><input id=\"BSPopup_OKbtn\" tabindex=\"1\" type=\"button\" onclick=\"parent.BSPopup.hide();\" style=\""+BSPopup_style_framebtnOK+";height:18px;\" value=\"ȷ��\"/></TD>";

		BSDate.dateHTML += "</TR>";
		BSDate.dateHTML += "</TABLE>";
		BSDate.dateHTML += "</TD></TR>";
		BSDate.dateHTML += "</TABLE>";
	}

	//չ�����ڿؼ�
	this.show = function(){
    var lYear=this.bsdate.getFullYear();
    var lMonth=this.bsdate.getMonth()+1;
    if (lMonth < 10){
      lMonth = "0"+lMonth;
    }
    var lDay=this.bsdate.getDate();
    if (lDay < 10){
      lDay = "0"+lDay;
    }
    var lHours=this.bsdate.getHours();
    if (lHours < 10){
      lHours = "0"+lHours;
    }
    var lMins=this.bsdate.getMinutes();
    if (lMins < 10){
      lMins = "0"+lMins;
    }
    var ro = "", rosty="";
    if (this.readonly){
      ro = " readonly=\"readonly\"";
      rosty="color:silver;";
    }
    var st = "",stformt="yyyy-mm-dd hh24:mi:ss";
    if (!this.showTime){
    	st = "display:none;";
    	stformt = "yyyy-mm-dd";
    }
    var tempHTML = "<span class=\"bsdate_frame\" id=\""+this.name+"_frame\" name=\""+this.name+"_frame\">";
    tempHTML += "<nobr>";
    tempHTML += "<input id=\""+this.name+"_input_year\" class=\"bsdate_input\" style=\"width:30px\" type=\"text\" value=\""+lYear+"\" size=\"3\" maxlength=\"4\" onfocus=\"return "+this.name+".dateFocus('year')\" onblur=\"return "+this.name+".dateBlur('year')\" onkeypress=\"return KeyFilter('number');\" onkeydown=\"return "+this.name+".keyDown('year')\""+ro+"/>";
    tempHTML += "<font class=\"bsdate_des\">"+BSDate.cl_resYear+"</font>";
    tempHTML += "<input id=\""+this.name+"_input_month\" class=\"bsdate_input\" style=\"width:15px\" type=\"text\" value=\""+lMonth+"\" size=\"1\" maxlength=\"2\" onfocus=\"return "+this.name+".dateFocus('month')\" onblur=\"return "+this.name+".dateBlur('month')\" onkeypress=\"return KeyFilter('number');\" onkeydown=\"return "+this.name+".keyDown('month')\""+ro+"/>";
    tempHTML += "<font class=\"bsdate_des\">"+BSDate.cl_resMonth+"</font>";
    tempHTML += "<input id=\""+this.name+"_input_day\" class=\"bsdate_input\" style=\"width:15px\" type=\"text\" value=\""+lDay+"\" size=\"1\" maxlength=\"2\" onfocus=\"return "+this.name+".dateFocus('day')\" onblur=\"return "+this.name+".dateBlur('day')\" onkeypress=\"return KeyFilter('number');\" onkeydown=\"return "+this.name+".keyDown('day')\""+ro+"/>";
    tempHTML += "<font class=\"bsdate_des\">"+BSDate.cl_resDay+"</font>";
    tempHTML += "<input id=\""+this.name+"_input_hour\" class=\"bsdate_input\" style=\"width:15px;"+st+"\" type=\"text\" value=\""+lHours+"\" size=\"1\" maxlength=\"2\" onfocus=\"return "+this.name+".dateFocus('hour')\" onblur=\"return "+this.name+".dateBlur('hour')\" onkeypress=\"return KeyFilter('number');\" onkeydown=\"return "+this.name+".keyDown('hour')\""+ro+"/>";
    tempHTML += "<font id=\""+this.name+"_input_mh\" class=\"bsdate_mhdes\"  style=\""+st+"\">:</font>";
    tempHTML += "<input id=\""+this.name+"_input_min\" class=\"bsdate_input\" style=\"width:15px;"+st+"\" type=\"text\" value=\""+lMins+"\" size=\"1\" maxlength=\"2\" onfocus=\"return "+this.name+".dateFocus('min')\" onblur=\"return "+this.name+".dateBlur('min')\" onkeypress=\"return KeyFilter('number');\" onkeydown=\"return "+this.name+".keyDown('min')\""+ro+"/>";
    tempHTML += "<span class=\"bsdate_btn\" id=\""+this.name+"_btn\" name=\""+this.name+"_btn\" onclick=\""+this.name+".showDig(this);return false;\" title=\""+BSDate.cl_resDateTitle+"\" style=\""+rosty+"\">"+BSDate.cl_resDateDrop+"</span>";
    if(typeof(this.name)=="string" && this.name.length>0){
    	tempHTML += "<input type=\"hidden\" value=\""+dateFormat(this.bsdate, stformt)+"\" id=\""+this.elmName+"\" name=\""+this.elmName+"\"/>";
    }
    tempHTML += "</nobr>";
    tempHTML += "</span>";
    document.write(tempHTML);
	}

	//�������б��
	this.showDig = function(inObjID){
		if (this.readonly){
			return;
		}
    var left = 128;
    this.setDateHTML();
    if (!this.showTime){
    	left = 90;
    }
    BSPopup.document.body.innerHTML=BSDate.dateHTML;
    BSPopup.show(GetDefineX(inObjID)-left, GetDefineY(inObjID)+14, 184, 162, document.body);
	}

	//������ʾ��������
	this.setInputDate = function(){
    if (document.getElementById(this.elmName) != null){
      var lYear=this.bsdate.getFullYear();
      var lMonth=this.bsdate.getMonth()+1;
      if (lMonth < 10){
        lMonth = "0"+lMonth;
      }
      var lDay=this.bsdate.getDate();
      if (lDay < 10){
        lDay = "0"+lDay;
      }
      var lHours=this.bsdate.getHours();
      if (lHours < 10){
        lHours = "0"+lHours;
      }
      var lMins=this.bsdate.getMinutes();
      if (lMins < 10){
        lMins = "0"+lMins;
	    }

	    var stformt="yyyy-mm-dd hh24:mi:ss";
	    if (!this.showTime){
	    	stformt = "yyyy-mm-dd";
    	}
	    document.getElementById(this.name+"_input_year").value = lYear;
	    document.getElementById(this.name+"_input_month").value = lMonth;
	    document.getElementById(this.name+"_input_day").value = lDay;
	    document.getElementById(this.name+"_input_hour").value = lHours;
	    document.getElementById(this.name+"_input_min").value = lMins;
	    document.getElementById(this.elmName).value = dateFormat(this.bsdate, stformt);
    }
	}

	//�����ʧȥ����
	this.dateBlur = function(srcType){
		if (this.readonly){
			return true;
		}
		var src=event.srcElement;
		var lYear=this.bsdate.getFullYear();
		var lMonth=this.bsdate.getMonth()+1;
		var lDay=this.bsdate.getDate();
		var lHours=this.bsdate.getHours();
		var lMins=this.bsdate.getMinutes();

		var val=parseInt(src.value,10);
		if(isNaN(val)){
			val=-1;
		}
		switch(srcType)	{
			case 'year':
				lYear=val==-1?lYear:val;
				break;
			case 'month':
				lMonth=val==-1?lMonth:val;
				break;
			case 'day':
				lDay=val==-1?lDay:val;
				break;
			case 'hour':
				lHours=val==-1?lHours:val;
				break;
			case 'min':
				lMins=val==-1?lMins:val;
				break;
			default:break;
		}
		this.setCurDate(lYear, lMonth, lDay, lHours, lMins);
		return true;
	}
	//�����۽�
	this.dateFocus = function (srcType){
		if (this.readonly){
			return true;
		}
		var src=event.srcElement;
		if(src && src.tagName=="INPUT")
		{
			switch(srcType)
			{
				case 'year':
					break;
				case 'month':
					break;
				case 'day':
					break;
				case 'hour':
					break;
				case 'min':
					break;
				default:;
			}
			src.select();
		}
		return true;
	}

	//���¼�ͷ�ı��ı�������
	this.keyDown = function (srcType){
		if (this.readonly){
			return true;
		}
		var bRefresh=true;
		var src=event.srcElement;
		var lYear=this.bsdate.getFullYear();
		var lMonth=this.bsdate.getMonth();
		var lDay=this.bsdate.getDate();
		var lHours=this.bsdate.getHours();
		var lMins=this.bsdate.getMinutes();
		var lStep=0;
		switch(event.keyCode){
			case 38://�ϼ�ͷ
				lStep=1;
				break;
			case 40://�¼�ͷ
				lStep=-1;
				break;
			case 13://�س�
				event.keyCode=9;//Tab
				bRefresh=false;
				break;
			default:bRefresh=false;break;
		}

		switch(srcType)	{
			case 'year':
				lYear+=lStep;
				break;
			case 'month':
				lMonth+=lStep;
				break;
			case 'day':
				lDay+=lStep;
				break;
			case 'hour':
				lHours+=lStep;
				break;
			case 'min':
				lMins+=lStep;
				break;
			default:break;
		}
		if(bRefresh){
			this.setCurDate(lYear, lMonth+1, lDay, lHours, lMins);
			//alert("keyDown:"+lMonth);
		}
		return true;
	}

	//�����޸ĺ��ʱ��
	this.setCurDate = function(lYear, lMonth, lDay, lHours, lMins){
		var hr=false;
		lYear=parseInt(lYear,10);
		lMonth=parseInt(lMonth,10);
		lDay=parseInt(lDay,10);
		lHours=parseInt(lHours,10);
		lMins=parseInt(lMins,10);

		if(!(isNaN(lYear) || isNaN(lMonth) || isNaN(lDay) || isNaN(lHours) || isNaN(lHours))){
			var dt=new Date(lYear, lMonth-1 , lDay, lHours, lMins);
			var dMax=new Date(BSDate.cl_dpMaxYear,BSDate.cl_dpMaxMonth,BSDate.cl_dpMaxDay,23,59);
			var dMin=new Date(BSDate.cl_dpMinYear,BSDate.cl_dpMinMonth,BSDate.cl_dpMinDay,0,0);
			if(dateCompare(dt, dMax)<=0 && dateCompare(dt, dMin)>=0){
				this.bsdate=dt;
			}
			else if (dateCompare(dt, dMax)>0){
				this.bsdate = dMax
			}
			else if (dateCompare(dt, dMin)<0){
				this.bsdate = dMin
			}
			hr=true;
		}
		if(hr){
			this.setInputDate();
		}
		return hr;
	}

	//ѡ�����
	this.toDay = function(event){
		event.returnValue=false;
		var today = new Date();
		this.bsdate.setYear(today.getFullYear());
		this.bsdate.setMonth(today.getMonth());
		this.bsdate.setDate(today.getDate());
		this.bsdate.setHours(today.getHours());
		this.bsdate.setMinutes(today.getMinutes());
		this.setDateHTML();
		BSPopup.document.body.innerHTML=BSDate.dateHTML;
		return false;
	}

	//ѡ����
	this.changeDay = function(event, inYear, inMonth, inDay){
		event.returnValue=false;
		this.bsdate.setYear(inYear);
		this.bsdate.setMonth(inMonth);
		this.bsdate.setDate(inDay);
		this.setDateHTML();
		BSPopup.document.body.innerHTML=BSDate.dateHTML;
		return false;
	}

	//�ı�Сʱ
	this.changeHour = function(event, inHour){
		event.returnValue=false;
		this.bsdate.setHours(this.bsdate.getHours() + inHour);
		this.setDateHTML();
		BSPopup.document.body.innerHTML=BSDate.dateHTML;
		return false;
	}

	//�ı����
	this.changeMin = function(event, inMin){
		event.returnValue=false;
		this.bsdate.setMinutes(this.bsdate.getMinutes() + inMin);
		this.setDateHTML();
		BSPopup.document.body.innerHTML=BSDate.dateHTML;
		return false;
	}

	//����
	this.changeMonth = function(event, arrFlg){
		var dMax=new Date(BSDate.cl_dpMaxYear,BSDate.cl_dpMaxMonth-1,BSDate.cl_dpMaxDay,0,0);
		var dMin=new Date(BSDate.cl_dpMinYear,BSDate.cl_dpMinMonth+1,BSDate.cl_dpMinDay,0,0);
		if (arrFlg){
			//��һ��
			if (dateCompare(this.bsdate, dMin)>0){
				this.bsdate.setMonth(this.bsdate.getMonth()-1);
			}
		}
		else{
			if (dateCompare(this.bsdate, dMax)<0){
				this.bsdate.setMonth(this.bsdate.getMonth()+1);
			}
		}
		this.setDateHTML();
		BSPopup.document.body.innerHTML=BSDate.dateHTML;
	}
}