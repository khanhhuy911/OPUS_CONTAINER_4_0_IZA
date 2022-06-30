/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtBC.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.carrier.carriermgmt.basic;

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.clv.carrier.carriermgmt.vo.CarrierVO;

/**
 * ALPS-Carrier Business Logic Command Interface<br>
 * - ALPS-Carrier에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Khanh Huy
 * @since J2EE 1.6
 */

public interface CarrierMgmtBC {

	/**
	 * Get carrier code
	 * @return
	 * @throws EventException
	 */
	public List<CarrierVO> getCarrierCode() throws EventException;
	
	/**
	 * Get RLane Code
	 * @return
	 * @throws EventException
	 */
	public List<CarrierVO> getRLaneCode() throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO	carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierVO(CarrierVO carrierVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO[] carrierVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void modifyCarrierVO(CarrierVO[] carrierVO,SignOnUserAccount account) throws EventException;
}