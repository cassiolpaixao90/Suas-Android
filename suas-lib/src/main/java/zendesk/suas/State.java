package zendesk.suas;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of state that's used in a {@link DefaultStore}.
 */
public class State implements Serializable {

    static String keyForClass(Class clazz) {
        return clazz.getSimpleName();
    }

    private final Map<String, Object> state;

    public State(@NonNull Map<String, Object> state) {
        this.state = new HashMap<>(state);
    }

    public State() {
        this.state = new HashMap<>();
    }

    @Nullable
    public Object getState(@NonNull String key) {
        return state.get(key);
    }

    @Nullable
    public <E> E getState(@NonNull Class<E> clazz) {
        final Object data = state.get(keyForClass(clazz));
        if(clazz.isInstance(data)) {
            //noinspection unchecked
            return (E) data;
        } else {
            return null;
        }
    }

    @Nullable
    public <E> E getState(@NonNull String stateKey, @NonNull Class<E> clazz) {
        final Object data = state.get(stateKey);
        if(clazz.isInstance(data)) {
            //noinspection unchecked
            return (E) data;
        } else {
            return null;
        }
    }

    @NonNull
    State copy() {
        return new State(new HashMap<>(state));
    }

    /**
     * Add or update the provided scope.
     */
    void updateKey(String key, Object newState) {
        state.put(key, newState);
    }

    <E> void updateKey(Class<E> stateKey, E newState) {
        state.put(keyForClass(stateKey), newState);
    }

    Collection<String> getStateKeys() {
        return state.keySet();
    }

    @Override
    public String toString() {
        return "State{" +
                "state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state1 = (State) o;

        return state.equals(state1.state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }
}

