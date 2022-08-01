package com.mathclub.cipher;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * GenerateFrequencies
 */
public class GenerateFrequencies {

  private static final String DEFAULT_INPUT_FILENAME = "input.txt";
  private static final int DEFAULT_BLOCK_SIZE = 2;

  public static void main(String[] args) throws Exception {
    String inputFilename = args.length >= 1 ? args[0] : DEFAULT_INPUT_FILENAME;
    String text = readTextFile(inputFilename);
    int blockSize = args.length >= 2 ? Integer.parseInt(args[1]) : DEFAULT_BLOCK_SIZE;
    Map<String, Integer> frequencies = generateFrequencies(text, blockSize);
    int n = text.length() / blockSize;
    NavigableSet<Frequency> results = new TreeSet<>();
    for (String label : frequencies.keySet()) {
      int count = frequencies.get(label);
      double frequency = (double) count / (double) n;
      results.add(new Frequency(label, count, frequency));
    }
    writeResultsFile(results, resultsFilename(inputFilename, blockSize));
  }

  private static String readTextFile(String filename) throws Exception {
    return new String(Files.readAllBytes(Paths.get(filename)));
  }

  /*
   * The name of the results file is generated from the input filename
   * by adding a tag to it. The tag has the form "Ngrams" where N is the
   * block size used in the frequency analysis. If the input filename
   * ends with a ".txt" extension, the extension will be preserved.
   *
   * Ex: N = 2, "article.txt" -> "article-2grams.txt"
   * Ex: N = 3, "inputfile" -> "inputfile-3grams"
   */
  private static String resultsFilename(String inputFilename, int blockSize) {
    String tag = String.format("-%dgrams", blockSize);
    if (inputFilename.endsWith(".txt")) {
      String baseFilename = inputFilename.substring(0, inputFilename.length() - 4);
      return baseFilename + tag + ".txt";
    } else {
      return inputFilename + tag;
    }
  }

  private static Map<String, Integer> generateFrequencies(String ciphertext, int blockSize) {
    Map<String, Integer> frequencies = new HashMap<>();
    for (int i = 0; i < ciphertext.length(); i += blockSize) {
      if (i + blockSize <= ciphertext.length()) {
        String block = ciphertext.substring(i, i + blockSize);
        int count = frequencies.containsKey(block) ? frequencies.get(block) : 0;
        frequencies.put(block, count + 1);
      }
    }
    return frequencies;
  }

  private static void writeResultsFile(NavigableSet<Frequency> results, String filename)
      throws Exception {
    try {
      File file = new File(filename);
      if (file.createNewFile()) {
        FileWriter fileWriter = new FileWriter(file);
        for (Frequency frequency : results.descendingSet()) {
          String fileEntry
              = String.format("%s, %3d, %.3f\n",
                  frequency.getLabel(),
                  frequency.getCount(),
                  frequency.getFrequency());
          fileWriter.write(fileEntry);
        }
        fileWriter.flush();
        fileWriter.close();
      } else {
        System.out.println("Results file named " + filename + " already exists.");
      }
    } catch (Exception e) {
      System.out.println("An error occurred: " + e);
    }
  }
}
