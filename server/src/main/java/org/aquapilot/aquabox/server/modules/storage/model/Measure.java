/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.storage.model;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class Measure {

   public String uuid;
   public String value;

   public Measure(String uuid, String value) {

      this.uuid = uuid;
      this.value = value;
   }

}
