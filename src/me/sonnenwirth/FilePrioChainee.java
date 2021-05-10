package me.sonnenwirth;
/**
 * Cette classe représente le TP2. On retrouvera majoritairement des
 * redefinitions des methodes de l'interface IFilePrio, mais avec
 * quelques ajustements. En somme, cette classe permet de creer
 * une file de listes chainees.
 *
 * @author Bogdan Sonnenwirth
 * Code permanent : SONB01029707
 * Courriel: sonnenwirth.bogdan@courrier.uqam.ca
 * Cours: INF2120-20
 * @version 2020-11-30
 */

public class FilePrioChainee <T extends ITachePrio> implements  IFilePrio<T> {
    /**
     * Ceux-cis sont les attributs d'instance qui ont pour fonction
     * d'avoir une taille et d'avoir un Maillon pour inserer
     * des elements
     */
    //-------ATTRIBUTS D'INSTANCE SECTION 2.1.2-------
    private int taille;
    private Maillon<T> elements;
    //------------------------------------------------

    /**
     * Constructeur de la file. Initialise a une taille 0 avec null elements
     */
    //-------CONSTRUCTEUR SECTION 2.1.3-------
    public FilePrioChainee() {
        taille = 0;
        elements = null;
    }
    //----------------------------------------

    /**
     * Enfile l'element (non null) dans cette file de priorite.
     *
     * @param element l'element a enfiler dans cette file de priorite.
     * @throws NullPointerException si l'element donne en parametre est null.
     */
    @Override
    public void enfiler(T element) throws NullPointerException {
        Maillon<T> debut = this.elements;
        Maillon<T> nouveau = new Maillon<>(element);
        Maillon<T> tmp = debut;

        if(element == null){
            throw new NullPointerException();
        }
        //cas 1, inserer dans file vide
        if(debut == null){
            debut = nouveau;
        }else{
            //cas 2 inserer au debut dans une liste non-vide
            if(debut.getInfo().getPriorite() < element.getPriorite()){
                nouveau.setSuivant(debut);
                debut = nouveau;
            }else{
            //cas 3 inserer au milieu dans une liste non-vide
                T infoTemporaire;
                while(tmp != null &&
                        tmp.getInfo().getPriorite() >= element.getPriorite()){
                    tmp = tmp.getSuivant();
                }
                if(tmp != null){
                    nouveau.setSuivant(tmp.getSuivant());
                    tmp.setSuivant(nouveau);

                    infoTemporaire = nouveau.getInfo();
                    nouveau.setInfo(tmp.getInfo());
                    tmp.setInfo(infoTemporaire);
                } else{
                    //cas 4 inserer à la fin dans une liste non-vide
                    Maillon<T> fin = debut;
                    while(fin.getSuivant() != null){
                        fin = fin.getSuivant();
                    }
                    fin.setSuivant(nouveau);
                }
            }
        }
        this.elements = debut;
        taille++;
    }

    /**
     * Defile l'element le plus prioritaire (premier arrivee de la plus grande
     * priorite) de cette file de priorite.

     * @return l'element defile.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *         de cette methode.
     */
    @Override
    public T defiler() throws FileVideException {
        Maillon<T> tete = this.elements;
        if (estVide()){
            throw new FileVideException();
        }
        this.elements = tete.getSuivant();
        taille--;
        return tete.getInfo();
    }

    /**
     * Defile l'element le plus prioritaire de la priorite donnee en parametre.
     * Si aucun element de la priorite donnee n'existe dans cette file de priorite,
     * la methode retourne null et cette file de priorite n'est pas modifiee.
     *
     * @param priorite la priorite de l'element a defiler.
     * @return l'element defile ou null si aucun element de la priorite donnee
     *         en parametre n'existe dans cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant qu'on
     *         ne tente de defiler l'element.
     */
    @Override
    public T defiler(int priorite) throws FileVideException {
        Maillon<T> debut = this.elements;
        Maillon<T> prec = debut;
        T element;
        if (estVide()){
            throw new FileVideException();
        }
        //cas 1 defile debut liste non vide
        if(debut.getInfo().getPriorite() == priorite){
            element = defiler();
        }else{
            //cas 2 defile milieu liste non vide
            while(prec.getSuivant() != null
                    && prec.getSuivant().getInfo().getPriorite() != priorite){
                prec = prec.getSuivant();
            }
            //cas 3 defile fin liste non vide
            if(prec.getSuivant() == null){
                element = null;
            }else{
                element = prec.getSuivant().getInfo();
                prec.setSuivant(prec.getSuivant().getSuivant());
            }
        }
        if(element != null){
            taille--;
        }
        return element;
    }

    /**
     * Defile tous les elements de la priorite donnee. Si aucun element de cette
     * priorite n'existe dans cette file de priorite, celle-ci n'est pas modifiee.
     * La methode retourne une file de priorite contenant tous les elements
     * defiles, dans le meme ordre que lorsqu'ils se trouvaient dans cette file
     * de priorite. Si aucun element n'est defile, la file retournee est vide.
     *
     * @param priorite la priorite des elements a defiler de cette file de
     *                 priorite.
     * @return Une file de priorite contenant tous les elements defiles, dans
     *         le meme ordre.
     * @throws FileVideException si cette file de priorite est vide avant
     *         l'appel de cette methode.
     */
    @Override
    public IFilePrio<T> defilerTous(int priorite) throws FileVideException {
        IFilePrio<T> fileContenu = sousFilePrio(priorite);
        while(prioriteExiste(priorite)){
            defiler(priorite);
        }
        return fileContenu;
    }

