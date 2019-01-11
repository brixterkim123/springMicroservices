import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.accenture.sef.xml.impl.BarsWriteXmlUtils;
import com.accenture.sef.xml.interfce.BarsWriteXMLUtilsInterface;


public class BarsWriteXMLUtilsClient {
	public static void main(String[] args){
		
		BarsWriteXMLUtilsInterface x = new BarsWriteXmlUtils();
		
		Document doc = x.createXMLDocument();
		Element rootElement = x.createDocumentElement(doc, "company");
		Element staffElement = x.createChildElement(doc, rootElement, "staff");
		x.createElementAttribute(doc, staffElement,"id","1");
		x.createElementTextNode(doc,staffElement, "firstname", "anton");
		x.transformToXML(doc, "c:\\ErrBUg\\file111.xml");
	}
}
