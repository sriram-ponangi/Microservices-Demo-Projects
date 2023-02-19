# Spring Batch Processing

### Spring Batch Architecture
![Spring Batch Architecture](notes/1%20Spring%20Batch%20Architecture.png)

The two main components of spring batch:
* **Jobs**
  * A job represents the entire batch process we want to execute.
  * A job executes one or more **_steps_** in an order commonly known as a flow.
  * The flow from one step to another can be dynamic, that is the step execution can be: _**Conditional**_ or _**Parallel**_
  * A job is triggered/started by a **JobLauncher**, which may pass-in additional info as **JobParameters**.
  * As the job runs the metadata regarding the job is written to the **job-repository**.
  
* **Steps**
  * A step is a phase in a batch-job that defines how the actual processing will occur for that portion of the job.
  * Typically, the processing logic within a step has 3 main parts:
    * **Read** the data from some datasource.
    * **Transform/process** the data.
    * **Write** the data to some datasource.
  * There are two types of steps used in the spring batch job:
    * **Tasklet-based Step** 
      * Contains a single execute method. Which run over and over until a signal to stop is generated.
    * **Chunk-base Step**
      * Used in the cases when we want to processed data from a datasource.
      * Uses the **_ItemReader_** & **_ItermWriter_** interfaces to process chunks of data.
      * Optionally, **_ItemProcessor_** interface can also be included to perform transformations on the data.

### State / Job Repository  Management
* The framework stores the metadata on the status/progress of the batch job at various stages to a Job Repository DB.
* In the case of a failure/termination of the batch job, the metadata stored in job repository is used to restart the job exactly from the appropriate step / data chunk where it failed instead of starting the processing from the beginning.
* In the image below the overall job failed because the Chunk-2 of the Step-2 failed. Now when the batch job is restarted we would like to only start from the step-2 and chunk-2.  
  ![Failed Step in Job](notes/2.%20Failed%20Step%20in%20Job.png)

* ### Spring Batch Job Repository Schema
  ![Job Repository Schema](notes/3.%20Spring%20Batch%20Job%20Repository%20Schema.png)

* ### JobInstance vs JobExecution From Job Repository Schema
  * When a job launcher creates a job it will typically pass the **name** of the job and some **parameters**. Every unique combination of the name and jon parameters creates a new **JobInstance**.
    * **NOTE:** If a JobInstance is successfully executed once it is not possible to re-run that JobInstance again. Instead, a new JobInstance must be created by passing a new parameter to the job. 
  * When we execute a JobInstance a new **JobExecution** is created.
  * In some situations(like failures) we might execute the same job instance (i.e., with same name & parameters) multiple times. But each time we execute a JobInstance we get a new JobExecution.
  * Similarly, when a step associated with a job is executed it creates a new **StepExecution**. A stepExecution is associated with a jobExecution.
  ![Spring Batch Architecture](notes/4.%20Job%20Instance%20Vs%20Execution.png)

### Job Flow
* Defining how jobs move from one step to another is one of the most important parts of the batch processing.
  * #### Sequential Flow
    * This transition allows us to specify the next step to execute upon the successful completion of a previous step.
    ![Sequential Job Flow](notes/5.%20Sequential%20Job%20flow.png)
    ```
       Job myJob = jobBuilderFactory.get("MyJob")
        .start(getStep1())
        .next(getStep2())
        .next(getStep3())
        .build();
    ```
  * #### Conditional Flow:
    * Depending on the result of the previous step we can choose the next step.
        ![Conditional Job Flow](notes/6.%20Conditional%20Job%20flow.png)
      ```
        Job myJob = jobBuilderFactory.get("MyJob")
         .start(getStep1())
         .on("FAILED").to(getStep3())
         .from(getStep1()).on("*").to(getStep2())
         .build();
      ```
    * The on() function allows us to specify a pattern that matches the exit status of the previous step. The pattern "*" means all status apart from "Failed" exit status.

### Batch Status
* Spring batch captures two **exit statuses** for the  **_Job Execution_** and **_Step Execution_**.
* The **Batch Status** represent the overall for the job or step execution.
  * The **Exit Status** is used in the conditional job flow by the string mentioned in this inside the `on("exit status")`

    | Batch Status | Exit Status  |
    | :----------: | :----------: | 
    | ABANDONED    | COMPLETED    | 
    | COMPLETED    | EXECUTING    |
    | FAILED       | FAILED       |
    | STARTED      | STOPPED      |
    | STOPPED      | CUSTOM-STATUS|

* Batch Statuses (Enum Values) are fixed and can have only one of the values mentioned above. Exit Status (String Values) have some defaults and they can be extended with custom status strings.


