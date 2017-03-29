/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.logger;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import org.aquapilot.aquabox.modules.logger.SLF4J.SLF4JTypeListener;

/**
 * This Module provide ability to inject easily an SLF4J logger everywhere.
 * <p>
 * To use it simply do : @Log Logger log; and your logger is ready to use
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class LoggerModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new SLF4JTypeListener());
    }
}
