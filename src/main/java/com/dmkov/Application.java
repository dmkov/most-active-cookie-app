package com.dmkov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    logger.debug("Application started");

    CommandArguments commandArguments = new CommandArguments(args);
    // get file and date from arguments
    // load data from file
    // output the result

    logger.debug("Application ended");
  }
}
