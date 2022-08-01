package com.mathclub.cipher;

/**
 * Frequency
 */
public class Frequency implements Comparable<Frequency> {

  private String label;
  private int count;
  private double frequency;

  public Frequency(String label, int count, double frequency) {
    this.label = label;
    this.count = count;
    this.frequency = frequency;
  }

  public String getLabel() {
    return label;
  }

  public int getCount() {
    return count;
  }

  public double getFrequency() {
    return frequency;
  }

  @Override
  public int compareTo(Frequency that) {
    return (this.count != that.count)
        ? this.count - that.count
        : this.label.compareTo(that.label);
  }

  @Override
  public String toString() {
    return label + "," + count + "," + frequency;
  }
}
