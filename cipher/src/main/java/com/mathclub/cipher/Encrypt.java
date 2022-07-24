package com.mathclub.cipher;

import com.mathclub.cipher.util.PlaintextFileReader;

import java.io.File;
import java.io.FileWriter;

/**
 * Encrypt
 */
public class Encrypt {

  private static final String KEY_FILENAME = "key.csv";
  private static final String CIPHERTEXT_FILENAME = "ciphertext.txt";
  private static final String PLAINTEXT_FILENAME = "plaintext.txt";

  private static Key key;

  public static void main(String[] args) throws Exception {
    key = new Key(KEY_FILENAME);
    PlaintextFileReader reader = new PlaintextFileReader();
    String plaintext = reader.readPlaintextFile(PLAINTEXT_FILENAME);
    String ciphertext = encrypt(plaintext);
    writeCiphertextToFile(ciphertext, CIPHERTEXT_FILENAME);
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
