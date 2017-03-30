/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.gpio.event;

import com.pi4j.io.gpio.Pin;

import javax.annotation.Generated;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class StateChangedEvent {

   private final PinState oldState;
   private final PinState newState;
   private final Pin pin;

   private StateChangedEvent(PinState oldState, PinState newState, Pin pin) {

      this.oldState = oldState;
      this.newState = newState;
      this.pin = pin;
   }

   public static OldStateStep newInstance() {

      return new Builder();
   }

   public PinState getOldState() {

      return this.oldState;
   }

   public PinState getNewState() {

      return this.newState;
   }

   public Pin getPin() {

      return this.pin;
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface OldStateStep {

      NewStateStep oldState(PinState oldState);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface NewStateStep {

      PinStep newState(PinState newState);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface PinStep {

      FinalStep pin(Pin pin);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface FinalStep {

      StateChangedEvent build();
   }

   @Generated(value = "Step Builder Generator Plugin")
   private static final class Builder implements OldStateStep, NewStateStep, PinStep, FinalStep {

      private PinState oldState;
      private PinState newState;
      private Pin pin;

      public NewStateStep oldState(PinState oldState) {

         this.oldState = oldState;
         return this;
      }

      public PinStep newState(PinState newState) {

         this.newState = newState;
         return this;
      }

      public FinalStep pin(Pin pin) {

         this.pin = pin;
         return this;
      }

      public StateChangedEvent build() {

         StateChangedEvent theObject = new StateChangedEvent(oldState, newState, pin);
         return theObject;
      }
   }
}
