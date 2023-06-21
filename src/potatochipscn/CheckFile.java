package potatochipscn;

import java.io.File;

public class CheckFile {
    static boolean nmsl;
    public static void main(String[] args) {
        File file = new File("C:/snakegamedata.txt");
        System.out.println(file.exists());
        nmsl = file.exists();
    }
}