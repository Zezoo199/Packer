package com.mobiquityinc.packer.repository;

import com.mobiquityinc.packer.model.Paket;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class PaketRepositoryTest {
  @Test
  public void givenEmptyRepository_whenSavePackage_thenSaved() {
    // given
    Assert.assertTrue(PackageRepository.findAll().isEmpty());

    // when
    PackageRepository.save(new Paket());

    // then
    Assert.assertTrue(PackageRepository.findAll().size() == 1);
  }

  @After
  public void clear() {
    PackageRepository.findAll().clear();
  }
}
