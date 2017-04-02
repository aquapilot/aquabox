/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.notifier;

import org.aquapilot.aquabox.server.modules.notifier.serices.NotifierFirebaseServiceImpl;
import org.aquapilot.aquabox.server.modules.notifier.serices.NotifierService;

/**
 * This enum list supported notifier for aquabox notifications.
 * A service implementation is always linked with the enum key so that guice can manage that automatically for us
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public enum SupportedNotifier {
   FIREBASE(NotifierFirebaseServiceImpl.class);

   private Class<? extends NotifierService> clazz;

   SupportedNotifier(Class<? extends NotifierService> clazz) {

      this.clazz = clazz;
   }

   public Class<? extends NotifierService> getAssociatedServiceClass() {

      return this.clazz;
   }
}
