/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.settings;

import com.google.common.base.Enums;
import org.aquapilot.modules.storage.SupportedDatabase;
import org.aquapilot.settings.exception.InvalidConfigFileException;
import org.aquapilot.settings.model.Settings;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;

public class SettingsHelperImpl implements SettingsHelper {

   private static final String SETTINGS_FILE = "settings.ini";

   private static final String DATABASE = "database";
   private static final String TYPE = "type";
   private static final String USERNAME = "username";
   private static final String PASSWORD = "password";
   @Override
   public Settings loadSettings() {

      try {
         Ini ini = new Ini(new File(SETTINGS_FILE));

         //------------
         // Database
         //------------

         // type
         SupportedDatabase database = null;
         String databaseTypeString = ini.get(DATABASE, TYPE);

         if (!isNullOrEmpty(databaseTypeString) && Enums
               .getIfPresent(SupportedDatabase.class, databaseTypeString)
               .isPresent()) {
            database = Enums.getIfPresent(SupportedDatabase.class, databaseTypeString).get();
         } else {
            throw new InvalidConfigFileException("Database type is not defined");
         }

         // user
         Optional<String> databaseUsername = Optional.ofNullable(ini.get(DATABASE, USERNAME));

         // password
         Optional<String> databasePassword = Optional.ofNullable(ini.get(DATABASE, PASSWORD));

         return Settings
               .newInstance()
               .database(database)
               .databaseUsername(databaseUsername.orElse(""))
               .databasePassword(databasePassword.orElse(""))
               .build();

      } catch (IOException e) {
         throw new InvalidConfigFileException("Database type is not defined", e);
      }
   }
}
