package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class WebsiteScripts extends WebsiteItem implements WebsiteFileContainer{
	
	private final List<WebsiteFile> files = new ArrayList<>();

	public WebsiteScripts() {
		super("Scripts", WebsiteItemType.SCRIPT_FILES);
	}

}
