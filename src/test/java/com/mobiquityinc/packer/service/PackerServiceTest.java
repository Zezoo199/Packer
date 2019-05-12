package com.mobiquityinc.packer.service;

import com.mobiquityinc.packer.exception.APIException;
import java.io.File;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PackerServiceTest {
  private PackerService service = new DefaultPackerService();
  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void givenSampleFile_whenPack_thenCheckOutput() throws Exception {
    // Given file from resources
    ClassLoader classLoader = getClass().getClassLoader();
    File sampleFileFromResources = new File(classLoader.getResource("sample.txt").getFile());

    // when
    String pack = service.pack(sampleFileFromResources.getPath());

    // then
    Assert.assertTrue(pack.equals("4\n" + "-\n" + "2,7\n" + "8,9"));
  }

  @Test
  public void givenAnotherFile_whenPack_thenCheckOutput() throws Exception {
    // Given another file from resources to check case of
    // "You would prefer to send a package which weights less in case
    // there is more than one package with the same price."
    ClassLoader classLoader = getClass().getClassLoader();
    File sampleFileFromResources = new File(classLoader.getResource("sample-2.txt").getFile());

    // when
    String pack = service.pack(sampleFileFromResources.getPath());

    // then check that index 3 is taken and not 2
    //    Assert.assertTrue(pack.equals("4,3"));
    Assert.assertTrue(pack.equals("4,3"));
  }

  @Test
  public void givenBadFileData_whenPack_thenException() throws Exception {
    // Given file from resources
    ClassLoader classLoader = getClass().getClassLoader();
    File fileWithBadData = new File(classLoader.getResource("badformatfile.txt").getPath());

    // expect
    expectedException.expect(APIException.class);
    expectedException.expectMessage(
        "Pre Validation Failed For file  "
            + fileWithBadData.getPath()
            + " Reason : NumberFormatException For input string: \"45A\"");

    // when
    String pack = service.pack(fileWithBadData.getPath());
  }

  @Test
  public void givenBadFilePath_whenPack_thenAPIException() throws Exception {
    // given
    String badFilePath = "XYZ";

    // expect
    expectedException.expect(APIException.class);
    expectedException.expectMessage(
        "Pre Validation Failed For file  XYZ Reason : NoSuchFileException XYZ");

    // when
    service.pack(badFilePath);
  }
}
