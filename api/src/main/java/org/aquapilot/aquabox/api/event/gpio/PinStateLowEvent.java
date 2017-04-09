/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api.event.gpio;

import com.pi4j.io.gpio.Pin;

/**
 * Event fired when a GPIO pin state changed from high to low.
 * <p>Note: it should be a registred pin else the aquabox will not care about it.</p>
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface PinStateLowEvent extends GpioEvent {

   /**
    * Return the pin that have a new state
    *
    * @return
    */
   Pin getPin();
}
