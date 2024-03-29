﻿/*=========================================================
*Copyright(c) 2017 CyberLogitec. All Rights Reserved.
*@FileName 	 : COM_ENS_0U2.js
*@FileTitle  : Approval Inquiry
*@author     : CLT
*@version    : 1.0
*@since      : 2017/07/03
=========================================================*/
// common global variables 
var sheetObjects=new Array();
var sheetCnt=0;
var comboObjects = new Array();
var comboCnt=0;
var sColSep="☜☞";
var selOfcCd = "";
// Event handler processing by button click event*/
document.onclick=processButtonClick;
// Event handler processing by button name */
function processButtonClick(){
 var sheetObject=sheetObjects[0];
 var formObject=document.form;	
	try {
		var srcName=ComGetEvent("name");
		if(ComGetBtnDisable(srcName)) return false;
		switch(srcName) {
			case "btns_multisearch":
				OnPopupClick();
            break;
			case "btn_retrieve":
	         	doActionIBSheet(sheetObject,formObject,IBSEARCH);
	        break;
			case "btn_new":
				ComResetAll();
				rhq_ofc_cd.SetSelectCode(selOfcCd);
				a_rhq_ofc_cd.SetSelectCode(selOfcCd);
				formObject.edate.value = ComGetNowInfo("ymd");
			    var sDate = ComGetDateAdd(formObject.edate.value, "M", -1);
			    formObject.sdate.value = ComGetDateAdd(sDate, "D", 1);
	        break;
			case "btns_Calendar2" : // Agreement Date (To Date)
 	    		var cal=new ComCalendarFromTo();
	            cal.select(formObject.sdate,  formObject.edate,  'yyyy-MM-dd');
 	    	break;   
			case "btn_downexcel":
				if(sheetObject.RowCount() < 1){//no data
					ComShowCodeMessage("COM132501");
				}else{
					sheetObject.Down2Excel({ SheetDesign:1, HiddenColumn:1, Merge:1 });
				}
			break;
			case "btn_detail":
				GoDetail();
    	    break;
			case "btn_approvalstep":
				var selRow=sheetObject.GetSelectRow();
    	        if(selRow <= 0) {
    	            ComShowCodeMessage("COM12176");
    	            return;
    	        }              	
            	var apro_rqst_no=sheetObject.GetCellValue(selRow, "apro_rqst_no");
				if (apro_rqst_no == undefined || apro_rqst_no == null || apro_rqst_no == ''||apro_rqst_no == -1 ){
					ComShowCodeMessage("COM132201", 'Approval Request No.');
					return false;
				}                	
                var url="/opuscntr/COM_ENS_0W1.do?apro_rqst_no="+apro_rqst_no;
                 ComOpenWindowCenter(url,  "COM_ENS_0W1", 800,220, false);
    	    break;
            case "btn_vndr":
				var param='';
				//ComOpenPopupWithTarget('/opuscntr/COM_ENS_0C1.do', 700, 550, '2:vndr_seq|4:vndr_nm', '1,0,1,1,1', true);
				ComOpenPopupWithTarget('/opuscntr/COM_ENS_0C1.do', 700, 550, '2:vndr_seq', '1,0,1,1,1', true);
			break;
        } // end switch
	}catch(e) {
		if( e == "[object Error]") {
			ComShowMessage(OBJECT_ERROR);
		} else {
			ComShowMessage(e.message);
		}
	}
}

function OnPopupClick(){
	var formObject=document.form;
	var xx1="";
    var title="CSR NO.";
	var param="?returnval="+xx1+"&returntitle="+title+"&pgmNo=COM_ENS_0U2";
	ComOpenPopup('/opuscntr/COM_ENS_0906.screen' + param, 420, 390, 'getCOM_ENS_0906', '1,0,1,1,1,1,1,1,1,1,1,1');
}

function getCOM_ENS_0906(rowArray,returnval) {
	var formObject=document.form;
	var x1=document.form.csr_no.value;
	document.form.csr_no.value=rowArray;
	formObject.csr_no.focus();
}

/**
 * registering IBSheet Object as list 
 * adding process for list in case of needing batch processing with other items
 * defining list on the top of source
 */
