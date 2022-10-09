package com.demo.suvidha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Rushi_Customer_suvidha")

public class Customer 
{

	@Id
	private String customerIdentity;
	private String customerName;
	private String customerAdd;
	private String customerEmail;
	private double annuallncome;
	private boolean incomeTaxReturnAttached;
	@JsonIgnore
	@OneToOne(mappedBy = "customer")
	private Loan loan;
	@Override
	public String toString() {
		return "Customer [customerIdentity=" + customerIdentity + ", customerName=" + customerName + ", customerAdd="
				+ customerAdd + ", customerEmail=" + customerEmail + ", annuallncome=" + annuallncome
				+ ", incomeTaxReturnAttached=" + incomeTaxReturnAttached + "]";
	}
	
	
	
}
