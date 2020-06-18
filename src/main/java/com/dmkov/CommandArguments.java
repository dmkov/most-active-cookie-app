package com.dmkov;

import java.io.File;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandArguments {
  private String filename;
  private String date;

  private static final Logger logger = LogManager.getLogger();

  public CommandArguments(String[] args) {
    logger.debug("Starting argument parsing: " + Arrays.toString(args));
    parseArguments(args);
    logger.debug("Finished argument parsing.");
  }

  public String getFilename() {
    return filename;
  }

  public String getDate() {
    return date;
  }

  private void parseArguments(String[] args) {
    filename = parseFilename(args);
    date = parseDate(args);
  }

  private String parseFilename(String[] args) {
    if (args == null || args.length < 1) {
      throw new IllegalArgumentException("Log file for parsing is not specified in the argument list");
    }
    File file = new File(args[0]);
    if (!file.exists() || !file.isFile()) {
      throw new IllegalArgumentException("Log file specified in arguments does not exist");
    }
    logger.debug("Parsed filename: " + args[0]);

    return args[0];
  }

  private String parseDate(String[] args) {
    if (args == null || args.length < 2) {
      throw new IllegalArgumentException("Date parameter is not specified in the argument list");
    }

    String value = null;
    for (int i = 0; i < args.length - 1; i++) {
      if (args[i].equals("-d")) {
        value = args[i + 1];
        break;
      }
    }
    if (value == null) {
      throw new IllegalArgumentException("Date parameter is not specified in the argument list");
    }
    logger.debug("Parsed date: " + value);

    return value;
  }

}
