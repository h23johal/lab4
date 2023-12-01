//Importerar klassen Random från paketet java.util
import java.util.Random;
//Importerar klassen Scanner från paketet java.util
import java.util.Scanner;
//Deklarerar klassen Weather
public class Weather {
    //Deklarerar variabeln daysInMay. Ger den datatypen integer och värdet 31.
    //Gör den även final dvs en konstant så den inte går att ändra.
    final int daysInMay = 31;
    //Deklarerar en array med datatypen integer och gör den lika lång som dagar i maj.
    int[] temp = new int[daysInMay];
    //Deklarerar en array med datatypen string och gör den lika lång som dagar i maj.
    String[] days = new String[daysInMay];
    //Skapar två backup array för att kunna hålla ursprungsvärden.
    int[] origTemp = new int[daysInMay];
    String[] origDays = new String[daysInMay];
    //En konstruktör för klassen Weather.
    //När ett nytt objekt skapas i klassen Weather kommer metoden randomData() anropas.
    Weather() {
        randomData();
    }
    //Tre stycken metoder för att skicka prints vid felkorrigering.
    public void wrongInputMenu(){
        System.out.println("välj ett korrekt alternativ, 1-11");
    }
    public void wrongInputDays(){
        System.out.println("välj ett korrekt alternativ, 1-31");
    }
    public void wrongInputSort(){
        System.out.println("Finns ingen dag med den temperaturen. Välj en annan");
    }
    //Metoden för att slumpa ut värden till våra arrayer.
    public void randomData() {
        //Deklarerar variabeln random.
        //Skapar en ny instans av Random klassen.
        Random random = new Random();
        //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
        for (int i = 0; i < daysInMay; i++) {
            //Tilldelar temp[i] ett slumpat värde 10-24. För att simulera rimliga temperaturer i maj.
            //Random objektet använder metoden nextInt för att slumpa ett tal 0-14 (15 tal) +10
            temp[i] = random.nextInt(15) + 10;
            //För att synkronisera en dag med en temperatur.
            //IF-sats för att fixa så att det inte står 1:e 2:e osv.
            if (i == 0 ||i ==  1 ||i ==  20 ||i ==  21 ||i ==  30) {
                days[i] = (i + 1) + ":a maj";
            }
            else {
                days[i] = (i + 1) + ":e maj";
            }
            //Gör samma sak för våra backup arrayer. För reset metoden senare.
            origTemp[i] = random.nextInt(15) + 10;
            if (i == 0 ||i ==  1 ||i ==  20 ||i ==  21 ||i ==  30) {
                origDays[i] = (i + 1) + ":a maj";
            }
            else {
                origDays[i] = (i + 1) + ":e maj";
            }
        }
    }
    //Metod för att skriva ut listan av dagar och temperaturer i ordning.
    public void list() {
        //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
        for (int i = 0; i < daysInMay; i++) {
            //Skriver dag och temperatur för varje dag i maj
            System.out.println(days[i] + " var det: " + temp[i] + "grader");
        }
    }
    //Skapar metod för att kunna sortera bort temperaturer som understiger det valda värdet. Med parametern scanner.
    public void listHotDays(Scanner scanner) {
        //deklarerar en boolean variabel och sätter värdet true
        boolean running = true;
        //En while loop för felkorrigering.
        while (running) {
            //Print vilken temp
            System.out.println("vilken temp");
            //Om inmatningen har en integer
            if (scanner.hasNextInt()) {
                // Deklarerar en variabel tempSort och ger den värdet från inmatningen genom scanner objektet och nextInt metoden.
                int tempSort = scanner.nextInt();
                //Om temperaturen är lika med eller större än 10 OCH är mindre eller lika med 24.
                if (10 <= tempSort && tempSort <= 24) {
                    //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
                    for (int i = 0; i < daysInMay; i++) {
                        //Om temperaturen är lika med eller högre än det valda värdet
                        if (temp[i] >= tempSort) {
                            //Skriv ut resultatet
                            System.out.println(days[i] + " var det: " + temp[i] + "grader");
                        } //if satsen
                    }//for loopen
                    running = false;
                }//andra if satsen
                else {
                    wrongInputSort();
                }//avslutar else
            }//första if satsen
            else {
                System.out.println("måste skriva ett datum i siffror");
                scanner.next();
            }//avslutar else

        }//while loopen
    }//metoden
    //Skapar en metod för att sortera dagar i ordning efter temperatur.
    //Hämtar parametern ascending. Om true, stigande ordning. Om false, fallande ordning.
    //Använder oss av bubblesorting algorithm.
    public void sortTemperatures(boolean ascending) {
        //Yttre for loop. Initieringen börjar på noll. Villkoret är dagar i maj -1(för att synkronisera mot index). Iteration +1.
        for (int i = 0; i < daysInMay - 1; i++) {
            //Inre for loop. Initieringen börjar på noll. Villkoret är dagar i maj -i(för att inte kolla samma dag igen) -1(för att synkronisera mot index). Iteration +1.
            for (int j = 0; j < daysInMay - i - 1; j++) {
                //If sats för att titta på om talet bredvid j är högre än j OM ascending är true annars så gör man tvärtom
                if (ascending && temp[j] > temp[j + 1] || !ascending && temp[j] < temp[j + 1]) {
                    // Byt plats på elementen om talet bredvid är högre/lägre.
                    //Skapar en tillfällig variabel tempTemp för att hålla temperaturen under sorteringen.
                    int tempTemp = temp[j];
                    //Byter plats. Flyttar talet ett steg åt ner.
                    temp[j] = temp[j + 1];
                    //Lägger tillbaka värdet från tempTemp.
                    temp[j + 1] = tempTemp;

                    //Gör samma sak för dagarna för att synkronisera positionen.
                    String tempDay = days[j];
                    //Byter plats. Flyttar talet ett steg ner.
                    days[j] = days[j + 1];
                    //Lägger tillbaka värdet från tempDay.
                    days[j + 1] = tempDay;
                }
            }
        }
    }
    //Skapar en metod för att kunna sortera tillbaka temperaturer och dagar till den ursprungliga ordningen.
    public void sortReset() {
        //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
        for (int i = 0; i < daysInMay; i++) {
            //Lägger tillbaka ursprungliga värden
            days[i] = origDays[i];
            temp[i] = origTemp[i];
        }
    }

