import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JesusValera
 */
public class Main {

    private static final String FILE = "example.txt";
    private List<String> lines;
    private File file;

    public static void main(String[] args) {
        new Main(usingmatrixrgs(args));
    }

    /**
     * @param args check if there is an argument set.
     * @return the path file
     */
    private static String usingmatrixrgs(String[] args) {
        if (args.length > 0) {
            return args[0];
        } else {
            return FILE;
        }
    }

    public Main(String path) {
        lines = new ArrayList<>();
        file = new File(path);

        if (!file.exists()) {
            System.err.println("File does not exists.");
            System.exit(0);
        }

        try {
            readLines(file);
            quicksort(lines, 0, lines.size() - 1);
            writeLines();

        } catch (IOException e) {
            System.err.println("There was a problem reading the file " + file.getName() + "\n-> " + e.getMessage());
        }

    }

    private void readLines(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            if (!line.equals("")) {
                lines.add(line);
            }
        }

        br.close();
        fr.close();
    }

    private void writeLines() throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String line : lines) {
            bw.write(line + "\n");
        }

        bw.close();
        fw.close();
    }

    private void bubble(List<String> matrix) {
        String temp;
        for (int i = 1; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size() - 1; j++) {
                int comp = matrix.get(j).compareTo(matrix.get(j + 1));
                if (comp >= 0) {
                    temp = matrix.get(j);
                    matrix.set(j, matrix.get(j + 1));
                    matrix.set(j + 1, temp);
                }
            }
        }
    }

    private void insertion(List<String> vector) {
        for (int i = 1; i < vector.size(); i++) {
            String aux = vector.get(i);
            int j;
            for (j = i - 1; j >= 0 && (vector.get(j).compareTo(aux) >= 0); j--) {
                vector.set(j + 1, vector.get(j));
            }
            vector.set(j + 1, aux);
        }
    }

    public void selection(List<String> matrix) {
        int i, j, pos;
        String menor, tmp;
        for (i = 0; i < matrix.size() - 1; i++) {
            menor = matrix.get(i);
            pos = i;
            for (j = i + 1; j < matrix.size(); j++) {
                if (matrix.get(j).compareTo(menor) < 0) {
                    menor = matrix.get(j);
                    pos = j;
                }
            }
            if (pos != i) {
                tmp = matrix.get(i);
                matrix.set(i, matrix.get(pos));
                matrix.set(pos, tmp);
            }
        }
    }

    private void shellSort(int[] matrix) {
        for (int increment = matrix.length / 2; increment > 0;
             increment = (increment == 2 ? 1 : (int) Math.round(increment / 2.2))) {
            for (int i = increment; i < matrix.length; i++) {
                for (int j = i; j >= increment && matrix[j - increment] > matrix[j]; j -= increment) {
                    int temp = matrix[j];
                    matrix[j] = matrix[j - increment];
                    matrix[j - increment] = temp;
                }
            }
        }
    }

    public void mergesort(List<String> A, int left, int right) {
        if (left < right) {
            int m = (left + right) / 2;
            mergesort(A, left, m);
            mergesort(A, m + 1, right);
            merge(A, left, m, right);
        }
    }

    public void merge(List<String> A, int left, int m, int right) {
        int i, j, k;
        String[] B = new String[A.size()];
        for (i = left; i <= right; i++)
            B[i] = A.get(i);

        i = left;
        j = m + 1;
        k = left;
        while (i <= m && j <= right)
            if (B[i].compareTo(B[j]) <= 0)
                A.set(k++, B[i++]);
            else
                A.set(k++, B[j++]);
        while (i <= m)
            A.set(k++, B[i++]);
    }

    public void quicksort(List<String> matrix, int left, int right) {

        String pivote = matrix.get(left);
        int i = left;
        int j = right;
        String aux;

        while (i < j) {
            while (matrix.get(i).compareTo(pivote) <= 0 && i < j) i++;
            while (matrix.get(j).compareTo(pivote) > 0) j--;
            if (i < j) {
                aux = matrix.get(i);
                matrix.set(i, matrix.get(j));
                matrix.set(j, aux);
            }
        }
        matrix.set(left, matrix.get(j));
        matrix.set(j, pivote);
        if (left < j - 1)
            quicksort(matrix, left, j - 1);
        if (j + 1 < right)
            quicksort(matrix, j + 1, right);
    }
}
