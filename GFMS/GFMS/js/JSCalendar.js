<!--
//135??????????????????
var _cldTabIFrame = null;
var _cldTabIFrame2 = null;

var _cldTabFrm = null;
var _cldMonthMenuFrm = null;
var _curCldTabRltvObj = null;
var _bCanHide = true;
var _bHaveUpdated = false;
var _bHaveShown = false;

var _bHaveSelectNewValue = false;

var _dCurYear = null;
var _dCurMonth = null;
var _dCurDate = null;


var _curCldTabRltvObjValue = "";

/**
  * ????????????????????????????????????????????????????????????????
  * _nShadowLength ???????????????????????????????? 4??
  * ?????????????????????????????????????????????????????????????????????????????? 0
  */
var _nShadowLength = 0;

/**
  * _sNeededFilePath ??????????JSCalendar????????????????????????
  * ????????????????????????????????????????????????????????????????
  * ??????
  *		_sNeededFilePath = "/MyWebAPP/comm/js/JSCalendar/";
  *
  * ?????????????????????????????????????????????????????? 
  */
var _sNeededFilePath = "/SCZH/images/";

/**
  * ????????????
  * 
  */
var _imgReset1 = new Image();
	_imgReset1.src = _sNeededFilePath + "reset1.gif";
var _imgReset2 = new Image();
	_imgReset2.src = _sNeededFilePath + "reset2.gif";
var _imgBtnYear = new Image();
	_imgBtnYear.src = _sNeededFilePath + "btnYears.gif";
var _imgBgToday1 = new Image();
	_imgBgToday1.src = _sNeededFilePath + "bgToday1.gif";

/**
  *	????????????????
  */
var _monthDays = new Array(12);
    _monthDays[ 0] = 31;
    _monthDays[ 1] = 28;
    _monthDays[ 2] = 31;
    _monthDays[ 3] = 30;
    _monthDays[ 4] = 31;
    _monthDays[ 5] = 30;
    _monthDays[ 6] = 31;
    _monthDays[ 7] = 31;
    _monthDays[ 8] = 30;
    _monthDays[ 9] = 31;
    _monthDays[10] = 30;
    _monthDays[11] = 31;

// ??????????
var _weeks = new Array(7);    
	_weeks[0] = "Sun.";
	_weeks[1] = "Mon.";
	_weeks[2] = "Tue.";
	_weeks[3] = "Wed.";
	_weeks[4] = "Thu.";
	_weeks[5] = "Fri.";
	_weeks[6] = "Sat.";

//????????????
var _months = new Array(12);
	_months[ 0] = "1st";
	_months[ 1] = "2nd";
	_months[ 2] = "3th";
	_months[ 3] = "4th";
	_months[ 4] = "5th";
	_months[ 5] = "6th";
	_months[ 6] = "7th";
	_months[ 7] = "8th";
	_months[ 8] = "9th";
	_months[ 9] = "10th";
	_months[10] = "11th";
	_months[11] = "12th";
	
//????????????
var _dftD = new Date(); 

//??????????????
document.write("<IFRAME id=frm name=frm style='display:none'></IFRAME>");

