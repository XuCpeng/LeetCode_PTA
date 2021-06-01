package cn.medemede.leecode;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class SolutionsSplit {

    private static final String OLD_CLASS_NAME = "public class Solution";
    private static final String[] headStrs = {
            "package cn.medemede.leecode.subsolutions;\n\r",
            "import cn.medemede.leecode.*;\n\r",
            "import java.util.*;\n\r",
            "public class Solution {\n\r"
    };
    private static final String DIR_PATH = "/home/xcp/Projects/gitfromhub/LeetCode_PTA/JavaCodeSpace/src/cn/medemede/leecode/";

    public static void main(String[] args) throws IOException {
        File solutionFile = Paths.get(DIR_PATH, "Solution.java").toFile();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(solutionFile)))) {

            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null && !line.contains("/**")) {
                line = bufferedReader.readLine();
            }

            while (line != null && line.contains("/**")) {

                // 写入头部内容
                for (String headStr : headStrs) {
                    sb.append(headStr);
                }

                String newClassName = null;
                // 写入方法体
                sb.append("/**\n\r");
                line = bufferedReader.readLine();
                while (line != null && !line.contains("/**") && !line.contains("main(")) {
                    if (newClassName == null && line.contains("public")) {
                        newClassName = line.trim().split(" ")[2].split("\\(")[0];
                    }
                    sb.append(line);
                    sb.append("\r");
                    line = bufferedReader.readLine();
                }
                sb.append("}\n\r");
                if (newClassName != null) {
                    saveFile(sb, newClassName);
                } else {
                    sb.delete(0, sb.length());
                }

            }
        }
    }

    private static void saveFile(StringBuilder sb, String newClassName) throws IOException {
        newClassName = (char) (newClassName.charAt(0) - 'a' + 'A') + newClassName.substring(1);
        String newClassHead = "public class " + newClassName;
        int i = sb.indexOf(OLD_CLASS_NAME);
        sb.replace(i, i + OLD_CLASS_NAME.length(), newClassHead);
        File renamedFile = Paths.get(DIR_PATH, "subsolutions", newClassName + ".java").toFile();
        FileUtils.writeStringToFile(renamedFile, sb.toString(), StandardCharsets.UTF_8);
        sb.delete(0, sb.length());
    }
}
