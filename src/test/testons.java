/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author antoi
 */
public class testons {
    String[][] Cellules = new String[6][7];
    public boolean testeur(String couleur, String[][] Cellules) {
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
            for (int j = 0; j < 7; j++) {
                String couleur2 = Cellules[i][j];
                if ((couleur1 == couleur2) || (couleur1 == Couleur)) {
                    comptligne++;//si la couleur de ma cellule correspond a ma
                    //couleur d'avant mon indice s'incremente, sinon mon indice
                    //retombe a zero ...
                    //on remarque que pour la premiere colonne l'indice vaut bien
                    //1, donc on ne demarre pas a la colonne 2 ... 
                } else {
                    comptligne = 0;
                }
                //a la fin on donne les bonnes valeurs aux indice (comme si on avait
                //une suite ... ) 
                couleur1 = couleur2;//en entrain de nouveau dans la boucle couleur2
                //change (ou pas) de valeur ... 

            }
            //apres avoir parcouru toutes nos lignes on verifie que
            //l'indice est superieur ou egal a quatre
            if (comptligne >= 4) {
                return true;
            }
        }

        //2 Colonne
        //on va procede comme avant mais en se deplacer par colonne
        //et en verifiant nos lignes : 
        int comptcolonne = 0;
        for (int j = 0; j < 7; j++) {
            //a chaque ligne le compteur est remis a zero
            comptcolonne = 0;
            String couleur1 = Cellules[0][j];//premiere couleur de chaque ligne
            for (int i = 0; i < 6; i++) {
                String couleur2 = Cellules[i][j];
                if ((couleur1 == couleur2) || (couleur1 == Couleur)) {
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

            }
            //apres avoir parcouru toutes nos lignes on verifie que
            //l'indice est superieur ou egal a quatre
            if (comptcolonne >= 4) {
                return true;
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
            if (i < 4) {//on cherche les diagonales qui descendent ... 
                int colonne = 0;//indice des colonnes
                String couleur2 = null;
                for (int k = i; k < 6 - i; k++)//on part de la case et on descend en
                //ligne jusqu'a la fin de la grille soit 6-i ...
                {
                    couleur2 = Cellules[k][colonne];
                }
                if ((couleur2 == couleur1) || (couleur1 == Couleur)) {
                    comptdiago++;

                } else {
                    comptdiago = 0;
                }
                couleur1 = couleur2;//on echange nos couleurs
                colonne++;//on incremente notre colonne

                if (comptdiago >= 4) {
                    return true;
                }
                comptdiago = 0;
            } else {
                int colonne = 0;//indice des colonnes
                String couleur2 = null;
                for (int k = i; k > 0; k = k - 1)//on part de la case et on monte en
                //ligne jusqu'a la fin de la grille soit 0 ...
                {
                    couleur2 = Cellules[k][colonne];
                }
                if ((couleur2 == couleur1) || (couleur1 == Couleur)) {
                    comptdiago++;

                } else {
                    comptdiago = 0;
                }
                couleur1 = couleur2;//on echange nos couleurs
                colonne++;//on incremente notre colonne

            }
            if (comptdiago >= 4) {
                return true;
            }
            comptdiago = 0;
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

                    int ligne = 0;//indice des colonnes
                    String couleur1 = Cellules[ligne][j];
                    //diagonale qui descend :

                    String couleur2 = null;
                    for (int k = j; k < 7 - j; k++)//on part de la case et on descend en
                    //colonne jusqu'a la fin de la grille soit 6-j ...
                    {
                        couleur2 = Cellules[ligne][k];
                    }
                    if ((couleur2 == couleur1) || (couleur1 == Couleur)) {
                        comptdiago++;

                    } else {
                        comptdiago = 0;
                    }
                    couleur1 = couleur2;//on echange nos couleurs
                    ligne++;//on incremente notre colonne

                    if (comptdiago >= 4) {
                        return true;
                    }
                    comptdiago = 0;

                }
                if (i == 1) {

                    int ligne = 6;//indice des colonnes
                    String couleur1 = Cellules[ligne][j];
                    //diagonale qui descend :

                    String couleur2 = null;
                    for (int k = j; k < 7 - j; k++)//on part de la case et on descend en
                    //colonne jusqu'a la fin de la grille soit 6-j ...
                    {
                        couleur2 = Cellules[ligne][k];
                    }
                    if ((couleur2 == couleur1) || (couleur1 == Couleur)) {
                        comptdiago++;

                    } else {
                        comptdiago = 0;
                    }
                    couleur1 = couleur2;//on echange nos couleurs
                    ligne = ligne - 1;//on incremente notre colonne

                    if (comptdiago >= 4) {
                        return true;
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
    }

}
