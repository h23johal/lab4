import java.util.Random;
import java.util.Scanner;

public class Weather {
        int daysInMay = 31;
        int[] temp = new int[daysInMay];
        String[] days = new String[daysInMay];
        int[] origTemp = new int[daysInMay];
        String[] origDays = new String[daysInMay];

        Weather() {
            randomData();
        }
    public void wrongInput(){
        System.out.println("välj ett korrekt alternativ, 1-11");
    }
    public void randomData() {
        Random random = new Random();
        for (int i = 0; i < daysInMay; i++) {
            temp[i] = random.nextInt(15) + 10;
            days[i] = (i + 1) + ":e maj";
            origTemp[i] = random.nextInt(15) + 10;
            origDays[i] = (i + 1) + ":e maj";
        }
    }
    public void list() {
        for (int i = 0; i < daysInMay; i++) {
            System.out.println(days[i] + " var det: " + temp[i] + "grader");
        }
    }
    public void listHotDays() {
        for (int i = 0; i < daysInMay; i++) {
            if (temp[i] >= 20) {
                System.out.println(days[i] + " var det: " + temp[i] + "grader");
            }
        }
    }
    public void sortTemperatures(boolean ascending) {
        for (int i = 0; i < daysInMay - 1; i++) {
            for (int j = 0; j < daysInMay - i - 1; j++) {
                if (ascending && temp[j] > temp[j + 1] || !ascending && temp[j] < temp[j + 1]) {
                    // Byt plats på elementen
                    int tempValue = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempValue;

                    String tempDay = days[j];
                    days[j] = days[j + 1];
                    days[j + 1] = tempDay;
                }
            }
        }
    }
    public void sortReset() {
        for (int i = 0; i < daysInMay; i++) {
            days[i] = origDays[i];
            temp[i] = origTemp[i];
            }
        }

    public void beforeAfter(Scanner scanner) {
        System.out.println("vilken dag gäller det? ");
        int day = scanner.nextInt() - 1;
        System.out.println(days[day] + " var det det " + temp[day] + "grader" );
        System.out.println("Dagen före " + days[day] + " var det den " + days[day - 1] + " och då var det " + temp[day - 1] + "grader" );
        System.out.println("Dagen efter " + days[day] + " var det den " + days[day + 1] + " och då var det " + temp[day + 1] + "grader" );

    }
    public void average() {
            double sum = 0;
            for (int i = 0;i < daysInMay; i++) {
                sum += temp[i];
            }
            double calcSum = sum / daysInMay;
        System.out.println("Medel temperaturen i maj var: " + String.format("%.2f", calcSum) + " grader");
    }
    public void warmestDay() {
            int biggest=temp[0];
            int index = 0;
            for (int i = 0; i < daysInMay; i++) {
                if (temp[i] > biggest) {
                    biggest=temp[i];
                    index = i;
                }

            }
        System.out.println("varmaste dagen var den: " + days[index] + " och då var det " + biggest + " grader");
    }
    public void coldestDay() {
            int lowest = temp[0];
            int index = 0;
            for (int i = 0; i < daysInMay; i++) {
                if (temp[i]<lowest) {
                    lowest=temp[i];
                    index = i;
                }
            }
        System.out.println("Kallaste dagen var den: " + days[index] + " och då var det" + lowest + "grader");

        }
    public void median() {
        int median = 0;
        double split = (double) daysInMay /2;
        int halfOfDaysInMay = (int) Math.ceil(split);
        sortTemperatures(true);
        for (int i = 0;i < halfOfDaysInMay; i++) {
            median = temp[i];
        }
        System.out.println("Mediantemperaturen i maj var: " + (median) + " grader");
    }
    public void commonTemp() {
        int mostCommonTemp = temp[0];
        int highestCount = 0;

        for (int i = 0; i < daysInMay; i++) {
            int count = 0;
            for (int j = 0; j < daysInMay; j++) {
                if (temp[j] == temp[i]) {
                    count++;
                }
            }
            if (count > highestCount) {
                highestCount = count;
                mostCommonTemp = temp[i];
            }
        }

        // Print the most common temperature
        System.out.println("Den mest förekommande temperaturen i maj var: " + mostCommonTemp + " grader");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Weather weather = new Weather();
        boolean running = true;

        //System.out.println(weather.days[input]);
        while (running){
            weather.sortReset();
            System.out.println("\nJasså du är meteorolog! Här har du ett program du kan leka i");
            System.out.println("Välj några av följande alternativ: ");
            System.out.println("1. Lista över temperaturerna för varje dag i maj");
            System.out.println("2. medeltemperaturen i maj");
            System.out.println("3. Vilken dag var varmast?");
            System.out.println("4. Vilken dag var kallast?");
            System.out.println("5. Sortera Majs dagar efter temperatur. kallast först!");
            System.out.println("6. Sortera Majs dagar efter temperatur. varmast först!");
            System.out.println("7. Välj dag i maj du vill veta mer om. inklusive dagen före och efter");
            System.out.println("8. mediantemperaturen i maj");
            System.out.println("9. Lista dagar i maj där temperaturen var 20 grader eller varmare");
            System.out.println("10. Den mest förekommande temperaturen i maj");
            System.out.println("11. Avsluta");
           switch(scanner.next()) {
               case "1":
                   weather.list();
                   break;
               case "2":
                   weather.average();
                   break;
               case "3":
                   weather.warmestDay();
                   break;
               case "4":
                   weather.coldestDay();
                   break;
               case "5":
                   weather.sortTemperatures(true); // kallast först
                   weather.list();
                   break;
               case "6":
                   weather.sortTemperatures(false);// varmast först
                   weather.list();
                   break;
               case "7":
                   weather.beforeAfter(scanner);
                   break;
               case "8":
                   weather.median();
                   break;
               case "9":
                   weather.listHotDays();
                   break;
               case "10":
                   weather.commonTemp();
                   break;
               case "11":
                   running = false;
                   break;
               default:
                   System.out.println("välj ett av alternativen i listan");
                   break;

           }
        }
        //weather.warmestDay();
        //weather.coldestDay();
        //weather.beforeAfter(input);
        //weather.average();
        //weather.sortTemperatures(true); // Sortera i stigande ordning
        //weather.sortTemperatures(false);// Sortera fallande ordning
        //weather.list(); // Visa den sorterade listan
        System.out.println("avslutar...");
        scanner.close();
        }
    }