function setSheetObject(sheet_obj){
	sheetObjects[sheetCnt++]=sheet_obj;
}
/**
 * Registering IBMultiCombo Object generated on Page to comboObject Array <br>
 * comboObjects Array is defined on the top of source. This function is called automatically when IBMultiCombo Object is generated by {@link CoObject#ComComboObject} <br>
 * @param {ibmulticombo} combo_obj    IBMultiCombo Object
 **/
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}

/**
 * initializing sheet 
 * implementing onLoad event handler in body tag 
 * adding first-served functions after loading screen. 
 */
function loadPage() {
	var sheetObject=sheetObjects[0];
	var formObject=document.form;
	for(i=0;i<sheetObjects.length;i++){
	    ComConfigSheet (sheetObjects[i]);
	    initSheet(sheetObjects[i],i+1);
	    ComEndConfigSheet(sheetObjects[i]);
	}
	initControl();
	formObject.edate.value = ComGetNowInfo("ymd");
    var sDate = ComGetDateAdd(formObject.edate.value, "M", -1);
    formObject.sdate.value = ComGetDateAdd(sDate, "D", 1);
	doActionIBSheet(sheetObject, formObject, IBSEARCH_ASYNC01);//RHQ.
	a_rhq_ofc_cd.SetEnable(0);
	a_ofc_cd.SetEnable(0);
}

function initControl() {
	var formObject=document.form;
	axon_event.addListenerFormat('change','obj_change',formObject);
}

function obj_change(){
	var obj=ComGetEvent();
	var formObj=document.form;
	var sheetObj=sheetObjects[0];
	if ( ComTrim(obj.value) != "" ) {
		switch(ComGetEvent("name")) {
    		case "vndr_seq":
        		doActionIBSheet(sheetObj, formObj , IBSEARCH_ASYNC03);
			   	break;
		}
    } else {
		switch(ComGetEvent("name")) {
    		case "vndr_seq":
        		formObj.vndr_nm.value="";
			   	break;
		}
	}
}

/**
 * init sheet
 */
function initSheet(sheetObj,sheetNo) {
    var cnt=0;
    switch(sheetNo) {
        case 1:      //IBSheet1 init
            with(sheetObj){
             var HeadTitle="||NO||STS|Remark|Module|Date|GL Date|Cost Office|Invoice Office|CSR No.|No. Of INV|Payment S/P|Payment S/P|Payment Due Date|Currency|Total Amount|Payment\nMzd Cd|Payment\nMethod|Bank\nAcct||" ;

             SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:0 } );

             var info    = { Sort:1, ColMove:1, HeaderCheck:1, ColResize:1 };
             var headers = [ { Text:HeadTitle, Align:"Center"} ];
             InitHeaders(headers, info);

             var cols = [{Type:"CheckBox",  Hidden:1, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"checkbox",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
		                 {Type:"Status",    Hidden:0, Width:0,    Align:"Center",  ColMerge:1,   SaveName:"ibflag",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:110,   Align:"Center",  ColMerge:0,  SaveName:"apro_rqst_no",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:1, Width:0,    Align:"Center",  ColMerge:0,   SaveName:"apro_rqst_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:110,  Align:"Center",  ColMerge:0,   SaveName:"apsts_cd",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:100,  Align:"Left",    ColMerge:0,   SaveName:"apro_rmk",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:70,   Align:"Center",  ColMerge:0,   SaveName:"sub_sys_cd",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:0,   SaveName:"rqst_st_dt",     KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:0,   SaveName:"gl_dt",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:0,   SaveName:"cost_ofc_cd",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:0,   SaveName:"inv_ofc_cd",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:160,  Align:"Center",  ColMerge:0,   SaveName:"csr_no",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:130,  Align:"Center",  ColMerge:0,   SaveName:"inv_knt",        KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:110,  Align:"Center",  ColMerge:0,   SaveName:"vndr_seq",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:160,  Align:"Left",    ColMerge:0,   SaveName:"vndr_nm",        KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:130,  Align:"Center",  ColMerge:0,   SaveName:"pay_due_dt",     KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"curr_cd",        KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Float",     Hidden:0, Width:100,  Align:"Right",   ColMerge:0,   SaveName:"apro_ttl_amt",   KeyField:0,   CalcLogic:"",   Format:"Float",       PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:1, Width:50,   Align:"Center",  ColMerge:0,   SaveName:"pay_mzd_cd",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"pay_mzd_nm",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:0, Width:50,   Align:"Center",  ColMerge:0,   SaveName:"bank_acct_flg",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
		                 {Type:"Text",      Hidden:1, Width:0,    Align:"Center",  ColMerge:0,   SaveName:"appyn",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 } ];
              
             InitColumns(cols);

             SetEditable(1);
             SetColHidden(1,1);
             resizeSheet();
         }
         break;
    }
}

