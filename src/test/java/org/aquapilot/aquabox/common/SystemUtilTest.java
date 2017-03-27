/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by vermeille on 27.03.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class SystemUtilTest {

   @Test
   public void checkSystem_shouldNotThrowAnyException() throws Exception {

      // Given
      SystemUtil systemUtil = new SystemUtil();

      // When
      systemUtil.checkSystem();

      // Then
      // No exception should occurs
   }

}