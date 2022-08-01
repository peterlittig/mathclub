package com.mathclub.cipher;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Encrypt
 */
public class Encrypt {

  private static final String KEY_FILENAME = "key.csv";
  private static final String DEFAULT_PLAINTEXT_FILENAME = "plaintext.txt";
  private static final String DEFAULT_CIPHERTEXT_FILENAME = "ciphertext.txt";
  private static final String ENCRYPTION_SUFFIX = "-encrypted";

  // Initialize with default values; use args to override.
  private static String plaintextFilename = DEFAULT_PLAINTEXT_FILENAME;
  private static String ciphertextFilename = DEFAULT_CIPHERTEXT_FILENAME;

  private static Key key;

  public static void main(String[] args) throws Exception {
    setConfiguration(args);
    String plaintext = readPlaintextFile(plaintextFilename);
    if (!isNormalized(plaintext)) {
      plaintext = NormalizePlaintext.normalize(plaintext);
    }
    String ciphertext = encrypt(plaintext);
    writeCiphertextToFile(ciphertext, ciphertextFilename);
  }

  private static void setConfiguration(String[] args) {
    key = new Key(KEY_FILENAME);
    if (args.length >= 1) {
      plaintextFilename = args[0];
    }
    if (!plaintextFilename.equals(DEFAULT_PLAINTEXT_FILENAME)) {
      ciphertextFilename = plaintextFilename + ENCRYPTION_SUFFIX;
    }
  }

  private static String readPlaintextFile(String filename) throws Exception {
    return new String(Files.readAllBytes(Paths.get(filename)));
  }

  private static boolean isNormalized(String plaintext) {
    return NormalizePlaintext.isNormalized(plaintext);
  }

  private static String encrypt(String plainText) {
    String cipherText = "";
    for (int i = 0; i < plainText.length(); i += 2) {
      String nextBigram = (i + 2 <= plainText.length())
          ? plainText.substring(i, i + 2)
          : plainText.substring(i, i + 1) + " ";
      cipherText += key.encrypt(nextBigram);
    }
    return cipherText;
  }

  private static void writeCiphertextToFile(String ciphertext, String filename)
      throws Exception {
    try {
      File file = new File(filename);
      if (file.createNewFile()) {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(ciphertext);
        fileWriter.flush();
        fileWriter.close();
      } else {
        System.out.println("Ciphertext file named " + filename + " already exists.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred: " + e);
    }
  }
}