//??????
function JSCalendar(rltvO, year, month, date){

	if(_bHaveUpdated && rltvO == _curCldTabRltvObj) return;
	//????????????input,??????????
	if(rltvO.tagName != "INPUT"){
		alert("JSCalendar 1.0 alerts you:\n\n INPUT element(object) must be required!");
		return
	};	
	rltvO.readOnly = true;                 //????????????
	rltvO.style.cursor = "default";        //??????????????????
	rltvO.style.textAlign = "center";      //????????????????
	_curCldTabRltvObjValue = rltvO.value;  //????????????????????
	_bHaveSelectNewValue = false;          //??????????????

	//------------------------------------------------------------------------------
	this.createCldTabFrm = createCldTabFrm;	
	this.fillCldTabFrm = fillCldTabFrm;
	this.placeCldTabFrm = placeCldTabFrm;
	//------------------------------------------------------------------------------
	
	this._rltvO = (rltvO == null ? document.body : rltvO);
	//var _oldDate = rltvO.value;	//????????????
	var _oldDateType = "From Input!";	//????????????

	try{
		if(_oldDate == "") 
			throw "";                   //????????????????????
		var d = _oldDate.split("-");    //??????????????????
		//var _tmpDate = getValidDate(_oldDate);         //????????????????????
		this._year = d[0];//_tmpDate.getFullYear();      //??????????????????
		this._month = d[1] - 1;//_tmpDate.getMonth() - 1;//??????????????????????
		this._date = d[2];//_tmpDate.getDate();          //????????????????????
	}catch(e){    //??????????????
		_debug("Error was be catched:" + e.description);
		_oldDateType = "From default or current date!";   //??????????????????????????
		this._year = (year == null ? _dftD.getFullYear() : year);
		//??????????
		//this._month = (month == null ? _dftD.getMonth() : month-1);;
		this._month = (month == null ? _dftD.getMonth() : month);;
		this._date = (date == null ? _dftD.getDate() : date);
	}
	//alert(this._year + "-" + (this._month + 1) + "-" + this._date );

	//????????????
	_dCurYear = this._year;
	_dCurMonth = this._month;
	_dCurDate = this._date;

	//????????????????????
	if(_cldTabIFrame == null)
		this.createCldTabFrm();
		
	//alert(_cldTabIFrame2)
		
	//??????????????  ??
	_cldTabIFrame2.style.display = "inline";
	
	//??????????????
	_debug(_oldDateType + "[]" + this._year + "-" + (this._month + 1) + "-" + this._date);

	//??????????
	this.fillCldTabFrm(this._year, this._month, this._date);

	//?????????????? ?? ????????????????????
	//    ????????
	if(!_bHaveShown || rltvO != _curCldTabRltvObj) 
		this.placeCldTabFrm();
	
	//_cldTabIFrame2.style.display = "inline";
	
	//????????????
	removeShadowDiv();

	//????????????
	MakeDivShadowEffect(_cldTabFrm, '#aaaaaa', _nShadowLength);

	//????????????????
	_curCldTabRltvObj = this._rltvO;
	
	//??????????????????
	_bHaveUpdated = true;
	_bHaveShown = true;
	
	//alert(_imgBgToday1.src);
}

