import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.accenture.sef.xml.impl.BarsReadXMLUtils;
import com.accenture.sef.xml.interfce.BarsReadXMLUtilsInterface;


public class BarsReadXMLUtilsClient {
	
	public static void main(String[] argz){
		BarsReadXMLUtilsInterface brxu = new BarsReadXMLUtils();
		NodeList nl = brxu.retrieveXMLNodeList("c:\\ErrBUg\\file.xml", "Staff");

		List<Element> nodeList= brxu.retrieveNodeElement(nl);
		
		Element y;
		for(int x=0;x<nodeList.size();x++){
			y = (Element) nodeList.get(x);
			System.out.println(brxu.getElementNodeValue(y, "firstname"));
		}
	}
	
}
