/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox;

import com.pi4j.io.gpio.RaspiPin;
import org.aquapilot.aquabox.common.CreditsUtil;
import org.aquapilot.aquabox.common.SystemUtil;
import org.aquapilot.aquabox.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.modules.notifier.model.NewSensorDetectedNotification;
import org.aquapilot.aquabox.modules.notifier.serices.NotifierService;
import org.aquapilot.aquabox.modules.sensors.SensorService;
import org.aquapilot.aquabox.modules.sensors.event.SensorDetectedEvent;
import org.aquapilot.aquabox.modules.sensors.event.SensorValueChangeEvent;
import org.aquapilot.aquabox.modules.sensors.listener.SensorListener;
import org.aquapilot.aquabox.modules.storage.services.StorageService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class is the main class of Aquabox.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class Aquabox {

    private GPIOService gpioService;
    private StorageService storageService;
    private SensorService sensorService;
    private NotifierService notifierService;

    private static boolean started = false;

    //setter method injector
    @Inject
    public void setServices(StorageService storageService, SensorService sensorService, GPIOService gpioService,
                            NotifierService notifierService) {
        this.storageService = storageService;
        this.sensorService = sensorService;
        this.gpioService = gpioService;
        this.notifierService = notifierService;
    }

    /**
     * Start the aquabox
     */
    public void start() {
        init();

        started = true;
        do {
            System.out.println(".");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                started = false;
            }

        }
        while (started);

        if (!started) {
            this.stop();
        }
    }

    /**
     * Stop the aquabox
     */
    public void stop() {
        gpioService.stop();
    }

    public void init() {
        CreditsUtil.printCredits();
        SystemUtil.checkSystem();

        try {
            storageService.start();
            notifierService.start();
            gpioService.start();
            gpioService.registerInputDigitalPin(RaspiPin.GPIO_00); // RF Sensor data
            gpioService.registerOutputDigitalPin(RaspiPin.GPIO_05); // Status rgb led

            // gpioService.registerChangeListener(RaspiPin.GPIO_00, event -> System.out.println("state of this pin changed !!!"));

            //   System.out.println("registred input pin amount: " + gpioService.getRegistredInputPins().size());
            //   System.out.println("registred output pin amount: " + gpioService.getRegistredOutputPins().size());

            sensorService.start();

            sensorService.registerListener(new SensorListener() {
                @Override
                public void onSensorValueChange(SensorValueChangeEvent event) {

                    System.out.println("=====================");
                    System.out.println(event.getUUID());
                    System.out.println(event.getOldValue());
                    System.out.println(event.getNewValue());
                    System.out.println("=====================");
                    System.out.println();

                    //         storageService.saveMeasure(event.getUUID(), event.getNewValue());
                }

                @Override
                public void onNewSensorDetected(SensorDetectedEvent event) {
                    System.out.println("===================");
                    System.out.println("(INFO) New sensor detected");
                    System.out.println("====================");
                    System.out.println();

                    // Notify firebase
                    notifierService.notify(new NewSensorDetectedNotification(event.getUUID()));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
