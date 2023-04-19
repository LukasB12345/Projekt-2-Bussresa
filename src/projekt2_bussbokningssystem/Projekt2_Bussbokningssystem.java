/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekt2_bussbokningssystem;

/**
 *
 * @author lukas.bommelin
 */
import java.util.Scanner;

public class Projekt2_Bussbokningssystem {
    static Scanner tangentbord = new Scanner(System.in);
    static int[] sittplatser = new int[21];
    static int[] kolla_fonsterplats = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21}; //Används för att kolla platsnumret för att kunna hitta fönsterplatser

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean kör = true; //Programmet körs så länge användaren inte väljer 4 (Avsluta programmet)

        int val;
        while (kör) { //Programmet körs så länge användaren inte väljer 4 (Avsluta programmet)
            System.out.println("Välkommen till mitt bussbokningssystem! Välj 1-8 i menyn nedan:\nVal 1 = Boka en plats\nVal 2 = Se antal lediga platser\nVal 3 = Beräkna vinsten av antalet sålda biljetter\nVal 4 = Avsluta programmet\nVal 5 = Hitta bokning\nVal 6 = Ta bort bokning\nVal 7 = Lista personer över & under 18år\nVal 8 = Boka en fönsterplats\n");
            if (tangentbord.hasNextInt()) {
                val = tangentbord.nextInt();
                if (val > 8 || val < 1) {
                    System.out.println("Du måste välja ett heltal mellan 1-8!!! Försök igen: ");
                }
                switch (val) {

                    case 1:
                        valEtt(); //Ifall användaren väljer 1 anropas metoden
                        break;
                    case 2:
                        valTvå();//Ifall användaren väljer 2 anropas metoden
                        break;
                    case 3:
                        valTre(); //Ifall användaren väljer 3 anropas metoden
                        break;
                    case 4: //Ifall användaren väljer 4 avslutas programmet då kör blir falsk
                        System.out.println("Hejdå!");
                        kör = false; //Vill man avsluta programmet blir värdet falskt och koden slutar köras/programmet avslutas
                        break;
                    case 5:
                        valFem(); //Ifall användaren väljer 5 anropas metoden
                        break;
                    case 6:
                        valSex(); //Ifall användaren väljer 6 anropas metoden
                        break;
                    case 7:
                        valSju(); //Ifall användaren väljer 7 anropas metoden
                        break;
                    case 8:
                        valÅtta(); //Ifall användaren väljer 8 anropas metoden
                        break;

                }
            } else {
                System.out.println("Du måste skriva in ett heltal mellan 1-8!!! Försök igen: "); //säger till ifall användaren skriver ett otillåtet tal och låter användaren försöka igen tills det blir rätt
                tangentbord.next();
            }
        }
    }
    //Här fortsätter sedan själva koden för bokningssystemet:

    static void valEtt() { //Om valet är ett anropas denna metod
        System.out.println("Du har valt 1!");

        for (int i = 0; i < sittplatser.length; i++) //Går igenom hela fältet med personnummer och letar efter lediga platser/element, med värdet 0
        {
            if (sittplatser[i] == 0) { //Om en plats är ledig får användaren skriva in ett födelsedatum för att boka en plats
                System.out.println("Skriv in ett födelsedatum med formatet ÅÅÅÅMMDD för att boka en plats"); //Om en plats är ledig får användaren skriva in sitt personnummer för att boka en plats
                if (tangentbord.hasNextInt()) {

                    int personnummer = tangentbord.nextInt(); //födelsedatumet/personnummret läses in här och sparas först i variabeln personnummer
                    if (personnummer < 19070304 || personnummer > 20230401) { //Kollar ifall bokningen är ett giltigt födelsedatum, personen måste ha fötts senare än 19070304 då detta är födelsedatumet för den äldsta levande personen. Personen måste även ha fötts innan 20230401 eftersom det är satt till "dagens datum", man kan inte vara född i framtiden. 
                        System.out.println("Du måste ange ett giltigt personnummer för en levande person. Använd formatet ÅÅÅÅMMDD. Försök igen:");
                    } else {
                        sittplatser[i] = personnummer; //Ger det första lediga elementet i fältet värdet av personnummret
                        System.out.println("Du har bokat en sittplats för personen med födelsedatumet: " + personnummer);
                        break;
                    }
                } else {
                    System.out.println("Du måste skriva in ett födelsedatum med formatet ÅÅÅÅMMDD. Använd siffror. Försök igen: "); //säger till ifall användaren skriver ett otillåtet tal och låter användaren försöka igen tills det blir rätt
                    tangentbord.next();
                }
            } else if (sittplatser[20] != 0) { //Om for-loopen har gått igenom hela fältet och det sista elementet inte är 0 är alla platser uppbokade
                System.out.println("Alla platser är uppbokade"); //skrivs ut här
                break;
            }
        }

    }

    //   if (personnummer < 19070304 && personnummer > 20230401) {
    //       System.out.println("Du måste ange ett giltigt personnummer för en levande person. Använd formatet ÅÅÅÅMMDD. Försök igen:");
    //   }
    static void valTvå() { //Om valet är två anropas denna metod
        System.out.println("Du har valt 2!");
        int lediga_platser = 0;
        for (int i = 0; i < sittplatser.length; i++) { //Går igenom hela fältet med personnummer och letar efter lediga platser/element, med värdet 0
            if (sittplatser[i] == 0) { //Om en plats har värdet 0 och då är ledig läggs det till 1 i variabeln som räknar lediga platser, hela fältet kollas igenom för att alla lediga platser ska räknas
                lediga_platser++;
            }
        }
        System.out.println("Det finns " + lediga_platser + " lediga platser"); //Här skrivs den totala räkningen för lediga platser ut
    }

    static void valTre() { //Om valet är tre anropas denna metod
        System.out.println("Du har valt 3!");
        double antal_barn = 0;
        double antal_vuxna = 0;
        double antal_pensionarer = 0;
        int nuvarandeÅret = 2023;
        int nuvarandeMånaden = 4;
        int nuvarandeDagen = 1;

        for (int i = 0; i < sittplatser.length; i++) { //Kollar igenom fältet för sittplatser efter bokningar och deras åldrar
            if (sittplatser[i] != 0) { //Och körs för alla bokade platser, inte för obokade med element som har värdet 0

                int födelseDatumet = sittplatser[i];
                int år = födelseDatumet / 10000; //Räknar ut födelseår
                int månad = (födelseDatumet / 100) % 100; //Räknar ut födelsemånad
                int dag = födelseDatumet % 100; //Räknar ut vilken dag i månaden födelsedagen inträffar
                int ålder = nuvarandeÅret - år; //Räknar ut åldern i år på personen, men tar inte än med ifall födelsedagen har inträffat i det nuvarande året, det görs sen

                if (månad > nuvarandeMånaden || (månad == nuvarandeMånaden && dag > nuvarandeDagen)) { //Om månaden på bokningen är mindre/innan än nuvarande månaden, ELLER ifall månaden på bokningen = den nuvarande månaden & dagen är större/senare än den nuvarande dagen
                    ålder--; //Så tas 1 bort från åldern, då födelsedagen inte inträffat
                }

                if (ålder < 18) { //om bokning = barn (under 18)
                    antal_barn++; //Läggs 1 barn till i räkningen

                }
                if (ålder >= 18 && ålder < 69) { //om bokning = vuxen (18/över 18 och under 69)
                    antal_vuxna++; //Läggs 1 vuxen till i räkningen
                }

                if (ålder >= 69) { //Om bokning = pensionär (69 eller över)
                    antal_pensionarer++; //Läggs 1 pensionär till i räkningen
                }

            }

        }
        double vinstBarn = antal_barn * 149.90; //Vinsten för barn defineras av antalet biljetter som bokats för barn multiplicerat med biljettpriset 149.90 för barn
        double vinstVuxna = antal_vuxna * 299.90; //Vinsten för vuxna defineras av antalet biljetter som bokats för vuxna multiplicerat med biljettpriset 299.90 för vuxna
        double vinstPensionarer = antal_pensionarer * 200.0; //Vinsten för pensionärer defineras av antalet biljetter som bokats för pensionärer multiplicerat med biljettpriset 200.0 för pensionärer

        double totaltPris = vinstBarn + vinstVuxna + vinstPensionarer; //Den totala vinsten defineras av vinsten för barn + vinsten för vuxna + vinsten för pensionärer

        System.out.println("Den totala vinsten är: " + totaltPris + " SEK"); //Här skrivs den totala vinsten ut

    }

    static void valFem() { //Hitta bokning
        System.out.println("Du har valt 5!");
        boolean hittad = false; //Skapar en boolean som berättar ifall bokningen är hittad, först är den falsk eftersom ingen bokning har hittats. Hittas ingen bokning är den kvar som falsk.
        System.out.println("Skriv in födelsedatumet med formatet ÅÅÅÅMMDD på bokningen du vill hitta: ");
        if (tangentbord.hasNextInt()) {
            int personnummer = tangentbord.nextInt();
            for (int i = 0; i < sittplatser.length; i++) { //kollar igenom fältet med bokningar för at hitta angivna födelsedagsdatumet/bokningen
                if (sittplatser[i] == personnummer) { //Om ett av elementen i fältet har samma värde som det angivna födelsedatumet
                    System.out.println("Bokningen har platsen: " + (i + 1)); //Så skrivs detta ut, 1 läggs till eftersom det första indexet är 0 men första sittplatsen 1.
                    hittad = true; //variabeln hittad blir sann eftersom bokningen har hittats
                    break;
                }
            }
            if (!hittad) { //Om bokningen inte hittas, värdet är alltså fortfarande falskt
                System.out.println("Ingen bokning har gjorts med det angivna födelsedatumet");
            }
        } else {
            System.out.println("Du måste skriva in ett födelsedatum med formatet ÅÅÅÅMMDD. Använd siffror. Försök igen: "); //säger till ifall användaren skriver ett otillåtet tal och låter användaren försöka igen tills det blir rätt
            tangentbord.next();
        }
    }

    static void valSex() {//Ta bort bokning
        System.out.println("Du har valt 6!");
        boolean hittad = false; //Skapar en boolean som berättar ifall bokningen är hittad, först är den falsk eftersom ingen bokning har hittats. Hittas ingen bokning är den kvar som falsk.
        System.out.println("Skriv in födelsedatumet med formatet ÅÅÅÅMMDD på bokningen du vill ta bort: ");
        if (tangentbord.hasNextInt()) {
            int bokningsnummer = tangentbord.nextInt();
            for (int i = 0; i < sittplatser.length; i++) {//Kollar igenom fältet för att hitta bokningen
                if (sittplatser[i] == bokningsnummer) { //Om bokningen hittas
                    sittplatser[i] = 0; //Sätts värdet till 0, platsen blir alltså obokad / bokningen tas bort
                    System.out.println("Bokningen med bokningsnumret: " + bokningsnummer + " har tagits bort");
                    hittad = true; //Variabeln blir sann eftersom bokningen hittats
                }
            }
            if (!hittad) {//Om bokningen inte hittas, värdet är alltså fortfarande falskt
                System.out.println("Ingen bokning har gjorts med det angivna födelsedatumet, därmed går det inte att ta bort bokningen");
            }
        } else {
            System.out.println("Du måste skriva in ett heltal med formatet ÅÅÅÅMMDD! Försök igen: "); //säger till ifall användaren skriver ett otillåtet tal och låter användaren försöka igen tills det blir rätt
            tangentbord.next();
        }
    }

    static void valSju() {//Lista alla personer som är över och under 18 år
        System.out.println("Du har valt 7!");
        int nuvarandeÅr = 2023;
        int nuvarandeMånad = 4;
        int nuvarandeDag = 1;

        for (int i = 0; i < sittplatser.length; i++) { //Hämtar ut alla värden i fältet / alla bokningar och kallar de för födelseDatum
            if (sittplatser[i] != 0) { //Om platsen inte är obokad så körs koden. Den körs alltså för alla bokade platser

                int födelseDatum = sittplatser[i];
                int år = födelseDatum / 10000; //Räknar ut födelseår
                int månad = (födelseDatum / 100) % 100; //Räknar ut födelsemånad
                int dag = födelseDatum % 100; //Räknar ut vilken dag i månaden födelsedagen inträffar
                int ålder = nuvarandeÅr - år; //Räknar ut åldern i år på personen, men tar inte än med ifall födelsedagen har inträffat i det nuvarande året, det görs sen

                if (månad > nuvarandeMånad || (månad == nuvarandeMånad && dag > nuvarandeDag)) { //Om månaden på bokningen är mindre/innan än nuvarande månaden, ELLER ifall månaden på bokningen = den nuvarande månaden & dagen är större/senare än den nuvarande dagen
                    ålder--; //Så tas 1 bort från åldern, då födelsedagen inte inträffat
                }

                if (ålder < 18) { //Om en bokning beräknats vara yngre än 18 skrivs det då ut här:
                    System.out.println("Bokningar med personer under 18 år:" + födelseDatum);
                }
            }

        }
        for (int i = 0; i < sittplatser.length; i++) { //Hämtar ut alla värden i fältet / alla bokningar och kallar de för födelseDatum
            if (sittplatser[i] != 0) { //Om platsen inte är obokad så körs koden. Den körs alltså för alla bokade platser

                int födelseDatum = sittplatser[i];
                int år = födelseDatum / 10000; //Räknar ut födelseår
                int månad = (födelseDatum / 100) % 100; //Räknar ut födelsemånad
                int dag = födelseDatum % 100; //Räknar ut vilken dag i månaden födelsedagen inträffar
                int ålder = nuvarandeÅr - år; //Räknar ut åldern i år på personen, men tar inte än med ifall födelsedagen har inträffat i det nuvarande året, det görs sen

                if (månad > nuvarandeMånad || (månad == nuvarandeMånad && dag > nuvarandeDag)) { //Om månaden på bokningen är mindre/innan än nuvarande månaden, ELLER ifall månaden på bokningen = den nuvarande månaden & dagen är större/senare än den nuvarande dagen
                    ålder--; //Så tas 1 bort från åldern, då födelsedagen inte inträffat
                }

                if (ålder >= 18) { //Om en bokning beräknats vara äldre än 18 skrivs det då ut här:
                    System.out.println("Bokningar med personer över 18 år:" + födelseDatum);
                }
            }

        }
    }

    static void valÅtta() {//Boka en fönsterplats
        System.out.println("Du har valt 8!");

        for (int i = 0; i < sittplatser.length; i++) { //Går igenom alla sittplatser/bokningar
            int kolla_ifall_fonsterplats = kolla_fonsterplats[i] % 4; //kollar vilket värde sittplatsen har med modulo 4
            if (kolla_ifall_fonsterplats == 1 || kolla_ifall_fonsterplats == 0) {//Om svaret är 1 eller 0 är platsen en fönsterplats (1,4,5,8,9,12,13,16,17,21), gäller dock även plats 20 (index 19) men det räknas bort i nästa rad
                if (sittplatser[i] == 0 && i != 19) { //Kollar om platsen är ledig och om den inte är plats 20, eftersom plats 20 också räknas som fönsterplats med modulo
                    System.out.println("Skriv in ditt födelsedatum med formatet ÅÅÅÅMMDD för att boka en fönsterplats "); //Om en plats är ledig får användaren skriva in sitt personnummer för att boka en fönsterplats
                    if (tangentbord.hasNextInt()) {
                        int personnummer = tangentbord.nextInt(); //födelsedatumet/personnummret läses in här och sparas först i variabeln personnummer
                        if (personnummer < 19070304 || personnummer > 20230401) { //Kollar ifall bokningen är ett giltigt födelsedatum, personen måste ha fötts senare än 19070304 då detta är födelsedatumet för den äldsta levande personen. Personen måste även ha fötts innan 20230401 eftersom det är satt till "dagens datum", man kan inte vara född i framtiden.
                            System.out.println("Du måste ange ett giltigt personnummer för en levande person. Använd formatet ÅÅÅÅMMDD. Försök igen:");
                        } else {
                            sittplatser[i] = personnummer; //och tilldelas det angivna personnumret denna plats i sittplatser-fältet (den första lediga fönsterplatsen som hittas. 
                            System.out.println("Du har bokat en fönsterplats för personen med födelsedatumet: " + personnummer);
                            break;
                        }
                    } else {
                        System.out.println("Du måste skriva in ett heltal med formatet ÅÅÅÅMMDD! "); //säger till ifall användaren skriver ett otillåtet tal och låter användaren försöka igen tills det blir rätt
                        tangentbord.next();
                    }
                }
            }

            if (i == 20 && sittplatser[i] != 0) { //Om den sista fönsterplatsen och sista platsen i bussen är bokad betyder det att alla fönsterplatser är uppbokade!
                System.out.println("Alla fönsterplatser är bokade!");
                break;
            }
        }

    }

}
