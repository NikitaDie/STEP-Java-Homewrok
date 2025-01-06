package com.nikitadeveloper.hs3.task2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrieDictionary {

    private final TrieNode sourceLanguageRoot = new TrieNode(null);
    private final TrieNode targetLanguageRoot = new TrieNode(null);

    public List<String> getTop10Definitions() {
        List<TrieNode> allWords = new ArrayList<>();
        collectWords(sourceLanguageRoot, allWords);

        return allWords.stream()
            .filter(node -> node.isEndOfWord)
            .sorted((n1, n2) -> Integer.compare(n2.getUsageCount(), n1.getUsageCount()))
            .limit(10)
            .map(TrieNode::getWord)
            .toList();
    }

    public List<String> getLeast10Definitions() {
        List<TrieNode> allWords = new ArrayList<>();
        collectWords(sourceLanguageRoot, allWords);

        return allWords.stream()
            .filter(node -> node.isEndOfWord)
            .sorted(Comparator.comparingInt(TrieNode::getUsageCount))
            .limit(10)
            .map(TrieNode::getWord)
            .toList();
    }

    public void addWord(String word, List<String> translations) {
        var wordNode = getNode(sourceLanguageRoot, word) == null
            ? addToTrie(sourceLanguageRoot, word)
            : getNode(sourceLanguageRoot, word);

        for (String translation : translations) {
            var translationNode = addToTrie(targetLanguageRoot, translation);
            linkWordToTranslation(wordNode, translationNode);
        }
    }

    public List<String> getTranslations(String word) {
        TrieNode wordNode = getNode(sourceLanguageRoot, word);
        if (wordNode != null && wordNode.isEndOfWord && wordNode.translations != null) {
            wordNode.incrementUsageCount();
            return wordNode.getAllTranslations();
        }
        return List.of();
    }

    public void changeDefinition(String oldWord, String newWord) {
        TrieNode oldWordNode = getNode(sourceLanguageRoot, oldWord);
        if (oldWordNode == null || !oldWordNode.isEndOfWord) return;

        List<String> translations = oldWordNode.getAllTranslations();
        removeWord(sourceLanguageRoot, oldWord);
        addWord(newWord, translations);
    }

    public void replaceTranslation(String word, String oldTranslation, String newTranslation) {
        if (!isDefinitionPresented(word)) return;

        removeTranslation(oldTranslation);
        addWord(word, List.of(newTranslation));
    }

    public void removeDefinition(String word) {
        removeWord(sourceLanguageRoot, word);
    }

    public void removeTranslation(String translation) {
        removeWord(targetLanguageRoot, translation);
    }

    private boolean isDefinitionPresented(String word) {
        return getNode(sourceLanguageRoot, word) != null;
    }

    private void linkWordToTranslation(TrieNode wordNode, TrieNode translationNode) {
        wordNode.translations.add(translationNode); // Link the word to its translation
        translationNode.translations.add(wordNode); // Link the translation to its word
    }

    private void removeWord(TrieNode root, String word) {
        TrieNode wordNode = getNode(root, word);
        if (wordNode == null || !wordNode.isEndOfWord) return;

        removeLinksToTranslations(wordNode);
        wordNode.translations.clear();
        wordNode.isEndOfWord = false;

        removeUnusedNodes(wordNode);
    }

    private void removeLinksToTranslations(TrieNode wordNode) {
        for (TrieNode translationNode : wordNode.translations) {
            translationNode.translations.remove(wordNode);
            removeTranslationIfOrphaned(translationNode);
        }
    }

    private void removeTranslationIfOrphaned(TrieNode translationNode) {
        if (translationNode.translations.isEmpty()) {
            translationNode.isEndOfWord = false;
            removeUnusedNodes(translationNode);
        }
    }

    private void removeUnusedNodes(TrieNode leaf) {
        char[] wordToBeRemoved = leaf.getWord().toCharArray();
        removeUnusedNodes(leaf, wordToBeRemoved, wordToBeRemoved.length - 1);
    }

    private void removeUnusedNodes(TrieNode current, char[] charactersToBeRemoved, int index) {
        if (current == null || index == 0) return;

        if (!current.translations.isEmpty() || current.isEndOfWord) return;

        TrieNode parent = current.parent;
        if (parent != null) {
            char characterToRemove = charactersToBeRemoved[index];
            parent.children.remove(characterToRemove);

            if (parent.children.isEmpty() && !parent.isEndOfWord) {
                removeUnusedNodes(parent, charactersToBeRemoved, index - 1);
            }
        }
    }

    private TrieNode addToTrie(TrieNode root, String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode parent = current;
            current = current.children.computeIfAbsent(c, k -> new TrieNode(parent));
        }
        current.isEndOfWord = true;
        return current;
    }

    private TrieNode getNode(TrieNode root, String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) return null;
        }
        return current;
    }

    private void collectWords(TrieNode node, List<TrieNode> allWords) {
        if (node == null) return;

        if (node.isEndOfWord) {
            allWords.add(node);
        }

        for (TrieNode child : node.children.values()) {
            collectWords(child, allWords);
        }
    }
}
