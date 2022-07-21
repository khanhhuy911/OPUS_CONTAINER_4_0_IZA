/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GEN_TRN_0003.js
*@FileTitle : Invoice Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
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
     * @class GEN_TRN_0003 : GEN_TRN_0003 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */

    
   	/* 개발자 작업	*/
// common global variable
var sheetObjects=new Array();
var sheetCnt=0;
var comboObjects = new Array();
var comboCnt = 0;
var tabObjects=new Array();
var tabCnt=0 ;
var beforetab=1;
var confirmOver3Months = false;
var flagCheckAll = false;
var doubClick = false;
var initFlag = true;
//Define an event handler that receives and handles button click events
document.onclick=processButtonClick;

/**
 * Set tab object
 * @param tab_obj
 */
function setTabObject(tab_obj){
	tabObjects[tabCnt++]=tab_obj;
}

/**
 * Set init tab
 * @param tabObj
 * @param tabNo
 */
function initTab(tabObj , tabNo) {
	switch(tabNo) {
	case 1:
		with (tabObj) {
			var cnt=0 ;
				InsertItem( "Summary" , "");
				InsertItem( "Detail" , "");
		}
		break;
	}
}

/**
 * {setComboObject} Put sheet objects in global variable "sheetObjects".<br>
 * @param {ibmulticombo} combo_obj    	IBMultiCombo Object
 **/
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
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
		var comboItems = partnerCombo.split("|");
		//then add content to combo
		addComboItem(comboObj, comboItems);
		break;
	default:
		with (comboObj) {
		//could select muti box in combo box
		SetMultiSelect(0);
		// set the height for drop list
        SetDropHeight(200);
		}
        break;
	}
}

/**
 * Set Sheet Object
 * @param sheet_obj
 */
function setSheetObject(sheet_obj) {
	sheetObjects[sheetCnt++] = sheet_obj;
}

/**
 * Setting sheet init
 */
function initSheet(sheetObj,sheetNo) {
	var cnt = 0;
	switch (sheetNo) {
		case 1:
			with(sheetObj){    
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle2 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name"
	
	            SetConfig( { SearchMode:2, MergeSheet:5, Page:20, DataRowMerge:1 } );
	
	            var info    = { Sort: 0, ColMove:0, HeaderCheck:0, ColResize:1 };
	            var headers = [ { Text: HeadTitle1, Align: "Center"},
	                            { Text: HeadTitle2, Align: "Center"}];
	            InitHeaders(headers, info);
	            
	            var cols = [ 
	       	             { Type: "Status", Hidden: 1, Width: 50,  Align: "Center", 	ColMerge: 0, SaveName: "ibflag" },
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "jo_crr_cd",       KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "rlane_cd",        KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 150, Align: "Left", 	ColMerge: 0, SaveName: "inv_no",          KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 200, Align: "Left",   	ColMerge: 0, SaveName: "csr_no",          KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",  ColMerge: 0, SaveName: "apro_flg",        KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "locl_curr_cd",    KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Right", 	ColMerge: 0, SaveName: "inv_rev_act_amt", KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Right", 	ColMerge: 0, SaveName: "inv_exp_act_amt", KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Left", 	ColMerge: 0, SaveName: "prnr_ref_no",     KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Left", 	ColMerge: 0, SaveName: "cust_vndr_eng_nm",KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}
	       	             ];
	            InitColumns(cols);
				SetEditable(1);
				SetWaitImageVisible(0);
			}
			break;
		case 2:
			with(sheetObj){
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle2 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Code|Name";
				
				SetConfig( { SearchMode:2, MergeSheet:5, Page:20, DataRowMerge:1 } );
				
	            var info    = { Sort: 0, ColMove:0, HeaderCheck:0, ColResize:1 };
	            var headers = [ { Text: HeadTitle1, Align: "Center"},
	                            { Text: HeadTitle2, Align: "Center"}];
	            InitHeaders(headers, info);
	            
	            var cols = [ 
		       	             { Type: "Status", Hidden: 1, Width: 50,  Align: "Center", 	ColMerge: 0, SaveName: "ibflag" },
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "jo_crr_cd",       KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "rlane_cd",        KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 150, Align: "Left", 	ColMerge: 0, SaveName: "inv_no",          KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 200, Align: "Left", 	ColMerge: 0, SaveName: "csr_no",          KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "apro_flg",        KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	             { Type: "Combo",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "rev_exp",         KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0, ComboText: "Rev|Exp", ComboCode: "R|E"},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "item",        	  KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", 	ColMerge: 0, SaveName: "locl_curr_cd",    KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Right", 	ColMerge: 0, SaveName: "inv_rev_act_amt", KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Right", 	ColMerge: 0, SaveName: "inv_exp_act_amt", KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Left", 	ColMerge: 0, SaveName: "prnr_ref_no",     KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Left", 	ColMerge: 0, SaveName: "cust_vndr_eng_nm",KeyField: 0, Format: "", UpdateEdit: 0, InsertEdit: 0}
		       	             ];
		            InitColumns(cols);
					SetEditable(1);
					SetWaitImageVisible(0);
			}
			break;
	}
}

