package com.test.utils;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.core.filter.SystemSession;
import com.test.model.Module;

public class HasAuthory extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String action;
	private static ArrayList<Module> moduleList = null;
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		moduleList = (ArrayList<Module>) (SystemSession.getSession().getAttribute("modulelist"));
		boolean auth = hasPri(action, moduleList);
		if(auth){
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	@Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;

	}

	@Override
	public void release() {
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	private boolean hasPri(String url,List<Module> moduleList) {
		boolean isIncluded = false;
		boolean hasPriority = false;
		if(moduleList!=null){
			for(Module m :moduleList){
				//是否纳入管理
				if(url.equals(m.getUrl())){
					isIncluded = true;
					break;
				}
			}
			if(isIncluded){
				for(Module m:moduleList){
					if(m.isChecked()&&url.equals(m.getUrl())){
						hasPriority = true;
						break;
					}
				}
			}
		}
		return hasPriority||!isIncluded;
	}
	
}
