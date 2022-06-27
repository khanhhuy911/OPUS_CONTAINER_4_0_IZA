/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : GenTrn0002Event.java
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.20 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.gentraining.codemgmt.event;

import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeDetailVO;
import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeVO;
import com.clt.framework.support.layer.event.EventSupport;

/**
 * GEN_TRN_0002 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  GEN_TRN_0002HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author khanhhuy
 * @see GEN_TRN_0002HTMLAction 참조
 * @since J2EE 1.6
 */
public class GenTrn0002Event extends EventSupport {
	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	CodeVO codeVO = null;
	
	/** Table Value Object Multi Data 처리 */
	CodeVO[] codeVOs = null;
	
	CodeDetailVO codeDetail = null;
	
	CodeDetailVO[] codeDetails = null;

	public GenTrn0002Event(){}
	
	public void setCodeVO(CodeVO codeVO){
		this. codeVO = codeVO;
	}

	public void setCodeVOS(CodeVO[] codeVOs){
		this. codeVOs = codeVOs;
	}

	public CodeVO getCodeVO(){
		return codeVO;
	}

	public CodeVO[] getCodeVOS(){
		return codeVOs;
	}
	
	public void setCodeDetailVO(CodeDetailVO codeDetail){
		this. codeDetail = codeDetail;
	}

	public void setCodeDetailVOS(CodeDetailVO[] codeDetails){
		this. codeDetails = codeDetails;
	}

	public CodeDetailVO getCodeDetailVO(){
		return codeDetail;
	}

	public CodeDetailVO[] getCodeDetailVOS(){
		return codeDetails;
	}
}
