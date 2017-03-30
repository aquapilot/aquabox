/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.aquapilot.aquabox.modules.logger.Log;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manage CLI parameters
 * <p>
 * It is based on Args4j
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @link https://github.com/kohsuke/args4j
 */
public class CLIHelperImpl implements CLIHelper {

   @Log
   Logger log;

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
      this.allowedToStart = true;
      try {
         this.parser.parseArgument(args);

         if (options.isHelp()) {
            showHelp();
            this.allowedToStart = false;
         }
         if (options.isVersion()) {
            showVersion();
            this.allowedToStart = false;
         }
         if (options.isDebug()) {
            this.debugEnabled = true;
            enableDebug();
         } else {
            this.debugEnabled = false;
         }
      } catch (CmdLineException e) {
         this.log.debug("The given cli parameter is not a valid one.");

         // display help
         showHelp();
         this.allowedToStart = false;
      }

   }

   @Override
   public void enableDebug() {

      LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
      lc.getLogger("org.aquapilot.aquabox").setLevel(Level.DEBUG);
   }

   @Override
   public void showHelp() {

      this.parser.printUsage(System.err);
   }

   @Override
   public void showVersion() {

      //  throw new NotImplementedException();
      System.out.println("TODO");
   }

   @Override
   public boolean isDebugEnabled() {

      return this.debugEnabled;
   }

   @Override
   public boolean isAppAllowedToStart() {

      return this.allowedToStart;
   }

}
