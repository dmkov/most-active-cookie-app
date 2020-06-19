package com.dmkov.reader;

import com.dmkov.model.CookieLog;
import java.time.LocalDate;
import java.util.List;

public interface CookiesReader {
  List<CookieLog> readCookiesFromSource(final String path, final LocalDate date);
}
