/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.gpio.services;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.spi.SpiDevice;
import org.aquapilot.aquabox.common.Service;
import org.aquapilot.aquabox.modules.gpio.listener.GPIOPinStateListener;

import java.util.Collection;

/**
 * This interface describe how the GPIO of the raspberry PI can be accessed trough Aquabox
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface GPIOService extends Service {

    /**
     * Define a pin as input
     *
     * @param pin
     */
    GpioPinDigitalInput registerInputDigitalPin(Pin pin);

    /**
     * Define a pin as output
     *
     * @param pin
     */
    GpioPinDigitalOutput registerOutputDigitalPin(Pin pin);

    /**
     * Return a collection of registred input pins
     *
     * @return
     */
    Collection<Pin> getRegistredInputPins();

    /**
     * Return a collection of registred output pins
     *
     * @return
     */
    Collection<Pin> getRegistredOutputPins();

    void registerChangeListener(Pin pin, GPIOPinStateListener listener);

    SpiDevice getSPI();
}
