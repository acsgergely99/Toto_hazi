package toto;

import toto.jatek.TotoJatek;

import java.util.Scanner;

public class Toto
{
    public static void main(String[] args)
    {
        TotoJatek t = new TotoJatek("toto.csv");

        System.out.println(t.getMaxNyeremenyListabol() + " Ft");

        double sum = t.getElsoGyozelem() + t.getMasikGyozelem() + t.getDontetlen();
        double avgElso = t.getElsoGyozelem() / sum * 100;
        double avgMasik = t.getMasikGyozelem() / sum * 100;
        double avgDontetlen = t.getDontetlen() / sum * 100;
        System.out.println("Statisztika: #1 csapat nyert: " + Math.round(avgElso * 100.0) / 100.0 + " %, #2 csapat nyert: " + Math.round(avgMasik * 100.0) / 100.0 + " %, döntetlen: " + Math.round(avgDontetlen * 100.0) / 100.0 + " %");

        Scanner sc = new Scanner(System.in);
        System.out.print("Kérem adjon meg egy dátumot (YYYY.MM.DD.): ");
        String datum = sc.next();
            
        
    }
}