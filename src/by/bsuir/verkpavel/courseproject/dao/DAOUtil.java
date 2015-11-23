package by.bsuir.verkpavel.courseproject.dao;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class DAOUtil {
    public static  Map<Class<?>,Dao<?,?>> getDAOs(ConnectionSource connectionSource) throws SQLException{
        Package result = null;
        for(Package p : Package.getPackages()) {
           if (p.getName().equals("by.bsuir.verkpavel.courseproject.dao.entity")) {
              result  = p;
           }
        }
        
        List<Class<?>> classes = getClassesForPackage(result);
        HashMap<Class<?>, Dao<?,?>> DAOs = new HashMap<>();
        
        for(Class<?> c : classes){
            DAOs.put(c, DaoManager.createDao(connectionSource, c));
        }
        
        return DAOs;
    }
    
    private static List<Class<?>> getClassesForPackage(Package pkg) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        String pkgname = pkg.getName();
        String relPath = pkgname.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (resource == null) {
            throw new RuntimeException("Unexpected problem: No resource for " + relPath);
        }
        classes.addAll(processDirectory(new File(resource.getPath()), pkgname));

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
