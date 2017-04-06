/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.sensors.event;

import org.aquapilot.aquabox.api.event.sensor.SensorBatteryStatusEvent;

import javax.annotation.Generated;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorBatteryStatusEventImpl implements SensorBatteryStatusEvent {

   private final String UUID;

   private SensorBatteryStatusEventImpl(String UUID) {

      this.UUID = UUID;
   }

   public static UUIDStep newInstance() {

      return new Builder();
   }

   public String getUUID() {

      return this.UUID;
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface UUIDStep {

      FinalStep UUID(String UUID);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface FinalStep {

      SensorBatteryStatusEventImpl build();
   }

   @Generated(value = "Step Builder Generator Plugin")
   private static final class Builder implements UUIDStep, FinalStep {

      private String UUID;

      public FinalStep UUID(String UUID) {

         this.UUID = UUID;
         return this;
      }

      public SensorBatteryStatusEventImpl build() {

         SensorBatteryStatusEventImpl theObject = new SensorBatteryStatusEventImpl(UUID);
         return theObject;
      }
   }
}