/**
 * Handling IBSheet's process(Retrieve, Save) <br>
 * @param {ibsheet} sheetObj Mandatory IBSheet Object
 * @param {form}    formObj Mandatory html form object
 * @param {int}     sAction mandatory,Constant Variable
 **/
function doActionIBSheet(sheetObj, formObj, sAction, arg) {
    switch(sAction) {
    	case IBSEARCH:
    		if(!isValidate(formObj)) return false;
			formObj.f_cmd.value=SEARCH;
			sheetObj.DoSearch("COM_ENS_0U2GS.do", FormQueryString(formObj) + "&" + ComGetPrefixParam(""));
    		break;
    	case IBSEARCH_ASYNC01:				
    		formObj.f_cmd.value=SEARCH01;
			var sXml=sheetObj.GetSearchData("COM_ENS_0U2GS.do", FormQueryString(formObj));
			var sStr=ComGetEtcData(sXml,"rhq");
			var arrStr=sStr.split("|");
			var rhqOfcCd=ComGetEtcData(sXml,"rhq_ofc_cd");
			MakeComboObject(rhq_ofc_cd, arrStr, rhqOfcCd);
			MakeComboObject(a_rhq_ofc_cd, arrStr, rhqOfcCd);
        break;
		case IBSEARCH_ASYNC02: // 화면 로딩시 Office 조회
			var v_rhq_ofc_cd;
			var v_ofc_cd;
			if(arg == "C") {
				v_rhq_ofc_cd = rhq_ofc_cd;
				v_ofc_cd = c_ofc_cd;
			} else {
				v_rhq_ofc_cd = a_rhq_ofc_cd;
				v_ofc_cd = a_ofc_cd;
			}
			if(v_rhq_ofc_cd.GetSelectCode() == "SINHO") {
				v_ofc_cd.RemoveAll();
				v_ofc_cd.InsertItem(0, "SINHO", "SINHO");
			} else {
				formObj.f_cmd.value=SEARCH02;
				formObj.v_rhq_ofc_cd.value = v_rhq_ofc_cd.GetSelectCode();
				var sXml=sheetObj.GetSearchData("COM_ENS_0U2GS.do", FormQueryString(formObj));
				var aXml=sXml.split("|$$|");
				ComXml2ComboItem(aXml[0], v_ofc_cd, "ofc_cd","ofc_cd" ); 
			}
		 	with (v_ofc_cd) {
		 		SetMultiSelect(1);
		 		SetMultiSeparator(",");
		 		SetDropHeight(320);
		 	}
		 	v_ofc_cd.SetSelectCode(formObj.usr_ofc_cd.value);
		 	if(v_ofc_cd.GetSelectCode() == '') {
		 		v_ofc_cd.SetSelectCode(0);
		 	}
			if(sheetObj.RowCount()> 0)
	    		sheetObj.RemoveAll();
		break;
        case IBSEARCH_ASYNC03:
        	if (validateForm(sheetObj, formObj, sAction)) {
        		var sXml=CsrGetRepairPartner(sheetObj,formObj.vndr_seq.value);
        		if(ComGetEtcData(sXml, "vndr_seq") != 'null'){
        			ComSetObjValue(formObj.vndr_nm, ComGetEtcData(sXml, "vndr_lgl_eng_nm"));
        		} else {
        			ComShowCodeMessage("COM12114", "Service Provider");
        			ComSetObjValue(formObj.vndr_nm, "");
        			ComSetObjValue(formObj.vndr_seq, "");
        			ComSetFocus(formObj.vndr_seq);
        		}
        	}
     	break;
    }
}

