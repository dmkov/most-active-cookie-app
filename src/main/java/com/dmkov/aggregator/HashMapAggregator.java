package com.dmkov.aggregator;

import com.dmkov.model.CookieLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HashMapAggregator implements MostActiveCookieAggregator {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public List<String> getMostActiveCookies(List<CookieLog> cookies) {
    logger.debug("Looking for the most active cookies in the list");

    // First of all, we put all cookies to the map with counters,
    // where for every cookie as a key we have number of occurrences in the value
    Map<String, Integer> countMap = getCookiesCountMap(cookies);

    // Then, we iterate through the map and collect max number of elements together with values
    // We could sort the list based on occurrences but it will give us O(nlogn) complexity,
    // instead, we do it in one linear iteration O(n)
    Integer maxCount = 0;
    List<String> maxList = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
      if (maxCount.compareTo(entry.getValue()) == 0) {
        maxList.add(entry.getKey());
      } else if (maxCount.compareTo(entry.getValue()) < 0) {
        maxCount = entry.getValue();
        maxList.clear();
        maxList.add(entry.getKey());
      }
    }

    logger.debug(maxList.size() + " cookie(s) were selected");
    return maxList;
  }

  private Map<String, Integer> getCookiesCountMap(List<CookieLog> cookies) {
    Map<String, Integer> result = new HashMap<>();
    for (CookieLog cookie : cookies) {
      Integer count = result.getOrDefault(cookie.getCookie(), 0);
      result.put(cookie.getCookie(), count + 1);
    }

    return result;
  }
}
