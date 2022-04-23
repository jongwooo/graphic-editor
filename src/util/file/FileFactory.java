package util.file;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

public class FileFactory implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final FileFactory FILE_FACTORY = new FileFactory();

    private final HashMap<String, File> cache;

    private FileFactory() {
        cache = new HashMap<>();
    }

    public static FileFactory createFileFactory() {
        return FILE_FACTORY;
    }

    public File getFile(String filePath) {
        if (cache.containsKey(filePath)) {
            return cache.get(filePath);
        } else {
            File newFile = new File(filePath);
            cache.put(filePath, newFile);
            return newFile;
        }
    }
}
