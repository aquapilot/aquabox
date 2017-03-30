/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.settings.model;

import org.aquapilot.aquabox.modules.notifier.SupportedNotifier;
import org.aquapilot.aquabox.modules.storage.SupportedDatabase;

import javax.annotation.Generated;

public class Settings {

   private final SupportedDatabase database;  // required
   private final String databaseName;
   private final String databaseUser;
   private final String databasePassword;
   private final SupportedNotifier notifierType;
   private final String notifierName;
   private final String notifierUser;
   private final String notifierPassword;

   private Settings(SupportedDatabase database, String databaseName, String databaseUser, String databasePassword,
         SupportedNotifier notifierType, String notifierName, String notifierUser, String notifierPassword) {

      this.database = database;
      this.databaseName = databaseName;
      this.databaseUser = databaseUser;
      this.databasePassword = databasePassword;
      this.notifierType = notifierType;
      this.notifierName = notifierName;
      this.notifierUser = notifierUser;
      this.notifierPassword = notifierPassword;
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

   public SupportedNotifier getNotifierType() {

      return notifierType;
   }

   public String getNotifierName() {

      return notifierName;
   }

   public String getNotifierUser() {

      return notifierUser;
   }

   public String getNotifierPassword() {

      return notifierPassword;
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

      NotifierTypeStep databasePassword(String databasePassword);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface NotifierTypeStep {

      NotifierNameStep notifierType(SupportedNotifier notifierType);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface NotifierNameStep {

      NotifierUserStep notifierName(String notifierName);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface NotifierUserStep {

      NotifierPasswordStep notifierUser(String notifierUser);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface NotifierPasswordStep {

      FinalStep notifierPassword(String notifierPassword);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface FinalStep {

      Settings build();
   }

   @Generated(value = "Step Builder Generator Plugin")
   private static final class Builder
         implements DatabaseStep, DatabaseNameStep, DatabaseUserStep, DatabasePasswordStep, NotifierTypeStep,
         NotifierNameStep, NotifierUserStep, NotifierPasswordStep, FinalStep {

      private SupportedDatabase database;
      private String databaseName;
      private String databaseUser;
      private String databasePassword;
      private SupportedNotifier notifierType;
      private String notifierName;
      private String notifierUser;
      private String notifierPassword;

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

      public NotifierTypeStep databasePassword(String databasePassword) {

         this.databasePassword = databasePassword;
         return this;
      }

      public NotifierNameStep notifierType(SupportedNotifier notifierType) {

         this.notifierType = notifierType;
         return this;
      }

      public NotifierUserStep notifierName(String notifierName) {

         this.notifierName = notifierName;
         return this;
      }

      public NotifierPasswordStep notifierUser(String notifierUser) {

         this.notifierUser = notifierUser;
         return this;
      }

      public FinalStep notifierPassword(String notifierPassword) {

         this.notifierPassword = notifierPassword;
         return this;
      }

      public Settings build() {

         Settings theObject = new Settings(database, databaseName, databaseUser, databasePassword, notifierType,
                                           notifierName, notifierUser, notifierPassword);
         return theObject;
      }
   }
}
