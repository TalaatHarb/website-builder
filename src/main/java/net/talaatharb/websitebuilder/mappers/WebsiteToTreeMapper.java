package net.talaatharb.websitebuilder.mappers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TreeItem;
import net.talaatharb.websitebuilder.dtos.Website;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponents;
import net.talaatharb.websitebuilder.dtos.WebsiteItem;
import net.talaatharb.websitebuilder.dtos.WebsitePage;
import net.talaatharb.websitebuilder.dtos.WebsitePages;
import net.talaatharb.websitebuilder.dtos.WebsiteRoot;

public class WebsiteToTreeMapper {

	public TreeItem<WebsiteItem> fromWebsiteToTree(Website website) {
		final TreeItem<WebsiteItem> websiteRoot = new TreeItem<>(new WebsiteRoot(website));

		final TreeItem<WebsiteItem> pages = new TreeItem<>(website.getPages());
		pages.getChildren().addAll(fromWebsitePagesToPageTree(website.getPages()));

		final TreeItem<WebsiteItem> customComponents = new TreeItem<>(website.getCustomComponents());
		customComponents.getChildren()
				.addAll(fromWebsiteCustomComponentsToComponentTree(website.getCustomComponents()));

		websiteRoot.getChildren().add(pages);
		websiteRoot.getChildren().add(customComponents);

		return websiteRoot;
	}

	List<TreeItem<WebsiteItem>> fromWebsitePagesToPageTree(WebsitePages pages) {
		List<TreeItem<WebsiteItem>> pagesTree = new ArrayList<>();
		pages.getPages().stream().forEach(page -> {
			TreeItem<WebsiteItem> pageTree = new TreeItem<>(page);
			pageTree.getChildren().addAll(fromWebsitePageToElementTree(page));
			pagesTree.add(pageTree);
		});
		return pagesTree;
	}

	List<TreeItem<WebsiteItem>> fromWebsitePageToElementTree(WebsitePage page) {
		List<TreeItem<WebsiteItem>> tree = new ArrayList<>();
		page.getElements().stream().forEach(element -> tree.add(new TreeItem<>(element)));
		return tree;
	}

	List<TreeItem<WebsiteItem>> fromWebsiteCustomComponentsToComponentTree(WebsiteCustomComponents customComponents) {
		List<TreeItem<WebsiteItem>> tree = new ArrayList<>();
		customComponents.getCustomComponents().stream().forEach(component -> tree.add(new TreeItem<>(component)));
		return tree;
	}
}
