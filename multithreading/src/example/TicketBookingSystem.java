package example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TicketBookingSystem {
    private static final int THREAD_POOL_SIZE = 3;
    private static final int TOTAL_BOOKINGS = 5;

    public static void main(String[] args) {
        ExecutorService bookingExecutor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int bookingId = 1; bookingId <= TOTAL_BOOKINGS; bookingId++) {
            bookingExecutor.execute(new TicketBookingTask(bookingId));
        }

        bookingExecutor.shutdown();

        try {
            if (!bookingExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                bookingExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            bookingExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

class TicketBookingTask implements Runnable {
    private final int bookingId;

    public TicketBookingTask(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public void run() {
        try {
            System.out.println("Booking " + bookingId + ": Booking received by " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Booking " + bookingId + ": Payment processed");
            Thread.sleep(1000);
            System.out.println("Booking " + bookingId + ": Ticket confirmed");
        } catch (InterruptedException e) {
            System.err.println("Booking " + bookingId + ": Interrupted - " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Booking " + bookingId + ": Error - " + e.getMessage());
        }
    }
}