package com.dmkov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandArgumentsTest {

  @Test
  public void exceptionIsThrownWhenFileNameIsNotSpecifiedAsFirstArgument() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CommandArguments(new String[0]));
    assertEquals(
        "Log file for parsing is not specified in the argument list", exception.getMessage());
  }

  @Test
  public void exceptionIsThrownWhenFileDoesNotExist() {
    String[] args = new String[] {"random-file.csv"};
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CommandArguments(args));
    assertEquals("Log file specified in arguments does not exist", exception.getMessage());
  }

  @Test
  public void exceptionIsThrownWhenSpecifiedParameterIsNotAFile() {
    String[] args = new String[] {"./"};
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CommandArguments(args));
    assertEquals("Log file specified in arguments does not exist", exception.getMessage());
  }

  @Test
  public void exceptionIsThrownWhenDateIsNotSpecified() {
    // file reading should be mocked in unit tests for better experience
    String[] args = new String[] {"src/test/resources/example_cookie_log.csv"};
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CommandArguments(args));
    assertEquals("Date parameter is not specified in the argument list", exception.getMessage());
  }

  @Test
  public void exceptionIsThrownWhenDateIsSpecifiedWithoutDArgument() {
    // file reading should be mocked in unit tests for better experience
    String[] args = new String[] {"src/test/resources/example_cookie_log.csv", "2018-12-09"};
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CommandArguments(args));
    assertEquals("Date parameter is not specified in the argument list", exception.getMessage());
  }

  @Test
  public void filenameCanBeRetrievedFromCommandArguments() {
    // file reading should be mocked in unit tests for better experience
    String[] args = new String[] {"src/test/resources/example_cookie_log.csv", "-d", "2018-12-09"};
    CommandArguments commandArguments = new CommandArguments(args);
    assertEquals(args[0], commandArguments.getFilename());
  }

  @Test
  public void dateCanBeRetrievedFromCommandArguments() {
    // file reading should be mocked in unit tests for better experience
    String[] args = new String[] {"src/test/resources/example_cookie_log.csv", "-d", "2018-12-09"};
    CommandArguments commandArguments = new CommandArguments(args);
    assertEquals(args[2], commandArguments.getDate());
  }
}
