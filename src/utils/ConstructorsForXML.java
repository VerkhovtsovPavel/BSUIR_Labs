package utils;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConstructorsForXML {

	public void ParamLangXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	DocumentBuilder builder;

	/**
	 * Запись настроек в XML файл
	 */
	public void WriteParamXML() throws TransformerException, IOException {

		Document doc = builder.newDocument();
		Element RootElement = doc.createElement("proxy");

		Element NameElementTitle = doc.createElement("use");
		NameElementTitle.appendChild(doc.createTextNode("true"));
		RootElement.appendChild(NameElementTitle);

		Element NameElementCompile = doc.createElement("host");
		NameElementCompile.appendChild(doc.createTextNode("127.0.0.1"));
		RootElement.appendChild(NameElementCompile);

		Element NameElementRuns = doc.createElement("port");
		NameElementRuns.appendChild(doc.createTextNode("8080"));
		RootElement.appendChild(NameElementRuns);

		doc.appendChild(RootElement);

		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("proxy.xml")));

	}
}
