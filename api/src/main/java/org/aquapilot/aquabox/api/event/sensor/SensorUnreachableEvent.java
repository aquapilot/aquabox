/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api.event.sensor;

/**
 * Event fired when a sensor doesn't respond any more.
 * <p>Example: its battery is down</p>
 * TODO: define a timeout and document it
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface SensorUnreachableEvent extends SensorEvent {

   /**
    * Return the concerned sensor unique id
    *
    * @return
    */
   String getUUID();

}
