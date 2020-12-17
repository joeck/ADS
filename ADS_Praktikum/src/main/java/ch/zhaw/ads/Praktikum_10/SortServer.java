package ch.zhaw.ads.Praktikum_10;

import ch.zhaw.ads.CommandExecutor;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class SortServer implements CommandExecutor {

    private final File bubble;
    private final File selection;
    private final File insertion;
    private final File bubbleWords;
    private final File selectionWords;
    private final File insertionWords;
    private final ArrayList<File> numberFiles;
    private final ArrayList<File> wordFiles;
    private String[] stringsToSort;

    private boolean sorted;

    public SortServer() {
        numberFiles = new ArrayList<>();
        wordFiles = new ArrayList<>();
        numberFiles.add(bubble = new File("bubble.log"));
        numberFiles.add(selection = new File("selection.log"));
        numberFiles.add(insertion = new File("insertion.log"));
        wordFiles.add(bubbleWords = new File("bubblewords.log"));
        wordFiles.add(selectionWords = new File("selectionwords.log"));
        wordFiles.add(insertionWords = new File("insertionwords.log"));
    }

    // input: algorithm {numbersToSort} {executions}
    // algorithms available: bubble, selection, insertion, all, bubblewords, selectionwords, insertionwords, allwords
    // e.g.:
    //      - bubble 10000 1        --> one time run 10000 numbers to sort
    //      - all 5000 10          --> ten runs 5000 numbers to sort
    //      - bubblewords 10000 5   --> five runs with 10000 randomized words
    //      - allwords 1000 23      --> 23 runs with 1000 randomized words
    @Override
    public String execute(String input) throws Throwable {
        String[] commands = input.toLowerCase().split(" ");
        String command = commands[0];
        int amount = parseInt(commands[1]);
        int executions = parseInt(commands[2]);
        String[] strings;
        Integer[] integers = new Integer[amount];

        switch(command) {
            case "allwords":
                strings = getRandomWords(amount);
                getTimeAll(strings, executions);
                return "Arrays got sorted\n";
            case "bubblewords":
            case "selectionwords":
            case "insertionwords":
                strings = getRandomWords(amount);
                getTimeToWrite(getFileFromString(command), strings, executions);
                if (sorted) {
                    return ("Array got sorted.\n"); //+ getSortedString(integers));
                } else return "Array not sorted.\n";
            case "all":
                generateNumbers(parseInt(commands[1]), integers);
                getTimeAll(integers, executions);
                return "Arrays got sorted\n";
            default:
                generateNumbers(parseInt(commands[1]), integers);
                getTimeToWrite(getFileFromString(command), integers, executions);
                if (sorted) {
                    return ("Array got sorted.\n"); //+ getSortedString(integers));
                } else return "Array not sorted.\n";
        }
    }

    static <K extends Comparable> void BubbleSortG(K[] a) {
        for (int k = a.length-1; k > 0; k--){
            // bubbleUp
            boolean noSwap = true;
            for (int i = 0; i < k; i++) {
                if (a[i].compareTo(a[i+1]) > 0) {
                    swap(a, i, i + 1);
                    noSwap = false;
                }
            }
            if (noSwap) break;
        }
    }

    static <K extends Comparable> void SelectionSort(K[] a) {
        for (int k = 0; k < a.length; k++) {
            int min = k;
            for (int i = k + 1; i < a.length; i ++) {
                if (a[i].compareTo(a[min]) < 0) min = i;
            }
            if (min != k) swap (a, min, k);
        }
    }

    static <K extends Comparable> void InsertionSort(K[] a) {
        for (int k = 1; k < a.length; k++) {
            K x = a[k];
            int i;
            for (i = k; ((i > 0) && (a[i-1].compareTo(x) > 0)); i--)
                a[i] = a[i-1];
            a[i] = x;
        }
    }

    private void getTimeToWrite(File fileToWrite, String[] arrayToSort, int executions) {
        stringsToSort = new String[arrayToSort.length];
        for(int index = 0; index < executions; index++) {
            System.arraycopy(arrayToSort, 0, stringsToSort, 0, arrayToSort.length);
            //System.out.println(Arrays.toString(stringsToSort));
            long end, start = System.currentTimeMillis();
            int count = 0;
            do {
                if(fileToWrite.getName().equals("bubblewords.log")) BubbleSortG(stringsToSort);
                if(fileToWrite.getName().equals("selectionwords.log")) SelectionSort(stringsToSort);
                if(fileToWrite.getName().equals("insertionwords.log")) InsertionSort(stringsToSort);
                count++;
                end = System.currentTimeMillis();
            } while (end - start < 5000);
            double time = (double)(end - start) / count;
            sorted = checkSorted(stringsToSort);
            //System.out.println(Arrays.toString(stringsToSort));
            writeToFile(fileToWrite, time, index, executions);
        }
    }

    private void getTimeToWrite(File fileToWrite, Integer[] a, int executions) {
        Integer[] numbersToSort = new Integer[a.length];
        for(int index = 0; index < executions; index++) {
            System.arraycopy(a, 0, numbersToSort, 0, a.length);
            //System.out.println(Arrays.toString(numbersToSort));
            long end, start = System.currentTimeMillis();
            int count = 0;
            do {
                if(fileToWrite.getName().equals("bubble.log")) BubbleSortG(numbersToSort);
                if(fileToWrite.getName().equals("selection.log")) SelectionSort(numbersToSort);
                if(fileToWrite.getName().equals("insertion.log")) InsertionSort(numbersToSort);
                count++;
                end = System.currentTimeMillis();
            } while (end - start < 5000);
            double time = (double)(end - start) / count;
            sorted = checkSorted(numbersToSort);
            //System.out.println(Arrays.toString(numbersToSort));
            writeToFile(fileToWrite, time, index, executions);
        }
    }

    private void getTimeAll(Integer[] a, int executions) {
        for(File f : numberFiles) {
            getTimeToWrite(f, a, executions);
        }
    }

    private void getTimeAll(String[] a, int executions) {
        for(File f : wordFiles) {
            getTimeToWrite(f, a, executions);
        }
    }

    private void writeToFile(File fileToWrite, double time, int index, int executions) {
        boolean APPEND = true;
        try(FileWriter fileWriter = new FileWriter(fileToWrite, APPEND)) {
            if(index == 0) fileWriter.append(';');
            if ((index < executions - 1)) {
                fileWriter.write(time + ";");
                System.out.println(fileToWrite.getName() + "--> Round finished: " + index);
            } else {
                fileWriter.write(time + "");
                System.out.println(fileToWrite.getName() + "--> Round finished: " + index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) return false;
        }
        return true;
    }

    private static <K> void swap(K[] k, int i, int j) {
        K h = k[i]; k[i] = k[j]; k[j] = h;
    }

    private String[] removeNull(String[] temp) {
        return Arrays.stream(temp).filter(Objects::nonNull).toArray(String[]::new);
    }

    private void shuffleArray(String[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            String temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    private File getFileFromString(String filename) {
        File toReturn = null;
        filename = filename.concat(".log");
        for(File f : numberFiles) {
            if (filename.equals(f.getName())) {
                toReturn = f;
                break;
            }
        }
        for(File f : wordFiles) {
            if (filename.equals(f.getName())) {
                toReturn = f;
                break;
            }
        }
        return toReturn;
    }

    private void generateNumbers(int numbersToSort, Integer[] a) {
        Random random = new Random();
        for(int i = 0; i < numbersToSort; i++) {
            a[i] = random.nextInt(1000000);
        }
    }

    private String[] getRandomWords(int amount) throws IOException {
        File wordlist = new File("/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Loesungen/ADS_Praktikum/src/main/resources/ch/zhaw/ads/Praktikum_10/wordlist-german.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(wordlist), StandardCharsets.UTF_8));
        String[] temp = new String[amount];
        String line;
        int counter = 0;
        double border = getBorder(amount);
        while ((line = br.readLine()) != null && counter < amount) {
            // 1'908'815 words
            if(Math.random() > border) {
                temp[counter] = line;
                counter++;
            }
        }
        shuffleArray(temp);
        temp = removeNull(temp);
        return temp;
    }

    private double getBorder(int amount) {
        if(amount > 25000) {
            return 0.975;
        } else if(amount < 1000){
            return 0.9994;
        } else return 0.99;
    }

    private String getSortedString(Integer[] a) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : a) {
            sb.append(integer).append("\n");
        }
        return String.valueOf(sb);
    }

    private String getSortedString(String[] a) {
        StringBuilder sb = new StringBuilder();
        for (String str : a) {
            sb.append(str).append("\n");
        }
        return String.valueOf(sb);
    }
}
