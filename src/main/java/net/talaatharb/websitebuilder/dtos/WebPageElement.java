package net.talaatharb.websitebuilder.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class WebPageElement extends WebsiteItem implements WebPageElementContainer{
	
	@Setter
	private String id;
	
	@Setter
	private String tag;
	
	private final List<String> classes = new ArrayList<>(); // style classes
	
	private final Map<String, String> styles = new HashMap<>(); // in-line styles
	
	private final Map<String, String> attributes = new HashMap<>(); // props/@Input
	
	private final Map<String, String> eventHandlers = new HashMap<>(); // events/@Output
	
	private final List<WebPageElement> elements = new ArrayList<>(); // children/sub-elements

	public WebPageElement(String name) {
		super(name, WebsiteItemType.ELEMENT);
	}
	
	protected WebPageElement(String name, WebsiteItemType itemType) {
		super(name, itemType);
	}

}
