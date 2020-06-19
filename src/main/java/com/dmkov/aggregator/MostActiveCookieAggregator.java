package com.dmkov.aggregator;

import com.dmkov.model.CookieLog;
import java.util.List;

public interface MostActiveCookieAggregator {
  List<String> getMostActiveCookies(List<CookieLog> cookies);
}
