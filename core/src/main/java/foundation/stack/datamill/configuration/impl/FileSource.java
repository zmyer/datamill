package foundation.stack.datamill.configuration.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * @author Ravi Chodavarapu (rchodava@gmail.com)
 */
public class FileSource extends AbstractSource {
    private final Properties properties;

    public FileSource(String propertiesLocation) throws IOException {
        this.properties = new Properties();

        File propertiesFile = new File(propertiesLocation);
        if (propertiesFile.isFile()) {
            try (FileInputStream fileStream = new FileInputStream(propertiesFile)) {
                properties.load(fileStream);
            }
        } else {
            try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(propertiesLocation)) {
                properties.load(resourceStream);
            }
        }
    }

    @Override
    public Optional<String> get(String name) {
        String value = properties.getProperty(name);
        return Optional.ofNullable(value);
    }
}
