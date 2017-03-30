/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.logger.slf4j;

import com.google.inject.MembersInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * This class inject SFL4J
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @link http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice
 */
public class SLF4JMembersInjector<T> implements MembersInjector<T> {

   private final Field field;
   private final Logger logger;

   public SLF4JMembersInjector(Field field) {

      this.field = field;
      this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
      field.setAccessible(true);
   }

   @Override
   public void injectMembers(T t) {

      try {
         this.field.set(t, this.logger);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }
}
