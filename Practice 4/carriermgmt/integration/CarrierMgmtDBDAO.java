/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtDBDAO.java
*@FileTitle : Carrier Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.carrier.carriermgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.clv.carrier.carriermgmt.basic.CarrierMgmtBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.clv.carrier.carriermgmt.vo.CarrierVO;


/**
 * ALPS CarrierMgmtDBDAO <br>
 * - ALPS-Carrier system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Khanh Huy
 * @see CarrierMgmtBCImpl 참조
 * @since J2EE 1.6
 */
public class CarrierMgmtDBDAO extends DBDAOSupport {

	/**
	 * Get Carrier Code
	 * @return
	 * @throws DAOException
	 */
	public List<CarrierVO> getCarrierCode() throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;

		try{
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierMgmtDBDAOCarrierCodeRSQL(), null, null);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	/**
	 * Get RLane Code
	 * @return
	 * @throws DAOException
	 */
	public List<CarrierVO> getRLaneCode() throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;

		try{
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierMgmtDBDAORLaneCodeRSQL(), null, null);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<CarrierVO> searchCarrierVO(CarrierVO carrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(carrierVO != null){
				Map<String, String> mapVO = carrierVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierMgmtDBDAOCarrierVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}

	 /**
	  * Check duplicate carrier
	  * @param carrierVO
	  * @return
	  * @throws DAOException
	  */
	public List<CarrierVO> checkExistCarrier(CarrierVO carrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOCheckExistCarrierRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<CarrierVO> carrierVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addCarrierVOS(List<CarrierVO> carrierVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierVO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierVOCSQL(), carrierVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<CarrierVO> carrierVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifyCarrierVOS(List<CarrierVO> carrierVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierVOUSQL(), carrierVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<CarrierVO> carrierVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removeCarrierVOS(List<CarrierVO> carrierVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierVODSQL(), carrierVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	
}