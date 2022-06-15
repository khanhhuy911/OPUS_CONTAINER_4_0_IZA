/*=========================================================
*Copyright(c) 2006 CyberLogitec
*@FileName : PRD_NODE.java
*@FileTitle : 사용자 관리5
*Open Issues :
*Change history :
*@LastModifyDate : 2007-02-28
*@LastModifier : Kildong_hong6
*@LastVersion : 1.0
* 2007-02-28 Kildong_hong6
* 1.0 최초 생성
=========================================================*/
package com.clt.syscommon.common.table;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;

/**
 * Table Value Ojbect<br>
 * - 모든 업무에서 공통으로 사용하는 PDTO(Data Transfer Object including Parameters)<br>
 * - 관련 Event에서 작성, 서버실행요청시 PDTO의 역할을 수행하는 Value Object<br>
 * 
 * @author Kildong_hong6
 * @since J2EE 1.4
 */
public final class PRD_NODE implements java.io.Serializable {

	private String         ibflag         = "";
	private String         page_rows      = "";
	private String         nod_cd         = "";
	private String         nod_nm         = "";
	private String         nod_tp_cd      = "";
	private String         loc_cd         = "";
	private String         onf_hir_yd_flg = "";
	private String         delt_flg       = "";
	private String         cre_usr_id     = "";
	private String         cre_dt         = "";
	private String         upd_usr_id     = "";
	private String         upd_dt         = "";

	public PRD_NODE(){}

	public PRD_NODE(
			String         ibflag        ,
			String         page_rows     ,
			String         nod_cd        ,
			String         nod_nm        ,
			String         nod_tp_cd     ,
			String         loc_cd        ,
			String         onf_hir_yd_flg,
			String         delt_flg      ,
			String         cre_usr_id    ,
			String         cre_dt        ,
			String         upd_usr_id    ,
			String         upd_dt        ){
		this.ibflag         = ibflag        ;
		this.page_rows      = page_rows     ;
		this.nod_cd         = nod_cd        ;
		this.nod_nm         = nod_nm        ;
		this.nod_tp_cd      = nod_tp_cd     ;
		this.loc_cd         = loc_cd        ;
		this.onf_hir_yd_flg = onf_hir_yd_flg;
		this.delt_flg       = delt_flg      ;
		this.cre_usr_id     = cre_usr_id    ;
		this.cre_dt         = cre_dt        ;
		this.upd_usr_id     = upd_usr_id    ;
		this.upd_dt         = upd_dt        ;
	}

	// getter method is proceeding ..
	public String         getIbflag        (){	return ibflag        	;	}
	public String         getPage_rows     (){	return page_rows     	;	}
	public String         getNod_cd        (){	return nod_cd        	;	}
	public String         getNod_nm        (){	return nod_nm        	;	}
	public String         getNod_tp_cd     (){	return nod_tp_cd     	;	}
	public String         getLoc_cd        (){	return loc_cd        	;	}
	public String         getOnf_hir_yd_flg(){	return onf_hir_yd_flg	;	}
	public String         getDelt_flg      (){	return delt_flg      	;	}
	public String         getCre_usr_id    (){	return cre_usr_id    	;	}
	public String         getCre_dt        (){	return cre_dt        	;	}
	public String         getUpd_usr_id    (){	return upd_usr_id    	;	}
	public String         getUpd_dt        (){	return upd_dt        	;	}

	// setter method is proceeding ..
	public void setIbflag        ( String         ibflag         ){	this.ibflag         = ibflag        	;	}
	public void setPage_rows     ( String         page_rows      ){	this.page_rows      = page_rows     	;	}
	public void setNod_cd        ( String         nod_cd         ){	this.nod_cd         = nod_cd        	;	}
	public void setNod_nm        ( String         nod_nm         ){	this.nod_nm         = nod_nm        	;	}
	public void setNod_tp_cd     ( String         nod_tp_cd      ){	this.nod_tp_cd      = nod_tp_cd     	;	}
	public void setLoc_cd        ( String         loc_cd         ){	this.loc_cd         = loc_cd        	;	}
	public void setOnf_hir_yd_flg( String         onf_hir_yd_flg ){	this.onf_hir_yd_flg = onf_hir_yd_flg	;	}
	public void setDelt_flg      ( String         delt_flg       ){	this.delt_flg       = delt_flg      	;	}
	public void setCre_usr_id    ( String         cre_usr_id     ){	this.cre_usr_id     = cre_usr_id    	;	}
	public void setCre_dt        ( String         cre_dt         ){	this.cre_dt         = cre_dt        	;	}
	public void setUpd_usr_id    ( String         upd_usr_id     ){	this.upd_usr_id     = upd_usr_id    	;	}
	public void setUpd_dt        ( String         upd_dt         ){	this.upd_dt         = upd_dt        	;	}

