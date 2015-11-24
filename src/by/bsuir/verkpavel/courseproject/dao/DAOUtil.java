package by.bsuir.verkpavel.courseproject.dao;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DAOUtil {
    
    public static List<Class<?>> getClassesForPackage(String pkg) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        String relPath = pkg.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (resource == null) {
            throw new RuntimeException("Unexpected problem: No resource for " + relPath);
        }
        classes.addAll(processDirectory(new File(resource.getPath()), pkg));

        return classes;
    }

    private static List<Class<?>> processDirectory(File directory, String pkgname) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        String[] files = directory.list();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            String className = null;
            if (fileName.endsWith(".class")) {
                className = pkgname + '.' + fileName.substring(0, fileName.length() - 6);
            }
            if (className != null) {
                classes.add(loadClass(className));
            }
            File subdir = new File(directory, fileName);
            if (subdir.isDirectory()) {
                classes.addAll(processDirectory(subdir, pkgname + '.' + fileName));
            }
        }
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        }
    }
}
