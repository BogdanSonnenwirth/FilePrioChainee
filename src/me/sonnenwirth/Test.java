package me.sonnenwirth;

public class Test{
    public static void main(String[] args) throws FileVideException {
        FilePrioChainee<TachePrio> file = new FilePrioChainee<>();
        TachePrio t1 = new TachePrio(12);
        TachePrio t2 = new TachePrio(43);
        TachePrio t16 = new TachePrio(68, "e");
        TachePrio t3 = new TachePrio(1);
        TachePrio t4 = new TachePrio(100);
        TachePrio t5 = new TachePrio(82);
        TachePrio t6 = new TachePrio(37);
        TachePrio t15 = new TachePrio(68, "e");
        TachePrio t7 = new TachePrio(68, "e");
        TachePrio t13 = new TachePrio(68);
        TachePrio t8 = new TachePrio(50);
        TachePrio t9 = new TachePrio(42);
        TachePrio t10 = new TachePrio(13);
        TachePrio t11 = new TachePrio(13);
        TachePrio t12 = new TachePrio(1);
        TachePrio t14 = new TachePrio(68, "h");
        file.enfiler(t5);
        file.enfiler(t3);
        file.enfiler(t16);
        file.enfiler(t10);
        file.enfiler(t8);
        file.enfiler(t11);
        file.enfiler(t12);
        file.enfiler(t1);
        file.enfiler(t14);
        file.enfiler(t2);
        file.enfiler(t4);
        file.enfiler(t6);
        file.enfiler(t7);
        file.enfiler(t9);
        file.enfiler(t13);
        file.enfiler(t15);
        System.out.println(file.toString());
        file.defiler();
        file.defiler(50);
        System.out.println(file.toString());
        System.out.println(file.prioriteExiste(27));
        System.out.println("Il y a " + file.taille() + " elements dans la file");
        System.out.println("Il y a " + file.taille(68) + " elements dans la file de priorité " + "68");
        System.out.println("Le premier element de la file est " + file.premier());
        System.out.println("Le premier element de la file de priorite " + "43" + " est " + file.premier(43));
        System.out.println("Est-ce que l'element " + "1" + " est dans la file? " + file.contient(new TachePrio(1)));
        System.out.println("La plus grande priorite est " + file.prioriteMax());
        System.out.println("La plus petite priorite est " + file.prioriteMin());
        System.out.println(file.sousFilePrio(68));
        System.out.println(file);
        //file.eliminerDoublons();
        //System.out.println("enleve doublons " + file);
        file.normaliser();
        System.out.println("file normalisee " + file);
        file.defilerTous(7);
        System.out.println("file defilee tous " + file);

        //System.out.println(file);
//        try {
//            System.out.println(file.defiler());
//            System.out.println(file.defiler());
//        } catch (FileVideException e) {
//            e.printStackTrace();
//        }
//        file.vider();
//        System.out.println(file);
    }
}
