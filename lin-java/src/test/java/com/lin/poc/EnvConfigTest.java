package com.lin.poc;

import org.junit.Assert;
import org.junit.Test;

public class EnvConfigTest {

    @Test
    public void testSetEnv(){
        try{
            EnvConfig envConfig = new EnvConfig("dev");
            Assert.assertNotNull(envConfig);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetValue() {
        try{
            EnvConfig envConfig = new EnvConfig("dev");
            String val = envConfig.getValue("test");
            Assert.assertEquals("OK", val);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadDefault() {
        try{
            EnvConfig envConfig = new EnvConfig("dev");
            String val = envConfig.getValue("file");
            Assert.assertEquals("MyFile", val);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
