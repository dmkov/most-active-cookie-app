package com.dmkov;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandArguments {
  private String filename;
  private LocalDate date;

  private static final Logger logger = LogManager.getLogger();

  public CommandArguments(final String[] args) {
    logger.debug("Starting argument parsing: " + Arrays.toString(args));
    parseArguments(args);
    logger.debug("Finished argument parsing.");
  }

  public String getFilename() {
    return filename;
  }

  public LocalDate getDate() {
    return date;
  }

  private void parseArguments(final String[] args) {
    filename = parseFilename(args);
    date = parseDate(args);
  }

  private String parseFilename(final String[] args) {
    if (args == null || args.length < 1) {
      throw new IllegalArgumentException(
          "Log file for parsing is not specified in the argument list");
    }
    if (!isFileValid(args[0])) {
      throw new IllegalArgumentException("Log file specified in arguments does not exist");
    }
    logger.debug("Parsed filename: " + args[0]);

    return args[0];
  }

  private boolean isFileValid(final String filenameValue) {
    File file = new File(filenameValue);
    return file.exists() && file.isFile();
  }

  private LocalDate parseDate(final String[] args) {
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
    if (value == null || !isDateValid(value)) {
      throw new IllegalArgumentException("Date parameter is not specified or can not be parsed");
    }
    logger.debug("Parsed date: " + value);

    return LocalDate.parse(value);
  }

  private boolean isDateValid(final String dateValue) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(dateValue);
    } catch (ParseException e) {
      return false;
    }
    return true;
  }
}
