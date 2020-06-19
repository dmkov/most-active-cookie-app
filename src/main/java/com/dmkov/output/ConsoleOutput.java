package com.dmkov.output;

import java.util.List;

public class ConsoleOutput implements ResultOutput {

  @Override
  public void outputResult(List<String> result) {
    result.forEach(System.out::println);
  }
}
