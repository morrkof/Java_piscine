package edu.school21.preprocessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProcess(String line) {
        return line.toUpperCase(Locale.ROOT);
    }
}
