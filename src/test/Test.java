/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;

/**
 *
 * @author antoi
 */
public class Test {

    /**
     *
     * @param couleur
     * @param Cellules
     * @return
     */
    public static boolean grandTest(String couleur, String[][] Cellules) {
        //on va tout d'abord creer une methode qui recherche en fonction de
        //parametre si il y aa un alignement, elle prend un point de depart, et
        //un deplacement, par exemple pour les ligne on a 0 et 1, colonne 1 et 0
        //et ainsi de suite ... 
        //Avec une telle fonction il suffit de faire :

        //1 Ligne :
        for (int ligne = 0; ligne < 7; ligne++) {
            if ((chercheAlign(0, ligne, 1, 0, Cellules,couleur))==true) {
                return true;
            }
        }
        //2 Colonne : 
        for (int colonne = 0; colonne < 6; colonne++) {
            if (chercheAlign(colonne, 0, 0, 1, Cellules,couleur)) {
                return true;
            }
        }
        //3 Diago Monte :
        for (int colonne = 0; colonne < 7; colonne++) {
            // Première diagonale ( / )
            if (chercheAlign(colonne, 0, 1, 1, Cellules,couleur)) {
                return true;
            }
            //3 Diago Descend : 
            if (chercheAlign(colonne, 0, -1, 1, Cellules,couleur)) {
                return true;
            }

        }
        //3 Diago Des Colonne : 
        for (int ligne = 0; ligne < 6; ligne++) {
            // Première diagonale ( / )
            if (chercheAlign(0, ligne, 1, 1, Cellules,couleur)) {
                return true;
            }
            // Deuxième diagonale ( \ )
            if (chercheAlign(6 - 1, ligne, -1, 1, Cellules,couleur)) {
                return true;
            }
        }

        return false;
    }

    public static boolean chercheAlign(int i, int j, int di, int dj, String[][] tab,String couleur) {
        //i=ligne de notre cellule de recherche
        //j=colonne de notre cellule de recherche
        //di=deplacement en i
        //dj=deplacement en j
        int compteur = 0;

        int curCol = j;
        int curRow = i;

        while ((curCol >= 0) && (curCol < 7) && (curRow >= 0) && (curRow < 6)) {
            if (tab[curRow][curCol] != couleur) {
                // Si la couleur change, on réinitialise le compteur
                couleur = tab[curRow][curCol];
                compteur = 1;
            } else {
                // Sinon on l'incrémente
                compteur++;
            }

            // On sort lorsque le compteur atteint 4
            if ((couleur != "VIDE") && (compteur == 4)) {
                return true;
            }

            // On passe à l'itération suivante
            curCol += j;
            curRow += i;
        }

        // Aucun alignement n'a été trouvé
        return false;
    }

