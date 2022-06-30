/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtDBDAORLaneCodeRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.28
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.28 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.carrier.carriermgmt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Khanh Huy
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierMgmtDBDAORLaneCodeRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Search VSL_SLAN_CD
	  * </pre>
	  */
	public CarrierMgmtDBDAORLaneCodeRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.clv.carrier.carriermgmt.integration").append("\n"); 
		query.append("FileName : CarrierMgmtDBDAORLaneCodeRSQL").append("\n"); 
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
		query.append("SELECT DISTINCT VSL_SLAN_CD as rlane_cd" ).append("\n"); 
		query.append("FROM MDM_REV_LANE" ).append("\n"); 
		query.append("ORDER BY rlane_cd" ).append("\n"); 

	}
}