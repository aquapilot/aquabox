/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.settings;

import org.aquapilot.settings.model.Settings;

/**
 * This class define the settings module
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface SettingsHelper {

   void saveSettings();
   Settings loadSettings();

}
