#Cortana Intelligence Suite Pixel Tracker Solution



## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Architecture](#architecture)
- [Setup Steps](#setup-steps)
   - [General](#setup-steps)
   - [Azure Storage](#storage)
   - [Azure Event Hubs](#eventhub)
   - [Azure Stream Analytics](#asa)
   - [Azure Web App](#webapp)
- [PowerBI Dashboard](#powerbi-dashboard)
   - [Local setup](#pbilocal)
   - [Publishing](#pbipublish)

## Introduction

The objective of this Guide is to demonstrate... .  Retailers can use these predictions to... . This tutorial also shows how .

The end-to-end solution is implemented in the cloud, using Microsoft Azure. The solution is composed of several Azure components, including data ingest, data storage, data movement, advanced analytics and visualization.

This deployment guide will walk you through the steps of creating a pixel tracker solution, including:

- 

## Prerequisites

The steps described later in this guide require the following prerequisites:

1.  An [Azure subscription](https://azure.microsoft.com/en-us/) with login credentials
2.  A [Microsoft Power BI](https://powerbi.microsoft.com/en-us/) account
3.  An installed copy of [Power BI Desktop](https://powerbi.microsoft.com/en-us/desktop/?gated=0&number=0)

## Architecture

Figure 1 illustrates the Azure architecture that we will create.

![Figure 1: Architecture](media/architecture.png)
Figure 1: Architecture

## Setup Steps

The following are the steps to deploy the end-to-end solution for the predictive pipelines.

### Accessing Files in the Git Repository

This tutorial will refer to files available in the Technical Deployment Guide section of the [Cortana Intelligence Pixel Tracker solution git repository](). You can download all of these files at once by clicking the "Clone or download" button on the repository.

You can download or view individual files by navigating through the repository folders. If you choose this option, be sure to download the "raw" version of each file by clicking the filename to view it, then cliking Download.

### Choose a Unique String

You will need a unique string to identify your deployment because some Azure services, e.g. Azure Storage requires a unique name for each instance across the service. We suggest you use only letters and numbers in this string and the length should not be greater than 9.
 
We suggest you use "[UI]churn[N]"  where [UI] is the user's initials,  N is a random integer that you choose and characters must be entered in in lowercase. Please open your memo file and write down "unique:[unique]" with "[unique]" replaced with your actual unique string.

### Create an Azure Resource Group

1. Log into the [Azure Management Portal](https://ms.portal.azure.com).
1. Click **Resource groups** button on upper left, and then click **+** button to add a resource group.
1. Enter your **unique string** for the resource group and choose your subscription.
1. For **Resource Group Location**, you should choose one of the following as they are the locations that offer all of the Azure services used in this guide:
  - East US 2

Please open your memo file and save the information in the form of the following table. Please replace the content in [] with its actual value.  

| **Azure Resource Group** |                     |
|------------------------|---------------------|
| resource group name    |[unique]|
| region              |[region]||

### Instruction for Finding Your Resource Group Overview

In this tutorial, all resources will be generated in the resource group you just created. You can easily access these resources from the resource group overview page, which can be accessed as follows:

1. Log into the [Azure Management Portal](https://ms.portal.azure.com).
1. Click the **Resource groups** button on the upper-left of the screen.
1. Choose the subscription your resource group resides in.
1. Search for (or directly select) your resource group in the list of resource groups.
 
Note that you may need to close the resource description page to add new resources.

In the following steps, if any entry or item is not mentioned in the instruction, please leave it as the default value.

<a name="storage"></a>
### Create an Azure Storage Account

1. Go to the [Azure Portal](https://ms.portal.azure.com) and navigate to the resource group you just created.
2. In ***Overview*** panel, click **+** to add a new resource. Enter **Storage Account** and hit "Enter" key to search.
3. Click on **Storage Account** offered by Microsoft (in the "Storage" category).
4. Click **Create** at the bottom of the description panel.
5. Enter your **unique string** for "Name".
6. Make sure the selected resource group is the one you just created. If not, choose the resource group you created for this solution.
7. Leaving the default values for all other fields, click the **Create** button at the bottom.
8. Go back to your resource group overview and wait until the storage account is deployed. To check the deployment status, refresh the page or the list of the resources in the resource group as needed.

#### Get the Primary Key for the Azure Storage Account
These are the steps to get the access key that will be used in the SQL script to load the data into Azure SQL DW:

1. Click the created storage account. In the new panel, click on **Access keys**.
1. In the new panel, click the "Click to copy" icon next to `key1`, and paste the key into your memo.

| **Azure Storage Account** |                     |
|------------------------|---------------------|
| Storage Account        |[unique string]|
| Access Key     |[key]             ||

<a name="eventhub"></a>
### Create an Azure Event Hub
1. Go to the [Azure Portal](https://ms.portal.azure.com) and navigate to your resource group.
2. In "Overview" panel, click **+ Add** to add a new resource. Type **Event Hubs** and hit "Enter" key to search.
3. Click on **Event Hubs** offered by Microsoft in the "Internet of Things" category.
4. Click **Create** at the bottom of the description panel.
5. In the new panel for creating a namespace, enter your **unique string** for "Name".
6. Leaving the default values for all other fields, click the **Create** button at the bottom of the panel.
7. Return to your resource group's overview page. When it has finished deploying, click on the resource of type "Event hubs".
9. Click the **+ Event Hub** button to add an event hub.
10. In the new panel:
    1. Enter **churn** for "Name".
    2. Enter **4** for "Partition Count".
    3. Enter **2** for "Message Retention".
    4. Click **Create** at the bottom.
11. Click on the ***Event Hubs*** option in the menu bar at left (under the "Entities" heading).
12. Click on the event hub named **churn** created through the previous steps. In the new panel:
    1. Click ***Shared access policies*** in the left-hand menu bar (under the ***SETTINGS*** heading).
    2. In the new panel,  click **+ Add** to add a new policy.
    3. In the new panel:
        1. Enter **sendreceive** for the "Policy name".
        2. Check **Send** and **Listen**.
        3. Click **Create** at the bottom of the panel.
        4. Wait until the new policy is created and shown in the listing of "Shared access policies".
        5. Click the policy **sendreceive**, and save the "PRIMARY KEY" to the memo table given below.

| **Azure Event Hub** |                        |
|---------------------|------------------------|
| EventHubServiceNamespace | [unique string]  |
| Event Hub           |  churn           |
| EventHubServicePolicy  |     sendreceive                 |
| EventHubServiceKey       |  [PrimaryKey]     ||

<a name="asa"></a>
### Create an Azure Stream Analytics Job
1. Go to the [Azure Portal](https://ms.portal.azure.com) and navigate to your resource group.
2. In ***Overview*** panel, click **+ Add** to add a new resource. Enter **Stream Analytics job** and hit "Enter" key to search.
3. Click on **Stream Analytics job** offered by Microsoft in the "Internet of Things" category.
4. Click **Create** at the bottom of the description panel.
5. Enter your **unique string** in the "Job name" field.
6. Click **Create** at the bottom.
7. Return to your resource group and refresh the listing until your Stream Analytics job appears, indicating that deployment has finished. Click on the Stream Analytics job's name.
9. In the Stream Analytics job overview panel, click **Inputs**.
10. In the new panel, click **+ Add** to add an input. In the "New input" panel:
    1. Enter  **datagen** for the "Input alias".
    2. Choose **Data Stream** for the "Source type".
    3. Choose **Event hub** for the "Source".
    5. Choose your **unique string** for the "Service bus namespace".
    6. Choose **churn** for the "Event hub name".
    7. Choose **sendreceive** as the "Event hub policy name".
    8. Leaving other fields on their default values, click the **Create** button at the bottom.
10. Return to the Stream Analytics job overview panel and click **Outputs**.
10. In the new panel, click **+ Add** to add an output. In the "New output" panel:
    1. Enter **sqldw** for the "Output alias".
    2. Choose **SQL database** for the "Sink".
    4. Choose your **unique string** for the "Database".
    5. Enter your username and password (recorded in the Azure SQL Data Warehouse memo table) for the database.
    6. Enter **Activities** for the "Table".
    7. Click the **Create** button at the bottom.
11. Return to the Stream Analytics job overview panel and click **Query**.
11. In the new panel, click **Query** and click **+** in the new panel. Remove the default query content and enter:

    ```
    SELECT
    *
    INTO
        adls
    FROM datagen;
    ```
    
    Click the **Save** icon to save the query.
    **[Note]: The input and output aliases are used in the query, and the selected column names must exactly match those in the Activities table.**
12. Return to the overview of the Stream Analytics job and click the "Start" button.
13. In the new panel, choose "Now" for the "Job output start time".
14. Click the **Start** button at the bottom.  

<a name="webapp"></a>
### Set up Azure Web Job/Data Generator

The data generator emits one day's transaction data every 15 minutes to reduce the wait time for viewing results in this demo.

1. Go to the [Azure Portal](https://ms.portal.azure.com) and navigate to your resource group.
2. In ***Overview*** panel, click **+ Add** to add a new resource. Enter **Web App** and hit "Enter" key to search.
3. In the result list, click on **Web App** offered by Microsoft in the "Web + Mobile" category.
4. Click the **Create** button at the bottom of the description panel.
5. In the new panel:
    1. Enter your **unique string** for the "App name".
    3. Click **App Service Plan/Location**. In the new **App Service Plan** panel:
        1. Click **Create New** and enter your unique string for the **App Service Plan**.
        2. Choose the region where your resource group resides.
        3. Click **OK** at the bottom of the panel.
6. Click the **Create** button at the bottom.
7. Return to the resource group overview. Refresh the resource listing until the app service deployment completes (usually takes around two minutes).
8. Click on the App Service resource whose type is "App Service" (not "App Service Plan") in your resource group to load its overview panel.
8. In the left-side panel, search for "Application Settings" and click on the **Application Settings** result. In the new panel:
    1. Choose **2.7** for the Python version.
    2. Choose **64-bit** for the Platform.
    3. Toggle **On** for the "Always on" setting.
    4. In the **App settings** section, add the following key-value pairs (using values recorded in your Azure Event Hub memo table) and leave the default entry as it is:

      | **Azure App Service Settings** |        | 
      |------------------------|---------------------|
      | EventHubServiceNamespace |[unique string]          |
      | EventHub              |churn         |
      | EventHubServicePolicy              |sendreceive         |
      |    EventHubServiceKey           |[unique string]            ||

  Note that the value for "EventHubServiceNamespace" is the **unique string** only, without the extension "servicebus.windows.net." Click **Save** and return to the App Service overview panel.

9. On the side panel, search for "WebJobs" and click on the **WebJobs** result.
10. Click on the **+ Add** button to add a WebJob. In the new panel:
    1. Enter **eventhub15min** for the "Name".
    2. Select the [eventhub_15min.zip](resource/eventhub_15min.zip) file (available in the `resource` folder of the [git repository](https://github.com/Azure/cortana-intelligence-churn-prediction-solution/tree/master/Technical%20Deployment%20Guide)) for "File Upload".
    3. Choose **Triggered** for Type.
    4. Choose the **Manual** setting from the "Triggers" drop-down menu.  (Note: the uploaded zip file contains a scheduled settings file, so we do not need to specify the schedule settings when submitting the WebJob.)
    5. Click the **OK** button at the bottom.
11. If necessary, refresh the listing until the new WebJob appears.
12. Select the job "eventhub15min" and click the **Run** button. Wait until the job finishes. To check the job's status, click the **Refresh** button until the status changes to "Completed".

<a name="pbilocal"></a>
## PowerBI Dashboard

Power BI is used to create visualizations for monitoring sales and predictions. It can also be used to help detect trends in important factors for predicting churn. The instructions that follow describe how you can use the provided Power BI desktop file (Customer-Churn-Report.pbix) to visualize your data. 

1. If you have not already done so, download and install the [Power BI Desktop application](https://powerbi.microsoft.com/en-us/desktop).
1. Download the Power BI template file `Customer-Churn-Report.pbix` (available in the `Power BI` folder of the [git repository](https://github.com/Azure/cortana-intelligence-churn-prediction-solution/tree/master/Technical%20Deployment%20Guide)) by left-clicking on the file and clicking on "Download" on the page that follows.
1. Double click the downloaded ".pbix" file to open it in Power BI Desktop.
1. The template file connects to a database used in development. You'll need to change some parameters so that it links to your own database. To do this, follow these steps:
    1. Click on "Edit Queries" as shown in the following figure.
       
        [![Figure 1][pic 1]][pic 1] 

    1. Select a Query from the Queries panel (e.g., Age) and click on "Advanced Editors" as shown in the following figure.

        [![Figure 2][pic 2]][pic 2] 

    1. In the pop-up window for Advanced Editor, replace all "dbchurn" values with your "unique string" (database and server name). This process is shown in the following two figures, which assumes that the unique string is "mydb". (You should use the name of your own database.) Click "Done" after making the changes.

        [![Figure 3][pic 3]][pic 3] 
        [![Figure 4][pic 4]][pic 4] 

    1. With the same Query (e.g., Age) selected, click on "Edit Credentials" and enter your your credentials for accessing your database (recorded in the SQL Data Warehouse memo table). Click on "Connect" as shown in the following figure.

        [![Figure 5][pic 5]][pic 5] 

    1. The data for your table should be displayed if the connection information was correct, as in the following figure.

        [![Figure 6][pic 6]][pic 6]

    1. Update the other Queries by replacing "dbchurn" with the name of your database. 
    1. Click on the "Close & Apply" ribbon after all Queries have been updated.
    
You should now see multiple tabs in Power BI Desktop's report page. The "MyDashboard" tab combines the content from the "Activities" and "Predictions" tabs. The "Features" tab displays the important variables for predicting churn: days between transactions, region, and number of transactions.

<a name="pbipublish"></a>
Now we can publish the report into Power BI online to easily share with others: 
 
1. Click on "Publish" as shown below. Sign in with your Power BI credentials and choose a destination (e.g., My Workspace). 
[![Figure 7][pic 7]][pic 7]

1. After the report is successfully published, you should see a window like the following. Click on "Got it."
[![Figure 8][pic 8]][pic 8]

1. Sign into [Power BI](www.powerbi.microsoft.com) and click on the "Customer-Churn-Report" report (under Reports) to open it. 
1. We'll share the Churn Rate Overview tab from the report to create a dashboard. To do this, click on the "MyDashboard" tab and select "Pin Live Page" as shown in the following figure. 
[![Figure 9][pic 9]][pic 9]

1. Pin the page to a new dashboard named "Customer Churn Dashboard" as shown in the following figure.
[![Figure 10][pic 10]][pic 10]

1. Pin the remaining 3 tabs using the same approach: Churn Rate Drill Down, Sales Overview, and Machine Learning. 
1. Locate the newly created "Customer Churn Dashboard" under Dashboards group. You can share it with others by clicking on the Share button, as shown in the following figure.
[![Figure 11][pic 11]][pic 11]

Below is an overview of the different reports:

1. The Churn Rate Overview report shows the number of customers in 3 churn risk groups: low risk (no more than 30% churn rate), moderate risk (30% 50% churn rate) and high risk (more than 30% churn rate) on each prediction date. 

1. The Churn Rate Drill Down report shows that two regions Northeast, and Mountain-Prairie have a high percentage of high risk users. Marketing managers in these regions can thus be notified to take churn prevention actions. 

1. The Sales Overview report provides sales information by different dimensions: date, region, and age.

1. The Machine Learning report demonstrates how Azure Machine Learning can help users identify important variables. 

In this dashboard, we used cutoff points 0.3 and 0.5 to generate 3 churn risk groups: low risk, moderate risk, and high risk. These cutoff points can be modified when necessary. The following screen shot shows how this can be done. 

1. Click on the bar chart.
 
1. Click on the Churn Risk variable in the Prediction table.

1. Change the cutoff points and labels.

1. Click on the check mark to confirm the changes.

[![Figure a][pic a]][pic a]
