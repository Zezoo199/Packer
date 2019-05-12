package com.mobiquityinc.packer.model;

import java.util.Comparator;

/** Model Thing */
public class Thing implements Comparable<Thing> {
  private long index;
  private double weight;
  private int price;

  public Thing() {}

  public Thing(long index, double weight, int price) {
    this.index = index;
    this.weight = weight;
    this.price = price;
  }

  public long getIndex() {
    return index;
  }

  public void setIndex(long index) {
    this.index = index;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * Comparing things by price desending and if price is same
   * then by weight ascending
   *
   * @param o
   * @return
   */
  @Override
  public int compareTo(Thing o) {
    return Comparator.comparing(Thing::getPrice)
        .reversed()
        .thenComparing(Thing::getWeight)
        .compare(this, o);
  }
}
