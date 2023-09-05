package org.example.util;

import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

import static org.example.constant.ConstantApp.DATABASE_PROPERTIES_FILE_PATH;
@Data
public class ApplicationProperties {
       private static final Map<String,String> properties;
       private static final Map<String,String> check;
//
    static {
        try (
                InputStream inputStream = ApplicationProperties.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILE_PATH);
        )
        {
            Yaml yaml = new Yaml();
            Map<String, Map<String, String>> data = yaml.load(inputStream);
            properties = data.get("database");
            check= data.get("check");
        } catch (Exception e) {

            System.err.println("Error initialization DataBaseConnector: " + e.getMessage());
            throw new RuntimeException("Error initialization DataBaseConnector: " + e.getMessage());
        }
    }
    public static Map<String,String> prop(){
        return properties;
    }
    public static String checkFolder(){
        return check.get("check-path");
    }

}
