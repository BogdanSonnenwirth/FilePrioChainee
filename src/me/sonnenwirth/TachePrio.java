package me.sonnenwirth;

/**
 * Classe fournissant une implementation de l'interface ITachePrio.
 * Une TachePrio represente une tache quelconque qui possede un description 
 * et une priorite.
 * 
 * @author Melanie Lord
 * @version Automne 2020
 */
public class TachePrio implements ITachePrio {
   
   private String description = "aucune"; //description de cette tache
   private int priorite;                  //priorite de cette tache
   
   /**
    * Construit une TachePrio possedant la priorite donnee en parametre.
    * La description est initialisee a "aucune".
    * @param priorite la priorite de cette TachePrio.
    */
   public TachePrio (int priorite) {
      this.priorite = priorite;
   }
   
   /**
    * Construit une TachePrio avec la priorite et la description donnees.
    * Si la description est null ou vide, celle-ci est plutot initialisee a 
    * "aucune".
    * @param priorite la priorite de cette TachePrio.
    * @param description la description de cette TachePrio.
    */
   public TachePrio (int priorite, String description) {
      this.priorite = priorite;
      
      if (description != null && !description.isEmpty()) {
         this.description = description;
      }
   }
   
   /**
    * Permet d'obtenir la priorite de cette TachePrio.
    * @return la priorite de cette TachePrio.
    */
   @Override
   public int getPriorite() {
      return priorite;
   }
   
   /**
    * Permet de modifier la priorite de cette TachePrio par la valeur donnee
    * en parametre.
    * @param priorite la nouvelle priorite de cette TachePrio. 
    */
   @Override
   public void setPriorite(int priorite) {
      this.priorite = priorite;
   }

   /**
    * Permet d'obtenir la description de cette TachePrio.
    * @return la description de cette TachePrio.
    */
   public String getDescription() {
      return description;
   }

   /**
    * Permet de modifier la description de cette TachePrio par la valeur donnee
    * en parametre.
    * @param description la nouvelle description de cette TachePrio.
    */
   public void setDescription(String description) {
      this.description = description;
   }
   
   /**
    * Construit une representation de cette TachePrio sous forme de chaine de
    * caracteres. La representation est formee de la description suivie de 
    * la priorite entre parentheses, comme suit : description(priorite).
    * @return une representation de cette TachePrio sous forme de chaine de
    * caracteres.
    */
   @Override
   public String toString() {
      return description + "(" + priorite + ")";
   }
   
   /**
    * Teste si cette TachePrio est egale a la tachePrio passee en parametre.
    * @param tachePrio la TachePrio a comparer avec cette TachePrio.
    * @return true si les deux TachePrio sont egales, false sinon.
    */
   @Override
   public boolean equals (Object tachePrio) {
      return  tachePrio != null 
                  && this.getClass().equals(tachePrio.getClass())  
                  && ((TachePrio)tachePrio).description.equals(this.description) 
                  && ((TachePrio)tachePrio).priorite == this.priorite;
   }
   
   
}
