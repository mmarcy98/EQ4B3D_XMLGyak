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
			File xmlFile = new File("XMLEQ4B3D.xml"); // fájlt beolvassa
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
		NodeList nList = doc.getElementsByTagName("etterem_terv"); //létrehozzuk a NoteListet, amiben eltároljuk az elemeket
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) { //az ID-k kiszedése
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
		NodeList nList = doc.getElementsByTagName("etterem"); //az étterem adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("eid").equals(eid)) { //kiirandó adatok kiszedése
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String menu = element.getElementsByTagName("menu").item(0).getTextContent();
					String tulajdonos = element.getElementsByTagName("tulajdonos").item(0).getTextContent();
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					System.out.println("Étterem adatok: \n\tNév:\t" + nev + "\n\tTulajdonos:\t" + tulajdonos
							+ "\n\tElérhetõség:\t" + elerhetoseg + "\n\tMenü:\t" + menu + "\n\tCim:\t" + cim); //kiíratás
				}
			}
		}
	}

	public static void ReadBeszallitoById(Document doc, String bid) {
		NodeList nList = doc.getElementsByTagName("beszallito"); //a beszállító adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("bid").equals(bid)) { //kiirandó adatok kiszedése
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					System.out.println("Beszállító adatok: \n\tNév:\t" + nev + "\n\tElérhetõség:\t" + elerhetoseg
							+ "\n\tCím:\t" + cim); //kiíratás
					String tid = element.getAttribute("tid");
					ReadTermeloById(doc, tid);
				}
			}
		}
	}

	public static void ReadTermeloById(Document doc, String tid) {
		NodeList nList = doc.getElementsByTagName("termelo"); //a termelõ adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("tid").equals(tid)) { //kiirandó adatok kiszedése
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					System.out.println("Termelõ adatok: \n\tNév:\t" + nev + "\n\tElérhetõség:\t" + elerhetoseg
							+ "\n\tCím:\t" + cim); //kiíratás
				}
			}
		}
	}

	public static void ReadSzemelyzetById(Document doc, String szid) {
		NodeList nList = doc.getElementsByTagName("szemelyzet"); //a személyzet adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("szid").equals(szid)) { //kiirandó adatok kiszedése
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					String munkakor = element.getElementsByTagName("munkakor").item(0).getTextContent();
					String SZIG = element.getElementsByTagName("SZIG").item(0).getTextContent();
					String szul_datum = element.getElementsByTagName("szul_datum").item(0).getTextContent();
					String vendeg = element.getElementsByTagName("vendeg").item(0).getTextContent();
					System.out.println("Személyzet adatok: \n\tNév:\t" + nev + "\n\tElérhetõség:\t" + elerhetoseg
							+ "\n\tCím:\t" + cim + "\n\tMunkakör:\t" + munkakor + "\n\tSzemélyigazolvány szám:\t" + SZIG
							+ "\n\tSzületési dátum:\t" + szul_datum + "\n\tVendégek száma:\t" + vendeg); //kiíratás
					String fid = element.getAttribute("fid");
					ReadFoglalasById(doc, fid);
				}
			}
		}
	}

	public static void ReadFoglalasById(Document doc, String fid) {
		NodeList nList = doc.getElementsByTagName("foglalas"); //a foglalás adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("fid").equals(fid)) { //kiirandó adatok kiszedése
					String hany_fo = element.getElementsByTagName("hany_fo").item(0).getTextContent();
					String datum = element.getElementsByTagName("datum").item(0).getTextContent();
					System.out.println("Foglalás adatok: \n\tHány fõre:\t" + hany_fo + "\n\tDátum:\t" + datum); //kiíratás
				}
			}
		}
	}
}
