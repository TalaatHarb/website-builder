package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsiteCustomComponents extends WebsiteItem implements WebsiteItemCategory {
	
	private List<WebsiteCustomComponent> customComponents = new ArrayList<>();

	public WebsiteCustomComponents() {
		super("Custom Components", WebsiteItemType.CUSTOM_COMPONENTS);
	}

}
