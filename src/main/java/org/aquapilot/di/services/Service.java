/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.di.services;

/**
 * This interface was introduced to follow google/guice good pratices guidelines
 * @see https://github.com/google/guice/wiki/ModulesShouldBeFastAndSideEffectFree
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface Service {
   /**
    * Starts the service. This method blocks until the service has completely started.
    */
   void start() throws Exception;

   /**
    * Stops the service. This method blocks until the service has completely shut down.
    */
   void stop();
}
