/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot;

import org.aquapilot.common.CreditsUtil;
import org.aquapilot.common.SystemUtil;
import org.aquapilot.di.services.sensor.SensorService;
import org.aquapilot.modules.storage.services.StorageService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class is the main class of Aquabox.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class Aquabox {

    private StorageService storageService;
    private SensorService sensorService;

    //setter method injector
    @Inject
    public void setServices(StorageService storageService, SensorService sensorService){
        this.storageService=storageService;
        this.sensorService=sensorService;
    }

    /**
     * Start the aquabox
     *
     */
    public void start() {
       init();

    }

    public void init() {
        CreditsUtil.printCredits();
        SystemUtil.checkSystem();
    }
}
