package bars.microservices.server.brixter.kim.d.duenas;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BillingController {

	protected static final Logger logger = LoggerFactory.getLogger(BillingController.class);

	private List<Record> angularRecord = null;
	@Autowired
	private BillingRepository billingRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	StorageService storageService;

	public BillingController(BillingRepository billingRepository, AccountRepository accountRepository,
			CustomerRepository customerRepository, RequestRepository requestRepository, StorageService storageService) {
		super();
		this.billingRepository = billingRepository;
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
		this.storageService = storageService;
	}

	@GetMapping("/all")
	public List<Billing> getAll() {

		List<Billing> listOfRecord = billingRepository.findAll();
		return listOfRecord;

	}

	@GetMapping("/getRequest")
	public List<Record> getRequest() {

		return angularRecord;
	}

	List<String> files = new ArrayList<String>();

	@PostMapping("/post")
	public List<Record> handleFileUpload(@RequestParam("file") MultipartFile file) throws BarsException, IOException {
 
		List<Record> newOfRecord = new ArrayList<>();
		storageService.store(file);
		files.add(file.getOriginalFilename());
		logger.info(file.getOriginalFilename() + " FILE NAME");
		String csvFile = file.getOriginalFilename().toString().toLowerCase();

		int counter = 0;
		try {
			
			if (csvFile.endsWith(".csv")) {
				logger.info(csvFile + " file");
				CSVInputFileImpl csv = new CSVInputFileImpl(csvFile);
				List<Request> request = csv.readFile(csvFile);

				List<Request> listOfRequest = request;
				List<Billing> listOfBilling = billingRepository.findAll();
				List<Record> listOfRecord = new ArrayList<>();

				for (int a = 0; a < listOfRequest.size(); a++) {
					counter++;
					Request requestObject = listOfRequest.get(a);

					Date startDateS = listOfRequest.get(a).getStartDate();
					Date endDateS = listOfRequest.get(a).getEndDate();
					for (int i = 0; i < listOfBilling.size(); i++) {

						int billingCycleId = requestObject.getBillingCycle();

						if (billingCycleId == listOfBilling.get(i).getBillingCycle()
								&& startDateS.equals(listOfBilling.get(i).getStartDate())
								&& endDateS.equals(listOfBilling.get(i).getEndDate())) {

							int accountId = listOfBilling.get(i).getAccountId();

							List<Account> listOfAccount = accountRepository.findByAccountId(accountId);

							int billingCycleInIF = listOfBilling.get(i).getBillingCycle();

							Date startDate = listOfBilling.get(i).getStartDate();
							Date endDate = listOfBilling.get(i).getEndDate();

							String accountname = listOfAccount.get(0).getAccountName();

							List<Customer> listOfCustomer = customerRepository
									.findByCustomerId(listOfAccount.get(0).getCustomerId());

							String custFirstName = listOfCustomer.get(0).getFirstName();
							String custLastName = listOfCustomer.get(0).getLastName();
							double billingAmount = listOfBilling.get(i).getBillingAmount();

							listOfRecord.add(new Record(billingCycleInIF, startDate, endDate, accountname,
									custFirstName, custLastName, billingAmount));

						} else {

						}
					}
				}
				if (listOfRecord.size() == 0) {

					throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
				}

				angularRecord = listOfRecord;
				BarsWriteXMLUtilsClient newXML = new BarsWriteXMLUtilsClient();
				newXML.writeXml(listOfRecord);
				counter=0;
				return listOfRecord;

			} else if (csvFile.endsWith(".txt")) {

				logger.info(csvFile + " TEXT FILE NAME");
				TxtInputFileImpl txt = new TxtInputFileImpl(csvFile);
				List<Request> request = txt.readFile(csvFile);

				List<Request> listOfRequest = request;
				List<Billing> listOfBilling = billingRepository.findAll();
				List<Record> listOfRecord = new ArrayList<>();

				for (int a = 0; a < listOfRequest.size(); a++) {
					counter++;
					Request requestObject = listOfRequest.get(a);

					Date startDateS = listOfRequest.get(a).getStartDate();
					Date endDateS = listOfRequest.get(a).getEndDate();
					for (int i = 0; i < listOfBilling.size(); i++) {

						int billingCycleId = requestObject.getBillingCycle();

						if (billingCycleId == listOfBilling.get(i).getBillingCycle()
								&& startDateS.equals(listOfBilling.get(i).getStartDate())
								&& endDateS.equals(listOfBilling.get(i).getEndDate())) {

							int accountId = listOfBilling.get(i).getAccountId();

							List<Account> listOfAccount = accountRepository.findByAccountId(accountId);

							int billingCycleInIF = listOfBilling.get(i).getBillingCycle();

							Date startDate = listOfBilling.get(i).getStartDate();
							Date endDate = listOfBilling.get(i).getEndDate();

							String accountname = listOfAccount.get(0).getAccountName();

							List<Customer> listOfCustomer = customerRepository
									.findByCustomerId(listOfAccount.get(0).getCustomerId());

							String custFirstName = listOfCustomer.get(0).getFirstName();
							String custLastName = listOfCustomer.get(0).getLastName();
							double billingAmount = listOfBilling.get(i).getBillingAmount();

							listOfRecord.add(new Record(billingCycleInIF, startDate, endDate, accountname,
									custFirstName, custLastName, billingAmount));
						} else {

						}
					}
				}
				if (listOfRecord.size() == 0) {

					throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
				}
				
				angularRecord = listOfRecord;
				BarsWriteXMLUtilsClient newXML = new BarsWriteXMLUtilsClient();
				newXML.writeXml(listOfRecord);
				counter=0;
				return listOfRecord;

			} else {
				throw new BarsException(BarsException.NO_SUPPORTED_FILE);
			}
		} catch (Exception e) {
			if (e.getMessage().contains(BarsException.BILLING_CYCLE_NOT_ON_RANGE)) {
				newOfRecord.add(new Record(BarsException.BILLING_CYCLE_NOT_ON_RANGE + CSVInputFileImpl.countError));
				logger.error(BarsException.BILLING_CYCLE_NOT_ON_RANGE);
				angularRecord = newOfRecord;
				CSVInputFileImpl.countError = 0;
				return newOfRecord;
			} else if (e.getMessage().contains(BarsException.INVALID_END_DATE_FORMAT)) {
				logger.error(BarsException.INVALID_END_DATE_FORMAT);
				newOfRecord.add(new Record(BarsException.INVALID_END_DATE_FORMAT+ CSVInputFileImpl.countError));
				logger.error(BarsException.INVALID_END_DATE_FORMAT);
				angularRecord = newOfRecord;
				CSVInputFileImpl.countError = 0;
				return newOfRecord;
			} else if (e.getMessage().contains(BarsException.INVALID_START_DATE_FORMAT)) {
				logger.error(BarsException.INVALID_START_DATE_FORMAT);
				newOfRecord.add(new Record(BarsException.INVALID_START_DATE_FORMAT + CSVInputFileImpl.countError));
				logger.error(BarsException.INVALID_START_DATE_FORMAT);
				angularRecord = newOfRecord;
				CSVInputFileImpl.countError = 0;
				return newOfRecord;
			} else if (e.getMessage().contains(BarsException.PATH_DOES_NOT_EXIST)) {
				logger.error(BarsException.PATH_DOES_NOT_EXIST);
				newOfRecord.add(new Record(BarsException.PATH_DOES_NOT_EXIST));
				logger.error(BarsException.PATH_DOES_NOT_EXIST);
				CSVInputFileImpl.countError = 0;
				angularRecord = newOfRecord;
				return newOfRecord;
			} else if (e.getMessage().contains(BarsException.NO_SUPPORTED_FILE)) {
				newOfRecord.add(new Record(BarsException.NO_SUPPORTED_FILE));
				logger.error(BarsException.NO_SUPPORTED_FILE);
				CSVInputFileImpl.countError = 0;
				angularRecord = newOfRecord;
				return newOfRecord;
			} else if (e.getMessage().contains(BarsException.NO_RECORDS_TO_READ)) {
				logger.error(BarsException.NO_RECORDS_TO_READ);
				newOfRecord.add(new Record(BarsException.NO_RECORDS_TO_READ));
				logger.error(BarsException.NO_RECORDS_TO_READ);
				CSVInputFileImpl.countError = 0;
				angularRecord = newOfRecord;
				return newOfRecord;
			} else if (e.getMessage().contains(BarsException.NO_RECORDS_TO_WRITE)) {
				logger.error(BarsException.NO_RECORDS_TO_WRITE);
				newOfRecord.add(new Record(BarsException.NO_RECORDS_TO_WRITE));
				logger.error(BarsException.NO_RECORDS_TO_WRITE);
				angularRecord = newOfRecord;
				CSVInputFileImpl.countError = 0;
				return newOfRecord;
			}
		}

		return null;

	}

}
