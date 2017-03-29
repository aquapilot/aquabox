/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * This class manage CLI parameters
 * <p>
 * It is based on Args4j
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @link https://github.com/kohsuke/args4j
 */
public class CLIHelperImpl implements CLIHelper {

   private CmdLineParser parser;

   private boolean debugEnabled;

   private boolean allowedToStart = true;

   @Override
   public void parseArguments(String[] args) {

      if (args == null || args.length == 0) {
         return;
      }

      CLIOptions options = new CLIOptions();
      this.parser = new CmdLineParser(options);
      allowedToStart = true;
      try {
         parser.parseArgument(args);

         if (options.isHelp()) {
            showHelp();
            allowedToStart = false;
         }
         if (options.isVersion()) {
            showVersion();
            allowedToStart = false;
         }
         if (options.isDebug()) {
            debugEnabled = true;
         } else {
            debugEnabled = false;
         }
      } catch (CmdLineException e) {
         // display help
         showHelp();
         allowedToStart = false;
      }

   }

   @Override
   public void showHelp() {

      parser.printUsage(System.err);
   }

   @Override
   public void showVersion() {

      //  throw new NotImplementedException();
      System.out.println("TODO");
   }

   @Override
   public boolean isDebugEnabled() {

      return debugEnabled;
   }

   @Override
   public boolean isAppAllowedToStart() {

      return allowedToStart;
   }

}
