/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api.event;

import com.pi4j.io.gpio.Pin;
import org.aquapilot.aquabox.api.PinState;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface StateChangedEvent extends AquaboxEvent {
    PinState getOldState();

    PinState getNewState();

    Pin getPin();
}
