package com.example.application_dontfailme.data.persistence;

import com.example.application_dontfailme.data.model.Recipe;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirestoreRecipeDetaiReposlitory implements RecipeDetailRepository {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final String COLLECTION_PATH = "recipe-collection";

    @Inject
    public FirestoreRecipeDetaiReposlitory() {
    }

    @Override
    public RecipeDetailLiveData getRecipeById(String id) {
        DocumentReference temp = firestore.collection(COLLECTION_PATH)
                .document(id);
        return new RecipeDetailLiveData(temp);
    }

    @Override
    public RecipeLiveData getAllRecipes() {
        return new RecipeLiveData(firestore.collection(COLLECTION_PATH).orderBy("name", Query.Direction.DESCENDING));
    }

    @Override
    public RecipeDetailLiveData updateRecipes(Recipe recipe) {
        DocumentReference documentReference = firestore.collection(COLLECTION_PATH).document(recipe.getId());
        documentReference.set(recipe);
        return new RecipeDetailLiveData(documentReference);
    }

    @Override
    public RecipeDetailLiveData addRecipe(Recipe recipe) {
        DocumentReference documentReference = firestore.collection(COLLECTION_PATH).document();
        recipe.setId(documentReference.getId());
        documentReference.set(recipe);
        return new RecipeDetailLiveData(documentReference);
    }

    @Override
    public void removeRecipe(String id) {
        firestore.collection(COLLECTION_PATH).document(id).delete();
    }
}
