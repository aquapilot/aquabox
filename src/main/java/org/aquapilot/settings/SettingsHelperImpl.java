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
import com.google.common.base.Preconditions;
import org.aquapilot.settings.model.Settings;
import org.aquapilot.modules.storage.SupportedDatabase;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class SettingsHelperImpl implements SettingsHelper {

   private static final String SETTINGS_FILE = "settings.properties";

   @Override
   public void saveSettings() {

      Properties properties = new Properties();
      OutputStream output = null;
      try {

         output = new FileOutputStream(SETTINGS_FILE);

         // set the properties value
         properties.setProperty("database", "firebase");
         properties.setProperty("database-username", "b");
         properties.setProperty("database-password", "c");

         // save properties to project root folder
         properties.store(output, "Aquabox settings file");

      } catch (IOException io) {
         io.printStackTrace();
      } finally {
         if (output != null) {
            try {
               output.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }
   }

   @Override
   public Settings loadSettings() {

       Properties prop = new Properties();
       InputStream input = null;

       try {

           input = new FileInputStream(SettingsHelperImpl.SETTINGS_FILE);

           // load a properties file
           prop.load(input);

           // get the property value and print it out
           String databaseString = prop.getProperty("database", null).toUpperCase();
           if(Enums.getIfPresent(SupportedDatabase.class, databaseString).isPresent()){
              SupportedDatabase database = Enums.getIfPresent(SupportedDatabase.class, databaseString).get();

               Settings.newInstance()
                       .database(database)
                       .databaseUser("frank")
                       .databasePassword("Dubosk")
                       .build();

           } else {
               checkArgument(false, "Error in setting file, this database '%s' is not supported.", databaseString);
           }

           System.out.println(prop.getProperty("database-username"));
           System.out.println(prop.getProperty("database-password"));

       } catch (IOException ex) {
           ex.printStackTrace();
       } finally {
           if (input != null) {
               try {
                   input.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }

      return Settings.newInstance()
            .database(SupportedDatabase.FIREBASE)
            .databaseUser("frank")
            .databasePassword("Dubosk")
            .build();
   }
}
