package com.mathclub.cipher.util;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * PlaintextFileReader
 */
public class PlaintextFileReader {

  public String readPlaintextFile(String filename) throws Exception {
    return new String(Files.readAllBytes(Paths.get(filename)))
        .replaceAll("\n", " ")
        .replaceAll("[^a-zA-Z ]", "")
        .toUpperCase();
  }
}
