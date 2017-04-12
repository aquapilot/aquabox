/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.cli;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.aquapilot.aquabox.server.common.version.VersionHelper;
import org.aquapilot.aquabox.server.common.version.VersionHelperImpl;
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

   // We cannot inject logger here (it is not a guice module)
   private static final Logger LOGGER = LoggerFactory.getLogger(CLIHelperImpl.class);

   private CmdLineParser parser;
   private boolean allowedToStart = true;
   private boolean simulatorEnabled = false;

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
            enableDebug();
         }
         if (options.isSimulator()) {
            this.simulatorEnabled = true;
         }
      } catch (CmdLineException e) {
         LOGGER.debug("The given cli parameter is not a valid one.");

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
   public boolean isSimulatorEnabled() {

      return this.simulatorEnabled;
   }

   @Override
   public void showHelp() {

      this.parser.printUsage(System.err);
   }

   @Override
   public void showVersion() {

      VersionHelper versionHelper = new VersionHelperImpl();

      String version = versionHelper.getVersion();
      System.out.println(version);
   }

   @Override
   public boolean isAppAllowedToStart() {

      return this.allowedToStart;
   }

}
