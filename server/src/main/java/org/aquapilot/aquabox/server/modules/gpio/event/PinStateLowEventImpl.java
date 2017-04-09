package org.aquapilot.aquabox.server.modules.gpio.event;

import com.pi4j.io.gpio.Pin;
import org.aquapilot.aquabox.api.event.gpio.PinStateLowEvent;

import javax.annotation.Generated;

/**
 * Created by vermeille on 06.04.2017.
 */
public class PinStateLowEventImpl implements PinStateLowEvent {

   private final Pin pin;

   private PinStateLowEventImpl(Pin pin) {

      this.pin = pin;
   }

   public static PinStep newInstance() {

      return new Builder();
   }

   public Pin getPin() {

      return pin;
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface PinStep {

      FinalStep pin(Pin pin);
   }

   @Generated(value = "Step Builder Generator Plugin")
   public interface FinalStep {

      PinStateLowEventImpl build();
   }

   @Generated(value = "Step Builder Generator Plugin")
   private static final class Builder implements PinStep, FinalStep {

      private Pin pin;

      public FinalStep pin(Pin pin) {

         this.pin = pin;
         return this;
      }

      public PinStateLowEventImpl build() {

         PinStateLowEventImpl theObject = new PinStateLowEventImpl(pin);
         return theObject;
      }
   }
}
