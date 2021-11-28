package hu.domparse.hc1y8y;

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

public class DomReadHC1Y8Y {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      try {   
	          
	          DocumentBuilder db = dbf.newDocumentBuilder();
	          Document doc = db.parse(new File("XMLHC1Y8Y.xml")); 
	          doc.getDocumentElement().normalize();
	          
	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println();
	          
	          NodeList list = doc.getElementsByTagName("hiba");
	    
	          
	          for (int temp = 0; temp < list.getLength(); temp++) {
	              Node node = list.item(temp);
	              if (node.getNodeType() == Node.ELEMENT_NODE) {
	                  Element element = (Element) node;  
	                  //id elérése
	                  String hid = element.getAttribute("hid"); 
	                  
	                  //Stringek elérése
	                  String hibaüzenet = element.getElementsByTagName("hibaüzenet").item(0).getTextContent();
	                  String mióta = element.getElementsByTagName("mióta").item(0).getTextContent();
	                  String hányszor = element.getElementsByTagName("hányszor").item(0).getTextContent();
	                  
	                  //Kiiratás
	                  System.out.println("Current Element :" + node.getNodeName());
	                  System.out.println("Hiba Id : " + hid);
	                  System.out.println("Hibaüzenet : " + hibaüzenet);
	                  System.out.println("A hiba fennáll : " + mióta + " óta");
	                  System.out.println("A hiba elõfordult : " + hányszor);
	                  System.out.println();
	                  
	                  NodeList ügyfel = doc.getElementsByTagName("ügyfél");
		                Node ünode = ügyfel.item(temp);
						if(ünode.getNodeType() == Node.ELEMENT_NODE) {
							Element üelement = (Element) ünode;
							
							//Stringek elérése
							String üid = üelement.getAttribute("üid");	
							String szül_idõ = üelement.getElementsByTagName("szül_idõ").item(0).getTextContent();
							String szül_hely = üelement.getElementsByTagName("szül_hely").item(0).getTextContent();
							String kód = üelement.getElementsByTagName("kód").item(0).getTextContent();
							String név = üelement.getElementsByTagName("név").item(0).getTextContent();
							
							//Kiiratás
							System.out.println("Az ügyfél idja : " + üid);
							System.out.println("Az ügyfél szül. ideje : " + szül_idõ);
				            System.out.println("Az ügyfél szül. helye : " + szül_hely);
				            System.out.println("Az ügyfél kódja : " + kód);
				            System.out.println("Az ügyfél neve : " + név);
				            System.out.println();
				            
				            
				       NodeList ügyintezo = doc.getElementsByTagName("ügyintézõ");
				       		Node ügynode = ügyintezo.item(temp);
							if(ügynode.getNodeType() == Node.ELEMENT_NODE) {
								Element ügyelement = (Element) ügynode;
								
								//Stringek elérése
								String ügyid = ügyelement.getAttribute("ügyid");	
								String szint = ügyelement.getElementsByTagName("szint").item(0).getTextContent();
								String beosztás = ügyelement.getElementsByTagName("beosztás").item(0).getTextContent();
								String ügynév = ügyelement.getElementsByTagName("név").item(0).getTextContent();
									
								//Kiiratás
								System.out.println("Az ügyintézõ idja : " + ügyid);
								System.out.println("Az ügyintézõ szintje : " + szint);
					            System.out.println("Az ügyintézõ beosztása : " + beosztás);
					            System.out.println("Az ügyintézõ neve : " + ügynév);
					            System.out.println();
					          
					  NodeList vezeto = doc.getElementsByTagName("vezetõ");
					       	Node vnode = vezeto.item(temp);
							if(vnode.getNodeType() == Node.ELEMENT_NODE) {
								Element velement = (Element) vnode;
									
								//Stringek elérése
								String vid = velement.getAttribute("vid");	
								String vszint = velement.getElementsByTagName("szint").item(0).getTextContent();
								String vbeosztás = velement.getElementsByTagName("beosztás").item(0).getTextContent();
								String vnév = velement.getElementsByTagName("név").item(0).getTextContent();
									
								//Kiiratás
								System.out.println("A vezetõ idja : " + vid);
								System.out.println("A vezetõ szintje : " + vszint);
						        System.out.println("A vezetõ beosztása : " + vbeosztás);
						        System.out.println("A vezetõ neve : " + vnév);
						        System.out.println();
								}

							}
		                }
	                  
	              }
	          }

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }

	  }

	}
