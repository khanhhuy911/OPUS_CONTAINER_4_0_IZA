/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GenTrn0004Event.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.carrier.carriermgmt.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.clv.carrier.carriermgmt.vo.CarrierVO;


/**
 * GEN_TRN_0004 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  GEN_TRN_0004HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Khanh Huy
 * @see GEN_TRN_0004HTMLAction 참조
 * @since J2EE 1.6
 */

public class GenTrn0004Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	CarrierVO carrierVO = null;
	
	/** Table Value Object Multi Data 처리 */
	CarrierVO[] carrierVOs = null;

	public GenTrn0004Event(){}
	
	public void setCarrierVO(CarrierVO carrierVO){
		this. carrierVO = carrierVO;
	}

	public void setCarrierVOS(CarrierVO[] carrierVOs){
		this. carrierVOs = carrierVOs;
	}

	public CarrierVO getCarrierVO(){
		return carrierVO;
	}

	public CarrierVO[] getCarrierVOS(){
		return carrierVOs;
	}

}