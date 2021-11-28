package hu.domparse.hc1y8y;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomModifyHC1Y8Y {
    
    public static void main(String[] args) {
    try {
    	File inputFile = new File("XMLHC1Y8Y.xml");
    	 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
         Document doc = docBuilder.parse(inputFile);
         Node hibakezeles = doc.getFirstChild();
         Node hiba = doc.getElementsByTagName("hiba").item(0);
         
         //02-re módosítjuk a hid-t 01rõl
         NamedNodeMap attr = hiba.getAttributes();
         Node nodeAttr = attr.getNamedItem("hid");
         nodeAttr.setTextContent("02");
         
         NodeList list = hiba.getChildNodes();
         
         //a hányszor elõfordulást módosítjuk 3-ról 2re
         for (int temp = 0; temp < list.getLength(); temp++) {
        	 Node node = list.item(temp);
        	 if (node.getNodeType() == Node.ELEMENT_NODE) {
        		 Element hElement = (Element) node ;
        		 if ("hányszor".equals(hElement.getNodeName())) {
        			 if("3".equals(hElement.getTextContent())) 
        				hElement.setTextContent("2"); 
        			 }
        		 
        		 //az idõpontot 10:00-ról 11:00-ra módosítjuk
        		 if("mióta".equals(hElement.getNodeName())) {
        			 if("10:00".equals(hElement.getTextContent()))
        				 hElement.setTextContent("11:00");
        		 }
        	 }
        	 NodeList childNodes = hibakezeles.getChildNodes();
        	 for(int count = 0; count < childNodes.getLength(); count++) {
        		 Node node2 = childNodes.item(count);
        		 
        		 //kitöröljük a MIóta elementet.
        		 if("Mióta".equals(node.getNodeName()))
        			 hiba.removeChild(node2);
        	 }
         }
         
         //kiíratás
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
