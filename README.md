Spring Boot — Developing First Microservice
In this article, we will develop our first microservice based on the spring-boot framework and the fundamentals of microservices architecture. This is exercise-driven and segregated into four areas — Design, Development, Test, and Deploy.

Photo by Alexander Dummer on Unsplash
As the topic is very wide, I do not intend to distract you with the details. Rather, I am covering the bare minimum on this topic here. Just good enough for you to start on the journey of Spring Boot Microservices. This is an exercise-driven article and I have segregated them into four areas. Each of these areas is mapped to the phases of the development lifecycle, which we all are accustomed to.
Designing First MicroService — We will conceptualize and define our first Microservice here based on the typical characteristics of Microservices and Spring Boot.
Developing First MicroService — We will develop the first Microservice in this part.
Testing First MicroService — We will test our first Microservice here with the integration tests based on Spring Boot.
Deploying First MicroService — We will deploy our first Microservice in this part and briefly understand advanced options.
Designing First MicroService
We will follow three simple steps to chalk out our microservice definition.
Aligning Microservice Principles
Defining Business Context
Defining Technology Context
Aligning Microservice Principles
The fundamental goals for a Microservice are simple -
To be Independently developed
To be Independently tested
To be Independently deployed
There are many theories to make it possible but we will focus on just the two fundamental principles -
Single responsibility principle
Common closure principle
Modeling Microservices
In this article, I am going to cover three important principles we must keep in mind while modeling microservices.
medium.com

Let’s assume we have an e-commerce store and one of the primary business functions is — “product catalog management”. We can define a service that can focus on implementing the related operations like create, update, delete, and read product definitions. All these functions are helping out to offer one business responsibility which is “product management”. We are not worried about how other business functions are implemented. Following the “single responsibility principle” helps in defining the scope and boundaries of the microservice.
The principle of “common closure” also helps us decide “what’s in and what’s out”. Assume that we have a class representing the Product data entity in our Product Catalog Service. If a new attribute is added to the product entity, we must update our functions (create, update, delete, read) to align with the new product definition. Keeping all the code related to these functions together, in one service, ensures we are following the common closure principle. If we implement them in separate services, we would need to update all of them with the change in product definition.
We will stick to these principles to implement our first microservice which can be independently developed, tested, and deployed.
Defining Business Context
We took the reference of the e-commerce domain in the previous section. We will continue this example and develop our first microservice called — Product Catalog Service. We will implement the functions to manage product definitions — “Create Product”, “Update Product”, “Get Product Details”, “Get Product List”, “Delete Product”.

We will keep the product definition simple having few attributes — title, description, image path, unit price. Each of the definitions will have a unique identifier “id”.
Defining Technology Context
We will be using Spring Boot, as the primary technology, to develop our service. Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can just run. In our “Product Catalog Service” all of its CRUD (create, read, update, delete) operations will be exposed as API through HTTP. The request(s) and response(s) will be exchanged through JSON format. Spring MVC will help us in building the restful service. We will be using a simple Map function to provide an in-memory storage mechanism. I have deliberately skipped using any database to ensure we stick to basics only.
Before we get our hands dirty, we need to ensure our machine has a compatible platform to run Spring Boot applications. To build the first microservice we need — JDK 11 and Maven 3.x. Install the specific JDK and maven versions, if it’s not present in your local environment.
JDK download — https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
Maven download — https://maven.apache.org/download.cgi
Our exercises are independent of any editor, so feel free to choose your favorite editor. Be it Eclipse, NetBeans, IntelliJ IDEA, VisualStudio, or something else.
Developing first Microservice
Setting Up
To generate the required structure and configuration, go to https://start.spring.io/. Stick to the default options and Spring Web as the dependency. Click on the GENERATE button to start downloading the archive. Unzip it at your preferred location. The folder structure looks similar to a typical maven based web project. Let’s peek into the core features we are getting out of the box.

If you see pom.xml you will find an entry of spring-boot-starter-web. This is the starter library helping in getting all the libraries related to the “Spring Web” module. This cuts down significant effort on the developer front to resolve and capture dependencies one by one.
As I provided the artifact name as “Product Catalog”, it generated ProductCatalogApplication.java which implements the main method.
@SpringBootApplication
public class ProductCatalogApplication {
 public static void main(String[] args) {
  SpringApplication.run(ProductCatalogApplication.class, args);
 }
}
You can see that the class is annotated with @SpringBootApplication. This is an umbrella annotation that is equivalent to a combination of @Configuration, @ComponentScan and @EnableAutoConfiguration annotations. @ComponentScan and @Configuration are the standard spring annotations to read bean definitions across the source code.
@EnableAutoConfiguration attempts to guess and configure the beans you most likely need. Auto-configuration classes are usually applied based on your classpath. For example, we have tomcat-embedded.jar on the classpath of Product Catalog Service (dependency of spring-boot-starter-web), so it will initialize TomcatServletWebServerFactory.
Another interesting part of this class is the main method. The following statement helps in starting the Spring Boot application.
SpringApplication.run(ProductCatalogApplication.class, args);
Developing Product Catalog Service
In this section, we will add a custom class — ProductCatalogService.java and implement the APIs for Product Catalog Service. Here is the code for ProductCatalogService.java .
@RestController
public class ProductCatalogService {  
    private static Map < String, Product > productCatalog = new HashMap < > (); 
    @PostMapping("/product")  
    public String addProduct(@RequestBody Product product) { 
        productCatalog.put(product.getId(), product); 
        return "product added successfully"; 
    } 
    @GetMapping("/product/{id}")  
    public Product getProductDetails(@PathVariable String id) { 
        return productCatalog.get(id); 
    }
    @GetMapping("/product")  
    public List < Product > getProductList() { 
        return new ArrayList < Product > (productCatalog.values()); 
    }
 
