package potatochipscn;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class Text {
    public static void createDataFile(){
        String filePath = "C:\\snakegamedata.txt"; // 文件路径
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("储存文件已成功创建。");
            } else {
                System.out.println("储存文件已存在，无需创建。");
            }
        } catch (IOException e) {
            System.out.println("创建储存文件时出现错误：" + e.getMessage());
        }
    }
    public static void writetoFile(int sb) {
        String filePath = "C:\\snakegamedata.txt"; // 文件路径
        String content = "first:true"; // 写入的内容
        String content2 = "first:false";
        if (sb == 1){
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(content);
                System.out.println("内容成功写入到储存文件。");
            } catch (IOException e) {
                System.out.println("失败");
            }
        }
        if (sb == 2){
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(content2);
                System.out.println("内容成功写入到储存文件。");
            } catch (IOException e) {
                System.out.println("失败");
            }
        }

    }
    public static void readFile() {
        String pathname = "C:\\snakegamedata.txt";
        String content = "first:true"; // 读取的内容
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)// 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String str = br.readLine();
            if (Objects.equals(str, content)){
                int sb = JOptionPane.showConfirmDialog(null,"是或否?","需要教程?",JOptionPane.YES_NO_OPTION);
                switch (sb) {
                    case JOptionPane.OK_OPTION:
                        JOptionPane.showMessageDialog(null, "上下左右移动,R键重开", "potatochipscn.SnakeGame", JOptionPane.INFORMATION_MESSAGE);
                        ClearFile.clearInfoForFile(pathname);
                        Text.writetoFile(2);
                        break;
                    case JOptionPane.NO_OPTION:
                    case JOptionPane.CANCEL_OPTION:
                        ClearFile.clearInfoForFile(pathname);
                        Text.writetoFile(2);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
