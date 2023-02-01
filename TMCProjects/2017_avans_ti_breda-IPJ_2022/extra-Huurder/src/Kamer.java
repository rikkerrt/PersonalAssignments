public class Kamer {
    private double prijs;
    private double lengte;
    private double breedte;
    private double hoogte;

    public Kamer(double lengte, double breedte, double hoogte, double prijs) {
        this.prijs = prijs;
        this.lengte = lengte;
        this.breedte = breedte;
        this.hoogte = hoogte;
    }

    public double getBreedte() {
        return breedte;
    }

    public double getHoogte() {
        return hoogte;
    }

    public double getLengte() {
        return lengte;
    }

    public double getPrijs() {
        return prijs;
    }
    
}
    
    