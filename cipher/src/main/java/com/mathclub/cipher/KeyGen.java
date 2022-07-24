package com.mathclub.cipher;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * KeyGen
 *
 * Generates a random key for the nk23 cipher.
 */
public class KeyGen {

  private static final List<String> ALPHABET
      = ImmutableList.of(
          "A", "B", "C", "D", "E", "F", "G", "H", "I",
          "J", "K", "L", "M", "N", "O", "P", "Q", "R",
          "S", "T", "U", "V", "W", "X", "Y", "Z", " "
      );

  // TODO: Allow to be specified on command line.
  private static final String KEY_FILENAME = "key.csv";

  public static void main(String[] args) {
    Map<String, String> key = generateRandomKey();
    writeKeyFile(key, KEY_FILENAME);
  }

  private static void writeKeyFile(Map<String, String> key, String filename) {
    try {
      File file = new File(filename);
      if (file.createNewFile()) {
        FileWriter fileWriter = new FileWriter(file);
        for (String bigram : key.keySet()) {
          String line = bigram + "," + key.get(bigram) + "\n";
          fileWriter.write(line);
	}
        fileWriter.flush();
        fileWriter.close();
      } else {
        System.out.println("File named " + filename + " already exists.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred: " + e);
    }
  }

  private static Map<String, String> generateRandomKey() {
    ImmutableMap.Builder<String, String> key = ImmutableMap.builder();
    List<String> codeList = generateCodeList();
    Collections.shuffle(codeList);
    int i = 0;
    for (String x : ALPHABET) {
      for (String y : ALPHABET) {
        String bigram = x + y;
        key.put(bigram, codeList.get(i++));
      }
    }
    return key.build();
  }

  private static List<String> generateCodeList() {
    List<String> codeList = new ArrayList<>();
    for (int i = 1; i < 1000; i++) {
      codeList.add(toCodeString(i));
    }
    return codeList;
  }

  /*
   * The input code should satisfy 1 <= code <= 999.
   */
  private static String toCodeString(int code) {
    if (code <= 0 || code >= 1000) {
      throw new IllegalArgumentException("Code must be in range 1-999 inclusive.");
    }
    if (code < 10) {
      return "00" + code;
    } else if (code < 100) {
      return "0" + code;
    } else {
      return "" + code;
    }
  }
}
