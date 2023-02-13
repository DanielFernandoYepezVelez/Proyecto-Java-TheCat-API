package models.services;

import models.Entity.Cat;
import models.Entity.FavouriteCat;
import models.Repository.CatRepository;

import java.io.IOException;

public class CatService {
    public static void catList() throws IOException {
        CatRepository.catList();
    }

    public static void favouriteCat(Cat cat) {
        CatRepository.favouriteCat(cat);
    }

    public static void favouriteCatList(String apiKey) throws IOException {
        CatRepository.favouriteCatList(apiKey);
    }

    public static void favouriteCatDelete(FavouriteCat favouriteCat) {
        CatRepository.favouriteCatDelete(favouriteCat);
    }
}
