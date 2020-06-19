package com.dmkov.reader;

import com.dmkov.model.CookieLog;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileReader implements CookiesReader {
  private static final Logger logger = LogManager.getLogger();

  public List<CookieLog> readCookiesFromSource(final String path, final LocalDate date) {
    logger.debug("Started cookies loading");

    final ZoneId utcZone = ZoneId.of("UTC");
    final ZonedDateTime filterStartTime = date.atStartOfDay(utcZone);
    final ZonedDateTime filterEndTime = date.atStartOfDay(utcZone).plusDays(1);
    List<CookieLog> list = loadCookiesFromFile(path, filterStartTime, filterEndTime);

    logger.debug(list.size() + " cookies were loaded for the given date");
    return list;
  }

  /**
   * The main method to load cookies from file and filter records for the given date.
   * It is pretty simple and not optimized for large files. As a part of possible optimization
   * the content should be loaded in chunks instead of one single stream.
   * I also did not use the fact that records are ordered in the file and after moving to the next
   * date we do not need to load the rest of the file.
   */
  private List<CookieLog> loadCookiesFromFile(
      final String filename,
      final ZonedDateTime filterStartTime,
      final ZonedDateTime filterEndTime) {

    List<CookieLog> list;
    // read file into stream, try-with-resources
    // skip the first line with headers
    try (Stream<String> stream = Files.lines(Paths.get(filename)).skip(1)) {
      list = stream
              .map(this::mapStringToCookieLog)
              .filter(
                  log ->
                      log != null
                          && filterStartTime.compareTo(log.getDate()) <= 0
                          && filterEndTime.compareTo(log.getDate()) > 0)
              .collect(Collectors.toList());

    } catch (IOException e) {
      throw new IllegalArgumentException("Cookie log file can not be open and read");
    }

    return list;
  }

  /**
   * Mapper of string log line to the CookieLog object
   * (This method should be extracted to a separate mapper class in future)
   */
  private CookieLog mapStringToCookieLog(final String str) {
    CookieLog cookie = null;
    try {
      cookie = new CookieLog(str);
    } catch (IllegalArgumentException | DateTimeParseException e) {
      logger.debug("Log " + str + " has wrong format and cannot be parsed. It will be skipped.");
    }
    return cookie;
  }
}
