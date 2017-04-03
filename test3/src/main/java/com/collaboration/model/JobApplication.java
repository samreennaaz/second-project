/*package com.collaboration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="C_Job_Applicaton")
public class JobApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobApplicationId;
	private int userId;
	private int jobId;
	private String dateApplied;
	private char status;
	
	public int getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(int jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
}
*/