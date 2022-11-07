package ba.unsa.etf.rpr;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopDaoSerializableFile implements LaptopDao{
    private ArrayList<Laptop> laptopi;
    private File file;
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    public void dodajLaptopUFile(Laptop laptop) {
            List<Laptop> temp= new ArrayList<Laptop>();
            boolean cont=true;
            try {
                ObjectInputStream in = new ObjectInputStream((new FileInputStream(file)));
                try {
                    while (cont) {
                        Object obj = in.readObject();
                        if (obj != null) {
                            temp.add((Laptop) obj);
                        } else {
                            cont = false;
                        }
                    }
                } catch (Exception e) {

                }
                in.close();
            }catch (Exception e){
                System.out.println("Greška: "+e);
            }
            try{
                ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(temp);
                out.close();

            } catch (FileNotFoundException e) {
                System.out.println("Greska" + e);
            } catch (IOException e) {
                System.out.println("Greska" + e);
            }

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
        ArrayList<Laptop> temp = new ArrayList<Laptop>();
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            temp= (ArrayList<Laptop>) in.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }
}
