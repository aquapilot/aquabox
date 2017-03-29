/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.settings;

import com.google.inject.AbstractModule;
import org.aquapilot.aquabox.modules.settings.model.Settings;

/**
 * This Module provide ability to manage settings
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SettingsModule extends AbstractModule {

   private Settings settings;

   public SettingsModule(final Settings settings) {

      this.settings = settings;
   }

   @Override
   protected void configure() {

      bind(Settings.class).annotatedWith(InjectSettings.class).toInstance(settings);
   }
}
