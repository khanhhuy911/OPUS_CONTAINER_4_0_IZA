/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeManagementDBDAO.java
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.gentraining.codemgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeDetailVO;
import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

/**
 * ALPS CodeMgmtDBDAO <br>
 * - ALPS-GenTraining system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author khanhhuy
 * @see CodeMgmtBCImpl 참조
 * @since J2EE 1.6
 */
public class CodeManagementDBDAO extends DBDAOSupport {

	/**
	 * Search code master
	 * 
	 * @param codeMgmtVO
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CodeVO> searchCodeVO(CodeVO codeVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CodeVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (codeVO != null) {
				Map<String, String> mapVO = codeVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CodeMgmtDBDAOCodeRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CodeVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * Check duplicate code master
	 * 
	 * @param codeVO
	 * @return
	 * @throws DAOException
	 */
	public List<CodeVO> checkDuplicateCode(CodeVO codeVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CodeVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (codeVO != null) {
				Map<String, String> mapVO = codeVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CodeMgmtDBDAOCodeRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CodeVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * Insert code object
	 * 
	 * @param codeVOS
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] addCodeVOS(List<CodeVO> codeVOS) throws DAOException,
			Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeVOS.size() > 0) {
				insCnt = sqlExe.executeBatch((ISQLTemplate) new CodeMgmtDBDAOCodeCSQL(), codeVOS, null);
				for (int i = 0; i < insCnt.length; i++) {
					if (insCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}

	/**
	 * Update code object
	 * 
	 * @param codeVOS
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] modifyCodeVOS(List<CodeVO> codeVOS) throws DAOException,
			Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeVOS.size() > 0) {
				updCnt = sqlExe.executeBatch((ISQLTemplate) new CodeMgmtDBDAOCodeUSQL(), codeVOS, null);
				for (int i = 0; i < updCnt.length; i++) {
					if (updCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}

	/**
	 * Delete code object
	 * 
	 * @param codeVOS
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] removeCodeVOS(List<CodeVO> codeVOS) throws DAOException,
			Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeVOS.size() > 0) {
				delCnt = sqlExe.executeBatch((ISQLTemplate) new CodeMgmtDBDAOCodeDSQL(), codeVOS, null);
				for (int i = 0; i < delCnt.length; i++) {
					if (delCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}

	/**
	 * Search code detail
	 * 
	 * @param codeDetailVO
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CodeDetailVO> searchCodeDetailVO(CodeDetailVO codeDetailVO)
			throws DAOException {
		DBRowSet dbRowset = null;
		List<CodeDetailVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (codeDetailVO != null) {
				Map<String, String> mapVO = codeDetailVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CodeMgmtDBDAOCodeDetailRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CodeDetailVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * Check duplicate code detail
	 * 
	 * @param CodeDetailVO
	 * @return
	 * @throws DAOException
	 */
	public List<CodeDetailVO> checkDuplicateCodeDetail(CodeDetailVO codeDetailVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CodeDetailVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (codeDetailVO != null) {
				Map<String, String> mapVO = codeDetailVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CodeMgmtDBDAOCodeDetailExistRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CodeDetailVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * Insert code detail object
	 * 
	 * @param codeDetailVOS
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] addCodeDetailVOS(List<CodeDetailVO> codeDetailVOS)
			throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeDetailVOS.size() > 0) {
				insCnt = sqlExe.executeBatch((ISQLTemplate) new CodeMgmtDBDAOCodeDetailCSQL(), codeDetailVOS, null);
				for (int i = 0; i < insCnt.length; i++) {
					if (insCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}

	/**
	 * Update code detail object
	 * 
	 * @param codeDetailVOS
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] modifyCodeDetailVOS(List<CodeDetailVO> codeDetailVOS)
			throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeDetailVOS.size() > 0) {
				updCnt = sqlExe.executeBatch((ISQLTemplate) new CodeMgmtDBDAOCodeDetailUSQL(), codeDetailVOS, null);
				for (int i = 0; i < updCnt.length; i++) {
					if (updCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}

	/**
	 * Delete code detail object
	 * 
	 * @param codeDetailVOS
	 * @return
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] removeCodeDetailVOS(List<CodeDetailVO> codeDetailVOS)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeDetailVOS.size() > 0) {
				delCnt = sqlExe.executeBatch((ISQLTemplate) new CodeMgmtDBDAOCodeDetailDSQL(), codeDetailVOS, null);
				for (int i = 0; i < delCnt.length; i++) {
					if (delCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
}
