package blagrove;
import java.util.*;
public
class Priority extends Main{
    int burst[];
    int priority[];
    int arr[];
    String[] proId;
    int proNum;

    void getValues(Scanner s) {
        System.out.print("Enter the number of process needed for scheduling: ");
        int getproNum = s.nextInt();//get process number from input
        proNum = getproNum;
        burst = new int[proNum];
        priority = new int[proNum];
        arr = new int[proNum];
        proId = new String[proNum];
        String start = "P";
        for (int i = 0; i < proNum; i++) {
            proId[i] = start.concat(Integer.toString(i));
            System.out.print("Enter burst time time for process " + (i) + ":");
            burst[i] = s.nextInt();
            System.out.print("Enter arrival time for process " + (i) + ":");
            arr[i] = s.nextInt();
            System.out.print("Enter priority for process " + (i) + ":");
            priority[i] = s.nextInt();

        }

    }

    void sort(int[] arrTime, int[] burstTime, int[] prTime, String[] pid) {
        int x;
        String y;
        for (int i = 0; i < proNum; i++) {
            for (int j = 0; j < proNum - i - 1; j++) {
                if (arrTime[j] > arrTime[j + 1]) {
                    //reorder burst time
                    x = arrTime[j];
                    arrTime[j] = arrTime[j + 1];
                    arrTime[j + 1] = x;

                    //reorder burst time
                    x = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = x;

                    //reorder priority
                    x = prTime[j];
                    prTime[j] = prTime[j + 1];
                    prTime[j + 1] = x;

                    //reorder process id
                    y = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = y;

                }
                //reorder based on priority arrival times are the same
                if (arrTime[j] == arrTime[j + 1]) {
                    if (prTime[j] > prTime[j + 1]) {
                        //swapping arrival time
                        x = arrTime[j];
                        arrTime[j] = arrTime[j + 1];
                        arrTime[j + 1] = x;

                        //swapping burst time
                        x = burstTime[j];
                        burstTime[j] = burstTime[j + 1];
                        burstTime[j + 1] = x;

                        //swapping priority
                        x = prTime[j];
                        prTime[j] = prTime[j + 1];
                        prTime[j + 1] = x;

                        //swapping process identity
                        y = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = y;

                    }
                }
            }

        }
    }

    void NonPreemptive() {
        int finishTime[] = new int[proNum];
        int burstTime[] = burst.clone();
        int arrTime[] = arr.clone();
        int prTime[] = priority.clone();
        String pid[] = proId.clone();
        int waitTime[] = new int[proNum];
        int turnAroundTime[] = new int[proNum];

        sort(arrTime, burstTime, prTime, pid);

        //calculating waiting & turn-around time for each process
        finishTime[0] = arrTime[0] + burstTime[0];
        turnAroundTime[0] = finishTime[0] - arrTime[0];
        waitTime[0] = turnAroundTime[0] - burstTime[0];

        for (int i = 1; i < proNum; i++) {
            finishTime[i] = burstTime[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - arrTime[i];
            waitTime[i] = turnAroundTime[i] - burstTime[i];
        }
        float sum = 0;
        for (int num : waitTime) {
            sum += num;
        }
        float averageWaitingTime = sum / proNum;

        sum = 0;
        for (int num : turnAroundTime) {
            sum += num;
        }
        float averageTurnAroundTime = sum / proNum;

        //print on console the order of processes along with their finish time & turn around time
        System.out.println("Non-Priority Scheduling Algorithm : ");
        System.out.format("%20s%20s%20s%20s%20s%20s%20s\n", "ProcessId","Priority", "ArrivalTime","BurstTime","FinishTime", "WaitingTime", "TurnAroundTime");
        for (int i = 0; i < proNum; i++) {
            System.out.format("%20s%20d%20d%20d%20d%20d%20d\n", pid[i], prTime[i], arrTime[i], burstTime[i], finishTime[i], waitTime[i], turnAroundTime[i]);
        }

        System.out.format("%100s%20f%20f\n", "Average", averageWaitingTime, averageTurnAroundTime);
    }


}


