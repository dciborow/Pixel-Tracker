package com.microsoft.azure.pixeltracker.server.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.inject.Singleton;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dcibo on 6/5/2017.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PixelControllerTest {

    @BeforeAll
    static void beforeAll() {
        Map<String, String> map = new HashMap<>();
        map.put("ServiceBusNamespaceName", "pixelTracker");
        map.put("EventHubName", "pixelTracker");
        map.put("sharedAccessSignatureKeyName", "RootManageSharedAccessKey");
        map.put("SharedAccessSignatureKey", "UfdOeHh4MxC4xGtapANUt07T+8iMsTb0PHt5Cg4THDo=");
        setEnv(map);
    }

    @Autowired
    @Singleton
    private MockMvc mvc;

    @Test
    void pixel() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pixel").accept(MediaType.IMAGE_GIF))
                .andExpect(status().isOk());

    }

    @Test
    void pixel2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pixel?this=that").accept(MediaType.IMAGE_GIF))
                .andExpect(status().isOk());

    }


    @SuppressWarnings("unchecked")
    private static void setEnv(Map<String, String> newenv) {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        } catch (NoSuchFieldException e) {
            try {
                Class[] classes = Collections.class.getDeclaredClasses();
                Map<String, String> env = System.getenv();
                for (Class cl : classes) {
                    if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                        Field field = cl.getDeclaredField("m");
                        field.setAccessible(true);
                        Object obj = field.get(env);
                        Map<String, String> map = (Map<String, String>) obj;
                        map.clear();
                        map.putAll(newenv);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}