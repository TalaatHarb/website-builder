package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class WebsiteCustomComponent extends WebsiteItem implements WebPageElementContainer{
	
	@Getter
	private final List<WebPageElement> elements = new ArrayList<>();

	public WebsiteCustomComponent(String name) {
		super(name, WebsiteItemType.CUSTOM_COMPONENT);
	}

}
