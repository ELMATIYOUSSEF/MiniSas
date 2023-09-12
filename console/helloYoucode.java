package console;
import static console.TextColor.*;

public class helloYoucode {
    public static void helloYoucode() {
        System.out.print(BLEU);
        System.out.println("\n\n");
        System.out.println("\t\t\t\t _ __");
        System.out.println("\t\t\t\t/// / __ /7/7 _    _ __  _         __  _    _// __");
        System.out.println("\t\t\t\t/ ` /,'o/////,'o|   \\V /,'o| /7/7 ,',','o| ,'o/,'o/");
        System.out.println("\t\t\t\t/_n_/ |_(//// |_,'    )/ |_,'/__/  \\_\\ |_,'|__/ |_(");
        System.out.println("\t\t\t\t                    //                            ");
        System.out.print(RESET);
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
