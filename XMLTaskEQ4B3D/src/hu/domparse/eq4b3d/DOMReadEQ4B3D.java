package hu.domparse.eq4b3d;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMReadEQ4B3D {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLEQ4B3D.xml"); // f�jlt beolvassa
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			Read(doc);
			//ExceptionHandling
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static void Read(Document doc) {
		NodeList nList = doc.getElementsByTagName("etterem_terv"); //l�trehozzuk a NoteListet, amiben elt�roljuk az elemeket
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) { //az ID-k kiszed�se
				String eid = element.getAttribute("eid");
				String bid = element.getAttribute("bid");
				String szid = element.getAttribute("szid");
				ReadEtteremById(doc, eid);
				ReadBeszallitoById(doc, bid);
				ReadSzemelyzetById(doc, szid);
			}
		}
	}

	public static void ReadEtteremById(Document doc, String eid) {
		NodeList nList = doc.getElementsByTagName("etterem"); //az �tterem adatait tartalmaz� nodeList l�trehoz�sa
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("eid").equals(eid)) { //kiirand� adatok kiszed�se
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String menu = element.getElementsByTagName("menu").item(0).getTextContent();
					String tulajdonos = element.getElementsByTagName("tulajdonos").item(0).getTextContent();
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					System.out.println("�tterem adatok: \n\tN�v:\t" + nev + "\n\tTulajdonos:\t" + tulajdonos
							+ "\n\tEl�rhet�s�g:\t" + elerhetoseg + "\n\tMen�:\t" + menu + "\n\tCim:\t" + cim); //ki�rat�s
				}
			}
		}
	}

	public static void ReadBeszallitoById(Document doc, String bid) {
		NodeList nList = doc.getElementsByTagName("beszallito"); //a besz�ll�t� adatait tartalmaz� nodeList l�trehoz�sa
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("bid").equals(bid)) { //kiirand� adatok kiszed�se
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					System.out.println("Besz�ll�t� adatok: \n\tN�v:\t" + nev + "\n\tEl�rhet�s�g:\t" + elerhetoseg
							+ "\n\tC�m:\t" + cim); //ki�rat�s
					String tid = element.getAttribute("tid");
					ReadTermeloById(doc, tid);
				}
			}
		}
	}

	public static void ReadTermeloById(Document doc, String tid) {
		NodeList nList = doc.getElementsByTagName("termelo"); //a termel� adatait tartalmaz� nodeList l�trehoz�sa
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("tid").equals(tid)) { //kiirand� adatok kiszed�se
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					System.out.println("Termel� adatok: \n\tN�v:\t" + nev + "\n\tEl�rhet�s�g:\t" + elerhetoseg
							+ "\n\tC�m:\t" + cim); //ki�rat�s
				}
			}
		}
	}

	public static void ReadSzemelyzetById(Document doc, String szid) {
		NodeList nList = doc.getElementsByTagName("szemelyzet"); //a szem�lyzet adatait tartalmaz� nodeList l�trehoz�sa
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("szid").equals(szid)) { //kiirand� adatok kiszed�se
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String munkakor = element.getElementsByTagName("munkakor").item(0).getTextContent();
					String SZIG = element.getElementsByTagName("SZIG").item(0).getTextContent();
					String szul_datum = element.getElementsByTagName("szul_datum").item(0).getTextContent();
					String vendeg = element.getElementsByTagName("vendeg").item(0).getTextContent();
					System.out.println("Szem�lyzet adatok: \n\tN�v:\t" + nev + "\n\tEl�rhet�s�g:\t" + elerhetoseg
							+ "\n\tC�m:\t" + cim + "\n\tMunkak�r:\t" + munkakor + "\n\tSzem�lyigazolv�ny sz�m:\t" + SZIG
							+ "\n\tSz�let�si d�tum:\t" + szul_datum + "\n\tVend�gek sz�ma:\t" + vendeg); //ki�rat�s
					String fid = element.getAttribute("fid");
					ReadFoglalasById(doc, fid);
				}
			}
		}
	}

	public static void ReadFoglalasById(Document doc, String fid) {
		NodeList nList = doc.getElementsByTagName("foglalas"); //a foglal�s adatait tartalmaz� nodeList l�trehoz�sa
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("fid").equals(fid)) { //kiirand� adatok kiszed�se
					String hany_fo = element.getElementsByTagName("hany_fo").item(0).getTextContent();
					String datum = element.getElementsByTagName("datum").item(0).getTextContent();
					System.out.println("Foglal�s adatok: \n\tH�ny f�re:\t" + hany_fo + "\n\tD�tum:\t" + datum); //ki�rat�s
				}
			}
		}
	}
}
