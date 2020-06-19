package com.dmkov.model;

import java.time.ZonedDateTime;

public class CookieLog {
  private final String cookie;
  private final ZonedDateTime date;

  private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

  public CookieLog(final String log) {
    // str[0] contains cookie and str[1] has a date string after splitting
    String[] str = log.split(",");
    if (str.length != 2) {
      throw new IllegalArgumentException("Cookie log format is not correct");
    }

    this.cookie = str[0];
    this.date = ZonedDateTime.parse(str[1]);
  }

  public String getCookie() {
    return cookie;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  @Override
  /**
   * This method can be simply generated using lombok
   */
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof CookieLog)) {
      return false;
    }
    CookieLog c = (CookieLog) o;

    // Compare the data members and return accordingly
    return cookie.equals(c.cookie) && date.equals(c.date);
  }
}