//????????iframe
function createCldTabFrm(){

	var _sz = "<HTML>"
			+ "<HEAD><link href='" +_sNeededFilePath 
				+ "JSCalendar.css' rel=stylesheet type='text/css'>"
			+ "</HEAD>"
			+ "<BODY leftmargin=0 topmargin=0 rightmargin=0 "
			+ " bottommargin=0 style='background-color:transparent;border:0px solid black;scroll:no'>"
			+ "</BODY>"
			+ "</HTML>";
	_cldTabIFrame = frm;//document.all("frm");//document.createElement("IFRAME");
	_cldTabIFrame2 = document.all("frm");
	_cldTabIFrame2.style.position = "absolute";

	//_cldTabIFrame2.document.body.scroll = "no";

	/*_innerTabFrmRect.right - _innerTabFrmRect.left*/
	_cldTabIFrame2.style.pixelWidth =   375 + _nShadowLength;
	/*_innerTabFrmRect.bottom - _innerTabFrmRect.top*/ 
	_cldTabIFrame2.style.pixelHeight = 165 + _nShadowLength;	
	
	_cldTabIFrame.document.open("text/html","replace");
	_cldTabIFrame.document.write(_sz);
	_cldTabIFrame.document.close();

	//????????
	_cldTabFrm = _cldTabIFrame.document.createElement("TABLE");
	_cldTabFrm.id = "JACKSHANGJIELOVEFEIFEI"//????????
	_cldTabFrm.style.position = "absolute"; //????????
	_cldTabFrm.className = "calendar"; //????????
	_cldTabFrm.border = 0;	//????
	//_cldTabFrm.height = 165;
	_cldTabFrm.style.pixelWidth = 350; //????????
	_cldTabFrm.cellSpacing = 1; //??????????1px
	_cldTabFrm.cellPadding = 1; //????????????1px
	_cldTabFrm.bgColor = "ffffff"; //????????
	_cldTabFrm.attachEvent("onmouseover", whenMouseOverCldTabFrm); //????????????????
	_cldTabFrm.attachEvent("onmouseout", whenMouseOutCldTabFrm);   //????????????????

	// Create Calendar Control header
	var _TR = _cldTabFrm.insertRow();//??????
	var _TD = _TR.insertCell();//??????????
	_TD.colSpan = 7;//????????????7
	_TD.align = "center";//????????????
	_TD.innerHTML = "&nbsp;";//"<b>JS Calendar 1.0 by Joe</b>";	//????????


	// Create main block
	// ????7??
	for(var i = 0; i < 7; i++){
		_TR = _cldTabFrm.insertRow();//????????
		for(var j = 0; j < 7; j++){
			_TD = _TR.insertCell();//??????????
			_TD.style.cursor = "default";//??????????????
			_TD.align = "center";//????????
			_TD.width = 50;//????????????50
			//_TD.style.border = "1px solid black";
			_TD.innerHTML = "*"; //??????????*
			if(i != 0){
				_TD.style.cursor = "hand";
				_TD.attachEvent("onmouseover", whenMouseOverDateItem);
				_TD.attachEvent("onmouseout", whenMouseOutDateItem);
				_TD.attachEvent("onclick", whenClickDateItem);
			}
			if(i == 0) //??????????????????????
				_TD.innerHTML = "<b>" + _weeks[j] + "</b>";
			if(i == 0 && (j == 0 || j == 6)) //????????tdHoliday????
				_TD.className = "tdHoliday";
		}
	}

	// Create footer
	_TR = _cldTabFrm.insertRow(2);//???????????????? ????
	_TD = _TR.insertCell(); //??????????
	_TD.colSpan = 7; //??????????7
	_TD.height = 1;  //??????1
	_TD.bgColor = "black"; //??????????

	_TR = _cldTabFrm.insertRow();
	_TD = _TR.insertCell();
	_TD.colSpan = 7;
	//????????????:??-??-??  ???????? ????????
	_TD.innerHTML = "<table cellspacing=0 cellpadding=0 class=calendar style='border:0px solid;width:100%'>"
		+ "<tr><td title='Switch to TODAY.'"
		+ "style='cursor:hand' "
		+ "onclick=\"parent._bHaveUpdated=false;parent.setTargetFormaValue(" 
			+ _dftD.getFullYear()    + "," 
			+ (_dftD.getMonth()+ 1) + "," 
			+ _dftD.getDate()  + ");"
			+ "parent.fillCldTabFrm(" 
			+ _dftD.getFullYear()    + "," 
			+ _dftD.getMonth() + "," + _dftD.getDate() 
			+ ");\">"
		+ "<b>&nbsp;<img src='" + _imgBgToday1.src + "' width=30px>Today : " 
			  + _dftD.getFullYear() + "-" + (_dftD.getMonth() + 1) + "-" + _dftD.getDate()
		+ "</td>"
		+ "<td width=30px style='cursor:hand' onclick=parent.resetTargetValue() title='Reset the target value.'>"
		+ "<img src='" + _imgReset1.src + "' onmouseover=\"this.src='" + _imgReset2.src + "'\""
		+ " onmouseout=\"this.src='" + _imgReset1.src + "'\"></td>"
		+ "<td align=right class=smallFont>JS Calendar 1.0</td></tr></table>";
	
	//??????????????????????
	_cldTabIFrame.document.body.insertBefore(_cldTabFrm);
}

