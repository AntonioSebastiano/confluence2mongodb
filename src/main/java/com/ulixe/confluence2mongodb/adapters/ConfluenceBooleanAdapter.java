package com.ulixe.confluence2mongodb.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.oxm.UnmarshallingFailureException;

public class ConfluenceBooleanAdapter extends XmlAdapter<String, Boolean> {

    @Override
    public Boolean unmarshal(String v) {
        if (v == null || v.isEmpty()) return null;
        return switch (v.toLowerCase()) {
            case "y", "yes", "true", "1" -> true;
            case "n", "no", "false", "0" -> false;
            default -> throw new UnmarshallingFailureException("Unknown value " + v + " for boolean field");
        };
    }

    @Override
    public String marshal(Boolean v) {
        if (v == null) {
            return null;
        }
        if (v) {
            return "Y";
        } else {
            return "N";
        }
    }
}