function sheet_OnSearchEnd() {
	var sheetObj=sheetObjects[0];
	for (var i=sheetObj.HeaderRows(); i<(sheetObj.HeaderRows() + sheetObj.RowCount()); i++){
		sheetObj.SetToolTipText(i,"pay_mzd_nm",sheetObj.GetCellValue(i,"pay_mzd_cd"));
    }
}


var selComboIndex, selComboCode;
function c_ofc_cd_OnSelect(comboObj, index, text, code) {
	selComboIndex = index;
	selComboCode = code;
}

function c_ofc_cd_OnCheckClick(comboObj) {
	CsrSetMultiCombo(comboObj, selComboIndex, selComboCode);
} 

function a_ofc_cd_OnSelect(comboObj, index, text, code) {
	selComboIndex = index;
	selComboCode = code;
}

function a_ofc_cd_OnCheckClick(comboObj) {
//	CsrSetMultiCombo(comboObj, selComboIndex, selComboCode);
	ComSetMultiCombo(comboObj, selComboIndex, selComboCode);
} 

function CsrSetMultiCombo(comboObj, index, code) {
	var formObj = document.form;
    var idx = parseInt(index);
    //All 일 경우
    if(index == 0) {
    	var count = parseInt(comboObj.GetItemCount())-1;
        if(comboObj.GetItemCheck(idx)) {
            for(var i=count ; i > 0 ; i--) {
                comboObj.SetItemCheck(i, true, null, null, false);
            }
        } else {
            for(var i=count ; i > 0 ; i--) {
                comboObj.SetItemCheck(i, false, null, null, false);
            }
        }
    } else {
    //All 이 아닌 경우
    	comboObj.SetItemCheck(0, false, null, null, false);
    }
}

// RHQ.Office 변경시, Office code 변경
function rhq_ofc_cd_OnChange(sheetObj)
{	
	var formObj=document.form;
	doActionIBSheet(sheetObjects[0], formObj, IBSEARCH_ASYNC02, "C");
}

//RHQ.Office 변경시, Office code 변경
function a_rhq_ofc_cd_OnChange(sheetObj)
{
	var formObj=document.form;
	doActionIBSheet(sheetObjects[0], formObj, IBSEARCH_ASYNC02, "A");
}

/**
 * Detail popup을 표시한다.
 */
function sheet_OnDblClick(Row, Col, CellX, CellY, CellW, CellH) {
	GoDetail();
}

function resizeSheet(){
    ComResizeSheet(sheetObjects[0]);
} 

/**
 * Validate Check<br>
 */
function isValidate(formObj) {
    with(formObj){
    	var start_date = sdate.value;
    	var end_date = edate.value;
    	if(start_date == "" || end_date == "") {
    		ComShowCodeMessage("COM130201", "Date");
    		return false;
    	}
    	if(ComChkPeriod(ComReplaceStr(start_date,"-",""), ComReplaceStr(end_date,"-","")) < 1)  {
    		ComShowCodeMessage("COM132002"); // End date must be greater than start date
    		return false;
    	}
    	var tmp_date=ComGetDateAdd(start_date, "M", 1);
    	tmp_date=ComGetDateAdd(tmp_date, "D", -1);
    	if(ComGetDaysBetween(tmp_date, end_date)>0){
    		ComShowMessage("Date should be set within one month."); 
    		return false;
    	}
    }
	return true;
}

/** 
 * office code select box <br>
 * @param {IBMultiCombo} comboObj  
 * @param  {Array} arrStr
 */
function MakeComboObject(cmbObj, arrStr1, arrStr2) {
	var formObj = document.form;
	cmbObj.RemoveAll();
	var isExist = false;
	for (var i=1; i < arrStr1.length;i++ ) {
		var ar_hd_qtr_ofc_cd='';
		if (arrStr1[i] != '') {
			ar_hd_qtr_ofc_cd=arrStr1[i];
		}
		cmbObj.InsertItem(i-1, ar_hd_qtr_ofc_cd, ar_hd_qtr_ofc_cd);
		if(arrStr2 == ar_hd_qtr_ofc_cd) {
			isExist = true;
		}
	}
	if(formObj.usr_ofc_cd.value == "SINHO") {
		cmbObj.InsertItem(0, "SINHO", "SINHO");
		isExist = true;
	}
	if(isExist) {
		cmbObj.SetSelectCode(arrStr2);
		selOfcCd = arrStr2;
	} else {
		cmbObj.SetSelectCode(arrStr1[1]);
		selOfcCd = arrStr1[1];
	}
//	cmbObj.SetBackColor("#CCFFFD");
	cmbObj.SetSelectCode(selOfcCd);
}

