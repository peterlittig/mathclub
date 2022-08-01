package com.mathclub.cipher;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * NormalizePlaintext
 */
public class NormalizePlaintext {

  private static final String DEFAULT_PLAINTEXT_FILENAME = "plaintext.txt";
  private static final String NORMALIZED_PREFIX = "normalized-";

  public static void main(String[] args) throws Exception {
    String plaintextFilename = args.length >= 1 ? args[0] : DEFAULT_PLAINTEXT_FILENAME;
    String plaintext = readPlaintextFile(plaintextFilename);
    String normalizedPlaintext = normalize(plaintext);
    String normalizedFilename = NORMALIZED_PREFIX + plaintextFilename;
    writeNormalizedPlaintextToFile(normalizedPlaintext, normalizedFilename);
  }

  public static boolean isNormalized(String plaintext) {
    return plaintext == null ? false : plaintext.matches("[^A-Z ]");
  }

  public static String normalize(String plaintext) {
    return plaintext
        .replaceAll("[\t\n]", " ")
        .replaceAll("[^a-zA-Z ]", "")
        .replaceAll("[ ]{2,}", " ")
        .toUpperCase();
  }

  private static String readPlaintextFile(String filename) throws Exception {
    return new String(Files.readAllBytes(Paths.get(filename)));
  }

  private static void writeNormalizedPlaintextToFile(String normalizedPlaintext, String filename)
      throws Exception {
    try {
      File file = new File(filename);
      if (file.createNewFile()) {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(normalizedPlaintext);
        fileWriter.flush();
        fileWriter.close();
      } else {
        System.out.println("File named " + filename + " already exists. Exiting early.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred: " + e);
    }
  }
}
