/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.plugins;

import org.aquapilot.aquabox.api.JavaPlugin;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PluginManagerTest {

    private PluginManagerInterface pluginManager;
    private Path pluginsPath = new File("src/test/resources/plugins").toPath();

    @Before
    public void setUp() throws Exception {

        pluginManager = spy(new PluginManager());

    }

    @Test
    public void loadPlugins_shouldOnlyLoad2Plugins_whenOnly2ArePresent() throws Exception {

        // Given

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
    public void loadPlugin_should(){

    }

}