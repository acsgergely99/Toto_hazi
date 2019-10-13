package toto.esemenyek;

public class Talalatok
{
    private int talalatokSzama;
    private int nyertTalalatokSzama;
    private int nyeremeny;

    public Talalatok(int talalatokSzama, int nyertTalalatokSzama, int nyeremeny)
    {
        this.talalatokSzama = talalatokSzama;
        this.nyertTalalatokSzama = nyertTalalatokSzama;
        this.nyeremeny = nyeremeny;
    }

    public int getTalalatokSzama()
    {
        return this.talalatokSzama;
    }
    public int getNyertTalalatokSzama()
    {
        return this.nyertTalalatokSzama;
    }
    public int getNyeremeny()
    {
        return this.nyeremeny;
    }
}