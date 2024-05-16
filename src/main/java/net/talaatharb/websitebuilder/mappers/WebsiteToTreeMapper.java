package net.talaatharb.websitebuilder.mappers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TreeItem;
import net.talaatharb.websitebuilder.dtos.WebPageElementContainer;
import net.talaatharb.websitebuilder.dtos.Website;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponents;
import net.talaatharb.websitebuilder.dtos.WebsiteFileContainer;
import net.talaatharb.websitebuilder.dtos.WebsiteItem;
import net.talaatharb.websitebuilder.dtos.WebsiteLinkedFiles;
import net.talaatharb.websitebuilder.dtos.WebsitePages;
import net.talaatharb.websitebuilder.dtos.WebsiteRoot;
import net.talaatharb.websitebuilder.dtos.WebsiteScripts;

public class WebsiteToTreeMapper {

	public TreeItem<WebsiteItem> fromWebsiteToTree(Website website) {
		final TreeItem<WebsiteItem> websiteRoot = new TreeItem<>(new WebsiteRoot(website));

		final TreeItem<WebsiteItem> pages = new TreeItem<>(website.getPages());
		pages.getChildren().addAll(fromWebsitePagesToPageTree(website.getPages()));

		final TreeItem<WebsiteItem> customComponents = new TreeItem<>(website.getCustomComponents());
		customComponents.getChildren()
				.addAll(fromWebsiteCustomComponentsToComponentTree(website.getCustomComponents()));

		final WebsiteLinkedFiles linkedFilesObject = website.getLinkedFiles();
		final TreeItem<WebsiteItem> linkedFiles = new TreeItem<>(linkedFilesObject);
		linkedFiles.getChildren().addAll(fromWebsiteFileContainerToFileTree(linkedFilesObject));

		final WebsiteScripts scriptFilesObject = website.getScriptFiles();
		final TreeItem<WebsiteItem> scriptFiles = new TreeItem<>(scriptFilesObject);
		scriptFiles.getChildren().addAll(fromWebsiteFileContainerToFileTree(scriptFilesObject));

		websiteRoot.getChildren().add(pages);
		websiteRoot.getChildren().add(customComponents);
		websiteRoot.getChildren().add(linkedFiles);
		websiteRoot.getChildren().add(scriptFiles);

		return websiteRoot;
	}

	private List<TreeItem<WebsiteItem>> fromWebsitePagesToPageTree(WebsitePages pages) {
		List<TreeItem<WebsiteItem>> pagesTree = new ArrayList<>();
		pages.getPages().stream().forEach(page -> {
			TreeItem<WebsiteItem> pageTree = new TreeItem<>(page);
			pageTree.getChildren().addAll(fromContainerToElementTree(page));
			pagesTree.add(pageTree);
		});
		return pagesTree;
	}

	private List<TreeItem<WebsiteItem>> fromContainerToElementTree(WebPageElementContainer container) {
		List<TreeItem<WebsiteItem>> tree = new ArrayList<>();
		container.getElements().stream().forEach(element -> {
			TreeItem<WebsiteItem> elementTree = new TreeItem<>(element);
			elementTree.getChildren().addAll(fromContainerToElementTree(element));
			tree.add(elementTree);
		});
		return tree;
	}

	private List<TreeItem<WebsiteItem>> fromWebsiteCustomComponentsToComponentTree(
			WebsiteCustomComponents customComponents) {
		List<TreeItem<WebsiteItem>> tree = new ArrayList<>();
		customComponents.getCustomComponents().stream().forEach(component -> {
			TreeItem<WebsiteItem> elementsTree = new TreeItem<>(component);
			elementsTree.getChildren().addAll(fromContainerToElementTree(component));
			tree.add(elementsTree);
		});
		return tree;
	}

	private List<TreeItem<WebsiteItem>> fromWebsiteFileContainerToFileTree(WebsiteFileContainer fileContainer) {
		List<TreeItem<WebsiteItem>> tree = new ArrayList<>();
		fileContainer.getFiles().stream().forEach(file -> tree.add(new TreeItem<>(file)));
		return tree;
	}
}
