/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.aquapilot.cli.CLIHelper;
import org.aquapilot.cli.CLIHelperImpl;
import org.aquapilot.modules.gpio.GPIOModule;
import org.aquapilot.modules.logger.LoggerModule;
import org.aquapilot.modules.sensors.RFModule;
import org.aquapilot.modules.storage.StorageModule;
import org.aquapilot.settings.SettingsHelper;
import org.aquapilot.settings.SettingsHelperImpl;
import org.aquapilot.settings.model.Settings;

/**
 * This class is the main class of Aquabox.
 * <p>
 * It launches the process according to given cli args
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class Launcher {

    private Launcher() {
        // Hide the implicit public constructor
    }

   public static final void main(String[] args) {

      CLIHelper cli = new CLIHelperImpl(args);

      SettingsHelper settingsHelper = new SettingsHelperImpl();
      Settings settings = settingsHelper.loadSettings();

      Injector injector = Guice.createInjector(new LoggerModule(), new StorageModule(settings), new GPIOModule(),
                                               new RFModule());

      Aquabox aquabox = injector.getInstance(Aquabox.class);
      aquabox.start();
    }
}
