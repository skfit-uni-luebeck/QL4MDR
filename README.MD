# Samply MDR QL4MDR

Samply MDR QL4MDR is the GraphlQL Interface to the Samply MDR implementing a new query language for metadata.
This project implements the GraphlQL Interface *only*. 

Currently supported entry points are (due to the missing Conceptual Layer of Samply):
* Namespace(s)
* DataElement

## Installation

See the MDR Bundle install guide.

## Usage

Open your web browser and navigate to the application, e.g.
`http://localhost:8080/samply4graph/`.

## Technology

This project uses the following technologies:

* [graphql-java](https://github.com/graphql-java/graphql-java)
* [graphiql](https://github.com/graphql/graphiql)

## Build

Use maven to build the `war` file:

```
mvn clean package
```