function sheet_OnDblClick(sheetObj, Row, Col, CellX, CellY, CellW, CellH) {
    with (sheetObj) {
        if (ComGetLenByByte(GetCellValue(Row, "ots_seq")) > 0 ) {
        	 ComOpenPopup("/opuscntr/ESM_FLT_0037_POP.do?" + "ots_seq=" + GetCellValue(Row , "ots_seq"), 1120, 682, "", "0,1", true);
        }
    }
}

function vndr_seq_change() {
	var sheetObject=sheetObjects[0];
    var formObject=document.form;
    
    doActionIBSheet(sheetObject,formObject,IBSEARCH_ASYNC02, 'VM');
}

function GoDetail() {
	var sheetObject = sheetObjects[0];
	if(sheetObject.RowCount()==0){
		ComShowCodeMessage( "COM130401");
		return;                		
	}
    var selRow=sheetObject.GetSelectRow();
    if(selRow < 1) {
        ComShowMessage(ComGetMsg("COM12176"));
        return;
    }
    var v_csr_no=sheetObject.GetCellValue(selRow, "csr_no");
    var height=screen.height; 
	var width=screen.width;
	var url="";
	var subSysCd=sheetObject.GetCellValue(selRow, "sub_sys_cd");
    if(subSysCd == "ACM") {
        var param="?csr_no=" + v_csr_no;
        ComOpenWindowCenter("ESM_AGT_0043.do" + param, "compop3", "700", "570", false);
    }else if(subSysCd == "LSE" || subSysCd == "CHS" || subSysCd == "MGS" || subSysCd == "MNR" || subSysCd == "PSO" || subSysCd == "TLL" || subSysCd == "CNI" ) {
        var w=800;
        var h=560;
        var leftpos=width/2 - w/2; 
    	var toppos=height/2 - h/2; 
    	if(leftpos<0) leftpos=0;
    	if(toppos<0) toppos=0;
		var csrNo=sheetObject.GetCellValue(sheetObject.GetSelectRow(),'csr_no');
		var costOfcCd=sheetObject.GetCellValue(sheetObject.GetSelectRow(),'cost_ofc_cd');
		var currCd=sheetObject.GetCellValue(sheetObject.GetSelectRow(),'curr_cd');
		var invSubSysCd=sheetObject.GetCellValue(selRow, "sub_sys_cd");
		 ComOpenWindowCenter("/opuscntr/COM_CSR_0011.do?csr_no="+csrNo+"&cost_ofc_cd="+costOfcCd+"&inv_sub_sys_cd="+invSubSysCd+"&curr_cd="+currCd, window, 860, 420, true);
    } else if(subSysCd == "TES") {
        var w=800;
        var h=560;
        var leftpos=width/2 - w/2; 
    	var toppos=height/2 - h/2; 
    	if(leftpos<0) leftpos=0;
    	if(toppos<0) toppos=0;
        var url="/opuscntr/ESD_TES_0026.do?csr_no="+v_csr_no;
        window.open(url, "detailPop", "status=no, width="+w+", height="+h+", resizable=no, scrollbars=no, left="+leftpos+", top="+toppos);
     } else if(subSysCd == "TRS") {
         var url="/opuscntr/ESD_TRS_0960.do?mode=trs&csr_no="+v_csr_no;
         ComOpenWindowCenter(url, window, 855, 420, true);
     }
}

function change_radio() {
	var formObject=document.form;
	if(formObject.rdo_ofc_tp.value == "C") {
		a_rhq_ofc_cd.SetEnable(0);
		a_ofc_cd.SetEnable(0);
		rhq_ofc_cd.SetEnable(1);
		c_ofc_cd.SetEnable(1);
	} else {
		a_rhq_ofc_cd.SetEnable(1);
		a_ofc_cd.SetEnable(1);
		rhq_ofc_cd.SetEnable(0);
		c_ofc_cd.SetEnable(0);
	}
}