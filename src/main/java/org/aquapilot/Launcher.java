/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.aquapilot.di.AppInjector;

/**
 * This class is the main class of Aquabox.
 * <p>
 * It launches the process according to given cli args
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class Launcher {

    public static final void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());
        Aquabox app = injector.getInstance(Aquabox.class);

        Environment environment = Environment.DEV;
        app.start(environment);
    }
}
