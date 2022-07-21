/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GEN_TRN_0004.js
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
/****************************************************************************************
  이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
					[수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
					기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
   /**
     * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
     * @author 한진해운
     */

    /**
     * @extends 
     * @class GEN_TRN_0004 : GEN_TRN_0004 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */

    
   	/* 개발자 작업	*/
var sheetObjects = new Array();
var sheetCnt = 0;
var comboObjects = new Array();
var comboCnt = 0;
var flagValidate = true;
var flagCheckAll = false;

document.onclick = processButtonClick;

/**
 * Setting sheet object
 * @param sheet_obj
 */
function setSheetObject(sheet_obj) {
	sheetObjects[sheetCnt++] = sheet_obj;
}

/**
 * {setComboObject} Put sheet objects in global variable "sheetObjects".<br>
 * @param {ibmulticombo} combo_obj    	IBMultiCombo Object
 **/
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}

/**
 * Load init page
 */
function loadPage(){
	// Generate Grid Layout
	for(i=0; i<sheetObjects.length; i++){
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i+1);
		ComEndConfigSheet(sheetObjects[i]);
	}
	
	for(i=0; i<comboObjects.length; i++){
		initCombo(comboObjects[i], i+1);
	}
	initControl();
}

/**
 * Setting init sheet
 */
function initSheet(sheetObj,sheetNo) {
	var cnt = 0;
	switch (sheetNo) {
	case 1: // sheet1 init
		with (sheetObj) {

			var HeadTitle = "|Chk|Carrier|Rev.Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";

			// Configure how to fetch initialized sheet
			SetConfig({SearchMode : 0, MergeSheet : 5, Page : 20, DataRowMerge : 0});

			// Setting header of sheet
			var info = {Sort : 1, ColMove : 0, HeaderCheck : 0, ColResize : 1};
			var headers = [ { Text : HeadTitle, Align : "Center" }];
			InitHeaders(headers, info);

			// define column
			var cols = [
			            {Type: "Status", 	Hidden: 1, Width: 30, 	Align: "Center", ColMerge: 0, SaveName: "ibflag"},
			            {Type: "DelCheck", 	Hidden: 0, Width: 30, 	Align: "Center", ColMerge: 0, SaveName: "chk", 			KeyField: 0},
			            {Type: "Text", 		Hidden: 0, Width: 100, 	Align: "Center", ColMerge: 0, SaveName: "jo_crr_cd", 	KeyField: 1, UpdateEdit: 0, InsertEdit: 1, EditLen: 3},
			            {Type: "Combo", 	Hidden: 0, Width: 100, 	Align: "Center", ColMerge: 0, SaveName: "rlane_cd", 	KeyField: 1, UpdateEdit: 1, InsertEdit: 1, EditLen: 5, ComboText: rLaneCombo, ComboCode: rLaneCombo},
			            {Type: "Float", 	Hidden: 0, Width: 100, 	Align: "Center", ColMerge: 0, SaveName: "vndr_seq", 	KeyField: 1, UpdateEdit: 1, InsertEdit: 1, EditLen: 6, MinimumValue: 100000},
			            {Type: "Text", 		Hidden: 0, Width: 50, 	Align: "Center", ColMerge: 0, SaveName: "cust_cnt_cd", 	KeyField: 1, UpdateEdit: 1, InsertEdit: 1, EditLen: 2},
			            {Type: "Float", 	Hidden: 0, Width: 100, 	Align: "Center", ColMerge: 0, SaveName: "cust_seq", 	KeyField: 1, UpdateEdit: 1, InsertEdit: 1, EditLen: 6},
			            {Type: "Text", 		Hidden: 0, Width: 100, 	Align: "Center", ColMerge: 0, SaveName: "trd_cd", 		KeyField: 0, UpdateEdit: 1, InsertEdit: 1, EditLen: 3},
			            {Type: "Combo", 	Hidden: 0, Width: 80, 	Align: "Center", ColMerge: 0, SaveName: "delt_flg", 	KeyField: 0, UpdateEdit: 1, InsertEdit: 1, ComboText: "Y|N", ComboCode: "Y|N"},
			            {Type: "Text", 		Hidden: 0, Width: 300, 	Align: "Center", ColMerge: 0, SaveName: "cre_dt", 		KeyField: 0, UpdateEdit: 0, InsertEdit: 0, Format: "YmdHms"},
			            {Type: "Text", 		Hidden: 0, Width: 300, 	Align: "Center", ColMerge: 0, SaveName: "cre_usr_id", 	KeyField: 0, UpdateEdit: 0, InsertEdit: 0},
			            {Type: "Text", 		Hidden: 0, Width: 300, 	Align: "Center", ColMerge: 0, SaveName: "upd_dt", 		KeyField: 0, UpdateEdit: 0, InsertEdit: 0, Format: "YmdHms"},
			            {Type: "Text", 		Hidden: 0, Width: 300, 	Align: "Center", ColMerge: 0, SaveName: "upd_usr_id", 	KeyField: 0, UpdateEdit: 0, InsertEdit: 0}
			        ];

			// setting column
			InitColumns(cols);
			// Merge cell (row, col, rows, cols)
			SetMergeCell(0,5,1,2);
			// Allow edit cell
			SetEditable(1);
			// hide Wait image
			SetWaitImageVisible(0);
			// setting size fit with screen
			resizeSheet();
		}
		break;
	}
}

