import Parameters.DataType;
import Parameters.SortingMode;

import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static SortingMode sortingMode = SortingMode.ASC;
    private static DataType dataType;
    private static String newFileName;
    private static List<String> usedFiles;
    private static String[][] dataStr;

    public static void main(String[] args) {

        usedFiles = new ArrayList<>();
        // Получаем аргументы и проверяем данные для дальнейшей работы программы
        takeArgs(args);
        checkData();


        // Получаем данные из файлов

        dataStr = new String[usedFiles.size()][];
        int k = 0;
        for (String name : usedFiles) {
            try {
                String data = readFiles(name);
                String[] dataArray = data.split("\n");
                dataStr[k] = dataArray;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            k++;

        }

        //Узнаём длину конечного массива для записи в файл
        int arrayLength = 0;
        for (int i = 0; i < dataStr.length; i++) {
            arrayLength += dataStr[i].length;
        }

        //Для String
        if (dataType == DataType.STRING) {
            makeFileString(arrayLength);
        }

        //Для Integer
        if (dataType == DataType.INTEGER) {
            makeFileInteger(arrayLength);
        }
    }

    public static void makeFileString(int arrayLength) {
        String[] newFileArray = new String[arrayLength];

        int position = 0;
        for (int i = dataStr.length - 1; i >= 0; i--) {
            for (int j = dataStr[i].length - 1; j >= 0; j--) {
                newFileArray[newFileArray.length - 1 - position] = dataStr[i][j];
                position++;
            }
        }
        newFileArray = sortArray(newFileArray);

        StringBuffer buffer = new StringBuffer();

        if (sortingMode == SortingMode.ASC) {

            for (int i = 0; i < newFileArray.length; i++) {
                buffer.append(newFileArray[i]);
                if (i != newFileArray.length - 1) {
                    buffer.append("\n");
                }
            }

        }

        if (sortingMode == SortingMode.DESC) {

            for (int i = newFileArray.length - 1; i >= 0; i--) {
                buffer.append(newFileArray[i]);
                if (i != 0) {
                    buffer.append("\n");
                }
            }

        }

        try {
            writer(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeFileInteger(Integer arrayLength) {
        Integer[] newFileArray = new Integer[arrayLength];

        int position = 0;
        for (int i = dataStr.length - 1; i >= 0; i--) {
            for (int j = dataStr[i].length - 1; j >= 0; j--) {
                newFileArray[newFileArray.length - 1 - position] = Integer.valueOf(dataStr[i][j]);
                position++;
            }
        }
        newFileArray = sortArray(newFileArray);

        StringBuffer buffer = new StringBuffer();

        if (sortingMode == SortingMode.ASC) {

            for (int i = 0; i < newFileArray.length; i++) {
                buffer.append(newFileArray[i]);
                if (i != newFileArray.length - 1) {
                    buffer.append("\n");
                }
            }

        }

        if (sortingMode == SortingMode.DESC) {

            for (int i = newFileArray.length - 1; i >= 0; i--) {
                buffer.append(newFileArray[i]);
                if (i != 0) {
                    buffer.append("\n");
                }
            }

        }

        try {
            writer(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writer(StringBuffer buffer) throws IOException {
        String outputFileName = "src/main/resources/output/" + newFileName;
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/output/" + newFileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 200);

        bufferedOutputStream.write(buffer.toString().getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public static void takeArgs (String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-a")) {
                sortingMode = SortingMode.ASC;
            } else if (args[i].equals("-d")) {
                sortingMode = SortingMode.DESC;
            } else if (args[i].equals("-i")) {
                dataType = DataType.INTEGER;
            } else if (args[i].equals("-s")) {
                dataType = DataType.STRING;
            } else if (args[i].contains(".txt")) {
                if (newFileName == null) {
                    newFileName = args[i];
                } else {
                    usedFiles.add(args[i]);
                }
            }
        }
    }

    public static void checkData() {
        if (dataType == null) {
            throw new NullPointerException("Data type is null (-code1)");
        }

        if (newFileName == null) {
            throw new NullPointerException("New file name is null (-code2)");
        }

        if (usedFiles.isEmpty()) {
            throw new IllegalStateException("It is not contains files that it need to convert (-code3)");
        }
    }

    public static String readFiles (String name) throws IOException {
        String inputFileName = "src/main/resources/input/" + name;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        if (reader == null) {
            throw new FileNotFoundException("File not found");
        }

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append("\n");
        }
        buffer.delete(buffer.lastIndexOf("\n"), buffer.lastIndexOf(""));

        return buffer.toString();
    }

    public static void mergeArray(Integer[] array, Integer[] arrayB, Integer[] arrayC) {

        int positionB = 0;
        int positionC = 0;

        for (int i = 0; i < array.length; i++) {
            if (positionB == arrayB.length) {
                array[i] = arrayC[positionC];
                positionC++;
            } else if (positionC == arrayC.length) {
                array[i] = arrayB[positionB];
                positionB++;
            } else if (arrayB[positionB] < arrayC[positionC]) {
                array[i] = arrayB[positionB];
                positionB++;
            } else {
                array[i] = arrayC[positionC];
                positionC++;
            }
        }
    }

    public static Integer [] sortArray(Integer[] array) {
        if (array == null) {
            return null;
        }
        if (array.length < 2) {
            return array;
        }

        Integer[] arrayB = new Integer[array.length / 2];
        System.arraycopy(array, 0, arrayB, 0, array.length / 2);

        Integer[] arrayC = new Integer[array.length - arrayB.length];
        System.arraycopy(array, arrayB.length, arrayC, 0, array.length - arrayB.length);

        sortArray(arrayB);
        sortArray(arrayC);

        mergeArray(array, arrayB, arrayC);

        return array;
    }

    public static String[] sortArray(String[] array) {
        if (array == null) {
            return null;
        }
        if (array.length < 2) {
            return array;
        }

        String[] arrayB = new String[array.length / 2];
        System.arraycopy(array, 0, arrayB, 0, array.length / 2);

        String[] arrayC = new String[array.length - arrayB.length];
        System.arraycopy(array, arrayB.length, arrayC, 0, array.length - arrayB.length);

        sortArray(arrayB);
        sortArray(arrayC);

        mergeArray(array, arrayB, arrayC);

        return array;
    }

    public static void mergeArray(String array[], String[] arrayB, String[] arrayC) {

        int positionB = 0;
        int positionC = 0;

        for (int i = 0; i < array.length; i++) {
            if (positionB == arrayB.length) {
                array[i] = arrayC[positionC];
                positionC++;
            } else if (positionC == arrayC.length) {
                array[i] = arrayB[positionB];
                positionB++;
            } else if (arrayB[positionB].compareTo(arrayC[positionC]) < 0) {
                array[i] = arrayB[positionB];
                positionB++;
            } else {
                array[i] = arrayC[positionC];
                positionC++;
            }
        }
    }


}
