package com.bsuir.wtlab5.dao.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.bsuir.wtlab5.dao.AdminMenuDao;
import com.bsuir.wtlab5.dao.db.UserMySQLDao;
import com.bsuir.wtlab5.entity.Dish;
import com.bsuir.wtlab5.exception.DaoException;
import com.bsuir.wtlab5.source.MenuStorage;

public class AdminXMLMenuDao implements AdminMenuDao {
	private static Logger log = Logger.getLogger(UserMySQLDao.class);

	private static final String XML_FILE_PATH = "menu.xml";

	private static AdminXMLMenuDao instance;

	public static AdminXMLMenuDao getInstance() {
		if (instance == null) {
			instance = new AdminXMLMenuDao();
		}
		return instance;
	}

	private AdminXMLMenuDao() {
	}

	@Override
	public ArrayList<Dish> getMenu() throws DaoException {
		MenuSAXHandler handler = new MenuSAXHandler();
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			xmlReader.setContentHandler(handler);
			InputSource xmlSource = new InputSource(XML_FILE_PATH);
			xmlReader.parse(xmlSource);
		} catch (SAXException | IOException e) {
			throw new DaoException("Error while parse Xml " + XML_FILE_PATH, e);
		}
		log.info("Menu successfully load from " + XML_FILE_PATH);
		return handler.getMenu();
	}

	@Override
	public void saveMenu(ArrayList<Dish> menu) throws DaoException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = builder.newDocument();
			Element rootElement = doc.createElementNS("http://www.bsuir.by/pavelVerk/menu", "menu");
			// rootElement.setAttributeNS("http://www.w3.org/2000/xmlns/",
			// "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation",
					"http://www.bsuir.by/pavelVerk/menu menu.xsd");
			// xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
			// xsi:schemaLocation =
			// "http://www.bsuir.by/pavelVerk/menu menu.xsd"

			for (Dish currentDish : MenuStorage.getInstanse().getMenu()) {
				Element dish = doc.createElement("dish");
				dish.setAttribute("id", "ID-" + String.valueOf(currentDish.getId()));
				rootElement.appendChild(dish);

				Element dishName = doc.createElement("name");
				dishName.appendChild(doc.createTextNode(currentDish.getName()));
				dish.appendChild(dishName);

				Element dishCost = doc.createElement("cost");
				dishCost.appendChild(doc.createTextNode(String.valueOf(currentDish.getCost())));
				dish.appendChild(dishCost);

				Element ingredients = doc.createElement("ingredients");
				for (String ingredient : currentDish.getIngredients()) {
					Element dishIngredient = doc.createElement("ingredient");
					dishIngredient.appendChild(doc.createTextNode(ingredient));
					ingredients.appendChild(dishIngredient);
				}
				dish.appendChild(ingredients);

				Element dishClass = doc.createElement("class");
				dishClass.appendChild(doc.createTextNode(currentDish.getDishClass()));
				dish.appendChild(dishClass);
			}
			doc.appendChild(rootElement);

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(XML_FILE_PATH)));

		} catch (ParserConfigurationException | FileNotFoundException | TransformerException e) {
			throw new DaoException("Error while write menu in " + XML_FILE_PATH, e);
		}
		log.info("Menu successfully save to " + XML_FILE_PATH);

	};

}
