/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.plugins;

import org.aquapilot.aquabox.api.PluginManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PluginManagerImplTest {

    private PluginManager pluginManager;
    private Path pluginsPath = new File("src/test/resources/plugins").toPath();

    @Before
    public void setUp() throws Exception {

        pluginManager = spy(new PluginManagerImpl());

    }

    @Test
    public void loadPlugins_shouldOnlyLoad2Plugins_whenOnly2ArePresent() throws Exception {

        // Given
        // 2 plugins jar are present in resources dir
        doReturn(null).when(pluginManager).loadPlugin(any());

        // When
        pluginManager.loadPlugins(pluginsPath);

        // Then
        verify(pluginManager, times(2)).loadPlugin(any());
    }

    @Test(expected = NullPointerException.class)
    public void loadPlugins_shouldThrowNullPointerException_whenANullPathIsGiven(){
        pluginManager.loadPlugins(null);
    }

    @Test
    public void loadPlugin_shouldVerifyTheGivenFileExists(){

    }

}