package net.talaatharb.websitebuilder.dtos;

public class WebsiteRoot extends WebsiteItem {
	
	public WebsiteRoot(String name) {
		super(name, WebsiteItemType.ROOT);
	}

	public WebsiteRoot(Website website) {
		super(website.getName(), WebsiteItemType.ROOT);
	}

}
