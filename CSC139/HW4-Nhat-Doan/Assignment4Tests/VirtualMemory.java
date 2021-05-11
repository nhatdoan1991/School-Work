package Assignment4Tests;

/*
    CSC139 Assignment 4: Virtual Memory System
    Nhat Doan - 217523684
*/
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Object;

public class VirtualMemory {

    private static Scanner scanner;
    private static FileWriter outputFile;
    private static int[] framesAray, PagesLastUsedArray, pageRequestsArray, currentFrames;
    private static int totalPage, totalFrame, totalRequests, pageFaults;
    private static int currentFrame, unmatcedFrame, frameCount;

    public static void main(String[] args) {

        File inputFile = new File("input.txt");
        try {
            scanner = new Scanner(inputFile);
            totalPage = scanner.nextInt();
            totalFrame = scanner.nextInt();
            framesAray = new int[totalFrame];
            for (int i = 0; i < framesAray.length; i++) {
                framesAray[i] = totalPage;
            }
            totalRequests = scanner.nextInt();
            pageRequestsArray = new int[totalRequests];

            for (int i = 0; i < pageRequestsArray.length; i++) {
                pageRequestsArray[i] = scanner.nextInt();
            }
            // runIt();
            FIFO();
            optimal();
            LRU();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCurrentFrame() {
        currentFrame++;
        if (currentFrame >= totalFrame) {
            currentFrame = 0;
        }
    }

    /*
     * Function FIFO: In the FIFO policy, the first page loaded into a physical
     * frame is selected for unloading (replacement).
     */
    public static void FIFO() {
        try {
            FileWriter outputFile = new FileWriter("output.txt");
            System.out.println("FIFO run:");
            outputFile.write("FIFO run:\n");
            framesAray = new int[totalFrame];
            currentFrame = 0;
            frameCount = 0;
            pageFaults = 0;
            for (int i = 0; i < pageRequestsArray.length; i++) {
                unmatcedFrame = totalFrame;
                for (int j = 0; j < totalFrame; j++) {
                    if (pageRequestsArray[i] == framesAray[j]) {
                        System.out.println("\tPage " + pageRequestsArray[i] + " already  in Frame " + j);
                        outputFile.write("\tPage " + pageRequestsArray[i] + " already  in Frame " + j + "\n");
                    } else {
                        unmatcedFrame--;
                    }
                }
                if (unmatcedFrame == 0 && frameCount < totalFrame) {
                    System.out.println("\tPage " + pageRequestsArray[i] + " loaded into Frame " + currentFrame);
                    outputFile.write("\tPage " + pageRequestsArray[i] + " loaded into Frame " + currentFrame + "\n");
                    framesAray[currentFrame] = pageRequestsArray[i];
                    updateCurrentFrame();
                    frameCount++;
                    pageFaults++;
                } else if (unmatcedFrame == 0 && frameCount >= totalFrame) {

                    System.out.println("\tPage " + framesAray[currentFrame] + " unloaded from Frame " + currentFrame
                            + ", Page " + pageRequestsArray[i] + " loaded into Frame " + currentFrame);
                    outputFile.write("\tPage " + framesAray[currentFrame] + " unloaded from Frame " + currentFrame
                            + ", Page " + pageRequestsArray[i] + " loaded into Frame " + currentFrame + "\n");
                    framesAray[currentFrame] = pageRequestsArray[i];
                    updateCurrentFrame();
                    pageFaults++;
                }
            }
            System.out.println(pageFaults + " page faults");
            outputFile.write(pageFaults + " page faults\n");
            System.out.println("------------========================================-----------");
            outputFile.write("------------========================================-----------\n");
            outputFile.close();

        } catch (IOException e) {
            System.out.println("An error occurred in opening writing file.");
            e.printStackTrace();
        }
    }

    /*
     * Function Optimal: In the Optimal policy, the page that will not be accessed
     * for the longest time in the future is selected for unloading (replacement).
     * This policy may be implemented by associating with each page-table entry a
     * number indicating its next-use time in the future. When replacement is
     * needed, replace the page with the greatest next-use number. If a page is not
     * referenced in the future, set its next-use time to INFINITY. If there are
     * multiple pages with INFINITE next-use times, replace the page that is
     * currently in the smallest frame number.
     */
    public static void optimal() {
        try {
            FileWriter outputFile = new FileWriter("output.txt", true);
            System.out.println("Optimal run:");
            outputFile.write("Optimal run:\n");
            framesAray = new int[totalFrame];
            currentFrame = 0;
            frameCount = 0;
            pageFaults = 0;
            for (int i = 0; i < pageRequestsArray.length; i++) {
                unmatcedFrame = totalFrame;
                for (int j = 0; j < totalFrame; j++) {
                    if (pageRequestsArray[i] == framesAray[j]) {
                        System.out.println("\tPage " + pageRequestsArray[i] + " already  in Frame " + j);
                        outputFile.write("\tPage " + pageRequestsArray[i] + " already  in Frame " + j + "\n");
                    } else {
                        unmatcedFrame--;
                    }
                }
                if (unmatcedFrame == 0 && frameCount < totalFrame) {
                    System.out.println("\tPage " + pageRequestsArray[i] + " loaded into Frame " + currentFrame);
                    outputFile.write("\tPage " + pageRequestsArray[i] + " loaded into Frame " + currentFrame + "\n");
                    framesAray[currentFrame] = pageRequestsArray[i];
                    updateCurrentFrame();
                    frameCount++;
                    pageFaults++;
                } else if (unmatcedFrame == 0 && frameCount >= totalFrame) {
                    int[] temp = new int[totalFrame];
                    int index = 0;
                    boolean flag = false;
                    for (int k = 0; k < totalFrame; k++) {
                        for (int j = i + 1; j < pageRequestsArray.length; j++) {
                            if (framesAray[k] == pageRequestsArray[j]) {
                                if (temp[k] < (j - i)) {
                                    temp[k] = j - i;
                                    index = k;
                                    j = pageRequestsArray.length;
                                }
                            }
                        }
                        if (temp[k] == 0) {
                            index = k;
                            k = totalFrame;
                            flag = true;
                        }
                    }
                    if (!flag) {
                        index = maxOfArray(temp);
                    }
                    System.out.println("\tPage " + framesAray[index] + " unloaded from Frame " + index + ", Page "
                            + pageRequestsArray[i] + " loaded into Frame " + index);
                    outputFile.write("\tPage " + framesAray[index] + " unloaded from Frame " + index + ", Page "
                            + pageRequestsArray[i] + " loaded into Frame " + index + "\n");
                    framesAray[index] = pageRequestsArray[i];
                    pageFaults++;
                }
            }
            System.out.println(pageFaults + " page faults");
            outputFile.write(pageFaults + " page faults\n");
            System.out.println("------------========================================-----------");
            outputFile.write("------------========================================-----------\n");
            outputFile.close();

        } catch (IOException e) {
            System.out.println("An error occurred in opening writing file.");
            e.printStackTrace();
        }
    }

    public static int maxOfArray(int[] array) {
        int max = 0;
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
                temp = i;
            }
        }
        return temp;
    }

