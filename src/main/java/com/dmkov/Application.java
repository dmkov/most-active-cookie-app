package com.dmkov;

import com.dmkov.aggregator.HashMapAggregator;
import com.dmkov.aggregator.MostActiveCookieAggregator;
import com.dmkov.model.CookieLog;
import com.dmkov.output.ConsoleOutput;
import com.dmkov.output.ResultOutput;
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

    MostActiveCookieAggregator aggregator = new HashMapAggregator();
    List<String> result = aggregator.getMostActiveCookies(cookies);

    ResultOutput console = new ConsoleOutput();
    console.outputResult(result);
  }
}
