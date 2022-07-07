/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GenTrn0003Event.java
*@FileTitle : Invoice Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.invoice.invoicemgmt.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceDTLVO;
import com.clt.apps.opus.clv.invoice.invoicemgmt.vo.InvoiceVO;


/**
 * GEN_TRN_0003 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  GEN_TRN_0003HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Khanh Huy
 * @see GEN_TRN_0003HTMLAction 참조
 * @since J2EE 1.6
 */

public class GenTrn0003Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	InvoiceVO invoiceVO = null;
	
	/** Table Value Object Multi Data 처리 */
	InvoiceVO[] invoiceVOs = null;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	InvoiceDTLVO invoiceDTLVO = null;
	
	/** Table Value Object Multi Data 처리 */
	InvoiceDTLVO[] invoiceDTLVOs = null;

	public GenTrn0003Event(){}
	
	public void setInvoiceVO(InvoiceVO invoiceVO){
		this. invoiceVO = invoiceVO;
	}

	public void setInvoiceVOS(InvoiceVO[] invoiceVOs){
		this. invoiceVOs = invoiceVOs;
	}

	public InvoiceVO getInvoiceVO(){
		return invoiceVO;
	}

	public InvoiceVO[] getInvoiceVOS(){
		return invoiceVOs;
	}
	
	public void setInvoiceDTLVO(InvoiceDTLVO invoiceDTLVO){
		this. invoiceDTLVO = invoiceDTLVO;
	}

	public void setInvoiceDTLVOS(InvoiceDTLVO[] invoiceDTLVOs){
		this. invoiceDTLVOs = invoiceDTLVOs;
	}

	public InvoiceDTLVO getInvoiceDTLVO(){
		return invoiceDTLVO;
	}

	public InvoiceDTLVO[] getInvoiceDTLVOS(){
		return invoiceDTLVOs;
	}

}