package bars.microservices.server.brixter.kim.d.duenas;

import java.util.Date;


//@JsonIgnoreProperties(ignoreUnknown = true, 
//value = {"amount","billingCycle"})
public class Record {

	private int customerId;
	private int accountId;
	private int billingId;
	private int billingCycle;
	private Date startDate;
	private Date endDate;
	private String accountName;
	private String customerLastName;
	private String customerFirstName;
	private Double amount;
	private String err;
	
	public Record() {
		
	}
	

	public Record(String err) {
		this.err = err;
		this.amount = 0.0;
	}
	
	public Record(int billingCycle, Date startDate, Date endDate,
			String accountName, String customerLastName, String customerFirstName, double amount) {
		super();
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accountName = accountName;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.amount = amount;
	}

	
	
	public String getErr() {
		return err;
	}
	
	public void setErr(String err) {
		this.err = err;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
}
