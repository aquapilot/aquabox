/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.storage.services;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.aquapilot.aquabox.server.modules.settings.model.Settings;
import org.aquapilot.aquabox.server.modules.storage.model.AquapilotIcon;
import org.aquapilot.aquabox.server.modules.storage.model.AquaticSystemType;
import org.aquapilot.aquabox.server.modules.storage.model.Measure;
import org.aquapilot.aquabox.server.modules.storage.model.SensorType;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.FileInputStream;
import java.util.Collection;

/**
 * This class implement NotifierService with Firebase
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class FirebaseServiceImpl implements StorageService {

   @Log
   Logger log;

   private Settings settings;

   public FirebaseServiceImpl() {

   }

   @Inject
   public void setServices(Settings settings) {

      this.settings = settings;
   }

   @Override
   public void saveAquaticSystem(String name, AquaticSystemType type) {

   }

   @Override
   public void saveSensor(String UUID, String name, SensorType type, AquapilotIcon icon) {

   }

   @Override
   public void saveSensor(String UUID, String name, Collection<SensorType> types, AquapilotIcon icon) {

   }

   @Override
   public void saveMeasure(String uuid, String newValue) {

      System.out.println("Save new measure");
//      DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/aquabox");
//
//      ref.push().setValue(new Measure(uuid, newValue), (databaseError, databaseReference) -> {
//         if (databaseError != null) {
//            System.out.println("Data could not be saved " + databaseError.getMessage());
//         } else {
//            System.out.println("Data saved successfully.");
//         }
//      });
   }

   @Override
   public void start() throws Exception {

      this.log.debug(">> Start Firebase service storage");

      // TODO: should read settings
      String dbName = this.settings.getDatabaseName();
      String databaseUrl = "https://" + dbName + ".firebaseio.com";

      FileInputStream serviceAccount = new FileInputStream("./toremove.json");

      if (FirebaseApp.getApps().isEmpty()) {
         FirebaseOptions options = new FirebaseOptions.Builder()
               .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
               .setDatabaseUrl(databaseUrl)
               .build();
         FirebaseApp.initializeApp(options, FirebaseApp.DEFAULT_APP_NAME);
      }

   }

   @Override
   public void stop() {

      this.log.debug(">> Firebase service storage stopped");
   }
}
