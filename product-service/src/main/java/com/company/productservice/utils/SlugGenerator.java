package com.company.productservice.utils;

import lombok.experimental.UtilityClass;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@UtilityClass
public class SlugGenerator {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");

    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private static final Pattern EDGES_DASHES = Pattern.compile("(^-|-$)");

    private static final int LIMIT = 20;

    public static String toSlug(String input) {

        String noWhitespace = WHITESPACE.matcher(input).replaceAll("-");

        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);

        String slug = NON_LATIN.matcher(normalized).replaceAll("");

        slug = EDGES_DASHES.matcher(slug).replaceAll("");

        String slugLengthLimit = slug.length() > LIMIT ? slug.substring(0, LIMIT) : slug;

        return slugLengthLimit.toLowerCase(Locale.ENGLISH);

    }
}
