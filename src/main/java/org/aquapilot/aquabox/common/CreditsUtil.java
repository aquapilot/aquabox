/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.common;

import com.github.lalyos.jfiglet.FigletFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * This class is a helper to display credits
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class CreditsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditsUtil.class);

    public static final void printCredits() {

        StringBuilder creditsString = new StringBuilder();
        creditsString.append("\n");
        creditsString.append("================================================================\n");

        try {
            String asciiArt = FigletFont.convertOneLine("Aquapilot");
            creditsString.append(asciiArt);

        } catch (IOException e) {
            LOGGER.warn("Could not generate ascii art for printCredits", e);
        } finally {
            creditsString.append("(C) Sébastien Vermeille <sebastien.vermeille@gmail.com> \n");
            creditsString.append("Distributed under MIT license \n");

            creditsString.append("================================================================\n");
            LOGGER.info(creditsString.toString());
        }
    }
}
