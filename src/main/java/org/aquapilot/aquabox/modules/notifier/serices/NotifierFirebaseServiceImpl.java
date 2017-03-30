/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.notifier.serices;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.aquapilot.aquabox.modules.logger.Log;
import org.aquapilot.aquabox.modules.notifier.model.NewSensorDetectedNotification;
import org.aquapilot.aquabox.modules.settings.model.Settings;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.FileInputStream;

/**
 * This class implement NotifierService with Firebase
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class NotifierFirebaseServiceImpl implements NotifierService {

   @Log
   Logger log;

   private Settings settings;

   public NotifierFirebaseServiceImpl() {

   }

   @Inject
   public void setServices(Settings settings) {

      this.settings = settings;
   }

   @Override
   public void start() throws Exception {

      if (FirebaseApp.getApps().isEmpty()) {
         // TODO: should read settings
         String dbName = this.settings.getNotifierName();
         String databaseUrl = "https://" + dbName + ".firebaseio.com";

         FileInputStream serviceAccount = new FileInputStream("./toremove.json");

         FirebaseOptions options = new FirebaseOptions.Builder()
               .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
               .setDatabaseUrl(databaseUrl)
               .build();
         FirebaseApp.initializeApp(options, FirebaseApp.DEFAULT_APP_NAME);
      }
      this.log.debug(">>> FirebaseNotifier service started");
   }

   @Override
   public void stop() {

      this.log.debug(">>> Firebase service notifier stopped");
   }

   @Override
   public void notify(NewSensorDetectedNotification notification) {

      System.out.println("Send notification in firebase queue");
      DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/notifications");

      ref.push().setValue(notification, (databaseError, databaseReference) -> {
         if (databaseError != null) {
            System.out.println("Data could not be saved " + databaseError.getMessage());
         } else {
            System.out.println("Data saved successfully.");
         }
      });
   }

   @Override
   public void notifyNative() {

   }
}
