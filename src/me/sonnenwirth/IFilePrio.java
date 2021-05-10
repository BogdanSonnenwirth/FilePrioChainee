package me.sonnenwirth;

/**
 * Iterface definissant le TDA File de priorite.
 * Voir la description de ce TDA dans l'enonce du TP2 (INF2120 A20).
 * 
 * @author Melanie Lord
 * @version Automne 2020
 * @param <T> le type des elements dans cette file de priorite. 
 *            Le type des elements doit implementer l'interface ITachePrio.
 */
public interface IFilePrio <T extends ITachePrio> {

   /**
    * Enfile l'element (non null) dans cette file de priorite.
    * 
    * Exemples : 
    *    Soit la file de priorite suivante : 
    *       tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    *    - l'appel de enfiler(e7(5)) donne la file : 
    *          tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e7(5), e6(3) ] fin
    * 
    *    - l'appel de enfiler(e8(7)) donne la file : 
    *          tete [ e1(9), e2(9), e8(7), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    * @param element l'element a enfiler dans cette file de priorite.
    * @throws NullPointerException si l'element donne en parametre est null.
    */
   void enfiler(T element);

   /**
    * Defile l'element le plus prioritaire (premier arrivee de la plus grande 
    * priorite) de cette file de priorite.
    * 
    * Exemple : 
    *    Soit la file de priorite suivante : 
    *       tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    *    - l'appel de defiler() retourne e1(9), et la file resultante est : 
    *         tete [ e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    * @return l'element defile.
    * @throws FileVideException si cette file de priorite est vide avant l'appel 
    *         de cette methode.
    */
   T defiler() throws FileVideException;
   
   /**
    * Defile l'element le plus prioritaire de la priorite donnee en parametre. 
    * Si aucun element de la priorite donnee n'existe dans cette file de priorite, 
    * la methode retourne null et cette file de priorite n'est pas modifiee.
    * 
    * Exemples : 
    *    Soit la file de priorite suivante : 
    *       tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    *    - l'appel de defiler(5) retourne e3(5), et la file resultante est : 
    *         tete [ e1(9), e2(9), e4(5), e5(5), e6(3) ] fin
    * 
    *    - l'appel de defiler(9) retourne e1(9), et la file resultante est : 
    *         tete [ e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    *    - l'appel de defiler(4) retourne null, et la file reste inchangee. 
    * 
    * @param priorite la priorite de l'element a defiler.
    * @return l'element defile ou null si aucun element de la priorite donnee
    *         en parametre n'existe dans cette file de priorite.
    * @throws FileVideException si cette file de priorite est vide avant qu'on 
    *         ne tente de defiler l'element. 
    */
   T defiler(int priorite) throws FileVideException;
   
   /**
    * Defile tous les elements de la priorite donnee. Si aucun element de cette
    * priorite n'existe dans cette file de priorite, celle-ci n'est pas modifiee.
    * La methode retourne une file de priorite contenant tous les elements 
    * defiles, dans le meme ordre que lorsqu'ils se trouvaient dans cette file
    * de priorite. Si aucun element n'est defile, la file retournee est vide.
    * 
    * Exemples : 
    *    Soit la file de priorite suivante : 
    *       tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    *    - l'appel de defilerTous(5) retourne la file suivante : 
    *          tete [ e3(5), e4(5), e5(5) ] fin
    * 
    *          et la file resultante modifiee est : 
    *          tete [ e1(9), e2(9), e6(3) ] fin
    * 
    *    - l'appel de defilerTous(4) retourne une file vide et la file resultante
    *      reste inchangee.
    * 
    * @param priorite la priorite des elements a defiler de cette file de 
    *                 priorite.
    * @return Une file de priorite contenant tous les elements defiles, dans 
    *         le meme ordre.
    * @throws FileVideException si cette file de priorite est vide avant
    *         l'appel de cette methode.
    */
   IFilePrio<T> defilerTous(int priorite) throws FileVideException;
   
   /**
    * Verifie si cette file de priorite contient au moins un element ayant la 
    * priorite donnee en parametre.
    * @param priorite la priorite dont on veut verifier l'existence dans cette
    *                 file de priorite.
    * @return true si au moins un element ayant la priorite donnee en parametre
    *         existe dans cette file de priorite, false sinon.
    */
   boolean prioriteExiste(int priorite);
   
   /**
    * Verifie si cette file de priorite contient des elements ou non.
    * @return true si cette file de priorite ne contient aucun element, false
    *         sinon.
    */
   boolean estVide();
   
   /**
    * Permet d'obtenir le nombre d'elements contenus dans cette file de priorite.
    * @return le nombre d'elements dans cette file de priorite.
    */
   int taille();
   
   /**
    * Permet d'obtenir le nombre d'elements ayant la priorite donnee en parametre
    * qui sont contenus dans cette file de priorite.
    * @param priorite la priorite des elements dont on veut le nombre.
    * @return le nombre d'elements ayant la priorite donnee en parametre qui sont 
    *         contenus dans cette file de priorite.
    */
   int taille(int priorite);
   
   /**
    * Permet de consulter l'element en tete de cette file de priorite, sans 
    * modifier celle-ci. L'element en tete de file est toujours l'element 
    * le plus ancien parmis ceux ayant la priorite la plus forte.
    * @return l'element en tete de cette file de priorite.
    * @throws FileVideException si cette file de priorite est vide avant l'appel
    *         de cette methode.
    */
   T premier() throws FileVideException;
   
