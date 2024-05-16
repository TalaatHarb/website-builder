package net.talaatharb.websitebuilder.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Website {
	
	private String name;
	private WebsitePages pages;
	private WebsiteCustomComponents customComponents;
	private WebsiteLinkedFiles linkedFiles;
	private WebsiteScripts scriptFiles;

}
