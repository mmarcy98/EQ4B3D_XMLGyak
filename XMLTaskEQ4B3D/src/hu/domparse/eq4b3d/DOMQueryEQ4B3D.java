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
		File xmlFile = new File("XMLEQ4B3D.xml"); //f�lj beolvas�s
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		LoadtermeloQuery(doc);
	}

	public static void LoadtermeloQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("termelo"); //termel� noteList l�trehoz�sa
		String termelo;
		Element element = null;
		Node nNode = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			String nev = element.getElementsByTagName("nev").item(0).getTextContent(); //lek�rdez�s n�v szerint
			System.out.println(nev);

		}
		System.out.println("\n�rja be annak a termel�nek a nev�t, amelyiknek adatait szeretn� l�tni:");
		Scanner sc = new Scanner(System.in); //consolr�l beolvas�s
		termelo = sc.nextLine();
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (termelo.equals("�judvar termel�i Piac")) { 
					LoadBeszallitoQuery(doc, "1");
					break;
				}

				if (termelo.equals("H�v�zi termel�i Piac")) {
					LoadBeszallitoQuery(doc, "2");
					break;
				}

				if (termelo.equals("�riszentp�ter Helyi termel�i Piac")) {
					LoadBeszallitoQuery(doc, "3");
					break;
				}
			}
		}
		sc.close();
	}

	public static void LoadBeszallitoQuery(Document doc, String bid) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("beszallito"); //besz�ll�t� noteList l�trehoz�sa

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			Element element = (Element) nNode;
			String tid = element.getAttribute("tid"); //a tid alapj�n kiszedj�k az elemet
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (bid.equals(tid)) {
					String Bid = element.getAttribute("bid"); //bid alapj�n kiszedj�k az elemet
					DOMReadEQ4B3D.ReadBeszallitoById(doc, Bid); //megh�vjuk a besz�ll�t� f�ggv�nyt
				}
			}
		}
	}
}