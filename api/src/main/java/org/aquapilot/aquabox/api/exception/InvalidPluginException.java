/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api.exception;

import java.io.FileNotFoundException;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class InvalidPluginException extends Exception {
    public InvalidPluginException(FileNotFoundException e) {

    }
}
