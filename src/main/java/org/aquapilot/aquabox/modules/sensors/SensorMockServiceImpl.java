/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.sensors;

import com.pi4j.io.spi.SpiDevice;
import org.aquapilot.aquabox.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.modules.logger.Log;
import org.aquapilot.aquabox.modules.sensors.event.SensorBatteryStatusEvent;
import org.aquapilot.aquabox.modules.sensors.event.SensorDetectedEvent;
import org.aquapilot.aquabox.modules.sensors.event.SensorUnreachableEvent;
import org.aquapilot.aquabox.modules.sensors.event.SensorValueChangeEvent;
import org.aquapilot.aquabox.modules.sensors.listener.SensorListener;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class manage the whole interactions between sensors and storage.
 * <p>
 * Basically it will communicate with the sensors
 * retrieve their values and save them to the specified storage engine.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorMockServiceImpl implements SensorService {

    private GPIOService gpioService;

    List<SensorListener> listeners = new ArrayList<>();

    private ExecutorService executor;

    @Log
    Logger log;

    //setter method injector
    @Inject
    public void setServices(GPIOService gpioService) {
        this.gpioService = gpioService;
    }

    private int randomBetween(int min, int max) {
        Random r = new Random();
        int result = r.nextInt(max - min) + min;
        return result;
    }

    @Override
    public void start() throws Exception {
        log.info("TESTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        SpiDevice spi = gpioService.getSPI();
        System.out.println(">> Sensor Service started");
        executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

            List<String> registredUUIDs = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                registredUUIDs.add(UUID.randomUUID().toString());
            }
            while (true) {

                TimeUnit.SECONDS.sleep(8);

                // generate random receptions
                for (SensorListener listener : this.listeners) {

                    switch (randomBetween(0, 10)) {
                        case 0:
                            SensorDetectedEvent event = SensorDetectedEvent.newInstance().UUID(UUID.randomUUID().toString()).build();
                            listener.onNewSensorDetected(event);
                            break;
                        case 1:
                            SensorBatteryStatusEvent sensorBatteryStatusEvent = SensorBatteryStatusEvent.newInstance().UUID(registredUUIDs.get(randomBetween(0, 2))).build();
                            listener.onSensorSendBatteryStatus(sensorBatteryStatusEvent);
                            break;
                        case 2:
                            SensorUnreachableEvent sensorUnreachableEvent = SensorUnreachableEvent.newInstance().UUID(registredUUIDs.get(randomBetween(0, 2))).build();
                            listener.onSensorUnreachable(sensorUnreachableEvent);
                            break;
                        default:
                            SensorValueChangeEvent sensorValueChangeEvent = SensorValueChangeEvent.newInstance().UUID(registredUUIDs.get(randomBetween(0, 2))).oldValue("15.0").newValue("22.4").build();
                            listener.onSensorValueChange(sensorValueChangeEvent);
                            break;
                    }

                }

            }
        });

    }

    @Override
    public void stop() {
        if (executor.isShutdown()) {
            return;
        }

        executor.shutdownNow();
        System.out.println(">> Sensor service stopped");
    }

    @Override
    public void registerListener(SensorListener listener) {
        listeners.add(listener);
    }
}
