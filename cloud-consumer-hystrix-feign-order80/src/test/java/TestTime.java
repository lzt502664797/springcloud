import java.util.concurrent.TimeUnit;

public class TestTime {
    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            System.out.println("error");
        }
    }
}
