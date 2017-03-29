/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.aquapilot.aquabox.cli.CLIHelper;
import org.aquapilot.aquabox.cli.CLIHelperImpl;
import org.aquapilot.aquabox.modules.gpio.GPIOModule;
import org.aquapilot.aquabox.modules.logger.LoggerModule;
import org.aquapilot.aquabox.modules.notifier.NotifierModule;
import org.aquapilot.aquabox.modules.sensors.SensorModule;
import org.aquapilot.aquabox.modules.settings.SettingsModule;
import org.aquapilot.aquabox.modules.settings.helper.SettingsHelper;
import org.aquapilot.aquabox.modules.settings.helper.SettingsHelperImpl;
import org.aquapilot.aquabox.modules.settings.model.Settings;
import org.aquapilot.aquabox.modules.storage.StorageModule;

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

       CLIHelper cli = new CLIHelperImpl();
       cli.parseArguments(args);

        SettingsHelper settingsHelper = new SettingsHelperImpl();
        Settings settings = settingsHelper.loadSettings();

       Injector injector = Guice.createInjector(new SettingsModule(settings), new LoggerModule(),
                                                new StorageModule(settings), new GPIOModule(), new SensorModule(),
                                                new NotifierModule());

        Aquabox aquabox = injector.getInstance(Aquabox.class);
        aquabox.start();
    }
}
