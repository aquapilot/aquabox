/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.modules.storage;

import org.aquapilot.modules.storage.services.FirebaseServiceImpl;
import org.aquapilot.modules.storage.services.StorageService;

/**
 * This enum list supported databases for aquabox data storage.
 * A service implementation is always linked with the enum key so that guice can manage that automatically for us
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public enum SupportedDatabase {
   FIREBASE(FirebaseServiceImpl.class);

   private Class<? extends StorageService> clazz;

   SupportedDatabase(Class<? extends StorageService> clazz){
      this.clazz = clazz;
   }

   public Class<? extends StorageService> getAssociatedServiceClass(){
      return this.clazz;
   }
}
