package io.github.doggymentor.exception;

public enum Error {
    USER_NOD_FOUND(1, "A user not found"),
    DOG_NOD_FOUND(2, "A dog not found"),
    DOG_EXIST(3, "You have a dog with the name"),
    INVALID_IMAGE_FORMAT(4, "Invalid image format"),
    COMMAND_NOT_FOUND(5, "A command not found"),
    DOG_HAS_COMMAND(6, "Dog has the command"),
    DOG_NOT_HAS_COMMAND(7, "Dog not has the command");

    private final int id;
    private final String message;

    Error(final int id, final String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
