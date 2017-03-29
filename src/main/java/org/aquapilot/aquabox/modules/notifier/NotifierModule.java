/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.notifier;

import com.google.inject.AbstractModule;
import org.aquapilot.aquabox.modules.notifier.serices.NotifierFirebaseServiceImpl;
import org.aquapilot.aquabox.modules.notifier.serices.NotifierService;

/**
 * This Module provide ability to communicate with the raspberry pi GPIO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class NotifierModule extends AbstractModule {

    @Override
    protected void configure() {

       bind(NotifierService.class).to(NotifierFirebaseServiceImpl.class).asEagerSingleton();
    }
}