	public static PRD_NODE fromRequest(HttpServletRequest request) {
		PRD_NODE model = new PRD_NODE();
		try {
			model.setIbflag        	(JSPUtil.getParameter(request, "ibflag        		".trim(), ""));
			model.setPage_rows     	(JSPUtil.getParameter(request, "page_rows     		".trim(), ""));
			model.setNod_cd        	(JSPUtil.getParameter(request, "nod_cd        		".trim(), ""));
			model.setNod_nm        	(JSPUtil.getParameter(request, "nod_nm        		".trim(), ""));
			model.setNod_tp_cd     	(JSPUtil.getParameter(request, "nod_tp_cd     		".trim(), ""));
			model.setLoc_cd        	(JSPUtil.getParameter(request, "loc_cd        		".trim(), ""));
			model.setOnf_hir_yd_flg	(JSPUtil.getParameter(request, "onf_hir_yd_flg		".trim(), ""));
			model.setDelt_flg      	(JSPUtil.getParameter(request, "delt_flg      		".trim(), ""));
			model.setCre_usr_id    	(JSPUtil.getParameter(request, "cre_usr_id    		".trim(), ""));
			model.setCre_dt        	(JSPUtil.getParameter(request, "cre_dt        		".trim(), ""));
			model.setUpd_usr_id    	(JSPUtil.getParameter(request, "upd_usr_id    		".trim(), ""));
			model.setUpd_dt        	(JSPUtil.getParameter(request, "upd_dt        		".trim(), ""));
		} catch (Exception ex) {
			//throw new Exception(ex.getMessage());
		}
		return model;
	}
	public static Collection fromRequest(HttpServletRequest request, int length) {
		PRD_NODE model = null;
		Collection models = new ArrayList();
		try {
			String[] ibflag         =  (JSPUtil.getParameter(request, "ibflag        		".trim(), length));
			String[] page_rows      =  (JSPUtil.getParameter(request, "page_rows     		".trim(), length));
			String[] nod_cd         =  (JSPUtil.getParameter(request, "nod_cd        		".trim(), length));
			String[] nod_nm         =  (JSPUtil.getParameter(request, "nod_nm        		".trim(), length));
			String[] nod_tp_cd      =  (JSPUtil.getParameter(request, "nod_tp_cd     		".trim(), length));
			String[] loc_cd         =  (JSPUtil.getParameter(request, "loc_cd        		".trim(), length));
			String[] onf_hir_yd_flg =  (JSPUtil.getParameter(request, "onf_hir_yd_flg		".trim(), length));
			String[] delt_flg       =  (JSPUtil.getParameter(request, "delt_flg      		".trim(), length));
			String[] cre_usr_id     =  (JSPUtil.getParameter(request, "cre_usr_id    		".trim(), length));
			String[] cre_dt         =  (JSPUtil.getParameter(request, "cre_dt        		".trim(), length));
			String[] upd_usr_id     =  (JSPUtil.getParameter(request, "upd_usr_id    		".trim(), length));
			String[] upd_dt         =  (JSPUtil.getParameter(request, "upd_dt        		".trim(), length));
			for (int i = 0; i < length; i++) {
				model = new PRD_NODE();
				model.setIbflag        		  (ibflag        	[i]);
				model.setPage_rows     		  (page_rows     	[i]);
				model.setNod_cd        		  (nod_cd        	[i]);
				model.setNod_nm        		  (nod_nm        	[i]);
				model.setNod_tp_cd     		  (nod_tp_cd     	[i]);
				model.setLoc_cd        		  (loc_cd        	[i]);
				model.setOnf_hir_yd_flg		  (onf_hir_yd_flg	[i]);
				model.setDelt_flg      		  (delt_flg      	[i]);
				model.setCre_usr_id    		  (cre_usr_id    	[i]);
				model.setCre_dt        		  (cre_dt        	[i]);
				model.setUpd_usr_id    		  (upd_usr_id    	[i]);
				model.setUpd_dt        		  (upd_dt        	[i]);
				models.add(model);
			}
		} catch (Exception ex) {
		}
		return models;
	}
	public static Collection fromRequestGrid(HttpServletRequest request, String prefix) {
		PRD_NODE model = null;
		Collection models = new ArrayList();
		int length = request.getParameterValues("ibflag").length;
		try {
			String[] ibflag         =  (JSPUtil.getParameter(request, prefix + "ibflag        		".trim(), length));
			String[] page_rows      =  (JSPUtil.getParameter(request, prefix + "page_rows     		".trim(), length));
			String[] nod_cd         =  (JSPUtil.getParameter(request, prefix + "nod_cd        		".trim(), length));
			String[] nod_nm         =  (JSPUtil.getParameter(request, prefix + "nod_nm        		".trim(), length));
			String[] nod_tp_cd      =  (JSPUtil.getParameter(request, prefix + "nod_tp_cd     		".trim(), length));
			String[] loc_cd         =  (JSPUtil.getParameter(request, prefix + "loc_cd        		".trim(), length));
			String[] onf_hir_yd_flg =  (JSPUtil.getParameter(request, prefix + "onf_hir_yd_flg		".trim(), length));
			String[] delt_flg       =  (JSPUtil.getParameter(request, prefix + "delt_flg      		".trim(), length));
			String[] cre_usr_id     =  (JSPUtil.getParameter(request, prefix + "cre_usr_id    		".trim(), length));
			String[] cre_dt         =  (JSPUtil.getParameter(request, prefix + "cre_dt        		".trim(), length));
			String[] upd_usr_id     =  (JSPUtil.getParameter(request, prefix + "upd_usr_id    		".trim(), length));
			String[] upd_dt         =  (JSPUtil.getParameter(request, prefix + "upd_dt        		".trim(), length));
			for (int i = 0; i < length; i++) {
				model = new PRD_NODE();
				model.setIbflag        		  ( ibflag        	[i]);
				model.setPage_rows     		  ( page_rows     	[i]);
				model.setNod_cd        		  ( nod_cd        	[i]);
				model.setNod_nm        		  ( nod_nm        	[i]);
				model.setNod_tp_cd     		  ( nod_tp_cd     	[i]);
				model.setLoc_cd        		  ( loc_cd        	[i]);
				model.setOnf_hir_yd_flg		  ( onf_hir_yd_flg	[i]);
				model.setDelt_flg      		  ( delt_flg      	[i]);
				model.setCre_usr_id    		  ( cre_usr_id    	[i]);
				model.setCre_dt        		  ( cre_dt        	[i]);
				model.setUpd_usr_id    		  ( upd_usr_id    	[i]);
				model.setUpd_dt        		  ( upd_dt        	[i]);
				models.add(model);
			}
		} catch (Exception ex) {
		}
		return models;
	}

	// toString() method is overriding ..
	public String toString() {
		StringBuffer ret = new StringBuffer();
		Field[] field = this.getClass().getDeclaredFields();
		String space="                              ";
		try {
			for (int i = 0; i < field.length; i++) {
				String[] arr=null;
				try {
					arr = (String[]) field[i].get(this);
				}catch(Exception ex) {
					arr=new String[1];
					arr[0]=String.valueOf(field[i].get(this));
				}
				if (arr != null) {
					for (int j = 0; j < arr.length; j++) {
						ret.append(field[i].getName().concat(space).substring(0,30).concat("= ") + arr[j] +"\n");
					}
				} else {
					ret.append(field[i].getName() + " =  null \n");
				}
			}
		} catch (Exception ex) {}
		return ret.toString();
	}

}
