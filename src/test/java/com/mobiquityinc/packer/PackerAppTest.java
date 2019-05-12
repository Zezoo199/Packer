package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exception.APIException;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class PackerAppTest {

  @Test
  public void givenSampleFile_whenPack_thenCheckOutput() throws APIException {
    // Given file from resources
    ClassLoader classLoader = getClass().getClassLoader();
    File sampleFileFromResources = new File(classLoader.getResource("sample.txt").getFile());

    // when
    String pack = Packer.pack(sampleFileFromResources.getPath());

    // then
    Assert.assertTrue(pack.equals("4\n" + "-\n" + "2,7\n" + "8,9"));
  }
}
