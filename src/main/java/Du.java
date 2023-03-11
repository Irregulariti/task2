import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Du {

    private static final List<String> eNum = Arrays.asList("B", "KB", "MB", "GB");

    public long sizeOfObject(String inputFile) {
        File file = new File(inputFile);
        if (!file.exists()) {
            throw new IllegalArgumentException();
        } else {
            if (file.isFile()) {
                return file.length();
            } else if (file.isDirectory()) {
                return sizeOfDirectory(file);
            }
        }
        return 0;
    }

    public long sizeOfDirectory(File directory) {
        long size = 0;
        for (File object : Objects.requireNonNull(directory.listFiles())) {
            if (object.isFile()) {
                size += object.length();
            } else {
                size += sizeOfDirectory(object);
            }
        }
        return size;
    }

    private long sumSize = 0;

    public String adaptionToReadable(long size, boolean h, boolean c, boolean si) {

        int base = 1024;
        if (si) base = 1000;

        StringBuilder answer = new StringBuilder();
        if (h) {
            int index = 0;
            while (size >= base && index <= 3) {
                size /= base;
                index++;
            }
            answer.append(size);
            answer.append(eNum.get(index));
        }else answer.append(Math.max(1,size/base));

        return answer.toString();
    }

    public void output(List<String> files, boolean h, boolean c, boolean si) {
        for (int i = 0; i < files.size(); i++) {
            long size = sizeOfObject(files.get(i));
            if (c) sumSize += size;
            if (!c) {
                System.out.print(files.get(i));
                System.out.print(" - ");
                System.out.println(adaptionToReadable(size, h, false, si));
            }
        }
        if (c) {
            System.out.print("Total sum - ");
            System.out.println(adaptionToReadable(sumSize, h, true, si));
        }
    }
}
