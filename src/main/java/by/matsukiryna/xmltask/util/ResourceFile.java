package by.matsukiryna.xmltask.util;

import by.matsukiryna.xmltask.exception.XmlException;
import by.matsukiryna.xmltask.main.Main;

import java.io.File;
import java.net.URL;

public class ResourceFile {
    public String getPath(String fileName) throws XmlException {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new XmlException("File not found! " + fileName);
        }
        String xmlFilePath = new File(resource.getFile()).getPath();
        return xmlFilePath;
    }
}
