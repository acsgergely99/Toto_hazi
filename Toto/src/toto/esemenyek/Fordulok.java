package toto.esemenyek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fordulok
{
    private int ev;
    private int het;
    private int forduloHeten;
    private LocalDate datum;
    private List<Talalatok> talalatokList;
    private List<Eredmenyek> eredmenyList;

    public Fordulok(String sor) {
        String[] adatok = sor.split(";");
        this.ev = Integer.parseInt(adatok[0]);
        this.het = Integer.parseInt(adatok[1]);
        if (adatok[2].equals(""))
        {
            this.forduloHeten = 1;
        }
        else
        {
            this.forduloHeten = Integer.parseInt(adatok[2]);
        }
        // DATUM
        if (adatok[3].equals(""))
        {
            int napokSzama = this.het * 7;
            if (this.ev % 4 == 0 && napokSzama > 366)
            {
                napokSzama -= 366;
                this.ev++;
            }
            else if (napokSzama > 365)
            {
                napokSzama -= 365;
                this.ev++;
            }
            LocalDate segedDatum = LocalDate.ofYearDay(this.ev, napokSzama);
            switch (this.forduloHeten)
            {
                case 1: this.datum = segedDatum.with(DayOfWeek.MONDAY); break;
                case 2: this.datum = segedDatum.with(DayOfWeek.TUESDAY); break;
                case 3: this.datum = segedDatum.with(DayOfWeek.WEDNESDAY); break;
                case 4: this.datum = segedDatum.with(DayOfWeek.THURSDAY); break;
                case 5: this.datum = segedDatum.with(DayOfWeek.FRIDAY); break;
                case 6: this.datum = segedDatum.with(DayOfWeek.SATURDAY); break;
                case 7: this.datum = segedDatum.with(DayOfWeek.SUNDAY); break;
            }
        }
        else
        {
            adatok[3] = adatok[3].replace(".", ";");
            String[] datumok = adatok[3].split(";");
            this.datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        }
        // TALALAT
        this.talalatokList = new ArrayList<>();
        int index = 4;
        int talalatokSzama = 14;
        do
        {
            String str = adatok[index + 1];
            str = str.replace(" ", "");
            int nyeremeny = Integer.parseInt(str.substring(0, str.length() - 2));
            this.talalatokList.add(new Talalatok(talalatokSzama, Integer.parseInt(adatok[index]), nyeremeny));
            index += 2;
            talalatokSzama--;
        }
        while (index < 14);

        // EREDMENYEK
        this.eredmenyList = new ArrayList<>();
        for (int i = 14; i < adatok.length; i++)
        {
            switch (adatok[i])
            {
                case "1": this.eredmenyList.add(Eredmenyek._1); break;
                case "+1": this.eredmenyList.add(Eredmenyek._1); break;
                case "2": this.eredmenyList.add(Eredmenyek._2); break;
                case "+2": this.eredmenyList.add(Eredmenyek._2); break;
                case "X": this.eredmenyList.add(Eredmenyek.X); break;
                case "+X": this.eredmenyList.add(Eredmenyek.X); break;
            }
        }
    }

    public int getEv() {
        return this.ev;
    }
    public void setEv(int ev) {
        this.ev = ev;
    }
    public int getHet() {
        return this.het;
    }
    public void setHet(int het) {
        this.het = het;
    }
    public int getForduloAHeten() {
        return this.forduloHeten;
    }
    public void setForduloAHeten(int forduloAHeten) {
        this.forduloHeten = forduloAHeten;
    }
    public LocalDate getDatum() {
        return this.datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public List<Talalatok> getTalalatok()
    {
        return this.talalatokList;
    }
    public void setTalalatok(List<Talalatok> talalatLista)
    {
        this.talalatokList = talalatLista;
    }
    public List<Eredmenyek> getEredmenyek()
    {
        return this.eredmenyList;
    }
    public void setEredmenyek(List<Eredmenyek> eredmenyekLista)
    {
        this.eredmenyList = eredmenyekLista;
    }

    public int getMaxNyeremeny()
    {
        int max = 0;
        for (int i = 0; i < this.talalatokList.size(); i++)
        {
            if (max < this.talalatokList.get(i).getNyeremeny())
            {
                max = this.talalatokList.get(i).getNyeremeny();
            }
        }
        return max;
    }
}

