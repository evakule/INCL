package com.incl.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/load")
public final class BatchController {
  private static final String TIME = "Time";
  private static final String LOAD_COUNTRIES_URI = "/countries";
  
  @Autowired
  private JobLauncher jobLauncher;
  
  @Autowired
  private Job job;
  
  @GetMapping(LOAD_COUNTRIES_URI)
  public BatchStatus load() throws
      JobParametersInvalidException,
      JobExecutionAlreadyRunningException,
      JobRestartException,
      JobInstanceAlreadyCompleteException {
    
    Map<String, JobParameter> maps = new HashMap<>();
    maps.put(TIME, new JobParameter(System.currentTimeMillis()));
    JobParameters parameters = new JobParameters(maps);
    JobExecution jobExecution = jobLauncher.run(job, parameters); // Launch the rocket
    return jobExecution.getStatus();
  }
}
