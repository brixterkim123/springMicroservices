package bars.microservices.server.brixter.kim.d.duenas;

public class BarsException extends Exception{
	private static final long serialVersionUID = 895610352435609967L;
	public static final String INVALID_START_DATE_FORMAT = "ERROR: Invalid Start Date format at Row Number ";
	public static final String INVALID_END_DATE_FORMAT = "ERROR: Invalid End Date format at RowNumber ";
	public static final String BILLING_CYCLE_NOT_ON_RANGE = "ERROR: Billing Cycle not on range at RowNumber ";
	public static final String PATH_DOES_NOT_EXIST = "Please input an existing request file path";
	public static final String NO_SUPPORTED_FILE = "No supported request file found in the file path.";
	public static final String NO_RECORDS_TO_READ = "No request(s) to read from input file.";
	public static final String NO_RECORDS_TO_WRITE = "No record(s) to write to the output file";

	public BarsException(String message) {

		super(message);
	}
	
	public BarsException(String message, Throwable cause) {
		
		super(message,cause);
	}
	
}