/**
 * Load init page
 */
function loadPage(){
	// Generate Tab Layout
	for(k = 0;k < tabObjects.length; k++){
		initTab(tabObjects[k], k + 1);
		tabObjects[k].SetSelectedIndex(0);
	}
	
	// Generate Combobox Layout
	for (i = 0; i < comboObjects.length; i++) {
		initCombo(comboObjects[i], i+1);
	}
	
	//Generate Grid Layout
	for (i = 0; i < sheetObjects.length; i++) {
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i + 1);
		ComEndConfigSheet(sheetObjects[i]);
	}
	
	initControl();
	resizeSheet();
	// Setting date search
	initDate();
	initFlag = false;
}

/**
 * Setting init control
 */
function initControl() {
	document.getElementById("acct_yrmon_from").addEventListener('change', function() {resetSheet();});
	document.getElementById("acct_yrmon_to").addEventListener('change', function() {resetSheet();});
}

/**
 * Set size of sheet
 * @param sheetObj
 */
function resizeSheet() {
	ComResizeSheet(sheetObjects[0]);
	ComResizeSheet(sheetObjects[1]);
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
 * Handle change tab
 * @param tabObj
 * @param nItem
 */
function tab1_OnChange(tabObj, nItem)
{
	var objs=document.all.item("tabLayer");
	objs[nItem].style.display="Inline";		
	//--------------- this is important! --------------------------//
	for(var i = 0; i<objs.length; i++){
		  if(i != nItem){
		   objs[i].style.display="none";
		   objs[beforetab].style.zIndex=objs[nItem].style.zIndex - 1 ;
		  }
		}
	//------------------------------------------------------//
	beforetab=nItem;
    resizeSheet();
	if (!initFlag && nItem == 1 && sheetObjects[1].SearchRows() == 0) {
		doActionIBSheet(sheetObjects[1],document.form,IBSEARCH);
	} else if (!initFlag && nItem == 0 && sheetObjects[0].SearchRows() == 0) {
		doActionIBSheet(sheetObjects[0],document.form,IBSEARCH);
	}
}

/**
 * handle click event
 */
function processButtonClick(){
    /*******************************************************/
    var formObject=document.form;
    // Code master
    var sheetObj_0 = sheetObjects[0];
    // Code detail
    var sheetObj_1 = sheetObjects[1];
	try {
		var srcName=ComGetEvent("name");
		switch(srcName) {
		case "btn_datefrom_down":
			formObject.acct_yrmon_from.value = addMonths(formObject.acct_yrmon_from.value, -1);
			resetSheet();
			break;
       	case "btn_datefrom_up":
       		formObject.acct_yrmon_from.value = addMonths(formObject.acct_yrmon_from.value, 1);
       		resetSheet();
       		break;
       	case "btn_dateto_down":
       		formObject.acct_yrmon_to.value = addMonths(formObject.acct_yrmon_to.value, -1);
       		resetSheet();
       		break;
       	case "btn_dateto_up":
       		formObject.acct_yrmon_to.value = addMonths(formObject.acct_yrmon_to.value, 1);
       		resetSheet();
       		break;
        case "btn_Retrieve":
        	if (!validateForm()) {return;}
        	doActionIBSheet(sheetObj_0,formObject,IBSEARCH);
        	doActionIBSheet(sheetObj_1,formObject,IBSEARCH);
      		break;
        case "btn_New":
        	resetSheet();
        	// disable function s_jo_crr_cd_OnCheckClick
        	flagCheckAll = true;
        	// Uncheck all option
        	comboObjects[0].SetSelectIndex(false);
        	// enable function s_jo_crr_cd_OnCheckClick
        	flagCheckAll = false;
        	// Remove all data in combo
        	for (i = 1; i < comboObjects.length; i++) {
        		comboObjects[i].RemoveAll();
        	}
        	initDate();
       		break;
       	case "btn_DownExcel":
       		if (sheetObj_0.SearchRows() > 0) {
       			// Download multiple sheets into a single excel document
           		sheetObj_0.Down2ExcelBuffer(true);
           		// Reserve downloading into the first worksheet.
           		sheetObj_0.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj_0), SheetDesign:1, Merge:1, FileName:'Data',SheetName:'Summary'});
           		if (sheetObj_1.SearchRows() > 0) {
               		// Reserve downloading into the second worksheet.
               		sheetObj_1.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj_1), SheetDesign:1, Merge:1, FileName:'Data',SheetName:'Detail'});
           		}
           		// Download all buffered sheet data into a single excel document
           		sheetObj_0.Down2ExcelBuffer(false);
       		} else {
       			ComShowCodeMessage("COM132501");
       		}
        	break;
       	case "btn_DownExcel2":
       		doActionIBSheet(sheetObj_1,formObject,IBDOWNEXCEL);
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
		ComOpenWait(true);
		if (sheetObj.id == "sheet1") {
			// Search summary
			formObj.f_cmd.value = SEARCH03;
		} else if (sheetObj.id == "sheet2") {
			// search detail
			formObj.f_cmd.value = SEARCH04;
		}
		sheetObj.DoSearch("GEN_TRN_0003GS.do", FormQueryString(formObj));
		break;
	case IBDOWNEXCEL:
		formObj.f_cmd.value = COMMAND01;
		let param ={
				URL:"/opuscntr/GEN_TRN_0003DL.do",
				ExtendParam:FormQueryString(formObj),
				FileName:"Details.xls",
				DownCols: makeHiddenSkipCol(sheetObjects[1]),
				Merge:1,
				SheetDesign:1,
				KeyFieldMark:0,
				SheetName:'Details'
		};
		sheetObjects[1].DirectDown2Excel(param);
		break;
	}
}

