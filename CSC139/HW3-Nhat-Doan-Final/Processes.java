/*
by Nhat Doan
Process class to hold an process object where store the processnumber, arrivaltime, cpuburst and priority
*/
public class Processes {
    private int processNumber;
    private int arrivalTime;
    private int cpuBurst;
    private int priority;
    private int lastBursttime;

    public Processes(int processNumber, int arrivalTime, int cpuBurst, int priority) {
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
        this.priority = priority;
        this.lastBursttime = arrivalTime;
    }

    public void setCpuBurst(int cpuBurst) {
        this.cpuBurst = cpuBurst;
    }

    public void setProcessNumber(int processNumber) {
        this.processNumber = processNumber;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setLastBursttime(int lastBursttime) {
        this.lastBursttime = lastBursttime;
    }

    public int getprocessNumber() {
        return this.processNumber;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getCpuBurst() {
        return this.cpuBurst;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getLastBursttime() {
        return this.lastBursttime;
    }
}