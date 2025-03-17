import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern emailRegex = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);

                for (Annotation annotation : field.getAnnotations()) {
                    processAnnotation(value, annotation, field.getName(), errors);
                }

            } catch (IllegalAccessException e) {
                errors.add("Помилка доступу до поля: " + field.getName());
            }
        }
        return errors;
    }

    private static void processAnnotation(Object value, Annotation annotation, String fieldName, List<String> errors) {
        if (annotation == null) return;

        if (annotation instanceof MyAnotations.NotNull notNull)
            validateNotNull(value, notNull, fieldName, errors);
        else if (annotation instanceof MyAnotations.NotEmpty notEmpty)
            validateNotEmpty(value, notEmpty, fieldName, errors);
        else if (annotation instanceof MyAnotations.Size size)
            validateSize(value, size, fieldName, errors);
        else if (annotation instanceof MyAnotations.Min min)
            validateMin(value, min, fieldName, errors);
        else if (annotation instanceof MyAnotations.Max max)
            validateMax(value, max, fieldName, errors);
        else if (annotation instanceof MyAnotations.Pattern pattern)
            validatePattern(value, pattern, fieldName, errors);
        else if (annotation instanceof MyAnotations.Email email)
            validateEmail(value, email, fieldName, errors);
        else if (annotation instanceof MyAnotations.Future future)
            validateFuture(value, future, fieldName, errors);
        else
            System.out.println("Unrecognized annotation on " + fieldName + ": " + annotation);
    }

    private static void validateNotNull(Object value, MyAnotations.NotNull annotation, String fieldName, List<String> errors) {
        if (value == null)
            errors.add(formatMessage(annotation.message(), fieldName));
    }

    private static void validateNotEmpty(Object value, MyAnotations.NotEmpty annotation, String fieldName, List<String> errors) {
        boolean isEmpty = value == null ||
            (value instanceof String string && string.trim().isEmpty()) ||
            (value instanceof Collection<?> collection && collection.isEmpty()) ||
            (value instanceof Map<?,?> map && map.isEmpty()) ||
            (value.getClass().isArray() && Array.getLength(value) == 0);

        if (isEmpty)
            errors.add(formatMessage(annotation.message(), fieldName));
    }

    private static Optional<Integer> getSize(Object value) {
        if (value == null) return Optional.empty();
        if (value instanceof String string) return Optional.of(string.length());
        if (value instanceof Collection<?> collection) return Optional.of(collection.size());
        if (value instanceof Map<?, ?> map) return Optional.of(map.size());
        if (value.getClass().isArray()) return Optional.of(Array.getLength(value));
        return Optional.empty();
    }

    private static Optional<BigDecimal> getNumericValue(Object value) {
        if (value == null) return Optional.empty();
        if (value instanceof BigDecimal bd) return Optional.of(bd);
        if (value instanceof Number number) return Optional.of(BigDecimal.valueOf(number.doubleValue()));
        return Optional.empty();
    }

    private static void validateSize(Object value, MyAnotations.Size annotation, String fieldName, List<String> errors) {
        Optional<Integer> lengthOpt = getSize(value);

        if (lengthOpt.isPresent()) {
            int length = lengthOpt.get();
            if (length < annotation.min() || length > annotation.max()) {
                errors.add(formatMessage(annotation.message(), fieldName, annotation.min(), annotation.max()));
            }
        }
    }

    public static void validateMin(Object value, MyAnotations.Min annotation, String fieldName, List<String> errors) {
        getNumericValue(value).ifPresent(numericValue -> {
            BigDecimal minValue = BigDecimal.valueOf(annotation.value());
            if (numericValue.compareTo(minValue) < 0) {
                errors.add(formatMessage(annotation.message(), fieldName, annotation.value()));
            }
        });
    }

    public static void validateMax(Object value, MyAnotations.Max annotation, String fieldName, List<String> errors) {
        if (isNumber(value)) {
            BigDecimal numericValue = toBigDecimal(value);
            BigDecimal maxValue = BigDecimal.valueOf(annotation.value());

            if (numericValue.compareTo(maxValue) > 0)
                errors.add(formatMessage(annotation.message(), fieldName, annotation.value()));

        }
    }

    public static void validatePattern(Object value, MyAnotations.Pattern annotation, String fieldName, List<String> errors) {
        if (value instanceof String string && !Pattern.matches(annotation.regex(), string))
            errors.add(formatMessage(annotation.message(), fieldName));
    }

    public static void validateEmail(Object value, MyAnotations.Email annotation, String fieldName, List<String> errors) {
        if (!(value instanceof String email))
            return;

        if (!emailRegex.matcher(email).matches())
            errors.add(formatMessage(annotation.message(), fieldName));
    }

    public static void validateFuture(Object value, MyAnotations.Future annotation, String fieldName, List<String> errors) {
        if (value instanceof Date date) {
            if (date.before(new Date()))
                errors.add(formatMessage(annotation.message(), fieldName));

        } else if (value instanceof LocalDate localDate) {
            if (localDate.isBefore(LocalDate.now()))
                errors.add(formatMessage(annotation.message(), fieldName));

        } else if (value instanceof LocalDateTime localDateTime) {
            if (localDateTime.isBefore(LocalDateTime.now()))
                errors.add(formatMessage(annotation.message(), fieldName));
        }
    }

    private static boolean isNumber(Object value) {
        return value instanceof Number;
    }

    private static BigDecimal toBigDecimal(Object value) {
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        else if (value instanceof Number)
            return BigDecimal.valueOf(((Number) value).doubleValue());

        return BigDecimal.ZERO;
    }

    private static String formatMessage(String message, String fieldName, Object... values) {
        String defaultMessage = switch (values.length) {
            case 0 -> "{field} is invalid";
            case 1 -> "{field} must be at least {value}";
            case 2 -> "{field} must be between {min} and {max}";
            default -> "{field} validation failed";
        };

        String template = message.isBlank() ? defaultMessage : message;
        return MessageFormat.format(template.replace("{field}", "{0}")
                .replace("{min}", "{1}")
                .replace("{max}", "{2}")
                .replace("{value}", "{1}"),
            fieldName,
            values.length > 0 ? values[0] : "",
            values.length > 1 ? values[1] : "");
    }
}