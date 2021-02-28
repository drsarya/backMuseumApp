package service.model;


import org.immutables.value.Value;

@Value.Immutable
public interface ExistingUser extends NewUser {
    long getId();
}
