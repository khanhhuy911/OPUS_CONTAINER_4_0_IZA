/*=========================================================
*Copyright(c) 2010 CyberLogitec
*@FileName : ApprovalDBDAOApprovalRouteSeqRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2010.07.06
*@LastModifier : 김현욱
*@LastVersion : 1.0
* 2010.07.06 김현욱
* 1.0 Creation
=========================================================*/
package com.clt.bizcommon.approval.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;

import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Kim Hyun Uk
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class ApprovalDBDAOApprovalRouteSeqRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Aproval Route Sequence 를 구합니다
	  * </pre>
	  */
	public ApprovalDBDAOApprovalRouteSeqRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("sub_sys_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("ofc_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.bizcommon.approval.integration").append("\n"); 
		query.append("FileName : ApprovalDBDAOApprovalRouteSeqRSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("SELECT APRO_ROUT_SEQ" ).append("\n"); 
		query.append("FROM COM_APRO_ROUT_DFLT" ).append("\n"); 
		query.append("WHERE SUB_SYS_CD = DECODE(@[sub_sys_cd],'TLL','MNR',@[sub_sys_cd])" ).append("\n"); 
		query.append("AND OFC_CD     = @[ofc_cd]" ).append("\n"); 

	}
}