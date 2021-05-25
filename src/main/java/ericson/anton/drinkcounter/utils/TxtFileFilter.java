package ericson.anton.drinkcounter.utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Set;

public class TxtFileFilter extends FileFilter {

    private static final Set<String> ACCEPTED_FILE_EXTENSIONS = Set.of("txt", "TXT");

    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory()) {
            return true;
        }
        return ACCEPTED_FILE_EXTENSIONS.contains(getFileExtension(pathname));
    }

    @Override
    public String getDescription() {
        return "TXT files";
    }

    private String getFileExtension(File pathname) {
        String fileName = pathname.getName();
        String[] fileNameExtension = fileName.split("\\.");
        return fileNameExtension.length == 2 ? fileNameExtension[1] : "";
    }
}
