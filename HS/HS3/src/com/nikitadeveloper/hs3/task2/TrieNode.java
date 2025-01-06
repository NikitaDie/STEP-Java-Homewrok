package com.nikitadeveloper.hs3.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
    TrieNode parent;
    Map<Character, TrieNode> children = new HashMap<>();
    List<TrieNode> translations = new ArrayList<>();
    int usageCount = 0;
    boolean isEndOfWord = false;

    public TrieNode(TrieNode parent) {
        this.parent = parent;
    }

    public void incrementUsageCount() {
        usageCount++;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public List<String> getAllTranslations() {
        List<String> result = new ArrayList<>();
        for (TrieNode translation : translations) {
            result.add(translation.getWord());
        }
        return result;
    }

    public String getWord() {
        StringBuilder sb = new StringBuilder();
        TrieNode current = this;
        while (current.parent != null) {
            TrieNode child = current;
            char branchChar = findBranchChar(current, child);
            sb.insert(0, branchChar);
            current = current.parent;
        }
        return sb.toString();
    }

    private char findBranchChar(TrieNode current, TrieNode child) {
        return current.parent.children.entrySet().stream()
            .filter(entry -> entry.getValue() == child)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Child not found in parent's children"));
    }
}
