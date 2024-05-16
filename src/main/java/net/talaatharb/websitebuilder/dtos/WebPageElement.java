package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class WebPageElement extends WebsiteItem implements WebPageElementContainer{
	
	@Getter
	private final List<WebPageElement> elements = new ArrayList<>();

	public WebPageElement(String name) {
		super(name, WebsiteItemType.ELEMENT);
	}

}
