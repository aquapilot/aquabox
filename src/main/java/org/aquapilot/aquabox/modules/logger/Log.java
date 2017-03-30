/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.logger;

import javax.inject.Scope;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation provide a simple way to inject the logger
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @link http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice
 */

@Scope
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Log {

}
