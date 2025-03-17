import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MyAnotations {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface NotNull {
        String message() default "Поле не може бути null";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface NotEmpty {
        String message() default "Поле не може бути порожнім";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Size {
        int min() default 0;
        int max() default Integer.MAX_VALUE;
        String message() default "Довжина повинна бути між {min} та {max}";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Min {
        int value();
        String message() default "Значення повинно бути не менше {value}";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Max {
        int value();
        String message() default "Значення повинно бути не більше {value}";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Pattern {
        String regex();
        String message() default "Значення не відповідає шаблону";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Email {
        String message() default "Некоректний формат email";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Future {
        String message() default "Дата повинна бути в майбутньому";
    }

}
