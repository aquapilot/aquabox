/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.gpio.listener;

import org.aquapilot.aquabox.modules.gpio.event.StateChangedEvent;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface GPIOPinStateListener {

    void onPinStateChanged(StateChangedEvent event);

    default void onPinStateHigh() {
    }

    default void onPinStateLow() {
    }

}