/**
 * Setting init combobox
 * @param comboObj
 * @param comboNo
 */
function initCombo(comboObj, comboNo){
	var formObject = document.form;
	switch (comboNo) {
	case 1:
		with (comboObj) {
		//could select muti box in combo box
		SetMultiSelect(1);
		// set the height for drop list
        SetDropHeight(200);
		}
		// split all components to array
		var comboItems = carrierCombo.split("|");
		//then add content to combo
		addComboItem(comboObj, comboItems);
		break;
	}
}

/**
 * add data to combobox
 * @param comboObj
 * @param comboItems
 */
function addComboItem(comboObj, comboItems) {
	for (var i=0 ; i < comboItems.length ; i++) {
		comboObj.InsertItem(i, comboItems[i], comboItems[i]);
	}   		
}

/**
 * Setting init control
 */
function initControl() {
	document.getElementById("s_vndr_seq").addEventListener('change', function() {checkVendor();});
}

/**
 * Handle click button
 */
function processButtonClick(){
    /***** 탭당 시트가 2개 이상인 경우엔 추가 시트변수 지정하여 사용한 *****/
    var sheetObj=sheetObjects[0];
    /*******************************************************/
    var formObject=document.form;
	try {
		var srcName=ComGetEvent("name");
       switch(srcName) {
      	case "btn_Retrieve":
       		doActionIBSheet(sheetObj,formObject,IBSEARCH);
       		break;
      	case "btn_New": //event fires when New button is clicked, reset form
			doActionIBSheet(sheetObjects[0], formObject, IBRESET);
       		break;
       	case "btn_Save":
       		doActionIBSheet(sheetObj,formObject,IBSAVE);
       		break;
       	case "btn_DownExcel":
       		doActionIBSheet(sheetObj,formObject,IBDOWNEXCEL);
       		break;
		case "btn_calendar_fr":
		case "btn_calendar_to":
            var cal=new ComCalendar();
            if(srcName == "btn_calendar_fr"){
            	cal.select(formObject.s_cre_dt_fr, 'yyyy-MM-dd');
            }else{
                cal.select(formObject.s_cre_dt_to, 'yyyy-MM-dd');
            }
            break;
		case "btn_Del":
       		doActionIBSheet(sheetObj,formObject,IBDELETE);
       		break;
       	case "btn_Add":
       		doActionIBSheet(sheetObj,formObject,IBINSERT);
       		break;
       } // end switch
	}catch(e) {
		if( e == "[object Error]") {
			ComShowMessage(OBJECT_ERROR);
		} else {
			ComShowMessage(e);
		}
	}
}

/**
 * action when click button
 * @param sheetObj
 * @param formObj
 * @param sAction
 * @returns
 */
function doActionIBSheet(sheetObj,formObj,sAction) {
	sheetObj.ShowDebugMsg(false);
	switch (sAction) {
	case IBSEARCH: // retrieve
		if (!validateForm()){return;}
		formObj.f_cmd.value = SEARCH;
		// Unhide header
		if(sheetObj.GetRowHidden(0) > -1){
			sheetObj.SetRowHidden(0,0);
		}
		ComOpenWait(true);
		sheetObj.DoSearch("GEN_TRN_0004GS.do", FormQueryString(formObj));
		break;
	case IBRESET:
		sheetObj.RemoveAll();
		// Hide header
		if(sheetObj.GetRowHidden(0) > -1){
			sheetObj.SetRowHidden(0,1);
		}
		// Uncheck all option
		if (comboObjects[0].GetSelectIndex() != -1) {
			var selectIndex = comboObjects[0].GetSelectIndex().split(",");
			for (var i = 0; i < selectIndex.length; i ++) {
				comboObjects[0].SetItemCheck(parseInt(selectIndex[i]));
			}
		}
		// Reset vendor value
		document.getElementById("s_vndr_seq").value = "";
		// Reset calendar value
		document.getElementById("s_cre_dt_fr").value = "" ;
		document.getElementById("s_cre_dt_to").value = "" ;
		break;
	case IBSAVE: // save
		if (!flagValidate) {return}
		formObj.f_cmd.value = MULTI;
		sheetObj.DoSave("GEN_TRN_0004GS.do", FormQueryString(formObj));
		break;
	case IBDOWNEXCEL:	//Download Excel button event
		if(sheetObj.RowCount() < 1){
			ComShowCodeMessage("COM132501");
		}else{
			sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
		}
		break;
	case IBDELETE: //Delete button event
		for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
			if(sheetObj.GetCellValue(i, "chk") == 1){
				sheetObj.SetRowHidden(i, 1);
				sheetObj.SetRowStatus(i,"D");
			}
		}
		break;
	case IBINSERT: // Add button event
		sheetObj.DataInsert(-1);
		break;
	}
}

