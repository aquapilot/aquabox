/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.modules.settings;

public class PropertyFileSettingsServiceImplImpl implements SettingsService {

   @Override
   public void saveSettings() {

   }

   @Override
   public Settings loadSettings() {

      return Settings.newInstance()
            .database(SupportedDatabase.FIREBASE)
            .databaseUser("frank")
            .databasePassword("Dubosk")
            .build();
   }
}
