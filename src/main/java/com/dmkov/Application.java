package com.dmkov;

import com.dmkov.model.CookieLog;
import com.dmkov.reader.CookiesReader;
import com.dmkov.reader.FileReader;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
  private static final Logger logger = LogManager.getLogger();

  public static void main(final String[] args) {
    logger.debug("Application started");

    CommandArguments commandArguments = new CommandArguments(args);
    CookiesReader cookiesReader = new FileReader();
    List<CookieLog> cookies = cookiesReader.readCookiesFromSource(
        commandArguments.getFilename(), commandArguments.getDate()
    );
    // load data from file
    // output the result

    logger.debug("Application ended");
  }
}
