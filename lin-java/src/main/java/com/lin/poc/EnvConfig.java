package com.lin.poc;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EnvConfig {

    private static final Logger LOG = LoggerFactory.getLogger(EnvConfig.class);
    private static final Map<String, String> envs = new HashMap<String, String>();
    private CompositeConfiguration config = new CompositeConfiguration();

    private static String ENV_DEFAULT = "default";
    private static String ENV_LOCAL = "local";
    private static String ENV_DEV = "dev";
    private static String ENV_QA = "qa";
    private static String ENV_UAT = "uat";
    private static String ENV_PROD = "prod";

    private String envFile = null;

    private static String DEFAULT_ENV_FILE = "lin_default.properties";
    public EnvConfig(String env) throws Exception {
        setEnv(env);
    }

    public void setEnv(String env) throws Exception {
        LOG.error("Loading environment " + env);

        String val = null;
        try{
            val = validateEnvVal(env);
        } catch (Exception e){
            throw e;
        }
        this.envFile = "lin_" + val + ".properties";
        LOG.info("Environment file =" + this.envFile);

        try{
            config.addConfiguration(new PropertiesConfiguration(this.envFile));

            //If environment is not default, we add default values
            if(!(DEFAULT_ENV_FILE.equals(this.envFile))){
                LOG.info("Loading additional default environment values...")    ;
                config.addConfiguration(new PropertiesConfiguration(DEFAULT_ENV_FILE));
            }
        } catch (ConfigurationException ce) {
            LOG.error("Unable to load environment configurations.");
            ce.printStackTrace();
        }
    }

    public String getValue(String key) {
        return config.getString(key);
    }

    public Boolean getValueAsBoolean(String key){
        return config.getBoolean(key);
    }

    public Long getValueAsLong(String key){
        return config.getLong(key);
    }

    public Integer getValueAsInt(String key){
        return config.getInt(key);
    }

    public boolean hasKey(String key){
        return config.containsKey(key);
    }


    private String validateEnvVal(String env) throws Exception {
        if(Utils.isNullOrEmptyStr(env)){
            throw new Exception ("Environment value cannont be empty or null. Sent " +  env);
        }

        String envVal = env.trim();
        if(!(ENV_DEFAULT.equals(envVal) || ENV_LOCAL.equals(envVal) || ENV_DEV.equals(envVal) || ENV_QA.equals(envVal) || ENV_UAT.equals(envVal) || ENV_PROD.equals(envVal))){
            throw new Exception("Invalid environment value provided. Sent " + envVal);
        }
        return envVal;
    }
}
