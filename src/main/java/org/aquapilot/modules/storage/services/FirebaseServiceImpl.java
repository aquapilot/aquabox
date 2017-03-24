/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.modules.storage.services;

import javax.inject.Singleton;

/**
 * This class implement StorageService with Firebase
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class FirebaseServiceImpl implements StorageService {

   public FirebaseServiceImpl() {

      // log.debug("Initialize FirebaseServiceImpl");
   }

    @Override
    public void saveAquaticSystem() {
        // TODO: implement it
    }
}
