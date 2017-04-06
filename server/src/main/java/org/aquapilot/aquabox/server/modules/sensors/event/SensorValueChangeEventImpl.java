/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.sensors.event;

import org.aquapilot.aquabox.api.event.sensor.SensorValueChangeEvent;

import javax.annotation.Generated;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorValueChangeEventImpl implements SensorValueChangeEvent {

   private final String UUID;
   private final String oldValue;
   private final String newValue;

   private SensorValueChangeEventImpl(String UUID, String oldValue, String newValue) {

      this.UUID = UUID;
      this.oldValue = oldValue;
      this.newValue = newValue;
   }

   public static UUIDStep newInstance() {

      return new Builder();
   }

   @Override
   public String getUUID() {

      return this.UUID;
   }

   @Override
   public String getOldValue() {

      return this.oldValue;
   }

   @Override
   public String getNewValue() {

      return this.newValue;
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface UUIDStep {

      OldValueStep UUID(String UUID);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface OldValueStep {

      NewValueStep oldValue(String oldValue);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface NewValueStep {

      FinalStep newValue(String newValue);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface FinalStep {

      SensorValueChangeEventImpl build();
   }

   @Generated(value = "Step Builder Generator Plugin")
   private static final class Builder implements UUIDStep, OldValueStep, NewValueStep, FinalStep {

      private String UUID;
      private String oldValue;
      private String newValue;

      public OldValueStep UUID(String UUID) {

         this.UUID = UUID;
         return this;
      }

      public NewValueStep oldValue(String oldValue) {

         this.oldValue = oldValue;
         return this;
      }

      public FinalStep newValue(String newValue) {

         this.newValue = newValue;
         return this;
      }

      public SensorValueChangeEventImpl build() {

         SensorValueChangeEventImpl theObject = new SensorValueChangeEventImpl(UUID, oldValue, newValue);
         return theObject;
      }
   }
}