/**
 * Reset all data in grid
 */
function resetSheet() {
	// Remove all data in sheet
	for (i = 0; i < sheetObjects.length; i++) {
		sheetObjects[i].RemoveAll();
	}
}

/**
 * Setting summary sheet after search
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 * @returns
 */
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
	for (var i=1; i<=sheetObj.RowCount()+1; i++) {
		if (sheetObj.GetCellValue(i,"jo_crr_cd") == ""){
			// format total sum
			if(sheetObj.GetCellValue(i,"inv_no") == ""){
				sheetObj.SetRowBackColor(i,"#F8CBAD");
				sheetObj.SetRangeFontBold(i,6,i,8,1);
			} else {
				// format subsum
				sheetObj.SetCellValue(i,"inv_no","");
				sheetObj.SetRowFontColor(i,"#C65911");
				sheetObj.SetRangeFontBold(i,6,i,8,1);
			}
		}
	}
 	ComOpenWait(false);
}

/**
 * Setting detail sheet after search
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 * @returns
 */
function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
	for (var i=1; i<=sheetObj.RowCount()+1; i++) {
		if (sheetObj.GetCellValue(i,"jo_crr_cd") == ""){
			// format total sum
			if (sheetObj.GetCellValue(i,"inv_no") == "") {
				sheetObj.SetRowBackColor(i,"#F8CBAD");
				sheetObj.SetRangeFontBold(i,8,i,10,1);
			} else {
				// format subsum
				sheetObj.SetCellValue(i,"inv_no","");
				sheetObj.SetRowFontColor(i,"#C65911");
				sheetObj.SetRangeFontBold(i,8,i,10,1);
			}
		}
	}
	// Select row after double click
	if (doubClick) {
		focusDetail(sheetObjects[0], sheetObjects[1], sheetObjects[0].GetSelectRow());
		doubClick = false;
	}
 	ComOpenWait(false);
}

/**
 * Get date init
 */
function initDate() {
	var formObj = document.form;
	var ymTo = ComGetNowInfo("ym");
	var ymFr = addMonths(ymTo, -1);
	formObj.acct_yrmon_to.value = ymTo;
	formObj.acct_yrmon_from.value = ymFr;
}

/**
 * Get previous/next month
 * @param date
 * @param numOfMonths
 * @returns
 */
function addMonths(date, numOfMonths) {
	if (checkDate(date)) {
		var dateCopy = new Date(date);
		dateCopy.setMonth(dateCopy.getMonth() + numOfMonths);
		var month = dateCopy.getMonth() + 1;
		return dateCopy.getFullYear() + "-" + (month <= 9 ? '0' + month : month);
	} else {
		return date;
	}
}

/**
 * Check date format
 * @param date
 * @returns {Boolean}
 */
function checkDate(date) {
	if (date == "") {
		return false
	}
	// Check value is date type
	var temp = new Date(date)
	if (isNaN(temp.getMonth())) {
		return false;
	}
	// Check format
	var rex = new RegExp('[0-9]{4}-[0-9]{2}');
	if (!rex.test(date)) {
		return false
	}
	return true;
}

