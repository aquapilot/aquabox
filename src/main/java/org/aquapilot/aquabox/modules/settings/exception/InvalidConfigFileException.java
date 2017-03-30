/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.settings.exception;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * This exception is thrown when a problem occurs according to the configuration file
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class InvalidConfigFileException extends UncheckedIOException {

   public InvalidConfigFileException(String message) {

      super(message, null);
   }

   public InvalidConfigFileException(String message, IOException cause) {

      super(message, cause);
   }
}
