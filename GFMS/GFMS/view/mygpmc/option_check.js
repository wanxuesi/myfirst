/***************************************** 
���÷���Ϊ�� 
Jselect($("#inputid"),{ 
bindid:'bindid', 
hoverclass:'hoverclass', 
optionsbind:function(){return hqhtml();} 
}); 
inputidΪ������Ҫ�󶨵��ı���id 
bindidΪ�������/�ջ�������Ŀؼ�id 
hoverclassΪ����Ƶ�ѡ��ʱ����ʽ 
hqhtmlΪ�󶨵����� 
******************************************/ 
(function($){ 
$.showselect = { 
init : function(o,options){ 
var defaults = { 
bindid : null, //�¼�����bindid�� 
hoverclass:null, //����Ƶ�ѡ��ʱ��ʽ���� 
optionsbind:function(){} //������󶨺��� 
} 
var options = $.extend(defaults,options); 
if(options.optionsbind!=null){//����󶨺�����Ϊ�� 
this._setbind(o,options); 
} 
if(options.bindid!=null){ 
this._showcontrol(o,options); 
} 
}, 
_showcontrol:function(o,options){//������������ʾ 
$("#"+options.bindid).toggle(function(){ 
$(o).next().slideDown(); 
},function(){ 
$(o).next().slideUp(); 
}) 
}, 
_setbind:function(o,options){//������ 
var optionshtml="<div style=\"z-index: 999; position: absolute;\">" 
+options.optionsbind()+"</div>"; 
$(o).after(optionshtml); 
var offset= $(o).offset(); 
var w=$(o).width(); 
$(o).next().css({top:offset.top+$(o).height()+7,left:offset.left,width:w}); 
if(options.hoverclass!=null){ 
$(o).next().find("tr").hover(function(){$(this).addClass(options.hoverclass);}, 
function(){$(this).removeClass(options.hoverclass);}); 
} 
$(o).next().find("input[type=checkbox]").filter("[lang=checked]").each(function(){$(this).attr("checked","checked");}); 
$(o).next().find("input[type=checkbox]").click(function(){ 
var $ckoption=$(this).attr("checked"); 
if($ckoption){ 
$(this).attr("checked",""); 
}else{ 
$(this).attr("checked","checked"); 
} 
}); 
$(o).next().find("tr").click(function(){ 
var $ckflag=$(this).find("input[type=checkbox]"); 
if($ckflag.attr("checked")){ 
$ckflag.attr("checked",""); 
$ckflag.attr("lang",""); 
} 
else{ 
$ckflag.attr("checked","checked"); 
$ckflag.attr("lang","checked"); 
} 
var selarray=new Array(); 
$(o).next().find("input[type=checkbox]").each(function(){ 
if($(this).attr("checked")) 
selarray.push($(this).parent().next().text()); 
}); 
$(o).val(selarray.join(',')); 
}); 
$(o).next().hide(); 
} 
} 
Jselect = function(o,json){ 
$.showselect.init(o,json); 
}; 
})(jQuery);