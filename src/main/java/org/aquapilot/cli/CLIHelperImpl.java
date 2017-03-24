/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.cli;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class manage CLI parameters
 * <p>
 * It is based on Args4j
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @see https://github.com/kohsuke/args4j
 */
public class CLIHelperImpl implements CLIHelper {

   private CLIOptions options;
   private CmdLineParser parser;

   private boolean debugEnabled;

   public CLIHelperImpl(String[] args) {

      this.options = new CLIOptions();
      this.parser = new CmdLineParser(options);

      if (args.length == 0) {
         return;
      }

      try {
         parser.parseArgument(args);

         if (options.isHelp()) {
            showHelp();
         }
         if (options.isVersion()) {
            showVersion();
         }
         if (options.isDebug()) {
            debugEnabled = true;
         } else {
            debugEnabled = false;
         }

      } catch (CmdLineException e) {
         // display help
         parser.printUsage(System.out);
      }
   }

   @Override
   public void showHelp() {

      parser.printUsage(System.err);
   }

   @Override
   public void showVersion() {

      throw new NotImplementedException();
   }

   @Override
   public boolean isDebugEnabled() {

      return debugEnabled;
   }

}
