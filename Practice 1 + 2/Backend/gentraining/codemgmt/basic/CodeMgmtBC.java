/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtBC.java
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

import java.util.List;

import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeDetailVO;
import com.clt.apps.opus.clv.gentraining.codemgmt.vo.CodeVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Gentraining Business Logic Command Interface<br>
 * - ALPS-Gentraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author khanhhuy
 * @since J2EE 1.6
 */

public interface CodeMgmtBC {

	/**
	 * 
	 * @param codeVO
	 * @return
	 * @throws EventException
	 */
	public List<CodeVO> searchCodeVO(CodeVO codeVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * @param codeVO
	 * @param account
	 * @throws EventException
	 */
	public void modifyCodeVO(CodeVO[] codeVOS, SignOnUserAccount account) throws EventException;
	
	/**
	 * 
	 * @param codeDetailVO
	 * @return
	 * @throws EventException
	 */
	public List<CodeDetailVO> searchCodeDetailVO(CodeDetailVO codeDetailVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.
	 * @param CodeDetailVOS
	 * @param account
	 * @throws EventException
	 */
	public void modifyCodeDetailVO(CodeDetailVO[] CodeDetailVOS, SignOnUserAccount account) throws EventException;
}
