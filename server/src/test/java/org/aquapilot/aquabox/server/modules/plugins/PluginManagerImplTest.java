/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.plugins;

import org.aquapilot.aquabox.api.PluginManager;
import org.aquapilot.aquabox.api.exception.InvalidPluginException;
import org.aquapilot.aquabox.server.modules.plugins.manager.PluginManagerImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Test(expected = NullPointerException.class)
    public void loadPlugins_shouldThrowNullPointerException_whenANullPathIsGiven() throws Exception{
        pluginManager.loadPlugins(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void loadPlugins_shouldThrowAnException_whenTheGivenPathDoesntExists() throws Exception{
        // Given
        Path wrongPath = new File("src/test/resources/wrongdir").toPath();

        // When
        pluginManager.loadPlugins(wrongPath);

        // Then
        // should throw an exception
    }

    @Test(expected = FileNotFoundException.class)
    public void loadPlugins_shouldThrowAnException_whenTheGivenPathIsNotADirectory() throws Exception{
        // Given
        Path filePath = new File("src/test/resources/plugins/textfile.txt").toPath();

        // When
        pluginManager.loadPlugins(filePath);

        // Then
        // should throw an exception
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

    @Test
    public void getPluginDescriptor_shouldReturnAPluginDescriptor_withACorrectlyWrittenPlugin() throws Exception {

        // Given
        File goodPlugin = new File("src/test/resources/plugins/StatusLed.jar");

        // When
        pluginManager.getPluginDescriptor(goodPlugin);

        // Then
        // Nothing wrong should happend
    }

    @Test
    public void loadPlugin_shouldThrowException_whenTheFileDoesntExists() throws Exception {

        // Given
        File unexistingFile = new File("src/test/resources/plugins/doesnt-exist.jar");

        // When
        pluginManager.loadPlugin(unexistingFile);

        // Then
        // it should throw an exception
        fail();
    }

    @Test
    public void loadPlugin_shouldThrowException_whenTheGivenFileIsADirectory() throws Exception {

        // Given
        File directory = new File("src/test/resources/plugins");

        // When
        pluginManager.loadPlugin(directory);

        // Then
        // it should throw an exception
        fail();

    }

    @Test
    public void loadPlugin_shouldThrowException_whenTheGivenFilenameDoesntEndWithJar() throws Exception {

        // Given
        File textFile = new File("src/test/resources/plugins/wrong.txt");

        // When
        pluginManager.loadPlugin(textFile);

        // Then
        // it should throw an exception
        fail();

    }


    @Test(expected = InvalidPluginException.class)
    public void loadPlugin_shouldThrowException_whenTheGivenPluginDoesntHaveAPluginINIFile() throws Exception {

        // Given
        File plugin = new File("src/test/resources/plugins/plugin-without-pluginini.jar");

        // When
        pluginManager.loadPlugin(plugin);

        // Then
        // it should throw an exception
    }

    @Test(expected = InvalidPluginException.class)
    public void loadPlugin_shouldThrowException_whenTheGivenPluginDoesntHaveAMainClassDefined() throws Exception {

        // Given
        File textFile = new File("src/test/resources/plugins/plugin-without-mainclassdeclared.jar");

        // When
        pluginManager.loadPlugin(textFile);

        // Then
        // it should throw an exception
    }

    @Test(expected = InvalidPluginException.class)
    public void loadPlugin_shouldThrowException_whenTheGivenPluginDoesntHaveAMainClassExtendingJavaPlugin() throws Exception {

        // Given
        File textFile = new File("src/test/resources/plugins/invalidPlugin.jar");

        // When
        pluginManager.loadPlugin(textFile);

        // Then
        // it should throw an exception
    }

    @Test
    public void loadPlugin_shouldThrowException_whenTheGivenPluginDoesntHaveAMainfdsafdasfdsafdsaClassExtendingJavaPlugin() throws Exception {

        // Given
       File plugin = new File("src/test/resources/plugins/sample-plugin.jar");

        // When
        pluginManager.loadPlugin(plugin);

        // Then
        // it should throw an exception
    }
}