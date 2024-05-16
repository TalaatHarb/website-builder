package net.talaatharb.websitebuilder.ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.talaatharb.websitebuilder.dtos.WebPageElement;
import net.talaatharb.websitebuilder.dtos.WebPageElementContainer;
import net.talaatharb.websitebuilder.dtos.Website;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponent;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponents;
import net.talaatharb.websitebuilder.dtos.WebsiteItem;
import net.talaatharb.websitebuilder.dtos.WebsiteLinkedFiles;
import net.talaatharb.websitebuilder.dtos.WebsitePage;
import net.talaatharb.websitebuilder.dtos.WebsitePages;
import net.talaatharb.websitebuilder.dtos.WebsiteScript;
import net.talaatharb.websitebuilder.dtos.WebsiteScripts;
import net.talaatharb.websitebuilder.dtos.WebsiteStyleSheet;
import net.talaatharb.websitebuilder.mappers.WebsiteToTreeMapper;

@Slf4j
@RequiredArgsConstructor
public class MainWindowController implements Initializable {

	private static final Random RANDOM = new Random();

	private final WebsiteToTreeMapper websiteToTreeMapper = new WebsiteToTreeMapper();

	@FXML
	@Getter(value = AccessLevel.PACKAGE)
	private TreeView<WebsiteItem> websiteTree;

	@FXML
	@Getter(value = AccessLevel.PACKAGE)
	private Label leftStatus;

	@FXML
	@Getter(value = AccessLevel.PACKAGE)
	private Label rightStatus;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("Initializing UI application Main window controller...");

		final Website website = createSampleWebsite();
		TreeItem<WebsiteItem> rootNode = websiteToTreeMapper.fromWebsiteToTree(website);
		rootNode.setExpanded(true);

		websiteTree.setRoot(rootNode);
		websiteTree.setCellFactory(treeView -> new WebsiteTreeCellImpl());
		websiteTree.getSelectionModel().selectedItemProperty().addListener(this::handleChangeSelectedTreeItem);
	}

	private void handleChangeSelectedTreeItem(ObservableValue<? extends TreeItem<WebsiteItem>> observable,
			TreeItem<WebsiteItem> oldValue, TreeItem<WebsiteItem> newValue) {
		log.info("Selected Text : " + newValue.getValue());
	}

	private Website createSampleWebsite() {
		final Website website = new Website();
		website.setName("Project 1");

		WebsitePages pages = new WebsitePages();
		pages.getPages().addAll(IntStream.range(1, RANDOM.nextInt(2, 10)).mapToObj(this::createSampleWebPage).toList());
		website.setPages(pages);

		WebsiteCustomComponents customComponents = new WebsiteCustomComponents();
		customComponents.getCustomComponents()
				.addAll(IntStream.range(1, RANDOM.nextInt(2, 5)).mapToObj(this::createSampleCustomComponent).toList());
		website.setCustomComponents(customComponents);

		website.setLinkedFiles(createSampleWebsiteLinkedFiles());
		website.setScriptFiles(createSampleWebsiteScripts());

		return website;
	}

	private WebsiteScripts createSampleWebsiteScripts() {
		WebsiteScripts scripts = new WebsiteScripts();
		scripts.getFiles().addAll(
				IntStream.range(1, RANDOM.nextInt(2, 5)).mapToObj(n -> new WebsiteScript("Script - " + n)).toList());
		return scripts;
	}

	private WebsiteLinkedFiles createSampleWebsiteLinkedFiles() {
		WebsiteLinkedFiles linkedFiles = new WebsiteLinkedFiles();
		linkedFiles.getFiles().addAll(IntStream.range(1, RANDOM.nextInt(2, 5))
				.mapToObj(n -> new WebsiteStyleSheet("Style file - " + n)).toList());
		return linkedFiles;
	}

	private WebsiteCustomComponent createSampleCustomComponent(int n) {
		WebsiteCustomComponent websiteCustomComponent = new WebsiteCustomComponent("Component - " + n);
		fillElementContainerWithElements(websiteCustomComponent);
		return websiteCustomComponent;
	}

	private void fillElementContainerWithElements(WebPageElementContainer elementContainer) {
		final int bound = RANDOM.nextInt(2, 7);
		final List<WebPageElement> elements = IntStream.range(1, RANDOM.nextInt(1, bound)).mapToObj(k -> {
			final WebPageElement webPageElement = new WebPageElement("Element - " + k);
			fillElementContainerWithElements(webPageElement);
			return webPageElement;
		}).toList();
		elementContainer.getElements().addAll(elements);
	}

	private WebsitePage createSampleWebPage(int n) {
		WebsitePage websitePage = new WebsitePage("Page - " + n);
		fillElementContainerWithElements(websitePage);
		return websitePage;
	}
}
