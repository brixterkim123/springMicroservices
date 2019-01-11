package bars.microservices.server.brixter.kim.d.duenas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billing")
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="billing_id")
	private int id;
	
	@Column(name="billing_cycle")
	private int billingCycle;
	
	@Column(name="billing_month")
	private String billingMonth;
	
	@Column(name="amount")
	private double billingAmount;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="last_edited")
	private String lastEdited;
	
	@Column(name="account_id")
	private int accountId;

	
	public Billing() {
		
	}
	
	public Billing(int billingCycle, String billingMonth, double billingAmount, Date startDate, Date endDate,
			String lastEdited, int accountId) {
		super();
		this.billingCycle = billingCycle;
		this.billingMonth = billingMonth;
		this.billingAmount = billingAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastEdited = lastEdited;
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}

	public double getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(int billingAmount) {
		this.billingAmount = billingAmount;
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

	public String getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
	
	
}
