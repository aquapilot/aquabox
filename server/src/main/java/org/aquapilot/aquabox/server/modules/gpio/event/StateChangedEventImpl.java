/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.gpio.event;

import com.pi4j.io.gpio.Pin;
import org.aquapilot.aquabox.api.PinState;
import org.aquapilot.aquabox.api.event.StateChangedEvent;

import javax.annotation.Generated;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class StateChangedEventImpl implements StateChangedEvent {

   private final PinState oldState;
   private final PinState newState;
   private final Pin pin;

   private StateChangedEventImpl(PinState oldState, PinState newState, Pin pin) {

      this.oldState = oldState;
      this.newState = newState;
      this.pin = pin;
   }

   public static OldStateStep newInstance() {

      return new Builder();
   }

   @Override
   public PinState getOldState() {

      return this.oldState;
   }

   @Override
   public PinState getNewState() {

      return this.newState;
   }

   @Override
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

      StateChangedEventImpl build();
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

      public StateChangedEventImpl build() {

         StateChangedEventImpl theObject = new StateChangedEventImpl(oldState, newState, pin);
         return theObject;
      }
   }
}
