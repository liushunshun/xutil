package com.xy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XiuYang on 2016/9/22.
 */
public class RegexUtil {
    public static void main(String[] args) {
        String text    =
                "This is the text to be searched " +
                        "for occurrences of the Http:// pattern.";
        //查找字符串
        String pattern = ".*http://.*";
        boolean metches = Pattern.matches(pattern,text);

        //Matcher
        Pattern pattern1 = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);//忽略大小写
        Matcher matcher = pattern1.matcher(text);
        boolean matches1 = matcher.matches();

        //正则表达式作为分隔符分隔文本
        Pattern pattern2 = Pattern.compile("\\shttp://\\s",Pattern.CASE_INSENSITIVE);//忽略大小写
        for(String s:pattern2.split(text)){
            System.out.println(s);
        }
        pattern2.pattern();//正则表达式字符串

        //如果正则表达式匹配文本开头而不匹配整个文本,lookingAt() 返回true,而matches() 返回false。
        String patternString = "This is ";
        Pattern pattern3 = Pattern.compile(patternString);
        Matcher matcher1 = pattern3.matcher(text);
        System.out.println("lookingAt = " + matcher1.lookingAt());
        System.out.println("matches   = " + matcher1.matches());

        /**
         * find() 方法用于在文本中查找出现的正则表达式，文本是创建Matcher时，通过 Pattern.matcher(text) 方法传入的。如果在文本中多次匹配，find() 方法返回第一个，之后每次调用 find() 都会返回下一个。
             start() 和 end() 返回每次匹配的字串在整个文本中的开始和结束位置。实际上, end() 返回的是字符串末尾的后一位，这样，可以在把 start() 和 end() 的返回值直接用在String.substring() 里。
         */
        String text1    =
                "This is the text which is to be searched " +
                        "for occurrences of the word 'is'.";
        String patternString2 = "is";
        Pattern pattern4 = Pattern.compile(patternString2);
        Matcher matcher2 = pattern4.matcher(text1);
        int count = 0;
        while(matcher2.find()) {
            count++;
            System.out.println("found: " + count + " : "  + matcher2.start() + " - " + matcher2.end());
        }

        matcher2.reset();//从头开始重新查找

        /**
         * group()

         假设想在一个文本中查找URL链接，并且想把找到的链接提取出来。当然可以通过 start()和 end()方法完成。但是用group()方法更容易些。

         分组在正则表达式中用括号表示，例如:
         (John)
         此正则表达式匹配John, 括号不属于要匹配的文本。括号定义了一个分组。当正则表达式匹配到文本后，可以访问分组内的部分。

         使用group(int groupNo) 方法访问一个分组。一个正则表达式可以有多个分组。每个分组由一对括号标记。想要访问正则表达式中某分组匹配的文本，可以把分组编号传入 group(int groupNo)方法。

         group(0) 表示整个正则表达式，要获得一个有括号标记的分组，分组编号应该从1开始计算。
         */
        Pattern pattern5 = Pattern.compile("(i[0-9]) (i\\S)");
        Matcher matcher3 = pattern5.matcher("This is i1 i2 is");
        while (matcher3.find()){
            System.out.println("fond: " + matcher3.group(1));
            System.out.println("fond: " + matcher3.group(2));
        }


        String s = " <img src=\"www.baidu.com\" /> \n" +
                "sdf <img src=\"www.baidu.com\" /> ";
        Pattern pattern6 = Pattern.compile("<\\s*img\\s+([^>]*)\\s*>");
        Matcher matcher4 = pattern6.matcher(s);
        System.out.println(matcher4.replaceAll("[图片]"));//输出： [图片]

        /**
         * <video width="320" height="240" controls="controls">
             <source src="movie.mp4" type="video/mp4" />
             <source src="movie.ogg" type="video/ogg" />
             <source src="movie.webm" type="video/webm" />
             <object data="movie.mp4" width="320" height="240">
                 <embed src="movie.swf" width="320" height="240" />
             </object>
         <embed src="http://player.youku.com/player.php/Type/Folder/Fid/28212553/Ob/1/sid/XMTczNDI3NTY2OA==/v.swf"
         quality="high" width="480" height="400" align="middle" allowScriptAccess="always"
         allowFullScreen="true" mode="transparent" type="application/x-shockwave-flash"></embed>
         </video>
         */

