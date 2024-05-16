package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class WebsitePage extends WebsiteItem implements WebPageElementContainer{
	
	@Getter
	private final List<WebPageElement> elements = new ArrayList<>();

	public WebsitePage(String name) {
		super(name, WebsiteItemType.PAGE);
	}

}
