/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtBCImpl.java
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.20
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.gentraining.codemgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.clv.gentraining.codemgmt.integration.CodeManagementDBDAO;
import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeDetailVO;
import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * @author khanh
 *
 */
public class CodeMgmtBCImpl extends BasicCommandSupport implements CodeMgmtBC {

	// Database Access Object
	private transient CodeManagementDBDAO dbDao = null;

	/**
	 * CodeMgmtBCImpl 객체 생성<br>
	 * CodeMgmtDBDAO를 생성한다.<br>
	 */
	public CodeMgmtBCImpl() {
		dbDao = new CodeManagementDBDAO();
	}

	/**
	 * Search code detail
	 * 
	 * @param CodeVO
	 *            codeVO
	 * @return List CodeVO
	 */
	public List<CodeVO> searchCodeVO(CodeVO codeVO) throws EventException {
		try {
			return dbDao.searchCodeVO(codeVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * Modify code object
	 * 
	 * @param CodeVO
	 *            [] CodeVO
	 * @param account
	 *            SignOnUserAccount
	 * @exception EventException
	 */
	public void modifyCodeVO(CodeVO[] CodeVO, SignOnUserAccount account)
			throws EventException {
		try {
			List<CodeVO> insertVoList = new ArrayList<CodeVO>();
			List<CodeVO> updateVoList = new ArrayList<CodeVO>();
			List<CodeVO> deleteVoList = new ArrayList<CodeVO>();
			for (int i = 0; i < CodeVO.length; i++) {
				// case insert
				if (CodeVO[i].getIbflag().equals("I")) {
					// Check duplicate
					if (!checkDuplicateCode(CodeVO[i])) {
						CodeVO[i].setCreUsrId(account.getUsr_id());
						insertVoList.add(CodeVO[i]);
					} else {
						throw new EventException(
								new ErrorHandler("ERR00001").getMessage());
					}

					// case update
				} else if (CodeVO[i].getIbflag().equals("U")) {
					CodeVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(CodeVO[i]);
					// case delete
				} else if (CodeVO[i].getIbflag().equals("D")) {
					deleteVoList.add(CodeVO[i]);
				}
			}

			// set list data to insert
			if (insertVoList.size() > 0) {
				dbDao.addCodeVOS(insertVoList);
			}
			// set list data to update
			if (updateVoList.size() > 0) {
				dbDao.modifyCodeVOS(updateVoList);
			}
			// set list data to delete
			if (deleteVoList.size() > 0) {
				dbDao.removeCodeVOS(deleteVoList);
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * check duplicate code master
	 * 
	 * @param CodeVO
	 *            codeVO
	 * @return true if code is exist in DB
	 * @throws EventException
	 */
	private boolean checkDuplicateCode(CodeVO codeVO) throws EventException {
		try {
			CodeVO tempVO = new CodeVO();
			tempVO.setIntgCdId(codeVO.getIntgCdId());
			if (dbDao.checkDuplicateCode(tempVO).size() > 0) {
				return true;
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return false;
	}

	/**
	 * Search code detail
	 * 
	 * @param CodeDetailVO
	 *            codeDetailVO
	 * @return List CodeDetailVO
	 * @exception EventException
	 */
	public List<CodeDetailVO> searchCodeDetailVO(CodeDetailVO codeDetailVO)
			throws EventException {
		try {
			return dbDao.searchCodeDetailVO(codeDetailVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * Modify code detail object
	 * 
	 * @param CodeDetailVOS
	 *            [] CodeDetailVOS
	 * @param account
	 *            SignOnUserAccount
	 * @exception EventException
	 */
	public void modifyCodeDetailVO(CodeDetailVO[] CodeDetailVOS,
			SignOnUserAccount account) throws EventException {
		try {
			List<CodeDetailVO> insertVoList = new ArrayList<CodeDetailVO>();
			List<CodeDetailVO> updateVoList = new ArrayList<CodeDetailVO>();
			List<CodeDetailVO> deleteVoList = new ArrayList<CodeDetailVO>();
			for (int i = 0; i < CodeDetailVOS.length; i++) {
				// case insert
				if (CodeDetailVOS[i].getIbflag().equals("I")) {
					if (!checkDuplicateCode(CodeDetailVOS[i])) {
						CodeDetailVOS[i].setCreUsrId(account.getUsr_id());
						insertVoList.add(CodeDetailVOS[i]);
					} else {
						throw new EventException(
								new ErrorHandler("ERR00001").getMessage());
					}

					// case update
				} else if (CodeDetailVOS[i].getIbflag().equals("U")) {
					CodeDetailVOS[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(CodeDetailVOS[i]);
					// case delete
				} else if (CodeDetailVOS[i].getIbflag().equals("D")) {
					deleteVoList.add(CodeDetailVOS[i]);
				}
			}

			// set list data to insert
			if (insertVoList.size() > 0) {
				dbDao.addCodeDetailVOS(insertVoList);
			}
			// set list data to update
			if (updateVoList.size() > 0) {
				dbDao.modifyCodeDetailVOS(updateVoList);
			}
			// set list data to delete
			if (deleteVoList.size() > 0) {
				dbDao.removeCodeDetailVOS(deleteVoList);
			}
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * check duplicate code detail
	 * 
	 * @param CodeDetailVO
	 *            CodeDetailVOS
	 * @return true if data is exist in DB
	 * @throws EventException
	 */
	private boolean checkDuplicateCode(CodeDetailVO CodeDetailVOS)
			throws EventException {
		try {
			CodeDetailVO tempVO = new CodeDetailVO();
			tempVO.setIntgCdId(CodeDetailVOS.getIntgCdId());
			tempVO.setIntgCdValCtnt(CodeDetailVOS.getIntgCdValCtnt());
			if (dbDao.checkDuplicateCodeDetail(tempVO).size() > 0) {
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