   /**
    * Permet de consulter l'element le plus prioritaire de la priorite donnee
    * en parametre, sans modifier cette file de priorite. Si aucun element 
    * de la priorite donnee existe dans cette file de priorite, la methode 
    * retourne null.
    * @param priorite la priorite de l'element le plus prioritaire que l'on veut
    *                 consulter.
    * @return l'element le plus prioritaire de la priorite donnee en parametre.
    * @throws FileVideException si cette file de priorite est vide avant l'appel
    *         de cette methode.
    */
   T premier(int priorite) throws FileVideException;
   
   /**
    * Retire tous les elements de cette file de priorite. Apres l'appel de cette
    * methode, l'appel de la methode estVide() retourne true.
    */
   void vider();  
 
   /**
    * Retourne une file de priorite contenant tous les elements ayant la priorite 
    * donnee en parametre se trouvant dans cette file de priorite. Les elements
    * dans la file de priorite a retourner doivent conserver l'ordre dans lequel
    * ils apparaissent dans cette file de priorite. Apres l'appel de cette methode,
    * cette file de priorite ne doit pas avoir ete modifiee. De plus, si aucun 
    * element ayant la priorite donnee ne se trouve dans cette file de priorite,
    * la methode retourne une file de priorite vide.
    * 
    * Exemples : 
    *    Soit la file de priorite suivante : 
    *       tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
    * 
    *    - l'appel de sousFilePrio(5) retourne la file suivante : 
    *          tete [ e3(5), e4(5), e5(5) ] fin
    * 
    *    - l'appel de sousFilePrio(4) retourne une file vide
    * 
    * @param priorite la priorite des elements de la file de priorite a retourner.
    * @return une file de priorite contenant tous les elements ayant la priorite 
    *         donnee en parametre se trouvant dans cette file de priorite.
    */
   IFilePrio<T> sousFilePrio(int priorite);
   
   /**
    * Teste si cette file de priorite contient au moins un element identique a 
    * celui donne en parametre. Un element e1 est identique a un element e2 
    * si e1.equals(e2) retourne true.
    * @param elem l'element dont on teste l'existence.
    * @return true s'il existe au moins un element dans cette file de priorite
    *         qui est identique a celui donne en parametre, false sinon.
    */
   boolean contient(T elem) throws FileVideException;
   
   /**
    * Normalise les priorites des elements de cette file de priorite
    * en modifiant celles-ci pour que la plus petite priorite devienne 1, que 
    * la deuxieme plus petite priorite devienne 2, et ainsi de suite, jusqu'a la
    * plus grande priorite (qui correspondra au nombre de priorites differentes 
    * dans cette file de priorite). 
    * 
    * Par exemple, soit la file de priorite suivante (qui contient 4 priorites
    * differentes) : 
    *    tete [ e1(9), e2(9), e3(5), e4(5), e5(3), e6(-4) ] 
    * 
    * Apres normalisation, la file de priorite devient : 
    *    tete [ e1(4), e2(4), e3(3), e4(3), e5(2), e6(1) ] fin
    * 
    * Si la file de priorite etait deja sous sa forme "normale", elle demeure 
    * inchangee.
    */
   void normaliser();
   
   /**
    * Elimine les doublons de cette file de priorite. Si une sousFile de priorite
    * contient, par exemple, 3 elements identiques, la methode elimine les deux
    * moins prioritaire (en terme du moment d'entree dans la file). Par exemple :
    * 
    * Soit le file de priorite suivante qui contient 2 elements e1(9), 
    * 4 elements e3(5), et 2 elements e4(5) : 
    *    tete [ e1(9), e2(9), e1(9), e3(5), e3(5), e4(5), e3(5), e4(5), e8(5), e3(5) ] 
    * 
    * Apres l'elimination des doublons, la file de priorite devient :
    *    tete [ e1(9), e2(9), e3(5), e4(5), e8(5) ] 
    * 
    * Note : un element e1 est identique a un element e2 si e1.equals(e2) 
    * retourne true.
    * 
    * Si la file de priorite ne contenait aucun doublon(s), elle demeure
    * inchangee.
    * 
    */
   void eliminerDoublons() throws FileVideException;
   
   /**
    * Permet d'obtenir la priorite la plus grande parmi les priorites de tous 
    * les elements de cette file de priorite.
    * @return la priorite maximum dans cette file de priorite.
    * @throws FileVideException si cette file de priorite est vide avant l'appel 
    *         de cette methode.
    */
   int prioriteMax() throws FileVideException;
   
   /**
    * Permet d'obtenir la priorite la plus petite parmi les priorites de tous 
    * les elements de cette file de priorite.
    * @return la priorite minimum dans cette file de priorite.
    * @throws FileVideException si cette file de priorite est vide avant l'appel 
    *         de cette methode.
    */
   int prioriteMin() throws FileVideException;
   
   /**
    * Retourne une copie de cette file de priorite. 
    * 
    * NOTE POUR LES ETUDIANTS : La copie retournee ne doit pas pointer vers le  
    * meme espace memoire que la file a copier. De plus, chacun des maillons 
    * de la file copiee ne doit pas pointer sur aucun des maillons de la file a 
    * copier. Par contre, l'info dans chaque maillon de la file copiee peut 
    * pointer sur la meme info que celle se trouvant dans les maillons de la 
    * file a copier. 
    * @return une copie de cette file de priorite.
    */
   IFilePrio<T> copie ();
 
}
