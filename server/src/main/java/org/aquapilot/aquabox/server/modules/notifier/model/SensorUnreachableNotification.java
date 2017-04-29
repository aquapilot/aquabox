/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.notifier.model;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorUnreachableNotification implements Notification {

   public String sensorUUID;
   public NotificationType type;

   public SensorUnreachableNotification(String sensorUUID) {

      this.sensorUUID = sensorUUID;
      this.type = NotificationType.SENSOR_UNREACHABLE;
   }

}
