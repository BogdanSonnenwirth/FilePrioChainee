package me.sonnenwirth;

/**
 * Classe qui modelise un maillon dans une liste de maillons chaines comportant
 * des elements qui possedent une priorite. Classe utilisee dans le cadre du 
 * TP2 INF2120 A20.
 *
 * @author Melanie Lord
 * @version Automne 2020
 * @param <T> le type de l'information stockee dans ce maillon. T doit implementer
 *            l'interface ITachePrio.
 */
public class Maillon<T extends ITachePrio> {

   private T info;             //l'information dans ce maillon (l'element)
   private Maillon<T> suivant; //le maillon suivant

   /**
    * Cree un nouveau maillon n'ayant pas de maillon suivant.
    * @param info l'information qui sera stockee dans le maillon
    */
   public Maillon(T info) {
      this(info, null);
   }

   /**
    * Cree un nouveau maillon ayant un maillon suivant.
    * @param info l'information qui sera stockee dans le maillon
    * @param suivant le maillon qui sera le suivant du maillon cree
    */
   public Maillon(T info, Maillon<T> suivant) {
      this.info = info;
      this.suivant = suivant;
   }

   /**
    * Permet d'obtenir l'info de ce maillon.
    * @return l'info de ce maillon.
    */
   public T getInfo() {
      return info;
   }

   /**
    * Permet d'obtenir le maillon suivant de ce maillon.
    * @return le maillon suivant de ce suivant ou null si aucun suivant.
    */
   public Maillon<T> getSuivant() {
      return suivant;
   }

   /**
    * Permet de modifier l'info de ce maillon par l'info donnee en parametre.
    * @param info la nouvelle info pour ce maillon.
    */
   public void setInfo(T info) {
      this.info = info;
   }

   /**
    * Permet de modifier le suivant de ce maillon par le maillon donne en 
    * parametre.
    * @param suivant le nouveau suivant de ce maillon.
    */
   public void setSuivant(Maillon<T> suivant) {
      this.suivant = suivant;
   }

   /**
    * Construit une representation sous forme de chaine de caracteres de ce 
    * maillon.
    * @return une representation sous forme de chaine de caracteres de ce 
    *         maillon.
    */
   @Override
   public String toString() {
      String s = getInfo() + ", ";
      if (getSuivant() == null) {
         s = s.substring(0, s.length() - 2);
      } else {
         s = s + getSuivant();  //appel recursif
      }
      return s;
   }
}
