package NumGenerator;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Генерация текстовых файлов с числами
public class Generator {
    public static void main(String[] args) {
        String file = generate(11, 10050000, 4);
        try {
            write("inFile2.txt", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generate(int step, int num, int start) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            start += step;
            buffer.append(start);
            if (i != num - 1) {
                buffer.append("\r\n");
            }
        }
        return buffer.toString();
    }

    public static void write(String name, String file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("src/main/resources/input/" + name);
        BufferedOutputStream buffer = new BufferedOutputStream(outputStream, 500);
        buffer.write(file.getBytes());
        buffer.flush();
        buffer.close();
    }
}
