/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * This Module manage GuavaEventBus
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class EventBusModule extends AbstractModule {

   private final EventBus eventBus = new EventBus("Default EventBus");

   @Override
   protected void configure() {

      bind(EventBus.class).toInstance(eventBus);
      bindListener(Matchers.any(), new TypeListener() {

         public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {

            typeEncounter.register((InjectionListener<I>) i -> eventBus.register(i));
         }
      });
   }
}
