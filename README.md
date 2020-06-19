# Most Active Cookie Application

This is a simple console application looking for the most frequent cookie stored in the file for the given date. 

## Requirements

* Java 8+
* Maven 3

## Build

Run `mvn clean package` to build the project. The build artifact will be stored in the `target/most-active-cookie.jar` path.

By default the application is configured in debug mode. If you would like to reduce amount of logs, please update `rootLogger.level` in `log4j2.properties` configuration file.

## Usage

To execute the application, copy jar file and run `java -jar most-active-cookie.jar cookie_log.csv -d 2018-12-09` with the path to your log file and required date to filter. 

Please make sure that the log file has following structure:
```
cookie,timestamp
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00
4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00
fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00
4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00
```

## Running unit tests

Run `mvn clean test` to execute unit tests. JUnit 5 framework is used in test classes to simplify assertion of testing results.
