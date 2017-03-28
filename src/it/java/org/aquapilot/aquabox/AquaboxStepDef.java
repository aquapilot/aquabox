/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by vermeille on 28.03.2017.
 */
public class AquaboxStepDef {

   private final PrintStream stdout = System.out;
   private ExecutorService executor;
   private final ByteArrayOutputStream output = new ByteArrayOutputStream();
   private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

   @Before
   public void before() throws Exception {

      // Redirect System.out to a custom handler
      System.setOut(new PrintStream(output, true, "UTF-8"));
   }

   @Given("^I am a user$")
   public void IamAUser() {

   }

   @When("^I run the aquabox jar$")
   public void iRunTheAquaboxJar() {

      // Start the app in a thread so it doesnt block the testing process
      executor = Executors.newSingleThreadExecutor();
      executor.submit(() -> {
         try {
            System.setOut(new PrintStream(output, true, "UTF-8"));
         } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
         }
         Launcher.main(null);
      });

      try {
         TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
         // It is an expected behavior
      }
   }

   @Then("^I should see credits at startup$")
   public void iShouldSeeCreditsLogAtStartup() throws Throwable {

      String expected = "================================================================\n"
            + "     _                                      _   _           _   \n"
            + "    / \\      __ _   _   _    __ _   _ __   (_) | |   ___   | |_ \n"
            + "   / _ \\    / _` | | | | |  / _` | | '_ \\  | | | |  / _ \\  | __|\n"
            + "  / ___ \\  | (_| | | |_| | | (_| | | |_) | | | | | | (_) | | |_ \n"
            + " /_/   \\_\\  \\__, |  \\__,_|  \\__,_| | .__/  |_| |_|  \\___/   \\__|\n"
            + "               |_|                 |_|                          \n"
            + "(C) Sébastien Vermeille <sebastien.vermeille@gmail.com> \n" + "Distributed under MIT license \n"
            + "================================================================";

      assertTrue(output.toString().contains(expected));
   }

   @After
   public void after() {

      executor.shutdownNow();
      System.setOut(stdout);
   }

}
