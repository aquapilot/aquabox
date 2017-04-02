/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.storage.services;

import org.aquapilot.aquabox.server.common.Service;
import org.aquapilot.aquabox.server.modules.storage.model.AquapilotIcon;
import org.aquapilot.aquabox.server.modules.storage.model.AquaticSystemType;
import org.aquapilot.aquabox.server.modules.storage.model.SensorType;

import java.util.Collection;

/**
 * Interface describing access to data storage
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface StorageService extends Service {

   /**
    * Save an aquatic system. It could be a simple aquarium, an aquaponic system
    *
    * @param name the displayed name of this system. (Displayed in the mobile app)
    * @param type describe the kind of system that it is (used later when configuring the sensors. If you choosed an
    *             aquaponic system, then it will give you advice according to this kind of aquatic system).
    */
   void saveAquaticSystem(String name, AquaticSystemType type);

   /**
    * Save a sensor
    *
    * @param UUID unique identifier of the sensor (stored in sensor EPROM)
    * @param name displayed name
    * @param type describe the kind of sensor (what does it measures
    * @param icon an icon associated with this sensor
    */
   void saveSensor(String UUID, String name, SensorType type, AquapilotIcon icon);

   /**
    * Save a sensor measuring more than on thing. (ie: A moisture + temperature sensor)
    *
    * @param UUID  unique identifier of the sensor (stored in sensor EPROM)
    * @param name  displayed name
    * @param types define what kind of measures does this sensor provide
    * @param icon  an icon associated with this sensor
    */
   void saveSensor(String UUID, String name, Collection<SensorType> types, AquapilotIcon icon);

   /**
    * Save a sensor measure
    *
    * @param sensorUUID unique identifier of the sensor
    * @param value      measured value
    */
   void saveMeasure(String sensorUUID, String value);
}
