package seminar.seminar2.g1060;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Titular[] clienti;
    private static List<Depozit> depozite;
    public static SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        try {
////            Cerinta 1
//            Adresa adresa = new Adresa("Brasov", "Brasov", "1 Decembrie 1918", "2a", 5678);
//            System.out.println(adresa);
//            Titular titular = new Titular("Pop Adrian", 1900808456789L, adresa);
//            System.out.println(titular);
//            Titular titular1 = titular;
//            Titular titular2 = new Titular();
//            Titular titular3 = new Titular(1900808456789L);
//            System.out.println(titular.equals(titular1) + "," +
//                    titular.equals(titular2) + "," + titular.equals(titular3));
//            Titular clona = (Titular) titular.clone();
//            titular.getAdresa().setStrada("Stefan cel Mare");
//            System.out.println("Titular:\n" + titular + "\nClona:\n" + clona);
//
//            Depozit depozit = new Depozit(titular, formatData.parse("15.03.2025"),
//                    Moneda.LEU, 10000, "SMB", TipDepozit.O_LUNA, 1111);
//            System.out.println(depozit);
//            double dobanda = depozit.calculDobanda();
//            System.out.println("Dobanda:" + dobanda);
//            ContCurent contCurent = new ContCurent(titular, formatData.parse("01.01.2025"),
//                    Moneda.LEU, 0, "SMB", "ROCNB000036544LCT8888888");
//            contCurent.depunereNumerar(dobanda);
//            System.out.println("Contul curent:");
//            System.out.println(contCurent);

//            Citire clienti cu rezultat in vector
            citireTitulari();
            System.out.println("Clienti:");
            for (Titular t : clienti) {
                System.out.println(t);
            }
//            Citire depozite
            citireDepozite();
            System.out.println("Depozite:");
            for (Depozit depozit : depozite) {
                System.out.println(depozit);
            }
//            Print depozite Euro
            System.out.println("Depozite in Euro: <Euro.csv>");
            print("Euro.csv");
//            Salvare - Serializare
            String fisierOutput = "Depozite.dat";
//            salvare(fisierOutput);
//            Restaurare lista de depozite - Deserializare
            restaurare(fisierOutput);
            System.out.println("Lista restaurata:");
            for (Depozit depozit : depozite) {
                System.out.println(depozit);
            }
//            Cautare depozite dupa cod contract
            System.out.println("Cod contract:");
            int codContract = Integer.parseInt(
                    new BufferedReader(
                            new InputStreamReader(System.in)).readLine());
            int k = depozite.indexOf(new Depozit(codContract));
            if (k==-1){
                System.out.println("Nu exista depozit cu codul contractului "+codContract);
            } else {
                System.out.println("Depozit cauat:");
                System.out.println(depozite.get(k));
            }

//            Sortare dupa un criteriu oarecare
//            Criteriu: nume titular
//            Creare comparator
            Comparator<Depozit> comparator = new Comparator<Depozit>() {
                @Override
                public int compare(Depozit o1, Depozit o2) {
                    return o1.getTitular().getNume().compareTo(o2.getTitular().getNume());
                }
            };
            Collections.sort(depozite,comparator);
            System.out.println("Lista depozite sortata dupa nume titular:");
            for (Depozit depozit : depozite) {
                System.out.println(depozit);
            }
            Depozit depozitCautat = new Depozit();
            depozitCautat.setTitular(new Titular("Hadar Cornel",128374612387L,new Adresa()));
            k = Collections.binarySearch(depozite,depozitCautat,comparator);
            if (k>=0){
                System.out.println("Depozit cautat:");
                System.out.println(depozite.get(k));
            } else {
                System.out.println("Nu exista depozit pentru titularul "+depozitCautat.getTitular().getNume());
                System.out.println("Pozitie de inserare:"+(-k-1));
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void restaurare(String numeFisier) {
        try (FileInputStream in1 = new FileInputStream(numeFisier);
             ObjectInputStream in = new ObjectInputStream(in1)) {
            depozite.clear();
            while (in1.available() != 0) {
                depozite.add((Depozit) in.readObject());
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.toString());
        }
    }

    private static void salvare(String numeFisier) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(numeFisier))) {
            for (Depozit depozit : depozite) {
                out.writeObject(depozit);
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.toString());
        }
    }

    private static void print(String numeFisier) {
        try (PrintWriter out = new PrintWriter(numeFisier)) {
            for (Depozit depozit : depozite) {
                if (depozit.getMoneda().equals(Moneda.EURO)) {
                    out.println(depozit.getTitular().getNume() + "," +
                            depozit.getCodContract() + "," + depozit.getValoare());
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, e.toString());
        }
    }

    public static void citireTitulari() {
        clienti = new Titular[0];
        try (BufferedReader in = new BufferedReader(new FileReader("clienti.csv"))) {
            String linie;
            while ((linie = in.readLine()) != null) {
                Titular titular = new Titular();
                String[] t = linie.trim().split(",");
                titular.setCnp(Long.parseLong(t[0].trim()));
                titular.setNume(t[1].trim());
                Adresa adresa = new Adresa(
                        t[2].trim(),
                        t[3].trim(),
                        t[4].trim(),
                        t[5].trim(),
                        Integer.parseInt(t[6].trim())
                );
                titular.setAdresa(adresa);
                clienti = Arrays.copyOf(clienti, clienti.length + 1);
                clienti[clienti.length - 1] = titular;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void citireDepozite() {
        try (BufferedReader in = new BufferedReader(new FileReader("depozite.csv"))) {
            depozite = new ArrayList<>();
            String linie;
            while ((linie = in.readLine()) != null) {
                Depozit depozit = new Depozit();
//                Citire
                String[] t = linie.trim().split(",");
                long cnp = Long.parseLong(t[0].trim());
                Titular titular = cautaTitular(cnp);
                if (titular != null) {
                    depozit.setTitular(titular);
                    depozit.setDataDeschidere(formatData.parse(t[1].trim()));
                    depozit.setMoneda(Moneda.valueOf(t[2].trim().toUpperCase()));
                    depozit.setValoare(Double.parseDouble(t[3].trim()));
                    depozit.setSucursala(t[4].trim());
                    depozit.setTipDepozit(TipDepozit.valueOf(t[5].trim().toUpperCase()));
                    depozit.setCodContract(Integer.parseInt(t[6].trim()));
                    depozite.add(depozit);
                } else {
                    System.out.println("Nu exista titular cu cnp " + cnp);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static Titular cautaTitular(long cnp) {
        for (Titular titular : clienti) {
            if (titular.getCnp() == cnp) {
                return titular;
            }
        }
        return null;
    }

    public static List<Depozit> getDepozite() {
        return depozite;
    }

    public static Titular[] getClienti() {
        return clienti;
    }
}
