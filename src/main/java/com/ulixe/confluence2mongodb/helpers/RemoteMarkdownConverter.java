package com.ulixe.confluence2mongodb.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RemoteMarkdownConverter implements MarkdownConverter {
    public RemoteMarkdownConverter() {
        accountIdToNameMap = new HashMap<>();
    }

    private Map<String, String> accountIdToNameMap;

    @Override
    public Map<String, String> getAccountIdToNameMap() {
        return accountIdToNameMap;
    }

    @Override
    public void setAccountIdToNameMap(Map<String, String> accountIdToNameMap) {
        this.accountIdToNameMap = Collections.unmodifiableMap(accountIdToNameMap);
    }

    @Override
    public String convertToMarkdown(
            String jiraText
    ) {
        final var issuesListsReplacedString = replaceIssuesLists(jiraText);
        final var tagsReplacedString = replaceTags(issuesListsReplacedString);
        return convertWithRemoteApi(tagsReplacedString);

    }

    private String replaceIssuesLists(String input) {

        // Regular expression to match [anything|anything|smart-card]
        String regex = "\\[[^]]+\\|smart-card]";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Now create matcher object
        Matcher matcher = pattern.matcher(input);

        // The replacement string
        String replacement = "[elenco issues originali non ricalcolabili]";

        // Replace all occurrences of the pattern with the replacement
        return matcher.replaceAll(replacement);
    }

    private String replaceTags(String input) {
        String regex = "\\[~accountid:([a-zA-Z0-9]+)]";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Now create matcher object
        Matcher matcher = pattern.matcher(input);
        // Create a StringBuffer to build the result
        StringBuilder result = new StringBuilder();

        // Find all matches and replace with corresponding name from map
        while (matcher.find()) {
            String accountId = matcher.group(1); // Get the accountid from the match
            System.out.println("Account id: " + accountId);
            String replacementName = accountIdToNameMap.getOrDefault(accountId, accountId); // Find replacement or use original if not found
            String replacement = "[user: " + replacementName + "]";
            matcher.appendReplacement(result, replacement); // Replace the match with the name
        }
        matcher.appendTail(result); // Append the rest of the string

        // Output the final result
        return result.toString();
    }

    private static String convertWithRemoteApi(String jiraText) {
        // Define the URL of the FastAPI service
        String apiUrl = "http://127.0.0.1:8005/jira-to-markdown";

        // Create a JSON payload
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("content", jiraText);

        // Convert the map to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpClient client = HttpClient.newHttpClient();
        // Create an HTTP client
        try {

            // Create the POST request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            TypeReference<HashMap<String, Object>> typeRef
                    = new TypeReference<>() {
            };
            HashMap<String, Object> o = objectMapper.readValue(response.body(), typeRef);
            return (String) o.get("markdown");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //client.close();
        }
    }
}
