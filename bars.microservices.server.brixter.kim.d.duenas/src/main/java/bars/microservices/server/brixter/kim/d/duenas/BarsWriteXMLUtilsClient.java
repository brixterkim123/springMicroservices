package bars.microservices.server.brixter.kim.d.duenas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.accenture.sef.xml.impl.BarsWriteXmlUtils;
import com.accenture.sef.xml.interfce.BarsWriteXMLUtilsInterface;


public class BarsWriteXMLUtilsClient {


	protected static final Logger logger = LoggerFactory.getLogger(BillingController.class);
	public void writeXml(List<Record> record) {
	
		
		BarsWriteXMLUtilsInterface x = new BarsWriteXmlUtils();
		Document doc = x.createXMLDocument();
		Element rootElement = x.createDocumentElement(doc, "schema");
		
		logger.info("WRITE XCML");
		for(Record xmlRecord : record) {

			logger.info("INSIDE XML");
			
			Element staffElement = x.createChildElement(doc, rootElement, "Request");
			x.createElementTextNode(doc,staffElement, "BillingCycle", xmlRecord.getBillingCycle()+"");
			x.createElementTextNode(doc,staffElement, "StartDate", xmlRecord.getStartDate()+"");
			x.createElementTextNode(doc,staffElement, "EndDate", xmlRecord.getEndDate()+"");
			x.createElementTextNode(doc,staffElement, "LastName", xmlRecord.getCustomerFirstName());
			x.createElementTextNode(doc,staffElement, "FirstName", xmlRecord.getCustomerLastName());
			x.createElementTextNode(doc,staffElement, "Amount", xmlRecord.getAmount()+"");
		}
		Date date = new Date();
        String strDateFormat = "MMddyyyy_hhmmss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formmatedDate = dateFormat.format(date);
        System.out.println(formmatedDate);
		
		x.transformToXML(doc, "C:\\BARS\\Report\\BARS_Report-"+formmatedDate+".xml");
		
}}
