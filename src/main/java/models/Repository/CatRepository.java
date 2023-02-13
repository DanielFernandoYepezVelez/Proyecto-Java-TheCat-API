package models.Repository;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import models.Entity.Cat;
import models.Entity.FavouriteCat;
import ui.UIMenuCats;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CatRepository {
    public static void catList() throws IOException {
        /* 1. Vamos A Traer Los Datos De Una API */
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        /* Cortar Los Corchetes, Para Que Se Entienda El JSON*/
        String json = response.body().string();
        json = json.substring(1, json.length());
        json = json.substring(0, json.length() - 1);

        /* Crear Un Objeto De La Clase Gson */
        Gson gson = new Gson();
        /* El Json Se Parsea Al Objeto Cat */
        Cat cat = gson.fromJson(json, Cat.class);

        /* Redimensionar La Imagen */
        Image image = null;

        try {
            URL url = new URL(cat.getUrl());

            image = ImageIO.read(url);
            ImageIcon catBackground = new ImageIcon(image);

            if (catBackground.getIconWidth() > 800) {
                // Redimensionar El Fondo Si Es Mayor A 800
                Image background = catBackground.getImage();
                Image backgroundModified = background.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                catBackground = new ImageIcon(backgroundModified);
            }

            UIMenuCats.showMenuImage(cat.getId(), catBackground, cat);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void favouriteCat(Cat cat) {
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n\"image_id\": \"" + cat.getId() + "\"\r\n}");

            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", cat.getApiKey())
                    .build();

            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void favouriteCatList(String apiKey) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .addHeader("x-api-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();

        // Guardamos El String Con La Respuesta
        String json = response.body().string();

        // Creamos El Objeto Gson
        Gson gson = new Gson();

        FavouriteCat[] favouriteCats = gson.fromJson(json, FavouriteCat[].class);

        if (favouriteCats.length > 0) {
            int minValue = 1;
            int maxValue = favouriteCats.length;
            int randomValue = (int) (Math.random() * ((maxValue - minValue) + 1)) + minValue;
            int indice = randomValue - 1;

            FavouriteCat favouriteCat = favouriteCats[indice];

            /* Redimensionar La Imagen */
            Image image = null;

            try {
                URL url = new URL(favouriteCat.getImage().getUrl());

                image = ImageIO.read(url);
                ImageIcon catBackground = new ImageIcon(image);

                if (catBackground.getIconWidth() > 800) {
                    // Redimensionar El Fondo Si Es Mayor A 800
                    Image background = catBackground.getImage();
                    Image backgroundModified = background.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                    catBackground = new ImageIcon(backgroundModified);
                }

                UIMenuCats.showMenuImageFav(apiKey, favouriteCat, catBackground);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void favouriteCatDelete(FavouriteCat favouriteCat) {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/" + favouriteCat.getId() + "")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", favouriteCat.getApiKey())
                    .build();

            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
