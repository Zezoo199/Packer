package com.mobiquityinc.packer.model;

import java.util.ArrayList;
import java.util.List;

/** Model Paket */
public class Paket {
  private double weightLimit;
  private List<Thing> things;

  public Paket() {
    things = new ArrayList<>();
  }

  public Paket(double weightLimit, List<Thing> things) {
    this.weightLimit = weightLimit;
    this.things = things;
  }

  public double getWeightLimit() {
    return weightLimit;
  }

  public void setWeightLimit(double weightLimit) {
    this.weightLimit = weightLimit;
  }

  public List<Thing> getThings() {
    return things;
  }

  public void setThings(List<Thing> things) {
    this.things = things;
  }
}
