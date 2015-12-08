package by.bsuir.verkpavel.courseproject.resources;

public enum Messages {
    INVALID_USERNAME_AND_PASSWORD("Введены неверные имя пользователя и пароль"),
    CLIENT_SUCCESSFULLY_ADDED("Клиент успешно добавлен"),
    ERROR_WHILE_ADD_RECORD("Ошибка при добавлении записи. Проверьте введенные данные или свяжитесь с администратором базы данных или тех. поддержкой"),
    EMPLOYEE_SUCCESSFULLY_ADDED("Сотрудник успешно добавлен"),
    PARCEL_SUCCESSFULLY_ADDED("Посылка успешно добавлен"),
    ERROR_WHILE_DELETE_RECORD("Ошибка при удалении записи. Проверьте введенные данные или свяжитесь с администратором базы данных или тех. поддержкой"),
    CONFIRM_DELETE("Вы уверены что хотите удалить запись?"),
    ERROR_WHILE_UPDATE_RECORD("Ошибка при удалении записи. Проверьте введенные данные или свяжитесь с администратором базы данных или тех. поддержкой");

    private String value;

    private Messages(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
