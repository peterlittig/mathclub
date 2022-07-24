package com.mathclub.cipher.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * CsvResourceLoader
 */
public class CsvResourceLoader {

  public List<List<String>> loadCsvResource(String filename) {
    List<List<String>> records = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(filename));) {
      while (scanner.hasNext()) {
        records.add(parseRow(scanner.nextLine()));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return records;
  }

  private List<String> parseRow(String scannedLine) {
    return Arrays.asList(scannedLine.split(","));
  }
}
