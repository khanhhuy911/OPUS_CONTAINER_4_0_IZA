/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceMgmtDBDAO.java
*@FileTitle : Invoice Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.invoice.invoicemgmt.integration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.clv.invoice.invoicemgmt.basic.InvoiceMgmtBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceDTLVO;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceVO;


/**
 * ALPS InvoiceMgmtDBDAO <br>
 * - ALPS-Invoice system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Khanh Huy
 * @see InvoiceMgmtBCImpl 참조
 * @since J2EE 1.6
 */
public class InvoiceMgmtDBDAO extends DBDAOSupport {

	/**
	 * Get partner list
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceVO> searchPartner() throws DAOException {
		DBRowSet dbRowset = null;
		List<InvoiceVO> list = null;

		try{
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceMgmtDBDAOSearchPartnerRSQL(), null, null);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, InvoiceVO .class);
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
	 * Get lane list
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceVO> searchLane(InvoiceVO invoiceVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<InvoiceVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(invoiceVO != null){
				Map<String, String> mapVO = invoiceVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceMgmtDBDAOSearchLaneRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, InvoiceVO .class);
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
	 * Get trade list
	 * @param invoiceVO
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceVO> searchTrade(InvoiceVO invoiceVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<InvoiceVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(invoiceVO != null){
				Map<String, String> mapVO = invoiceVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceMgmtDBDAOSearchTradeRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, InvoiceVO .class);
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
	 * search summary invoice
	 * 
	 * @param InvoiceVO invoiceVO
	 * @return List<InvoiceVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<InvoiceVO> searchInvoiceVO(InvoiceVO invoiceVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<InvoiceVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(invoiceVO != null){
				Map<String, String> mapVO = invoiceVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceMgmtDBDAOInvoiceVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, InvoiceVO .class);
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
	  * Search detail invoice
	  * @param invoiceDTLVO
	  * @return
	  * @throws DAOException
	  */
	 public List<InvoiceDTLVO> searchInvoiceDTLVO(InvoiceVO invoiceVO) throws DAOException {
			DBRowSet dbRowset = null;
			List<InvoiceDTLVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				if(invoiceVO != null){
					Map<String, String> mapVO = invoiceVO .getColumnValues();
				
					param.putAll(mapVO);
					velParam.putAll(mapVO);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new InvoiceMgmtDBDAOInvoiceDTLRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, InvoiceDTLVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
		}	
}