    public static boolean testeur(String couleur, String[][] Cellules) {
        String Couleur = couleur;
        //Pour verifier qu'une grille est gagnante pour un joueur on doit 
        //verifier plusieurs choses ...
        //par colonne il y a trois possibilités de gagne
        //par ligne il y a quatre possibilités de gagne
        //en diagonales, il y a 4*(1 possibilité, 2,3 possibilités)
        //Donc 3*7 + 4*6 + 4*(1+2+3)=69 possibilités de gagne.

        //1 Ligne :
        //on va parcourir nos lignes. Dans chaque lignes on regarde notre
        //premier element. On le compare au suivant et si c'est le meme on 
        //garde 1 en memoire, on regarde le suivant et on incremente notre
        //indice. Si notre indice==4 on sort et c'est bon. Sinon on continue
        //et on verifie a chaque ligne, on fera ensuite pareil avec les
        //colonnes :
        int comptligne = 0;
        for (int i = 0; i < 6; i++) {
            //a chaque ligne le compteur est remis a zero
            comptligne = 0;
            String couleur1 = Cellules[i][0];

//premiere couleur de chaque ligne
            for (int j = 1; j < 7; j++) {
                String couleur2 = Cellules[i][j];
                if ((couleur1 == Couleur) && (comptligne == 0)) {
                    comptligne++;
                }
                if ((couleur1 == couleur2) && (couleur1 == Couleur)) {
                    comptligne++;//si la couleur de ma cellule correspond a ma
                    //couleur d'avant mon indice s'incremente, sinon mon indice
                    //retombe a zero ...
                    //on remarque que pour la premiere colonne l'indice vaut bien
                    //1 ... 
                } else {
                    comptligne = 0;
                }
                //a la fin on donne les bonnes valeurs aux indice (comme si on avait
                //une suite ... ) 
                couleur1 = couleur2;//en entrain de nouveau dans la boucle couleur2
                //change (ou pas) de valeur ... 

                //apres avoir parcouru toutes nos lignes on verifie que
                //l'indice est superieur ou egal a quatre
                if (comptligne >= 4) {
                    return true;
                }
            }
        }
//return false;/*
        //2 Colonne
        //on va procede comme avant mais en se deplacer par colonne
        //et en verifiant nos lignes : 
        int comptcolonne = 0;
        for (int j = 0; j < 7; j++) {
            //a chaque ligne le compteur est remis a zero
            comptcolonne = 0;
            String couleur1 = Cellules[0][j];//premiere couleur de chaque ligne

            for (int i = 1; i < 6; i++) {
                String couleur2 = Cellules[i][j];
                if ((couleur1 == Couleur) && (comptcolonne == 0)) {
                    comptcolonne++;
                }
                if ((couleur1 == couleur2) && (couleur1 == Couleur)) {
                    comptcolonne++;//si la couleur de ma cellule correspond a ma
                    //couleur d'avant mon indice s'incremente, sinon mon indice
                    //retombe a zero ...
                    //on remarque que pour la premiere colonne l'indice vaut bien
                    //1, donc on ne demarre pas a la colonne 2 ... 
                } else {
                    comptcolonne = 0;
                }
                //a la fin on donne les bonnes valeurs aux indice (comme si on avait
                //une suite ... ) 
                couleur1 = couleur2;//en entrain de nouveau dans la boucle couleur2
                //change (ou pas) de valeur ... 

                //apres avoir parcouru toutes nos lignes on verifie que
                //l'indice est superieur ou egal a quatre
                if (comptcolonne >= 4) {
                    return true;
                }
            }
        }

        //3 Diagonales
        //Pour les diagonales c'est plus compliqué ...
        //on a 12 'ligne' de diagonale possible, donc douze verifications
        //de 'ligne' a faire ...
        // en fait on a ca :
        //  [\][\][\][\][ ][ ][ ]
        //  [\][ ][ ][ ][ ][ ][ ]
        //  [\][ ][ ][ ][ ][ ][ ]
        //  [/][ ][ ][ ][ ][ ][ ]
        //  [/][ ][ ][ ][ ][ ][ ]
        //  [/][/][/][/][ ][ ][ ]
        // a chaque trais on a une diagonale possible
        // on aura donc une symetrie dans le schema ... 
        int comptdiago = 0;
        for (int i = 0; i < 6; i++) {//on parcourt notre premiere colonne
            String couleur1 = Cellules[i][0];
            if (i < 3) {//on cherche les diagonales qui descendent ... 
                int colonne = 0;//indice des colonnes
                String couleur2 = null;

                for (int k = i; k < 6; k++) {//on part de la case et on descend en
                    //ligne jusqu'a la fin de la grille soit 6 ...

                    couleur2 = Cellules[k][colonne];
                    if ((couleur1 == Couleur) && (comptdiago == 0)) {
                        comptdiago++;

                    }
                    if ((couleur2 == couleur1) && (couleur1 == Couleur)) {
                        comptdiago++;

                    } else {
                        comptdiago = 0;
                    }
                    couleur1 = couleur2;//on echange nos couleurs
                    colonne++;//on incremente notre colonne

                    if (comptdiago >= 4) {
                        return true;
                    }

                }
                comptdiago = 0;
            } else {
                int colonne = 0;//indice des colonnes
                String couleur2 = null;
                for (int k = i; k > 0; k = k - 1) {//on part de la case et on monte en
                    //ligne jusqu'a la fin de la grille soit 0 ...

                    couleur2 = Cellules[k][colonne];
                    if ((couleur1 == Couleur) && (comptdiago == 0)) {
                        comptdiago++;
                    }
                    if ((couleur2 == couleur1) && (couleur1 == Couleur)) {
                        comptdiago++;

                    } else {
                        comptdiago = 0;
                    }
                    couleur1 = couleur2;//on echange nos couleurs
                    colonne++;//on incremente notre colonne

                    if (comptdiago >= 4) {
                        return true;
                    }
                }
                comptdiago = 0;
            }
        }
        // Pour le reste des lignes de diagonale :

        //  [\][\][\][\][ ][ ][ ]
        //  [\][ ][ ][ ][ ][ ][ ]
        //  [\][ ][ ][ ][ ][ ][ ]
        //  [/][ ][ ][ ][ ][ ][ ]
        //  [/][ ][ ][ ][ ][ ][ ]
        //  [/][/][/][/][ ][ ][ ]
        comptdiago = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 4; j++) {

                if (i == 0) {

                    int ligne = 0;//indice des lignes
                    String couleur1 = Cellules[ligne][j];
                    //diagonale qui descend :

                    String couleur2 = null;
                    for (int k = j; k < 7; k++) {//on part de la case et on descend en
                        //colonne jusqu'a la fin de la grille soit 7 ...

                        couleur2 = Cellules[ligne][k];
                        if ((couleur1 == Couleur) && (comptdiago == 0)) {
                            comptdiago++;
                        }
                        if ((couleur2 == couleur1) && (couleur1 == Couleur)) {
                            comptdiago++;

                        } else {
                            comptdiago = 0;
                        }
                        couleur1 = couleur2;//on echange nos couleurs
                        ligne++;//on incremente notre colonne

                        if (comptdiago >= 4) {
                            return true;
                        }

                    }
                    comptdiago = 0;
                }
                if (i == 1) {
                    int ligne = 5;//indice des lignes
                    String couleur1 = Cellules[ligne][j];
                    //diagonale qui descend :

                    String couleur2 = null;
                    for (int k = j; k < 7; k++) {//on part de la case et on descend en
                        //colonne jusqu'a la fin de la grille soit 7 ...

                        couleur2 = Cellules[ligne][k];
                        if ((couleur1 == Couleur) && (comptdiago == 0)) {
                            comptdiago++;
                        }
                        if ((couleur2 == couleur1) && (couleur1 == Couleur)) {
                            comptdiago++;

                        } else {
                            comptdiago = 0;
                        }
                        couleur1 = couleur2;//on echange nos couleurs
                        ligne = ligne - 1;//on incremente notre colonne

                        if (comptdiago >= 4) {
                            return true;
                        }

                    }
                    comptdiago = 0;

                }
            }

        }
