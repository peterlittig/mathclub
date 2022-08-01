package com.mathclub.cipher;

import com.google.common.collect.ImmutableMap;
import com.mathclub.cipher.util.CsvResourceLoader;

import java.util.List;
import java.util.Map;

/**
 * Key
 *
 * Represents a key in the NK23 cipher.
 */
public class Key {

  private final Map<String, String> decryptionMap;
  private final Map<String, String> encryptionMap;

  /*
   * The List<List<String>> returned by the CsvResourceLoader
   * should have the following properties:
   *
   * Each List<String> should contain two entries. The first
   * is expected to be a bigram constructed from the alphabet
   * {'A', 'B', ..., 'Z', ' '}. The second is expected to be
   * a trigram consisting of digits 0 - 9.
   */
  public Key(String filename) {
    CsvResourceLoader loader = new CsvResourceLoader();
    List<List<String>> mappings = loader.loadCsvResource(filename);
    decryptionMap = initializeDecryptionMap(mappings);
    encryptionMap = initializeEncryptionMap(mappings);
  }

  private Map<String, String> initializeDecryptionMap(List<List<String>> mappings) {
    ImmutableMap.Builder<String, String> decryptionMap = ImmutableMap.builder();
    for (List<String> mapping : mappings) {
      // TODO: Add validation checks.
      decryptionMap.put(mapping.get(1).substring(1, 4), mapping.get(0).substring(1, 3));
    }
    return decryptionMap.build();
  }

  private Map<String, String> initializeEncryptionMap(List<List<String>> mappings) {
    ImmutableMap.Builder<String, String> encryptionMap = ImmutableMap.builder();
    for (List<String> mapping : mappings) {
      // TODO: Add validation checks.
      encryptionMap.put(mapping.get(0).substring(1, 3), mapping.get(1).substring(1, 4));
    }
    return encryptionMap.build();
  }

  public String decrypt(String trigram) {
    if (!decryptionMap.containsKey(trigram)) {
      throw new IllegalArgumentException("Cannot decrupt invalid trigram: " + trigram);
    }
    return decryptionMap.get(trigram);
  }

  public String encrypt(String bigram) {
    if (!encryptionMap.containsKey(bigram)) {
      throw new IllegalArgumentException("Cannot encrypt invalid bigram: " + bigram);
    }
    return encryptionMap.get(bigram);
  }
}
