/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtBCImpl.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.16
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.16 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.gentraining.errmsgmgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.clv.gentraining.errmsgmgmt.integration.ErrMsgMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.clv.gentraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS-GenTraining Business Logic Command Interface<br>
 * - ALPS-GenTraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author khanhhuy
 * @since J2EE 1.6
 */
public class ErrMsgMgmtBCImpl extends BasicCommandSupport implements ErrMsgMgmtBC {

	// Database Access Object
	private transient ErrMsgMgmtDBDAO dbDao = null;

	/**
	 * ErrMsgMgmtBCImpl 객체 생성<br>
	 * ErrMsgMgmtDBDAO를 생성한다.<br>
	 */
	public ErrMsgMgmtBCImpl() {
		dbDao = new ErrMsgMgmtDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public List<ErrMsgVO> searchErrMsgVO(ErrMsgVO errMsgVO) throws EventException {
		try {
			return dbDao.searchErrMsgVO(errMsgVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO[] errMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void modifyErrMsgVO(ErrMsgVO[] errMsgVO, SignOnUserAccount account) throws EventException{
		try {
			List<ErrMsgVO> insertVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> updateVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> deleteVoList = new ArrayList<ErrMsgVO>();
			for ( int i=0; i<errMsgVO .length; i++ ) {
				// insert data
				if ( errMsgVO[i].getIbflag().equals("I")){
					if (!checkDuplicate(errMsgVO[i])) {
						errMsgVO[i].setCreUsrId(account.getUsr_id());
						insertVoList.add(errMsgVO[i]);
					} else {
						throw new EventException(new ErrorHandler("ERR00001").getMessage());
					}

				// update data
				} else if ( errMsgVO[i].getIbflag().equals("U")){
					errMsgVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(errMsgVO[i]);
				// delete data
				} else if ( errMsgVO[i].getIbflag().equals("D")){
					deleteVoList.add(errMsgVO[i]);
				}
			}
			
			// set list data to insert
			if ( insertVoList.size() > 0 ) {
				dbDao.addErrMsgVOS(insertVoList);
			}
			// set list data to update
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyErrMsgVOS(updateVoList);
			}
			// set list data to delete
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeErrMsgVOS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}	
	
	/**
	 * check duplicate data in db
	 * @param errMsgVO
	 * @return
	 * @throws EventException
	 */
	private boolean checkDuplicate(ErrMsgVO errMsgVO) throws EventException {
		ErrMsgVO tenpErrMsgVO = new ErrMsgVO();
		tenpErrMsgVO.setErrMsgCd(errMsgVO.getErrMsgCd());
		if (searchErrMsgVO(tenpErrMsgVO).size() > 0) {
			return true;
		}
		return false;
	}
}