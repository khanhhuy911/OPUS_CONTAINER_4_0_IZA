/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceSC.java
*@FileTitle : Invoice Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.clv.invoice.invoicemgmt.basic.InvoiceMgmtBC;
import com.clt.apps.opus.clv.invoice.invoicemgmt.basic.InvoiceMgmtBCImpl;
import com.clt.apps.opus.clv.invoice.invoicemgmt.event.GenTrn0003Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceDTLVO;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceVO;


/**
 * ALPS-Invoice Business Logic ServiceCommand - ALPS-Invoice 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Khanh Huy
 * @see InvoiceMgmtDBDAO
 * @since J2EE 1.6
 */

public class InvoiceSC extends ServiceCommandSupport {

	/**
	 * Invoice system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("InvoiceSC 시작");
	}

	/**
	 * Invoice system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("InvoiceSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-Invoice system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("GenTrn0003Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH01) || e.getFormCommand().isCommand(FormCommand.SEARCH02)
					|| e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = getDateCombo(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH03)) {
				eventResponse = searchInvoiceVO(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH04)) {
				eventResponse = searchInvoiceVODTL(e);
			} else if (e.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				eventResponse = downloadInvoiceVODTL(e);
			}
		}
		return eventResponse;
	}
	
	/**
	 * Get data combo
	 * @param e
	 * @return
	 * @throws EventException
	 */
	private EventResponse getDateCombo(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0003Event event = (GenTrn0003Event)e;
		InvoiceMgmtBC command = new InvoiceMgmtBCImpl();
		List<InvoiceVO> listInvoiceVO = null;
		StringBuilder codeList = new StringBuilder();
		try{
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				// Get data partner combo
				listInvoiceVO = command.searchPartner();
				// Convert list to String
				for (int i = 0; i < listInvoiceVO.size(); i++) {
					codeList.append("|" + listInvoiceVO.get(i).getJoCrrCd());
				}
				eventResponse.setETCData("PARTNER_LIST", codeList.substring(1).toString());
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				// Get data lane combo
				listInvoiceVO = command.searchLane(event.getInvoiceVO());
				// Convert list to String
				for (int i = 0; i < listInvoiceVO.size(); i++) {
					codeList.append("|" + listInvoiceVO.get(i).getRlaneCd());
				}
				eventResponse.setETCData("LANE_LIST", codeList.substring(1).toString());
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {
				// Get data trade combo
				listInvoiceVO = command.searchTrade(event.getInvoiceVO());
				// Convert list to String
				for (int i = 0; i < listInvoiceVO.size(); i++) {
					codeList.append("|" + listInvoiceVO.get(i).getTrnCd());
				}
				eventResponse.setETCData("TRADE_LIST", codeList.substring(1).toString());
			}
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * GEN_TRN_0003 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchInvoiceVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0003Event event = (GenTrn0003Event)e;
		InvoiceMgmtBC command = new InvoiceMgmtBCImpl();

		try{
			List<InvoiceVO> list = command.searchInvoiceVO(event.getInvoiceVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * Search invoice detail
	 * @param e
	 * @return
	 * @throws EventException
	 */
	private EventResponse searchInvoiceVODTL(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0003Event event = (GenTrn0003Event)e;
		InvoiceMgmtBC command = new InvoiceMgmtBCImpl();

		try{
			List<InvoiceDTLVO> list = command.searchInvoiceDTLVO(event.getInvoiceVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * Get detail invoice download
	 * @param e
	 * @return
	 * @throws EventException
	 */
	private EventResponse downloadInvoiceVODTL(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0003Event event = (GenTrn0003Event)e;
		InvoiceMgmtBC command = new InvoiceMgmtBCImpl();
		List<Map<String, String>> listRs = new ArrayList<>();
		Map<String, String> tempMap = null;
		try{
			List<InvoiceDTLVO> list = command.searchInvoiceDTLVO(event.getInvoiceVO());
			for (int i = 0; i < list.size(); i++) {
				tempMap = new HashMap<String, String>();
				tempMap.put("csr_no",list.get(i).getCsrNo());
				tempMap.put("inv_rev_act_amt",list.get(i).getInvRevActAmt()); 
				tempMap.put("locl_curr_cd",list.get(i).getLoclCurrCd());
				tempMap.put("cust_vndr_seq",list.get(i).getCustVndrSeq()); 
				tempMap.put("jo_crr_cd",list.get(i).getJoCrrCd());
				tempMap.put("rlane_cd",list.get(i).getRlaneCd()); 
				tempMap.put("rev_exp",list.get(i).getRevExp());
				tempMap.put("cust_vndr_cnt_cd",list.get(i).getCustVndrCntCd()); 
				tempMap.put("inv_no",list.get(i).getInvNo());
				tempMap.put("cust_vndr_eng_nm",list.get(i).getCustVndrEngNm());
				tempMap.put("inv_exp_act_amt",list.get(i).getInvExpActAmt()); 
			    tempMap.put("item",list.get(i).getItem());
			    tempMap.put("prnr_ref_no",list.get(i).getPrnrRefNo()); 
			    tempMap.put("apro_flg",list.get(i).getAproFlg());
			    listRs.add(tempMap);
			}
			eventResponse.setRsVoList(listRs);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
}