/*package com.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.model.Job;
import com.collaboration.service.JobService;

@RestController
public class JobController {
	
	@Autowired(required = true)
	private JobService jobService;
	
	@RequestMapping(value="/getAllJobs", method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllJobs()
	{
		List<Job> jobs=jobService.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/postJob", method=RequestMethod.POST)
	public ResponseEntity<Job> postJob(@RequestBody Job job){
		
		job.setStatus('V');
		
		if(jobService.postJob(job) == false){
			
			job.setErrorCode("404");
			job.setErrorMessage("Not able to post a job");
		}
		else
		{
			job.setErrorCode("200");
			job.setErrorMessage("Successfully posted the job");
		}
		
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	
	

}
*/