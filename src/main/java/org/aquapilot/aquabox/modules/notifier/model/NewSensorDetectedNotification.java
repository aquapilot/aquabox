/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.notifier.model;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class NewSensorDetectedNotification implements Notification {

    public String sensorUUID;
    public NotificationType type;

    public NewSensorDetectedNotification(String sensorUUID) {
        this.sensorUUID = sensorUUID;
        this.type = NotificationType.NEW_SENSOR_DETECTED;
    }

}
