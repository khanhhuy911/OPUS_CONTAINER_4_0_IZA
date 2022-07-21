<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GEN_TRN_0003.jsp
*@FileTitle : Invoice Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.clv.invoice.invoicemgmt.event.GenTrn0003Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	GenTrn0003Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	Logger log = Logger.getLogger("com.clt.apps.Invoice.InvoiceMgmt");
	String partnerList = "";

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (GenTrn0003Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		
		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		} else{
			partnerList = eventResponse.getETCData("PARTNER_LIST");
		}
	}catch(Exception e) {
		out.println(e.toString());
	}
%>

<body  onLoad="setupPage();">
<script language="javascript">
	var partnerCombo = "ALL|<%=partnerList%>";
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if
		loadPage();
	}
</script>
<script language="javascript" type="text/javascript" src="apps/opus/clv/invoice/invoicemgmt/script/InvoiceMessage.js"></script>
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
<!-- 개발자 작업	-->
<div class="page_title_area clear">
		<h2 class="page_title">
			<button type="button">
				<span id="title">Invoice Management</span>
			</button>
		</h2>
		<div class="opus_design_btn">
			<button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
			--><button type="button" class="btn_normal" name="btn_New" id="btn_New">New</button><!--
			--><button type="button" class="btn_normal" name="btn_DownExcel"id="btn_DownExcel">Down Excel</button><!-- 
			--><button type="button" class="btn_normal" name="btn_DownExcel2" id="btn_DownExcel2">Down Excel2</button>
		</div>
		<div class="location">
			<span id="navigation"></span>
		</div>
	</div>
	<div class="wrap_search_tab">
		<div class="opus_design_inquiry wFit">
			<table>
			<colgroup>
				<col width="100">
				<col width="350">
				<col width="70">
				<col width="70">
				<col width="70">
				<col width="70">
				<col width="70">
				<col width="*">
			</colgroup>
				<tbody>
					<tr>
						<th>Year Month</th>
						<td>
							<input type="text" style="width: 100px;" class="input" value="" name="acct_yrmon_from" id="acct_yrmon_from"><!--
							--><button type="button" class="btn_left" name="btn_datefrom_down" id="btn_datefrom_down"></button><!--
							--><button type="button" class="btn_right" name="btn_datefrom_up" id="btn_datefrom_up"></button><!--
							--><input type="text" style="width: 100px;" class="input" value="" name="acct_yrmon_to" id="acct_yrmon_to"><!--
							--><button type="button" class="btn_left" name="btn_dateto_down" id="btn_dateto_down"></button><!--
							--><button type="button" class="btn_right" name="btn_dateto_up" id="btn_dateto_up"></button>
						</td>
						<th>Partner</th>
						<td><script type="text/javascript">ComComboObject('s_jo_crr_cd', 1, 100, 1, 0, 0);</script></td>
						<th>Lane</th>
						<td><script type="text/javascript">ComComboObject('s_rlane_cd', 1, 100, 1, 0, 0);</script></td>
						<th>Trade</th>
						<td><script type="text/javascript">ComComboObject('s_trade_cd', 1, 100, 1, 0, 0);</script></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="wrap_result">
		<div class="opus_design_tab sm">
			<script type="text/javascript">ComTabObject('tab1')</script>
		</div>

		<div class="opus_design_grid clear" name="tabLayer" id="tabLayer">
			<script language="javascript">ComSheetObject('sheet1');</script>
		</div>

		<div class="opus_design_grid clear" name="tabLayer" id="tabLayer">
			<script language="javascript">ComSheetObject('sheet2');</script>
		</div>
	</div>
<!-- 개발자 작업  끝 -->
</form>
</body>