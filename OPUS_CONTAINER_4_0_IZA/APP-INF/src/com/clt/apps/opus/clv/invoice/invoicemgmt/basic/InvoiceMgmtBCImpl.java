/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceMgmtBCImpl.java
*@FileTitle : Invoice Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.invoice.invoicemgmt.basic;

import java.util.List;

import com.clt.apps.opus.clv.invoice.invoicemgmt.integration.InvoiceMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceDTLVO;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceVO;

/**
 * ALPS-Invoice Business Logic Command Interface<br>
 * - ALPS-Invoice에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Khanh Huy
 * @since J2EE 1.6
 */
public class InvoiceMgmtBCImpl extends BasicCommandSupport implements InvoiceMgmtBC {

	// Database Access Object
	private transient InvoiceMgmtDBDAO dbDao = null;

	/**
	 * InvoiceMgmtBCImpl 객체 생성<br>
	 * InvoiceMgmtDBDAO를 생성한다.<br>
	 */
	public InvoiceMgmtBCImpl() {
		dbDao = new InvoiceMgmtDBDAO();
	}
	
	/**
	 * Get partner list
	 */
	public List<InvoiceVO> searchPartner() throws EventException {
		try {
			return dbDao.searchPartner();
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	/**
	 * Get lane list
	 * @param InvoiceVO invoiceVO
	 */
	public List<InvoiceVO> searchLane(InvoiceVO invoiceVO) throws EventException {
		try {
			return dbDao.searchLane(invoiceVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Get trade list
	 * @param InvoiceVO invoiceVO
	 */
	public List<InvoiceVO> searchTrade(InvoiceVO invoiceVO) throws EventException {
		try {
			return dbDao.searchTrade(invoiceVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	/**
	 * Search Summary Invoice
	 * 
	 * @param InvoiceVO invoiceVO
	 * @return List<InvoiceVO>
	 * @exception EventException
	 */
	public List<InvoiceVO> searchInvoiceVO(InvoiceVO invoiceVO) throws EventException {
		try {
			return dbDao.searchInvoiceVO(invoiceVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Search Detail Invoice
	 * @param invoiceDTLVO
	 * @return
	 * @throws EventException
	 */
	public List<InvoiceDTLVO> searchInvoiceDTLVO(InvoiceVO invoiceVO) throws EventException {
		try {
			return dbDao.searchInvoiceDTLVO(invoiceVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}