package com.mathclub.cipher;

import com.mathclub.cipher.util.PlaintextFileReader;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Decrypt
 */
public class Decrypt {

  private static final String KEY_FILENAME = "key.csv";
  private static final String CIPHERTEXT_FILENAME = "ciphertext.txt";
  private static final String DECRYPTED_TEXT_FILENAME = "decrypted-text.txt";

  private static Key key;

  public static void main(String[] args) throws Exception {
    key = new Key(KEY_FILENAME);
    String ciphertext = readCiphertextFile(CIPHERTEXT_FILENAME);
    String decryptedText = decrypt(ciphertext);
    writeDecryptedTextFile(decryptedText, DECRYPTED_TEXT_FILENAME);
  }

  private static String readCiphertextFile(String filename) throws Exception {
    return new String(Files.readAllBytes(Paths.get(filename)));
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
