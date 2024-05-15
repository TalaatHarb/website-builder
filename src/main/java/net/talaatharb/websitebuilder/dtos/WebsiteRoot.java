package net.talaatharb.websitebuilder.dtos;

public class WebsiteRoot extends WebsiteItem {
	
	public WebsiteRoot(String name) {
		super(name, WebSiteItemType.ROOT);
	}

	public WebsiteRoot(Website website) {
		super(website.getName(), WebSiteItemType.ROOT);
	}

}
