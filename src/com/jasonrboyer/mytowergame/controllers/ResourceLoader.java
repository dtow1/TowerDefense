package com.jasonrboyer.mytowergame.controllers;

import java.io.File;
import java.io.InputStream;

final public class ResourceLoader
{
    /**
     * @param path
     * @return
     */
    /**
     * @param path
     * @return
     */
    public static InputStream load(String path) { 
        InputStream input = ResourceLoader.class.getResourceAsStream(path);
        if(input==null) {
            input = ResourceLoader.class.getResourceAsStream(File.separator + path);
        }
        return input;
    }
}
