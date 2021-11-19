package edu.school21.preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String preProcess(String line) {
        return line.toLowerCase(Locale.ROOT);
    }
}
