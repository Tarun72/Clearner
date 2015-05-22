package com.syntax.highlighter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.internal.co;

import prettify.PrettifyParser;

import syntaxhighlight.ParseResult;
import syntaxhighlight.Parser;

public class PrettifyHighlighter {
	private static final Map<String, String> COLORS = buildColorsMap();

    private static final String FONT_PATTERN = "<font color=\"#%s\">%s</font>";

    private final Parser parser = new PrettifyParser();

    public String highlight(String fileExtension, String sourceCode) {
        StringBuilder highlighted = new StringBuilder();
        System.out.println("File Extension Source Code "+sourceCode+fileExtension);
        List<ParseResult> results = parser.parse(fileExtension, sourceCode);
        for(ParseResult result : results){
            String type = result.getStyleKeys().get(0);
            String content = sourceCode.substring(result.getOffset(), result.getOffset() + result.getLength());
            
            highlighted.append(String.format(FONT_PATTERN, getColor(type), content));
        
        }
        return highlighted.toString();
    }

    private String getColor(String type){
        return COLORS.containsKey(type) ? COLORS.get(type) : COLORS.get("pln");
    }
// ffffff white 
    private static Map<String, String> buildColorsMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("typ", "FF0000"); 
        map.put("kwd", "FF0000");
        map.put("lit", "000000");
        map.put("com", "228B22"); 
        map.put("str", "00BFFF"); 
        map.put("pun", "000000"); 
        map.put("pln", "000000");
        map.put("fun", "000000"); 
        return map;
    }
}
