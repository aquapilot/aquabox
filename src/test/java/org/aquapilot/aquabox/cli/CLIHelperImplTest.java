/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.cli;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Test Class
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class CLIHelperImplTest {

    @Test
    public void CLIHelperImpl_shouldNotThrowException_whenArgsIsNull() {
        new CLIHelperImpl(null);
    }

    @Test
    public void CLIHelperImpl_shouldNotThrowException_whenArgsIsAnEmptyArray() {
        String[] args = new String[0];
        new CLIHelperImpl(args);
    }

    @Test
    public void CLIHelperImpl_shouldShowHelp_whenRequested() throws Exception {

        String[] args = new String[1];
        args[0] = "-h";

        CLIHelperImpl cliHelper = spy(new CLIHelperImpl(args));

        doCallRealMethod().when(cliHelper).showHelp();

        verify(cliHelper, times(1)).showHelp();
    }

    @Test
    public void showVersion() throws Exception {

    }

    @Test
    public void isDebugEnabled() throws Exception {

    }

}