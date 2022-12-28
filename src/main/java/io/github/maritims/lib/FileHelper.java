package io.github.maritims.lib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {
    public static List<String> getFileContent(String fileName) {
        InputStream is = FileHelper.class.getClassLoader().getResourceAsStream(fileName);
        return is == null ? Collections.emptyList() : new BufferedReader(new InputStreamReader(is))
                .lines()
                .collect(Collectors.toList());
    }
}
