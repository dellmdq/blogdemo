package org.dellmdq.blogdemo.util;

import java.io.IOException;
import java.net.URL;

public class ImageValidation {

    public static boolean urlValidation(String url) {

        try {
            (new URL(url)).openStream().close();
            return true;
        } catch (Exception exc) {
        }
        return false;
    }

    public static boolean validateJpgPngExtension(String url) {
        if (url.contains(".jpg") || url.contains(".png")) {
            return true;
        } else {
            return false;
        }
    }

}
