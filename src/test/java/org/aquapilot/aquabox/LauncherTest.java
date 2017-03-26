/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This class test Launcher class
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class LauncherTest {

    private static InputStream original;
    private static FileInputStream fips;

    @BeforeClass
    public static void before() throws FileNotFoundException {
//        original = original = System.in;
//        fips = new FileInputStream(new File("tmp.log"));
//        System.setIn(fips);
    }

    @AfterClass
    public static void after() {
//        System.setIn(original);
    }

    @Test
    @Ignore
    public void main_shouldNotThrowException_whenGivenNullArgs() {
        String[] args = null;
        Launcher.main(args);
    }

}