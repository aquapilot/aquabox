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
import org.aquapilot.aquabox.common.AquaboxListener;
import org.aquapilot.aquabox.plugins.exception.InvalidPluginException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PluginManager implements PluginManagerInterface {

    public Map<JavaPlugin, AquaboxListener> plugins = new HashMap<>();

    public List<Class> pluginsList = new ArrayList<Class>();

    @Override
    public JavaPlugin getPlugin(String name) {
        return null;
    }

    @Override
    public Collection<JavaPlugin> getPlugins() {
        return plugins.keySet();
    }

    @Override
    public boolean isPluginEnabled(String name) {
        return false;
    }

    @Override
    public boolean isPluginEnabled(JavaPlugin plugin) {
        return false;
    }

    // TODO see if it should be in interface
    private PluginDescriptor getPluginDescriptor(File file) {
        checkNotNull(file, "File cannot be null");
        return null;
    }

    @Override
    public JavaPlugin loadPlugin(File file) throws InvalidPluginException {
        checkNotNull(file, "File cannot be null");

        if (!file.exists()) {
            throw new InvalidPluginException(new FileNotFoundException(file.getPath() + " does not exist"));
        }

  //      final PluginDescriptor pluginDescriptor = getPluginDescriptor(file);

//        String mainClass = pluginDescriptor.getMainClass();
        URLClassLoader loader = null;
        JarFile jar = null;
        try {
            // Load jar in memory
            loader = new URLClassLoader(new URL[]{file.toURL()});
            jar = new JarFile(file.getAbsolutePath());

            // Store jar content
            Enumeration enumeration = jar.entries();
            Class tmpClass = null;
            String tmp;
            while (enumeration.hasMoreElements()) {

                tmp = enumeration.nextElement().toString();

                //On vérifie que le fichier courant est un .class (et pas un fichier d'informations du jar )
                if (tmp.length() > 6 && tmp.substring(tmp.length() - 6).compareTo(".class") == 0) {

                    tmp = tmp.substring(0, tmp.length() - 6);
                    tmp = tmp.replaceAll("/", ".");

                    tmpClass = Class.forName(tmp, true, loader);

                    this.pluginsList.add(tmpClass);

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(loader != null){
                try {
                    loader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Collection<JavaPlugin> loadPlugins(Path path) {
        checkNotNull(path, "Directory cannot be null");

        if (!Files.exists(path)) {

        }

        if (!Files.isDirectory(path)) {

        }

        List<File> pluginsToLoad = Collections.emptyList();
        try {
            Files.list(path)
                    .filter(file -> Files.isRegularFile(file))
                    .filter(file -> file.getFileName().toString().endsWith(".jar"))
                    .forEach(
                    file -> {
                        try {
                            loadPlugin(file.toFile());
                        } catch (InvalidPluginException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void disablePlugins() {

    }

    @Override
    public void clearPlugins() {

    }

    @Override
    public void registerEvents(AquaboxListener listener, JavaPlugin plugin) {

    }

    @Override
    public void enablePlugin(JavaPlugin plugin) {

    }

    @Override
    public void disablePlugin(JavaPlugin plugin) {

    }
}
