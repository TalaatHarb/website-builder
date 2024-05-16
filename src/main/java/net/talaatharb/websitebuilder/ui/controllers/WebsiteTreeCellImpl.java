package net.talaatharb.websitebuilder.ui.controllers;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import net.talaatharb.websitebuilder.dtos.WebPageElement;
import net.talaatharb.websitebuilder.dtos.WebPageElementContainer;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponent;
import net.talaatharb.websitebuilder.dtos.WebsiteCustomComponents;
import net.talaatharb.websitebuilder.dtos.WebsiteItemType;
import net.talaatharb.websitebuilder.dtos.WebsitePage;
import net.talaatharb.websitebuilder.dtos.WebsitePages;
import net.talaatharb.websitebuilder.dtos.WebsiteItem;

public class WebsiteTreeCellImpl extends TreeCell<WebsiteItem> {
	private TextField textField;

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getItem().toString());
		setGraphic(getTreeItem().getGraphic());
	}

	private void createContextMenuDetails(ContextMenu addMenu) {
		final WebsiteItemType itemType = getItem().getItemType();

		// change behavior depending on node type (can get pretty complex)
		switch (itemType) {
		case PAGES:
			createNewPageMenu(addMenu);
			break;
		case PAGE, ELEMENT, CUSTOM_COMPONENT:
			createNewElementMenu(addMenu);
			break;
		case CUSTOM_COMPONENTS:
			createNewComponentMenu(addMenu);
			break;
		default:
			break;
		}
	}

	private void createNewComponentMenu(ContextMenu addMenu) {
		final String addComponentPrompt = "Add Component";
		final String newComponentName = "New Component";
		final MenuItem addComponentMenuItem = new MenuItem(addComponentPrompt);
		addComponentMenuItem.setOnAction(t -> {
			WebsiteCustomComponent newComponent = new WebsiteCustomComponent(newComponentName);
			TreeItem<WebsiteItem> newComponentTreeItem = new TreeItem<>(newComponent);
			
			((WebsiteCustomComponents)getItem()).getCustomComponents().add(newComponent);
			getTreeItem().getChildren().add(newComponentTreeItem);
		});
		addMenu.getItems().add(addComponentMenuItem);
	}

	private void createNewElementMenu(ContextMenu addMenu) {
		final String addElementPrompt = "Add Element";
		final String newElementName = "New Element";
		final MenuItem addElementMenuItem = new MenuItem(addElementPrompt);
		addElementMenuItem.setOnAction(t -> {
			WebPageElement newElement = new WebPageElement(newElementName);
			TreeItem<WebsiteItem> newElementTreeItem = new TreeItem<>(newElement);
			
			((WebPageElementContainer)getItem()).getElements().add(newElement);
			getTreeItem().getChildren().add(newElementTreeItem);
		});
		addMenu.getItems().add(addElementMenuItem);
	}

	private void createNewPageMenu(ContextMenu addMenu) {
		final String addPagePrompt = "Add Page";
		final String newPageName = "New Page";
		final MenuItem addPageMenuItem = new MenuItem(addPagePrompt);
		addPageMenuItem.setOnAction(t -> {
			WebsitePage newPage = new WebsitePage(newPageName);
			TreeItem<WebsiteItem> newPageTreeItem = new TreeItem<>(newPage);
			
			((WebsitePages)getItem()).getPages().add(newPage);
			getTreeItem().getChildren().add(newPageTreeItem);
		});
		addMenu.getItems().add(addPageMenuItem);
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setOnKeyReleased(t -> {
			if (t.getCode() == KeyCode.ENTER) {
				final String newName = textField.getText();
				WebsiteItem item = getItem();
				item.setName(newName);
				commitEdit(item);
			} else if (t.getCode() == KeyCode.ESCAPE) {
				cancelEdit();
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}

	@Override
	public void startEdit() {
		super.startEdit();

		if (textField == null) {
			createTextField();
		}
		setText(null);
		setGraphic(textField);
		textField.selectAll();
	}

	@Override
	public void updateItem(WebsiteItem item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setText(null);
				setGraphic(textField);
			} else {
				setText(getString());
				setGraphic(getTreeItem().getGraphic());
				if (!WebsiteItemType.ROOT.equals(item.getItemType())) {
					final ContextMenu addMenu = new ContextMenu();
					createContextMenuDetails(addMenu);
					setContextMenu(addMenu);
				}
			}
		}
	}
}
