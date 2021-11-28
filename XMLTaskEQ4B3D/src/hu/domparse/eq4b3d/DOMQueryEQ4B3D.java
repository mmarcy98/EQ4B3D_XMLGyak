package hu.domparse.eq4b3d;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryEQ4B3D {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		File xmlFile = new File("XMLEQ4B3D.xml"); //fálj beolvasás
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		LoadtermeloQuery(doc);
	}

	public static void LoadtermeloQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("termelo"); //termelõ noteList létrehozása
		String termelo;
		Element element = null;
		Node nNode = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			String nev = element.getElementsByTagName("nev").item(0).getTextContent(); //lekérdezés név szerint
			System.out.println(nev);

		}
		System.out.println("\nÍrja be annak a termelõnek a nevét, amelyiknek adatait szeretné látni:");
		Scanner sc = new Scanner(System.in); //consolról beolvasás
		termelo = sc.nextLine();
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (termelo.equals("Újudvar termelõi Piac")) { 
					LoadBeszallitoQuery(doc, "1");
					break;
				}

				if (termelo.equals("Hévízi termelõi Piac")) {
					LoadBeszallitoQuery(doc, "2");
					break;
				}

				if (termelo.equals("Õriszentpéter Helyi termelõi Piac")) {
					LoadBeszallitoQuery(doc, "3");
					break;
				}
			}
		}
		sc.close();
	}

	public static void LoadBeszallitoQuery(Document doc, String bid) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("beszallito"); //beszállító noteList létrehozása

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			Element element = (Element) nNode;
			String tid = element.getAttribute("tid"); //a tid alapján kiszedjük az elemet
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (bid.equals(tid)) {
					String Bid = element.getAttribute("bid"); //bid alapján kiszedjük az elemet
					DOMReadEQ4B3D.ReadBeszallitoById(doc, Bid); //meghívjuk a beszállító függvényt
				}
			}
		}
	}
}