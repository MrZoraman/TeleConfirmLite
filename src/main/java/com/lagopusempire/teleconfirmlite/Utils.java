package com.lagopusempire.teleconfirmlite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static void ExportResource(InputStream resourceStream, File exportLocation) throws IOException {
        if(resourceStream == null) {
            throw new IllegalArgumentException("ResourceStream cannot be null!");
        }
        
        try(OutputStream resStreamOut = new FileOutputStream(exportLocation)) {
            int readBytes;
            byte[] buffer = new byte[4096];
            while ((readBytes = resourceStream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        }
    }
}
