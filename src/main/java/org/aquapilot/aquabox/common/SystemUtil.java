/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.common;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * This class is a helper to check system requirements
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SystemUtil {

   private static final DefaultArtifactVersion JAVA_MINIMUM_SUPPORTED_VERSION = new DefaultArtifactVersion("8.0");

   private static final Logger LOGGER = LoggerFactory.getLogger(SystemUtil.class);

   /**
    * Check system requirements (available memory etc)
    */
   public void checkSystem() {

      LOGGER.info("Checking system ...");

      // TODO: implement get gpio version etc.

      checkJavaVersion();
   }

   private void writeCheckMessage(MessageType type, String description) {

      AnsiConsole.systemInstall();
      switch (type) {
         case OK:
            System.out.println("[" + ansi().fg(GREEN).a("OK").reset() + "   ]\t" + description);
            break;
         case WARNING:
            System.out.println("[" + ansi().fg(YELLOW).a("WARN").reset() + "]\t" + description);
            break;
         case ERROR:
            System.out.println("[" + ansi().fg(RED).a("ERROR").reset() + "]\t" + description);
            break;
      }
      AnsiConsole.systemUninstall();
   }

   public void checkJavaVersion() {

      if (JAVA_MINIMUM_SUPPORTED_VERSION.compareTo(getInstalledJavaVersion()) > 0) {
         writeCheckMessage(MessageType.ERROR,
                           String.format("Your java version (%s) is not supported. It should be at least version %s.",
                                         getInstalledJavaVersion(), JAVA_MINIMUM_SUPPORTED_VERSION));
      } else {
         writeCheckMessage(MessageType.OK,
                           String.format("Your java version (%s) is fine. It should be at least version %s.",
                                         getInstalledJavaVersion(), JAVA_MINIMUM_SUPPORTED_VERSION));
      }
   }

   /**
    * Returns the installed java version for humans (example: 8.0)
    * Note: Dear Oracle, no one understand why when we install Java8 the version is 1.8.0.
    *
    * @return
    */
   public DefaultArtifactVersion getInstalledJavaVersion() {

      String version = System.getProperty("java.version");
      String normalizedVersion = "";
      if (version.contains("_")) {
         normalizedVersion = version.substring(0, version.indexOf('_')).replace("1.", "");
      }
      return new DefaultArtifactVersion(normalizedVersion);
   }
}
