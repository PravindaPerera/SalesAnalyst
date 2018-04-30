# SalesAnalyst
A Web based tool designed to analyze sales related information for a given business operation.

### Directory Structure

##### `src/main/jaba/com.salesAnalyst.version1.SalesAnalyst/controllers`
All files which are responsible of accepting each request and 
directing it to the respective service layer directories 
and returning control back to the view layer.

##### `src/main/jaba/com.salesAnalyst.version1.SalesAnalyst/entities`
All domain model object files.

##### `src/main/jaba/com.salesAnalyst.version1.SalesAnalyst/repositories`
Implementations for CRUD operations

##### `src/main/jaba/resources`
View layer

### Installation Guide
1. clone the repository 
[Creator](https://github.com/PravindaPerera/SalesAnalyst)
2. set up maven in your local machine
3. execute the SalesAnalystApplication or run `./mvnw spring-boot:run` or 
build the JAR file using `./mvnw clean package` and run `java -jar target/gs-accessing-data-mysql-0.1.0.jar`

### Contributor
- Pravinda Perera