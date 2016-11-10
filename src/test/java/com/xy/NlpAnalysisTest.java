package com.xy;


import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by XiuYang on 2016/10/10.
 */
public class NlpAnalysisTest<T> {

    public static void main(String[] args) {
        String s = "java相关 服务器";

        List<String> keyWords = NlpAnalysis.parse(s).getTerms().stream().map(Term::getRealName).
                filter(StringUtils::isNotBlank).collect(Collectors.toList());

        keyWords.forEach(System.out::println);
    }
}
