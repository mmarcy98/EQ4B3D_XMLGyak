package xpatheq4b3d1109;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathEQ4B3D {
	
	public static void main(String[] args) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse("C:\\WWW\\EQ4B3D_1109\\studentEQ4B3D.xml");
		document.getDocumentElement().normalize();
		
		XPath xPath = XPathFactory.newInstance().newXPath();
		 String expression = "class";
		 
		 NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathContent.NODESET);
		 
		 for(int i = 0; i < nodeList.getLength(); i++) {
			 Node node = NodeList.item(i);
			 
			 System.out.println("\nAktuális elem: "+node.getUserData());
			 
			 if(node.getNodeType()==Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
				 Element element = (Element) node;
				 
				 System.out.println("Hallgató ID: " +element.getAttribute("id"));
			 }
			 
		 }
		
	}

}
