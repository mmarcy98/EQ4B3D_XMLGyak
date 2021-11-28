package hu.domparse.hc1y8y;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DomQueryHC1Y8Y {
	
	public static void main(String argv[]) {
		try {
			File inputFile = new File("XMLHC1Y8Y.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.print("Root element: ");
			System.out.println(doc.getDocumentElement().getNodeName());
			NodeList nlist = doc.getElementsByTagName("hiba");
			System.out.println("-------------------------------------------");
			
			//Kiválasztjuk és kiírjuk a Hiba elemet.
			for(int temp =0; temp < nlist.getLength(); temp++) {
				Node nNode = nlist.item(temp);
				System.out.println("\n Current Element: ");
				System.out.println(nNode.getNodeName());
				
				//a hibán belül megkeressük a hibaüzenet elemeket és kiiratjuk id-nak megfelelõen 
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element hElement = (Element) nNode;
					System.out.print("id : ");
					System.out.println(hElement.getAttribute("hid"));
					NodeList hibaüzenetlist = hElement.getElementsByTagName("hibaüzenet");
					
					for (int count = 0; count < hibaüzenetlist.getLength(); count++) {
						Node node = hibaüzenetlist.item(count);
						
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element hibaüzenet = (Element) node;
							System.out.print("hibaüzenet: :");
							System.out.println(hibaüzenet.getTextContent());
						}
					}
				}
				//a hibán belül megkeressük a beosztás elemeket és kiiratjuk idnak megfelelõen.
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element hElement = (Element) nNode;
					System.out.print("id : ");
					System.out.println(hElement.getAttribute("hid"));
					NodeList beosztáslist = hElement.getElementsByTagName("beosztás");
					
					for (int count = 0; count < beosztáslist.getLength(); count++) {
						Node node = beosztáslist.item(count);
						
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element beosztás = (Element) node;
							System.out.print("beosztás: :");
							System.out.println(beosztás.getTextContent());
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
