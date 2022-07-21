/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtDBDAOCodeExistRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.22
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.22 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.gentraining.codemgmt.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author khanhhuy
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CodeMgmtDBDAOCodeExistRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Search first row code master
	  * </pre>
	  */
	public CodeMgmtDBDAOCodeExistRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.clv.gentraining.codemgmt.integration ").append("\n"); 
		query.append("FileName : CodeMgmtDBDAOCodeExistRSQL").append("\n"); 
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
		query.append("SELECT " ).append("\n"); 
		query.append("	INTG_CD_ID" ).append("\n"); 
		query.append("FROM COM_INTG_CD" ).append("\n"); 
		query.append("WHERE INTG_CD_ID = @[intg_cd_id]" ).append("\n"); 
		query.append("AND ROWNUM = 1" ).append("\n"); 

	}
}