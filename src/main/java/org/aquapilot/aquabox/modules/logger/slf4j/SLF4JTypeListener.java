/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.logger.slf4j;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.aquapilot.aquabox.modules.logger.Log;
import org.slf4j.Logger;

import java.lang.reflect.Field;

/**
 * This class tell guice when to inject our Logger (basically it search all fields annotated with our @Log annotation)
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * @link http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice
 */
public class SLF4JTypeListener implements TypeListener {

   @Override
   public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {

      for (Field field : typeLiteral.getRawType().getDeclaredFields()) {
         if (field.getType() == Logger.class && field.isAnnotationPresent(Log.class)) {
            typeEncounter.register(new SLF4JMembersInjector<>(field));
         }
      }
   }

}
