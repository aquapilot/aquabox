package org.aquapilot.aquabox.server.modules.gpio.event;

import com.pi4j.io.gpio.Pin;
import org.aquapilot.aquabox.api.event.gpio.PinStateHighEvent;

import javax.annotation.Generated;

/**
 * Created by vermeille on 06.04.2017.
 */
public class PinStateHighEventImpl implements PinStateHighEvent {

   private final Pin pin;

   private PinStateHighEventImpl(Pin pin) {

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

      PinStateHighEventImpl build();
   }

   @Generated(value = "Step Builder Generator Plugin")
   private static final class Builder implements PinStep, FinalStep {

      private Pin pin;

      public FinalStep pin(Pin pin) {

         this.pin = pin;
         return this;
      }

      public PinStateHighEventImpl build() {

         PinStateHighEventImpl theObject = new PinStateHighEventImpl(pin);
         return theObject;
      }
   }
}
