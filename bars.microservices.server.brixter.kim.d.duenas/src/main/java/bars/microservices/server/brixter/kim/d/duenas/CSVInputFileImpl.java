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

public class CSVInputFileImpl {
	protected static final Logger logger = LoggerFactory.getLogger(CSVInputFileImpl.class);
	public static int countError = 0;

	String file = "";

	public CSVInputFileImpl(String urlFile) {
		this.file = urlFile;
	}

	@SuppressWarnings({ "null" })
	public List<Request> readFile(String urlFile) throws BarsException, NumberFormatException, IOException {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = " ";

		List<Request> listReq = new ArrayList<Request>();

		try {
			br = new BufferedReader(new FileReader(
					"C:\\Users\\tr_lnd_user\\Documents\\ServerAngular\\bars.microservices.server.brixter.kim.d.duenas\\upload-dir\\"
							+ file));
		} catch (FileNotFoundException e1) {
			br.close();
			throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE);
		}

		logger.info(listReq.size() + " SIZE OF CSV FILEEE 1st");
		while ((line = br.readLine()) != null) {

			logger.info(listReq.size() + " SIZE OF CSV FILEEE 2nd");
			countError++;
			String[] numbers = line.split(cvsSplitBy);

			String pattern = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			int requestBillingCycle = 0;
			try {
				requestBillingCycle = Integer.parseInt(numbers[0]);

			} catch (Exception e) {
				br.close();
				logger.info("ERROR IN READ");
				throw new BarsException(BarsException.NO_RECORDS_TO_READ);
			}
			
			if (requestBillingCycle > 12) {
				br.close();
				throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + countError);
			}
			try {
				Date requestStartDate = simpleDateFormat.parse(numbers[1]);

				try {
					Date requestEndDate = simpleDateFormat.parse(numbers[2]);
					java.sql.Date sqlEndDate = new java.sql.Date(requestEndDate.getTime());
					java.sql.Date sqlStartDate = new java.sql.Date(requestStartDate.getTime());

					logger.info(sqlStartDate + " start");
					logger.info(sqlEndDate + " end");

					Request req = new Request(requestBillingCycle, sqlStartDate, sqlEndDate);

					listReq.add(req);
				} catch (ParseException e) {
					br.close();
					throw new BarsException(BarsException.INVALID_END_DATE_FORMAT + countError);
				}

			} catch (ParseException e) {
				br.close();
				throw new BarsException(BarsException.INVALID_START_DATE_FORMAT + countError);
			}

		}

		br.close();
		logger.info(listReq.size() + " SIZE OF CSV FILEEE");
		if (listReq.size() == 0) {
			throw new BarsException(BarsException.NO_RECORDS_TO_READ);
		}
		return listReq;
	}

}
