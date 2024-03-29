/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GEN_TRN_0001.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.16
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.16 
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
    
   	/* 개발자 작업	*/
    var sheetObjects=new Array();
    var sheetCnt=0;
    // flag check format message code
    var flagValidate = true;
    // click button
    document.onclick=processButtonClick;
    
    function setSheetObject(sheet_obj) {
    	sheetObjects[sheetCnt++] = sheet_obj;
    }
    
    function loadPage(){
    	//generate Grid Layout
    	for (i = 0; i < sheetObjects.length; i++) {
    		ComConfigSheet(sheetObjects[i]);
    		initSheet(sheetObjects[i], i + 1);
    		ComEndConfigSheet(sheetObjects[i]);
    	}
    	
    	//auto search data after loading finish.
    	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
    }
    
    function initSheet(sheetObj,sheetNo) {
    	var cnt = 0;
    	switch (sheetNo) {
    	case 1: // sheet1 init
    		with (sheetObj) {

    			var HeadTitle = "STS|Del|Msg Cd|Msg Type|Msg Level|Message|Description";

    			SetConfig({SearchMode : 0, MergeSheet : 0, Page : 20, DataRowMerge : 0, FrozenCol: 4});

    			var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
    			var headers = [ { Text : HeadTitle, Align : "Center" }];
    			InitHeaders(headers, info);

    			// define column
    			var cols = [ 
    	             { Type : "Status", 	Hidden : 1, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "ibflag" }, 
    	             { Type : "DelCheck", 	Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "del_chk" }, 
    	             { Type : "Text", 		Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_msg_cd",  KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1,  EditLen: 8 }, 
    	             { Type : "Combo", 		Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_tp_cd",   KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1, ComboText:"Server|UI|Both", ComboCode:"S|U|B" }, 
    	             { Type : "Combo", 		Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_lvl_cd",  KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1, ComboText:"ERR|WARNING|INFO", ComboCode:"E|W|I" }, 
    	             { Type : "Text", 		Hidden : 0, Width : 600, Align : "Left",   ColMerge : 0, SaveName : "err_msg",     KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1, MultiLineText:1}, 
    	             { Type : "Text", 		Hidden : 0, Width : 100, Align : "Left",   ColMerge : 0, SaveName : "err_desc",    KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1}
    	             ];

    			// setting column
    			InitColumns(cols);
    			SetEditable(1);
    			// hide Wait image
    			SetWaitImageVisible(0);
    			// setting size fit with screen
    			resizeSheet();
    		}
    		break;
    	}
    }
    
    function processButtonClick(){
        /***** 탭당 시트가 2개 이상인 경우엔 추가 시트변수 지정하여 사용한 *****/
        var sheetObject1=sheetObjects[0];
        /*******************************************************/
        var formObject=document.form;
   	try {
   		var srcName=ComGetEvent("name");
           switch(srcName) {
           	case "btn_Add":
           		doActionIBSheet(sheetObject1,formObject,IBINSERT);
           		break;
           	case "btn_Retrieve":
           		doActionIBSheet(sheetObject1,formObject,IBSEARCH);
           		break;
           	case "btn_Save":
           		doActionIBSheet(sheetObject1,formObject,IBSAVE);
           		break;
           	case "btn_DownExcel":
           		doActionIBSheet(sheetObject1,formObject,IBDOWNEXCEL);
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
    		formObj.f_cmd.value = SEARCH;
    		ComOpenWait(true);
    		sheetObj.DoSearch("GEN_TRN_0001GS.do", FormQueryString(formObj) );
    		break;
    	case IBSAVE: // save
    		if (!flagValidate) {return}
    		formObj.f_cmd.value = MULTI;
    		sheetObj.DoSave("GEN_TRN_0001GS.do", FormQueryString(formObj));
    		break;
    	case IBINSERT: // Add button event
    		sheetObj.DataInsert();
    		break;
    	case IBDELETE: //Delete button event
    		for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
    			if(sheetObj.GetCellValue(i, "del_chk") == 1){
    				sheetObj.SetRowHidden(i, 1);
    				sheetObj.SetRowStatus(i,"D");
    			}
    		}
    		break;
		case IBDOWNEXCEL:	//Download Excel button event
			if(sheetObj.RowCount() < 1){
				ComShowCodeMessage("COM132501");
			}else{
				sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
			}
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
     * setting size of sheet
     * @returns
     */
    function resizeSheet(){
    	ComResizeSheet(sheetObjects[0]);
      }
        
    /**
     * validate message code when value change
     * @param sheetObj
     * @param Row
     * @param Col
     * @param Value
     */
    function sheet1_OnEditValidation(sheetObj, Row, Col, Value) {
    	if (sheetObj.ColSaveName(Col) == 'err_msg_cd') {
    		var regex = new RegExp(/[A-Z]{3}[0-9]{5}/);
    		if (!regex.test(Value)) {
    			ComShowCodeMessage('COM12117',Value);
    			sheetObj.ValidateFail(1);
    			flagValidate = false;
    		} else {
    			flagValidate = true;
    		}
    	}
    }
    

	/* 개발자 작업  끝 */