package com.abocode.codemaker.generate;

import freemarker.template.Configuration;

import java.io.IOException;
import java.util.Locale;

import com.abocode.codemaker.util.CodeResourceUtils;

public class BaseCodeFactory {

    public BaseCodeFactory() {
    }

    public Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), CodeResourceUtils.FREEMARKER_CLASSPATH);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

}
