package bars.microservices.server.brixter.kim.d.duenas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private int accountId;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="is_active")
	private char isActive;
	
	@Column(name="last_edited")
	private String lastEdited;

	@Column(name="customer_id")
	private int customerId;
	
	public Account() {
		
	}
	
	public Account(String accountName, Date dateCreated, char isActive, String lastEdited, int customerId) {
		super();
		this.accountName = accountName;
		this.dateCreated = dateCreated;
		this.isActive = isActive;
		this.lastEdited = lastEdited;
		this.customerId = customerId;
	}

	

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public String getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	
}
