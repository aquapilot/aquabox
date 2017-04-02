/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.settings.helper;

import com.google.common.base.Enums;
import org.aquapilot.aquabox.server.modules.notifier.SupportedNotifier;
import org.aquapilot.aquabox.server.modules.settings.exception.InvalidConfigFileException;
import org.aquapilot.aquabox.server.modules.settings.model.Settings;
import org.aquapilot.aquabox.server.modules.storage.SupportedDatabase;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;

public class SettingsHelperImpl implements SettingsHelper {

   private static final String SETTINGS_FILE = "./settings.ini";

   private static final String DATABASE = "database";
   private static final String TYPE = "type";
   private static final String USERNAME = "username";
   private static final String PASSWORD = "password";//NOSONAR: it is not a password value only a text 'password'
   private static final String NAME = "name";
   private static final String NOTIFIER = "notifier";

   @Override
   public Settings loadSettings() {

      try {
         Ini ini = new Ini(new File(SETTINGS_FILE));

         //------------
         // Database
         //------------

         // type
         SupportedDatabase database;
         String databaseTypeString = ini.get(DATABASE, TYPE);

         // TODO: ugly code need to be refactored
         if (!isNullOrEmpty(databaseTypeString) && Enums
               .getIfPresent(SupportedDatabase.class, databaseTypeString)
               .isPresent()) {
            database = Enums.getIfPresent(SupportedDatabase.class, databaseTypeString).get();
         } else {
            throw new InvalidConfigFileException("Database type is not defined");
         }

         // url
         Optional<String> databaseName = Optional.ofNullable(ini.get(DATABASE, NAME));

         // user
         Optional<String> databaseUsername = Optional.ofNullable(ini.get(DATABASE, USERNAME));

         // password
         Optional<String> databasePassword = Optional.ofNullable(ini.get(DATABASE, PASSWORD));

         //------------
         // Notifier
         //------------

         // type
         SupportedNotifier notifier;
         String notifierTypeString = ini.get(NOTIFIER, TYPE);

         // TODO: ugly code need to be refactored
         if (!isNullOrEmpty(notifierTypeString) && Enums
               .getIfPresent(SupportedNotifier.class, notifierTypeString)
               .isPresent()) {
            notifier = Enums.getIfPresent(SupportedNotifier.class, notifierTypeString).get();
         } else {
            throw new InvalidConfigFileException("Notifier type is not defined");
         }

         // url
         Optional<String> notifierName = Optional.ofNullable(ini.get(NOTIFIER, NAME));

         // user
         Optional<String> notifierUsername = Optional.ofNullable(ini.get(NOTIFIER, USERNAME));

         // password
         Optional<String> notifierPassword = Optional.ofNullable(ini.get(NOTIFIER, PASSWORD));

         return Settings
               .newInstance()
               .database(database)
               .databaseName(databaseName.orElse(""))
               .databaseUser(databaseUsername.orElse(""))
               .databasePassword(databasePassword.orElse(""))
               .notifierType(notifier)
               .notifierName(notifierName.orElse(""))
               .notifierUser(notifierUsername.orElse(""))
               .notifierPassword(notifierPassword.orElse(""))
               .build();

      } catch (IOException e) {
         throw new InvalidConfigFileException("Database type is not defined", e);
      }
   }
}
