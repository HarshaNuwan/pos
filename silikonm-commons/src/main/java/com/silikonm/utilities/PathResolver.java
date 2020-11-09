package com.silikonm.utilities;

import java.io.File;
import java.security.CodeSource;

/**
 * @author harsha
 */
public class PathResolver {

    private static PathResolver pathResolver = null;

    private PathResolver() {
    }

    public static PathResolver getInstance() {
        if (pathResolver == null) {
            pathResolver = new PathResolver();
        }
        return pathResolver;
    }

    public String getPath() {
        try {
            CodeSource codeSource = PathResolver.class.getProtectionDomain().getCodeSource();
            final File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            return jarDir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
