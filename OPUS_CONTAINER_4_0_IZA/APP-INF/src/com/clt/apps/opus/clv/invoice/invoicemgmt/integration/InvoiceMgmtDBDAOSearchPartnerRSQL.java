/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : InvoiceMgmtDBDAOSearchPartnerRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.04
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.clv.invoice.invoicemgmt.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Khanh Huy
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class InvoiceMgmtDBDAOSearchPartnerRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Search JO_CRR_CD
	  * </pre>
	  */
	public InvoiceMgmtDBDAOSearchPartnerRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.clv.invoice.invoicemgmt.integration ").append("\n"); 
		query.append("FileName : InvoiceMgmtDBDAOSearchPartnerRSQL").append("\n"); 
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
		query.append("	DISTINCT(JO_CRR_CD)" ).append("\n"); 
		query.append("FROM JOO_CARRIER" ).append("\n"); 

	}
}