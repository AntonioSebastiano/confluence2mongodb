package com.ulixe.confluence2mongodb.helpers;

import java.util.Map;

public interface MarkdownConverter {
    Map<String, String> getAccountIdToNameMap();
    void setAccountIdToNameMap(Map<String, String> accountIdToNameMap);
    String convertToMarkdown(
            String jiraText
    );
}
