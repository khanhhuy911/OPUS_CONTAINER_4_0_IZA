/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceViewAdapterDL.java
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

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.controller.ViewAdapter;
import com.clt.framework.core.layer.event.GeneralEventResponse;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.clv.invoice 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 InvoiceSC로 실행요청<br>
 * - InvoiceSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Khanh Huy
 * @see InvoiceEvent 참조
 * @since J2EE 1.6
 */
public class InvoiceViewAdapterDL extends ViewAdapter {

	/**
	 * Forward response
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	public String makeXML(HttpServletRequest request, HttpServletResponse response) {
		GeneralEventResponse eventResponse = (GeneralEventResponse)request.getAttribute("EventResponse");
		request.setAttribute("SHEETDATA", eventResponse.getRsVoList());
		String forwardPath="./js/ibsheet/jsp/DirectDown2Excel.jsp" ; 
	    RequestDispatcher rd=request.getRequestDispatcher(forwardPath); 
	    try {
			rd.forward(request,response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	protected String makeDataTag(List<AbstractValueObject> arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String makeDataTag(DBRowSet arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