//????????????
function fillCldTabFrm(year, month, date){
	//return;
	var dCurDate = 0;
	var dNextMonthDate = 1;
	var iDateStartRow = 3;
	//var _d = new Date(this._year, this._month, 1);
	var _d = new Date(year, month, 1);
	//alert(year + "-" + month + "-" + date);
	var _day = _d.getDay();
	var _td = null;
    //??????
	_dCurYear = year;
	//??????
	_dCurMonth = month;
	//??????
	_dCurDate = date;

	if (((_dCurYear % 4 == 0) && !(_dCurYear % 100 == 0))
		||(_dCurYear % 400 == 0)) _monthDays[1] = 29;
	else _monthDays[1] = 28;
			
	_cldTabFrm.rows(0).cells(0).innerHTML
	 = "<table bgcolor=420042 class=calendar style=\"color:white;font-weight:bolder;border:0px solid;width:100%;height:20px;\" cellspacing=0 cellpadding=0>"
		+ "<tr><td>"
		+ "&nbsp;<a style='cursor:hand' title='Last Month' onclick=\"parent.switchLastMonth()\"><<</a>"
		+ "</td>"
		+ "<td align=center vAlign=middle>"
		+ "<map name=mapForBtnYears><area title='Next Year' href='Javascript:parent.switchLastYear()' shape=rect coords=0,0,10,5><area title='Last Year' href='Javascript:parent.switchNextYear()' shape=rect coords=0,5,10,15></map>"		
		+ "" + _months[_dCurMonth] + " , " + _dCurYear
		+ "&nbsp;<img border=0 align=absMiddle src='" + _imgBtnYear.src + "' style='height:12px;width:10px' usemap=#mapForBtnYears>&nbsp;"
		+ "</td>"
		+ "<td align=right>"
		+ "<a style='cursor:hand' title='Next Month' onclick=\"parent.switchNextMonth()\">>></a>&nbsp"
		+ "</td></tr></table>"
			
	_day = (_day == 0 ? 7 : _day);
	
	for(var i = _day - 1, dlt = 0; i >= 0; i--){
		_td = _cldTabFrm.rows(iDateStartRow).cells(i);
		//_td.disabled = true;
		_td.className = "lastMonth";
		_td.title = "";
		_td.name = "LASTMONTH";
		_td.style.backgroundColor = "transparent";
		_td.style.border = "0px solid";
		var _nextMonth = _dCurMonth - 1;
		if(_nextMonth < 0) _nextMonth = 11;
		_td.innerText = (_monthDays[_nextMonth] - (dlt++));
	}
	//alert(_cldTabFrm.rows.length);
	i = _day;
	for(var d = 1, iRow = iDateStartRow; d <= _monthDays[_dCurMonth] || iRow < 9; ){
		for(; i < 7; i++){
			dCurDate = d++;
			_td = _cldTabFrm.rows(iRow).cells(i);
			_td.disabled = false;
			_td.className = "normal";
			_td.name = "CURRENTMONTH";
			_td.style.backgroundColor = "transparent";
			_td.style.border = "0px solid";
			if(i == 0 || i == 6) _td.className = "tdHoliday";
			if(d - 1 > _monthDays[_dCurMonth]){
				dCurDate = dNextMonthDate++;
				//_td.disabled = true;
				_td.className = "nextMonth";
				_td.name = "NEXTMONTH";
			}
			//if(dCurDate < 10) dCurDate = "0" + dCurDate;
			_td.innerHTML = dCurDate;

			if(dCurDate == _dCurDate && _td.name == "CURRENTMONTH"){
				//_cldTabFrm.rows(iRow).cells(i).style.backgroundColor = "620662";
				//_cldTabFrm.rows(iRow).cells(i).background = "bgCurDate.gif";
				_cldTabFrm.rows(iRow).cells(i).className = "tdCurDate";
			}	
			if(dCurDate == _dftD.getDate() 
				&& _dCurMonth == _dftD.getMonth() 
				&& _dCurYear == _dftD.getFullYear()
				&& _td.name == "CURRENTMONTH"){
				_cldTabFrm.rows(iRow).cells(i).className = "tdToday";
				if(dCurDate == _dCurDate)
					_cldTabFrm.rows(iRow).cells(i).className = "tdTodayCurDate";
			}	
			if(dCurDate == _dftD.getDate() && dCurDate == _dCurDate && _td.name == "CURRENTMONTH"){
				//_td.style.backgroundColor = "#663366";
				//_td.style.border = "1px solid gray";
				//_cldTabFrm.rows(iRow).cells(i).className = "tdTodayCurDate";
			}	
			
		}
		i = 0;
		iRow++;
	}
}


function placeCldTabFrm(){
	
	var _rect = this._rltvO.getBoundingClientRect();
	var _bodyWidth = document.body.clientWidth;
	var _bodyHeight = document.body.clientHeight;
	
	var _tmp = _cldTabIFrame;
	//alert(_bodyHeight);
	_cldTabIFrame = _cldTabIFrame2;

	var _innerTabFrmRect = _cldTabFrm.getBoundingClientRect();
	//_debug(_innerTabFrmRect.right + "," + _innerTabFrmRect.left)

	_cldTabIFrame2.style.pixelWidth = /*_innerTabFrmRect.right - _innerTabFrmRect.left*/  375 + _nShadowLength;
	_cldTabIFrame2.style.pixelHeight = _innerTabFrmRect.bottom - _innerTabFrmRect.top + _nShadowLength;	
	
	_cldTabIFrame.style.pixelLeft = _rect.left - 2 + document.body.scrollLeft;
	_cldTabIFrame.style.pixelTop = _rect.bottom - 2 + document.body.scrollTop;
	
	var _cldTabFrmRect = _cldTabIFrame.getBoundingClientRect();
		
	if(_cldTabFrmRect.right > _bodyWidth){
		_cldTabIFrame.style.pixelLeft -= (_cldTabFrmRect.right - _bodyWidth + _nShadowLength);
	}
	
	if(_cldTabFrmRect.bottom > _bodyHeight){
		//alert("dd");
		_cldTabIFrame.style.pixelTop = _rect.top - _nShadowLength;
		_cldTabIFrame.style.pixelTop -= (_cldTabFrmRect.bottom - _cldTabFrmRect.top - document.body.scrollTop);
	}
	
	//alert(_cldTabIFrame.style.pixelWidth);
	_cldTabIFrame = _tmp;
	//if(
}

