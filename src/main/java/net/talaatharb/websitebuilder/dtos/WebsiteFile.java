package net.talaatharb.websitebuilder.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class WebsiteFile extends WebsiteItem {
	
	private String url = "";
	private String content = "";

	protected WebsiteFile(String name, WebsiteItemType itemType) {
		super(name, itemType);
	}

}
