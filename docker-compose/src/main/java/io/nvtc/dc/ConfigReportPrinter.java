package io.nvtc.dc;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

public class ConfigReportPrinter implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(@SuppressWarnings("null") ApplicationPreparedEvent event) {

        ConfigurableEnvironment env = event.getApplicationContext().getEnvironment();

        System.out.println("\n=====================================================");
        System.out.println(" SPRING BOOT CONFIGURATION REPORT");
        System.out.println("=====================================================");

        env.getPropertySources().forEach(source -> {
            System.out.println("\n-- Property Source: " + source.getName() + " --");

            if (source.getSource() instanceof java.util.Map<?, ?> map) {
                map.keySet().stream()
                        .map(Object::toString) // ensures keys are Strings
                        .sorted()
                        .forEach(key -> {
                            try {
                                String value = env.getProperty(key);
                                if (value != null) {
                                    System.out.printf("%s = %s%n", key, value);
                                }
                            } catch (Exception ignored) {
                            }
                        });
            }
        });

        System.out.println("=====================================================\n");
    }
}
