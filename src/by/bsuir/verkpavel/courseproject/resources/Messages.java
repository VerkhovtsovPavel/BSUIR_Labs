package by.bsuir.verkpavel.courseproject.resources;

public enum Messages {
    INVALID_USERNAME_AND_PASSWORD("Введены неверные имя пользователя и пароль"),
    CLIENT_SUCCESSFULLY_ADDED("Клиент успешно добавлен"),
    ERROR_WHILE_ADD_CLIENT("Ошибка при добавлении клиента");

    private String value;

    private Messages(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
