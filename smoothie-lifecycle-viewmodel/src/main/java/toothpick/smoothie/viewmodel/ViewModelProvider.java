package toothpick.smoothie.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProviders;
import javax.inject.Provider;

/**
 * Can be used in a binding to inject the view model.
 * Beware of installing such a binding in a scope that doesn't
 * depend on the activity life cycle, it would be ineffective.
 * If one wants to rewrite this class, make sure that the provider
 * doesn't leak the activity.
 * @param <T> a view model class.
 * Warning this provider is not scoped. Ideally, it should be a singleton
 * of a view model scope.
 */
public class ViewModelProvider<T extends ViewModel> implements Provider<T> {
    private final T viewModel;

    /**
     * Provides instances of a {@link ViewModel} class. Internally this provider will use
     * {@link ViewModelProviders#of(FragmentActivity)} to obtain the instance. This is required
     * so that the view model is registered by the view model extension, and its onCleared
     * method is called.
     * @param activity holds the view model.
     * @param viewModelClass the class of the view model instance to return.
     */
    public ViewModelProvider(@NonNull FragmentActivity activity,
                             @NonNull Class<? extends T> viewModelClass) {
        //we should not keep the activity, otherwise, it will leak from the view model scope
        viewModel = ViewModelProviders.of(activity).get(viewModelClass);
    }

    /**
     * Provides instances of a {@link ViewModel} class. Internally this provider will use
     * {@link ViewModelProviders#of(FragmentActivity)} to obtain the instance. This is required
     * so that the view model is registered by the view model extension, and its onCleared
     * method is called.
     * @param activity holds the view model.
     * @param factory required to build view model instances.
     * @param viewModelClass the class of the view model instance to return.
     */
    public ViewModelProvider(@NonNull FragmentActivity activity,
                             @Nullable Factory factory,
                             @NonNull Class<? extends T> viewModelClass) {
        //we should not keep the activity, otherwise, it will leak from the view model scope
        viewModel = ViewModelProviders.of(activity, factory).get(viewModelClass);
    }

    /**
     * Provides instances of a {@link ViewModel} class. Internally this provider will use
     * {@link ViewModelProviders#of(Fragment)} to obtain the instance. This is required
     * so that the view model is registered by the view model extension, and its onCleared
     * method is called.
     * @param fragment holds the view model.
     * @param viewModelClass the class of the view model instance to return.
     */
    public ViewModelProvider(@NonNull Fragment fragment,
                             @NonNull Class<? extends T> viewModelClass) {
        //we should not keep the activity, otherwise, it will leak from the view model scope
        viewModel = ViewModelProviders.of(fragment).get(viewModelClass);
    }

    /**
     * Provides instances of a {@link ViewModel} class. Internally this provider will use
     * {@link ViewModelProviders#of(Fragment)} to obtain the instance. This is required
     * so that the view model is registered by the view model extension, and its onCleared
     * method is called.
     * @param fragment holds the view model.
     * @param factory required to build view model instances.
     * @param viewModelClass the class of the view model instance to return.
     */
    public ViewModelProvider(@NonNull Fragment fragment,
                             @Nullable Factory factory,
                             @NonNull Class<? extends T> viewModelClass) {
        //we should not keep the activity, otherwise, it will leak from the view model scope
        viewModel = ViewModelProviders.of(fragment, factory).get(viewModelClass);
    }

    /**
     * Wraps a view model instance for injection.
     * @param viewModel the view model instance that will always be injected.
     */
    public ViewModelProvider(@NonNull T viewModel) {
        //we should not keep the activity, otherwise, it will leak.
        this.viewModel = viewModel;
    }

    @Override
    public T get() {
        return viewModel;
    }
}