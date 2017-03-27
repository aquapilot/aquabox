/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test CLIHelperImpl class
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class CLIHelperImplTest {

   private static CLIHelper cliHelper;

   @Before
   public void before() {

      cliHelper = spy(new CLIHelperImpl());
   }

    @Test
    public void parseArguments_shouldNotThrowException_whenArgsIsNull() {

       // Given
       String[] nullArgs = null;

       // When
       cliHelper.parseArguments(nullArgs);

       // Then
       // No exception should occurs
    }

    @Test
    public void parseArguments_shouldNotThrowException_whenArgsIsAnEmptyArray() {

       // Given
       String[] emptyArgs = new String[0];

       // When
       cliHelper.parseArguments(emptyArgs);

       // Then
       // No exception should occurs
    }

    @Test
    public void showHelp_shouldBeCalled_whenRequestedWithHelpFlag() throws Exception {

       // Given
       String[] args = new String[] { "-h" };

       // When
       cliHelper.parseArguments(args);

       // Then
       verify(cliHelper, times(1)).showHelp();
    }

   @Test
   public void showHelp_shouldBeCalled_whenRequestedWithAlternativeHelpFlag() throws Exception {

      // Given
      String[] args = new String[] { "--help" };

      // When
      cliHelper.parseArguments(args);

      // Then
      verify(cliHelper, times(1)).showHelp();
   }

   @Test
   public void showHelp_shouldBeCalled_whenInvalidArgumentsIsGiven() {

      // Given
      String[] args = new String[] { "-unknownArg" };

      // When
      cliHelper.parseArguments(args);

      // Then
      verify(cliHelper, times(1)).showHelp();

   }

   @Test
   public void showVersion_shouldBeCalled_whenRequestedWithVersionFlag() throws Exception {

      // Given
      String[] args = new String[] { "-v" };

      // When
      cliHelper.parseArguments(args);

      // Then
      verify(cliHelper, times(1)).showVersion();
   }

   @Test
   public void showVersion_shouldBeCalled_whenRequestedWithVersionAlternativeFlag() throws Exception {

      // Given
      String[] args = new String[] { "--version" };

      // When
      cliHelper.parseArguments(args);

      // Then
      verify(cliHelper, times(1)).showVersion();
   }

   @Test
   public void isDebugEnabled_shouldEnableDebug_whenRequestedWithDebugFlag() throws Exception {

      // Given
      String[] args = new String[] { "-d" };

      // When
      cliHelper.parseArguments(args);

      // Then
      assertEquals(true, cliHelper.isDebugEnabled());
   }

   @Test
   public void isDebugEnabled_shouldBeTrue_whenRequestedWithDebugAlternativeFlag() throws Exception {

      // Given
      String[] args = new String[] { "--debug" };

      // When
      cliHelper.parseArguments(args);

      // Then
      assertEquals(true, cliHelper.isDebugEnabled());
   }

   @Test
   public void isDebugEnabled_shouldBeFalse_whenRequestedWithoutDebugFlag() throws Exception {

      // Given
      String[] args = new String[] { "--something" };

      // When
      cliHelper.parseArguments(args);

      // Then
      assertEquals(false, cliHelper.isDebugEnabled());
   }

}