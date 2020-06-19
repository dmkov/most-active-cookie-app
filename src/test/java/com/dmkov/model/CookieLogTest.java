package com.dmkov.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

public class CookieLogTest {

  @Test
  public void exceptionIsThrownWhenStringCanNotBeSplitByComa() {
    String log = "abcd2018-12-12";
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CookieLog(log));
    assertEquals("Cookie log format is not correct", exception.getMessage());
  }

  @Test
  public void exceptionIsThrownWhenStringHasMultipleComas() {
    String log = "abcd,2018-12-12,aaaa";
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new CookieLog(log));
    assertEquals("Cookie log format is not correct", exception.getMessage());
  }

  @Test
  public void exceptionIsThrownWhenDateCanNotBeParsed() {
    String log = "abcd,20181212";
    assertThrows(DateTimeParseException.class, () -> new CookieLog(log));
  }

  @Test
  public void dateCanBeRetrievedFromCookieLog() {
    String log = "5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00";
    ZonedDateTime expected = ZonedDateTime.parse("2018-12-09T07:25:00+00:00");

    CookieLog cookieLog = new CookieLog(log);
    assertEquals(expected, cookieLog.getDate());
  }

  @Test
  public void cookieCanBeRetrievedFromCookieLog() {
    String log = "5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00";

    CookieLog cookieLog = new CookieLog(log);
    assertEquals("5UAVanZf6UtGyKVS", cookieLog.getCookie());
  }

}
