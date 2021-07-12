# adv-importer

* This service is responsible for importing data from csv via REST API.
* A required format is: 
  Datasource,Campaign,Daily,Clicks,Impressions
  Special Ads,Waterfall Touristik,25/12/21,7,22425

* In order to query data please use swagger UI for doing it in convenient way:
  http://advimporter-env.eba-mu3hvwsc.us-east-2.elasticbeanstalk.com/swagger-ui.html#
  
* Tech stack: Java 8, Hibernate, Maria DB, Spring Boot, Swagger, Docker, maven

* CI/CD: travis is responsible for unit tests and deploying app to dockerhub and AWS Elastic Beanstalk:
  https://www.travis-ci.com/github/mephisto2120/adv-importer
* In order to execute integration tests db instance is required. It can be created by invoking script:  
  src/main/resources/import.sql. Eventually application will create tables it when db exists. 