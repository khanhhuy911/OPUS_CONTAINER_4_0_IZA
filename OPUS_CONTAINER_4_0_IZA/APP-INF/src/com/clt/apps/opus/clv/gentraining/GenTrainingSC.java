/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GenTrainingSC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.16
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.16 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.gentraining;

import java.util.List;

import com.clt.apps.opus.clv.gentraining.codemgmt.basic.CodeMgmtBC;
import com.clt.apps.opus.clv.gentraining.codemgmt.basic.CodeMgmtBCImpl;
import com.clt.apps.opus.clv.gentraining.codemgmt.event.GenTrn0002Event;
import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeDetailVO;
import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeVO;
import com.clt.apps.opus.clv.gentraining.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.clv.gentraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.clv.gentraining.errmsgmgmt.event.GenTrn0001Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.clv.gentraining.errmsgmgmt.vo.ErrMsgVO;


/**
 * ALPS-GenTraining Business Logic ServiceCommand - ALPS-GenTraining 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author khanhhuy
 * @see CarrierMgmtDBDAO
 * @since J2EE 1.6
 */

public class GenTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * GenTraining system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("GenTrainingSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * GenTraining system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("GenTrainingSC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-GenTraining system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// Handle message code
		if (e.getEventName().equalsIgnoreCase("GenTrn0001Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchErrMsgVO(e);
			}
			// case: insert/update/delete
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = modifyErrMsgVO(e);
			}
		} else if (e.getEventName().equalsIgnoreCase("GenTrn0002Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchCodeVO(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {
				eventResponse = searchCodeDetailVO(e);
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI01)) {
				eventResponse = modifyCodeVO(e);
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI02)) {
				eventResponse = modifyCodeDetailVO(e);
			}
		}
		return eventResponse;
	}

	/**
	 * GEN_TRN_0001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchErrMsgVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0001Event event = (GenTrn0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();

		try{
			List<ErrMsgVO> list = command.searchErrMsgVO(event.getErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}

	/**
	 * GEN_TRN_0001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse modifyErrMsgVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0001Event event = (GenTrn0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		
		try{
			begin();
			command.modifyErrMsgVO(event.getErrMsgVOS(),account);
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
	
	/**
	 * GEN_TRN_0002 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCodeVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0002Event event = (GenTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();

		try{
			List<CodeVO> list = command.searchCodeVO(event.getCodeVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * GEN_TRN_0002 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCodeDetailVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0002Event event = (GenTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();

		try{
			List<CodeDetailVO> list = command.searchCodeDetailVO(event.getCodeDetailVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * GEN_TRN_0002 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse modifyCodeVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0002Event event = (GenTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();
		
		try{
			begin();
			command.modifyCodeVO(event.getCodeVOS(),account);
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
	
	/**
	 * GEN_TRN_0002 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse modifyCodeDetailVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		GenTrn0002Event event = (GenTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();
		
		try{
			begin();
			command.modifyCodeDetailVO(event.getCodeDetailVOS(),account);
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