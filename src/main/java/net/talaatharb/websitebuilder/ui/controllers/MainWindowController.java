package net.talaatharb.websitebuilder.ui.controllers;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

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
import net.talaatharb.websitebuilder.dtos.Website;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponent;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponents;
import net.talaatharb.websitebuilder.dtos.WebsiteItem;
import net.talaatharb.websitebuilder.dtos.WebsitePage;
import net.talaatharb.websitebuilder.dtos.WebsitePages;
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
	}

	private Website createSampleWebsite() {
		final Website website = new Website();
		website.setName("Project 1");

		WebsitePages pages = new WebsitePages();
		pages.getPages().addAll(IntStream.range(1, RANDOM.nextInt(2, 10)).mapToObj(this::createSampleWebPage).toList());
		website.setPages(pages);

		WebsiteCustomComponents customComponents = new WebsiteCustomComponents();
		customComponents.getCustomComponents().addAll(IntStream.range(1, RANDOM.nextInt(2, 5))
				.mapToObj(n -> new WebsiteCustomComponent("Component - " + n)).toList());
		website.setCustomComponents(customComponents);
		return website;
	}

	private WebsitePage createSampleWebPage(int n) {
		WebsitePage websitePage = new WebsitePage("Page - " + n);
		List<WebPageElement> elements = IntStream.range(1, RANDOM.nextInt(5, 10)).mapToObj(k -> new WebPageElement("Element - " + k)).toList();
		websitePage.getElements().addAll(elements);
		return websitePage;
	}
}
