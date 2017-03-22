/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.modules.settings;

import com.google.inject.AbstractModule;
import org.aquapilot.di.services.storage.FirebaseServiceImpl;
import org.aquapilot.di.services.storage.StorageService;

/**
 * This class define the settings module
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SettingsModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(StorageService.class).to(FirebaseServiceImpl.class);
   }

}