    /**
     * Verifie si cette file de priorite contient au moins un element ayant la
     * priorite donnee en parametre.
     * @param priorite la priorite dont on veut verifier l'existence dans cette
     *                 file de priorite.
     * @return true si au moins un element ayant la priorite donnee en parametre
     *         existe dans cette file de priorite, false sinon.
     */
    @Override
    public boolean prioriteExiste(int priorite) {
        Maillon<T> debut = this.elements;
        boolean answer = false;
        if(debut.getInfo().getPriorite() == priorite){
            answer = true;
        }else{
            while (debut.getSuivant() != null
                    && debut.getSuivant().getInfo().getPriorite() != priorite ){
                debut = debut.getSuivant();
            }
            if(debut.getSuivant() != null
                    && debut.getSuivant().getInfo().getPriorite() == priorite){
                answer = true;
            }
        }
        return answer;
    }
    /**
     * Verifie si cette file de priorite contient des elements ou non.
     * @return true si cette file de priorite ne contient aucun element, false
     *         sinon.
     */
    @Override
    public boolean estVide() {
        boolean answer = false;
        if(taille == 0){
            answer = true;
        }
        return answer;
    }

    /**
     * Permet d'obtenir le nombre d'elements contenus dans cette file de priorite.
     * @return le nombre d'elements dans cette file de priorite.
     */
    @Override
    public int taille() {
        return taille;
    }

    /**
     * Permet d'obtenir le nombre d'elements ayant la priorite donnee en parametre
     * qui sont contenus dans cette file de priorite.
     * @param priorite la priorite des elements dont on veut le nombre.
     * @return le nombre d'elements ayant la priorite donnee en parametre qui sont
     *         contenus dans cette file de priorite.
     */
    @Override
    public int taille(int priorite) {
        Maillon<T> debut = this.elements;
        int taillePriorite = 0;
        if(debut.getInfo().getPriorite() == priorite){
            taillePriorite++;
        }
        for(int i = 0; i < taille(); i++){
            debut = debut.getSuivant();
            if(debut != null && (debut.getInfo().getPriorite() == priorite)){
                taillePriorite++;
            }
        }
        return taillePriorite;
    }

    /**
     * Permet de consulter l'element en tete de cette file de priorite, sans
     * modifier celle-ci. L'element en tete de file est toujours l'element
     * le plus ancien parmis ceux ayant la priorite la plus forte.
     * @return l'element en tete de cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *         de cette methode.
     */
    @Override
    public T premier() throws FileVideException {
        Maillon<T> debut = this.elements;
        if (estVide()){
            throw new FileVideException();
        }
        return debut.getInfo(); //retourne premier element
    }

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
    @Override
    public T premier(int priorite) throws FileVideException {
        Maillon<T> debut = this.elements;
        T element = null;
        if (estVide()){
            throw new FileVideException();
        }
        if(debut.getInfo().getPriorite() == priorite){
            element = debut.getInfo();
        }else{
            while (debut.getSuivant() != null &&
                    debut.getSuivant().getInfo().getPriorite() != priorite ){
                debut = debut.getSuivant();
            }
            if(debut.getSuivant() != null &&
                    debut.getSuivant().getInfo().getPriorite() == priorite){
                element = debut.getSuivant().getInfo();
            }
        }
        return element;
    }

    /**
     * Retire tous les elements de cette file de priorite. Apres l'appel de cette
     * methode, l'appel de la methode estVide() retourne true.
     */
    @Override
    public void vider() {
        elements = null;
        taille = 0;
    }

    /**
     * Retourne une file de priorite contenant tous les elements ayant la priorite
     * donnee en parametre se trouvant dans cette file de priorite. Les elements
     * dans la file de priorite a retourner doivent conserver l'ordre dans lequel
     * ils apparaissent dans cette file de priorite. Apres l'appel de cette methode,
     * cette file de priorite ne doit pas avoir ete modifiee. De plus, si aucun
     * element ayant la priorite donnee ne se trouve dans cette file de priorite,
     * la methode retourne une file de priorite vide.

     * @param priorite la priorite des elements de la file de priorite a retourner.
     * @return une file de priorite contenant tous les elements ayant la priorite
     *         donnee en parametre se trouvant dans cette file de priorite.
     */
    @Override
    public IFilePrio<T> sousFilePrio(int priorite) {
        Maillon<T> debut = this.elements;
        IFilePrio nouveau = new FilePrioChainee();
        if(debut.getInfo().getPriorite() == priorite){
            nouveau.enfiler(debut.getInfo());
        }
        while (debut.getSuivant() != null){
            if(debut.getSuivant().getInfo().getPriorite() == priorite){
                nouveau.enfiler(debut.getSuivant().getInfo());
            }
            debut = debut.getSuivant();
        }
        return nouveau;
    }

