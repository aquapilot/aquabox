/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.gpio.services;

import com.pi4j.io.gpio.RaspiPin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class PI4JMockGPIOServiceImpl
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class PI4JMockGPIOServiceImplTest {

   private GPIOService gpioService;

   @Before
   public void before() {

      gpioService = new PI4JMockGPIOServiceImpl();
   }

   @Test
   public void getRegisteredInputPins_shouldContainsTwoRegisteredPin() throws Exception {

      // Given
      // done in before()

      // When
      gpioService.registerInputDigitalPin(RaspiPin.GPIO_00);
      gpioService.registerInputDigitalPin(RaspiPin.GPIO_02);

      // Then
      assertEquals(2, gpioService.getRegistredInputPins().size());
   }

   @Test
   public void getRegisteredInputPins_shouldNotRegisterTwiceTheSamePin() throws Exception {

      // Given
      // done in before()

      // When
      gpioService.registerInputDigitalPin(RaspiPin.GPIO_00);
      gpioService.registerInputDigitalPin(RaspiPin.GPIO_00);   // Same pin

      // Then
      assertEquals(1, gpioService.getRegistredInputPins().size());
   }

   @Test
   public void getRegisteredInputPins_shouldNotBeNull_whenNothingIsRegistered() {

      // Given
      gpioService = new PI4JMockGPIOServiceImpl();

      // When
      // nothing is done

      // Then
      assertNotNull(gpioService.getRegistredInputPins());
   }

   @Test
   public void getRegisteredInputPins_shouldBeEmpty_whenGPIOServiceIsInstanciated() {

      // Given
      gpioService = new PI4JMockGPIOServiceImpl();

      // When
      // nothing is done

      // Then
      assertEquals(0, gpioService.getRegistredInputPins().size());
   }

   @Test
   public void getRegisteredOutputPins_shouldContainsTwoRegisteredPin() throws Exception {

      // Given
      // done in before()

      // When
      gpioService.registerOutputDigitalPin(RaspiPin.GPIO_00);
      gpioService.registerOutputDigitalPin(RaspiPin.GPIO_02);

      // Then
      assertEquals(2, gpioService.getRegistredOutputPins().size());
   }

   @Test
   public void getRegisteredOutputPins_shouldNotRegisterTwiceTheSamePin() throws Exception {

      // Given
      // done in before()

      // When
      gpioService.registerOutputDigitalPin(RaspiPin.GPIO_00);
      gpioService.registerOutputDigitalPin(RaspiPin.GPIO_00);   // Same pin

      // Then
      assertEquals(1, gpioService.getRegistredOutputPins().size());
   }

   @Test
   public void getRegisteredOutputPins_shouldNotBeNull_whenNothingIsRegistered() {

      // Given
      gpioService = new PI4JMockGPIOServiceImpl();

      // When
      // nothing is done

      // Then
      assertNotNull(gpioService.getRegistredOutputPins());
   }

   @Test
   public void getRegisteredOutputPins_shouldBeEmpty_whenGPIOServiceIsInstanciated() {

      // Given
      gpioService = new PI4JMockGPIOServiceImpl();

      // When
      // nothing is done

      // Then
      assertEquals(0, gpioService.getRegistredOutputPins().size());
   }
}