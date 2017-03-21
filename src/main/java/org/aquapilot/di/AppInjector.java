/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.di;

import com.google.inject.AbstractModule;
import org.aquapilot.di.services.sensor.RF433ServiceImpl;
import org.aquapilot.di.services.sensor.SensorService;
import org.aquapilot.di.services.storage.FirebaseServiceImpl;
import org.aquapilot.di.services.storage.StorageService;

/**
 * This class define which service implementation to  use for each guice di declared service.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class AppInjector extends AbstractModule {
    @Override
    protected void configure() {
        //bind service -> implementation
        bind(StorageService.class).to(FirebaseServiceImpl.class);
        bind(SensorService.class).to(RF433ServiceImpl.class);
    }
}
