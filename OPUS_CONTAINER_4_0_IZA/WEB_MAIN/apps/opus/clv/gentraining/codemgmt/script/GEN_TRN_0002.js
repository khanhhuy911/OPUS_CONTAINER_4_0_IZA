/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GEN_TRN_0002.js
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.20 
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
    // click button
    document.onclick=processButtonClick;
    // data return after data saved
    var resultSave = null;
    
    /**
     * Set Sheet Object
     * @param sheet_obj
     */
    function setSheetObject(sheet_obj) {
    	sheetObjects[sheetCnt++] = sheet_obj;
    }
	
    /**
     * Load init page
     */
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
    
    /**
     * Setting init sheet
     */
    function initSheet(sheetObj,sheetNo) {
    	var cnt = 0;
    	switch (sheetNo) {
    	case 1: // sheet1 init
    		with (sheetObj) {

    			var HeadTitle = "|SubSystem|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date";

    			SetConfig({SearchMode : 0, MergeSheet : 0, Page : 20, DataRowMerge : 0});

    			var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
    			var headers = [ { Text : HeadTitle, Align : "Center" }];
    			InitHeaders(headers, info);

    			// define column
    			var cols = [ {Type:"Status",    Hidden:1,  Width:10,    Align:"Center",  ColMerge:0,   SaveName:"ibflag",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
							 {Type:"Text",      Hidden:0,  Width:70,    Align:"Center",  ColMerge:0,   SaveName:"ownr_sub_sys_cd", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, AcceptKeys : "E", InputCaseSensitive : 1},
							 {Type:"Text",      Hidden:0,  Width:60,    Align:"Center",  ColMerge:0,   SaveName:"intg_cd_id",      KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1, AcceptKeys : "E", InputCaseSensitive : 1},
							 {Type:"Text",      Hidden:0,  Width:200,   Align:"Left",    ColMerge:0,   SaveName:"intg_cd_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
							 {Type:"Text",      Hidden:0,  Width:50,    Align:"Center",  ColMerge:0,   SaveName:"intg_cd_len",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
							 {Type:"Combo",     Hidden:0,  Width:100,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_tp_cd",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboCode:"G", ComboText:"General"},
							 {Type:"Text",      Hidden:0,  Width:150,   Align:"Left",    ColMerge:0,   SaveName:"mng_tbl_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
							 {Type:"Text",      Hidden:0,  Width:350,   Align:"Left",    ColMerge:0,   SaveName:"intg_cd_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
							 {Type:"Combo",     Hidden:0,  Width:40,    Align:"Center",  ColMerge:0,   SaveName:"intg_cd_use_flg", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1,ComboCode:"N|Y", ComboText:"N|Y"},
							 {Type:"Text",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:"cre_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
							 {Type:"Date",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:"cre_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
							 {Type:"Text",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:"upd_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
							 {Type:"Date",      Hidden:0,  Width:80,    Align:"Center",  ColMerge:0,   SaveName:"upd_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 }

    	             ];

    			// setting column
    			InitColumns(cols);
    			SetEditable(1);
    			// hide Wait image
    			SetWaitImageVisible(0);
    			SetSheetHeight(240);
    		}
    		break;
    	case 2: // sheet2 init
    		with (sheetObj) {

    			var HeadTitle = "|Cd ID|Cd Val|Display Name|Description Remark|Order";

    			SetConfig({SearchMode : 0, MergeSheet : 0, Page : 20, DataRowMerge : 0});

    			var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
    			var headers = [ { Text : HeadTitle, Align : "Center" }];
    			InitHeaders(headers, info);

    			// define column
    			var cols = [ {Type:"Status",    Hidden:1,  Width:10,   Align:"Center",  ColMerge:0,   SaveName:"ibflag",              KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
						     {Type:"Text",      Hidden:1,  Width:10,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_id",          KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_ctnt",    KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:200,  Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_dp_desc", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:600,  Align:"Left",    ColMerge:0,   SaveName:"intg_cd_val_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
						     {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_dp_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 }

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
        // Count row changed on sheet master
        var sheet0Change = sheetObj_0.RowCount("I")+sheetObj_0.RowCount("U")+sheetObj_0.RowCount("D");
        // Count row changed on sheet detail
        var sheet1Change = sheetObj_1.RowCount("I")+sheetObj_1.RowCount("U")+sheetObj_1.RowCount("D");
   	try {
   		var srcName=ComGetEvent("name");
           switch(srcName) {
            case "btn_Retrieve":
            	if (sheet0Change != 0 || sheet1Change != 0) {
            		if (ComShowCodeConfirm("COM12112")) {
            			doActionIBSheet(sheetObj_0,formObject,IBSEARCH);
            		}
            	} else {
            		doActionIBSheet(sheetObj_0,formObject,IBSEARCH);
            	}
          		break;
            case "btn_Save_Mst":
            	doActionIBSheet(sheetObj_0,formObject,IBSAVE);
           		break;
            case "btn_Save_Dtl":
            	doActionIBSheet(sheetObj_1,formObject,IBSAVE);
           		break;
           	case "btn_Add_Mst":
           		if (sheetObj_1.RowCount("I")+sheetObj_1.RowCount("U")+sheetObj_1.RowCount("D") != 0) {
           			if (confirm("Sheet 'Detail' was changed. Are you sure you want to search new data?")) {
                   		doActionIBSheet(sheetObj_0,formObject,IBINSERT);
                   		// remove all data in code detail sheet
                  		sheetObj_1.RemoveAll();
           			}
           		} else {
               		doActionIBSheet(sheetObj_0,formObject,IBINSERT);
           		}
           		break;
           	case "btn_rowdelete_ms":
           		doActionIBSheet(sheetObj_0,formObject,IBDELETE);
           		// Hide all code detail
           		if(sheetObj_1.SearchRows() > 0){
           			if (confirm("Do you delete detail table?")){
           				for(i=sheetObj_1.LastRow();i>0;i--){
           					sheetObj_1.SetCellValue(i, "ibflag","D");
           					sheetObj_1.SetRowHidden(i,1);
          		        }
                	}
            	}
            	break;
           	case "btn_Add_Dtl":
           		if (document.form.s_intg_cd_id.value != sheetObj_0.GetCellValue(sheetObj_0.GetSelectRow(), "intg_cd_id")) {
           			// confirm add row
           			if (ComShowCodeConfirm("COM12121", document.form.s_intg_cd_id.value)) {
               			doActionIBSheet(sheetObj_1,formObject,IBINSERT);
               			// select row master
               			for (i=sheetObj_1.LastRow();i>0;i--) {
               				if (document.form.s_intg_cd_id.value == sheetObj_0.GetCellValue(i, "intg_cd_id")) {
               					sheetObj_0.SetSelectRow(i);
               					break;
               				}
               			}
               		}
           		} else {
           			doActionIBSheet(sheetObj_1,formObject,IBINSERT);
           		}
           		break;
           	case "btn_rowdelete_dl":
            	doActionIBSheet(sheetObj_1,formObject,IBDELETE);
            	break;
           	case "btn_DownExcel":
           		// Download multiple sheets into a single excel document
           		sheetObj_0.Down2ExcelBuffer(true);
           		// Reserve downloading into the first worksheet.
           		sheetObj_0.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj_0), FileName:'Data',SheetName:'CodeMaster'});
           		if (sheetObj_1.SearchRows() > 0) {
               		// Reserve downloading into the second worksheet.
               		sheetObj_1.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj_1), FileName:'Data',SheetName: 'CodeDetail_' + document.form.s_intg_cd_id.value});
           		}
           		// Download all buffered sheet data into a single excel document
           		sheetObj_0.Down2ExcelBuffer(false);
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
    			formObj.f_cmd.value = SEARCH01;
    		} else if (sheetObj.id == "sheet2") {
    			formObj.f_cmd.value = SEARCH02;
    		}
    		sheetObj.DoSearch("GEN_TRN_0002GS.do", FormQueryString(formObj));
    		break;
    	case IBSAVE: // save
    		if (sheetObj.id == "sheet1") {
    			formObj.f_cmd.value = MULTI01;
    		} else if (sheetObj.id == "sheet2") {
    			formObj.f_cmd.value = MULTI02;
    		}
        	sheetObj.DoSave("GEN_TRN_0002GS.do", FormQueryString(formObj));
    		break;
    	case IBINSERT: // Add button event
    		sheetObj.DataInsert();   
    		// Setting value for column "intg_cd_id"
    		if (sheetObj.id == "sheet2") {
    			sheetObj.SetCellValue(sheetObj.GetSelectRow(), "intg_cd_id", document.form.s_intg_cd_id.value);
    		}
    		controlButtonDetail();
    		break;
    	case IBDELETE: //Delete button event
    		// setting ibflag to delete
    		sheetObj.SetCellValue(sheetObj.GetSelectRow(),"ibflag","D");
    		// hide row selected
			sheetObj.SetRowHidden(sheetObj.GetSelectRow(), 1);
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
  		// remove all data in code detail sheet
  		sheetObjects[1].RemoveAll();
  		document.form.s_intg_cd_id.value = "";
  		controlButtonDetail();
     	ComOpenWait(false);
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
    function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) {
    	controlButtonDetail();
     	ComOpenWait(false);
    }
    
    /**
     * setting size of sheet code detail
     * @returns
     */
    function resizeSheet(){
    	ComResizeSheet(sheetObjects[1]);
      }
    
    /**
     * Handle select row in sheet code master
     * @param sheetObj
     * @param Row
     * @param Col
     */
	function sheet1_OnDblClick(sheetObj, Row, Col) {
		// get value column intg_cd_id on code master
		var value = sheetObj.GetCellValue(Row, "intg_cd_id");
		// set value column intg_cd_id to insert code detail
		document.form.s_intg_cd_id.value = value;
		// Search code detail
    	doActionIBSheet(sheetObjects[1],document.form,IBSEARCH);
    }
	
	/**
	 * Handle button control
	 */
	function controlButtonDetail() {
		if (document.form.s_intg_cd_id.value != "") {
			document.getElementById('btn_Add_Dtl').disabled = false;
		} else {
			document.getElementById('btn_Add_Dtl').disabled = true;
		}
		if (sheetObjects[1].LastRow() > 0) {
			document.getElementById('btn_rowdelete_dl').disabled = false;
			document.getElementById('btn_Save_Dtl').disabled = false;
		} else {
			document.getElementById('btn_rowdelete_dl').disabled = true;
			document.getElementById('btn_Save_Dtl').disabled = true;
		}
		
	}
	/* 개발자 작업  끝 */