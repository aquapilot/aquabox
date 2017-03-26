/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.gpio.services;

import com.pi4j.io.gpio.*;
import com.pi4j.io.spi.SpiDevice;
import org.aquapilot.aquabox.modules.gpio.listener.GPIOPinStateListener;

import java.util.Collection;

/**
 * This class provide simple way to manage PI4J in Aquabox
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PI4JGPIOServiceImpl implements GPIOService {

    private final static GpioController gpio = GpioFactory.getInstance();

    public GpioController getGPIOController() {
        return gpio;
    }

    @Override
    public GpioPinDigitalInput registerInputDigitalPin(Pin pin) {
        return null;
    }

    @Override
    public GpioPinDigitalOutput registerOutputDigitalPin(Pin pin) {
        return null;
    }

    @Override
    public Collection<Pin> getRegistredInputPins() {
        return null;
    }

    @Override
    public Collection<Pin> getRegistredOutputPins() {
        return null;
    }

    @Override
    public void registerChangeListener(Pin pin, GPIOPinStateListener listener) {

    }

    @Override
    public SpiDevice getSPI() {

        return null;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() {
        gpio.shutdown();    // stop all GPIO activity/threads by shutting down the GPIO controller
    }
}
