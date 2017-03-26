/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.sensors.event;

import javax.annotation.Generated;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorBatteryStatusEvent {

    private final String UUID;

    private SensorBatteryStatusEvent(String UUID) {
        this.UUID = UUID;
    }

    public static UUIDStep newInstance() {
        return new Builder();
    }

    public String getUUID() {
        return UUID;
    }

    @Generated(value = "Step Builder Generator Plugin")
    public interface UUIDStep {
        FinalStep UUID(String UUID);
    }

    @Generated(value = "Step Builder Generator Plugin")
    public interface FinalStep {
        SensorBatteryStatusEvent build();
    }

    @Generated(value = "Step Builder Generator Plugin")
    private static final class Builder implements UUIDStep, FinalStep {
        private String UUID;

        public FinalStep UUID(String UUID) {
            this.UUID = UUID;
            return this;
        }

        public SensorBatteryStatusEvent build() {
            SensorBatteryStatusEvent theObject = new SensorBatteryStatusEvent(UUID);
            return theObject;
        }
    }
}
