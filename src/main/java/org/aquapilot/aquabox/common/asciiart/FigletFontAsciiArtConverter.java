/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.common.asciiart;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

/**
 * Created by vermeille on 27.03.2017.
 */
public class FigletFontAsciiArtConverter implements AsciiArtConverter {

   @Override
   public String convertTextToAsciiArt(String text) throws IOException {

      return FigletFont.convertOneLine(text);
   }
}
