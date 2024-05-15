package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsitePage extends WebsiteItem{
	
	private List<WebPageElement> elements = new ArrayList<>();

	public WebsitePage(String name) {
		super(name, WebSiteItemType.PAGE);
	}

}
