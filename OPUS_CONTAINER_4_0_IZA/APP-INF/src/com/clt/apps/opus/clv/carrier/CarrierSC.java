/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierSC.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.carrier;

import java.util.List;
import com.clt.apps.opus.clv.carrier.carriermgmt.basic.CarrierMgmtBC;
import com.clt.apps.opus.clv.carrier.carriermgmt.basic.CarrierMgmtBCImpl;
import com.clt.apps.opus.clv.carrier.carriermgmt.event.GenTrn0004Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.clv.carrier.carriermgmt.vo.CarrierVO;


/**
 * ALPS-Carrier Business Logic ServiceCommand - ALPS-Carrier 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Khanh Huy
 * @see CarrierMgmtDBDAO
 * @since J2EE 1.6
 */

public class CarrierSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * Carrier system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("CarrierSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Carrier system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("CarrierSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-Carrier system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("GenTrn0004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = getDataCombo();
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchCarrierVO(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = modifyCarrierVO(e);
			}
		}
		return eventResponse;
	}
	
	/**
	 * Get data for combobox
	 * @return
	 * @throws EventException
	 */
	private EventResponse getDataCombo() throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierMgmtBC command = new CarrierMgmtBCImpl();

		try{
			List<CarrierVO> carrierList = command.getCarrierCode();
			List<CarrierVO> rLaneList = command.getRLaneCode();
			StringBuilder carrierCode = new StringBuilder();
			StringBuilder rLane = new StringBuilder();
			// Convert CarrierVO to String
			for (int i = 0; i < carrierList.size(); i++) {
				carrierCode.append("|" + carrierList.get(i).getJoCrrCd());
			}
			for (int i = 0; i < rLaneList.size(); i++) {
				rLane.append("|" + rLaneList.get(i).getRlaneCd());
			}
			eventResponse.setETCData("carrierItems", carrierCode.substring(1).toString());
			eventResponse.setETCData("rLaneItems", rLane.substring(1).toString());
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * GEN_TRN_0004 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCarrierVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0004Event event = (GenTrn0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();

		try{
			List<CarrierVO> list = command.searchCarrierVO(event.getCarrierVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}

	/**
	 * GEN_TRN_0004 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse modifyCarrierVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0004Event event = (GenTrn0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try{
			begin();
			command.modifyCarrierVO(event.getCarrierVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("BKG06071").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}