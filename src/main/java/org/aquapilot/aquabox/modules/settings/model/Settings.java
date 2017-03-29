/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.settings.model;

import org.aquapilot.aquabox.modules.storage.SupportedDatabase;

import javax.annotation.Generated;

public class Settings {

   private final SupportedDatabase database;  // required
   private final String databaseName;
   private final String databaseUser;
   private final String databasePassword;

   private Settings(SupportedDatabase database, String databaseName, String databaseUser, String databasePassword) {

      this.database = database;
      this.databaseName = databaseName;
      this.databaseUser = databaseUser;
      this.databasePassword = databasePassword;
   }

   public static DatabaseStep newInstance() {

      return new Builder();
   }

   public SupportedDatabase getDatabase() {

      return database;
   }

   public String getDatabaseName() {

      return databaseName;
   }

   public String getDatabaseUser() {

      return databaseUser;
   }

   public String getDatabasePassword() {

      return databasePassword;
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface DatabaseStep {

      DatabaseNameStep database(SupportedDatabase database);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface DatabaseNameStep {

      DatabaseUserStep databaseName(String databaseName);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface DatabaseUserStep {

      DatabasePasswordStep databaseUser(String databaseUser);
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
   private static final class Builder
         implements DatabaseStep, DatabaseNameStep, DatabaseUserStep, DatabasePasswordStep, FinalStep {

      private SupportedDatabase database;
      private String databaseName;
      private String databaseUser;
      private String databasePassword;

      public DatabaseNameStep database(SupportedDatabase database) {

         this.database = database;
         return this;
      }

      public DatabaseUserStep databaseName(String databaseName) {

         this.databaseName = databaseName;
         return this;
      }

      public DatabasePasswordStep databaseUser(String databaseUser) {

         this.databaseUser = databaseUser;
         return this;
      }

      public FinalStep databasePassword(String databasePassword) {

         this.databasePassword = databasePassword;
         return this;
      }

      public Settings build() {

         Settings theObject = new Settings(database, databaseName, databaseUser, databasePassword);
         return theObject;
      }
   }
}
