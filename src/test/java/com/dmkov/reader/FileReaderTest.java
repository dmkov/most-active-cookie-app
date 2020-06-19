package com.dmkov.reader;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dmkov.CommandArguments;
import com.dmkov.model.CookieLog;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileReaderTest {

  @Test
  public void exceptionIsThrownIfFileCanNotBeRead() {
    // file reading should be mocked in unit tests for better experience
    String filename = "src/test/resources/not_existing_file.csv";
    LocalDate date = LocalDate.of(2018, 12, 9);

    CookiesReader fileReader = new FileReader();
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class,
            () -> fileReader.readCookiesFromSource(filename, date));
    assertEquals("Cookie log file can not be open and read", exception.getMessage());
  }

  @Test
  public void listOfCookieLogsCanBeFilteredForTheSpecificDate() {
    // file reading should be mocked in unit tests for better experience
    String filename = "src/test/resources/example_cookie_log.csv";
    LocalDate date = LocalDate.of(2018, 12, 9);
    List<CookieLog> expected = new ArrayList<>(asList(
        new CookieLog("AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00"),
        new CookieLog("SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00"),
        new CookieLog("5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00"),
        new CookieLog("AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00")
    ));

    CookiesReader fileReader = new FileReader();
    List<CookieLog> list = fileReader.readCookiesFromSource(filename, date);

    assertEquals(list, expected);
  }

  @Test
  public void resultListIsEmptyIfThereAreNoRecordsForTheGivenDate() {
    // file reading should be mocked in unit tests for better experience
    String filename = "src/test/resources/example_cookie_log.csv";
    LocalDate date = LocalDate.of(2020, 12, 9);
    List<CookieLog> expected = new ArrayList<>();

    CookiesReader fileReader = new FileReader();
    List<CookieLog> list = fileReader.readCookiesFromSource(filename, date);

    assertEquals(list, expected);
  }

  @Test
  public void resultListIsEmptyIfThereIsEmptyFile() {
    // file reading should be mocked in unit tests for better experience
    String filename = "src/test/resources/empty_cookie_log.csv";
    LocalDate date = LocalDate.of(2020, 12, 9);
    List<CookieLog> expected = new ArrayList<>();

    CookiesReader fileReader = new FileReader();
    List<CookieLog> list = fileReader.readCookiesFromSource(filename, date);

    assertEquals(list, expected);
  }

  @Test
  public void logsWithWrongFormatAreSkippedFromResultSet() {
    // file reading should be mocked in unit tests for better experience
    String filename = "src/test/resources/wrong_format_cookie_log.csv";
    LocalDate date = LocalDate.of(2018, 12, 9);

    List<CookieLog> expected = new ArrayList<>(asList(
        new CookieLog("AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00"),
        new CookieLog("SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00")
    ));

    CookiesReader fileReader = new FileReader();
    List<CookieLog> list = fileReader.readCookiesFromSource(filename, date);

    assertEquals(list, expected);
  }
}
