/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.cli;

import org.kohsuke.args4j.Option;

/**
 * This class describe allowed options in CLI.
 * <p>
 * For more info see Args4j documentation https://github.com/kohsuke/args4j
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class CLIOptions {

   @Option(name = "-h", aliases = { "--help" }, usage = "Display help")
   @SuppressWarnings("unused") // It is actually used by args4j
   private boolean help;

   @Option(name = "-v", aliases = { "--version" }, usage = "Display version")
   @SuppressWarnings("unused") // It is actually used by args4j
   private boolean version;

   @Option(name = "-d", aliases = { "--debug" }, usage = "Enable debug mode")
   @SuppressWarnings("unused") // It is actually used by args4j
   private boolean debug;

   @Option(name = "-s",
         aliases = { "--simulate" },
         usage = "Simulate raspberrypi activity on the current running " + "device.")
   @SuppressWarnings("unused") // It is actually used by args4j
   private boolean simulator;

   public boolean isHelp() {

      return this.help;
   }

   public boolean isVersion() {

      return this.version;
   }

   public boolean isDebug() {

      return this.debug;
   }

   public boolean isSimulator() {

      return this.simulator;
   }
}
