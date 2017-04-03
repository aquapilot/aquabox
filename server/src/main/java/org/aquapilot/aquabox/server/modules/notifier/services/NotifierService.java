/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.notifier.services;

import org.aquapilot.aquabox.server.common.Service;
import org.aquapilot.aquabox.server.modules.notifier.model.NewSensorDetectedNotification;

/**
 * This class manage notifications
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface NotifierService extends Service {

   /**
    * Publish a notification on a firebase queue
    *
    * @param notification
    */
   void notify(NewSensorDetectedNotification notification);

   void notifyNative();
}