    /**
     * Teste si cette file de priorite contient au moins un element identique a
     * celui donne en parametre. Un element e1 est identique a un element e2
     * si e1.equals(e2) retourne true.
     * @param elem l'element dont on teste l'existence.
     * @return true s'il existe au moins un element dans cette file de priorite
     *         qui est identique a celui donne en parametre, false sinon.
     */
    @Override
    public boolean contient(T elem) {
        Maillon<T> debut = this.elements;
        boolean answer = false;
        if(!estVide()){
            if(debut.getInfo().equals(elem)){
                answer = true;
            }else{
                while(debut.getSuivant() != null
                        && !(debut.getSuivant().getInfo().equals(elem))){
                    debut = debut.getSuivant();
                }
                if(debut.getSuivant() != null
                        && debut.getSuivant().getInfo().equals(elem)){
                    answer = true;
                }
            }
        }
        return answer;
    }

    /**
     * Normalise les priorites des elements de cette file de priorite
     * en modifiant celles-ci pour que la plus petite priorite devienne 1, que
     * la deuxieme plus petite priorite devienne 2, et ainsi de suite, jusqu'a la
     * plus grande priorite (qui correspondra au nombre de priorites differentes
     * dans cette file de priorite).
     *
     * Si la file de priorite etait deja sous sa forme "normale", elle demeure
     * inchangee.
     */
    @Override
    public void normaliser() {
        Maillon<T> debut = this.elements;
        Maillon<T> nouveau = debut;
        int nombre = 0;
        while(debut != null){
            if(debut.getSuivant() != null){
                if(debut.getInfo().getPriorite()
                        != debut.getSuivant().getInfo().getPriorite()){
                    nombre++;
                }
            }else{
                nombre++;
            }
            debut = debut.getSuivant();
        }
        while(nouveau != null){
            if(nouveau.getSuivant() != null) {
                if(nouveau.getInfo().getPriorite()
                        != nouveau.getSuivant().getInfo().getPriorite()){
                    nouveau.getInfo().setPriorite(nombre);
                    nombre--;
                }else{
                    nouveau.getInfo().setPriorite(nombre);
                }
            }else {
                nouveau.getInfo().setPriorite(nombre);
                nombre--;
            }
            nouveau = nouveau.getSuivant();
        }
    }

    /**
     * Elimine les doublons de cette file de priorite. Si une sousFile de priorite
     * contient, par exemple, 3 elements identiques, la methode elimine les deux
     * moins prioritaire (en terme du moment d'entree dans la file).
     *
     * Si la file de priorite ne contenait aucun doublon(s), elle demeure
     * inchangee.
     */
    @Override
    public void eliminerDoublons() {
        Maillon<T> debut = this.elements;
        FilePrioChainee<T> nouveau = new FilePrioChainee<>();
        while(debut != null){
            if(!nouveau.contient(debut.getInfo())){
                nouveau.enfiler(debut.getInfo());
            }
            debut = debut.getSuivant();
        }
        this.elements = nouveau.elements;
    }

    /**
     * Permet d'obtenir la priorite la plus grande parmi les priorites de tous
     * les elements de cette file de priorite.
     * @return la priorite maximum dans cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *         de cette methode.
     */
    @Override
    public int prioriteMax() throws FileVideException {
        return premier().getPriorite();
        //Pour une file, la plus grande priorite se situe en debut de file.
        //premier() trouve l'info du premier element
    }

    /**
     * Permet d'obtenir la priorite la plus petite parmi les priorites de tous
     * les elements de cette file de priorite.
     * @return la priorite minimum dans cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *         de cette methode.
     */
    @Override
    public int prioriteMin() throws FileVideException {
        Maillon<T> debut = this.elements;
        int prioriteMin = 0;
        if (estVide()){
            throw new FileVideException();
        }
        while (debut.getSuivant() != null){
            debut = debut.getSuivant();
        }
        if(debut.getSuivant() == null){
            prioriteMin = debut.getInfo().getPriorite();
        }
        return prioriteMin;
    }

    /**
     * Retourne une copie de cette file de priorite.
     * @return une copie de cette file de priorite.
     */
    @Override
    public IFilePrio<T> copie() {
        IFilePrio<T> copie = new FilePrioChainee<>();
        Maillon<T> courant = this.elements;
        while(courant != null){
            copie.enfiler(courant.getInfo());
            courant = courant.getSuivant();
        }
        return copie;
    }

    /**
     * Construit une representation sous forme de chaine de caracteres de cette
     * file de priorite.
     * @return une representation sous forme de chaine de caracteres de cette
     *         file de priorite.
     */
    @Override
    public String toString() {
        String s = "tete [ ";
        Maillon<T> tmp = elements;
        if (tmp == null) {
            s = s + " ] fin";
        } else {
            while (tmp != null) {
                s = s + tmp.getInfo() + ", ";
                tmp = tmp.getSuivant();
            }
            s = s.substring(0, s.length() -2) + " ] fin";
        }
        return s;
    }
}
