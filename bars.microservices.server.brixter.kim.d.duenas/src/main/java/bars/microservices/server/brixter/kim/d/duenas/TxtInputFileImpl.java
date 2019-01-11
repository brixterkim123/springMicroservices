package bars.microservices.server.brixter.kim.d.duenas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TxtInputFileImpl {
	protected static final Logger logger = LoggerFactory.getLogger(TxtInputFileImpl.class);

	String file = "";

	public TxtInputFileImpl(String urlFile) {
		this.file = urlFile;
	}

	public List<Request> readFile(String urlFile) throws BarsException, NumberFormatException, IOException, ParseException{

		String line = "";
		CSVInputFileImpl.countError = 0;
		
		List<Request> listReq = new ArrayList<Request>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\Users\\tr_lnd_user\\Documents\\ServerAngular\\bars.microservices.server.brixter.kim.d.duenas\\upload-dir\\"
							+ file));
		}catch(FileNotFoundException e){
			br.close();
			throw new BarsException(BarsException.PATH_DOES_NOT_EXIST);
		}
		int txtCountError = 0;
	while ((line = br.readLine()) != null) {
		txtCountError++;
		String bc = "";
		String sd = "";
		String ed = "";

		for (int i = 0; i < line.length(); i++) {

			if (i <= 1) {
				bc += line.charAt(i);
			} else if (i >= 2 && i <= 9) {
				sd += line.charAt(i);
			} else if (i >= 10 && i <= 17) {
				ed += line.charAt(i);
			}

		}

		String convertedSD = sd.substring(0, 2) + "/" + sd.substring(2, 4) + "/" + sd.substring(4, 8);
		String convertedED = ed.substring(0, 2) + "/" + ed.substring(2, 4) + "/" + ed.substring(4, 8);
		logger.info(Integer.parseInt(bc) + ", " + convertedSD + ", " + convertedED);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		int requestBillingCycle = Integer.parseInt(bc);
		if(requestBillingCycle > 12) {
			br.close();
				CSVInputFileImpl.countError= txtCountError;
			throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + CSVInputFileImpl.countError);
		}
		try {
			Date requestStartDate = simpleDateFormat.parse(convertedSD);
			try {
				
				
				Date requestEndDate = simpleDateFormat.parse(convertedED);
				java.sql.Date sqlStartDate = new java.sql.Date(requestStartDate.getTime());
				java.sql.Date sqlEndDate = new java.sql.Date(requestEndDate.getTime());

				Request req = new Request(requestBillingCycle, sqlStartDate, sqlEndDate);
				listReq.add(req);
				
			}catch(ParseException e){
				br.close();
				CSVInputFileImpl.countError= txtCountError;
				throw new BarsException(BarsException.INVALID_END_DATE_FORMAT );
			}
		}catch(ParseException e){
			br.close();
			CSVInputFileImpl.countError= txtCountError;
			throw new BarsException(BarsException.INVALID_START_DATE_FORMAT );
		}
		
	}
	
	
		
		br.close();
		if (listReq.size() == 0) {
			throw new BarsException(BarsException.NO_RECORDS_TO_READ);
		}
		return listReq;
	}
}
