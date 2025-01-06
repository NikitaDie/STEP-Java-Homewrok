package com.nikitadeveloper.hs3.task2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrieDictionary trieDictionary = new TrieDictionary();

        // Add words with translations
        trieDictionary.addWord("apple", List.of("manzana", "pomme"));
        trieDictionary.addWord("banana", List.of("plátano", "banane"));
        trieDictionary.addWord("cherry", List.of("cereza", "cerise"));
        trieDictionary.addWord("date", List.of("dátil", "datte"));
        trieDictionary.addWord("elephant", List.of("elefante", "éléphant"));

        // Simulate usage by getting translations (this increases usageCount)
        System.out.println("Translations for 'apple': " + trieDictionary.getTranslations("apple"));
        System.out.println("Translations for 'banana': " + trieDictionary.getTranslations("banana"));
        System.out.println("Translations for 'cherry': " + trieDictionary.getTranslations("cherry"));
        System.out.println("Translations for 'date': " + trieDictionary.getTranslations("date"));
        System.out.println("Translations for 'elephant': " + trieDictionary.getTranslations("elephant"));

        // Get and print the top 10 most used words
        List<String> top10 = trieDictionary.getTop10Definitions();
        System.out.println("\nTop 10 Definitions (Most Used):");
        top10.forEach(System.out::println);

        // Get and print the least 10 used words
        List<String> least10 = trieDictionary.getLeast10Definitions();
        System.out.println("\nLeast 10 Definitions (Least Used):");
        least10.forEach(System.out::println);

        // Change the definition of a word
        System.out.println("\nChanging 'apple' to 'fruit':");
        trieDictionary.changeDefinition("apple", "fruit");
        System.out.println("Translations for 'fruit': " + trieDictionary.getTranslations("fruit"));

        // Replace translation for a word
        System.out.println("\nReplacing 'pomme' with 'apfel' for 'fruit':");
        trieDictionary.replaceTranslation("fruit", "pomme", "apfel");
        System.out.println("Translations for 'fruit': " + trieDictionary.getTranslations("fruit"));

        // Remove a definition (word)
        System.out.println("\nRemoving 'banana':");
        trieDictionary.removeDefinition("banana");
        System.out.println("Translations for 'banana': " + trieDictionary.getTranslations("banana"));

        // Remove a translation (translation)
        System.out.println("\nRemoving translation 'cerise':");
        trieDictionary.removeTranslation("cerise");
        System.out.println("Translations for 'cherry': " + trieDictionary.getTranslations("cherry"));
    }
}