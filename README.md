# Database-technologies

Repository contains database modelling and setup documentation featuring several key database technologies
and JPA application core that is meant to provide the interface to work with the mentioned database.
Produced as two-person team coursework project within **CTU FEE B0B36DBS - Database Systems** university course.
The project uses PostgreSQL database and Java Persistence API basic classes for the application core.

---

## Documentation contents

Documentation (***db-modeling-and-setup-doc.pdf***) consists of several gradually submitted related parts, featuring:
- Brief description of the information system that the database is being setup for;
- Database modelling stages - conceptual, relational and ER models;
- Tables creation script;
- Generating and inserting relevant mock data into the database tables - demo scripts;
- Relevant data selects of different kinds and complexity levels;
- Relevant usage examples of key database technologies like transactions, triggers, views and indexes on the prepared database with explanations.

---

## Application core structure

| Path                                                           | Description                                                                                                                                                                                                                                                                                 |
|----------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| application-core/src/main/java/entities/                       | Folder contains database entity classes, automatically generated with Jakarta Persistence library and the built-in entity generation tool of IntelliJ IDEA Ultimate IDE. Preserves the entire structure of the original database – inter-entity relationships, cardinalities, etc.          |
| application-core/src/main/java/DAO/                        | Folder contains two Data Access Object classes – represents the data layer responsible for communication with the database; not directly accessible by the application users.                                                                                                               |
| application-core/src/main/java/service/                    | Folder contains two service classes, which represent the interface provided to the application users. These service classes work primarily with Visitor and Event entities and use the corresponding provided methods of the DAO layer.                                                     |
| application-core/src/main/java/Main.java                   | Main class of the application, contains structured demonstrational examples of the application usage. The application core is created for introductory purposes and, while not having a proper user interface or complete business logic, fully satisfies the B0B36DBS course requirements. |
