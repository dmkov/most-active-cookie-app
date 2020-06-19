package com.dmkov.aggregator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dmkov.model.CookieLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HashMapAggregatorTest {

  @Test
  public void emptyResultIsReturnedForEmptyList() {
    List<CookieLog> list = new ArrayList<>();
    List<String> expected = new ArrayList<>();

    MostActiveCookieAggregator aggregator = new HashMapAggregator();
    assertEquals(expected, aggregator.getMostActiveCookies(list));
  }

  @Test
  public void twoCookiesAreReturnedIfTheyHaveSameOccurrence() {
    List<CookieLog> list = new ArrayList<>(asList(
        new CookieLog("AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00"),
        new CookieLog("SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00"))
    );
    List<String> expected = new ArrayList<>(asList(
        "AtY0laUfhglK3lC7", "SAZuXPGUrfbcn5UA"
    ));

    MostActiveCookieAggregator aggregator = new HashMapAggregator();
    assertEquals(expected, aggregator.getMostActiveCookies(list));
  }

  @Test
  public void cookieWithLargestOccurrenceIsReturned() {
    List<CookieLog> list = new ArrayList<>(asList(
        new CookieLog("AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00"),
        new CookieLog("SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00"),
        new CookieLog("AtY0laUfhglK3lC7,2018-12-09T11:13:00+00:00"))
    );
    List<String> expected = new ArrayList<>(Collections.singletonList(
        "AtY0laUfhglK3lC7"
    ));

    MostActiveCookieAggregator aggregator = new HashMapAggregator();
    assertEquals(expected, aggregator.getMostActiveCookies(list));
  }

}
