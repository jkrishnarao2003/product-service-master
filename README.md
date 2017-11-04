### Product Service
Provides product catalog. 

Primary focus is to provide a micro service which will get all the products. 

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a Cloud PLatform like IBM Bluemix.

### Prerequisites

What you will need: 

* JDK 1.8 or later
* Maven 3.0+
* Spring Boot 1.3.6
* Spring Tool Suite or any other IDE
* In Memory Db (h2)
* Cloud Foundry CLI

### Installing

* Download and unzip the source repository or clone it using Git: 

  https://github.com/prokarma-technologylab/product-service.git


* cd into product-service-master

* Build the JAR file with 

  mvn clean package
  
* Executing on local

 The procedure above will create a runnable JAR. You can run the JAR file by:

 java -jar target/product-service-1.0.jar

 * Validate Test Results

   mvn test 
   
   Navigate to /target/site/jacoco open index.html to view all the test reports.
   



### Deploying to Bluemix

* Change to the directory containing pom.xml 

  cd product-service-master

* connect to Bluemix using 

  [Directory containing cloud foundry CLI]/cf api https://api.ng.bluemix.net

* Login to Bluemix 

  [Directory containing cloud foundry CLI]/cf login -u [username@org.com] -o [organization name] -s [SPACE]

* Deploy to Bluemix using

  cf push -f manifest.yml
  
### Validation

http:// your domain name /products  This will return all the products


 





  
