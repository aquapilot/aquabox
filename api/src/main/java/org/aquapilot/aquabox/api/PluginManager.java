/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api;

import org.aquapilot.aquabox.api.exception.InvalidPluginException;
import org.aquapilot.aquabox.api.listener.AquaboxListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Collection;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface PluginManager {

    /**
     * Registers the specified plugin loader
     *
     * @param loader Class name of the PluginLoader to register
     * @throws IllegalArgumentException Thrown when the given Class is not a
     *                                  valid PluginLoader
     */
//    public void registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException;

    /**
     * Checks if the given plugin is loaded and returns it when applicable
     * <p>
     * Please note that the name of the plugin is case-sensitive
     *
     * @param name Name of the plugin to check
     * @return Plugin if it exists, otherwise null
     */
    public JavaPlugin getPlugin(String name);

    /**
     * Gets a list of all currently loaded plugins
     *
     * @return Array of Plugins
     */
    public Collection<JavaPlugin> getPlugins();

    /**
     * Checks if the given plugin is enabled or not
     * <p>
     * Please note that the name of the plugin is case-sensitive.
     *
     * @param name Name of the plugin to check
     * @return true if the plugin is enabled, otherwise false
     */
    public boolean isPluginEnabled(String name);

    /**
     * Checks if the given plugin is enabled or not
     *
     * @param plugin Plugin to check
     * @return true if the plugin is enabled, otherwise false
     */
    public boolean isPluginEnabled(JavaPlugin plugin);

    PluginDescriptor getPluginDescriptor(File file) throws InvalidPluginException;

    /**
     * Loads the plugin in the specified file
     * <p>
     * File must be valid according to the current enabled Plugin interfaces
     *
     * @param file File containing the plugin to load
     * @return The Plugin loaded, or null if it was invalid
     * @throws InvalidPluginException      Thrown when the specified file is not a
     *                                     valid plugin
     * @throws InvalidDescriptionException Thrown when the specified file
     *                                     contains an invalid description
     * @throws UnknownDependencyException  If a required dependency could not
     *                                     be resolved
     */
    JavaPlugin loadPlugin(
            File file) throws InvalidPluginException; //, InvalidDescriptionException, UnknownDependencyException;

    /**
     * Loads the plugins contained within the specified directory
     *
     * @param path Directory to check for plugins
     * @return A list of all plugins loaded
     */
    Collection<JavaPlugin> loadPlugins(Path path) throws FileNotFoundException;

    /**
     * Disables all the loaded plugins
     */
    void disablePlugins();

    /**
     * Disables and removes all plugins
     */
    void clearPlugins();

    /**
     * Registers all the events in the given listener class
     *
     * @param listener Listener to register
     * @param plugin   Plugin to register
     */
    void registerEvents(AquaboxListener listener, JavaPlugin plugin);

    /**
     * Registers the specified executor to the given event class
     *
     * @param event    Event type to register
     * @param listener Listener to register
     * @param priority Priority to register this event at
     * @param executor EventExecutor to register
     * @param plugin   Plugin to register
     */
//    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority,
//                              EventExecutor executor, Plugin plugin);

    /**
     * Registers the specified executor to the given event class
     *
     * @param event           Event type to register
     * @param listener        Listener to register
     * @param priority        Priority to register this event at
     * @param executor        EventExecutor to register
     * @param plugin          Plugin to register
     * @param ignoreCancelled Whether to pass cancelled events or not
     */
//    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority,
//                              EventExecutor executor, Plugin plugin, boolean ignoreCancelled);

    /**
     * Enables the specified plugin
     * <p>
     * Attempting to enable a plugin that is already enabled will have no
     * effect
     *
     * @param plugin Plugin to enable
     */
    void enablePlugin(JavaPlugin plugin);

    /**
     * Disables the specified plugin
     * <p>
     * Attempting to disable a plugin that is not enabled will have no effect
     *
     * @param plugin Plugin to disable
     */
    void disablePlugin(JavaPlugin plugin);

}