function whenMouseOverCldTabFrm(){
	_bCanHide = false;
}

function whenMouseOutCldTabFrm(){
	_bCanHide = true;
}

function getNextDate(year, month, date){
	if(date == null) date = 1;
	if(date > _monthDays[month + 1]) date = _monthDays[month + 1];
	return new Date(year, month + 1, date);
}

function getLastDate(year, month, date){
	if(date == null) date = 1;
	if(date > _monthDays[month - 1]) date = _monthDays[month - 1];
	return new Date(year, month - 1, date);
}
function switchLastMonth(bLast){
	if(bLast == null) bLast = true;
	var _tmpdate = null;
	_tmpdate = bLast ? getLastDate(_dCurYear, _dCurMonth, _dCurDate) : getNextDate(_dCurYear, _dCurMonth, _dCurDate);
	//_curCldTabRltvObj.value = _tmpdate.getFullYear() + "-" + (_tmpdate.getMonth() + 1) + "-" + _tmpdate.getDate();
	setTargetFormaValue(_tmpdate.getFullYear(), _tmpdate.getMonth() + 1, _tmpdate.getDate());
	_bHaveUpdated = false;
	//JSCalendar(_curCldTabRltvObj);	
	fillCldTabFrm(_tmpdate.getFullYear(), _tmpdate.getMonth(), _tmpdate.getDate());;
}
function switchNextMonth(){
	switchLastMonth(false);
}
function switchLastYear(){
	//_curCldTabRltvObj.value = (_dCurYear * 1 + 1) + "-" + (_dCurMonth + 1) + "-" + _dCurDate;
	setTargetFormaValue(_dCurYear * 1 + 1, _dCurMonth * 1 + 1, _dCurDate);
	_bHaveUpdated = false;
	//JSCalendar(_curCldTabRltvObj);	
	fillCldTabFrm(_dCurYear * 1 + 1, _dCurMonth * 1, _dCurDate);
}
function switchNextYear(){
	//_curCldTabRltvObj.value = (_dCurYear - 1) + "-" + (_dCurMonth + 1) + "-" + _dCurDate;
	setTargetFormaValue(_dCurYear * 1 - 1, _dCurMonth * 1 + 1, _dCurDate);
	_bHaveUpdated = false;
	//JSCalendar(_curCldTabRltvObj);	
	fillCldTabFrm(_dCurYear * 1 - 1, _dCurMonth * 1, _dCurDate);
}
function whenMouseOverDateItem(){
	var e = _cldTabIFrame.event.srcElement;
	var _tmpdate = null;
	if(e.tagName == "TD"){
		if(e.name == "LASTMONTH"){
			_tmpdate = getLastDate(_dCurYear, _dCurMonth);
			e.title = "Last : " + _tmpdate.getFullYear() + "-" + (_tmpdate.getMonth() + 1) + "-" + e.innerText;
			return;
		}
		if(e.name == "NEXTMONTH"){
			_tmpdate = getNextDate(_dCurYear, _dCurMonth);
			e.title = "Next : " + _tmpdate.getFullYear() + "-" + (_tmpdate.getMonth() + 1) + "-" + e.innerText;
			return;
		}
	
		var sCurDate = _dCurYear + "-" + (_dCurMonth + 1) + "-" + e.innerText;
		e.title = "Current : " + sCurDate;
		
		e.style.backgroundColor = "#EFEFEF";
		setTargetFormaValue(_dCurYear, _dCurMonth + 1, e.innerText);
	}	
}

function resetTargetValue(){
	_curCldTabRltvObj.value = "";
	_bHaveSelectNewValue = true;
	hideCldTabFrm();	
}

function setTargetFormaValue(year, month, date){
	var _year, _month, _date;
	_year = year;
	_month = month * 1;
	_date = date * 1;
	if(_month < 10) _month = "0" + _month;
	if(_date < 10) _date = "0" + _date;
	//you can change the date view format!!
	_curCldTabRltvObj.value = _year + "-" + _month + "-" + _date;
}