/**
 * close loading imgage
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 * @returns
 */
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
 	ComOpenWait(false);
}

/**
 * handle after save
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 */
function sheet1_OnSaveEnd(sheetObj, Code, Msg, StCode, StMsg) {
	if (Code >= 0) {
		resetCarrierCombo(sheetObj);
	}
}

/**
 * setting size of sheet
 * @returns
 */
function resizeSheet(){
	ComResizeSheet(sheetObjects[0]);
}

/**
 * Reset carriercombo after save
 */
function resetCarrierCombo(sheetObj) {
	formObj = document.form;
	formObj.f_cmd.value = -1;
	// Get option selected in Carrier combo
	var carrierSelected = formObj.s_jo_crr_cd.value.split(",");
	var sXml = sheetObj.GetSearchData("GEN_TRN_0004GS.do", FormQueryString(formObj));
	var carrierCode = "ALL|" + ComGetEtcData(sXml, "carrierItems");
	// Reset all option in Carrier combo
	comboObjects[0].RemoveAll();
	addComboItem(comboObjects[0], carrierCode.split("|"));
	// Reselect option search
	for (var i = 0; i < carrierSelected.length; i ++) {
		comboObjects[0].SetSelectText(carrierSelected[i], true);
	}
}
/**
 * Validate cell
 * @param sheetObj
 * @param Row
 * @param Col
 * @param Value
 * @returns {Boolean}
 */
function sheet1_OnChange(sheetObj, Row, Col, Value) {
	if(sheetObj.GetCellValue(Row,"jo_crr_cd") != "" && sheetObj.GetCellValue(Row,"rlane_cd") != "") {
		for (var i = 1; i < sheetObj.RowCount(); i++) {
			if (i != Row && sheetObj.GetCellValue(Row,"jo_crr_cd") == sheetObj.GetCellValue(i,"jo_crr_cd")
					&& sheetObj.GetCellValue(Row,"rlane_cd") == sheetObj.GetCellValue(i,"rlane_cd")) {
				ComShowCodeMessage("COM12115");
				// Focus error cell
				sheetObj.SetSelectCell(Row, Col);
				flagValidate = false;
				return;
			}
		}
	}
}

/**
 * validate form
 * @returns true if form not error
 */
function validateForm() {
	var regex = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;
	var creDtFr = document.getElementById("s_cre_dt_fr"); //get value from date from 
    var creDtTo = document.getElementById("s_cre_dt_to"); //get value from date to

    // Check Vendor code
    if (!checkVendor()) {
    	return false
    }
    // Check format date
    if (creDtFr.value != "" && !creDtFr.value.match(regex)) {
    	ComShowCodeMessage("COM12132");
    	creDtFr.focus();
    	return false;
    }
    // Check format date
    if (creDtTo.value != "" && !creDtTo.value.match(regex)) {
    	ComShowCodeMessage("COM12132");
    	creDtTo.focus();
    	return false;
    }
    return true;
}

/**
 * Check input of Vendor
 * @returns {Boolean}
 */
function checkVendor() {
    var vendor = document.getElementById("s_vndr_seq");
    // Check Vendor code
    if (vendor.value != "" && !vendor.value.isNumber()) {
    	ComShowCodeMessage("COM12122", "Vendor");
    	vendor.focus();
    	return false;
    } else {
    	return true;
    }
}

/**
 * Handle check all option in combobox
 * @param comboObj
 * @param Index
 * @param Text
 * @param Code
 */
function s_jo_crr_cd_OnCheckClick(comboObj, Index, Text, Code) {
	if (Index == 0 && !flagCheckAll) {
		// Lock function OnCheckClick
		flagCheckAll = true;
		// Get status of option "ALL"
		var status = comboObj.GetItemCheck(Index);
		// Set status for all option
		comboObj.SetSelectIndex(status);
		// Unlock function OnCheckClick
		flagCheckAll = false;
	} else if (!flagCheckAll) {
		// Lock function OnCheckClick
		flagCheckAll = true;
		if (comboObj.GetSelectIndex().split(",").length != comboObj.GetItemCount()) {
			comboObj.SetItemCheck(0, false);
		} else {
			comboObj.SetItemCheck(0, true);
		}
		// Unlock function OnCheckClick
		flagCheckAll = false;
	}
}
	/* 개발자 작업  끝 */