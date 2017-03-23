/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.modules.gpio.services;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

import java.util.Collection;

/**
 * This class provide simple way to manage PI4J in Aquabox
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PI4JMockGPIOServiceImpl implements GPIOService {

    private final static GpioController gpio = null;

    public GpioController getGPIOController() {
        return gpio;
    }

    @Override
    public GpioPinDigitalInput registerInputDigitalPin(RaspiPin pin) {
        return null;
    }

    @Override
    public GpioPinDigitalOutput registerOutputDigitalPin(RaspiPin pin) {
        return null;
    }

    @Override
    public Collection<RaspiPin> getRegistredInputPins() {
        return null;
    }

    @Override
    public Collection<RaspiPin> getRegistredOutputPins() {
        return null;
    }
}
