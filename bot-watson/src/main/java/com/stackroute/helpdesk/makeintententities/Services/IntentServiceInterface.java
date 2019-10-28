package com.stackroute.helpdesk.makeintententities.Services;

import java.util.List;

public interface IntentServiceInterface {
    public String createIntent(String name, List<String> testStrings);
}
