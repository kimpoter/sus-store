package susstore.susstore.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginLoader
{
    public static ArrayList<String> getAllClassNames(String filePath) {
        ArrayList<String> classNames = new ArrayList<>();

        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(filePath));
            JarEntry jarEntry;

            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if (jarEntry.getName().endsWith(".class")) {
                    classNames.add(jarEntry.getName().replaceAll("/", "\\.").replace(".class", ""));
                }
            }
            jarFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classNames;
    }

    public static void load(String filePath) {
        try {
            ArrayList<String> classNames = getAllClassNames(filePath);

            File f = new File(filePath);
            URL url = f.toURI().toURL();

            URLClassLoader classLoader = new URLClassLoader(new URL[] { url });

            for (String className : classNames) {
                try {
                    classLoader.loadClass(className);
                }
                catch (Exception e) {
                    System.out.println("Class " + className + " not loadable.");
                }
                catch (Error e) {
                    System.out.println("Class " + className + " not loadable.");
                }
            }

            Plugin plugin = null;

            for (String className: classNames)
            {
                try {
                    Class<?> pluginClass = classLoader.loadClass(className);

                    plugin = (Plugin) pluginClass.newInstance();

                    break;
                }
                catch (Exception e) {
                    System.out.println("Class " + className + " does not implements plugin.");
                }
                catch (Error e) {
                    System.out.println("Class " + className + " not loadable.");
                }
            }

            plugin.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}