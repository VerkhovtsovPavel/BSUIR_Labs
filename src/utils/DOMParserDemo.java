package utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import drawing.Painter;

import shapes.BaseShape;

public class DOMParserDemo {

	public static void main(String[] args) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("xml/temp.xml"));

		ArrayList<BaseShape> shapeList = new ArrayList<BaseShape>();

		NodeList shapes = document.getDocumentElement().getChildNodes();
		String shapeName = null;
		for (int i = 0; i < shapes.getLength(); i++) {
			ArrayList<Integer> paramList = new ArrayList<Integer>();
			Node shape = shapes.item(i);
			if (shape instanceof Element) {
				NodeList shapeAttributes = shape.getChildNodes();
				for (int j = 0; j < shapeAttributes.getLength(); j++) {
					Node attribute = shapeAttributes.item(j);
					if (attribute instanceof Element) {
						switch (attribute.getNodeName()) {
						case "name":
							shapeName = shape.getAttributes().getNamedItem("name").getNodeValue();
							break;
						case "parameters":
							paramList.clear();
							NodeList parameters = attribute.getChildNodes();
							for (int k = 0; k < parameters.getLength(); k++) {
								Node parameter = parameters.item(j);
								paramList.add(Integer.valueOf(parameter.getNodeValue()));
							}
							break;
						}

					}
				}
			}
			Class<?> shapeClass = Class.forName("shapes." + shapeName);
			Constructor<?> shapeConstructor = shapeClass.getDeclaredConstructors()[0];
			shapeList.add((BaseShape) shapeConstructor.newInstance(paramList));
		}
		
		Painter.raiseList(shapeList);

	}

}

class Employee {
	String id;
	String firstName;
	String lastName;
	String location;

	@Override
	public String toString() {
		return firstName + " " + lastName + "(" + id + ")" + location;
	}
}
