# Most Active Cookie Application

This is a simple console application looking for the most frequent cookie stored in the file for the given date. 

## Requirements

* Java 8+
* Maven 3

## Build

Run `mvn clean package` to build the project. The build artifact will be stored in the `target/most-active-cookie.jar` path.

By default the application is configured in debug mode. If you would like to reduce amount of logs, please update `rootLogger.level` in `log4j2.properties` configuration file.

## Usage

## Running unit tests

Run `mvn clean test` to execute unit tests. JUnit 5 framework is used in test classes to simplify assertion of testing results.
