package cn.medemede.leecode;

/**
 * Excel表列名称
 */
public class ConvertToTitle {
    public String convertToTitle(int columnNumber) {
        int zero = 'A' - 1;
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            int tmp = columnNumber % 26;
            if (tmp == 0) {
                tmp = 'Z';
                columnNumber -= 26;
            } else {
                tmp += zero;
            }
            columnNumber /= 26;
            sb.insert(0, (char) tmp);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ConvertToTitle convertToTitle=new ConvertToTitle();
        System.out.println(convertToTitle.convertToTitle(701));
    }
}
