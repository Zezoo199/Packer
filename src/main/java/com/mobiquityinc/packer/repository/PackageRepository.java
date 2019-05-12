package com.mobiquityinc.packer.repository;

import com.mobiquityinc.packer.model.Paket;
import java.util.ArrayList;
import java.util.List;

/** Singleton in memory repository for holding pakets from file */
public class PackageRepository {
  private static List<Paket> pakets;

  private PackageRepository() {}

  public static List<Paket> findAll() {
    if (pakets == null) {
      pakets = new ArrayList<>();
    }
    return pakets;
  }

  public static void save(Paket paket) {
    if (pakets == null) {
      pakets = new ArrayList<>();
    }
    pakets.add(paket);
  }
}
