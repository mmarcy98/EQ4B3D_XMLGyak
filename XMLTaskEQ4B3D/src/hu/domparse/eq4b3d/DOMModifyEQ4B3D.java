package hu.domparse.eq4b3d;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMModifyEQ4B3D {

	public static void main(String[] args) {
		try {
			File inputFile = new File("XMLEQ4B3D.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			Node szemelyzet = doc.getElementsByTagName("szemelyzet").item(0);

			// 10-re módosítjuk a szid-t 1-rõl
			NamedNodeMap attr = szemelyzet.getAttributes();
			Node nodeAttr = attr.getNamedItem("szid");
			nodeAttr.setTextContent("10");

			NodeList list = szemelyzet.getChildNodes();

			// a hibaüzenetet megváltoztatjuk
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element hElement = (Element) node;
					if ("SZIG".equals(hElement.getNodeName())) {
						if ("156934AS".equals(hElement.getTextContent()))
							hElement.setTextContent("156934SE");
					}
				}

			}
			NodeList list2 = doc.getElementsByTagName("beszallito");
			for (int j = 0; j < list2.getLength(); j++) {
				Node beszallito = list2.item(j);
				if (beszallito.getNodeType() == Node.ELEMENT_NODE) {
					String id = beszallito.getAttributes().getNamedItem("bid").getTextContent();
					if ("1".equals(id.trim())) {
						NodeList gyerekNodes = beszallito.getChildNodes();
						for (int k = 0; k < gyerekNodes.getLength(); k++) {
							Node item = gyerekNodes.item(k);
							if (item.getNodeType() == Node.ELEMENT_NODE) {
								// kitöröljük az ügyfélbõl a kód mezõt
								if ("elerhetoseg".equalsIgnoreCase(item.getNodeName())) {
									beszallito.removeChild(item);
								}
								// módosítjuk az 1-es ügyfélidvel rendelkezõ nevét
								if ("nev".equalsIgnoreCase(item.getNodeName())) {
									item.setTextContent("XYZ");
								}
							}
						}
					}
				}
			}
			// kiíratás
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.println("-----------Módosított File-----------");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}