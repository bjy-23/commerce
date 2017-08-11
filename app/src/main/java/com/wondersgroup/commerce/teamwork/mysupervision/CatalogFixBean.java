package com.wondersgroup.commerce.teamwork.mysupervision;

import java.util.ArrayList;

public class CatalogFixBean {
	private CatalogBean catalogBean;
	private ArrayList<CatalogFixBean> children = new ArrayList<CatalogFixBean>();

	public ArrayList<CatalogFixBean> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<CatalogFixBean> children) {
		this.children = children;
	}

	public CatalogFixBean(CatalogBean catalogBean) {
		super();

		this.catalogBean = catalogBean;
	}

	public void addChild(CatalogFixBean temp) {
		children.add(temp);
	}

	public CatalogBean getCatalogBean() {
		return catalogBean;
	}

	public void setCatalogBean(CatalogBean catalogBean) {
		this.catalogBean = catalogBean;
	}

}
