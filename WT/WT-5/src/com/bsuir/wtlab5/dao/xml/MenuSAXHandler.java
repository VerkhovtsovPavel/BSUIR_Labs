package com.bsuir.wtlab5.dao.xml;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.bsuir.wtlab5.entity.Dish;

public class MenuSAXHandler extends DefaultHandler {
	private Logger log = Logger.getLogger(MenuSAXHandler.class);

	private ArrayList<Dish> menu = new ArrayList<Dish>();
	private Dish dish;
	private StringBuilder text;

	private ArrayList<String> ingredients;

	public ArrayList<Dish> getMenu() {
		return menu;
	}

	public void startDocument() throws SAXException {
		log.info("Parsing started.");
	}

	public void endDocument() throws SAXException {
		log.info("Parsing ended.");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		log.info("startElement -> " + "uri: " + uri + ", localName: "
				+ localName + ", qName: " + qName);
		text = new StringBuilder();
		if (qName.equals("Dish")) {
			dish = new Dish();
			dish.setId((Integer.parseInt(attributes.getValue("id").replace("\\D+",""))));
		}
	}

	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (qName.toUpperCase().replace("-", "_")) {
		case "name":
			dish.setName(text.toString());
			break;
		case "cost":
			dish.setCost(Integer.parseInt(text.toString()));
			break;
		case "ingredient":
			ingredients.add(text.toString());
			break;
		case "ingredients":
			dish.setIngredients(ingredients);
			ingredients.clear();
			break;
		case "class":
			dish.setDishClass(text.toString());
		case "dish":
			menu.add(dish);
			dish = null;
			break;
		}
	}
}
