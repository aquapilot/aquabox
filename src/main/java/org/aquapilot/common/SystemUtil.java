/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a helper to check system requirements
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SystemUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemUtil.class);

    /**
     * Check system requirements (available memory etc)
     */
    public static final void checkSystem() {

        //LOGGER.info("Checking system ...");

        StringBuilder checkSystemString = new StringBuilder();
        // TODO: implement get gpio version etc.

    }
}
