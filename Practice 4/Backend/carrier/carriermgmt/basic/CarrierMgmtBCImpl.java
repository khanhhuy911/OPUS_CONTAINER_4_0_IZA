/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtBCImpl.java
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

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.clv.carrier.carriermgmt.integration.CarrierMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.clv.carrier.carriermgmt.vo.CarrierVO;

/**
 * ALPS-Carrier Business Logic Command Interface<br>
 * - ALPS-Carrier에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Khanh Huy
 * @since J2EE 1.6
 */
public class CarrierMgmtBCImpl extends BasicCommandSupport implements CarrierMgmtBC {

	// Database Access Object
	private transient CarrierMgmtDBDAO dbDao = null;

	/**
	 * CarrierMgmtBCImpl 객체 생성<br>
	 * CarrierMgmtDBDAO를 생성한다.<br>
	 */
	public CarrierMgmtBCImpl() {
		dbDao = new CarrierMgmtDBDAO();
	}
	
	/**
	 * get carrier code
	 */
	public List<CarrierVO> getCarrierCode() throws EventException {
		try {
			return dbDao.getCarrierCode();
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * get rlane code
	 */
	public List<CarrierVO> getRLaneCode() throws EventException {
		try {
			return dbDao.getRLaneCode();
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierVO(CarrierVO carrierVO) throws EventException {
		try {
			return dbDao.searchCarrierVO(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO[] carrierVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void modifyCarrierVO(CarrierVO[] carrierVO, SignOnUserAccount account) throws EventException{
		try {
			List<CarrierVO> insertVoList = new ArrayList<CarrierVO>();
			List<CarrierVO> updateVoList = new ArrayList<CarrierVO>();
			List<CarrierVO> deleteVoList = new ArrayList<CarrierVO>();
			for ( int i=0; i<carrierVO .length; i++ ) {
				if ( carrierVO[i].getIbflag().equals("I")){
					if (!checkDuplicate(carrierVO[i])) {
						carrierVO[i].setCreUsrId(account.getUsr_id());
						insertVoList.add(carrierVO[i]);
					} else {
						throw new EventException(new ErrorHandler("ERR12356", new String[]{carrierVO[i].getJoCrrCd()}).getMessage());
					}
				} else if ( carrierVO[i].getIbflag().equals("U")){
					carrierVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(carrierVO[i]);
				} else if ( carrierVO[i].getIbflag().equals("D")){
					deleteVoList.add(carrierVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addCarrierVOS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyCarrierVOS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeCarrierVOS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Check duplicate carrier
	 * @param carrierVO
	 * @return true if carrier is exist
	 * @throws EventException
	 */
	private boolean checkDuplicate(CarrierVO carrierVO) throws EventException {
		try {
			CarrierVO tempCarrierVO = new CarrierVO();
			tempCarrierVO.setJoCrrCd(carrierVO.getJoCrrCd());
			if (dbDao.checkExistCarrier(tempCarrierVO).size() > 0) {
				return true;
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return false;
	}
}