
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Overview
We can use EntityGraph to formulate better-performing queries and deal with performance loading. 

In this application Status, subStatuses and address are loaded lazily. The method findAll of UserRepository return UserEntity from user table data with single query.  But when we are calling getStatus() of UserEntity it will call one more select query and again it will generate new query when we will call getSubStatuses() of StatusEntity, Similarly for getAddress it will generate +1 query. 

Here is table and data.

#### User Table:
![image](https://user-images.githubusercontent.com/26097904/193919760-0a830064-b136-4ab2-8ae5-65f3d915ca4a.gif)

#### Status Table:
![image](https://user-images.githubusercontent.com/26097904/193920262-dd5a3d72-83c9-4190-83a4-d6254a23b91c.gif)

#### SubStatus Table:
![image](https://user-images.githubusercontent.com/26097904/193920385-91f210b0-fae7-499e-a980-89d1b8303da3.gif)

#### Address Table:
![image](https://user-images.githubusercontent.com/26097904/193920485-dcddbd3c-afff-4a7f-9733-8c4e06f1ce7e.gif)

### Without EntityGraph: 

findAll() method of UserRepository:
![image](https://user-images.githubusercontent.com/26097904/193920684-d1042bb8-222f-4bbe-9725-1db5e7e8b6d0.gif)
Below is the Test Result screenshot, we can see there are 9 queries generated.
![image](https://user-images.githubusercontent.com/26097904/193920837-31f3ab8f-47a9-4b39-89ea-1ae09146ae09.gif)

> 1st query to get user list.<br /> 
> 3 status table query by 3 status Ids(1,2,3).<br /> 
> 3 SubStatus table query by substatus ids(1,2,3) and<br /> 
> 2 address table query by address ids(1,2).

So, If we have huge data then number of query will be increase.

API response time without EntityGraph: 26.0ms
![image](https://user-images.githubusercontent.com/26097904/193921182-ca908221-8c90-406a-ba4c-0ac8fc396ff8.gif)


#### This can be easily achieved by 1 query using join. We can use EntityGraph with different FetchType Strategies to achieve this.

Fetch(EntityGraph.EntityGraphType.FETCH): The attributes that are specified by attributeNodes are treated as FetchType.EAGER and the rest of the attributes are treated as FetchType.LAZY

Load(EntityGraph.EntityGraphType.LOAD): The attributes that are specified by attributeNodes are treated as FetchType.EAGER and the rest of the attributes are treated according to their specified or default FetchType

It defaults to EntityGraph.EntityGraphType.FETCH.

### With EntityGraph: 

findAll() method of UserRepository:
![image](https://user-images.githubusercontent.com/26097904/193925604-0adc3490-3513-46dc-90a7-98355a9220c8.gif)

Below is the Test Result screenshot with EntityGraph
![image](https://user-images.githubusercontent.com/26097904/193925687-cacbf8e8-2107-4047-8f8b-76e8df3b3c90.gif)
 Here we are seeing there is 1 query generated using left outer join

API response time with EntityGraph: 14.3ms
![image](https://user-images.githubusercontent.com/26097904/193925798-f3ca97ba-ff45-448b-ac03-aeb17f82f740.gif)


### How to provide graph name dynamically?

1. You can use https://github.com/Cosium/spring-data-jpa-entity-graph <br /> 
   Add in build.gradle file:
![image](https://user-images.githubusercontent.com/26097904/193926083-58e98dad-07d3-4c88-ab71-59ea8ec7beed.gif)

2. Add EnableJpaRepositories in configuration or main class 
![image](https://user-images.githubusercontent.com/26097904/193926326-8f0eadee-76ca-4618-8386-15e2723cac19.gif)


3. Extends EntityGraphJpaRepository and EntityGraphJpaSpecificationExecutor in repository interface
![image](https://user-images.githubusercontent.com/26097904/193926458-0ac114dc-e1c7-4c7d-9c53-db66fd65e625.gif)

4. Provide different graph name from service class
![image](https://user-images.githubusercontent.com/26097904/193926574-474b0f80-a445-4a95-885b-14c5b42065e5.gif)









