package XML_Project_MVN.projectArtifact;


import java.io.File;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

public class XPathParser {

	public static void main(String[] args) {
		try {
			File inputFile = new File("file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			XPath xPath = XPathFactory.newInstance().newXPath();
			
			XPathParserApi xPathParser = new XPathParserApi();
			
			xPathParser.getAllAuthorsData(xPath, doc);
//			xPathParser.getAuthorDataById(xPath, doc, "a1");
//			xPathParser.getArticlesByAuthorId(xPath, doc, "a1");
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
