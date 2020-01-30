package XML_Project_MVN.projectArtifact;


import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XPathParserApi {

	XPathParserApi() {
	}

	public void getAllAuthorsData(XPath xPath, Document doc) {

		try {
			String author = "ManagementSystem/Authors/Author";
			NodeList authorList = (NodeList) xPath.compile(author).evaluate(doc, XPathConstants.NODESET);

			String faculty = "/ManagementSystem/Universities/University/Faculty/Department/authorId";
			NodeList facultyList = (NodeList) xPath.compile(faculty).evaluate(doc, XPathConstants.NODESET);

			String article = "ManagementSystem/Articles/Article";
			NodeList articleList = (NodeList) xPath.compile(article).evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < authorList.getLength(); i++) {
				Node nNode = authorList.item(i);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String authorId = eElement.getAttribute("id");

					System.out.println(
							"First Name : " + eElement.getElementsByTagName("FirstName").item(0).getTextContent());
					System.out.println(
							"Last Name : " + eElement.getElementsByTagName("LastName").item(0).getTextContent());

					System.out.println("----Studies:----");

					for (int j = 0; j < facultyList.getLength(); j++) {

						Node nNodeFaculty = facultyList.item(j);

						if (nNodeFaculty.getNodeType() == Node.ELEMENT_NODE) {

							Element eElementFaculty = (Element) nNodeFaculty;
							String facultyAuthorId = eElementFaculty.getTextContent();

							if (authorId.equals(facultyAuthorId)) {
								Element departmentName = (Element) eElementFaculty.getParentNode();
								Element facultyName = (Element) eElementFaculty.getParentNode().getParentNode();
								Element universityName = (Element) eElementFaculty.getParentNode().getParentNode()
										.getParentNode();

								System.out.println(universityName.getAttribute("name") + "/"
										+ facultyName.getAttribute("name") + "/"
										+ departmentName.getElementsByTagName("name").item(0).getTextContent());

							}
						}

					}

					System.out.println("-------Published------");

					for (int k = 0; k < articleList.getLength(); k++) {

						Node nNodeArticle = articleList.item(k);

						if (nNodeArticle.getNodeType() == Node.ELEMENT_NODE) {

							Element eElementArticle = (Element) nNodeArticle;
							String articleAuthorId = eElementArticle.getElementsByTagName("authorId").item(0)
									.getTextContent();

							if (authorId.equals(articleAuthorId)) {

								System.out.println(
										eElementArticle.getElementsByTagName("title").item(0).getTextContent());

							}
						}

					}

				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	public void getAuthorDataById(XPath xPath, Document doc, String authorId) {

		try {
			String author = "/ManagementSystem/Authors/Author[@id = '" + authorId + "']";
			Node authorList = (Node) xPath.compile(author).evaluate(doc, XPathConstants.NODE);

			String faculty = "/ManagementSystem/Universities/University/Faculty/Department/authorId";
			NodeList facultyList = (NodeList) xPath.compile(faculty).evaluate(doc, XPathConstants.NODESET);

			String article = "ManagementSystem/Articles/Article";
			NodeList articleList = (NodeList) xPath.compile(article).evaluate(doc, XPathConstants.NODESET);

			if (authorList.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) authorList;

				System.out
						.println("First Name : " + eElement.getElementsByTagName("FirstName").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("LastName").item(0).getTextContent());

				System.out.println("----Studies:----");

				for (int j = 0; j < facultyList.getLength(); j++) {

					Node nNodeFaculty = facultyList.item(j);

					if (nNodeFaculty.getNodeType() == Node.ELEMENT_NODE) {

						Element eElementFaculty = (Element) nNodeFaculty;
						String facultyAuthorId = eElementFaculty.getTextContent();

						if (authorId.equals(facultyAuthorId)) {
							Element departmentName = (Element) eElementFaculty.getParentNode();
							Element facultyName = (Element) eElementFaculty.getParentNode().getParentNode();
							Element universityName = (Element) eElementFaculty.getParentNode().getParentNode()
									.getParentNode();

							System.out.println(
									universityName.getAttribute("name") + "/" + facultyName.getAttribute("name") + "/"
											+ departmentName.getElementsByTagName("name").item(0).getTextContent());

						}
					}

				}

				System.out.println("-------Published------");

				for (int k = 0; k < articleList.getLength(); k++) {

					Node nNodeArticle = articleList.item(k);

					if (nNodeArticle.getNodeType() == Node.ELEMENT_NODE) {

						Element eElementArticle = (Element) nNodeArticle;
						String articleAuthorId = eElementArticle.getElementsByTagName("authorId").item(0)
								.getTextContent();

						if (authorId.equals(articleAuthorId)) {

							System.out.println(eElementArticle.getElementsByTagName("title").item(0).getTextContent());

						}
					}

				}

			}

		} catch (

		XPathExpressionException e) {
			e.printStackTrace();
		}

	}

	public void getArticlesByAuthorId(XPath xPath, Document doc, String authorId) {

		try {
			String article = "ManagementSystem/Articles/Article";
			NodeList articleList = (NodeList) xPath.compile(article).evaluate(doc, XPathConstants.NODESET);

			String author = "/ManagementSystem/Authors/Author[@id = '" + authorId + "']";
			Node authorList = (Node) xPath.compile(author).evaluate(doc, XPathConstants.NODE);

			for (int k = 0; k < articleList.getLength(); k++) {

				Node nNodeArticle = articleList.item(k);

				if (nNodeArticle.getNodeType() == Node.ELEMENT_NODE) {

					Element eElementArticle = (Element) nNodeArticle;
					String articleAuthorId = eElementArticle.getElementsByTagName("authorId").item(0).getTextContent();

					Element eElement = (Element) authorList;

					if (authorId.equals(articleAuthorId)) {
						System.out.println("========");
						System.out.println(
								"First Name : " + eElement.getElementsByTagName("FirstName").item(0).getTextContent());
						System.out.println(
								"Last Name : " + eElement.getElementsByTagName("LastName").item(0).getTextContent());
						System.out.println(
								"Title: " + eElementArticle.getElementsByTagName("title").item(0).getTextContent());
						System.out.println(
								"Name: " + eElementArticle.getElementsByTagName("name").item(0).getTextContent());
						System.out.println(
								"Year: " + eElementArticle.getElementsByTagName("year").item(0).getTextContent());
						System.out.println(
								"DOI: " + eElementArticle.getElementsByTagName("DOI").item(0).getTextContent());
						System.out.println(
								"Rating: " + eElementArticle.getElementsByTagName("rating").item(0).getTextContent());

					}
				}

			}
		} catch (

		XPathExpressionException e) {
			e.printStackTrace();
		}

	}
}
