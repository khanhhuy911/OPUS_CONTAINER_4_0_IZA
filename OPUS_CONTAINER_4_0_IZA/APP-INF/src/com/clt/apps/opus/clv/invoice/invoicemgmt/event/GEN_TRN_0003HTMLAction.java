/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GEN_TRN_0003HTMLAction.java
*@FileTitle : Invoice Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.invoice.invoicemgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.clv.invoice 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 InvoiceSC로 실행요청<br>
 * - InvoiceSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Khanh Huy
 * @see InvoiceEvent 참조
 * @since J2EE 1.6
 */

public class GEN_TRN_0003HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * GEN_TRN_0003HTMLAction 객체를 생성
	 */
	public GEN_TRN_0003HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 InvoiceEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		GenTrn0003Event event = new GenTrn0003Event();
		
		if(command.isCommand(FormCommand.SEARCH01) || command.isCommand(FormCommand.SEARCH02)
				|| command.isCommand(FormCommand.SEARCH03) || command.isCommand(FormCommand.SEARCH04)
				|| command.isCommand(FormCommand.COMMAND01)) {
			String partnerCode = "";
			// Convert list code to a string
			if (!"".equals(JSPUtil.getParameter(request, "s_jo_crr_cd",""))) {
				String[] tempString = JSPUtil.getParameter(request, "s_jo_crr_cd").split(",");
				StringBuilder joCodeList = new StringBuilder();
				for (String string : tempString) {
					joCodeList.append(",'" + string +"'");
				}
				partnerCode = joCodeList.substring(1).toString();
			}
			// Set parameter into VO Object
			InvoiceVO objSearch = new InvoiceVO();
			objSearch.setJoCrrCd(partnerCode);
			objSearch.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd",""));
			objSearch.setDateFr(JSPUtil.getParameter(request, "acct_yrmon_from", ""));
			objSearch.setDateTo(JSPUtil.getParameter(request, "acct_yrmon_to", ""));
			event.setInvoiceVO(objSearch);
		}

		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}