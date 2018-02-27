

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XML {
	static DocumentBuilderFactory docFactory;// = DocumentBuilderFactory.newInstance();
	static DocumentBuilder docBuilder;// = docFactory.newDocumentBuilder();
	static Document doc;// = docBuilder.newDocument();
	
	static{
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Element> generujElementy(Zakladka danePodstawowe, Zakladka sprzedaze, Zakladka zakupy){
		List<Element> lista = new ArrayList<>();
		List<Element> danePodstawoweList = generujElementyDanePodstawowe(danePodstawowe);
		for (Element e: danePodstawoweList){
			lista.add(e);
		}
		System.out.println(danePodstawoweList.size());
		List<Element> sprzedazeList = generujElementyJednejZakladki(sprzedaze);
		for (Element e: sprzedazeList){
			lista.add(e);
		}
		lista.add(generujPodsumowanieDlaZakladki(sprzedaze));
		List<Element> zakupyList = generujElementyJednejZakladki(zakupy);
		for (Element e: zakupyList){
			lista.add(e);
		}
		lista.add(generujPodsumowanieDlaZakladki(zakupy));
		return lista;
	}
	
	public static List<Element> generujElementyDanePodstawowe(Zakladka danePodstawowe){
		List<Element> lista = new ArrayList<>();
		Element naglowek = doc.createElement("tns:Naglowek");
		
		Element kodFormularza = doc.createElement("tns:KodFormularza");
		kodFormularza.setAttribute("wersjaSchemy", danePodstawowe.get(0).get(0));
		kodFormularza.setAttribute("kodSystemowy", danePodstawowe.get(0).get(1));
		kodFormularza.appendChild(doc.createTextNode(danePodstawowe.get(0).get(2)));
		
		naglowek.appendChild(kodFormularza);
		
		List<String> tytulyKolumn = BibliotekaWspolnychMetod.pobierzTytulyKolumn(danePodstawowe.getZakres());
		for (int i=3;i<=8;i++){
			Element e = doc.createElement("tns:"+tytulyKolumn.get(i));
			e.appendChild(doc.createTextNode(danePodstawowe.get(0).get(i)));
			naglowek.appendChild(e);
		}
		lista.add(naglowek);
		Element podmiot1 = doc.createElement("tns:Podmiot1");
		for (int i=9;i<=11;i++){
			Element e = doc.createElement("tns:"+tytulyKolumn.get(i));
			e.appendChild(doc.createTextNode(danePodstawowe.get(0).get(i)));
			podmiot1.appendChild(e);
		}
		lista.add(podmiot1);
		
		return lista;
	}
	
	public static List<Element> generujElementyJednejZakladki(Zakladka zakladka){
		List<Element> lista = new ArrayList<>();
		for (List<String> l : zakladka){
			Element element = doc.createElement("tns:"+zakladka.getZakres().getTagXML()+"Wiersz");
			List<String> tytulyKolumn = BibliotekaWspolnychMetod.pobierzTytulyKolumn(zakladka.getZakres());
			Iterator<String> i = tytulyKolumn.iterator();
			for (String s: l){
				if (s.equals("")){
					i.next();
				}else{
					Element e = doc.createElement("tns:"+i.next()+"");
					e.appendChild(doc.createTextNode(s));
					element.appendChild(e);
				}
			}
			lista.add(element);
		}
		return lista;
	}
	
	public static Element generujPodsumowanieDlaZakladki(Zakladka zakladka){
		Element e = doc.createElement("tns:"+zakladka.getZakres().getTagXML()+"Ctrl");
		Element liczbaWierszy = doc.createElement("tns:LiczbaWierszy"+zakladka.getZakres().getTagXML());
		liczbaWierszy.appendChild(doc.createTextNode(""+zakladka.size()));
		Element podatekNaliczony = doc.createElement("tns:PodatekNaliczony");
		podatekNaliczony.appendChild(doc.createTextNode(""+String.format( "%.2f", BibliotekaWspolnychMetod.obliczPodatek(zakladka, zakladka.getZakres()))));
		e.appendChild(liczbaWierszy);
		e.appendChild(podatekNaliczony);
		//...
		return e;
	}
	
	public static void zapiszDoXML(List<Element> elementy){
				
		try {
			
			Element rootElement = doc.createElement("tns:JPK");
			rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootElement.setAttribute("xmlns:tns", "http://jpk.mf.gov.pl/wzor/2017/11/13/1113/");
			rootElement.setAttribute("xmlns:etd", "http://crd.gov.pl/xml/schematy/dziedzinowe/mf/2016/01/25/eD/DefinicjeTypy/");
			doc.appendChild(rootElement);
			
			for (Element e: elementy){
				rootElement.appendChild(e);
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource ds = new DOMSource(doc);
			StreamResult sr = new StreamResult(new File("xml.xml"));
			
			t.transform(ds, sr);
			
		}catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String argv[]) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("company");
		doc.appendChild(rootElement);

		// staff elements
		Element staff = doc.createElement("Staff");
		rootElement.appendChild(staff);

		// set attribute to staff element
		Attr attr = doc.createAttribute("id");
		attr.setValue("1");
		staff.setAttributeNode(attr);

		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
		Element firstname = doc.createElement("firstname");
		firstname.appendChild(doc.createTextNode("yong"));
		staff.appendChild(firstname);

		// lastname elements
		Element lastname = doc.createElement("lastname");
		lastname.appendChild(doc.createTextNode("mook kim"));
		staff.appendChild(lastname);

		// nickname elements
		Element nickname = doc.createElement("nickname");
		nickname.appendChild(doc.createTextNode("mkyong"));
		staff.appendChild(nickname);

		// salary elements
		Element salary = doc.createElement("salary");
		salary.appendChild(doc.createTextNode("100000"));
		staff.appendChild(salary);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\file.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}