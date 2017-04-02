/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.common;

import org.aquapilot.aquabox.server.common.asciiart.AsciiArtConverter;
import org.aquapilot.aquabox.server.common.asciiart.FigletFontAsciiArtConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by vermeille on 27.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreditsUtilTest {

   private static Logger logger = LoggerFactory.getLogger(CreditsUtilTest.class);

   @Before
   public void before() {

   }

   @Test
   public void printCredits_shouldConvertTextToAscii() {

      CreditsUtil creditsUtil = new CreditsUtil(new FigletFontAsciiArtConverter());
      creditsUtil.printCredits();

      //TODO: should read the log output

   }

   @Test
   public void printCredits_shouldNotThrowException_whenAnIOExceptionOccursInTheConverter() throws Exception {

      // Given
      AsciiArtConverter asciiArtConverter = mock(FigletFontAsciiArtConverter.class);
      when(asciiArtConverter.convertTextToAsciiArt(anyString())).thenThrow(IOException.class);
      CreditsUtil creditsUtil = new CreditsUtil(asciiArtConverter);

      // When
      creditsUtil.printCredits();

      // Then
      // should not throw exception
   }

}