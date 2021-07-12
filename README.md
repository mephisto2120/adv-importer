# adv-importer

* This service is responsible for importing data from csv via REST API.
* Required format is: 
  Datasource,Campaign,Daily,Clicks,Impressions
  Special Ads,Waterfall Touristik,25/12/21,7,22425

* In order to query data please use swagger UI for doing it in convenient way:
  http://advimporter-env.eba-mu3hvwsc.us-east-2.elasticbeanstalk.com/swagger-ui.html#
  
* Tech stack: Java 8, Hibernate, Maria DB, Spring Boot, Swagger, Docker

* CI/CD: travis is responsible for unit tests and deploying app to dockerhub and AWS Elastic Beanstalk:
  https://www.travis-ci.com/github/mephisto2120/adv-importer