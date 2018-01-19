package zendesk.suas;


import android.support.annotation.NonNull;

/**
 * The {@code Store} contains the application's state, reducer logic, middleware and listeners.
 *
 * <ul>
 *     <li>{@link State} represents the application state. The state is partition into state keys.</li>
 *     <li>{@link Reducer} represents the logic to update the {@link State}. A reducer provides the functionality to update the state for a particular {@link Action}.</li>
 *     <li>{@link Middleware} is a function that intercepts an {@link Action} and can enrich or alter it before reaching the {@link Reducer}.</li>
 *     <li>{@link Listener} a function that gets called on state changes</li>
 * </ul>
 */
public interface Store extends GetState, Dispatcher {

    /**
     * Resets the full internal state with a new state and notifies all registered {@link Listener}
     *
     * @param state the new state
     */
    void reset(@NonNull State state);


    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any state changes.
     *     If nothing else provided {@link Filters#DEFAULT} will be used to decide whether to
     *     notify or not.
     * </p>
     *
     * @param listener callback to be notified on state changes
     * @return a subscription for managing the listener's lifecycle
     */
    Subscription addListener(@NonNull Listener<State> listener);

    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any state changes.
     *     The provided {@link Filter} is used to decide whether to notify or not.
     * </p>
     *
     * @param filter function used to decide whether to notify or not
     * @param listener callback to be notified on state changes
     * @return a subscription for managing the listener's lifecycle
     */
    Subscription addListener(@NonNull Filter<State> filter, @NonNull Listener<State> listener);

    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any state changes.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code key}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     If nothing else provided {@link Filters#DEFAULT} will be used to decide whether to
     *     notify or not.
     * </p>
     *
     * @param stateSelector function for converting {@link State} to an object of type {@code <E>}
     * @param listener callback to be notified on state changes
     * @param <E> type of the returned object of the state selector
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull StateSelector<E> stateSelector, @NonNull Listener<E> listener);

    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any state changes.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code key}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     If nothing else provided {@link Filters#DEFAULT} will be used to decide whether to
     *     notify or not.
     * </p>
     *
     * @param filter function used to decide whether to notify or not
     * @param stateSelector function for converting {@link State} to an object of type {@code <E>}
     * @param listener callback to be notified on state changes
     * @param <E> type of the returned object of the state selector
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull Filter<State> filter, @NonNull StateSelector<E> stateSelector, @NonNull Listener<E> listener);


    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any changes to the part state that's tied
     *     to the passed in key.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code key}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     If nothing else provided {@link Filters#DEFAULT} will be used to decide whether to
     *     notify or not.
     * </p>
     *
     * @param stateKey the state key to listen for changes
     * @param listener callback to be notified on state changes
     * @param <E> type of the state that's registered on the provided {@code key}
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull String stateKey, @NonNull Listener<E> listener);

    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any changes to the part state that's tied
     *     to the passed in key.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code key}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     The provided {@link Filter} is used to decide whether to notify or not.
     * </p>
     *
     * @param stateKey the state key to listen for changes
     * @param filter function used to decide whether to notify or not
     * @param listener callback to be notified on state changes
     * @param <E> type of the state that's registered on the provided {@code key}
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull String stateKey, @NonNull Filter<E> filter, @NonNull Listener<E> listener);


    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any changes to the part state that's tied
     *     to the passed in {@link Class}.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code class}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     If nothing else provided {@link Filters#DEFAULT} will be used to decide whether to
     *     notify or not.
     * </p>
     *
     * @param clazz the state key and type listen for changes
     * @param listener callback to be notified on state changes
     * @param <E> type of the state that's registered on the provided {@code key}
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull Class<E> clazz, @NonNull Listener<E> listener);

    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any changes to the part state that's tied
     *     to the passed in {@link Class}.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code class}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     The provided {@link Filter} is used to decide whether to notify or not.
     * </p>
     *
     * @param clazz the state key and type to listen for changes
     * @param filter function used to decide whether to notify or not
     * @param listener callback to be notified on state changes
     * @param <E> type of the state that's registered on the provided {@code clazz}
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull Class<E> clazz, @NonNull Filter<E> filter, @NonNull Listener<E> listener);


    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any changes to the part state that's tied
     *     to the passed in {@code key} and of type {@code clazz}.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code key}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     If nothing else provided {@link Filters#DEFAULT} will be used to decide whether to
     *     notify or not.
     * </p>
     *
     * @param stateKey the state key to listen for changes
     * @param clazz the state type to listen for changes
     * @param listener callback to be notified on state changes
     * @param <E> type of the state that's registered on the provided {@code key}
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull String stateKey, @NonNull Class<E> clazz, @NonNull Listener<E> listener);

    /**
     * Adds a new {@link Listener} to the store.
     *
     * <p>
     *     The provided {@link Listener} will be notified on any changes to the part state that's tied
     *     to the passed in {@code key} and of type {@code clazz}.
     *     If the provided type {@code E} doesn't match the object behind the passed in {@code key}
     *     the listener will never be notified and a warning will be logged.
     *     <br>
     *     The provided {@link Filter} is used to decide whether to notify or not.
     * </p>
     *
     * @param stateKey the state key to listen for changes
     * @param clazz the state type to listen for changes
     * @param filter function used to decide whether to notify or not
     * @param listener callback to be notified on state changes
     * @param <E> type of the state that's registered on the provided {@code key}
     * @return a subscription for managing the listener's lifecycle
     */
    <E> Subscription addListener(@NonNull String stateKey, @NonNull Class<E> clazz, @NonNull Filter<E> filter, @NonNull Listener<E> listener);

    /**
     * Adds a new {@link Listener<Action>} to the store.
     *
     * <p>
     *      Action listeners will be notified whenever a new action is dispatched. Inside the
     *      action notification you cast to the specific action and read the payload of the action.
     * </p>
     * @param actionListener callback to be notified when an action happens.
     * @return a subscription for managing the listener's lifecycle
     */
    Subscription addActionListener(Listener<Action<?>> actionListener);

    /**
     * Remove a listener from the store.
     *
     * @param listener the listener to remove
     */
    void removeListener(@NonNull Listener<?> listener);

}
