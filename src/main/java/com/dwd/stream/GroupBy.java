package com.dwd.stream;

import com.google.common.collect.Lists;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupBy {

  private static List<String> data = Lists.asList("a", new String[]{"a", "b", "c"});

  private static List<Pair<String, String>> pairs = Lists.asList(new Pair<>("a", "m"), new Pair[] {new Pair<>("a", "b"), new Pair<>("c", "b"), new Pair<>("m", "b")});

  public static void main(String[] args) {
    groupAndCount();
    System.out.println("===");
    groupAndCollect();
    System.out.println("===");
    groupAndCollect2();
  }

  public static void groupAndCount() {
    Map<String, Long> collect = getData().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    print(collect);
  }

  public static void groupAndCollect() {
    Map<String, List<String>> collect = getData().collect(Collectors.groupingBy(Function.identity()));
    print(collect);
  }

  public static void groupAndCollect2() {
    Map<String, List<String>> collect = pairs.stream()
            .collect(Collectors.groupingBy(p -> p.getKey(), Collectors.mapping(p -> p.getValue(), Collectors.toList())));
    print(collect);
  }

  public static void print(Map<?, ?> data) {
    data.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + "->" + entry.getValue()));
  }

  public static Stream<String> getData() {
    return data.stream();
  }
}
