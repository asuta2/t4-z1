package ba.unsa.etf.rpr;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class LaptopDaoXMLFile implements LaptopDao{
    private ArrayList<Laptop> laptopi;
    private File file;
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    public void dodajLaptopUFile(Laptop laptop) {

    }

    public Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException {
        Laptop temp=null;
        for(Laptop x: laptopi)
        {
            if(x.getProcesor().equals(procesor)) {
                temp = x;
                break;
            }
        }
        if(temp==null)
            throw new NeodgovarajuciProcesorException("OwO");
        return temp;
    }

    public void napuniListu(ArrayList<Laptop> laptopi) {
        Collections.copy(this.laptopi,laptopi);
    }

    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        return null;
    }
}
