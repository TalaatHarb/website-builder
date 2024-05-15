package net.talaatharb.websitebuilder.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class WebsiteItem {
		
	private final String name;
	
	@Getter
	private final WebSiteItemType itemType;
	
	@Override
	public String toString() {
		return name;
	}

}