    @PutMapping("/product")  
    public String updateProduct(@RequestBody Product product) { 
        productCatalog.put(product.getId(), product); 
        return "product updated successfully"; 
    }
 
    @DeleteMapping("/product/{id}")  
    public String deleteProduct(@PathVariable String id) { 
        productCatalog.remove(id); 
        return "product deleted successfully"; 
    } 
}
Spring Web starter by default enables Spring MVC to develop RESTFul services. I have used the following Spring MVC annotations —
RestController — Spring MVC annotation to tag a class as restful service
PostMapping, GetMapping, PutMapping, DeleteMapping are the Spring MVC annotations to represent REST operations — POST, GET, PUT, and DELETE.
We are using basic storage based on HashMap to store and read the product definitions.
private static Map<String,Product> productCatalog = new HashMap<>();
In the code above, we are referring to another class, implemented through Product.java, which represents the data entity for products.
public class Product { 
    private String id; 
    private String title; 
    private String desc; 
    private String imagePath; 
    private double unitPrice;
}
Running and Accessing Service
Now we are ready to run our first Microservice based on Spring Boot. Run the following maven command and access the restful service at http://localhost:8080.
mvn spring-boot:run
This command has started the embedded tomcat server at port 8080 and deployed our web-based service (Application) on top of it. All the magic has happened behind the scene. The embedded web server makes it possible to deploy our web-based services anywhere.
It’s time that you create some sample product definitions and play around with the service APIs. You can use API tools like Postman to create and execute the HTTP requests. Here are the illustrative inputs for our service APIs.
Create Product API
Endpoint: http://localhost:8080/product (POST)
Request Body:
{
  “id”: “test-product-1”,
  “title”: “test product 1”,
  “desc”: “test product 1”,
  “imagePath”: “gc://image-path”,
  “unitPrice”: 10.0
 }
Get Product API
Endpoint: http://localhost:8080/product/test-product-1 (GET)
Get Product List API
Endpoint: http://localhost:8080/product (GET)
Update Product API
Endpoint: http://localhost:8080/product (PUT)
{
 “id”: “test-product-1”,
 “title”: “test product 1 updated”,
 “desc”: “test product 1 updated”,
 “imagePath”: “gc://image-path”,
 “unitPrice”: 10.0
 }
Delete Product API
Endpoint: http://localhost:8080/product/test-product-id (DELETE)
Testing First MicroService
Overview
In this section, we will test our first microservice with the tools provided by Spring Boot. We will build the integration tests to test our service APIs, end to end. With Spring Boot we can perform the integration tests without deploying the service to any external infrastructure. This gives immense power to validate the end to end functionality without worrying about the deployment platforms.
If you look at the service’s pom.xml you will find “spring-boot-starter-test” as one of the dependencies. This package automatically gets the well-adopted utility libraries including — Junit, Mockito, Spring-test, Assertj, Hamcrest, JSONassert & JsonPath. We will be using some of these APIs to enable integration tests for us.
Developing Tests
Here is the test class — ProductCatalogApplicationTests.java which carries our sample test cases. Each of the test cases maps to the APIs implemented in the Product Catalog Service.

We are using @SpringBootTest annotation, which does many things behind the scenes. It initializes the context loader and automatically searches for the Spring Boot configuration. It loads a web ApplicationContext and provides a mock web environment. This annotation also provides advanced options to initialize other web environments including the real-time web server.
As we are interested in testing our web endpoints, we are also using @AutoConfigureMockMvc . This enables us to do mock-based testing of our restful service. In our case, we are using Spring MVC to implement our restful APIs. @AutoConfigureMockMvc automatically configures the MVC-based application context. If you have only Spring WebFlux, it will detect that and configure a WebFlux-based application context instead.
We also used optional @TestMethodOrder and @Order annotations to ensure APIs are tested in order. We can test the “get product details” API only when an order is created through the “create product” API.
With the sample tests, we are able to validate
Create Product API (API returns a successful response)
Get Product API (API returns a successful response, API returns correct product definition )
Get Product List API (API returns a successful response, API returns the correct list of product definitions )
Update Product API (API returns a successful response)
Delete Product API (API returns a successful response)
Running these tests is as easy as running a Maven or Gradle command. In our example, we are using Maven as our build tool. We can run these integration tests with the following command.
mvn test
As the tests can be run through the build tool(mvn), we can easily include them in our build process and speed up our deployments.
SpringBoot provides multiple options to enrich the testing experience. This includes providing “multiple web environments”, “mocking” and “auto-configuration” for JSON, Spring MVC, Spring Data, etc.
Deploying first microservice
Earlier we did a local run of our service with the maven command (mvn spring-boot:run). This approach is useful for development purposes only. Production environments do not have the build or development tools. To run our service in such an environment, we need to follow a different approach.
If you use the Maven build (mvn install) command, this will generate an executable jar file. This jar includes the embedded web server as well as all the other dependencies, required to run the service. You can run our microservice without any build tool, setup, or web server. You can directly execute the following command.
java -jar target/product_catalog-0.0.1.jar
With the help of this command, you can run the service in any environment, be it a Physical Machine, Virtual Machine, Docker Container, or the Cloud Platform. The most popular deployment platforms for Spring Boot based services include Docker, Kubernetes, and Cloud platforms.
Spring Boot also provides options to “production ready” features, such as health, auditing, and metric REST or JMX end-points, based on the spring-boot-actuator module.
Summary
I captured the bare minimum to develop, test, and deploy our very first Microservice. To explore more on Spring Boot Microservices, check out my exercise-driven series
