package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsiteCustomComponents extends WebsiteItem {
	
	private List<WebsiteCustomComponent> customComponents = new ArrayList<>();

	public WebsiteCustomComponents() {
		super("Custom Components", WebSiteItemType.CUSTOM_COMPONENTS);
	}

}
