package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsitePages extends WebsiteItem implements WebsiteItemCategory{
	
	private List<WebsitePage> pages = new ArrayList<>();

	public WebsitePages() {
		super("Pages", WebsiteItemType.PAGES);
	}

}
