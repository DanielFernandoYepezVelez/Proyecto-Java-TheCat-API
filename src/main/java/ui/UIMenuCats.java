package ui;

import models.Entity.Cat;
import models.Entity.FavouriteCat;
import models.services.CatService;

import javax.swing.*;
import java.io.IOException;

public class UIMenuCats {
    private static int chooseMenu = -1;
    private static String[] catBotons = {"1. Ver Gatos.", "2. Ver Favoritos.", "3. Salir."};
    private static String[] imageCatBotons = {"Ver Otra Imagen", "Favorito", "Volver"};
    private static String[] favImageCatBotons = {"Ver Otra Imagen", "Eliminar Favorito", "Volver"};

    public static void showMenuUser() throws IOException {
        do {
            String option = (String) JOptionPane.showInputDialog(null, "Gatos Java",
                    "Menu Principal", JOptionPane.INFORMATION_MESSAGE, null, catBotons, catBotons[0]);

            if (option == null) {
                return;
            }

            for (int i = 0; i < catBotons.length; i++) {
                if (option.equalsIgnoreCase(catBotons[i])) {
                    chooseMenu = i;
                }
            }

            switch (chooseMenu) {
                case 0:
                    CatService.catList();
                    break;

                case 1:
                    Cat cat = new Cat();
                    CatService.favouriteCatList(cat.getApiKey());
                    break;

                default:
                    break;
            }
        } while (chooseMenu != 1);
    }

    public  static void showMenuImage(String idCat, ImageIcon catBackground, Cat cat) throws IOException {
        String menu = "Opciones: \n"
                + "1. Ver Otra Imagen. \n"
                + "2. Favorito. \n"
                + "3. Volver. \n";

        String choose = (String) JOptionPane.showInputDialog(
                null, menu, idCat, JOptionPane.INFORMATION_MESSAGE,
                catBackground, imageCatBotons, imageCatBotons[0]);

        if (choose == null) {
            return;
        }

        int chooseSelected = -1;

        for (int i = 0; i < imageCatBotons.length; i++) {
            if (choose.equalsIgnoreCase(imageCatBotons[i])) {
                chooseSelected = i;
            }
        }

        switch (chooseSelected) {
            case 0:
                CatService.catList();
                break;

            case 1:
                CatService.favouriteCat(cat);
                break;

            default:
                break;
        }
    }

    public  static void showMenuImageFav(String apiKey, FavouriteCat favouriteCat, ImageIcon catBackground) throws IOException {
        String menu = "Opciones: \n"
                + "1. Ver Otra Imagen. \n"
                + "2. Eliminar Favorito. \n"
                + "3. Volver. \n";

        String choose = (String) JOptionPane.showInputDialog(
                null, menu, favouriteCat.getId(), JOptionPane.INFORMATION_MESSAGE,
                catBackground, favImageCatBotons, favImageCatBotons[0]);

        if (choose == null) {
            return;
        }

        int chooseSelected = -1;

        for (int i = 0; i < favImageCatBotons.length; i++) {
            if (choose.equalsIgnoreCase(favImageCatBotons[i])) {
                chooseSelected = i;
            }
        }

        switch (chooseSelected) {
            case 0:
                CatService.favouriteCatList(apiKey);
                break;

            case 1:
                CatService.favouriteCatDelete(favouriteCat);
                break;

            default:
                break;
        }
    }
}