function whenMouseOutDateItem(){
	var e = _cldTabIFrame.event.srcElement;
	if(e.tagName == "TD")
		e.style.backgroundColor = "";
}
function whenClickDateItem(){
	var e = _cldTabIFrame.event.srcElement;
	var _tmpdate = null;
	var _month = null;
	var _date = null;
	if(e.tagName == "TD"){
		_bHaveUpdated = false;
		if(e.name == "LASTMONTH"){
			_tmpdate = getLastDate(_dCurYear, _dCurMonth);
			setTargetFormaValue(_tmpdate.getFullYear(), _tmpdate.getMonth() + 1, e.innerText);
			fillCldTabFrm(_tmpdate.getFullYear(), _tmpdate.getMonth(), e.innerText);
			/* new */ //JSCalendar(_curCldTabRltvObj);
			return;
		}
		if(e.name == "NEXTMONTH"){
			_tmpdate = getNextDate(_dCurYear, _dCurMonth);
			setTargetFormaValue(_tmpdate.getFullYear(), _tmpdate.getMonth() + 1, e.innerText);
			/* new */ //JSCalendar(_curCldTabRltvObj);
			fillCldTabFrm(_tmpdate.getFullYear(), _tmpdate.getMonth(), e.innerText);
			return;
		}
		_bHaveSelectNewValue = true;
		hideCldTabFrm();
	}
}

function hideCldTabFrm(){
	//try{
	if(!_bHaveSelectNewValue)
		if(_cldTabIFrame == null || !_bCanHide) return;

	//alert(_cldTabIFrame);		
	var oFiredObj = null;
	try{
		oFiredObj = event.srcElement;
	}catch(e){
		oFiredObj = _cldTabIFrame.event.srcElement;
	}
	if(oFiredObj == _curCldTabRltvObj) return;
	//alert(_curCldTabRltvObj);//
		
	_cldTabIFrame2.style.display = "none";
	_bHaveUpdated = false;
	_bHaveShown = false;
	removeShadowDiv();
	//alert(_bHaveSelectNewValue);
	if(!_bHaveSelectNewValue)
		_curCldTabRltvObj.value = _curCldTabRltvObjValue;
	//}catch(e){_debug(e.description);}	
}

function removeShadowDiv(){
	try{
		var arrShadowDiv = eval("window.document.arr" + _cldTabFrm.id);
		for(var i = 0; i < arrShadowDiv.length; i++)
			arrShadowDiv[i].removeNode(true);
	}catch(e){_debug(e.description);}	
}

function MakeDivShadowEffect(divObj, color, nLength)
{
	//alert(divObj);
	var tmpstr = "window.document.arr" + divObj.id + " = new Array();";
	eval(tmpstr);
	//alert( tmpstr );
	var arrShadowDiv = eval("window.document.arr" + divObj.id);
	//window.document.arrJACKSHANGJIELOVEFEIFEI = new Array();
	//var arrShadowDiv = window.document.arrJACKSHANGJIELOVEFEIFEI;
	//alert(arrShadowDiv.length);
	var _rect = divObj.getBoundingClientRect();
	for( i = nLength; i > 0; i --)
	{
		var rect = _cldTabIFrame.document.createElement( "DIV" );
		rect.style.position = "absolute";
		rect.style.left = (divObj.style.posLeft + i ) + "px";
		rect.style.top = (divObj.style.posTop + i ) + "px";
		rect.style.width = divObj.offsetWidth + "px";
		rect.style.height = divObj.offsetHeight + "px";
		rect.style.backgroundColor = color;
		var opacity = 1 - i / (i + 1);
		//alert(rect.style.width);
		rect.style.filter = 'alpha(opacity=' + (100 * opacity) + ')';
		rect.style.zIndex = divObj.style.zIndex - 1;
		//alert(divObj.tagName);
		//divObj.insertAdjacentElement("beforeEnd", rect);
		_cldTabIFrame.document.body.insertBefore(rect);
		arrShadowDiv[arrShadowDiv.length] = rect;
		//alert( i );
	}	
}

//????????????
function _debug(s){
	return;
	//var o = new Option(s, s);
	//_debugLst.add(o);
	form1._debugLst.options[form1._debugLst.length] = new Option(s, s, 0, 0);
	form1._debugLst.options[form1._debugLst.length - 1].selected = true;
	//_debugLst.item(_debugLst.length - 1).selected = true;
}
document.onclick = hideCldTabFrm;
//createCldTabFrm();
//-->