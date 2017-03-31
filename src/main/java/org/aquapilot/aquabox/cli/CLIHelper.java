/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

/**
 * This interface define how to manage CLI parameters
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @see https://github.com/kohsuke/args4j
 */
public interface CLIHelper {

   /**
    * Parse given arguments (usually coming from main(String[] args)).
    * During the parsing it will automatically call the required methods (showHelp, showVersion)
    *
    * @param args arguments list (main(String[] args)
    */
   void parseArguments(String[] args);

   /**
    * Display a list of accepted cli parameters with a description
    */
   void showHelp();

   /**
    * Display the aquabox version
    */
   void showVersion();

   /**
    * Returns true if the received CLI parameters are not blockers
    *
    * @return
    */
   boolean isAppAllowedToStart();

   /**
    * Enable verbose console output
    * <p>It display log.debug entries</p>
    */
   void enableDebug();
}
