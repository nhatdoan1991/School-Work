
/*
by Nhat Doan
Class Scheduler will read an input file("input.txt") and distingush whether the input is Round Robin,Shortest Job First (SJF) 
Priority Scheduling without Preemption (PR_noPREMP), or Priority Scheduling with Preemption (PR_withPREMP)
Then, it will write to 'output.txt' file the result of scheduling the processes from the file "input.txt" 

To run the program: in the folder where there are Scheduler.java, Processes.java, SJFComparator.java, and PriorityComparator.java
Do the following command line in cmd terminal:
-----Make Executable file: 	javac Scheuduler.java
-----run: 			java Scheduler 
*/
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class Scheduler {

    private static Scanner scanner;
    private static String type;
    private static int totalProcesses;

    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        try {
            scanner = new Scanner(inputFile);
            type = scanner.next();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        switch (type) {
        case "RR":
            System.out.println("The File has Round Robin type");
            roundRobin();
            System.out.println("Please check result in output.txt");
            break;
        case "SJF":
            System.out.println("The File has Shortest Job First (SJF) type");
            SJF();
            System.out.println("Please check result in output.txt");
            break;
        case "PR_noPREMP":
            System.out.println("The File has Priority Scheduling without Preemption (PR_noPREMP) type");
            PR_noPREMP();
            System.out.println("Please check result in output.txt");
            break;
        case "PR_withPREMP":
            System.out.println("The File has Priority Scheduling witt Preemption (PR_withPREMP) type");
            prWithPremp();
            System.out.println("Please check result in output.txt");
            break;
        }
    }

    public static void roundRobin() {
        try {
            FileWriter outputFile = new FileWriter("output.txt");
            // start writing here to "output.txt"
            int timeQuantum = scanner.nextInt();
            outputFile.write(type + " " + timeQuantum + "\n");
            if (scanner.hasNext()) {
                totalProcesses = scanner.nextInt();

            } else {
                System.out.println("wrong format for input.txt file");
                outputFile.write("wrong format in input.txt file");
                outputFile.close();
                return;
            }
            int remainProcesses = totalProcesses;
            Processes processes[] = new Processes[totalProcesses];
            for (int i = 0; i < totalProcesses; i++) {
                processes[i] = new Processes(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), 0);
                scanner.nextLine();
            }
            LinkedList<Processes> queue = new LinkedList<Processes>();
            int time = 0;
            float waitTime = 0;
            for (int i = 0; i < totalProcesses; i++) {
                if ((processes[i].getArrivalTime() <= time) && (processes[i].getCpuBurst() > 0)) {
                    if (!queue.contains(processes[i])) {
                        queue.add(processes[i]);
                    }
                }
            }
            while (remainProcesses > 0) {
                if (queue.size() != 0) {
                    Processes temp = queue.peekFirst();
                    outputFile.write(time + "\t" + temp.getprocessNumber() + "\n");
                    waitTime += (time - temp.getLastBursttime());
                    if (temp.getCpuBurst() > timeQuantum) {

                        temp.setCpuBurst(temp.getCpuBurst() - timeQuantum);
                        time += timeQuantum;
                        temp.setLastBursttime(time);
                    } else if (temp.getCpuBurst() > 0) {

                        time += temp.getCpuBurst();
                        temp.setCpuBurst(0);
                        remainProcesses--;
                    }
                    for (int i = 0; i < totalProcesses; i++) {
                        if ((processes[i].getArrivalTime() <= time) && (processes[i].getCpuBurst() > 0)) {
                            if (!queue.contains(processes[i])) {
                                queue.add(processes[i]);
                            }
                        }
                    }
                    if (temp.getCpuBurst() != 0 && time != 0) {
                        queue.add(temp);
                    }
                    queue.remove();
                } else {
                    time++;
                }

            }
            outputFile.write("Average Waiting Time: " + waitTime / totalProcesses + "\n");
            // close written file
            outputFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void SJF() {
        try {
            FileWriter outputFile = new FileWriter("output.txt");
            // start writing here to "output.txt"
            outputFile.write(type + "\n");
            if (scanner.hasNext()) {
                totalProcesses = scanner.nextInt();
            } else {
                System.out.println("wrong format for input.txt file");
                outputFile.write("wrong format in input.txt file");
                outputFile.close();
                return;
            }

            Comparator<Processes> comparator = new SJFComparator();
            PriorityQueue<Processes> queue = new PriorityQueue<Processes>(totalProcesses, comparator);

            Processes processes[] = new Processes[totalProcesses];
            for (int i = 0; i < totalProcesses; i++) {
                processes[i] = new Processes(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                        scanner.nextInt());
            }
            int time = 0;
            float waitTime = 0;
            int remainProcesses = totalProcesses;
            while (remainProcesses > 0) {
                // add processes that have not been processed to priority queue base on CPU
                // burst time
                for (int i = 0; i < totalProcesses; i++) {
                    if ((processes[i].getArrivalTime() <= time) && (processes[i].getCpuBurst() > 0)) {
                        if (!queue.contains(processes[i])) {
                            queue.add(processes[i]);
                        }
                    }
                }
                // process avaiable processes
                if (queue.size() != 0) {
                    Processes temp = queue.remove();
                    waitTime = waitTime + (time - temp.getArrivalTime());
                    outputFile.write(time + "\t" + temp.getprocessNumber() + "\n");
                    time += temp.getCpuBurst();
                    temp.setCpuBurst(0);
                    remainProcesses--;
                } else {
                    // increase time to 1 if there if no processes in the queue, and if there are
                    // still processes needed to be proceeded
                    time++;
                }

            }
            outputFile.write("Average Waiting Time: " + waitTime / totalProcesses + "\n");
            // close written file
            outputFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void PR_noPREMP() {
        try {
            FileWriter outputFile = new FileWriter("output.txt");
            // start writing here to "output.txt"
            outputFile.write(type + "\n");
            if (scanner.hasNext()) {
                totalProcesses = scanner.nextInt();
            } else {
                System.out.println("wrong format for input.txt file");
                outputFile.write("wrong format in input.txt file");
                outputFile.close();
                return;
            }

            Comparator<Processes> comparator = new PriorityComparator();
            PriorityQueue<Processes> queue = new PriorityQueue<Processes>(totalProcesses, comparator);
            Processes processes[] = new Processes[totalProcesses];
            for (int i = 0; i < totalProcesses; i++) {
                processes[i] = new Processes(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                        scanner.nextInt());
            }
            int time = 0;
            float waitTime = 0;
            int remainProcesses = totalProcesses;
            while (remainProcesses > 0) {
                for (int i = 0; i < totalProcesses; i++) {
                    if ((processes[i].getArrivalTime() <= time) && (processes[i].getCpuBurst() > 0)) {
                        if (!queue.contains(processes[i])) {
                            queue.add(processes[i]);
                        }
                    }
                }
                if (queue.size() != 0) {
                    Processes temp = queue.remove();
                    waitTime = waitTime + (time - temp.getArrivalTime());
                    outputFile.write(time + "\t" + temp.getprocessNumber() + "\n");
                    time += temp.getCpuBurst();
                    temp.setCpuBurst(0);
                    remainProcesses--;
                } else {
                    time++;
                }

            }
            outputFile.write("Average Waiting Time: " + waitTime / totalProcesses + "\n");
            // close written file
            outputFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void prWithPremp() {
        try {
            FileWriter outputFile = new FileWriter("output.txt");
            // start writing here to "output.txt"
            outputFile.write(type + "\n");
            if (scanner.hasNext()) {
                totalProcesses = scanner.nextInt();
            } else {
                System.out.println("wrong format for input.txt file");
                outputFile.write("wrong format in input.txt file");
                outputFile.close();
                return;
            }

            Comparator<Processes> comparator = new PriorityComparator();
            PriorityQueue<Processes> queue = new PriorityQueue<Processes>(totalProcesses, comparator);
            Processes processes[] = new Processes[totalProcesses];
            for (int i = 0; i < totalProcesses; i++) {
                processes[i] = new Processes(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                        scanner.nextInt());
            }
            int time = 0;
            float waitTime = 0;
            int remainProcesses = totalProcesses;
            int currentProcess = 0;
            while (remainProcesses > 0) {
                for (int i = 0; i < totalProcesses; i++) {
                    if ((processes[i].getArrivalTime() <= time) && (processes[i].getCpuBurst() > 0)) {
                        if (!queue.contains(processes[i])) {
                            queue.add(processes[i]);
                        }
                    }
                }
                if (queue.size() != 0) {
                    Processes temp = queue.remove();
                    if (currentProcess != temp.getprocessNumber()) {
                        waitTime = waitTime + (time - temp.getLastBursttime());
                        outputFile.write(time + "\t" + temp.getprocessNumber() + "\n");
                        currentProcess = temp.getprocessNumber();
                    }
                    temp.setCpuBurst(temp.getCpuBurst() - 1);
                    time++;
                    temp.setLastBursttime(time);
                    if (temp.getCpuBurst() == 0) {
                        remainProcesses--;
                    } else {
                        queue.add(temp);
                    }

                } else {
                    time++;
                }
            }
            outputFile.write("Average Waiting Time: " + waitTime / totalProcesses + "\n");
            // close written file
            outputFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
