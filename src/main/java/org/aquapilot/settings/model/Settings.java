/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.settings.model;


import org.aquapilot.modules.storage.SupportedDatabase;

import javax.annotation.Generated;

public class Settings {

   private final SupportedDatabase database;  // required
   private final String databaseUser;
   private final String databasePassword;

   private Settings(SupportedDatabase database, String databaseUser, String databasePassword) {

      this.database = database;
      this.databaseUser = databaseUser;
      this.databasePassword = databasePassword;
   }

   public static DatabaseStep newInstance() {

      return new Builder();
   }

   public SupportedDatabase getDatabase() {

      return database;
   }

   public String getDatabaseUser() {

      return databaseUser;
   }

   public String getDatabasePassword() {

      return databasePassword;
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface DatabaseStep {

      DatabaseUserStep database(SupportedDatabase database);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface DatabaseUserStep {

      DatabasePasswordStep databaseUsername(String databaseUser);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface DatabasePasswordStep {

      FinalStep databasePassword(String databasePassword);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface FinalStep {

      Settings build();
   }

   @Generated(value = "Step Builder Generator Plugin")
   private static final class Builder implements DatabaseStep, DatabaseUserStep, DatabasePasswordStep, FinalStep {

      private SupportedDatabase database;
      private String databaseUser;
      private String databasePassword;

      public DatabaseUserStep database(SupportedDatabase database) {

         this.database = database;
         return this;
      }

      public DatabasePasswordStep databaseUsername(String databaseUser) {

         this.databaseUser = databaseUser;
         return this;
      }

      public FinalStep databasePassword(String databasePassword) {

         this.databasePassword = databasePassword;
         return this;
      }

      public Settings build() {

         Settings theObject = new Settings(database, databaseUser, databasePassword);
         return theObject;
      }
   }
}