//si on a rien eu de tout ca, on renvoie false
        return false;

//au therme de l'ecriture de la methode, on s'est rendu compte que notre methode
//etait simplement capable de dire si une grille etait gagnante, mais ne 
//dependait pas de la couleur du joueur ...
//on a donc rajoute une condition dans le if pour verifier que la couleur 
//correspond ... 
//*/
    }

    public static void tasserGrille(int i, int j, String[][] tab) {
        j = j - 1;
        String a = tab[0][j];//contient la cellule 1 (0) de notre colonne
        tab[0][j] = null;//on vide notre premiere cellule
        for (int k = 1; k < i; k++) {//pour k va de la ligne zero a la ligne i
            //si l'indice était 0 alors on entre pas dans la boucle
            //puisque 1!<0, pour le reste on effectue :

            String b = tab[k][j];//contient la cellule actuelle
            tab[k][j] = a;
            a = b;
            //a la fin l'objet a contient la cellule ciblee au depart

        }
    }

    public static void affiche(String[][] tab) {
        int i = 0, j = 0;

        for (i = 0; i < 6; i++) {
            String ligne = tab[i][j];
            for (j = 1; j < 7; j++) {
                ligne = ligne + " " + tab[i][j] + " ";
            }
            j = 0;
            System.out.println(ligne);
        }

    }

    public static void main(String[] args) {
        Random r = new Random();//un objet r de classe random

        String[][] monTableau = new String[6][7];
        /*
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int n = r.nextInt(2);//un nombre entier n aleatoire entre 0 et 1
                if (n == 0) {//dans un cas ... 
                    monTableau[i][j] = "J";

                } else {//dans l'autre cas ... 
                    monTableau[i][j] = "R";
                }
            }
    
        }*/

        for (int k = 5; k < 6; k++) {
            monTableau[k][k] = "R";
        }

        affiche(monTableau);
        System.out.println(" ");
        tasserGrille(6, 6, monTableau);
        affiche(monTableau);
        System.out.println(" ");
        //System.out.println(testeur("R", monTableau));
        System.out.println(grandTest("R", monTableau));
    }
}
