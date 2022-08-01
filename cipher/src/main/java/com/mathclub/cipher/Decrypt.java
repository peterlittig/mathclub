package com.mathclub.cipher;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Decrypt
 */
public class Decrypt {

  private static final String KEY_FILENAME = "key.csv";
  private static final String DEFAULT_CIPHERTEXT_FILENAME = "ciphertext.txt";
  private static final String DEFAULT_DECRYPTED_FILENAME = "decrypted.txt";
  private static final String DECRYPTION_SUFFIX = "-decrypted";

  private static Key key;
  private static String ciphertextFilename = DEFAULT_CIPHERTEXT_FILENAME;
  private static String decryptedFilename = DEFAULT_DECRYPTED_FILENAME;

  public static void main(String[] args) throws Exception {
    setConfiguration(args);
    String ciphertext = readCiphertextFile(ciphertextFilename);
    String decryptedText = decrypt(ciphertext);
    writeDecryptedTextFile(decryptedText, decryptedFilename);
  }

  private static void setConfiguration(String[] args) {
    key = new Key(KEY_FILENAME);
    if (args.length >= 1) {
      ciphertextFilename = args[0];
    }
    if (!ciphertextFilename.equals(DEFAULT_CIPHERTEXT_FILENAME)) {
      decryptedFilename = ciphertextFilename + DECRYPTION_SUFFIX;
    }
  }

  private static String readCiphertextFile(String filename) throws Exception {
    return new String(Files.readAllBytes(Paths.get(filename)))
        .replaceAll("[^0-9]", "");
  }

  private static String decrypt(String cipherText) {
    String decryptedText = "";
    for (int i = 0; i < cipherText.length(); i += 3) {
      String nextTrigram = cipherText.substring(i, i + 3);
      decryptedText += key.decrypt(nextTrigram);
    }
    return decryptedText;
  }

  private static void writeDecryptedTextFile(String decryptedText, String filename)
      throws Exception {
    try {
      File file = new File(filename);
      if (file.createNewFile()) {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(decryptedText);
        fileWriter.flush();
        fileWriter.close();
      } else {
        System.out.println("Decrypted text file named " + filename + " already exists.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred: " + e);
    }
  }
}
