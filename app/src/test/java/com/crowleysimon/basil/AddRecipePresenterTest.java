package com.crowleysimon.basil;

import com.crowleysimon.basil.data.repository.RecipeRepository;
import com.crowleysimon.basil.presentation.ViewNotFoundException;
import com.crowleysimon.basil.presentation.addrecipe.AddRecipePresenter;
import com.crowleysimon.basil.presentation.addrecipe.AddRecipePresenterImpl;
import com.crowleysimon.basil.view.addrecipe.AddRecipeView;
import com.schinizer.rxunfurl.model.PreviewData;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddRecipePresenterTest {

    private AddRecipePresenter presenter;
    private RecipeRepository mockRepository;
    private AddRecipeView mockView;

    @Before
    public void setup() {
        mockRepository = mock(RecipeRepository.class);
        mockView = mock(AddRecipeView.class);
        presenter = new AddRecipePresenterImpl(mockRepository);
        presenter.setView(mockView);
    }

    @Test
    public void errorOnEmptyUrl() {
        when(mockView.getUrl()).thenReturn(null);
        presenter.generateRecipe();

        verify(mockView, times(1)).clearErrorState();
        verify(mockView, times(1)).showUrlIsRequired();
        verify(mockView, never()).getRecipeFromUrl(any());
    }

    @Test
    public void successOnCorrectlyFormattedUrl() {
        when(mockView.getUrl()).thenReturn("http://www.google.com");
        presenter.generateRecipe();

        verify(mockView, times(1)).clearErrorState();
        verify(mockView, never()).showUrlIsRequired();
        verify(mockView, times(1)).getRecipeFromUrl("http://www.google.com");
    }

    @Test
    public void showErrorOnFailingToGenerateRecipe() {
        when(mockRepository.storeRecipe(any())).thenReturn(false);
        PreviewData previewData = new PreviewData();
        previewData.setDescription("description");
        previewData.setTitle("title");
        previewData.setUrl("url");

        presenter.generateRecipe();
        verify(mockView, times(1)).showStorageError();
        verify(mockView, never()).showSuccess();
    }

    @Test
    public void showSuccessOnGeneratingRecipe() {
        when(mockRepository.storeRecipe(any())).thenReturn(true);
        PreviewData previewData = new PreviewData();
        previewData.setDescription("description");
        previewData.setTitle("title");
        previewData.setUrl("url");

        presenter.generateRecipe();
        verify(mockView, times(1)).showSuccess();
        verify(mockView, never()).showStorageError();
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
        presenter.saveRecipe();
    }
}