/**
 * Handle check all option in combo
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

/**
 * Get data for Lane combo
 */
function s_jo_crr_cd_OnBlur()  {
	formObj = document.form;
	if (formObj.s_jo_crr_cd.value != "") {
		formObj.f_cmd.value = SEARCH01;
		var sXml = sheetObjects[0].GetSearchData("GEN_TRN_0003GS.do", FormQueryString(formObj));
		var laneCombo = ComGetEtcData(sXml, "LANE_LIST");
		// Reset all option in Lane combo
		comboObjects[1].RemoveAll();
		// Reset all option in Trade combo
		comboObjects[2].RemoveAll();
		// Add new option into lane combo
		addComboItem(comboObjects[1], laneCombo.split("|"));
	} else {
		// Reset all option in Lane combo
		comboObjects[1].RemoveAll();
		// Reset all option in Trade combo
		comboObjects[2].RemoveAll();
	}
}

/**
 * Get data for Trade combo
 */
function s_rlane_cd_OnBlur()  {
	formObj = document.form;
	if (formObj.s_rlane_cd.value != "") {
		formObj.f_cmd.value = SEARCH02;
		var sXml = sheetObjects[0].GetSearchData("GEN_TRN_0003GS.do", FormQueryString(formObj));
		var laneCombo = ComGetEtcData(sXml, "TRADE_LIST");
		// Reset all option in Lane combo
		comboObjects[2].RemoveAll();
		// Add new option into lane combo
		addComboItem(comboObjects[2], laneCombo.split("|"));
	} else {
		// Reset all option in Lane combo
		comboObjects[2].RemoveAll();
	}
}

/**
 * Handle double click in Summary sheet
 * @param sheetObj
 * @param Row
 * @param Col
 */
function sheet1_OnDblClick(sheetObj, Row, Col) {
	sheetObj1 = sheetObjects[1];
	tabObjects[0].SetSelectedIndex(1);
	if (sheetObj1.SearchRows() == 0) {
		doubClick = true;
	} else {
		focusDetail(sheetObj, sheetObj1, Row);
	}
}

/**
 * Handle focus detail sheet after double click summary sheet
 * @param sheetObj_0
 * @param sheetObj_1
 * @param Row
 * @param Col
 */
function focusDetail(sheetObj_0, sheetObj_1, Row) {
	if(Row == sheetObj_0.RowCount()) {
		sheetObj_1.SetSelectRow(sheetObj_1.RowCount());
	} else if (Row == sheetObj_0.RowCount()+1) {
		sheetObj_1.SetSelectRow(sheetObj_1.RowCount()+1);
	} else {
		var rowPartner = sheetObj_1.FindText(1, sheetObj_0.GetCellValue(Row, 1), Row);
		var rowRlane = sheetObj_1.FindText(2, sheetObj_0.GetCellValue(Row, 2), rowPartner);
		var rowInvoice = sheetObj_1.FindText(3, sheetObj_0.GetCellValue(Row, 3), rowRlane);
		var rowSlipNo = sheetObj_1.FindText(4, sheetObj_0.GetCellValue(Row, 4), rowInvoice);
		var rowApproved = sheetObj_1.FindText(5, sheetObj_0.GetCellValue(Row, 5), rowSlipNo);
		var rowCurr = sheetObj_1.FindText(8, sheetObj_0.GetCellValue(Row, 6), rowApproved);
		var rowCode = sheetObj_1.FindText(11, sheetObj_0.GetCellValue(Row, 9), rowCurr);
		sheetObj_1.SetSelectRow(rowCode);
	}
}

/**
 * Validate search form
 * @returns {Boolean}
 */
function validateForm() {
	var formObj = document.form;
	var dateFrom = formObj.acct_yrmon_from.value;
	var dateTo = formObj.acct_yrmon_to.value;
	// Check format date
	if (!checkDate(dateFrom)) {
		ComShowCodeMessage("COM12180");
		formObj.acct_yrmon_from.focus();
		return false;
	}
	// Check format date
	if (!checkDate(dateTo)) {
		ComShowCodeMessage("COM12180");
		formObj.acct_yrmon_to.focus();
		return false;
	}
	// End date must be greater than start date
	if (ComGetDaysBetween(dateFrom, dateTo) <= 0) {
		ComShowCodeMessage("COM132002");
		return false;
	}
	// Confirm search data over 3 months
	if (ComGetDaysBetween(dateFrom, dateTo) > 30 && !confirmOver3Months) {
		if (ComShowCodeConfirm("GEN130408")) {
			confirmOver3Months = true;
			return true;
		} else {
			return false
		}
	}
	return true;
}
	/* 개발자 작업  끝 */