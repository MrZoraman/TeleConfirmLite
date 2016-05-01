package com.lagopusempire.teleconfirmlite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static void ExportResource(Class c, InputStream resourceStream, File exportLocation) throws Exception {
        if(resourceStream == null) {
            throw new IllegalArgumentException("ResourceStream cannot be null!");
        }
        
//        InputStream stream = null;
        OutputStream resStreamOut = null;
        try {
//            stream = c.getResourceAsStream(resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
//            if (stream == null) {
//                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
//            }

            int readBytes;
            byte[] buffer = new byte[4096];
            resStreamOut = new FileOutputStream(exportLocation);
            while ((readBytes = resourceStream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
//            if (stream != null) {
//                stream.close();
//            }
            if (resStreamOut != null) {
                resStreamOut.close();
            }
        }
    }
}
