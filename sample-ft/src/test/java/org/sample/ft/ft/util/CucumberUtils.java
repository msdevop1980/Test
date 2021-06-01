package org.sample.ft.ft.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

final class CucumberUtil{

    private static final Pattern DATE_PATTERN=Pattern.compile("sysdate.*?");
    //private static final Set<String> ISO_COUNTRIES= Set.of(Locale.getISOCountries());

     private static  final Set<String> CURRENT_DATES=new HashSet<>(Arrays.asList("sysdate","Today","CurrentDate"));

    private static final Set<String>EOT=new HashSet<>(Arrays.asList("31-12-9999"));

    public static String elementAt(final String[] array,final int index){
        return ArrayUtils.getLength(array)>index?array[index]:null;
    }

    public static String[]splitByComma(final String input){
        return StringUtils.isNotBlank(input)?input.split(","):null;
    }

}