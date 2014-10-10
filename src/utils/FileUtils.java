package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import shapes.BaseShape;
import drawing.Painter;

public class FileUtils {
		
	public static void saveObjectListInObjectiveFile(String filePath) {
		FileOutputStream fileStream;
		try {
			fileStream = new FileOutputStream(filePath);
			ObjectOutputStream serialazer = new ObjectOutputStream(fileStream);
			serialazer.writeObject(Painter.getObjectList());
			serialazer.flush();
			serialazer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void readObjectListFromObjectiveFile(String filePath) {
		FileInputStream fileStream;
		ObjectInputStream deserialazer;
		try {
			fileStream = new FileInputStream(filePath);
			deserialazer = new ObjectInputStream(fileStream);
			Painter.raiseList((ArrayList<BaseShape>) deserialazer.readObject());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveObjectListInXMLFile(String filePath, ArrayList<BaseShape> shapeList) {
		DocumentBuilder builder = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Document doc = builder.newDocument();
		Element RootElement = doc.createElement("Shapes");

		Iterator<BaseShape> shapeIterator = shapeList.iterator();
		while (shapeIterator.hasNext()) {
			BaseShape currentShape = (BaseShape) shapeIterator.next();
			Element shape = doc.createElement("shape");
			RootElement.appendChild(shape);

			Element shapeName = doc.createElement("name");
			shapeName.appendChild(doc.createTextNode(currentShape.getShapeName()));
			shape.appendChild(shapeName);

			Element shapeParameters = doc.createElement("parameters");
			Iterator<Integer> paramIterator = currentShape.getParams().iterator();
			while (paramIterator.hasNext()) {
				Integer currentParameter = paramIterator.next();
				Element parameter = doc.createElement("param");
				parameter.appendChild(doc.createTextNode(currentParameter.toString()));
				shapeParameters.appendChild(parameter);
			}
			shape.appendChild(shapeParameters);
		}
		if (shapeList.isEmpty()) {
			RootElement.appendChild(doc.createTextNode("Shape list is empty"));
		}
		doc.appendChild(RootElement);

		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(filePath)));
		} catch (FileNotFoundException | TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	public static void readObjectListInXMLFile(String filePath) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filePath));
		} catch (SAXException | IOException | ParserConfigurationException e) {

			e.printStackTrace();
		}

		ArrayList<BaseShape> shapeList = new ArrayList<BaseShape>();
		ArrayList<Integer> paramList = new ArrayList<Integer>();

		NodeList shapes = document.getDocumentElement().getChildNodes();
		String shapeName = null;
		for (int i = 0; i < shapes.getLength(); i++) {

			Node shape = shapes.item(i);
			if (shape instanceof Element) {
				NodeList shapeAttributes = shape.getChildNodes();
				for (int j = 0; j < shapeAttributes.getLength(); j++) {
					Node attribute = shapeAttributes.item(j);
					if (attribute instanceof Element) {
						switch (attribute.getNodeName()) {
						case "name":
							shapeName = attribute.getTextContent();
							break;
						case "parameters":
							paramList.clear();
							NodeList parameters = attribute.getChildNodes();
							for (int k = 0; k < parameters.getLength(); k++) {
								Node parameter = parameters.item(k);
								if (parameter instanceof Element) {
									paramList.add(Integer.valueOf(parameter.getTextContent()));
								}
							}
							break;
						}

					}
				}
				Class<?> shapeClass;
				try {
					shapeClass = Class.forName("shapes." + shapeName);
					Constructor<?> shapeConstructor = shapeClass.getDeclaredConstructors()[0];
					shapeList.add((BaseShape) shapeConstructor.newInstance(paramList));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.out.println("Error when open XML file");
					e.printStackTrace();
				}
			}

		}
		Painter.raiseList(shapeList);
	}

}
