package org.aquapilot.aquabox.common.version;

import org.aquapilot.aquabox.Aquabox;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by vermeille on 31.03.2017.
 */
public class VersionHelperImpl implements VersionHelper {

   private static final String VERSION_FILE = "./version.txt";
   private static final String VERSION = "version";
   private static final String BUILD_ID = "buildId";

   public VersionHelperImpl() {

   }

   @Override
   public String getVersion() {

      return loadProperties().getProperty(VERSION);
   }

   @Override
   public String getBuildId() {

      return loadProperties().getProperty(BUILD_ID);
   }

   private Properties loadProperties() {

      Properties prop = new Properties();
      InputStream input = null;
      try {
         input = Aquabox.class.getClassLoader().getResourceAsStream(VERSION_FILE);

         // load a properties file
         prop.load(input);

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
         return prop;
      }
   }
}