    public static int indexOfValue(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target)
                return i;
        }
        return -1;
    }

    /*
     * Function LRU: In the LRU policy, the page that has not been accessed for the
     * longest time is selected for unloading (replacement). This policy may be
     * implemented by associating with each page-table entry a time stamp indicating
     * the latest time at which the page was accessed. When replacement is needed,
     * replace the page with the smallest time stamp.
     */
    public static void LRU() {
        try {
            FileWriter outputFile = new FileWriter("output.txt", true);
            System.out.println("LRU run:");
            outputFile.write("LRU run:\n");
            framesAray = new int[totalFrame];
            currentFrame = 0;
            frameCount = 0;
            pageFaults = 0;
            PagesLastUsedArray = new int[totalPage];
            for (int j = 0; j < totalPage; j++) {
                PagesLastUsedArray[j] = -1;
            }
            currentFrames = new int[totalPage];
            for (int i = 0; i < pageRequestsArray.length; i++) {
                unmatcedFrame = totalFrame;
                for (int j = 0; j < totalFrame; j++) {
                    if (pageRequestsArray[i] == framesAray[j]) {
                        System.out.println("\tPage " + pageRequestsArray[i] + " already  in Frame " + j);
                        outputFile.write("\tPage " + pageRequestsArray[i] + " already  in Frame " + j + "\n");
                        int temp = PagesLastUsedArray[pageRequestsArray[i]];
                        for (int k = 0; k < totalPage; k++) {
                            if (PagesLastUsedArray[k] >= 0 && PagesLastUsedArray[k] <= temp) {
                                PagesLastUsedArray[k] = PagesLastUsedArray[k] + 1;
                            }
                        }
                        PagesLastUsedArray[pageRequestsArray[i]] = 0;
                    } else {
                        unmatcedFrame--;
                    }
                }
                if (unmatcedFrame == 0 && frameCount < totalFrame) {
                    System.out.println("\tPage " + pageRequestsArray[i] + " loaded into Frame " + currentFrame);
                    outputFile.write("\tPage " + pageRequestsArray[i] + " loaded into Frame " + currentFrame + "\n");
                    framesAray[currentFrame] = pageRequestsArray[i];
                    currentFrames[pageRequestsArray[i]] = currentFrame;
                    updateCurrentFrame();
                    for (int j = 0; j < totalPage; j++) {
                        if (PagesLastUsedArray[j] >= 0) {
                            PagesLastUsedArray[j] = PagesLastUsedArray[j] + 1;
                        }
                    }
                    PagesLastUsedArray[pageRequestsArray[i]] = 0;
                    frameCount++;
                    pageFaults++;
                } else if (unmatcedFrame == 0 && frameCount >= totalFrame) {
                    int temp = indexOfValue(PagesLastUsedArray, totalFrame - 1);
                    // int temp1 = PagesLastUsedArray[pageRequestsArray[i]];
                    for (int j = 0; j < totalPage; j++) {
                        if (PagesLastUsedArray[j] >= 0) {
                            PagesLastUsedArray[j] = PagesLastUsedArray[j] + 1;
                        }
                    }
                    PagesLastUsedArray[pageRequestsArray[i]] = 0;
                    PagesLastUsedArray[temp] = -1;
                    System.out.println("\tPage " + temp + " unloaded from Frame " + currentFrames[temp] + ", Page "
                            + pageRequestsArray[i] + " loaded into Frame " + currentFrames[temp]);
                    outputFile.write("\tPage " + temp + " unloaded from Frame " + currentFrames[temp] + ", Page "
                            + pageRequestsArray[i] + " loaded into Frame " + currentFrames[temp] + "\n");
                    framesAray[currentFrames[temp]] = pageRequestsArray[i];
                    currentFrames[pageRequestsArray[i]] = currentFrames[temp];
                    currentFrames[temp] = totalPage;
                    pageFaults++;
                }
            }
            System.out.println(pageFaults + " page faults");
            outputFile.write(pageFaults + " page faults\n");
            System.out.println("------------========================================-----------");
            outputFile.write("------------========================================-----------\n");
            outputFile.close();

        } catch (

        IOException e) {
            System.out.println("An error occurred in opening writing file.");
            e.printStackTrace();
        }
    }
}
