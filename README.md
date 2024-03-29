# sales-management-system-api-java-spring-boot
To run the api, you must first run the `create-db.sql` in mysql.

#### Tech Stack
- Java Spring Boot
- Hibernate
- MySQL

#### Exposed API Resources
- GET /api/products => read all products
- GET /api/products/{id} => read product by id
- POST /api/products => create new product
- PUT /api/products/{id} => update existing product
- DELETE /api/products/{id} => delete product
- GET /api/clients => read all clients
- GET /api/clients/{id} => read client by id
- POST /api/clients => create new client
- PUT /api/clients/{id} => update existing client
- DELETE /api/clients/{id} => delete client
- GET /api/sales => read all sales
- GET /api/sales/{id} => read sale by id
- POST /api/sales => create new sale
- PUT /api/sales/{id} => update existing sale
- GET /api/generate-csv-report/sales-report => generate CSV file for sales summary
- GET /api/generate-csv-report/clients-report => generate CSV file for clients summary
- A Swagger UI is available at /swagger to gain clearer understanding of the request body params