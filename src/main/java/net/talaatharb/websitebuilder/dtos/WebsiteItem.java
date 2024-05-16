package net.talaatharb.websitebuilder.dtos;

import lombok.Getter;
import lombok.Setter;

public abstract class WebsiteItem {
	
	@Setter
	protected String name;
	
	protected WebsiteItem(String name, WebsiteItemType itemType) {
		this.name = name;
		this.itemType = itemType;
	}
	
	@Getter
	private final WebsiteItemType itemType;
	
	@Override
	public String toString() {
		return name;
	}

}
