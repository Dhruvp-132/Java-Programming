import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ride implements RideInterface {
    private String rideName;
    private int maxRiders;
    private Employee rideOperator;
    private Queue<Visitor> waitingLine = new LinkedList<>();
    private LinkedList<Visitor> rideHistory = new LinkedList<>();
    private int numOfCycles = 0;
    private Lock lock = new ReentrantLock(); // Create a ReentrantLock

    public Ride() {
    }

    public Ride(String rideName, int maxRiders) {
        this.rideName = rideName;
        this.maxRiders = maxRiders;
    }

    // Getters and Setters Methods
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public int getMaxRiders() {
        return maxRiders;
    }

    public void setMaxRiders(int maxRiders) {
        this.maxRiders = maxRiders;
    }

    public Employee getRideOperator() {
        return rideOperator;
    }

    public void setRideOperator(Employee rideOperator) {
        this.rideOperator = rideOperator;
    }

    public LinkedList<Visitor> getRideHistory() {
        return rideHistory;
    }

    // RideManagement Methods
    @Override
    public void AddVisitorToQueue(Visitor visitor) {
        lock.lock(); // Lock before accessing the waitingLine
        try {
            waitingLine.add(visitor);
        } catch (Exception e) {
            System.err.println("Error adding visitor to queue: " + e.getMessage());
        } finally {
            lock.unlock(); // Unlock after accessing the waitingLine
        }
    }

    @Override
    public void RemoveVisitorFromQueue(Visitor visitor) {
        lock.lock(); // Lock before accessing the waitingLine
        try {
            waitingLine.remove(visitor);
            System.out.println(visitor.getName() + " removed from the queue.");
        } catch (Exception e) {
            System.err.println("Error removing visitor from queue: " + e.getMessage());
        } finally {
            lock.unlock(); // Unlock after accessing the waitingLine
        }
    }


    @Override
    public void PrintQueue() {
        lock.lock(); // Lock before accessing the waitingLine
        try {
            System.out.println("Current Queue:");
            for (Visitor visitor : waitingLine) {
                System.out.println(visitor.getName() + " added to the queue.");
            }
        } catch (Exception e) {
            System.err.println("Error printing queue: " + e.getMessage());
        } finally {
            lock.unlock(); // Unlock after accessing the waitingLine
        }
    }

    @Override
    public void RunOneCycle() {
        lock.lock(); // Lock before accessing the waitingLine and rideHistory
        try {
            if (rideOperator == null) {
                System.out.println("Ride cannot be run. No ride operator assigned.");
                return;
            }

            if (waitingLine.isEmpty()) {
                System.out.println("Ride cannot be run. No visitors in the queue.");
                return;
            }
            System.out.println("Running one cycle of the ride...");
            int ridersCount = Math.min(maxRiders, waitingLine.size());
            for (int i = 0; i < ridersCount; i++) {
                Visitor visitor = waitingLine.poll();
                if (visitor!= null) {
                    rideHistory.add(visitor);
                    System.out.println(visitor.getName() + " enjoyed the ride!");
                }
            }
            numOfCycles++;
            System.out.println("Ride cycle completed. Number of cycles: " + numOfCycles);
        } catch (Exception e) {
            System.err.println("Error running one cycle: " + e.getMessage());
        } finally {
            lock.unlock(); // Unlock after accessing the waitingLine and rideHistory
        }
    }

    @Override
    public void PrintRideHistory() {
        lock.lock(); // Lock before accessing the rideHistory
        try {
            System.out.println("Ride History:");
            Iterator<Visitor> iterator = rideHistory.iterator();
            if (iterator.hasNext()) {
                do {
                    Visitor visitor = iterator.next();
                    System.out.println(visitor.getName());
                } while (iterator.hasNext());
            }
        } catch (Exception e) {
            System.err.println("Error printing ride history: " + e.getMessage());
        } finally {
            lock.unlock(); // Unlock after accessing the rideHistory
        }
    }

    // Part 4A

    public void AddVisitorToRideHistory(Visitor visitor) {
        lock.lock(); // Lock before accessing the rideHistory
        try {
            rideHistory.add(visitor);
            System.out.println(visitor.getName() + " added to ride history.");
        } catch (Exception e) {
            System.err.println("Error adding visitor to ride history: " + e.getMessage());
        } finally {
            lock.unlock(); // Unlock after accessing the rideHistory
        }
    }

    public boolean CheckVisitorInRideHistory(Visitor visitor) {
        lock.lock(); // Lock before accessing the rideHistory
        try {
            return rideHistory.contains(visitor);
        } catch (Exception e) {
            System.err.println("Error checking visitor in ride history: " + e.getMessage());
            return false;
        } finally {
            lock.unlock(); // Unlock after accessing the rideHistory
        }
    }

    public int GetNumberOfVisitorsInRideHistory() {
        lock.lock(); // Lock before accessing the rideHistory
        try {
            return rideHistory.size();
        } catch (Exception e) {
            System.err.println("Error getting number of visitors in ride history: " + e.getMessage());
            return 0;
        } finally {
            lock.unlock(); // Unlock after accessing the rideHistory
        }
    }

    //Part4B

    public void SortRideHistory() {
        lock.lock();
        try {
            Collections.sort(rideHistory, new VisitorComparator());
            System.out.println("Ride history sorted.");
        } catch (Exception e) {
            System.err.println("Error sorting ride history: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
    // Part 6
    public void writeRideHistoryToFile(String filename) {
        lock.lock();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Visitor visitor : rideHistory) {
                writer.println(visitor.getName() + "," + visitor.getAge() + "," + visitor.getEmail() + "," + visitor.getVisitorId() + "," + visitor.getTicketType());
            }
            System.out.println("Ride history successfully written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
    // Part 7
    public void readRideHistoryFromFile(String filename) {
        lock.lock();
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    String email = parts[2];
                    String visitorId = parts[3];
                    String ticketType = parts[4];
                    Visitor visitor = new Visitor(name, age, email, visitorId, ticketType);
                    rideHistory.add(visitor);
                    System.out.println("Visitor added to ride history: " + visitor.getName());
                } else {
                    System.err.println("Error: Invalid file format.");
                }
            }
            System.out.println("Ride history successfully read from " + filename);
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Error: File not found.");
        } finally {
            lock.unlock();
        }
    }

}