    public void beforeAfter(Scanner scanner) {
        System.out.println("vilken dag gäller det? ");
        //Startar en while loop för felkorrigering.
        //Deklarerar en boolean running och ger den värdet true.
        boolean running = true;
        while (running) {
            //om scanner har en int.
            if (scanner.hasNextInt()) {
                //Deklarerar en integer day och tilldelar den ett integer värde från objektet scanner och metoden nextInt.
                //Korrigerar inputen med -1 för att synkronisera mot index.
                int day = scanner.nextInt() - 1;
                //om man väljer dag 1-31.
                if (0 <= day && day <= 30) {
                    //Skriver ut dag och temperatur den dagen man valt
                    System.out.println(days[day] + " var det det " + temp[day] + "grader");
                    //Skapar en if-sats för om dagen innan man valt före 1a maj så får man ett felmeddelande.
                    if (0 < day) {
                        //Skriver ut dag och temperatur dagen innan dagen man valt
                        System.out.println("Dagen före " + days[day] + " var det den " + days[day - 1] + " och då var det " + temp[day - 1] + "grader");
                        }
                        else {
                        System.out.println("Det finns ingen data innan den 1a Maj");
                        }
                    //Skapar en if-sats för om dagen efter dagen man valt är efter 31a maj så får man ett felmeddelande.
                    if (day < 30) {
                        //Skriver ut dag och temperatur dagen efter dagen man valt
                        System.out.println("Dagen efter " + days[day] + " var det den " + days[day + 1] + " och då var det " + temp[day + 1] + "grader");
                    }
                    else {
                        System.out.println("finns ingen data efter 31a Maj");
                    }
                    //Ger running värdet false som stänger loopen
                    running = false;
                }
                //Om inte 1-31. Hämta metoden för felmeddelande och gå tillbaka ett steg i loopen.
                else {
                    wrongInputDays();
                }
            }
            //Om inte ett heltal. Felmeddelande och prova igen.
            else {
                wrongInputDays();
                scanner.next();
            }
        }
    }
    //Metod för att räkna ut medel
    public void average() {
        double sum = 0;
        //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
        for (int i = 0;i < daysInMay; i++) {
            sum += temp[i];
        }
        double calcSum = sum / daysInMay;
        System.out.println("Medel temperaturen i maj var: " + String.format("%.2f", calcSum) + " grader");
    }
    //Skapar en metod för att räkna ut varmaste dagen
    public void warmestDay() {
        //Deklarerar variabeln biggest, med datatypen integer och ger den värdet av första platsen i arrayen temp
        int biggest=temp[0];
        //Deklarerar variabeln index, med datatypen integer och ger den värdet 0.
        int index = 0;
        //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
        for (int i = 0; i < daysInMay; i++) {
            //Om temperaturen är högre än biggest
            if (temp[i] > biggest) {
                //Så läggs den in i biggest
                biggest=temp[i];
                //sparar vilken plats i arrayen den kallaste temperaturen hittades
                index = i;
            }

        }
        System.out.println("varmaste dagen var den: " + days[index] + " och då var det " + biggest + " grader");
    }
    //Skapar en metod för att räkna ut kallaste dagen
    public void coldestDay() {
        //Deklarerar variabeln lowest, med datatypen integer och ger den värdet av första platsen i arrayen temp
        int lowest = temp[0];
        //Deklarerar variabeln index, med datatypen integer och ger den värdet 0.
        int index = 0;
        //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
        for (int i = 0; i < daysInMay; i++) {
            //Om temperaturen är lägre än lowest
            if (temp[i]<lowest) {
                //Så läggs den in i lowest
                lowest=temp[i];
                //sparar vilken plats i arrayen den kallaste temperaturen hittades
                index = i;
            }
        }
        System.out.println("Kallaste dagen var den: " + days[index] + " och då var det" + lowest + "grader");

    }
    //Skapar metod för att räkna ut medianen.
    public void median() {
        //Deklarerar integer variabeln median.
        double median;
        //Kör sorterings metoden.
        sortTemperatures(true);
        //Om days in may är ett jämnt tal så är medianen 2dagar
        //Nu är daysInMay final 31 dagar så denna kommer aldrig användas.
        if (daysInMay % 2 == 0) {
            //Så vi tar de 2 dagarna och delar på 2 för att få medianen
            median = (temp[daysInMay /2 - 1] + temp[daysInMay /2]) / 2.0;
        }
        //Är det ett udda tal tar vi bara mitten.
        else {
            median = temp[daysInMay /2];
        }
        System.out.println("Mediantemperaturen i maj var: " + (median) + " grader");
    }
    //Skapar metod för att få fram den vanligast förekommande temperaturen
    public void commonTemp() {
        //Deklarerar variabeln mostCommonTemp, datatyp integer och ger den värdet från första positionen i arrayen temp.
        int mostCommonTemp = temp[0];
        //Deklarerar variabeln highestCount, datatyp integer och ger den värdet 0
        int highestCount = 0;
        //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
        for (int i = 0; i < daysInMay; i++) {
            //Deklarerar en variabel count med datatypen integer och tilldelar den värdet 0.
            int count = 0;
            //For loop. Initieringen börjar på noll. Villkoret är dagar i maj. Iteration +1.
            for (int j = 0; j < daysInMay; j++) {
                //Om temperaturen är densamma i temp j och i så adderas en till count.
                if (temp[j] == temp[i]) {
                    count++;
                }
            }
            //Om talet på platsen "i" förekom flest gånger så läggs det värdet in i highestCount
            if (count > highestCount) {
                highestCount = count;
                //När loopen är avklarad och alla tal har jämförts så ligger nu den vanligast förekommande temperaturen i mostCommonTemp
                mostCommonTemp = temp[i];
            }
        }

        //Skriver ut den vanligast förekommande temperaturen
        System.out.println("Den mest förekommande temperaturen i maj var: " + mostCommonTemp + " grader");
    }
    public static void main(String[] args) {
        //Initierar ett scanner objekt för att kunna använda metoder inom klassen Scanner.
        //Och för att läsa inmatning från användaren via standardinmatningsströmmen (System.in)
        Scanner scanner = new Scanner(System.in);
        //Initierar ett weather objekt för att kunna använda metoder inom klassen Weather
        Weather weather = new Weather();
        //deklarerar en variabel running med datatypen boolean och ger den värdet true.
        boolean running = true;

        //Initierar en while loop med villkoret att running är true.
        while (running){
            //Vi börjar med att återställa sorteringen till ursprungsvärden genom metoden sortReset.
            weather.sortReset();
            //Skriver ut och numrerar alla alternativ med förklarande strängar av text.
            System.out.println("\nJasså du är meteorolog! Här har du ett program du kan göra allt möjligt roligt i:");
            System.out.println("Välj några av följande alternativ: \n");
            System.out.println("1. Lista över temperaturerna för varje dag i maj");
            System.out.println("2. medeltemperaturen i maj");
            System.out.println("3. Vilken dag var varmast?");
            System.out.println("4. Vilken dag var kallast?");
            System.out.println("5. Sortera Majs dagar efter temperatur. kallast först!");
            System.out.println("6. Sortera Majs dagar efter temperatur. varmast först!");
            System.out.println("7. Välj dag i maj du vill veta mer om. inklusive dagen före och efter");
            System.out.println("8. Mediantemperaturen i maj");
            System.out.println("9. Sortera bort de dagar som understiger temperaturen du väljer");
            System.out.println("10. Den mest förekommande temperaturen i maj");
            System.out.println("11. Avsluta");
            //Switch-sats för att hantera menyalternativ
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
                    weather.listHotDays(scanner);
                    break;
                case "10":
                    weather.commonTemp();
                    break;
                case "11":
                    running = false;
                    break;
                    //Om man inte skriver 1-11 så blir det default. Vilket är ett felmeddelande
                default:
                    weather.wrongInputMenu();
                    break;

            }
        }
        //Avsluts utskrift
        System.out.println("avslutar...");
        //Stänger scanner objektet
        scanner.close();
    }
}