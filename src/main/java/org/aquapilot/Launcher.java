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
import com.google.inject.Key;
import org.aquapilot.di.services.Service;
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

    public static final void main(String[] args) {

        SettingsHelper settingsHelper = new SettingsHelperImpl();
settingsHelper.saveSettings();
        Settings settings = settingsHelper.loadSettings();
        Injector injector = Guice.createInjector(new StorageModule(settings));

    }
}
