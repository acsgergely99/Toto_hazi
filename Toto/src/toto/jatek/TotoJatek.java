package toto.jatek;

import toto.esemenyek.Eredmenyek;
import toto.esemenyek.Fordulok;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TotoJatek
{
    private List<Fordulok> forduloList;

    public TotoJatek(String fajl)
    {
        this.forduloList = new ArrayList<>();
        try
        {
            FileReader fr = new FileReader(fajl);
            BufferedReader br = new BufferedReader(fr);
            String sor = br.readLine();
            while (sor != null)
            {
                this.forduloList.add(new Fordulok(sor));
                sor = br.readLine();
            }
            fr.close();
            br.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.print("A fájl nem létezik");
        }
        catch (Exception e)
        {
            System.err.printf("Hiba %s", e);
        }
    }

    public int getMaxNyeremenyListabol()
    {
        int max = 0;
        for (int i = 0; i < this.forduloList.size(); i++)
        {
            if (max < this.forduloList.get(i).getMaxNyeremeny())
            {
                max = this.forduloList.get(i).getMaxNyeremeny();
            }
        }
        return max;
    }

    public int getElsoGyozelem()
    {
        int sum = 0;
        for (int i = 0; i < this.forduloList.size(); i++)
        {
            for (int j = 0; j < this.forduloList.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloList.get(i).getEredmenyek().get(j).equals(Eredmenyek._1))
                {
                    sum++;
                }
            }
        }
        return sum;
    }
    public int getMasikGyozelem()
    {
        int sum = 0;
        for (int i = 0; i < this.forduloList.size(); i++)
        {
            for (int j = 0; j < this.forduloList.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloList.get(i).getEredmenyek().get(j).equals(Eredmenyek._2))
                {
                    sum++;
                }
            }
        }
        return sum;
    }
    public int getDontetlen()
    {
        int sum = 0;
        for (int i = 0; i < this.forduloList.size(); i++)
        {
            for (int j = 0; j < this.forduloList.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloList.get(i).getEredmenyek().get(j).equals(Eredmenyek.X))
                {
                    sum++;
                }
            }
        }
        return sum;
    }

    public boolean vanEIlyenDatum(String segedDatum)
    {
        segedDatum = segedDatum.replace(".", "-");
        String[] datumok = segedDatum.split("-");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        boolean vanE = false;
        for (int i = 0; i < this.forduloList.size(); i++)
        {
            if (this.forduloList.get(i).getDatum().equals(datum))
            {
                vanE = true;
            }
        }
        return vanE;
    }

    public void getEredmeny(String segedDatum, String tipp)
    {
        segedDatum = segedDatum.replace(".", "-");
        String[] datumok = segedDatum.split("-");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));

        tipp = tipp.toUpperCase();
        String[] tippek = tipp.split("");
        List<Eredmenyek> tippekLista = new ArrayList<>();
        for (int i = 0; i < tippek.length; i++)
        {
            switch (tippek[i])
            {
                case "1": tippekLista.add(Eredmenyek._1); break;
                case "2": tippekLista.add(Eredmenyek._2); break;
                case "X": tippekLista.add(Eredmenyek.X); break;
            }
        }
        int talalat = 0;
        int nyeremeny = 0;
        boolean voltEJatek = false;
        for (int i = 0; i < this.forduloList.size(); i++)
        {
            if (this.forduloList.get(i).getDatum().equals(datum))
            {
                voltEJatek = true;
                for (int j = 0; j < this.forduloList.get(i).getEredmenyek().size(); j++)
                {
                    if (this.forduloList.get(i).getEredmenyek().get(j) == tippekLista.get(j))
                    {
                        talalat++;
                    }
                }
                if (talalat >= 10)
                {
                    for (int j = 0; j < this.forduloList.get(i).getTalalatok().size(); j++)
                    {
                        if (this.forduloList.get(i).getTalalatok().get(j).getTalalatokSzama() == talalat)
                        {
                            nyeremeny = this.forduloList.get(i).getTalalatok().get(j).getNyeremeny();
                        }
                    }
                }
                else
                {
                    nyeremeny = 0;
                }
            }
        }
        if (voltEJatek == false)
        {
            System.out.println("Nem volt ezen a napon totó.");
        }
        else
        {
            System.out.printf("Eredmény: találat: %d, nyeremeny: %d Ft", talalat, nyeremeny);
        }
    }
}