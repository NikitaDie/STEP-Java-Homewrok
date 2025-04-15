package com.example.nikitadeveloper.hs01032025.config;

import com.example.nikitadeveloper.hs01032025.model.MealCategory;
import com.example.nikitadeveloper.hs01032025.model.MealTime;
import com.example.nikitadeveloper.hs01032025.model.Recipe;
import com.example.nikitadeveloper.hs01032025.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedRecipes(RecipeRepository recipeRepository) {
        return args -> {
            if (recipeRepository.count() == 0) {
                recipeRepository.saveAll(List.of(
                    Recipe.builder()
                        .title("Вівсянка з фруктами")
                        .description("Смачна і корисна вівсянка з бананом і яблуком.")
                        .videoUrl("https://youtube.com/vivsyanka")
                        .category(MealCategory.VEGAN)
                        .mealTime(MealTime.BREAKFAST)
                        .cuisine("Українська")
                        .popularity(10)
                        .ingredients(List.of("вівсянка", "молоко", "банан", "яблуко"))
                        .build(),

                    Recipe.builder()
                        .title("Салат з тунцем")
                        .description("Легкий салат з тунцем і овочами.")
                        .videoUrl("https://youtube.com/salat")
                        .category(MealCategory.NON_VEGETARIAN)
                        .mealTime(MealTime.LUNCH)
                        .cuisine("Середземноморська")
                        .popularity(20)
                        .ingredients(List.of("тунець", "огірок", "помідор", "оливкова олія"))
                        .build(),

                    Recipe.builder()
                        .title("Панкейки")
                        .description("Солодкі американські млинці з кленовим сиропом.")
                        .videoUrl("https://www.youtube.com/watch?v=pancakes1")
                        .category(MealCategory.VEGETARIAN)
                        .mealTime(MealTime.BREAKFAST)
                        .cuisine("Американська")
                        .popularity(85)
                        .ingredients(List.of("борошно", "молоко", "яйця", "цукор", "розпушувач"))
                        .build(),

                    Recipe.builder()
                        .title("Борщ")
                        .description("Традиційний український суп з буряком і м’ясом.")
                        .videoUrl("https://www.youtube.com/watch?v=borscht1")
                        .category(MealCategory.NON_VEGETARIAN)
                        .mealTime(MealTime.LUNCH)
                        .cuisine("Українська")
                        .popularity(87)
                        .ingredients(List.of("буряк", "капуста", "морква", "яловичина", "картопля"))
                        .build(),

                    Recipe.builder()
                        .title("Спагеті Карбонара")
                        .description("Класична італійська паста з беконом та сиром.")
                        .videoUrl("https://www.youtube.com/watch?v=carbonara2")
                        .category(MealCategory.NON_VEGETARIAN)
                        .mealTime(MealTime.DINNER)
                        .cuisine("Італійська")
                        .popularity(92)
                        .ingredients(List.of("паста", "яйця", "пармезан", "бекон", "перець"))
                        .build(),

                    Recipe.builder()
                        .title("Веганський боул")
                        .description("Збалансована страва з овочів та круп.")
                        .videoUrl("https://www.youtube.com/watch?v=vegan3")
                        .category(MealCategory.VEGAN)
                        .mealTime(MealTime.LUNCH)
                        .cuisine("Ф'южн")
                        .popularity(75)
                        .ingredients(List.of("кіноа", "нут", "авокадо", "батат", "шпинат"))
                        .build(),

                    Recipe.builder()
                        .title("Чікен Тікка Масала")
                        .description("Гостра курка у вершковому соусі.")
                        .videoUrl("https://www.youtube.com/watch?v=tikka4")
                        .category(MealCategory.NON_VEGETARIAN)
                        .mealTime(MealTime.DINNER)
                        .cuisine("Індійська")
                        .popularity(95)
                        .ingredients(List.of("курка", "йогурт", "томати", "спеції", "вершки"))
                        .build(),

                    Recipe.builder()
                        .title("Безглютенові млинці")
                        .description("Пухкі млинці для людей з непереносимістю глютену.")
                        .videoUrl("https://www.youtube.com/watch?v=gf-pancakes5")
                        .category(MealCategory.GLUTEN_FREE)
                        .mealTime(MealTime.BREAKFAST)
                        .cuisine("Американська")
                        .popularity(64)
                        .ingredients(List.of("рисове борошно", "молоко", "яйця", "розпушувач", "мед"))
                        .build(),

                    Recipe.builder()
                        .title("Тост з авокадо")
                        .description("Ситний сніданок або перекус.")
                        .videoUrl("https://www.youtube.com/watch?v=avo-toast6")
                        .category(MealCategory.VEGAN)
                        .mealTime(MealTime.SNACK)
                        .cuisine("Ф'южн")
                        .popularity(78)
                        .ingredients(List.of("хліб", "авокадо", "лимон", "сіль", "перець"))
                        .build(),

                    Recipe.builder()
                        .title("Суші роли")
                        .description("Японська класика з рисом і рибою.")
                        .videoUrl("https://www.youtube.com/watch?v=sushi7")
                        .category(MealCategory.NON_VEGETARIAN)
                        .mealTime(MealTime.LUNCH)
                        .cuisine("Японська")
                        .popularity(89)
                        .ingredients(List.of("рис", "норі", "лосось", "авокадо", "огірок"))
                        .build(),

                    Recipe.builder()
                        .title("Грецький салат")
                        .description("Салат зі свіжих овочів та сиру фета.")
                        .videoUrl("https://www.youtube.com/watch?v=greeksalad8")
                        .category(MealCategory.VEGETARIAN)
                        .mealTime(MealTime.DINNER)
                        .cuisine("Грецька")
                        .popularity(70)
                        .ingredients(List.of("помідори", "огірки", "фета", "оливки", "цибуля"))
                        .build(),

                    Recipe.builder()
                        .title("Кето омлет")
                        .description("Омлет з низьким вмістом вуглеводів.")
                        .videoUrl("https://www.youtube.com/watch?v=keto-omelette9")
                        .category(MealCategory.KETO)
                        .mealTime(MealTime.BREAKFAST)
                        .cuisine("Європейська")
                        .popularity(81)
                        .ingredients(List.of("яйця", "сир", "шинка", "перець", "олія"))
                        .build(),

                    Recipe.builder()
                        .title("Салат «Цезар»")
                        .description("Популярний салат з куркою і сухариками.")
                        .videoUrl("https://www.youtube.com/watch?v=caesar10")
                        .category(MealCategory.NON_VEGETARIAN)
                        .mealTime(MealTime.LUNCH)
                        .cuisine("Американська")
                        .popularity(88)
                        .ingredients(List.of("курка", "сухарики", "салат Романо", "пармезан", "соус Цезар"))
                        .build()
                ));

                System.out.println("📦 Демо-рецепти додані до БД.");
            } else {
                System.out.println("📦 Рецепти вже існують. Пропускаємо сидінг.");
            }
        };
    }
}