/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.gpio.listener;

import org.aquapilot.aquabox.api.event.gpio.PinStateChangedEvent;
import org.aquapilot.aquabox.api.listener.AquaboxListener;

/**
 * This class is intended to provide async call when something occurs on the selected pins
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface GPIOPinStateListener extends AquaboxListener {

   /**
    * Called when a pin changed state
    *
    * @param event
    */
   void onPinStateChanged(PinStateChangedEvent event);

   default void onPinStateHigh(PinStateHighEvent event) {

   }

   default void onPinStateLow(PinStateLowEvent event) {

   }

}
