/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceMgmtBC.java
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

import com.clt.framework.core.layer.event.EventException;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceDTLVO;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceVO;

/**
 * ALPS-Invoice Business Logic Command Interface<br>
 * - ALPS-Invoice에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Khanh Huy
 * @since J2EE 1.6
 */

public interface InvoiceMgmtBC {

	/**
	 * Get partner list
	 * @return
	 * @throws EventException
	 */
	public List<InvoiceVO> searchPartner() throws EventException;
	
	/**
	 * Get lane list
	 * @param invoiceVO
	 * @return
	 * @throws EventException
	 */
	public List<InvoiceVO> searchLane(InvoiceVO invoiceVO) throws EventException;
	
	/**
	 * Get trade list
	 * @param invoiceVO
	 * @return
	 * @throws EventException
	 */
	public List<InvoiceVO> searchTrade(InvoiceVO invoiceVO) throws EventException;
	
	/**
	 * Search Summary Invoice
	 * 
	 * @param InvoiceVO	invoiceVO
	 * @return List<InvoiceVO>
	 * @exception EventException
	 */
	public List<InvoiceVO> searchInvoiceVO(InvoiceVO invoiceVO) throws EventException;
	
	/**
	 * Search Detail Invoice
	 * @param invoiceDTLVO
	 * @return
	 * @throws EventException
	 */
	public List<InvoiceDTLVO> searchInvoiceDTLVO(InvoiceVO invoiceVO) throws EventException;
}