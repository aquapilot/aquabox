/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.modules.sensors;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class RF433ServiceImpl implements SensorService {

    public RF433ServiceImpl() {
        //    private void init() {
        //        this.initLed();
        //        this.initReceiver();
        //    }
        //
        //    private void initLed() {
        //        // provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
        //        GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
        //                "Status LED",           // PIN FRIENDLY NAME (optional)
        //                PinState.HIGH);      // PIN STARTUP STATE (optional)
        //
        //        myLed.blink(1000L);
        //    }
        //
        //    private void initReceiver() {
        //        // provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
        //        GpioPinDigitalInput rfReceiver = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,   // PIN NUMBER
        //                "RF Signal");      // PIN STARTUP STATE (optional)
        //
        //        // create and register gpio pin listener
        //        rfReceiver.addListener(new GpioPinListenerDigital() {
        //
        //            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        //                // display pin state on console
        //                System.out.println(" --> RECEIVER: " + event.getPin() + " = " + event.getState());
        //            }
        //
        //        });
        //
        //    }
    }

    @Override
    public void onReceiveMessage(String message) {

    }

    @Override
    public void sendMessage(String message) {

    }
}
