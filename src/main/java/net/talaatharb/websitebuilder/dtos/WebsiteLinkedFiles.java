package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class WebsiteLinkedFiles extends WebsiteItem implements WebsiteFileContainer {
	
	private final List<WebsiteFile> files = new ArrayList<>();

	public WebsiteLinkedFiles() {
		super("Linked files", WebsiteItemType.LINKED_FILES);
	}

}
