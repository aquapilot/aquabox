/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.common.asciiart;

import java.io.IOException;

/**
 * Created by vermeille on 27.03.2017.
 */
public interface AsciiArtConverter {

   /**
    * Convert a text to ascii art string
    *
    * @param text
    * @return ready to print string ascii art string
    */
   String convertTextToAsciiArt(String text) throws IOException;
}