        String video = "<video width=\"320\" height=\"240\" controls=\"controls\">\n" +
                "  <source src=\"movie.mp4\" type=\"video/mp4\" />\n" +
                "  <source src=\"movie.ogg\" type=\"video/ogg\" />\n" +
                "  <source src=\"movie.webm\" type=\"video/webm\" />\n" +
                "Your browser does not support the video tag.\n" +
                "</video> 123 <video width=\"320\" height=\"240\" controls=\"controls\"/> 456 " +
                "" +
                "<embed src=\"http://player.youku.com/player.php/Type/Folder/Fid/28212553/Ob/1/sid/XMTczNDI3NTY2OA==/v.swf\"\n" +
                "         quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\"\n" +
                "         allowFullScreen=\"true\" mode=\"transparent\" type=\"application/x-shockwave-flash\"></embed> 789" +
                "" +
                "<embed src=\"movie.swf\" width=\"320\" height=\"240\" />" +
                "123456" +
                "<object data=\"movie.mp4\" width=\"320\" height=\"240\">\n" +
                "                 <embed src=\"movie.swf\" width=\"320\" height=\"240\" />\n" +
                "             </object> " +
                "<embed src=\"http://player.youku.com/player.php/Type/Folder/Fid/28212553/Ob/1/sid/XMTczNDI3NTY2OA==/v.swf\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" allowFullScreen=\"true\" mode=\"transparent\" type=\"application/x-shockwave-flash\"></embed> ";

        String ss = " <embed src=\"movie.swf\" width=\"320\" height=\"240\" /><embed src=\"http://player.youku.com/player.php/Type/Folder/Fid/28212553/Ob/1/sid/XMTczNDI3NTY2OA==/v.swf\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" allowFullScreen=\"true\" mode=\"transparent\" type=\"application/x-shockwave-flash\"></embed> ";
        /**
         * 正则表达式：<(?<tag>[^\s>]+)[^>]*>(.|\n)*?</\k<tag>>
           说明：匹配成对的HTML标签，它将会匹配Html标签及标签中的内容，本例分三段匹配三个<table>标签及</table>中的内容。
           原理：
                <                                   //html标签中的 <
                (?<tag>[^\s>]+)                     // (?<name>), 分组命名的方式，[^\s>]：非任何空白字符及“>”至少匹配一次
                [^>]*>                              //非“>”匹配 0 到 n 次，及html的标签“>”.
                (.|\n)                              //在两个或多个项之间时行选择，(zlf)ood 与 "zood" 或 "food" 匹配.
                *?                                  // 应与上一个(.|\n)联合起来看， .*？ 就意味着匹配任意数量的重复，但是在能使整个匹配成功的前提下使用最少的重复，懒惰模式。
                </\k<tag>>                          //反向引用命名的组，语法为 \k<name>， 及html 标签“>”

            注意：“(.|\n)”后面的 "*" 匹配 0 个到多个任意字符，而“？”使得“*”匹配范围最小化，即在找到表达式的下一部分之前匹配尽可能少的字符。
            本例使用了分组命名及反向引用命名组的概念。
         */
        //匹配所有成对的html标签
        //Pattern pattern7 = Pattern.compile("<(?<video>[^\\s>]+)[^>]*>(.|\\n)*?</\\k<video>>");
        //匹配自闭合的html标签
        //Pattern pattern7 = Pattern.compile("<\\s*video\\s+([^>]*)\\s*>");

        String s1 = Pattern.compile("<\\s*(video|embed|object)\\s+([^>]*)\\s*/>").matcher(ss).replaceAll("[视频]");
        System.out.println("s1 : "+s1);

        //下面一行代码在过滤很长的文本时会报堆栈溢出
        String s2 = Pattern.compile("<(?<tag>[video|embed|object|^\\s>]+)[^>]*>(.|\\n)*?</\\k<tag>>").matcher(s1).replaceAll("[视频]");

        System.out.println("s2 : "+s2);

    }
}
